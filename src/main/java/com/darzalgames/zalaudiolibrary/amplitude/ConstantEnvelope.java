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
		return constant;
	}

	public static final ConstantEnvelope zeroEnvelope() {
		return new ConstantEnvelope(0);
	}

}
