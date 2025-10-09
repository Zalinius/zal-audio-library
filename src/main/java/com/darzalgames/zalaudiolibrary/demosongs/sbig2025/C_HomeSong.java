package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class C_HomeSong extends Song {

	public C_HomeSong() {
		super("Home", 1f);

		SequentialTrack mainTrack = createTrack("main", Sbig2025Album.GUITAR, 0.7f);

		SequentialTrack rhythmTrack = createTrack("rhythm", Sbig2025Album.RHYTHM, 0.4f);
		rhythmTrack.addSilence(NoteDuration.EIGHTH);
		rhythmTrack.addNote(NoteDuration.EIGHTH, Pitch.C2);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addSilence(NoteDuration.QUARTER);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B2);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B2);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B2);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addSilence(NoteDuration.QUARTER);
	}
}
