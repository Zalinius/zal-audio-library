package com.darzalgames.zalaudiolibrary.effects.sampling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SampleExploderTest {

	@Test
	void constructor_withNegativeCuttoff_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SampleExploder(-0.2f));
	}

	@Test
	void apply_onValuesAboveThreshold_doesNotExplode() {
		SampleEffect sampleEffect = new SampleExploder(0.5f);

		assertEquals(0.8f, sampleEffect.apply(0.8f));
		assertEquals(1.0f, sampleEffect.apply(1.0f));
		assertEquals(-0.7f, sampleEffect.apply(-0.7f));
	}

	@Test
	void apply_belowThreshold_explodesPositiveAndNegativeValues() {
		SampleEffect sampleEffect = new SampleExploder(0.5f);

		assertEquals(0.5f, sampleEffect.apply(0.2f));
		assertEquals(0.5f, sampleEffect.apply(  0f));
		assertEquals(-0.5f, sampleEffect.apply(-0.4f));
	}

}
