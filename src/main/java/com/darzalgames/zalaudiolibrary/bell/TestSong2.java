package com.darzalgames.zalaudiolibrary.bell;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestSong2 extends Song {

	public static final Pitch F4s = makeSharp(Pitch.F4);

	public TestSong2() {
		super("test", 2f);
		Synth synth = Synth.saw();
		Envelope envelope = AdsrEnvelope.quadratic(.01f, .09f, .3f, .3f);

		Track mainTrack = createTrack("main", new Instrument(synth, envelope), 1f);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.A4, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.G4, envelope);


		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.A4, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);
		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.D4, envelope);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.G4, envelope);
		mainTrack.addNote(synth, NoteDuration.EIGHTH, F4s, envelope);

		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		//		mainTrack.addNote(synth, NoteDuration.QUARTER, Pitch.E4, envelope);
		//		mainTrack.addNote(synth, NoteDuration.EIGHTH, Pitch.A4, envelope);
		//		mainTrack.addNote(synth, NoteDuration.QUARTER_DOT, Pitch.G4, envelope);

		mainTrack.addSilence(NoteDuration.WHOLE);
	}

	public static Pitch makeSharp(Pitch pitch) {
		return Pitch.makePitch(pitch.getName() + "#", pitch.getFrequency() * (float)Math.pow(2, 1/12f));
	}

}
