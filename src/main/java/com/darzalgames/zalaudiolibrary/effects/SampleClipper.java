package com.darzalgames.zalaudiolibrary.effects;

public class SampleClipper implements SampleEffect {

	private final float clipAmplitude;

	public SampleClipper(float clipAmplitude) {
		if(clipAmplitude < 0) {
			throw new IllegalArgumentException("clip amplitude must be non-negative: " + clipAmplitude);
		}
		this.clipAmplitude = clipAmplitude;
	}

	@Override
	public float[] apply(float[] samples) {
		for (int i = 0; i < samples.length; i++) {
			float sample = samples[i];
			if(sample < -clipAmplitude) {
				sample = -clipAmplitude;
			}
			else if(sample > clipAmplitude) {
				sample = clipAmplitude;
			}
			samples[i] = sample;
		}
		return samples;
	}
}
