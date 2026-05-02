package com.darzalgames.zalaudiolibrary;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class BlankSong extends Song {

	public BlankSong() {
		super("silence");
		SequentialTrack track = new SequentialTrack(getSongName(), "main", new Instrument(SynthFactory.zero(), ConstantEnvelope.zeroEnvelope()), 0f);
		addTrack(track);
		track.addNote(NoteDuration.WHOLE, Pitch.NONE);
		track.addNote(NoteDuration.WHOLE, Pitch.NONE);
		track.addNote(NoteDuration.WHOLE, Pitch.NONE);
		track.addNote(NoteDuration.WHOLE, Pitch.NONE);
	}
}
