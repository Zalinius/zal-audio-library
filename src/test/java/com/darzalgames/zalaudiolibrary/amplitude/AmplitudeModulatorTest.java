package com.darzalgames.zalaudiolibrary.amplitude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AmplitudeModulatorTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void constructor_withAmplitudeOutsideBounds_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new AmplitudeModulator(-0.1f, 20));
		assertThrows(IllegalArgumentException.class, () -> new AmplitudeModulator(1.1f, 20));
	}

	@Test
	void apply_withAmplitude0_alwaysReturns1() {
		AmplitudeModulator amplitudeModulation = new AmplitudeModulator(0, 20);

		assertEquals(1, amplitudeModulation.apply(0f));
		assertEquals(1, amplitudeModulation.apply(0.1f));
		assertEquals(1, amplitudeModulation.apply(0.23f));
		assertEquals(1, amplitudeModulation.apply(0.98f));
		assertEquals(1, amplitudeModulation.apply(1.2f));
	}

	@Test
	void apply_withAmplitude1_returnsSineWaveValues() {
		AmplitudeModulator amplitudeModulation = new AmplitudeModulator(1, 1);

		assertEquals(0, amplitudeModulation.apply(0f));
		assertEquals(1, amplitudeModulation.apply(0.25f));
		assertEquals(0, amplitudeModulation.apply(0.5f), ALLOWED_ERROR);
		assertEquals(-1, amplitudeModulation.apply(0.75f));
		assertEquals(0, amplitudeModulation.apply(1f), ALLOWED_ERROR);
	}

	@Test
	void apply_withAmplitude1Half_returnsValuesBetween0And1() {
		AmplitudeModulator amplitudeModulation = new AmplitudeModulator(0.5f, 1);

		assertEquals(0.5f, amplitudeModulation.apply(0f));
		assertEquals(1, amplitudeModulation.apply(0.25f));
		assertEquals(0.5f, amplitudeModulation.apply(0.5f), ALLOWED_ERROR);
		assertEquals(0, amplitudeModulation.apply(0.75f));
		assertEquals(0.5f, amplitudeModulation.apply(1f));
	}

}
