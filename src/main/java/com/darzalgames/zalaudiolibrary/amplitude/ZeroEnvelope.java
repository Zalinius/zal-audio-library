package com.darzalgames.zalaudiolibrary.amplitude;

/**
 * An envelope which always returns 0
 */
public class ZeroEnvelope implements Envelope {

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		return 0;
	}

}
