package com.darzalgames.zalaudiolibrary.effects.sampling;

import java.util.Arrays;

public class SampleExploder implements SampleEffect {

	private final float explodeCutoff;

	public SampleExploder(float explodeCutoff) {
		if(explodeCutoff < 0) {
			throw new IllegalArgumentException("clip amplitude must be non-negative: " + explodeCutoff);
		}
		this.explodeCutoff = explodeCutoff;
	}

	@Override
	public float[] apply(float[] samples) {
		float[] copy = Arrays.copyOf(samples, samples.length);
		for (int i = 0; i < copy.length; i++) {
			float sample = copy[i];
			if(sample >= 0 && sample < explodeCutoff) {
				sample = explodeCutoff;
			}
			else if(sample < 0 && sample > -explodeCutoff) {
				sample = -explodeCutoff;
			}
			copy[i] = sample;
		}
		return copy;
	}
}
