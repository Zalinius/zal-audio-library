package com.darzalgames.zalaudiolibrary.synth.complex;

import com.darzalgames.zalaudiolibrary.amplitude.AmplitudeModulator;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.PercussiveEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class Partial {
	private final Synth synth;
	private final float frequencyMultiplier;
	private final float amplitude;
	private final Envelope envelope;

	private final float amplitudeModulationAmplitude;
	private final float amplitudeModulationFrequency;

	private final int partialIndex;


	public Partial(float frequencyMultiplier, float wobbleAmplitude, float wobbleFrequency, float amplitude, float releaseTime, int partialIndex) {
		synth = Synth.sine();
		this.frequencyMultiplier = frequencyMultiplier;
		amplitudeModulationAmplitude = wobbleAmplitude;
		amplitudeModulationFrequency = wobbleFrequency;
		this.amplitude = amplitude;
		PercussiveEnvelope baseEnvelope = ArEnvelope.quadratic(0.001f, releaseTime-0.001f);
		AmplitudeModulator amplitudeModulator = new AmplitudeModulator(amplitudeModulationAmplitude, amplitudeModulationFrequency);
		envelope = amplitudeModulator.modulateEnvelope(baseEnvelope);
		this.partialIndex = partialIndex;
	}

	public float computeSample(float t, float fundamentalFrequency) {
		float toneComponent = synth.f((t * frequencyMultiplier * fundamentalFrequency)%1);

		return amplitude * envelope.getEnvelope(t, t) * toneComponent;
	}


	public Synth getSynth() {
		return synth;
	}

	public Envelope getEnvelope() {
		return envelope;
	}

	public int getPartialIndex() {
		return partialIndex;
	}

	public float getAmplitude() {
		return amplitude;
	}

	public Pitch getPartialPitch(Pitch fundamental) {
		return Pitch.makePitch(fundamental.getName() + "x" + frequencyMultiplier, fundamental.getFrequency() * frequencyMultiplier);
	}
}
