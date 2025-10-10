package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class TestLoopSoundArtifactSong extends Song {

	public TestLoopSoundArtifactSong() {
		super("Test Loop Sound Artifact", 2f);

		final float mainAmplitude = 0.55f;

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, mainAmplitude);
		addTrack(mainTrack);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addSilence(NoteDuration.QUARTER);
	}
}
