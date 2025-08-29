package com.darzalgames.zalaudiolibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AudioUtilsTest {

	private static final float ALLOWED_ERROR = 0.01f;

	@Test
	void decibelToAmplitude() {
		assertEquals(1.0f, AudioUtils.decibelToAmplitude(0));
		assertEquals(0.5f, AudioUtils.decibelToAmplitude(-6), ALLOWED_ERROR);
		assertEquals(0.1f, AudioUtils.decibelToAmplitude(-20));
		assertEquals(0.05f, AudioUtils.decibelToAmplitude(-26), ALLOWED_ERROR);
		assertEquals(0.01f, AudioUtils.decibelToAmplitude(-40));
	}

}
