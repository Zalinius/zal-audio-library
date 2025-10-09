package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.*;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.CompositeTrack;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.complex.ComplexSynth;
import com.darzalgames.zalaudiolibrary.synth.complex.TrumpetComplexSynth;

public class TrumpetSong extends Song {

	public static final Pitch F4s = Pitch.F4.sharpen();
	public static final Pitch G4s = Pitch.G4.sharpen();

	public static final NoteDuration QUARTER_SIXTEENTH = NoteDuration.tie(QUARTER, SIXTEENTH);
	public static final NoteDuration HALF_EIGHTH = NoteDuration.tie(HALF, EIGHTH);

	private final CompositeTrack main;
	private final CompositeTrack mainOctave;
	private final CompositeTrack secondary;
	private final CompositeTrack tuba;

	public TrumpetSong() {
		super("Trumpet Song", 2f);

		ComplexSynth trumpetComplexSynth = new TrumpetComplexSynth(1f, true);
		ComplexSynth tubaComblexSynth = new TrumpetComplexSynth(1f, false);

		main = new CompositeTrack(trumpetComplexSynth, getSongName(), "trumpet", 0.34f);
		addTrack(main);

		mainOctave = new CompositeTrack(trumpetComplexSynth, getSongName(), "trumpet bright", 0.34f);
		addTrack(mainOctave);

		secondary = new CompositeTrack(trumpetComplexSynth, getSongName(), "trumpet accompaniment", 0.2f);
		addTrack(secondary);

		tuba = new CompositeTrack(tubaComblexSynth, getSongName(), "tuba", 0.2f);
		addTrack(tuba);

		SequentialTrack percLow = new SequentialTrack(getSongName(), "perc Low", new Instrument(Synth.brownianNoise(0.7f), ArEnvelope.quadratic(0.01f, 0.15f)), 0.1f);
		SequentialTrack percHi = new SequentialTrack(getSongName(), "perc Hi", new Instrument(Synth.brownianNoise(0.5f), ArEnvelope.quadratic(0.01f, 0.15f)), 0.15f);
		addTrack(percLow);
		addTrack(percHi);

		percLow.addNote(SIXTEENTH, Pitch.C4);
		percLow.addSilence(SIXTEENTH);
		percLow.addNote(SIXTEENTH, Pitch.C4);
		percLow.addSilence(SIXTEENTH);

		percLow.addNote(SIXTEENTH, Pitch.C4);
		percLow.addSilence(SIXTEENTH);
		percLow.addNote(SIXTEENTH, Pitch.C4);
		percLow.addSilence(SIXTEENTH);

		percHi.addNote(SIXTEENTH, Pitch.C4);
		percHi.addSilence(SIXTEENTH);
		percHi.addSilence(SIXTEENTH);
		percHi.addSilence(SIXTEENTH);

		percHi.addSilence(SIXTEENTH);
		percHi.addSilence(SIXTEENTH);
		percHi.addSilence(SIXTEENTH);
		percHi.addSilence(SIXTEENTH);

		addNote(SIXTEENTH, E4);
		addNote(SIXTEENTH, F4);
		addNote(SIXTEENTH, G4);
		addNote(SIXTEENTH, G4s);

		addNote(QUARTER_SIXTEENTH, A4, C4);
		addSilence(SIXTEENTH);
		addNote(EIGHTH, G4s);
		addNote(QUARTER_SIXTEENTH, G4, C4);
		addSilence(SIXTEENTH);
		addNote(EIGHTH, F4s);

		addNote(EIGHTH_DOT, F4, C4);
		addNote(EIGHTH_DOT, G4, B3);
		addNote(QUARTER_DOT, E4, G3);
		addNote(SIXTEENTH, E4);
		addNote(SIXTEENTH, F4);
		addNote(SIXTEENTH, G4);
		addNote(SIXTEENTH, G4s);

		addNote(QUARTER_DOT, A4, C4);
		addNote(EIGHTH, G4s);
		addNote(QUARTER_DOT, G4, C4);
		addNote(EIGHTH, F4s);

		addNote(EIGHTH_DOT, F4, C4);
		addNote(EIGHTH_DOT, E4, B3);
		addNote(HALF_EIGHTH, C4, E3);

		addNote(EIGHTH, A4, C4);
		addNote(EIGHTH, A4, C4);
		addNote(EIGHTH, G4s, C4);
		addNote(EIGHTH, G4s, C4);
		addNote(EIGHTH, G4, C4);
		addNote(EIGHTH, G4, C4);
		addNote(EIGHTH, F4s, C4);
		addNote(EIGHTH, F4s, C4);

		addNote(EIGHTH_DOT, F4, C4);
		addNote(EIGHTH_DOT, G4, B3);
		addNote(HALF_EIGHTH, E4, G3);

		addNote(EIGHTH, A4, C4);
		addNote(EIGHTH, A4, C4);
		addNote(EIGHTH, G4s, C4);
		addNote(EIGHTH, G4s, C4);
		addNote(EIGHTH, G4, C4);
		addNote(EIGHTH, G4, C4);
		addNote(EIGHTH, F4s, C4);
		addNote(EIGHTH, F4s, C4);

		addNote(EIGHTH_DOT, F4, C4);
		addNote(EIGHTH_DOT, E4, B3);
		addNote(QUARTER_DOT, C4, E3);
		addNote(EIGHTH, C4, E3);
		addSilence(EIGHTH);

		addSilence(HALF_DOT);

	}

	private void addSilence(NoteDuration duration) {
		main.addSilence(duration);
		mainOctave.addSilence(duration);
		secondary.addSilence(duration);
		tuba.addSilence(duration);
	}

	private void addNote(NoteDuration duration, Pitch mainPitch) {
		main.addNote(duration, mainPitch);
		mainOctave.addNote(duration, Pitch.makePitch(mainPitch.getName() + "x2", mainPitch.getFrequency() * 2));
		secondary.addSilence(duration);
		tuba.addSilence(duration);
	}

	private void addNote(NoteDuration duration, Pitch mainPitch, Pitch secondaryPitch) {
		main.addNote(duration, mainPitch);
		mainOctave.addNote(duration, Pitch.makePitch(mainPitch.getName() + "x2", mainPitch.getFrequency() * 2));
		secondary.addNote(duration, secondaryPitch);
		tuba.addNote(duration, secondaryPitch.octaveDown());
	}

	public void makeSad() {
		// TODO Somehow this sounds like a bad organ? to be investigated (as in, make a good organ synth
		main.addMusicalEffect(new TransposeEffect(p -> p.down().up()));
		mainOctave.addMusicalEffect(new TransposeEffect(p -> p.down().up()));
		secondary.addMusicalEffect(new TransposeEffect(p -> p.down().up()));
		tuba.addMusicalEffect(new TransposeEffect(p -> p.down().up()));
	}

}
