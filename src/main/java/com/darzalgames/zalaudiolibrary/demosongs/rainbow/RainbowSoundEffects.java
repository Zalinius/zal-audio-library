package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.Collection;
import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class RainbowSoundEffects {

	private RainbowSoundEffects() {}

	public static Collection<SoundEffect> soundEffects() {
		return List.of(springSound(), summerSound(), fallSound(), winterSound(), successSound(), greatSuccessSound());
	}

	private static SoundEffect springSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 3), 1f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.70f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C4, p -> 1f, 0.71f, envelope, 0.3f, "toot");
		SoundEffect soundEffect = new SoundEffect("springSound");
		soundEffect.addSound(strum1);

		return soundEffect;
	}

	private static SoundEffect summerSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 1), 1f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.49f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C3, p -> 1f, 0.5f, envelope, 0.4f, "strum1");
		SimpleSound strum2 = new SimpleSound(bassSynth, Pitch.E3, p -> 1f, 0.5f, envelope, 0.4f, "strum2");
		SoundEffect soundEffect = new SoundEffect("summerSound");
		soundEffect.addSound(strum1);
		soundEffect.addSound(strum2, 0.075f);

		return soundEffect;
	}

	private static SoundEffect fallSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 1), 1f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.49f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C2, p -> 1f, 0.5f, envelope, 0.8f, "strum1");
		SoundEffect soundEffect = new SoundEffect("autumnSound");
		soundEffect.addSound(strum1);

		return soundEffect;
	}

	private static SoundEffect winterSound() {
		Synth bassSynth = SynthFactory.sine();
		Envelope envelope = TriangleEnvelope.quadratic(0.2f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C5, p -> 1f, 0.5f, envelope, 0.3f, "winter");
		SimpleSound strum2 = new SimpleSound(bassSynth, Pitch.E5, p -> 1f, 0.5f, envelope, 0.3f, "winter2");
		SoundEffect soundEffect = new SoundEffect("winterSound");
		soundEffect.addSound(strum1);
		soundEffect.addSound(strum2);

		return soundEffect;
	}

	private static SoundEffect successSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(1, 2), 2f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.49f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C4, p -> 1f, 0.5f, envelope, 0.25f, "strum1");
		SimpleSound strum2 = new SimpleSound(bassSynth, Pitch.E4, p -> 1f, 0.5f, envelope, 0.25f, "strum2");
		SimpleSound strum3 = new SimpleSound(bassSynth, Pitch.G4, p -> 1f, 0.5f, envelope, 0.25f, "strum3");
		SimpleSound strum4 = new SimpleSound(bassSynth, Pitch.C5, p -> 1f, 0.5f, envelope, 0.25f, "strum4");
		SoundEffect soundEffect = new SoundEffect("successSound");
		soundEffect.addSound(strum1);
		soundEffect.addSound(strum2);
		soundEffect.addSound(strum3);
		soundEffect.addSound(strum4);

		return soundEffect;
	}

	private static SoundEffect greatSuccessSound() {
		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(1, 2), 2f);
		Envelope envelope = ArEnvelope.quadratic(.01f, 0.74f);

		SimpleSound strum1 = new SimpleSound(bassSynth, Pitch.C4, p -> 1f, 0.75f, envelope, 0.2f, "strum1");
		SimpleSound strum2 = new SimpleSound(bassSynth, Pitch.E4, p -> 1f, 0.75f, envelope, 0.2f, "strum2");
		SimpleSound strum3 = new SimpleSound(bassSynth, Pitch.G4, p -> 1f, 0.75f, envelope, 0.2f, "strum3");
		SimpleSound strum4 = new SimpleSound(bassSynth, Pitch.C5, p -> 1f, 0.75f, envelope, 0.2f, "strum4");
		SimpleSound strum5 = new SimpleSound(bassSynth, Pitch.C6, p -> 1f, 0.75f, envelope, 0.1f, "strum5");
		SoundEffect soundEffect = new SoundEffect("greatSuccessSound");
		soundEffect.addSound(strum1);
		soundEffect.addSound(strum2);
		soundEffect.addSound(strum3);
		soundEffect.addSound(strum4);
		soundEffect.addSound(strum5);

		return soundEffect;
	}

}
