package com.darzalgames.zalaudiolibrary.amplitude.percussive;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Interpolation;

/**
 * A wave envelope with an Attack and Release phase
 * The envelope always lasts for the combined duration of the two phases, there is no sustain phase
 */
public class ArEnvelope implements PercussiveEnvelope {

	private final float attackTime; //in seconds
	private final float releaseTime; //in seconds
	private final UnaryOperator<Float> increasingInterpolation;
	private final UnaryOperator<Float> decreasingInterpolation;

	/**
	 * Constructs an AR envelope
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param releaseTime the duration of the decreasing phase of the envelope
	 * @param increasingInterpolation the response curve for the the attack phase
	 * @param decreasingInterpolation the response curve for the the release phase
	 */
	public ArEnvelope(float attackTime, float releaseTime, UnaryOperator<Float> increasingInterpolation, UnaryOperator<Float> decreasingInterpolation) {
		this.attackTime = attackTime;
		this.releaseTime = releaseTime;
		this.increasingInterpolation = increasingInterpolation;
		this.decreasingInterpolation = decreasingInterpolation;
	}

	@Override
	public float getEnvelope(float currentTime) {
		if(currentTime < 0f || currentTime >= attackTime + releaseTime) {
			return 0f;
		}

		if(currentTime < attackTime) {
			float interpolant = currentTime / attackTime;
			return increasingInterpolation.apply(interpolant);
		}
		else {
			float interpolant = (currentTime - attackTime) / releaseTime;
			return decreasingInterpolation.apply(interpolant);
		}
	}

	/**
	 * Builds an AR envelope with a linear response curve
	 * @param attack the time the envelope is in the attack phase
	 * @param release the time the envelope is in the release phase
	 * @return A linear AR envelope
	 */
	public static ArEnvelope linear(float attack, float release) {
		return new ArEnvelope(attack, release, Interpolation.INCREASING_LINEAR, Interpolation.DECREASING_LINEAR);
	}

	/**
	 * Builds an AR envelope with a quadratic response curve
	 * @param attack the time the envelope is in the attack phase
	 * @param release the time the envelope is in the release phase
	 * @return A quadratic AR envelope
	 */
	public static ArEnvelope quadratic(float attack, float release) {
		return new ArEnvelope(attack, release, Interpolation.INCREASING_NEGATIVE_QUADRATIC, Interpolation.DECREASING_POSITIVE_QUADRATIC);
	}
}