package com.darzalgames.zalaudiolibrary.pipeline;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.functional.Do;
import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.TestTools.AudioConsumerSpy;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.demosongs.sbig2025.Sbig2025Album;

class LoopingSoundArtifactBugIT {

	@Test
	void samples_atNotesLoopPoint_arentZero() {
		TestLoopSong testSong = new TestLoopSong();
		AudioConsumerSpy audioConsumerSpy = new AudioConsumerSpy();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumerSpy, 1, 0);
		audioPipeline.changeSong(testSong);
		int steps = 4 * AudioConstants.STEPS_PER_SECOND;

		Do.xTimes(steps, () -> audioPipeline.processMusicStep());

		assertNotEquals(0f, audioConsumerSpy.getSample(1));
		assertNotEquals(0f, audioConsumerSpy.getSample(44101));
	}

	private static class TestLoopSong extends Song {

		private final SequentialTrack mainTrack;

		public TestLoopSong() {
			super("Test Loop", 2f);

			mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, 1f);
			addTrack(mainTrack);

			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addSilence(NoteDuration.QUARTER);
		}
	}
}
