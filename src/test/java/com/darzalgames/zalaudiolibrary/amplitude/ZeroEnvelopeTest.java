package com.darzalgames.zalaudiolibrary.amplitude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ZeroEnvelopeTest {

	@Test
	void getEnvelope_insideAndOutsideDuration_returns0() {
		ZeroEnvelope envelope = new ZeroEnvelope();
		float duration = 1f;

		assertEquals(0, envelope.getEnvelope(duration, -0.1f));
		assertEquals(0, envelope.getEnvelope(duration,  0.0f));
		assertEquals(0, envelope.getEnvelope(duration,  0.5f));
		assertEquals(0, envelope.getEnvelope(duration,  1.0f));
		assertEquals(0, envelope.getEnvelope(duration,  1.1f));
	}

}
