package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class EnvelopeReverser implements TrackEffect{

	@Override
	public MusicalInstant apply(MusicalInstant instant) {
		Envelope reversedEnvelope = reverseEnvelope(instant.envelope());

		return new MusicalInstant(instant.synth(), instant.pitch(), instant.duration(), reversedEnvelope, instant.amplitude(), instant.id());
	}

	public static Envelope reverseEnvelope(Envelope original) {
		return (float envelopeDuration, float currentTime) -> original.getEnvelope(envelopeDuration, envelopeDuration-currentTime);
	}

}
