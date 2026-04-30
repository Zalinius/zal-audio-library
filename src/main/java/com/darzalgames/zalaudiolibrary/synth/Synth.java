package com.darzalgames.zalaudiolibrary.synth;

import java.util.function.UnaryOperator;

public interface Synth {

	/**
	 * Computes the synth value at a given point
	 * @param x the progress through the synth's period, between [0,1]
	 * @return A wave height, between [-1, 1]
	 */
	float f(float x);

	UnaryOperator<Float> getWaveFunction();

}
