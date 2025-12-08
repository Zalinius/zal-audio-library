package com.darzalgames.zalaudiolibrary.composing;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public record Instrument(Synth synth, Envelope envelope, UnaryOperator<Float> frequencyModulator) {
	public Instrument(Synth synth, Envelope envelope) {
		this(synth, envelope, noFrequencyModulation());
	}

	public static Instrument percussiveLaser(float duration) {
		return kickDrum(duration);
	}

	public static Instrument kickDrum(float duration) {
		float samplingDuration = duration * 2;
		Envelope envelope = ArEnvelope.quadratic(0.01f, duration - 0.01f);
		Synth synth = Synth.sine();
		UnaryOperator<Float> frequencyModulator = decayingFrequencyModulator(samplingDuration);
		return new Instrument(synth, envelope, frequencyModulator);
	}

	private static UnaryOperator<Float> decayingFrequencyModulator(float duration) {
		return t -> 1f - (t / duration);
	}

	public static UnaryOperator<Float> noFrequencyModulation() {
		return t -> 1f;
	}

}
