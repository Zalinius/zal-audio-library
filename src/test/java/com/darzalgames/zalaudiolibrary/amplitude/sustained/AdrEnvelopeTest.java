package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdrEnvelopeTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_outsideEnvelopeDuration_returns0() {
		AdrEnvelope adrEnvelope = AdrEnvelope.linear(1, 1, 0.5f);
		float duration = 3f;

		assertEquals(0, adrEnvelope.getEnvelope(duration, -0.1f));
		assertEquals(0, adrEnvelope.getEnvelope(duration, 0.0f));
		assertEquals(0, adrEnvelope.getEnvelope(duration, 4.0f));
		assertEquals(0, adrEnvelope.getEnvelope(duration, 4.1f));
	}

	@Test
	void getEnvelope_forLinearInterpolation_returnsCorrectValues() {
		AdrEnvelope adrEnvelope = AdrEnvelope.linear(1, 1, 0.3f);
		float duration = 3f;

		// attack
		assertEquals(0, adrEnvelope.getEnvelope(duration, 0f));
		assertEquals(0.25f, adrEnvelope.getEnvelope(duration, 0.25f));
		assertEquals(0.5f, adrEnvelope.getEnvelope(duration, 0.5f));
		assertEquals(1f, adrEnvelope.getEnvelope(duration, 1f));

		// decay
		assertEquals(0.825f, adrEnvelope.getEnvelope(duration, 1.25f));
		assertEquals(0.65f, adrEnvelope.getEnvelope(duration, 1.5f));
		assertEquals(0.475f, adrEnvelope.getEnvelope(duration, 1.75f), ALLOWED_ERROR);
		assertEquals(0.3f, adrEnvelope.getEnvelope(duration, 2f));

		// sustained release
		assertEquals(0.225f, adrEnvelope.getEnvelope(duration, 2.25f), ALLOWED_ERROR);
		assertEquals(0.15f, adrEnvelope.getEnvelope(duration, 2.5f));
		assertEquals(0.075f, adrEnvelope.getEnvelope(duration, 2.75f));
		assertEquals(0.0f, adrEnvelope.getEnvelope(duration, 3f));
	}

	@Test
	void getEnvelope_forSmallValues_returnsCorrectValuesDuringRelease() {
		AdrEnvelope adrEnvelope = AdrEnvelope.linear(.01f, .89f, .3f);
		float duration = 1f;

		// sustained release
		assertEquals(0.3f, adrEnvelope.getEnvelope(duration, 0.9f), ALLOWED_ERROR);
		assertEquals(0.15f, adrEnvelope.getEnvelope(duration, 0.95f), ALLOWED_ERROR);
		assertEquals(0.0f, adrEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_forQuadraticInterpolation_returnsCorrectValues() {
		AdrEnvelope adrEnvelope = AdrEnvelope.quadratic(1, 1, 0.5f);
		float duration = 3f;

		// attack
		assertEquals(0, adrEnvelope.getEnvelope(duration, 0f));
		assertEquals(0.75f, adrEnvelope.getEnvelope(duration, 0.5f));
		assertEquals(1f, adrEnvelope.getEnvelope(duration, 1f));

		// decay
		assertEquals(0.625f, adrEnvelope.getEnvelope(duration, 1.5f));
		assertEquals(0.5f, adrEnvelope.getEnvelope(duration, 2f));

		// sustained release
		assertEquals(0.125f, adrEnvelope.getEnvelope(duration, 2.5f));
		assertEquals(0.0f, adrEnvelope.getEnvelope(duration, 3f));
	}

}
