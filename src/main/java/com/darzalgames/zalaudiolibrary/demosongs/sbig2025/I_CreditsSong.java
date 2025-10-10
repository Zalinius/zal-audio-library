package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class I_CreditsSong extends Song {

	public I_CreditsSong() {
		super("Credits", 2f);

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.GUITAR, 0.5f);
		addTrack(mainTrack);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.D4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);

		mainTrack.addNote(NoteDuration.HALF, Pitch.C4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C5);
	}
}
