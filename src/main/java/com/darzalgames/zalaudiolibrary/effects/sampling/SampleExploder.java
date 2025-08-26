package com.darzalgames.zalaudiolibrary.effects.sampling;

public class SampleExploder implements SampleEffect {

	private final float explodeCutoff;

	public SampleExploder(float explodeCutoff) {
		if(explodeCutoff < 0) {
			throw new IllegalArgumentException("clip amplitude must be non-negative: " + explodeCutoff);
		}
		this.explodeCutoff = explodeCutoff;
	}

	@Override
	public Float apply(Float sample) {
		if(sample >= 0 && sample < explodeCutoff) {
			sample = explodeCutoff;
		}
		else if(sample < 0 && sample > -explodeCutoff) {
			sample = -explodeCutoff;
		}
		return sample;
	}
}
