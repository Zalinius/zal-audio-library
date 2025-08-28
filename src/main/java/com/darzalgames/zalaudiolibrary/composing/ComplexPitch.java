package com.darzalgames.zalaudiolibrary.composing;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;

public class ComplexPitch {

	private final Pitch basePitch;
	private final Envelope pitchModulator;

	public ComplexPitch(Pitch basePitch) {
		this(basePitch, new ConstantEnvelope(1f));
	}

	public ComplexPitch(Pitch basePitch, Envelope pitchModulator) {
		this.basePitch = basePitch;
		this.pitchModulator = pitchModulator;
	}

	public float getFrequency(float envelopeDuration, float currentTime) {
		float multiplier = pitchModulator.getEnvelope(envelopeDuration, currentTime);

		return basePitch.getFrequency() * multiplier;
	}

	public Pitch getBasePitch() {
		return basePitch;
	}

	public Envelope getPitchModulator() {
		return pitchModulator;
	}

}
