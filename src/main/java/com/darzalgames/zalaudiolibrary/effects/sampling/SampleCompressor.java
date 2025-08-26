package com.darzalgames.zalaudiolibrary.effects.sampling;

public class SampleCompressor implements SampleEffect {

	public static final int MAX_BIT_DEPTH = 16;

	private final int bitDepth;

	public SampleCompressor(int bitDepth) {
		if(bitDepth < 1 || bitDepth > MAX_BIT_DEPTH) {
			throw new IllegalArgumentException("Bit depth must be between 1 and " + MAX_BIT_DEPTH + ": " + bitDepth);
		}
		this.bitDepth = bitDepth;
	}

	@Override
	public Float apply(Float sample) {
		int binarySample = (int)Math.round((sample * Math.pow(2, bitDepth)));
		return (float)(binarySample / (Math.pow(2, bitDepth)));
	}

}
