package com.darzalgames.zalaudiolibrary;

import com.darzalgames.zalaudiolibrary.amplitude.AsrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestSong extends Song {

	public TestSong() {
		super("test");
		Synth synth = Synth.triangle();
		Envelope envelope = new AsrEnvelope(0.05f, 1f, 0.05f);

		Track mainTrack = createTrack("main", new Instrument(synth, envelope));

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.HALF, Pitch.E4, envelope);


		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.F4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.F4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.B3, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.B3, envelope);
		mainTrack.addNote(synth, NoteDuration.HALF, Pitch.G3, envelope);


		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.C4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.HALF, Pitch.C5, envelope);


		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.B4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.B4, envelope);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.B4, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.C5, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.C5, envelope);
		mainTrack.addNote(synth, NoteDuration.HALF, Pitch.C5, envelope);
	}

}
