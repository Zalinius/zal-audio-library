package com.darzalgames.zalaudiolibrary.amplitude.sustained;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.Interpolation;

/**
 * A wave envelope with an Attack, Sustain and Release phase
 * ASR envelopes last for the entire duration of a note
 */
public class AsrEnvelope implements Envelope {

	private final float attackTime; //in seconds
	private final float releaseTime; //in seconds
	private final UnaryOperator<Float> increasingInterpolation;
	private final UnaryOperator<Float> decreasingInterpolation;

	/**
	 * Constructs an ASR envelope
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param releaseTime the duration of the decreasing phase of the envelope
	 * @param increasingInterpolation the response curve for the the attack phase
	 * @param decreasingInterpolation the response curve for the the release phase
	 */
	public AsrEnvelope(float attackTime, float releaseTime,	UnaryOperator<Float> increasingInterpolation, UnaryOperator<Float> decreasingInterpolation) {
		this.attackTime = attackTime;
		this.releaseTime = releaseTime;
		this.increasingInterpolation = increasingInterpolation;
		this.decreasingInterpolation = decreasingInterpolation;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if(currentTime < 0f || currentTime >= envelopeDuration) {
			return 0f;
		}

		float sustainTime = envelopeDuration - attackTime - releaseTime;

		if(currentTime < attackTime) {
			float interpolant = currentTime / attackTime;
			return increasingInterpolation.apply(interpolant);
		}
		else if (currentTime < attackTime + sustainTime) {
			return 1f;
		}
		else {
			float interpolant = (currentTime - (attackTime + sustainTime)) / releaseTime;
			return decreasingInterpolation.apply(interpolant);
		}
	}

	/**
	 * Builds an ASR envelope with a linear response curve
	 * @param attack the time the envelope is in the attack phase
	 * @param release the time the envelope is in the release phase
	 * @return A linear AR envelope
	 */
	public static AsrEnvelope linear(float attack, float release) {
		return new AsrEnvelope(attack, release, Interpolation.INCREASING_LINEAR, Interpolation.DECREASING_LINEAR);
	}

	/**
	 * Builds an ASR envelope with a quadratic response curve
	 * @param attack the time the envelope is in the attack phase
	 * @param release the time the envelope is in the release phase
	 * @return A quadratic ASR envelope
	 */
	public static AsrEnvelope quadratic(float attack, float release) {
		return new AsrEnvelope(attack, release, Interpolation.INCREASING_NEGATIVE_QUADRATIC, Interpolation.DECREASING_POSITIVE_QUADRATIC);
	}
}