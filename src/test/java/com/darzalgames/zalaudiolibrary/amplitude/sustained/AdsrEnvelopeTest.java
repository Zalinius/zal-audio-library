package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdsrEnvelopeTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_outsideEnvelopeDuration_returns0() {
		AdsrEnvelope adsrEnvelope = AdsrEnvelope.linear(1, 1, 0.5f, 1);
		float duration = 4f;

		assertEquals(0, adsrEnvelope.getEnvelope(duration, -0.1f));
		assertEquals(0, adsrEnvelope.getEnvelope(duration,  0.0f));
		assertEquals(0, adsrEnvelope.getEnvelope(duration,  4.0f));
		assertEquals(0, adsrEnvelope.getEnvelope(duration, 	4.1f));
	}

	@Test
	void getEnvelope_forLinearInterpolation_returnsCorrectValues() {
		AdsrEnvelope adsrEnvelope = AdsrEnvelope.linear(1, 1, 0.3f, 1);
		float duration = 4f;

		//attack
		assertEquals(0, adsrEnvelope.getEnvelope(duration, 0f));
		assertEquals(0.25f, adsrEnvelope.getEnvelope(duration, 0.25f));
		assertEquals(0.5f, adsrEnvelope.getEnvelope(duration, 0.5f));
		assertEquals(1f, adsrEnvelope.getEnvelope(duration, 1f));

		//decay
		assertEquals(0.825f, adsrEnvelope.getEnvelope(duration, 1.25f));
		assertEquals(0.65f, adsrEnvelope.getEnvelope(duration, 1.5f));
		assertEquals(0.475f, adsrEnvelope.getEnvelope(duration, 1.75f), ALLOWED_ERROR);
		assertEquals(0.3f, adsrEnvelope.getEnvelope(duration, 2f));

		//sustain
		assertEquals(0.3f, adsrEnvelope.getEnvelope(duration, 2.25f));
		assertEquals(0.3f, adsrEnvelope.getEnvelope(duration, 2.5f));
		assertEquals(0.3f, adsrEnvelope.getEnvelope(duration, 2.75f));
		assertEquals(0.3f, adsrEnvelope.getEnvelope(duration, 3f));

		//release
		assertEquals(0.225f, adsrEnvelope.getEnvelope(duration, 3.25f), ALLOWED_ERROR);
		assertEquals(0.15f, adsrEnvelope.getEnvelope(duration, 3.5f));
		assertEquals(0.075f, adsrEnvelope.getEnvelope(duration, 3.75f));
		assertEquals(0.0f, adsrEnvelope.getEnvelope(duration, 4f));
	}

	@Test
	void getEnvelope_forSmallValues_returnsCorrectValuesDuringRelease() {
		AdsrEnvelope adsrEnvelope = AdsrEnvelope.linear(.01f, .09f, .3f, .1f);
		float duration = 1f;

		//release
		assertEquals(0.3f, adsrEnvelope.getEnvelope(duration, 0.9f), ALLOWED_ERROR);
		assertEquals(0.15f, adsrEnvelope.getEnvelope(duration, 0.95f), ALLOWED_ERROR);
		assertEquals(0.0f, adsrEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_forQuadraticInterpolation_returnsCorrectValues() {
		AdsrEnvelope adsrEnvelope = AdsrEnvelope.quadratic(1, 1, 0.5f, 1);
		float duration = 4f;

		//attack
		assertEquals(0, adsrEnvelope.getEnvelope(duration, 0f));
		assertEquals(0.75f, adsrEnvelope.getEnvelope(duration, 0.5f));
		assertEquals(1f, adsrEnvelope.getEnvelope(duration, 1f));

		//decay
		assertEquals(0.625f, adsrEnvelope.getEnvelope(duration, 1.5f));
		assertEquals(0.5f, adsrEnvelope.getEnvelope(duration, 2f));

		//sustain
		assertEquals(0.5f, adsrEnvelope.getEnvelope(duration, 2.5f));
		assertEquals(0.5f, adsrEnvelope.getEnvelope(duration, 3f));

		//release
		assertEquals(0.125f, adsrEnvelope.getEnvelope(duration, 3.5f));
		assertEquals(0.0f, adsrEnvelope.getEnvelope(duration, 4f));
	}

}
