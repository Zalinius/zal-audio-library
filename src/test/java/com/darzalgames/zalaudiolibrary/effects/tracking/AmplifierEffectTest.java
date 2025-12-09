package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AmplifierEffectTest {

	@Test
	void multiplyAmplitudes_withMultiplier1_hasNoEffect() {
		AmplifierEffect amplifierEffect = new AmplifierEffect(1f);
		float originalAmplitude = 0.5f;

		float result = amplifierEffect.multiplyAmplitudes(originalAmplitude);

		assertEquals(0.5f, result);
	}

	@Test
	void multiplyAmplitudes_withMultiplier1Point5_increasesAmplitudeBy50Percent() {
		AmplifierEffect amplifierEffect = new AmplifierEffect(1.5f);
		float originalAmplitude = 0.5f;

		float result = amplifierEffect.multiplyAmplitudes(originalAmplitude);

		assertEquals(0.75f, result);
	}

}
