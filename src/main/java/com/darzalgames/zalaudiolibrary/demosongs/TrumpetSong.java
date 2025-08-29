package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.*;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;
import com.darzalgames.zalaudiolibrary.synth.Synth;
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

		TrumpetComplexSynth trumpetComplexSynth = new TrumpetComplexSynth();

		main = new CompositeTrack(trumpetComplexSynth, getSongName(), "trumpet", 0.5f);
		addTrack(main);
		mainOctave = new CompositeTrack(new TrumpetComplexSynth(), getSongName(), "trumpet bright", 0.2f);
		addTrack(mainOctave);

		secondary = new CompositeTrack(new TrumpetComplexSynth(), getSongName(), "trumpet accompaniment", 0.3f);
		addTrack(secondary);
		tuba = new CompositeTrack(new TrumpetComplexSynth(1f, false), getSongName(), "tuba", 0.2f);
		addTrack(tuba);
		tuba.addMusicalEffect(new TransposeEffect(Pitch::octaveDown));

		SequentialTrack perc = new SequentialTrack(getSongName(), "perc", new Instrument(Synth.brownianNoise(0.7f), ArEnvelope.quadratic(0.01f, 0.15f)), 0.01f);
		perc.addNote(SIXTEENTH, Pitch.C4);
		perc.addSilence(SIXTEENTH);
		perc.addNote(SIXTEENTH, Pitch.C4);
		perc.addNote(SIXTEENTH, Pitch.C4);
		addTrack(perc);



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
		addNote(HALF_EIGHTH, C4, E3);

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
		mainOctave.addNote(duration, Pitch.makePitch(mainPitch.getName() + "x2", mainPitch.getFrequency()*2));
		secondary.addSilence(duration);
		tuba.addSilence(duration);
	}

	private void addNote(NoteDuration duration, Pitch mainPitch, Pitch secondaryPitch) {
		main.addNote(duration, mainPitch);
		mainOctave.addNote(duration, Pitch.makePitch(mainPitch.getName() + "x2", mainPitch.getFrequency()*2));
		secondary.addNote(duration, secondaryPitch);
		tuba.addNote(duration, secondaryPitch);
	}

}
