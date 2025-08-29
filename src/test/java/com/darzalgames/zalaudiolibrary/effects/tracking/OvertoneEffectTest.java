package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

class OvertoneEffectTest {

	@Test
	void octaveOvertoneEffect_createsMoreInstants_withFirstSharingTheOriginalIdAndPitch() {
		OvertoneEffect overtoneEffect = OvertoneEffect.octaveChord();
		MusicalInstant original = new MusicalInstant(Synth.sine(), Pitch.C4, NoteDuration.QUARTER, ArEnvelope.linear(0.1f, 0.9f), 1, "id");

		List<MusicalInstant> overtones = overtoneEffect.apply(original);
		MusicalInstant baseTone = overtones.get(0);
		MusicalInstant octaveTone = overtones.get(1);

		assertEquals(2, overtones.size());

		assertEquals(original.synth(), baseTone.synth());
		assertEquals(original.pitch(), baseTone.pitch());
		assertEquals(original.duration(), baseTone.duration());
		assertEquals(original.envelope(), baseTone.envelope());
		assertEquals(original.amplitude(), baseTone.amplitude());
		assertEquals(original.id(), baseTone.id());

		assertEquals(original.synth(), octaveTone.synth());
		assertNotEquals(original.pitch(), octaveTone.pitch());
		assertEquals(Pitch.C5, octaveTone.pitch());
		assertEquals(original.duration(), octaveTone.duration());
		assertEquals(original.envelope(), octaveTone.envelope());
		assertEquals(original.amplitude(), octaveTone.amplitude());
		assertNotEquals(original.id(), octaveTone.id());
	}

}
