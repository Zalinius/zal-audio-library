package com.darzalgames.zalaudiolibrary;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.AudioConsumer;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestTools {

	public static final Instrument TEST_INSTRUMENT = new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope());

	public static class AudioConsumerSpy implements AudioConsumer {
		private final List<Float> samplesList;

		public AudioConsumerSpy() {
			samplesList = new ArrayList<>();
		}

		@Override
		public void close() throws Exception {}

		@Override
		public void writeSamples(float[] samples) {
			for (float f : samples) {
				samplesList.add(f);
			}
		}

		public float getSample(int index) {
			return samplesList.get(index);
		}

		public List<Float> getSamplesList() {
			return samplesList;
		}
	}

}
