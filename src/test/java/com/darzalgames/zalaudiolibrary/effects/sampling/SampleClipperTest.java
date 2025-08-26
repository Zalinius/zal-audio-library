package com.darzalgames.zalaudiolibrary.effects.sampling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SampleClipperTest {

	@Test
	void constructor_withNegativeCuttoff_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SampleClipper(-0.2f));
	}

	@Test
	void apply_onValuesBelowThreshold_doesNotClip() {
		SampleEffect sampleEffect = new SampleClipper(0.5f);

		assertEquals(0.2f, sampleEffect.apply(0.2f));
		assertEquals(0.0f, sampleEffect.apply(0.0f));
		assertEquals(-0.4f, sampleEffect.apply(-0.4f));
	}

	@Test
	void apply_beyondThreshold_clipsPositiveAndNegativeValues() {
		SampleEffect sampleEffect = new SampleClipper(0.5f);

		assertEquals(0.5f, sampleEffect.apply(0.8f));
		assertEquals(0.5f, sampleEffect.apply(1.0f));
		assertEquals(-0.5f, sampleEffect.apply(-0.7f));
	}

}
