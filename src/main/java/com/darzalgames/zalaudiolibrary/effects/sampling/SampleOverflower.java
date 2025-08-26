package com.darzalgames.zalaudiolibrary.effects.sampling;

public class SampleOverflower implements SampleEffect {

	private final float overflowAmplitude;

	public SampleOverflower(float overflowAmplitude) {
		if(overflowAmplitude < 0) {
			throw new IllegalArgumentException("overflow amplitude must be non-negative: " + overflowAmplitude);
		}
		this.overflowAmplitude = overflowAmplitude;
	}

	@Override
	public Float apply(Float sample) {
		boolean originalIsPositive = sample >= 0;
		sample = Math.abs(sample);
		if(sample > overflowAmplitude) {
			float overflow = sample - overflowAmplitude;
			sample = -overflowAmplitude + overflow;
		}

		if(!originalIsPositive) {
			sample = -sample;
		}

		return sample;
	}

}
