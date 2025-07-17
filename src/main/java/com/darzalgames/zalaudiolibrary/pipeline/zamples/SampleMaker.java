package com.darzalgames.zalaudiolibrary.pipeline.zamples;

import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;

public class SampleMaker {

	public float[] makeSamples(SimpleSound simpleSound) {
		float[] samples = new float[(int) (AudioConstants.SAMPLING_RATE * simpleSound.getDuration())];

		for (int i = 0; i < samples.length; i++) {
			float t = i/(float)AudioConstants.SAMPLING_RATE;
			float x = simpleSound.getFrequency() * t % 1f;

			float sample = simpleSound.getTimbre().f(x) * simpleSound.getEnvelope().getEnvelope(simpleSound.getDuration(), t);
			samples[i] = sample;
		}

		return samples;
	}
}
