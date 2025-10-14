package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class F_FakeMenuSong extends Song {

	public F_FakeMenuSong() {
		super("Redecarating Awaits", 3f);

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, 0.55f);
		addTrack(mainTrack);
		SequentialTrack bassTrack = new SequentialTrack(getSongName(), "bass", Sbig2025Album.BASS_DRONE, 0.2f);
		addTrack(bassTrack);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.E4);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.D3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.D4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.D4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.F4);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.D4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.E4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.E4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.G4);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.A3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.A4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.F4);
		bassTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.G3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.G4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.E4);

		bassTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.F3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);

		bassTrack.addNote(NoteDuration.QUARTER_DOT, Pitch.D3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

	}
}
