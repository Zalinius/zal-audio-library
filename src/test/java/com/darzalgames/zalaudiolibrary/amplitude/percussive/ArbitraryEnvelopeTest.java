package com.darzalgames.zalaudiolibrary.amplitude.percussive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.data.Tuple;

class ArbitraryEnvelopeTest {

	private final static float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_onEnvelopeWith0_alwaysReturns0() {
		PercussiveEnvelope envelope = new ArbitraryEnvelope(List.of());

		assertEquals(0, envelope.getEnvelope(0.00f));
		assertEquals(0, envelope.getEnvelope(0.25f));
		assertEquals(0, envelope.getEnvelope(0.50f));
		assertEquals(0, envelope.getEnvelope(0.75f));
		assertEquals(0, envelope.getEnvelope(1.00f));
	}

	@Test
	void getEnvelope_onEnvelopeWith1Point_alwaysReturns0exceptAtPoint() {
		PercussiveEnvelope envelope = new ArbitraryEnvelope(List.of(new Tuple<>(0.5f, 1f)));

		assertEquals(0, envelope.getEnvelope(0.00f));
		assertEquals(0, envelope.getEnvelope(0.25f));
		assertEquals(0, envelope.getEnvelope(0.49f));
		assertEquals(1f, envelope.getEnvelope(0.50f));
		assertEquals(0, envelope.getEnvelope(0.51f));
		assertEquals(0, envelope.getEnvelope(0.75f));
		assertEquals(0, envelope.getEnvelope(1.00f));
	}

	@Test
	void getEnvelope_onEnvelopeWith2Points_returns0OutsidePointsAndInterpolatesBetweenThem() {
		PercussiveEnvelope envelope = new ArbitraryEnvelope(List.of(new Tuple<>(0.5f, 1f), new Tuple<>(0.6f, 0.5f)));

		assertEquals(0, envelope.getEnvelope(0.00f));
		assertEquals(0, envelope.getEnvelope(0.25f));
		assertEquals(0, envelope.getEnvelope(0.49f));
		assertEquals(1f, envelope.getEnvelope(0.50f));
		assertEquals(0.9f, envelope.getEnvelope(0.52f), ALLOWED_ERROR);
		assertEquals(0.75f, envelope.getEnvelope(0.55f), ALLOWED_ERROR);
		assertEquals(0.6f, envelope.getEnvelope(0.58f), ALLOWED_ERROR);
		assertEquals(0.5f, envelope.getEnvelope(0.60f));
		assertEquals(0, envelope.getEnvelope(0.61f));
		assertEquals(0, envelope.getEnvelope(0.75f));
		assertEquals(0, envelope.getEnvelope(1.00f));
	}

	@Test
	void getEnvelopeDuration_onEmptyEnvelope_returns0() {
		PercussiveEnvelope envelope = new ArbitraryEnvelope(List.of());

		assertEquals(0.0f, envelope.getEnvelopeDuration());
	}

	@Test
	void getEnvelopeDuration_onEnvelopeWith2Points_returnsTimeOfFinalEntry() {
		PercussiveEnvelope envelope = new ArbitraryEnvelope(List.of(new Tuple<>(0.5f, 1f), new Tuple<>(0.6f, 0.5f)));

		assertEquals(0.6f, envelope.getEnvelopeDuration());
	}

}
