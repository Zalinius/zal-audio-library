package com.darzalgames.zalaudiolibrary.effects.sampling;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SampleClipperTest {

	@Test
	void constructor_withNegativeCuttoff_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SampleClipper(-0.2f));
	}

	@Test
	void apply_onValuesBelowThreshold_doesNotClip() {
		float[] samples = {0.2f, 0f, -0.4f};
		SampleEffect sampleEffect = new SampleClipper(0.5f);

		float[] clipped = sampleEffect.apply(samples);

		assertArrayEquals(samples, clipped);
	}

	@Test
	void apply_beyondThreshold_clipsPositiveAndNegativeValues() {
		float[] samples = {0.8f, 1.0f, -0.7f};
		SampleEffect sampleEffect = new SampleClipper(0.5f);

		float[] clipped = sampleEffect.apply(samples);

		assertFalse(Arrays.equals(samples, clipped));
		assertEquals(0.5f, clipped[0]);
		assertEquals(0.5f, clipped[1]);
		assertEquals(-0.5f, clipped[2]);
	}

}
