package com.darzalgames.zalaudiolibrary.effects.sampling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SampleCompressorTest {

	@Test
	void constructor_withBitDepthLessThan1orGreaterThanMax_throwsException() {
		assertThrows(IllegalArgumentException.class, () -> new SampleCompressor(0));
		assertThrows(IllegalArgumentException.class, () -> new SampleCompressor(SampleCompressor.MAX_BIT_DEPTH + 1));
		assertDoesNotThrow(() -> new SampleCompressor(2));
		assertDoesNotThrow(() -> new SampleCompressor(16));
	}

	@Test
	void compressor_roundsAndCompressesValues() {
		SampleCompressor compressor = new SampleCompressor(2);

		assertEquals(0, compressor.apply(-0.1f));
		assertEquals(0, compressor.apply(0f));
		assertEquals(0, compressor.apply(0.1f));

		assertEquals(0.25f, compressor.apply(0.2f));
		assertEquals(0.25f, compressor.apply(0.3f));

		assertEquals(0.5f, compressor.apply(0.4f));
		assertEquals(0.5f, compressor.apply(0.5f));
		assertEquals(0.5f, compressor.apply(0.6f));

		assertEquals(0.75f, compressor.apply(0.7f));
		assertEquals(0.75f, compressor.apply(0.8f));

		assertEquals(1f, compressor.apply(0.9f));
		assertEquals(1f, compressor.apply(1.0f));

		assertEquals(-1f, compressor.apply(-0.9f));
		assertEquals(-1f, compressor.apply(-1.0f));
	}

}
