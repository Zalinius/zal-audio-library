package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.*;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import com.darzalgames.zalaudiolibrary.composing.CompositeTrack;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.complex.BellComplexSynth;

public class BellSong extends Song {

	public static final Pitch F4s = Pitch.F4.sharpen();
	public static final Pitch G4s = Pitch.G4.sharpen();

	public BellSong() {
		super("Bell Song", 2f);

		BellComplexSynth bellComplexSynth = new BellComplexSynth(1f, Synth.sine());

		CompositeTrack bellTrack = new CompositeTrack(bellComplexSynth, getSongName(), "bell", 1.f);
		addTrack(bellTrack);


		bellTrack.addNote(QUARTER_DOT, E4);
		bellTrack.addNote(EIGHTH, E4);
		bellTrack.addNote(QUARTER, A4);
		bellTrack.addNote(QUARTER, G4s);

		bellTrack.addNote(QUARTER_DOT, D4);
		bellTrack.addNote(EIGHTH, D4);
		bellTrack.addNote(QUARTER, F4s);
		bellTrack.addNote(QUARTER, E4);


		bellTrack.addNote(QUARTER, E4);
		bellTrack.addNote(QUARTER_DOT, E4);
		bellTrack.addNote(EIGHTH, A4);
		bellTrack.addNote(QUARTER, A4);

		bellTrack.addNote(QUARTER, D4);
		bellTrack.addNote(QUARTER_DOT, D4);
		bellTrack.addNote(EIGHTH, G4);
		bellTrack.addNote(QUARTER, G4);


		bellTrack.addNote(QUARTER, E4);
		bellTrack.addNote(QUARTER_DOT, E4);
		bellTrack.addNote(EIGHTH, A4);
		bellTrack.addNote(QUARTER, A4);

		bellTrack.addNote(QUARTER, D4);
		bellTrack.addNote(QUARTER_DOT, D4);
		bellTrack.addNote(EIGHTH, G4);
		bellTrack.addNote(EIGHTH, G4);
		bellTrack.addNote(EIGHTH, F4s);


		bellTrack.addNote(QUARTER_DOT, E4);
		bellTrack.addNote(EIGHTH, E4);
		bellTrack.addNote(QUARTER, A4);
		bellTrack.addNote(QUARTER, G4s);

		bellTrack.addNote(QUARTER_DOT, D4);
		bellTrack.addNote(EIGHTH, D4);
		bellTrack.addNote(QUARTER, F4s);
		bellTrack.addNote(QUARTER, E4);


		bellTrack.addNote(QUARTER_DOT, E4);
		bellTrack.addNote(EIGHTH, E4);
		bellTrack.addNote(QUARTER, A4);
		bellTrack.addNote(QUARTER, G4s);

		bellTrack.addNote(QUARTER_DOT, B4);
		bellTrack.addNote(EIGHTH, B4);
		bellTrack.addNote(EIGHTH, G4);
		bellTrack.addNote(EIGHTH, G4);
		bellTrack.addNote(EIGHTH, F4s);
		bellTrack.addNote(EIGHTH, F4s);

		bellTrack.addNote(WHOLE, E4);
	}

}
