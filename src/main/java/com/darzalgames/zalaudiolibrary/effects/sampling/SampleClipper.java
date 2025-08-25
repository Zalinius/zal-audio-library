package com.darzalgames.zalaudiolibrary.effects.sampling;

public class SampleClipper implements SampleEffect {

	private final float clipAmplitude;

	public SampleClipper(float clipAmplitude) {
		if(clipAmplitude < 0) {
			throw new IllegalArgumentException("clip amplitude must be non-negative: " + clipAmplitude);
		}
		this.clipAmplitude = clipAmplitude;
	}

	@Override
	public Float apply(Float sample) {
		if(sample < -clipAmplitude) {
			sample = -clipAmplitude;
		}
		else if(sample > clipAmplitude) {
			sample = clipAmplitude;
		}
		return sample;
	}
}
