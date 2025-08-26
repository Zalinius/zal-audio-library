package com.darzalgames.zalaudiolibrary.effects.sampling;

public class BitCompressor implements SampleEffect {

	private final int bitDepth;

	public BitCompressor(int bitDepth) {
		this.bitDepth = bitDepth;
	}

	@Override
	public Float apply(Float sample) {
		int binarySample = (int) (sample * Math.pow(2, bitDepth));
		return (float)(binarySample / (Math.pow(2, bitDepth)));
	}

}
