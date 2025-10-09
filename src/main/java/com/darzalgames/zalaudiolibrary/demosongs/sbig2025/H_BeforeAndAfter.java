package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class H_BeforeAndAfter extends Song {

	public H_BeforeAndAfter() {
		super("Before & After", 4);

		SequentialTrack bassTrack = new SequentialTrack(getSongName(), "bass", Sbig2025Album.GUITAR, 0.7f);
		addTrack(bassTrack);
		SequentialTrack percussionTrack = new SequentialTrack(getSongName(), "perc", Sbig2025Album.PERC, 0.7f);
		addTrack(percussionTrack);

		// Intro
		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C3);
		bassTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.G2);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C3);
		bassTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.G2);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C3);
		bassTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.G2);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addSilence(NoteDuration.HALF);
		bassTrack.addSilence(NoteDuration.QUARTER);
		bassTrack.addSilence(NoteDuration.HALF);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		percussionTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		// looping segment
		bassTrack.setRepetitionPoint();
		percussionTrack.setRepetitionPoint();

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, 0.4f);
		mainTrack.padWithSilence(bassTrack.lengthInBeats());
		mainTrack.setRepetitionPoint();
		addTrack(mainTrack);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4.sharpen());
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4.sharpen());
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4.sharpen());
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4.sharpen());
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4.sharpen());
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4.sharpen());
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.D4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.D4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.B3);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.B3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C3);
		bassTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.G2);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C3);
		bassTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.G2);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C3);
		bassTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		bassTrack.addNote(NoteDuration.HALF, Pitch.G2);

		bassTrack.addNote(NoteDuration.HALF_DOT, Pitch.C3);
		bassTrack.addSilence(NoteDuration.HALF);
		bassTrack.addSilence(NoteDuration.QUARTER);
		bassTrack.addSilence(NoteDuration.HALF);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

		percussionTrack.addSilence(NoteDuration.QUARTER);
		percussionTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		percussionTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

	}

}
