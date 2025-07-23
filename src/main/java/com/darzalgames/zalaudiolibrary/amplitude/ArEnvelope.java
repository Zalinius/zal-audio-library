package com.darzalgames.zalaudiolibrary.amplitude;

/**
 * A wave envelope with an Attack and Release phase
 * The envelope always lasts for the combined duration of the two phases
 */
public class ArEnvelope implements Envelope {

	private final float attackTime; //in seconds
	private final float releaseTime; //in seconds

	/**
	 * Constructs an AR envelope
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param releaseTime the duration of the decreasing phase of the envelope
	 */
	public ArEnvelope(float attackTime, float releaseTime) {
		this.attackTime = attackTime;
		this.releaseTime = releaseTime;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if(currentTime < 0f || currentTime >= attackTime + releaseTime) {
			return 0f;
		}

		if(currentTime < attackTime) {
			return currentTime / attackTime;
		}
		else {
			float releaseScaledTime = currentTime -attackTime;
			return 1f - releaseScaledTime/releaseTime;
		}
	}

}