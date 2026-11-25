package com.darzalgames.zalaudiolibrary.exporting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.TestTools;
import com.darzalgames.zalaudiolibrary.TestTools.AudioConsumerSpy;

class MultiAudioConsumerTest {

	@Test
	void writeSamples_writesSamplesToAllOutputStreams() throws Exception {
		AudioConsumerSpy spy0 = new TestTools.AudioConsumerSpy();
		AudioConsumerSpy spy1 = new TestTools.AudioConsumerSpy();
		AudioConsumerSpy spy2 = new TestTools.AudioConsumerSpy();
		MultiAudioConsumer multiOutputStream = new MultiAudioConsumer(spy0, spy1, spy2);

		multiOutputStream.writeSamples(new float[] { 1f, 0f, -1f, 0f });
		multiOutputStream.close();

		assertEquals(List.of(1f, 0f, -1f, 0f), spy0.getSamplesList());
		assertEquals(List.of(1f, 0f, -1f, 0f), spy1.getSamplesList());
		assertEquals(List.of(1f, 0f, -1f, 0f), spy2.getSamplesList());
	}

	@Test
	void close_callsCloseOnAllOutputStreams() throws Exception {
		AudioConsumerSpy spy0 = new TestTools.AudioConsumerSpy();
		AudioConsumerSpy spy1 = new TestTools.AudioConsumerSpy();
		AudioConsumerSpy spy2 = new TestTools.AudioConsumerSpy();
		MultiAudioConsumer multiOutputStream = new MultiAudioConsumer(spy0, spy1, spy2);

		multiOutputStream.close();

		assertTrue(spy0.wasCloseCalled());
		assertTrue(spy1.wasCloseCalled());
		assertTrue(spy2.wasCloseCalled());
	}

}
