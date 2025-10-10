package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class B_MenuSong extends Song {

	public B_MenuSong() {
		super("Adventure Awaits", 3f);

		final float mainAmplitude = 0.55f;
		final float drumAmplitude = 0.2f;

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, mainAmplitude);
		addTrack(mainTrack);
		SequentialTrack bassTrack = createTrack("bass", Sbig2025Album.BASS_DRONE, drumAmplitude);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.E4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.G3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.G4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.C5);
		mainTrack.addSilence(NoteDuration.EIGHTH);
	}
}
