package com.darzalgames.zalaudiolibrary.amplitude;

/**
 * A wave envelope with Attack, Sustain and Release phases
 */
public class AsrEnvelope implements Envelope {

	private final float attackTime; //in seconds
	private final float sustainLevel; //as an amplitude ratio (between 0 and 1)
	private final float releaseTime; //in seconds

	/**
	 * Constructs an ASR envelope
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param sustainLevel the level at which the sustained phase of the envelope will be
	 * @param releaseTime the duration of the decreasing phase of the envelope
	 */
	public AsrEnvelope(float attackTime, float sustainLevel, float releaseTime) {
		this.attackTime = attackTime;
		this.sustainLevel = sustainLevel;
		this.releaseTime = releaseTime;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if(currentTime < 0f || currentTime >= envelopeDuration) {
			return 0f;
		}

		float sustainTime = envelopeDuration - attackTime - releaseTime;

		if(currentTime < attackTime) {
			return sustainLevel * currentTime / attackTime;
		}
		else if (currentTime < attackTime + sustainTime) {
			return sustainLevel;
		}
		else {
			return -sustainLevel/releaseTime*currentTime + sustainLevel/releaseTime*(envelopeDuration);
		}
	}
}