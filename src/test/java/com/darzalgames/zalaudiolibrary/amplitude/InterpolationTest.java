package com.darzalgames.zalaudiolibrary.amplitude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

class InterpolationTest {

	@Test
	void linearIncreasing_respectsBoundaryConditionsAndIsLinear() {
		UnaryOperator<Float> linearIncreasing = Interpolation.INCREASING_LINEAR;

		assertEquals(0f, linearIncreasing.apply(0f));
		assertEquals(0.5f, linearIncreasing.apply(0.5f));
		assertEquals(1f, linearIncreasing.apply(1f));
	}

	@Test
	void linearDecreasing_respectsBoundaryConditionsAndIsLinear() {
		UnaryOperator<Float> linearDecreasing = Interpolation.DECREASING_LINEAR;

		assertEquals(1f, linearDecreasing.apply(0f));
		assertEquals(0.5f, linearDecreasing.apply(0.5f));
		assertEquals(0f, linearDecreasing.apply(1f));
	}

	@Test
	void quadraticIncreasing_respectsBoundaryConditionsAndIsQuadratic() {
		UnaryOperator<Float> quadraticIncreasing = Interpolation.INCREASING_NEGATIVE_QUADRATIC;

		assertEquals(0f, quadraticIncreasing.apply(0f));
		assertEquals(0.75f, quadraticIncreasing.apply(0.5f));
		assertEquals(1f, quadraticIncreasing.apply(1f));
	}

	@Test
	void quadraticDecreasing_respectsBoundaryConditionsAndIsQuadratic() {
		UnaryOperator<Float> quadraticDecreasing = Interpolation.DECREASING_POSITIVE_QUADRATIC;

		assertEquals(1f, quadraticDecreasing.apply(0f));
		assertEquals(0.25f, quadraticDecreasing.apply(0.5f));
		assertEquals(0f, quadraticDecreasing.apply(1f));
	}

}
