package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

class TransposeEffectTest {

	@Test
	void transpose_createsCopyOfInstantWithTransposedPitch() {
		TransposeEffect transposeEffect = new TransposeEffect(Pitch::up);
		MusicalInstant original = new MusicalInstant(Synth.sine(), Pitch.C4, Instrument.noFrequencyModulation(), NoteDuration.QUARTER, ArEnvelope.linear(0.1f, 0.9f), 1, "id");

		MusicalInstant transpose = transposeEffect.applySimpleEffect(original);

		assertNotEquals(original.pitch(), transpose.pitch());
		assertEquals(Pitch.D4, transpose.pitch());
		assertEquals(original.synth(), transpose.synth());
		assertEquals(original.duration(), transpose.duration());
		assertEquals(original.envelope(), transpose.envelope());
		assertEquals(original.amplitude(), transpose.amplitude());
		assertEquals(original.id(), transpose.id());
	}

}
