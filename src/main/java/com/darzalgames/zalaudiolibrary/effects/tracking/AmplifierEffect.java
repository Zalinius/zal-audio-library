package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class AmplifierEffect extends SimpleMusicalEffect {

	private float amplitude;

	public AmplifierEffect(float amplitude) {
		this.amplitude = amplitude;
	}

	public void setAmplitude(float newAmplitude) {
		amplitude = newAmplitude;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant original) {
		float newAmplitude = multiplyAmplitudes(original.amplitude());
		return new MusicalInstant(original.synth(), original.pitch(), original.frequencyModulator(), original.duration(), original.envelope(), newAmplitude, original.id());
	}

	public float multiplyAmplitudes(float originalAmplitude) {
		return amplitude * originalAmplitude;
	}

}
