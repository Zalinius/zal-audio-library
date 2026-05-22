package com.darzalgames.zalaudiolibrary;

import java.util.List;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SixteenthRhythmTrack;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class MetronomeSong extends Song {

	SixteenthRhythmTrack rhythmTrack;

	protected MetronomeSong() {
		super("metronome", 1);

		List<Boolean> baseRhythm = List.of(
				true, false, false, false,
				true, false, false, false,
				true, false, false, false,
				true, false, false, false
		);

		Instrument instrument = new Instrument(SynthFactory.whiteNoise(), ArEnvelope.quadratic(0.01f, 0.09f));
		rhythmTrack = new SixteenthRhythmTrack(getSongName(), "rhythm", instrument, 0.1f, baseRhythm, Pitch.C3);
		addTrack(rhythmTrack);
	}

}
