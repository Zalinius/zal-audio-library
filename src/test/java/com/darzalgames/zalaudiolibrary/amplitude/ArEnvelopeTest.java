package com.darzalgames.zalaudiolibrary.amplitude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ArEnvelopeTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_outsideDuration_returns0() {
		Envelope arEnvelope = new ArEnvelope(0.5f, 0.5f);
		float duration = 1f;

		assertEquals(0, arEnvelope.getEnvelope(duration, 0));
		assertEquals(0, arEnvelope.getEnvelope(duration, -1));
		assertEquals(0, arEnvelope.getEnvelope(duration, 1));
		assertEquals(0, arEnvelope.getEnvelope(duration, 2));
	}

	@Test
	void getEnvelope_duringAttack_increasesLinearlyFrom0To1() {
		Envelope arEnvelope = new ArEnvelope(0.5f, 0.5f);
		float duration = 1f;

		assertEquals(0.0f, arEnvelope.getEnvelope(duration, 0.0f), ALLOWED_ERROR);
		assertEquals(0.2f, arEnvelope.getEnvelope(duration, 0.1f), ALLOWED_ERROR);
		assertEquals(0.4f, arEnvelope.getEnvelope(duration, 0.2f), ALLOWED_ERROR);
		assertEquals(0.6f, arEnvelope.getEnvelope(duration, 0.3f), ALLOWED_ERROR);
		assertEquals(0.8f, arEnvelope.getEnvelope(duration, 0.4f), ALLOWED_ERROR);
		assertEquals(1.0f, arEnvelope.getEnvelope(duration, 0.5f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_duringRelease_decreasesLinearlyFromSustainLevelTo0() {
		Envelope arEnvelope = new ArEnvelope(0.5f, 0.5f);
		float duration = 1f;

		assertEquals(1.0f, arEnvelope.getEnvelope(duration, 0.5f), ALLOWED_ERROR);
		assertEquals(0.8f, arEnvelope.getEnvelope(duration, 0.6f), ALLOWED_ERROR);
		assertEquals(0.6f, arEnvelope.getEnvelope(duration, 0.7f), ALLOWED_ERROR);
		assertEquals(0.4f, arEnvelope.getEnvelope(duration, 0.8f), ALLOWED_ERROR);
		assertEquals(0.2f, arEnvelope.getEnvelope(duration, 0.9f), ALLOWED_ERROR);
		assertEquals(0.0f, arEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
	}

}
