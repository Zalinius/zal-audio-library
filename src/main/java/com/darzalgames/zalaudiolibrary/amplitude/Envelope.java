package com.darzalgames.zalaudiolibrary.amplitude;

/**
 * A interface representing wave and sound envelopes
 * inputs outside the duration of the envelope should return 0, and inputs inside should returns values in [0,1]
 */
public interface Envelope {
	/**
	 * Get the envelope amplitude at a certain time
	 * @param envelopeDuration the total duration of the envelope
	 * @param currentTime the current progress in the envelope
	 * @return the amplitude of the envelope, between [0,1]
	 */
	float getEnvelope(float envelopeDuration, float currentTime);
}