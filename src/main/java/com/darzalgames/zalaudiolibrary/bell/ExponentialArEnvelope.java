package com.darzalgames.zalaudiolibrary.bell;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;

public class ExponentialArEnvelope implements Envelope {

	private final float attackTime; //in seconds
	private final float releaseTime; //in seconds

	/**
	 * Constructs an AR envelope
	 * @param attackTime the duration of the increasing phase of the envelope
	 * @param releaseTime the duration of the decreasing phase of the envelope
	 */
	public ExponentialArEnvelope(float attackTime, float releaseTime) {
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
