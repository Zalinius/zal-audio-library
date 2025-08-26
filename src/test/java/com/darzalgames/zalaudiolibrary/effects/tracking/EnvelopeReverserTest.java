package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

class EnvelopeReverserTest {

	@Test
	void reverseEnvelope_onSymmetricalEnvelope_returnsIdenticalEnvelope() {
		AsrEnvelope originalEnvelope = AsrEnvelope.linear(0.1f, 0.1f);

		Envelope reversedEnvelope = EnvelopeReverser.reverseEnvelope(originalEnvelope);

		assertEquals(reversedEnvelope.getEnvelope(1f, 0.0f), originalEnvelope.getEnvelope(1f, 0.0f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.2f), originalEnvelope.getEnvelope(1f, 0.2f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.4f), originalEnvelope.getEnvelope(1f, 0.4f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.6f), originalEnvelope.getEnvelope(1f, 0.6f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.8f), originalEnvelope.getEnvelope(1f, 0.8f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 1.0f), originalEnvelope.getEnvelope(1f, 1.0f));
	}

	@Test
	void reverseEnvelope_onNonSymmetricalEnvelope_returnsReversedEnvelope() {
		AdsrEnvelope originalEnvelope = AdsrEnvelope.linear(0.01f, 0.05f, 1f, 0.3f);

		Envelope reversedEnvelope = EnvelopeReverser.reverseEnvelope(originalEnvelope);

		assertEquals(reversedEnvelope.getEnvelope(1f, 0.0f), originalEnvelope.getEnvelope(1f, 1.0f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.2f), originalEnvelope.getEnvelope(1f, 0.8f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.4f), originalEnvelope.getEnvelope(1f, 0.6f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.6f), originalEnvelope.getEnvelope(1f, 0.4f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 0.8f), originalEnvelope.getEnvelope(1f, 0.2f));
		assertEquals(reversedEnvelope.getEnvelope(1f, 1.0f), originalEnvelope.getEnvelope(1f, 0.0f));
	}

	@Test
	void apply_createsInstantWithIdenticalContentsExceptEnvelope() {
		Envelope original = AdsrEnvelope.linear(0.01f, 0.09f, 0.5f, 0.9f);
		MusicalInstant musicalInstant = new MusicalInstant(Synth.sine(), Pitch.C4, NoteDuration.QUARTER, original, 1f, "instant ID");

		MusicalInstant modifiedInstant = new EnvelopeReverser().apply(musicalInstant).get(0);

		assertNotEquals(musicalInstant, modifiedInstant);
		assertEquals(musicalInstant.synth(), modifiedInstant.synth());
		assertEquals(musicalInstant.pitch(), modifiedInstant.pitch());
		assertEquals(musicalInstant.duration(), modifiedInstant.duration());
		assertEquals(musicalInstant.amplitude(), modifiedInstant.amplitude());
		assertEquals(musicalInstant.id(), modifiedInstant.id());
		assertNotEquals(musicalInstant.envelope(), modifiedInstant.envelope());
	}

}
