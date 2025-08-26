package com.darzalgames.zalaudiolibrary.effects.sampling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SampleOverflowerTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void constructor_withNegativeCuttoff_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SampleOverflower(-0.2f));
	}

	@Test
	void apply_onValuesBelowThreshold_doesNotOverflow() {
		SampleEffect sampleEffect = new SampleOverflower(0.5f);

		assertEquals(0.2f, sampleEffect.apply(0.2f));
		assertEquals(0.0f, sampleEffect.apply(0.0f));
		assertEquals(-0.4f, sampleEffect.apply(-0.4f));
	}

	@Test
	void apply_beyondThreshold_overflowsPositiveValuesAndUnderflowsNegativeValues() {
		SampleEffect sampleEffect = new SampleOverflower(0.5f);

		assertEquals(-0.2f, sampleEffect.apply(0.8f), ALLOWED_ERROR);
		assertEquals(-0.0f, sampleEffect.apply(1.0f), ALLOWED_ERROR);
		assertEquals(0.3f, sampleEffect.apply(-0.7f), ALLOWED_ERROR);
	}

}
