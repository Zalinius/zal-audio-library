package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.synth.Synth;

class SynthClipperTest {

	private static final float ALLOWED_ERROR = 0.001f;

	@Test
	void constructor_withNegativeClipThreshold_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SynthClipper(-0.1f));
	}

	@Test
	void clipSynth_onSineWave_clipsValuesAboveThreshold() {
		Synth clippedSynth = SynthClipper.clipSynth(Synth.sine(), 0.5f);

		assertEquals(0, clippedSynth.f(0));
		assertEquals(0.5f, clippedSynth.f(0.125f));
		assertEquals(0.5f, clippedSynth.f(0.25f));
		assertEquals(0.5f, clippedSynth.f(0.375f));
		assertEquals(0, clippedSynth.f(0.5f), ALLOWED_ERROR);
		assertEquals(0, clippedSynth.f(0));
		assertEquals(-0.5f, clippedSynth.f(0.625f));
		assertEquals(-0.5f, clippedSynth.f(0.75f));
		assertEquals(-0.5f, clippedSynth.f(0.875f));
		assertEquals(0, clippedSynth.f(1f), ALLOWED_ERROR);
	}


}
