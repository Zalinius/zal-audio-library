package com.darzalgames.zalaudiolibrary.composing.tracks;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.IntFunction;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.validation.CompositionError;
import com.darzalgames.zalaudiolibrary.composing.validation.TrackEmptyError;
import com.darzalgames.zalaudiolibrary.composing.validation.TrackFractionalError;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SequentialTrack implements Track {

	private final NavigableMap<Fraction, List<MusicalInstant>> trackMelody;
	private final String songName;
	private final String trackName;
	private final Instrument instrument;
	private final float amplitude;
	private final List<MusicalEffect> trackEffects;

	private Fraction repetitionPoint;

	public SequentialTrack(String songName, String trackName, Instrument instrument, float amplitude) {
		trackMelody = new TreeMap<>();
		this.songName = songName;
		this.trackName = trackName;
		this.instrument = instrument;
		this.amplitude = amplitude;
		trackEffects = new ArrayList<>();
		repetitionPoint = new Fraction();
	}

	public void addNote(NoteDuration duration, Pitch pitch, Pitch... chord) {
		addNote(instrument.synth(), instrument.envelope(), duration, pitch, chord);
	}

	public void addSilence(NoteDuration duration) {
		addNote(Synth.zero(), ConstantEnvelope.zeroEnvelope(), duration, Pitch.NONE);
	}

	private void addNote(Synth synth, Envelope envelope, NoteDuration duration, Pitch pitch, Pitch... chord) {
		List<MusicalInstant> chordedInstants = new ArrayList<>();

		IntFunction<Float> chordAmplitudeFunction = n -> (1f / (float) Math.sqrt(n));
		float chordAmplitude = amplitude * chordAmplitudeFunction.apply(chord.length + 1);

		Fraction newInstantStartBeat = lengthInBeats();
		String instantId = getIdPrefix() + newInstantStartBeat;
		MusicalInstant mainInstant = new MusicalInstant(synth, pitch, duration, envelope, chordAmplitude, instantId);
		chordedInstants.add(mainInstant);

		for (int i = 0; i < chord.length; i++) {
			String chordId = instantId + "-" + (i + 1);
			MusicalInstant chordInstant = new MusicalInstant(synth, chord[i], duration, envelope, chordAmplitude, chordId);
			chordedInstants.add(chordInstant);
		}

		trackMelody.put(newInstantStartBeat, chordedInstants);
	}

	public void setRepetitionPoint() {
		repetitionPoint = lengthInBeats();
	}

	@Override
	public void addMusicalEffect(MusicalEffect trackEffect) {
		trackEffects.add(trackEffect);
	}

	@Override
	public void padWithSilence(Fraction beats) {
		addSilence(new NoteDuration(beats));
	}

	public Fraction introductionLengthInBeats() {
		return repetitionPoint;
	}

	public Fraction lengthInBeats() {
		if (trackMelody.isEmpty()) {
			return new Fraction();
		}
		return Fraction.add(trackMelody.lastKey(), trackMelody.lastEntry().getValue().get(0).duration().inBeats());
	}

	public Fraction loopingLengthInBeats() {
		return Fraction.subtract(lengthInBeats(), repetitionPoint);
	}

	@Override
	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat) {
		if (trackMelody.isEmpty()) { // TODO can this be removed now thanks to validate()
			throw new IllegalStateException("Can't call getMusicalInstantsActiveThisBeatInclusive when Track melody empty");
		}
		Fraction beatIndex = computeRelativeBeatIndex(new Fraction(startBeat));
		Fraction endBeat = Fraction.add(beatIndex, new Fraction(1));
		List<TimedMusicalInstant> allActiveInstants = new ArrayList<>();

		boolean wrappedAround = false;

		do {
			Entry<Fraction, List<MusicalInstant>> instantAtOrBeforeBeat = trackMelody.floorEntry(beatIndex);
			int trackRepetitionCounter = computeLoopIteration(new Fraction(startBeat));
			if (wrappedAround) {
				trackRepetitionCounter += 1;
			}
			Fraction activeInstantAbsoluteStartTime = Fraction.add(loopingLengthInBeats().scale(trackRepetitionCounter), instantAtOrBeforeBeat.getKey());
			Collection<MusicalInstant> musicalInstants = instantAtOrBeforeBeat.getValue();
			for (Iterator<MusicalEffect> it = trackEffects.iterator(); it.hasNext();) {
				MusicalEffect trackEffect = it.next();
				Collection<MusicalInstant> affectedInstants = new ArrayList<>();
				musicalInstants.forEach(instant -> affectedInstants.addAll(trackEffect.apply(instant)));
				musicalInstants = affectedInstants;
			}
			musicalInstants.forEach(instant -> allActiveInstants.add(new TimedMusicalInstant(activeInstantAbsoluteStartTime, instant)));

			beatIndex = Fraction.add(instantAtOrBeforeBeat.getKey(), instantAtOrBeforeBeat.getValue().get(0).duration().inBeats());
			if (beatIndex.isGreaterThanOrEqual(endBeat) && endBeat.isGreaterThanOrEqual(lengthInBeats())) {
				wrappedAround = true;
				beatIndex = computeRelativeBeatIndex(beatIndex);
				endBeat = Fraction.integerRemainder(beatIndex, endBeat);
			}

		} while (beatIndex.isLessThanOrEqual(endBeat));

		return allActiveInstants;
	}

	private Fraction computeRelativeBeatIndex(Fraction absoluteBeat) {
		if (absoluteBeat.isGreaterThanOrEqual(repetitionPoint)) {
			Fraction loopingLength = loopingLengthInBeats();
			Fraction progressInLoop = Fraction.subtract(absoluteBeat, repetitionPoint);
			Fraction remainderInLoop = Fraction.integerRemainder(progressInLoop, loopingLength);
			return Fraction.add(remainderInLoop, repetitionPoint);
		} else {
			return absoluteBeat;
		}
	}

	private int computeLoopIteration(Fraction absoluteBeat) {
		if (absoluteBeat.isGreaterThanOrEqual(repetitionPoint)) {
			Fraction loopingLength = loopingLengthInBeats();
			Fraction progressInLoop = Fraction.subtract(absoluteBeat, repetitionPoint);
			return Fraction.integerDivision(progressInLoop, loopingLength);
		} else {
			return 0;
		}
	}

	@Override
	public List<CompositionError> validate() {
		List<CompositionError> errors = new ArrayList<>();
		Fraction trackLength = lengthInBeats();

		if (trackLength.isZero()) {
			errors.add(new TrackEmptyError(this));
		}
		if (!trackLength.isInteger()) {
			errors.add(new TrackFractionalError(this));
		}

		return errors;
		// TODO check track length works with the song's time signature
	}

	@Override
	public String getTrackName() {
		return trackName;
	}

	public String getIdPrefix() {
		return songName + " - " + trackName + " - ";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		trackMelody.forEach((f, mi) -> builder.append(f + ":" + mi + '\n'));
		return builder.toString();
	}

}
