package com.darzalgames.zalaudiolibrary.synth;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

class WaveFunctionsTest {

	@Test
	void squareWave() throws Exception {
		UnaryOperator<Float> squareWave = WaveFunctions.getSquareWaveFunction();

		assertEquals(1f, squareWave.apply(0f));
		assertEquals(1f, squareWave.apply(0.49f));
		assertEquals(-1f, squareWave.apply(0.5f));
		assertEquals(-1f, squareWave.apply(0.99f));
	}

	@Test
	void pulseWave() throws Exception {
		UnaryOperator<Float> pulseWave = WaveFunctions.getPulseWaveFunction(0.2f);

		assertEquals(1f, pulseWave.apply(0f));
		assertEquals(1f, pulseWave.apply(0.19f));
		assertEquals(-1f, pulseWave.apply(0.2f));
		assertEquals(-1f, pulseWave.apply(0.99f));
	}

	@Test
	void pulseWave_throwsForModulationOutsideOf0_1() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> WaveFunctions.getPulseWaveFunction(-0.1f));
		assertDoesNotThrow(() -> WaveFunctions.getPulseWaveFunction(0f));
		assertDoesNotThrow(() -> WaveFunctions.getPulseWaveFunction(0.5f));
		assertDoesNotThrow(() -> WaveFunctions.getPulseWaveFunction(1f));
		assertThrows(IllegalArgumentException.class, () -> WaveFunctions.getPulseWaveFunction(1.1f));
	}

	@Test
	void sinWave() throws Exception {
		UnaryOperator<Float> sinWave = WaveFunctions.getSinWaveFunction();

		assertEquals(0f, sinWave.apply(0f), 0.001);
		assertEquals((float)Math.sqrt(2)/2, sinWave.apply(0.125f), 0.001);
		assertEquals(1f, sinWave.apply(0.25f), 0.001);
		assertEquals(0f, sinWave.apply(0.5f), 0.001);
		assertEquals(-1f, sinWave.apply(0.75f), 0.001);
		assertEquals(0f, sinWave.apply(1f), 0.001);
	}

	@Test
	void triangleWave() throws Exception {
		UnaryOperator<Float> triangleWave = WaveFunctions.getTriangleWaveFunction();

		assertEquals(0f, triangleWave.apply(0f), 0.001);
		assertEquals(0.5f, triangleWave.apply(0.125f), 0.001);
		assertEquals(1f, triangleWave.apply(0.25f), 0.001);
		assertEquals(0f, triangleWave.apply(0.5f), 0.001);
		assertEquals(-1f, triangleWave.apply(0.75f), 0.001);
		assertEquals(0f, triangleWave.apply(1f), 0.001);
	}

	@Test
	void sawWave() throws Exception {
		UnaryOperator<Float> sawWave = WaveFunctions.getSawtoothWaveFunction();

		assertEquals(0f, sawWave.apply(0f), 0.001);
		assertEquals(0.5f, sawWave.apply(0.25f), 0.001);
		assertEquals(1f, sawWave.apply(0.5f), 0.001);
		assertEquals(-0.5f, sawWave.apply(0.75f), 0.001);
		assertEquals(0f, sawWave.apply(1f), 0.001);
	}

	@Test
	void bandLimitedSawWave() throws Exception {
		UnaryOperator<Float> sawWave = WaveFunctions.getBandLimitedSawtoothWaveFunction(2);

		assertEquals(0f, sawWave.apply(0f), 0.001);
		assertEquals(0f, sawWave.apply(0.5f), 0.001);
		assertEquals(0f, sawWave.apply(1f), 0.001);
	}

	@Test
	void bandLimitedSawWave_largeHarmonic_approximatesSawWave() throws Exception {
		UnaryOperator<Float> bandLimitedSawWave = WaveFunctions.getBandLimitedSawtoothWaveFunction(40000);
		UnaryOperator<Float> sawWave = WaveFunctions.getSawtoothWaveFunction();

		assertEquals(sawWave.apply(0.0f), bandLimitedSawWave.apply(0.0f), 0.01f);
		assertEquals(sawWave.apply(0.1f), bandLimitedSawWave.apply(0.1f), 0.01f);
		assertEquals(sawWave.apply(0.2f), bandLimitedSawWave.apply(0.2f), 0.01f);
		assertEquals(sawWave.apply(0.3f), bandLimitedSawWave.apply(0.3f), 0.01f);
		assertEquals(sawWave.apply(0.4f), bandLimitedSawWave.apply(0.4f), 0.01f);

		assertEquals(sawWave.apply(0.6f), bandLimitedSawWave.apply(0.6f), 0.01f);
		assertEquals(sawWave.apply(0.7f), bandLimitedSawWave.apply(0.7f), 0.01f);
		assertEquals(sawWave.apply(0.8f), bandLimitedSawWave.apply(0.8f), 0.01f);
		assertEquals(sawWave.apply(0.9f), bandLimitedSawWave.apply(0.9f), 0.01f);
		assertEquals(sawWave.apply(1.0f), bandLimitedSawWave.apply(1.0f), 0.01f);
	}

	@Test
	void bandLimitedSawWave_throwsWhenHarmonicsAreNonPositive() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> WaveFunctions.getBandLimitedSawtoothWaveFunction(0));
		assertDoesNotThrow(() -> WaveFunctions.getBandLimitedSawtoothWaveFunction(1));
	}

	@Test
	void whiteNoise_isBounded() throws Exception {
		UnaryOperator<Float> whiteNoise = WaveFunctions.getWhiteNoiseFunction();
		for (int i = 0; i < 100; i++) {
			float x = i/100f;
			float result = whiteNoise.apply(x);
			assertTrue(result >= -1f);
			assertTrue(result <=  1f);
		}
	}

	@Test
	void brownianNoise_isBounded() throws Exception {
		UnaryOperator<Float> brownianNoise = WaveFunctions.getBrownianNoiseFunction(0.5f);
		for (int i = 0; i < 100; i++) {
			float x = i/100f;
			float result = brownianNoise.apply(x);
			assertTrue(result >= -1f);
			assertTrue(result <=  1f);
		}
	}

	@Test
	void brownianNoise_sequentialValuesAreClose() throws Exception {
		UnaryOperator<Float> brownianNoise = WaveFunctions.getBrownianNoiseFunction(0.5f);
		float lastResult = brownianNoise.apply(0f);
		for (int i = 0; i < 100; i++) {
			float x = i/100f;
			float result = brownianNoise.apply(x);

			assertTrue(Math.abs(result - lastResult) <= 2*0.5f);

			lastResult = result;
		}
	}

	@Test
	void nullWave_alwaysReturns0() throws Exception {
		UnaryOperator<Float> nullWave = WaveFunctions.getNullWaveFunction();

		assertEquals(0f, nullWave.apply(0f));
		assertEquals(0f, nullWave.apply(0.25f));
		assertEquals(0f, nullWave.apply(0.5f));
		assertEquals(0f, nullWave.apply(0.75f));
		assertEquals(0f, nullWave.apply(1f));
	}

}
