package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.PercussiveEnvelope;

class EnvelopeReverserIT {

	@Test
	void reverseEnvelope_withSymmetricPercussiveEnvelope_hasNoEffect() {
		PercussiveEnvelope envelope = ArEnvelope.linear(0.25f, 0.25f);

		Envelope reversedEnvelope = EnvelopeReverser.reverseEnvelope(envelope);

		float envelopeDuration = 0.5f;
		assertEquals(envelope.getEnvelope(envelopeDuration, 0), reversedEnvelope.getEnvelope(envelopeDuration, 0));
		assertEquals(envelope.getEnvelope(envelopeDuration, 0.25f), reversedEnvelope.getEnvelope(envelopeDuration, 0.25f));
		assertEquals(envelope.getEnvelope(envelopeDuration, 0.5f), reversedEnvelope.getEnvelope(envelopeDuration, 0.5f));
		assertEquals(envelope.getEnvelope(envelopeDuration, 0.75f), reversedEnvelope.getEnvelope(envelopeDuration, 0.75f));
		assertEquals(envelope.getEnvelope(envelopeDuration, 1), reversedEnvelope.getEnvelope(envelopeDuration, 1));
	}

	@Test
	void reverseEnvelope_withSymmetricPercussiveEnvelope_hasNoEffectEvenWithLongDuration() {
		PercussiveEnvelope envelope = ArEnvelope.linear(0.25f, 0.25f);

		Envelope reversedEnvelope = EnvelopeReverser.reverseEnvelope(envelope);

		float envelopeDuration = 1f;
		assertEquals(envelope.getEnvelope(envelopeDuration, 0), reversedEnvelope.getEnvelope(envelopeDuration, 0));
		assertEquals(envelope.getEnvelope(envelopeDuration, 0.25f), reversedEnvelope.getEnvelope(envelopeDuration, 0.25f));
		assertEquals(envelope.getEnvelope(envelopeDuration, 0.5f), reversedEnvelope.getEnvelope(envelopeDuration, 0.5f));
		assertEquals(envelope.getEnvelope(envelopeDuration, 0.75f), reversedEnvelope.getEnvelope(envelopeDuration, 0.75f));
		assertEquals(envelope.getEnvelope(envelopeDuration, 1), reversedEnvelope.getEnvelope(envelopeDuration, 1));
	}

}
