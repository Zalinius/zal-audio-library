package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.*;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import com.darzalgames.zalaudiolibrary.composing.CompositeTrack;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.synth.complex.TrumpetComplexSynth;

public class TrumpetSong extends Song {

	public static final Pitch F4s = Pitch.F4.sharpen();
	public static final Pitch G4s = Pitch.G4.sharpen();

	public TrumpetSong() {
		super("Bell Song", 2f);

		TrumpetComplexSynth trumpetComplexSynth = new TrumpetComplexSynth();

		CompositeTrack trumpetTrack = new CompositeTrack(trumpetComplexSynth, getSongName(), "trumpet");
		addTrack(trumpetTrack);


		trumpetTrack.addNote(SIXTEENTH, E4);
		trumpetTrack.addNote(SIXTEENTH, F4);
		trumpetTrack.addNote(SIXTEENTH, G4);
		trumpetTrack.addNote(SIXTEENTH, G4s);


		trumpetTrack.addNote(QUARTER_DOT, A4);
		trumpetTrack.addNote(EIGHTH, G4s);

		trumpetTrack.addNote(QUARTER_DOT, G4);
		trumpetTrack.addNote(EIGHTH, F4s);

		trumpetTrack.addNote(EIGHTH_DOT, F4);
		trumpetTrack.addNote(EIGHTH_DOT, G4);
		trumpetTrack.addNote(QUARTER_DOT, E4);
		trumpetTrack.addNote(SIXTEENTH, E4);
		trumpetTrack.addNote(SIXTEENTH, F4);
		trumpetTrack.addNote(SIXTEENTH, G4);
		trumpetTrack.addNote(SIXTEENTH, G4s);



		trumpetTrack.addNote(QUARTER_DOT, A4);
		trumpetTrack.addNote(EIGHTH, G4s);
		trumpetTrack.addNote(QUARTER_DOT, G4);
		trumpetTrack.addNote(EIGHTH, F4s);

		trumpetTrack.addNote(EIGHTH_DOT, F4);
		trumpetTrack.addNote(EIGHTH_DOT, E4);
		trumpetTrack.addNote(QUARTER_DOT, C4);
		trumpetTrack.addNote(QUARTER, C4);

		trumpetTrack.addNote(EIGHTH, A4);
		trumpetTrack.addNote(EIGHTH, A4);
		trumpetTrack.addNote(EIGHTH, G4s);
		trumpetTrack.addNote(EIGHTH, G4s);
		trumpetTrack.addNote(EIGHTH, G4);
		trumpetTrack.addNote(EIGHTH, G4);
		trumpetTrack.addNote(EIGHTH, F4s);
		trumpetTrack.addNote(EIGHTH, F4s);


		trumpetTrack.addNote(EIGHTH_DOT, F4);
		trumpetTrack.addNote(EIGHTH_DOT, G4);
		trumpetTrack.addNote(QUARTER_DOT, E4);
		trumpetTrack.addNote(QUARTER, E4);


		trumpetTrack.addNote(EIGHTH, A4);
		trumpetTrack.addNote(EIGHTH, A4);
		trumpetTrack.addNote(EIGHTH, G4s);
		trumpetTrack.addNote(EIGHTH, G4s);
		trumpetTrack.addNote(EIGHTH, G4);
		trumpetTrack.addNote(EIGHTH, G4);
		trumpetTrack.addNote(EIGHTH, F4s);
		trumpetTrack.addNote(EIGHTH, F4s);


		trumpetTrack.addNote(EIGHTH_DOT, F4);
		trumpetTrack.addNote(EIGHTH_DOT, E4);
		trumpetTrack.addNote(QUARTER_DOT, C4);
		trumpetTrack.addNote(QUARTER, C4);

	}

}
