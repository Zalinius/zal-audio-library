package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.PercussiveEnvelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

/**
 * A track effect that reverses an envelope of a musical instant, making sounds sound reversed
 */
public class EnvelopeReverser extends SimpleMusicalEffect {

	private boolean isEnabled;

	/**
	 * Creates a new disabled EnvelopeReverser
	 */
	public EnvelopeReverser() {
		isEnabled = false;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant instant) {
		if (isEnabled) {
			Envelope reversedEnvelope = reverseEnvelope(instant.envelope());
			return new MusicalInstant(instant.synth(), instant.pitch(), instant.frequencyModulator(), instant.duration(), reversedEnvelope, instant.amplitude(), instant.id());
		} else {
			return instant;
		}
	}

	/**
	 * enables the envelope reverser
	 */
	public void enable() {
		isEnabled = true;
	}

	/**
	 * disables the envelope reverser
	 */
	public void disable() {
		isEnabled = false;
	}

	/**
	 * toggler the envelope reverser
	 * @return the new state of the envelope reverser
	 */
	public boolean toggle() {
		isEnabled = !isEnabled;
		return isEnabled;
	}

	/**
	 * whether or not the envelope reverser is active
	 * @return True if the envelope reverser is enabled, false otherwise
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * creates an envelope which is reversed from the original
	 * @param original The envelope to reverse
	 * @return A reversed envelope, which is like traversing the original envelope backwards
	 */
	public static Envelope reverseEnvelope(Envelope original) {
		if (original instanceof PercussiveEnvelope percussiveEnvelope) {
			return reversePercussiveEnvelope(percussiveEnvelope);
		}
		return (float envelopeDuration, float currentTime) -> original.getEnvelope(envelopeDuration, envelopeDuration - currentTime);
	}

	private static Envelope reversePercussiveEnvelope(PercussiveEnvelope original) {
		return (float envelopeDuration, float currentTime) -> {
			float percussiveDuration = original.getEnvelopeDuration();
			if (currentTime <= percussiveDuration) {
				return original.getEnvelope(envelopeDuration, percussiveDuration - currentTime);
			} else {
				return original.getEnvelope(envelopeDuration, currentTime);
			}
		};
	}

}
