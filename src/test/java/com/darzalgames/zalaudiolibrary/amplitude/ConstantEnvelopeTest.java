package com.darzalgames.zalaudiolibrary.amplitude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ConstantEnvelopeTest {

	@Test
	void getEnvelope_onZeroEnvelopeInsideAndOutsideDuration_returns0() {
		ConstantEnvelope envelope = ConstantEnvelope.zeroEnvelope();
		float duration = 1f;

		assertEquals(0, envelope.getEnvelope(duration, -0.1f));
		assertEquals(0, envelope.getEnvelope(duration,  0.0f));
		assertEquals(0, envelope.getEnvelope(duration,  0.5f));
		assertEquals(0, envelope.getEnvelope(duration,  1.0f));
		assertEquals(0, envelope.getEnvelope(duration,  1.1f));
	}

}
