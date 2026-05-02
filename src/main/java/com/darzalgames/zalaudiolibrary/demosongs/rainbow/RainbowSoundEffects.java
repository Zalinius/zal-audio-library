package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.Collection;
import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class RainbowSoundEffects {

	public static Collection<SoundEffect> soundEffects() {
		return List.of(springSound(), summerSound(), fallSound(), winterSound());
	}

	private static SoundEffect springSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 3), 1f);
		Envelope envelope = ArEnvelope.quadratic(.05f, 0.70f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C4, p -> 1f, 0.75f, envelope, 0.5f, "toot");
		SoundEffect soundEffect = new SoundEffect("springSound");
		soundEffect.addSound(strum1);

		return soundEffect;
	}

	private static SoundEffect summerSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 1), 1f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.49f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C3, p -> 1f, 0.5f, envelope, 0.5f, "strum1");
		SimpleSound strum2 = new SimpleSound(bassSynth, Pitch.E3, p -> 1f, 0.5f, envelope, 0.5f, "strum2");
		SoundEffect soundEffect = new SoundEffect("summerSound");
		soundEffect.addSound(strum1);
		soundEffect.addSound(strum2);

		return soundEffect;
	}

	private static SoundEffect fallSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 1), 1f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.49f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C2, p -> 1f, 0.5f, envelope, 1f, "strum1");
		SoundEffect soundEffect = new SoundEffect("autumnSound");
		soundEffect.addSound(strum1);

		return soundEffect;
	}

	private static SoundEffect winterSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(3), 1);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.29f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C6, p -> 1f, 0.3f, envelope, 0.5f, "winter");
		SoundEffect soundEffect = new SoundEffect("winterSound");
		soundEffect.addSound(strum1);

		return soundEffect;
	}

}
