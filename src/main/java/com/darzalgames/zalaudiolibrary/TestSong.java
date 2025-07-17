package com.darzalgames.zalaudiolibrary;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.composing.Song;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestSong extends Song {

	public TestSong() {
		Synth synth = Synth.triangle();

		addNote(synth, NoteDuration.QUARTER, Pitch.C4);
		addNote(synth, NoteDuration.QUARTER, Pitch.C4);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4);

		addNote(synth, NoteDuration.QUARTER, Pitch.G4);
		addNote(synth, NoteDuration.QUARTER, Pitch.G4);
		addNote(synth, NoteDuration.HALF, Pitch.E4);


		addNote(synth, NoteDuration.QUARTER, Pitch.F4);
		addNote(synth, NoteDuration.QUARTER, Pitch.F4);
		addNote(synth, NoteDuration.QUARTER, Pitch.D4);
		addNote(synth, NoteDuration.QUARTER, Pitch.D4);

		addNote(synth, NoteDuration.QUARTER, Pitch.B3);
		addNote(synth, NoteDuration.QUARTER, Pitch.B3);
		addNote(synth, NoteDuration.HALF, Pitch.G3);


		addNote(synth, NoteDuration.QUARTER, Pitch.C4);
		addNote(synth, NoteDuration.QUARTER, Pitch.C4);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4);

		addNote(synth, NoteDuration.QUARTER, Pitch.G4);
		addNote(synth, NoteDuration.QUARTER, Pitch.G4);
		addNote(synth, NoteDuration.HALF, Pitch.C5);


		addNote(synth, NoteDuration.QUARTER, Pitch.B4);
		addNote(synth, NoteDuration.QUARTER, Pitch.B4);
		addNote(synth, NoteDuration.EIGHTH, Pitch.A4);
		addNote(synth, NoteDuration.EIGHTH, Pitch.G4);
		addNote(synth, NoteDuration.EIGHTH, Pitch.A4);
		addNote(synth, NoteDuration.EIGHTH, Pitch.B4);

		addNote(synth, NoteDuration.QUARTER, Pitch.C5);
		addNote(synth, NoteDuration.QUARTER, Pitch.C5);
		addNote(synth, NoteDuration.HALF, Pitch.C5);
	}

}
