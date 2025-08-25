package com.darzalgames.zalaudiolibrary;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.effects.tracking.EnvelopeReverser;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestSong extends Song {

	public TestSong() {
		super("test", 1f);
		Synth synth = Synth.triangle();
		Envelope envelope = AdsrEnvelope.linear(.01f, .09f, .3f, .4f);

		Track mainTrack = createTrack("main", new Instrument(synth, envelope), 1f);
		mainTrack.addEffect(new EnvelopeReverser());
		//		mainTrack.addEffect(new SynthClipper(0.7f));
		//		addSampleEffect(new SampleClipper(0.5f));
		//		addSampleEffect(new SampleExploder(1f));
		//		mainTrack.addEffect(new SynthExploder(0.3f));

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
