package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

/**
 * A track effect that reverses an envelope of a musical instant, making sounds sound reversed
 */
public class EnvelopeReverser implements TrackEffect{

	@Override
	public MusicalInstant apply(MusicalInstant instant) {
		Envelope reversedEnvelope = reverseEnvelope(instant.envelope());

		return new MusicalInstant(instant.synth(), instant.pitch(), instant.duration(), reversedEnvelope, instant.amplitude(), instant.id());
	}

	/**
	 * creates an envelope which is reversed from the original
	 * @param original The envelope to reverse
	 * @return A reversed envelope, which is like traversing the original envelope backwards
	 */
	public static Envelope reverseEnvelope(Envelope original) {
		return (float envelopeDuration, float currentTime) -> original.getEnvelope(envelopeDuration, envelopeDuration-currentTime);
	}

}
