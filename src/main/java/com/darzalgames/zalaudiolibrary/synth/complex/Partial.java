package com.darzalgames.zalaudiolibrary.synth.complex;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.ComplexPitch;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class Partial {
	private final Synth synth;
	private final Envelope frequencyModulator;
	private final float amplitude;
	private final Envelope envelope;

	private final int partialIndex;

	public Partial(Synth synth, float frequencyMultiplier, float amplitude, Envelope envelope, int partialIndex) {
		this(synth, (a,b) -> frequencyMultiplier, amplitude, envelope, partialIndex);
	}

	public Partial(Synth synth, Envelope frequencyModulator, float amplitude, Envelope envelope, int partialIndex) {
		this.synth = synth;
		this.frequencyModulator = frequencyModulator;
		this.envelope = envelope;
		this.amplitude = amplitude;
		this.partialIndex = partialIndex;
	}

	public Synth getSynth() {
		return synth;
	}

	public Envelope getFrequencyModulator() {
		return frequencyModulator;
	}

	public ComplexPitch getPartialPitch(Pitch fundamental) {
		return new ComplexPitch(fundamental, frequencyModulator);
	}

	public float getAmplitude() {
		return amplitude;
	}

	public Envelope getEnvelope() {
		return envelope;
	}

	public int getPartialIndex() {
		return partialIndex;
	}

}
