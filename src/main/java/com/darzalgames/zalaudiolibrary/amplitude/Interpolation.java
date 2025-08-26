package com.darzalgames.zalaudiolibrary.amplitude;

import java.util.function.UnaryOperator;

/**
 * Convenience Interpolation functions for amplitude envelopes
 * They all have domains and ranges in [0,1]
 */
public class Interpolation {

	private Interpolation() {}

	/**
	 * An increasing linear interpolation
	 */
	public static final UnaryOperator<Float> INCREASING_LINEAR = x -> x;
	/**
	 * An decreasing linear interpolation
	 */
	public static final UnaryOperator<Float> DECREASING_LINEAR = x -> 1-x;

	/**
	 * The increasing part of the downward facing quadratic
	 */
	public static final UnaryOperator<Float> INCREASING_NEGATIVE_QUADRATIC = x -> -1*(x-1)*(x-1) + 1;
	/**
	 * The decreasing part of the upward facing quadratic
	 */
	public static final UnaryOperator<Float> DECREASING_POSITIVE_QUADRATIC = x -> (x-1)*(x-1);

}
