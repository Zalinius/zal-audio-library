package com.darzalgames.zalaudiolibrary.amplitude.percussive;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;

/**
 * An envelope with a percussive nature, which is to say it has no sustain phase
 */
public interface PercussiveEnvelope extends Envelope {

	@Override
	default float getEnvelope(float envelopeDuration, float currentTime) {
		return getEnvelope(currentTime);
	}

	/**
	 * Get the percussive envelope amplitude at a certain time
	 * As a convenience, if the currentTime is outside the envelope, 0f should be returned.
	 * @param currentTime the current progress in the envelope
	 * @return the amplitude of the envelope, between [0,1]
	 */
	float getEnvelope(float currentTime);

}
