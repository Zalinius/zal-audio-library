package com.darzalgames.zalaudiolibrary.sfx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

class SoundEffectTest {

	private static final float ALLOWED_ERROR = 0.00001f;

	@Test
	void duration_withInstantSounds_returnsDurationOfLongestIndividualSound() {
		float duration1 = 1.3f;
		SimpleSound s1 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, duration1, ConstantEnvelope.zeroEnvelope(), 1f, "sound1");
		float duration2 = 1.5f;
		SimpleSound s2 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, duration2, ConstantEnvelope.zeroEnvelope(), 1f, "sound2");
		float duration3 = 1.1f;
		SimpleSound s3 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, duration3, ConstantEnvelope.zeroEnvelope(), 1f, "sound3");

		SoundEffect soundEffect = new SoundEffect("sfx", s1, s2, s3);

		assertEquals(1.5f, soundEffect.duration());
	}

	@Test
	void duration_withDelayedSounds_returnsTimingOfLastEndingSound() {
		float duration1 = 1.3f;
		SimpleSound s1 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, duration1, ConstantEnvelope.zeroEnvelope(), 1f, "sound1");
		float duration2 = 1.5f;
		float delay2 = 0.1f;
		SimpleSound s2 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, duration2, ConstantEnvelope.zeroEnvelope(), 1f, "sound2");
		float duration3 = 1.1f;
		float delay3 = .8f;
		SimpleSound s3 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, duration3, ConstantEnvelope.zeroEnvelope(), 1f, "sound3");

		SoundEffect soundEffect = new SoundEffect("sfx", s1);
		soundEffect.addSound(s2, delay2);
		soundEffect.addSound(s3, delay3);

		assertEquals(1.9f, soundEffect.duration(), ALLOWED_ERROR);
	}

	@Test
	void addSound_withAlreadyUsedSimpleSoundId_throwsIllegalArgumentException() {
		SimpleSound s1 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, 1f, ConstantEnvelope.zeroEnvelope(), 1f, "sound1");
		SimpleSound s2 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, 1f, ConstantEnvelope.zeroEnvelope(), 1f, "sound2");
		SimpleSound s3 = new SimpleSound(SynthFactory.zero(), Pitch.NONE, t -> 1f, 1f, ConstantEnvelope.zeroEnvelope(), 1f, "sound2");
		SoundEffect soundEffect = new SoundEffect("sfx", s1, s2);

		assertThrows(IllegalArgumentException.class, () -> soundEffect.addSound(s1));
		assertThrows(IllegalArgumentException.class, () -> soundEffect.addSound(s2));
		assertThrows(IllegalArgumentException.class, () -> soundEffect.addSound(s3));
		assertThrows(IllegalArgumentException.class, () -> soundEffect.addSound(s1, 1f));
		assertThrows(IllegalArgumentException.class, () -> new SoundEffect("sfx", s1, s2, s3));
	}

}
