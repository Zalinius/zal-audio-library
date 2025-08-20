package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.synth.Synth;

class SynthExploderTest {

	private static final float ALLOWED_ERROR = 0.001f;

	@Test
	void constructor_withNegativeExplodeThreshold_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new SynthExploder(-0.1f));
	}

	@Test
	void explodeSynth_onSineWave_explodesValuesAboveThreshold() {
		Synth explodedSynth = SynthExploder.explodeSynth(Synth.sine(), 0.5f);

		assertEquals(0.5f, explodedSynth.f(0));
		assertEquals(Math.sqrt(2)/2, explodedSynth.f(0.125f), ALLOWED_ERROR);
		assertEquals(1f, explodedSynth.f(0.25f));
		assertEquals(Math.sqrt(2)/2, explodedSynth.f(0.375f), ALLOWED_ERROR);
		assertEquals(0.5f, explodedSynth.f(0.5f), ALLOWED_ERROR);
		assertEquals(0.5f, explodedSynth.f(0));
		assertEquals(-Math.sqrt(2)/2, explodedSynth.f(0.625f), ALLOWED_ERROR);
		assertEquals(-1f, explodedSynth.f(0.75f));
		assertEquals(-Math.sqrt(2)/2, explodedSynth.f(0.875f), ALLOWED_ERROR);
		assertEquals(-0.5f, explodedSynth.f(1f), ALLOWED_ERROR);
	}

}
