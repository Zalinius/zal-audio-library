package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.synth.Synth;

class SynthOverflowerTest {

	@Test
	void constructor_withNegativeOverflowThreshold_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SynthOverflower(-0.1f));
	}

	@Test
	void overflowSynth_onSineWave_overflowsValuesAboveThreshold() {
		Synth overflowedSynth = SynthOverflower.overflowSynth(Synth.triangle(), 0.75f);

		assertEquals(0, overflowedSynth.f(0));
		assertEquals(0.5f, overflowedSynth.f(0.125f));
		assertEquals(-0.5f, overflowedSynth.f(0.25f));
		assertEquals(0.5f, overflowedSynth.f(0.375f));
		assertEquals(0, overflowedSynth.f(0.5f));
		assertEquals(0, overflowedSynth.f(0));
		assertEquals(-0.5f, overflowedSynth.f(0.625f));
		assertEquals( 0.5f, overflowedSynth.f(0.75f));
		assertEquals(-0.5f, overflowedSynth.f(0.875f));
		assertEquals(0, overflowedSynth.f(1f));
	}


}
