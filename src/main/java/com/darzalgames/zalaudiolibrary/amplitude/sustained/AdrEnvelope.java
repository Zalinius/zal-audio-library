package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.Interpolation;

/**
 * An Attack-Decay-Release envelope, where the release phase is sustained, that is to say, the release is stretched over the remainder of the note duration
 */
public class AdrEnvelope implements Envelope {

	private final float attackTime; // in seconds
	private final float decayTime; // in seconds
	private final float sustainLevel; // as an amplitude ratio (between 0 and 1)

	private final UnaryOperator<Float> increasingInterpolation;
	private final UnaryOperator<Float> decreasingInterpolation;

	/**
	 * Constructs an ADSR envelope
	 * @param attackTime              the duration of the increasing phase of the envelope
	 * @param decayTime               the duration of the decay phase of the envelope
	 * @param releaseLevel            the level at which the decay ends and the sustained release begins
	 * @param increasingInterpolation the response curve for the the attack phase
	 * @param decreasingInterpolation the response curve for the the decay and release phases
	 */
	public AdrEnvelope(float attackTime, float decayTime, float releaseLevel,
			UnaryOperator<Float> increasingInterpolation, UnaryOperator<Float> decreasingInterpolation) {
		this.attackTime = attackTime;
		this.decayTime = decayTime;
		sustainLevel = releaseLevel;

		this.increasingInterpolation = increasingInterpolation;
		this.decreasingInterpolation = decreasingInterpolation;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if (currentTime < 0f || currentTime >= envelopeDuration) {
			return 0f;
		}

		float sustainDuration = envelopeDuration - (attackTime + decayTime);

		if (currentTime < attackTime) {
			float interpolant = currentTime / attackTime;
			return increasingInterpolation.apply(interpolant);
		} else if (currentTime < attackTime + decayTime) {
			float interpolant = (currentTime - attackTime) / decayTime;
			return (1 - sustainLevel) * decreasingInterpolation.apply(interpolant) + sustainLevel;
		} else {
			float interpolant = (currentTime - (attackTime + decayTime)) / sustainDuration;
			return decreasingInterpolation.apply(interpolant) * sustainLevel;
		}
	}

	/**
	 * Builds an ADR envelope with a linear response curve
	 * @param attackTime   the duration of the increasing phase of the envelope
	 * @param decayTime    the duration of the decay phase of the envelope
	 * @param releaseLevel the level at which the decay ends and the sustained release begins
	 * @return A linear ADSR envelope
	 */
	public static final AdrEnvelope linear(float attackTime, float decayTime, float releaseLevel) {
		return new AdrEnvelope(attackTime, decayTime, releaseLevel, Interpolation.INCREASING_LINEAR, Interpolation.DECREASING_LINEAR);
	}

	/**
	 * Builds an ADR envelope with a quadratic response curve
	 * @param attackTime   the duration of the increasing phase of the envelope
	 * @param decayTime    the duration of the decay phase of the envelope
	 * @param releaseLevel the level at which the decay ends and the sustained release begins
	 * @return A quadratic ADSR envelope
	 */
	public static final AdrEnvelope quadratic(float attackTime, float decayTime, float releaseLevel) {
		return new AdrEnvelope(attackTime, decayTime, releaseLevel, Interpolation.INCREASING_NEGATIVE_QUADRATIC, Interpolation.DECREASING_POSITIVE_QUADRATIC);
	}

}