package com.darzalgames.zalaudiolibrary.effects;

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
		for (int i = 0; i < samples.length; i++) {
			float sample = samples[i];
			if(sample >= 0 && sample < explodeCutoff) {
				sample = explodeCutoff;
			}
			else if(sample < 0 && sample > -explodeCutoff) {
				sample = -explodeCutoff;
			}
			samples[i] = sample;
		}
		return samples;
	}
}
