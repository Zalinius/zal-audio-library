package com.darzalgames.zalaudiolibrary.effects.sampling;

import java.util.Arrays;

public class SampleClipper implements SampleEffect {

	private final float clipAmplitude;

	public SampleClipper(float clipAmplitude) {
		if(clipAmplitude < 0) {
			throw new IllegalArgumentException("clip amplitude must be non-negative: " + clipAmplitude);
		}
		this.clipAmplitude = clipAmplitude;
	}

	@Override
	public float[] apply(final float[] samples) {
		float[] copy = Arrays.copyOf(samples, samples.length);
		for (int i = 0; i < copy.length; i++) {
			float sample = copy[i];
			if(sample < -clipAmplitude) {
				sample = -clipAmplitude;
			}
			else if(sample > clipAmplitude) {
				sample = clipAmplitude;
			}
			copy[i] = sample;
		}
		return copy;
	}
}
