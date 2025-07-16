package com.darzalgames.zalaudiolibrary.amplitude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AsrEnvelopeTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_outsideDuration_returns0() {
		Envelope asrEnvelope = new AsrEnvelope(0.1f, 1f, 0.1f);
		float duration = 1f;

		assertEquals(0, asrEnvelope.getEnvelope(duration, 0));
		assertEquals(0, asrEnvelope.getEnvelope(duration, -1));
		assertEquals(0, asrEnvelope.getEnvelope(duration, 1));
		assertEquals(0, asrEnvelope.getEnvelope(duration, 2));
	}

	@Test
	void getEnvelope_duringSustain_returnsSustainLevel() {
		float sustain = 0.7f;
		Envelope asrEnvelope = new AsrEnvelope(0.1f, sustain, 0.1f);
		float duration = 1f;

		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.1f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.2f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.3f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.4f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.5f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.6f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.7f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.8f));
		assertEquals(sustain, asrEnvelope.getEnvelope(duration, 0.9f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_duringAttack_increasesLinearlyFrom0ToSustainLevel() {
		Envelope asrEnvelope = new AsrEnvelope(0.1f, 1f, 0.1f);
		float duration = 1f;

		assertEquals(0.0f, asrEnvelope.getEnvelope(duration, 0.00f), ALLOWED_ERROR);
		assertEquals(0.2f, asrEnvelope.getEnvelope(duration, 0.02f), ALLOWED_ERROR);
		assertEquals(0.4f, asrEnvelope.getEnvelope(duration, 0.04f), ALLOWED_ERROR);
		assertEquals(0.6f, asrEnvelope.getEnvelope(duration, 0.06f), ALLOWED_ERROR);
		assertEquals(0.8f, asrEnvelope.getEnvelope(duration, 0.08f), ALLOWED_ERROR);
		assertEquals(1.0f, asrEnvelope.getEnvelope(duration, 0.10f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_duringRelease_decreasesLinearlyFromSustainLevelTo0() {
		Envelope asrEnvelope = new AsrEnvelope(0.1f, 1f, 0.1f);
		float duration = 1f;

		assertEquals(1.0f, asrEnvelope.getEnvelope(duration, 0.90f), ALLOWED_ERROR);
		assertEquals(0.8f, asrEnvelope.getEnvelope(duration, 0.92f), ALLOWED_ERROR);
		assertEquals(0.6f, asrEnvelope.getEnvelope(duration, 0.94f), ALLOWED_ERROR);
		assertEquals(0.4f, asrEnvelope.getEnvelope(duration, 0.96f), ALLOWED_ERROR);
		assertEquals(0.2f, asrEnvelope.getEnvelope(duration, 0.98f), ALLOWED_ERROR);
		assertEquals(0.0f, asrEnvelope.getEnvelope(duration, 1.00f), ALLOWED_ERROR);
	}

}
