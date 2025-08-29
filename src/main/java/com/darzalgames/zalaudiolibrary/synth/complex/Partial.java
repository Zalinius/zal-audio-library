package com.darzalgames.zalaudiolibrary.synth.complex;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class Partial {
	private final Synth synth;
	private final float frequencyMultiplier;
	private final float amplitude;
	private final Envelope envelope;

	private final int partialIndex;

	public Partial(Synth synth, float frequencyMultiplier, float amplitude, Envelope envelope, int partialIndex) {
		this.synth = synth;
		this.frequencyMultiplier = frequencyMultiplier;
		this.envelope = envelope;
		this.amplitude = amplitude;
		this.partialIndex = partialIndex;
	}

	public Synth getSynth() {
		return synth;
	}

	public Pitch getPartialPitch(Pitch fundamental) {
		return Pitch.makePitch(fundamental.getName() + "x" + frequencyMultiplier , fundamental.getFrequency() * frequencyMultiplier);
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
