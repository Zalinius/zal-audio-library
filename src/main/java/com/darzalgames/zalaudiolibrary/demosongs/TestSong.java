package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.amplitude.AmplitudeModulator;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestSong extends Song {

	public TestSong() {
		super("test", 2);
		Synth synth = Synth.sine();
		Envelope envelope = AdsrEnvelope.quadratic(.01f, .09f, .3f, .2f);
		AmplitudeModulator amplitudeModulator = new AmplitudeModulator(1f, 2);
		envelope = amplitudeModulator.modulateEnvelope(envelope);

		SequentialTrack mainTrack = createTrack("main", new Instrument(synth, envelope), 1f);

		// mainTrack.addEffect(new EnvelopeReverser());
		// mainTrack.addEffect(new TransposeEffect(Pitch::down));

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.E4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3);
		mainTrack.addNote(NoteDuration.HALF, Pitch.G3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C5);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.B4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C5);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C5);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C5);
	}

}
