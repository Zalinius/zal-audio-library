package com.darzalgames.zalaudiolibrary.amplitude.percussive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;

class ArEnvelopeTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_outsideDuration_returns0() {
		Envelope arEnvelope = ArEnvelope.linear(0.5f, 0.5f);
		float duration = 1f;

		assertEquals(0, arEnvelope.getEnvelope(duration, 0));
		assertEquals(0, arEnvelope.getEnvelope(duration, -1));
		assertEquals(0, arEnvelope.getEnvelope(duration, 1));
		assertEquals(0, arEnvelope.getEnvelope(duration, 2));
	}

	@Test
	void getEnvelope_duringAttack_increasesLinearlyFrom0To1() {
		Envelope arEnvelope = ArEnvelope.linear(0.5f, 0.5f);
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
		Envelope arEnvelope = ArEnvelope.linear(0.5f, 0.5f);
		float duration = 1f;

		assertEquals(1.0f, arEnvelope.getEnvelope(duration, 0.5f), ALLOWED_ERROR);
		assertEquals(0.8f, arEnvelope.getEnvelope(duration, 0.6f), ALLOWED_ERROR);
		assertEquals(0.6f, arEnvelope.getEnvelope(duration, 0.7f), ALLOWED_ERROR);
		assertEquals(0.4f, arEnvelope.getEnvelope(duration, 0.8f), ALLOWED_ERROR);
		assertEquals(0.2f, arEnvelope.getEnvelope(duration, 0.9f), ALLOWED_ERROR);
		assertEquals(0.0f, arEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_onQuadratic_returnsCorrectValues() {
		ArEnvelope arEnvelope = ArEnvelope.quadratic(1f, 1f);

		assertEquals(0.00f, arEnvelope.getEnvelope(0.0f), ALLOWED_ERROR);
		assertEquals(0.36f, arEnvelope.getEnvelope(0.2f), ALLOWED_ERROR);
		assertEquals(0.75f, arEnvelope.getEnvelope(0.5f), ALLOWED_ERROR);
		assertEquals(0.96f, arEnvelope.getEnvelope(0.8f), ALLOWED_ERROR);
		assertEquals(0.99f, arEnvelope.getEnvelope(0.9f), ALLOWED_ERROR);
		assertEquals(1.00f, arEnvelope.getEnvelope(1.0f), ALLOWED_ERROR);
		assertEquals(0.64f, arEnvelope.getEnvelope(1.2f), ALLOWED_ERROR);
		assertEquals(0.25f, arEnvelope.getEnvelope(1.5f), ALLOWED_ERROR);
		assertEquals(0.04f, arEnvelope.getEnvelope(1.8f), ALLOWED_ERROR);
		assertEquals(0.00f, arEnvelope.getEnvelope(2.0f), ALLOWED_ERROR);
	}

}
