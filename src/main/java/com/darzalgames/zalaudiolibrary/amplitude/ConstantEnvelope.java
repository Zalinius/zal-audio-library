package com.darzalgames.zalaudiolibrary.amplitude;

/**
 * An envelope which always returns 0
 */
public class ConstantEnvelope implements Envelope {

	private final float constant;

	public ConstantEnvelope(float constant) {
		this.constant = constant;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if (currentTime < 0 || currentTime > envelopeDuration) {
			return 0f;
		} else {
			return constant;
		}
	}

	public static final ConstantEnvelope zeroEnvelope() {
		return new ConstantEnvelope(0);
	}

}
