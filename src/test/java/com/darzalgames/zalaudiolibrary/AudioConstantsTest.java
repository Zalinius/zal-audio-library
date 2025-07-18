package com.darzalgames.zalaudiolibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AudioConstantsTest {

	@Test
	void samplingRate_isEqualToSamplesPerStepTimesStepsPerSecond() {
		assertEquals(AudioConstants.SAMPLING_RATE, AudioConstants.SAMPLES_PER_STEP * AudioConstants.STEPS_PER_SECOND);
	}

}
