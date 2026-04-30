package com.darzalgames.zalaudiolibrary.pipeline;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.TestTools;
import com.darzalgames.zalaudiolibrary.TestTools.AudioConsumerSpy;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

class StepBoundaryArtifactBugIT {

	@Test
	void samples_atStepBoundary_arentEqualButAreClose() {
		TestSong testSong = new TestSong();
		AudioConsumerSpy audioConsumerSpy = new AudioConsumerSpy();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumerSpy, 1, 0);
		audioPipeline.requestChangeSong(testSong);

		audioPipeline.processMusicStep();
		audioPipeline.processMusicStep();
		float firstStepLastSample = audioConsumerSpy.getSample(AudioConstants.SAMPLES_PER_STEP - 1);
		float secondStepFirstSample = audioConsumerSpy.getSample(AudioConstants.SAMPLES_PER_STEP);
		float difference = Math.abs(firstStepLastSample - secondStepFirstSample);

		assertNotEquals(firstStepLastSample, secondStepFirstSample);
		assertTrue(difference < 0.05f);
	}

	private static class TestSong extends Song {

		private final SequentialTrack mainTrack;

		public TestSong() {
			super("Test Step Boundary", 1f);

			mainTrack = new SequentialTrack(getSongName(), "main", TestTools.FULL_SINE, 1f);
			addTrack(mainTrack);

			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.F4);
		}
	}

}
