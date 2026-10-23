package com.darzalgames.zalaudiolibrary.demosongs.thedoor;

import java.util.List;
import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class SpookySoundEffects {

	public static List<SoundEffect> soundEffects() {
		return List.of(effect1(), effect2(), effect3());
	}

	public static SoundEffect effect1() {
		Synth synth = SynthFactory.pulse(.3f);
		Envelope envelope = AdsrEnvelope.quadratic(.02f, .08f, .3f, 0.2f);
		UnaryOperator<Float> pitchShift = t -> 1f - (t / 10);

		SimpleSound strum1 = new SimpleSound(synth, Pitch.B3, pitchShift, 0.3f, envelope, 0.3f, "tsing1");
		SimpleSound strum2 = new SimpleSound(synth, Pitch.F3, pitchShift, 0.3f, envelope, 0.3f, "tsing2");
		SoundEffect soundEffect = new SoundEffect("short harpsichord sfx");
		soundEffect.addSound(strum2);
		soundEffect.addSound(strum1);

		return soundEffect;
	}

	public static SoundEffect effect2() {
		Synth synth = SynthFactory.pulse(.3f);
		Envelope envelope = AdsrEnvelope.quadratic(.02f, .08f, .3f, 0.3f);
		UnaryOperator<Float> pitchShift = t -> 1f - (t / 10);

		SimpleSound strum1 = new SimpleSound(synth, Pitch.B3, pitchShift, 0.7f, envelope, 0.3f, "tsing1");
		SimpleSound strum2 = new SimpleSound(synth, Pitch.F3, pitchShift, 0.7f, envelope, 0.3f, "tsing2");
		SoundEffect soundEffect = new SoundEffect("medium harpsichord sfx");
		soundEffect.addSound(strum2);
		soundEffect.addSound(strum1);

		return soundEffect;
	}

	public static SoundEffect effect3() {
		Synth synth = SynthFactory.pulse(.3f);
		Envelope envelope = AdsrEnvelope.quadratic(.02f, .08f, .3f, 1f);
		UnaryOperator<Float> pitchShift = t -> 1f - (t / 10);

		SimpleSound strum1 = new SimpleSound(synth, Pitch.B2, pitchShift, 1.5f, envelope, 0.3f, "tsing1");
		SimpleSound strum2 = new SimpleSound(synth, Pitch.F2, pitchShift, 1.5f, envelope, 0.3f, "tsing2");
		SoundEffect soundEffect = new SoundEffect("long deep harpsichord sfx");
		soundEffect.addSound(strum2);
		soundEffect.addSound(strum1);

		return soundEffect;
	}

}
