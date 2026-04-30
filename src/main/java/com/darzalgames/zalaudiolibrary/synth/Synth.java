package com.darzalgames.zalaudiolibrary.synth;

import java.util.function.UnaryOperator;

/**
 * A synthetic sound, built on a wave function, with domain [0,1] and range (typically) [-1,1]
 */
public class Synth {

	private final UnaryOperator<Float> waveFunction;

	/**
	 * Constructs a synth from a wave function
	 * @param waveFunction forming the basis of the synth
	 */
	public Synth(UnaryOperator<Float> waveFunction) {
		this.waveFunction = waveFunction;
	}

	/**
	 * Computes the synth value at a given point
	 * @param x the progress through the synth's period, between [0,1]
	 * @return A wave height, between [-1, 1]
	 * @throws IllegalArgumentException if x is not within [0,1]
	 */
	public float f(float x) {
		if (x < 0f || x > 1f) {
			throw new IllegalArgumentException("Argument must be in range [0,1] : " + x);
		}
		return waveFunction.apply(x);
	}

	public UnaryOperator<Float> getWaveFunction() {
		return waveFunction;
	}

}
