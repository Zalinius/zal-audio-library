package com.darzalgames.zalaudiolibrary.composing;

import java.util.*;
import java.util.Map.Entry;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SequentialTrack implements Track {

	private final NavigableMap<Fraction, MusicalInstant> trackMelody;
	private final String songName;
	private final String trackName;
	private final Instrument instrument;
	private final float amplitude;

	private final List<MusicalEffect> trackEffects;


	public SequentialTrack(String songName, String trackName, Instrument instrument) {
		this(songName, trackName, instrument, 0.1f);
	}

	public SequentialTrack(String songName, String trackName, Instrument instrument, float amplitude) {
		trackMelody = new TreeMap<>();
		this.songName = songName;
		this.trackName = trackName;
		this.instrument = instrument;
		this.amplitude = amplitude;
		trackEffects = new ArrayList<>();
	}

	public void addNote(NoteDuration duration, Pitch pitch) {
		addNote(instrument.synth(), duration, pitch, instrument.envelope());
	}

	public void addSilence(NoteDuration duration) {
		addNote(Synth.zero(), duration, Pitch.NONE, ConstantEnvelope.zeroEnvelope());
	}

	public void addNote(Synth synth, NoteDuration duration, Pitch pitch, Envelope envelope) {
		Fraction newInstantStartBeat = lengthInBeats();
		String instantId = getIdPrefix() + newInstantStartBeat;
		MusicalInstant newInstant = new MusicalInstant(synth, pitch, duration, envelope, amplitude, instantId);
		trackMelody.put(newInstantStartBeat, newInstant);
	}

	@Override
	public void addMusicalEffect(MusicalEffect trackEffect) {
		trackEffects.add(trackEffect);
	}

	public Fraction lengthInBeats() {
		if(trackMelody.isEmpty()) {
			return new Fraction();
		}
		return Fraction.add(trackMelody.lastKey(), trackMelody.lastEntry().getValue().duration().inBeats());
	}

	@Override
	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat){
		if(trackMelody.isEmpty()) {
			throw new IllegalStateException("Can't call getMusicalInstantsActiveThisBeatInclusive when Track melody empty");
		}
		Fraction beatIndex = Fraction.integerRemainder(new Fraction(startBeat), lengthInBeats());
		List<TimedMusicalInstant> allActiveInstants = new ArrayList<>();
		Fraction endBeat = Fraction.add(beatIndex, new Fraction(1));

		do {
			Entry<Fraction, MusicalInstant> instantAtOrBeforeBeat = trackMelody.floorEntry(beatIndex);
			int trackRepetitionCounter = Fraction.integerDivision(new Fraction(startBeat), lengthInBeats());
			Fraction activeInstantAbsoluteStartTime = Fraction.add(lengthInBeats().scale(trackRepetitionCounter), instantAtOrBeforeBeat.getKey());
			Collection<MusicalInstant> musicalInstants = List.of(instantAtOrBeforeBeat.getValue());
			for (Iterator<MusicalEffect> it = trackEffects.iterator(); it.hasNext();) {
				MusicalEffect trackEffect = it.next();
				Collection<MusicalInstant> affectedInstants = new ArrayList<>();
				musicalInstants.forEach(instant -> affectedInstants.addAll(trackEffect.apply(instant)));
				musicalInstants = affectedInstants;
			}
			musicalInstants.forEach(instant -> allActiveInstants.add(new TimedMusicalInstant(activeInstantAbsoluteStartTime, instant)));

			beatIndex = Fraction.add(instantAtOrBeforeBeat.getKey(), instantAtOrBeforeBeat.getValue().duration().inBeats());
			if(beatIndex.isGreaterThanOrEqual(endBeat) && endBeat.isGreaterThanOrEqual(lengthInBeats())) {
				beatIndex = Fraction.integerRemainder(beatIndex, lengthInBeats());
				endBeat = Fraction.integerRemainder(beatIndex, endBeat);
			}

		} while (beatIndex.isLessThanOrEqual(endBeat));

		return allActiveInstants;
	}

	@Override
	public boolean isValid() {
		Fraction trackLength = lengthInBeats();

		return !trackLength.isZero() && trackLength.isInteger();
		//TODO check track length works with the song's time signature
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
