package com.darzalgames.zalaudiolibrary;

import com.darzalgames.zalaudiolibrary.amplitude.AsrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.composing.Song;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestSong extends Song {

	public TestSong() {
		Synth synth = Synth.triangle();
		Envelope envelope = new AsrEnvelope(0.05f, 1f, 0.05f);


		addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);

		addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		addNote(synth, NoteDuration.HALF, Pitch.E4, envelope);


		addNote(synth, NoteDuration.QUARTER, Pitch.F4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.F4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);

		addNote(synth, NoteDuration.QUARTER, Pitch.B3, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.B3, envelope);
		addNote(synth, NoteDuration.HALF, Pitch.G3, envelope);


		addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);

		addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		addNote(synth, NoteDuration.HALF, Pitch.C5, envelope);


		addNote(synth, NoteDuration.QUARTER, Pitch.B4, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.B4, envelope);
		addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		addNote(synth, NoteDuration.EIGHTH, Pitch.G4, envelope);
		addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		addNote(synth, NoteDuration.EIGHTH, Pitch.B4, envelope);

		addNote(synth, NoteDuration.QUARTER, Pitch.C5, envelope);
		addNote(synth, NoteDuration.QUARTER, Pitch.C5, envelope);
		addNote(synth, NoteDuration.HALF, Pitch.C5, envelope);
	}

}
