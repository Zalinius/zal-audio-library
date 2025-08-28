package com.darzalgames.zalaudiolibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.PercussiveEnvelope;
import com.darzalgames.zalaudiolibrary.synth.complex.trumpetExperiment.ArbitraryEnvelope;

class ArbitraryEnvelopeTestMOVEAANDRENAME {

	private final static float ALLOWED_ERROR = 0.0001f;

	@Test
	void getEnvelope_onEnvelopeWith0_alwaysReturns0() {
		PercussiveEnvelope envelope0 = new ArbitraryEnvelope(List.of());

		assertEquals(0, envelope0.getEnvelope(0.00f));
		assertEquals(0, envelope0.getEnvelope(0.25f));
		assertEquals(0, envelope0.getEnvelope(0.50f));
		assertEquals(0, envelope0.getEnvelope(0.75f));
		assertEquals(0, envelope0.getEnvelope(1.00f));
	}

	@Test
	void getEnvelope_onEnvelopeWith1Point_alwaysReturns0exceptAtPoint() {
		PercussiveEnvelope envelope1 = new ArbitraryEnvelope(List.of(new Tuple<>(0.5f, 1f)));

		assertEquals(0, envelope1.getEnvelope(0.00f));
		assertEquals(0, envelope1.getEnvelope(0.25f));
		assertEquals(0, envelope1.getEnvelope(0.49f));
		assertEquals(1f, envelope1.getEnvelope(0.50f));
		assertEquals(0, envelope1.getEnvelope(0.51f));
		assertEquals(0, envelope1.getEnvelope(0.75f));
		assertEquals(0, envelope1.getEnvelope(1.00f));
	}

	@Test
	void getEnvelope_onEnvelopeWith2Points_returns0OutsidePointsAndInterpolatesBetweenThemn() {
		PercussiveEnvelope envelope2 = new ArbitraryEnvelope(List.of(new Tuple<>(0.5f, 1f), new Tuple<>(0.6f, 0.5f)));

		assertEquals(0, envelope2.getEnvelope(0.00f));
		assertEquals(0, envelope2.getEnvelope(0.25f));
		assertEquals(0, envelope2.getEnvelope(0.49f));
		assertEquals(1f, envelope2.getEnvelope(0.50f));
		assertEquals(0.9f, envelope2.getEnvelope(0.52f), ALLOWED_ERROR);
		assertEquals(0.75f, envelope2.getEnvelope(0.55f), ALLOWED_ERROR);
		assertEquals(0.6f, envelope2.getEnvelope(0.58f), ALLOWED_ERROR);
		assertEquals(0.5f, envelope2.getEnvelope(0.60f));
		assertEquals(0, envelope2.getEnvelope(0.61f));
		assertEquals(0, envelope2.getEnvelope(0.75f));
		assertEquals(0, envelope2.getEnvelope(1.00f));
	}

}
