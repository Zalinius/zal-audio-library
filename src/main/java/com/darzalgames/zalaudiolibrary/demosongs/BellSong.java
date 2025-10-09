package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.*;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.CompositeTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.EnvelopeReverser;
import com.darzalgames.zalaudiolibrary.effects.tracking.SynthChanger;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.complex.BellComplexSynth;

public class BellSong extends Song {

	public static final Pitch F4s = Pitch.F4.sharpen();
	public static final Pitch G4s = Pitch.G4.sharpen();

	private final CompositeTrack bellTrack;
	private final CompositeTrack bellOctaveTrack;

	public BellSong() {
		super("Bell Song", 2f);

		BellComplexSynth bellComplexSynth = new BellComplexSynth(0.5f, Synth.sine());

		bellTrack = new CompositeTrack(bellComplexSynth, getSongName(), "bell", 0.8f);
		addTrack(bellTrack);
		bellOctaveTrack = new CompositeTrack(bellComplexSynth, getSongName(), "bell octave", 0.8f);
		addTrack(bellOctaveTrack);


		addNote(QUARTER_DOT, E4);
		addNote(EIGHTH, E4);
		addNote(QUARTER, A4);
		addNote(QUARTER, G4s);

		addNote(QUARTER_DOT, D4);
		addNote(EIGHTH, D4);
		addNote(QUARTER, F4s);
		addNote(QUARTER, E4);


		addNote(QUARTER, E4);
		addNote(QUARTER_DOT, E4);
		addNote(EIGHTH, A4);
		addNote(QUARTER, A4);

		addNote(QUARTER, D4);
		addNote(QUARTER_DOT, D4);
		addNote(EIGHTH, G4);
		addNote(QUARTER, G4);


		addNote(QUARTER, E4);
		addNote(QUARTER_DOT, E4);
		addNote(EIGHTH, A4);
		addNote(QUARTER, A4);

		addNote(QUARTER, D4);
		addNote(QUARTER_DOT, D4);
		addNote(EIGHTH, G4);
		addNote(EIGHTH, G4);
		addNote(EIGHTH, F4s);


		addNote(QUARTER_DOT, E4);
		addNote(EIGHTH, E4);
		addNote(QUARTER, A4);
		addNote(QUARTER, G4s);

		addNote(QUARTER_DOT, D4);
		addNote(EIGHTH, D4);
		addNote(QUARTER, F4s);
		addNote(QUARTER, E4);


		addNote(QUARTER_DOT, E4);
		addNote(EIGHTH, E4);
		addNote(QUARTER, A4);
		addNote(QUARTER, G4s);

		addNote(QUARTER_DOT, B4);
		addNote(EIGHTH, B4);
		addNote(EIGHTH, G4);
		addNote(EIGHTH, G4);
		addNote(EIGHTH, F4s);
		addNote(EIGHTH, F4s);

		addNote(WHOLE, E4);
	}

	private void addNote(NoteDuration duration, Pitch pitch) {
		bellTrack.addNote(duration, pitch);
		bellOctaveTrack.addNote(duration, Pitch.makePitch(pitch.getName() + "x2", pitch.getFrequency()*2));
	}

	public void backwards1() {
		bellTrack.addMusicalEffect(new EnvelopeReverser());
	}

	public void backwards2() {
		bellOctaveTrack.addMusicalEffect(new EnvelopeReverser());
	}

	public void makeSquare() {
		bellTrack.addMusicalEffect(new SynthChanger(Synth.square()));
		bellOctaveTrack.addMusicalEffect(new SynthChanger(Synth.square()));
	}


}
