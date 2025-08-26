package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.Interpolation;

/**
 * A sustained wave envelope in a triangle shape, with an Attack and Release phase
 * It's the same shape as an AR envelope, but will be stretched to the duration of the note played.
 */
public class TriangleEnvelope implements Envelope {

	private final float attackPercentage;
	private final float releasePercentage;
	private final UnaryOperator<Float> increasingInterpolation;
	private final UnaryOperator<Float> decreasingInterpolation;

	/**
	 * Constructs a Triangle envelope which increases for a certain fraction of time, then decrease during the rest
	 * @param attackPercentage What percentage of the envelope is increasing
	 * @param increasingInterpolation the response curve for the the attack phase
	 * @param decreasingInterpolation the response curve for the the release phase
	 */
	public TriangleEnvelope(float attackPercentage, UnaryOperator<Float> increasingInterpolation, UnaryOperator<Float> decreasingInterpolation) {
		if(attackPercentage < 0 || attackPercentage > 1f) {
			throw new IllegalArgumentException("attack percentage must be between 0 and 1: " + attackPercentage);
		}
		this.attackPercentage = attackPercentage;
		releasePercentage = 1f- attackPercentage;
		this.increasingInterpolation = increasingInterpolation;
		this.decreasingInterpolation = decreasingInterpolation;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if(currentTime < 0f || currentTime >= envelopeDuration) {
			return 0f;
		}

		float envelopeProgress = currentTime / envelopeDuration;

		if(envelopeProgress < attackPercentage) {
			float interpolant = envelopeProgress / attackPercentage;
			return increasingInterpolation.apply(interpolant);
		}
		else {
			float interpolant = (envelopeProgress - attackPercentage) / releasePercentage;
			return decreasingInterpolation.apply(interpolant);
		}
	}

	/**
	 * Builds a triangle envelope with a linear response curve
	 * @param attackPercentage the percentage of the time the envelope is in the attack phase
	 * @return A linear triangle envelope
	 */
	public static TriangleEnvelope linear(float attackPercentage) {
		return new TriangleEnvelope(attackPercentage, Interpolation.INCREASING_LINEAR, Interpolation.DECREASING_LINEAR);
	}

	/**
	 * Builds a triangle envelope with a quadratic response curve
	 * @param attackPercentage the percentage of the time the envelope is in the attack phase
	 * @return A quadratic triangle envelope
	 */
	public static TriangleEnvelope quadratic(float attackPercentage) {
		return new TriangleEnvelope(attackPercentage, Interpolation.INCREASING_NEGATIVE_QUADRATIC, Interpolation.DECREASING_POSITIVE_QUADRATIC);
	}

}