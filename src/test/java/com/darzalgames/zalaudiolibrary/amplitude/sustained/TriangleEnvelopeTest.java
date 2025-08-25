package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;

class TriangleEnvelopeTest {

	private static final float ALLOWED_ERROR = 0.0001f;

	@Test
	void constructor_withAttackPercentageOutsideOf0and1_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> TriangleEnvelope.linear(-0.1f));
		assertThrows(IllegalArgumentException.class, () -> TriangleEnvelope.linear(1.1f));
	}

	@Test
	void getEnvelope_outsideDuration_returns0() {
		Envelope triangleEnvelope = TriangleEnvelope.linear(0.5f);
		float duration = 1f;

		assertEquals(0, triangleEnvelope.getEnvelope(duration, 0));
		assertEquals(0, triangleEnvelope.getEnvelope(duration, -1));
		assertEquals(0, triangleEnvelope.getEnvelope(duration, 1));
		assertEquals(0, triangleEnvelope.getEnvelope(duration, 2));
	}

	@Test
	void getEnvelope_duringAttack_increasesLinearlyFrom0To1() {
		Envelope triangleEnvelope = TriangleEnvelope.linear(0.5f);
		float duration = 1f;

		assertEquals(0.0f, triangleEnvelope.getEnvelope(duration, 0.0f), ALLOWED_ERROR);
		assertEquals(0.2f, triangleEnvelope.getEnvelope(duration, 0.1f), ALLOWED_ERROR);
		assertEquals(0.4f, triangleEnvelope.getEnvelope(duration, 0.2f), ALLOWED_ERROR);
		assertEquals(0.6f, triangleEnvelope.getEnvelope(duration, 0.3f), ALLOWED_ERROR);
		assertEquals(0.8f, triangleEnvelope.getEnvelope(duration, 0.4f), ALLOWED_ERROR);
		assertEquals(1.0f, triangleEnvelope.getEnvelope(duration, 0.5f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_duringRelease_decreasesLinearlyFromSustainLevelTo0() {
		Envelope triangleEnvelope = TriangleEnvelope.linear(0.5f);
		float duration = 1f;

		assertEquals(1.0f, triangleEnvelope.getEnvelope(duration, 0.5f), ALLOWED_ERROR);
		assertEquals(0.8f, triangleEnvelope.getEnvelope(duration, 0.6f), ALLOWED_ERROR);
		assertEquals(0.6f, triangleEnvelope.getEnvelope(duration, 0.7f), ALLOWED_ERROR);
		assertEquals(0.4f, triangleEnvelope.getEnvelope(duration, 0.8f), ALLOWED_ERROR);
		assertEquals(0.2f, triangleEnvelope.getEnvelope(duration, 0.9f), ALLOWED_ERROR);
		assertEquals(0.0f, triangleEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_withAsymmetricEnvelope_hasCorrectValues() {
		float attackPercentage = 0.10f;
		Envelope triangleEnvelope = TriangleEnvelope.linear(attackPercentage);
		float duration = 1f;

		assertEquals(0.0f, triangleEnvelope.getEnvelope(duration, 0.0f), ALLOWED_ERROR);
		assertEquals(0.5f, triangleEnvelope.getEnvelope(duration, 0.05f), ALLOWED_ERROR);
		assertEquals(1.0f, triangleEnvelope.getEnvelope(duration, 0.1f), ALLOWED_ERROR);
		assertEquals(0.5f, triangleEnvelope.getEnvelope(duration, 0.55f), ALLOWED_ERROR);
		assertEquals(0.0f, triangleEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
	}

	@Test
	void getEnvelope_withQuadraticInterpolation_hasCorrectValues() {
		Envelope triangleEnvelope = TriangleEnvelope.quadratic(0.5f);
		float duration = 2f;

		assertEquals(0.0f, triangleEnvelope.getEnvelope(duration, 0.0f), ALLOWED_ERROR);
		assertEquals(0.75f, triangleEnvelope.getEnvelope(duration, 0.5f), ALLOWED_ERROR);
		assertEquals(1.0f, triangleEnvelope.getEnvelope(duration, 1.0f), ALLOWED_ERROR);
		assertEquals(0.25f, triangleEnvelope.getEnvelope(duration, 1.5f), ALLOWED_ERROR);
		assertEquals(0.0f, triangleEnvelope.getEnvelope(duration, 2.0f), ALLOWED_ERROR);
	}

}
