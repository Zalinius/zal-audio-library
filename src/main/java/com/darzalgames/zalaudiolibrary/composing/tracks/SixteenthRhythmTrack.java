package com.darzalgames.zalaudiolibrary.composing.tracks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

public class SixteenthRhythmTrack implements Track {

	private final String songName;
	private final String trackName;
	private final Instrument instrument;
	private final float amplitude;
	private final Pitch pitch;

	private final Function<Integer, Boolean> rhythm;
	private final int rhythmLength;

	public SixteenthRhythmTrack(String songName, String trackName, Instrument instrument, float amplitude, List<Boolean> rhythm, Pitch pitch) {
		this.songName = songName;
		this.trackName = trackName;
		this.instrument = instrument;
		this.amplitude = amplitude;
		this.pitch = pitch;

		Tuple<Integer, Function<Integer, Boolean>> rhythmTuple = makeSixteenthRhythm(rhythm);
		rhythmLength = rhythmTuple.e();
		this.rhythm = rhythmTuple.f();
	}

	public static Tuple<Integer, Function<Integer, Boolean>> makeSixteenthRhythm(List<Boolean> rhythm) {
		return new Tuple<>(rhythm.size(), sixteenth -> rhythm.get(sixteenth));
	}

	public static Tuple<Integer, Function<Integer, Boolean>> makeSixteenthRhythm(boolean... rhythm) {
		List<Boolean> rhythmList = new ArrayList<>();
		for (boolean b : rhythm) {
			rhythmList.add(b);
		}
		return makeSixteenthRhythm(rhythmList);
	}

	@Override
	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat) {
		List<TimedMusicalInstant> timedMusicalInstants = new ArrayList<>();

		for (int sixteenth = 0; sixteenth < rhythmLength; sixteenth++) {
			if (rhythm.apply(sixteenth)) {
				Fraction instantBeat = Fraction.add(new Fraction(startBeat), NoteDuration.SIXTEENTH.inBeats().scale(sixteenth));
				String id = getIdPrefix() + sixteenth;
				MusicalInstant rhythmInstant = new MusicalInstant(instrument.synth(), pitch, NoteDuration.SIXTEENTH, instrument.envelope(), amplitude, id);
				timedMusicalInstants.add(new TimedMusicalInstant(instantBeat, rhythmInstant));
			}
		}

		return timedMusicalInstants;
	}

	@Override
	public void padWithSilence(Fraction beats) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMusicalEffect(MusicalEffect musicalEffect) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getIdPrefix() {
		return songName + " - " + trackName + " - ";
	}

}
