package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.Interpolation;

/**
 * A wave envelope with an Attack, Decay and Sustain and Release phase
 * ADSR envelopes last for the entire duration of a note
 */
public class AdsrEnvelope implements Envelope {

	private final float attackTime; //in seconds
	private final float decayTime; //in seconds
	private final float sustainLevel; //as an amplitude ratio (between 0 and 1)
	private final float releaseTime; //in seconds

	private final UnaryOperator<Float> increasingInterpolation;
	private final UnaryOperator<Float> decreasingInterpolation;

	/**
	 * Constructs an ADSR envelope
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param decayTime the duration of the decay phase of the envelope
	 * @param sustainLevel the sustain level between the decay and release phases
	 * @param releaseTime the duration of the final release phase of the envelope
	 * @param increasingInterpolation the response curve for the the attack phase
	 * @param decreasingInterpolation the response curve for the the decay and release phases
	 */
	public AdsrEnvelope(float attackTime, float decayTime, float sustainLevel, float releaseTime,
			UnaryOperator<Float> increasingInterpolation, UnaryOperator<Float> decreasingInterpolation) {
		this.attackTime = attackTime;
		this.decayTime = decayTime;
		this.sustainLevel = sustainLevel;
		this.releaseTime = releaseTime;

		this.increasingInterpolation = increasingInterpolation;
		this.decreasingInterpolation = decreasingInterpolation;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if(currentTime < 0f || currentTime >= envelopeDuration) {
			return 0f;
		}

		float sustainTime = envelopeDuration - (attackTime + decayTime + releaseTime);

		if(currentTime < attackTime) {
			float interpolant = currentTime/attackTime;
			return increasingInterpolation.apply(interpolant);
		}
		else if (currentTime < attackTime + decayTime) {
			float interpolant = (currentTime - attackTime) / decayTime;
			return (1-sustainLevel)*decreasingInterpolation.apply(interpolant) + sustainLevel;
		}
		else if (currentTime < attackTime + decayTime + sustainTime) {
			return sustainLevel;
		}
		else {
			float interpolant = (releaseTime + currentTime -envelopeDuration) / releaseTime;
			return decreasingInterpolation.apply(interpolant) * sustainLevel;
		}
	}

	/**
	 * Builds an ADSR envelope with a linear response curve
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param decayTime the duration of the decay phase of the envelope
	 * @param sustainLevel the sustain level between the decay and release phases
	 * @param releaseTime the duration of the final release phase of the envelope
	 * @return A linear ADSR envelope
	 */
	public static final AdsrEnvelope linear(float attackTime, float decayTime, float sustainLevel, float releaseTime) {
		return new AdsrEnvelope(attackTime, decayTime, sustainLevel, releaseTime, Interpolation.INCREASING_LINEAR, Interpolation.DECREASING_LINEAR);
	}

	/**
	 * Builds an ADSR envelope with a quadratic response curve
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param decayTime the duration of the decay phase of the envelope
	 * @param sustainLevel the sustain level between the decay and release phases
	 * @param releaseTime the duration of the final release phase of the envelope
	 * @return A quadratic ADSR envelope
	 */
	public static final AdsrEnvelope quadratic(float attackTime, float decayTime, float sustainLevel, float releaseTime) {
		return new AdsrEnvelope(attackTime, decayTime, sustainLevel, releaseTime, Interpolation.INCREASING_NEGATIVE_QUADRATIC, Interpolation.DECREASING_POSITIVE_QUADRATIC);
	}

}