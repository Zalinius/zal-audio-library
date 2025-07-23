package com.darzalgames.zalaudiolibrary.amplitude;

/**
 * A wave envelope in a triangle shape, with an Attack and Release phase
 */
public class TriangleEnvelope implements Envelope {

	private final float attackPercentage;

	/**
	 * Constructs a Triangle envelope whick increases for a certain fraction of time, then decrease during the rest
	 * @param attackPercentage What percentage of the envelope is increasing
	 */
	public TriangleEnvelope(float attackPercentage) {
		this.attackPercentage = attackPercentage;
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		if(currentTime < 0f || currentTime >= envelopeDuration) {
			return 0f;
		}

		float envelopePercentage = currentTime / envelopeDuration;

		if(envelopePercentage < attackPercentage) {
			return envelopePercentage / attackPercentage;
		}
		else {
			float releasePercentage = 1f - attackPercentage;
			return 1-((envelopePercentage - attackPercentage)/releasePercentage);
		}
	}

}