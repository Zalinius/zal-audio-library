package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public abstract class AbstractRainbowSeedSong extends Song {

	AbstractRainbowSeedSong(String name) {
		super(name, 3f);

		SequentialTrack mainTrack = new SequentialTrack(name, "main", getMainInstrument(), 0.5f);
		addTrack(mainTrack);

		mainTrack.addNote(NoteDuration.WHOLE, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.D4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.D4);

		mainTrack.addNote(NoteDuration.WHOLE, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.F4);

		mainTrack.addNote(NoteDuration.WHOLE, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.A4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.A4);

		mainTrack.addNote(NoteDuration.WHOLE, Pitch.B4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.B4);
		mainTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.C5);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C5);

		mainTrack.addNote(NoteDuration.SIXTH, Pitch.B4);
		mainTrack.addNote(NoteDuration.THIRD, Pitch.A4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.G4);

		mainTrack.addNote(NoteDuration.SIXTH, Pitch.A4);
		mainTrack.addNote(NoteDuration.THIRD, Pitch.G4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.F4);

		mainTrack.addNote(NoteDuration.SIXTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.THIRD, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.E4);

		mainTrack.addNote(NoteDuration.SIXTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.THIRD, Pitch.E4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.D4);

	}

	public abstract Instrument getMainInstrument();

}
