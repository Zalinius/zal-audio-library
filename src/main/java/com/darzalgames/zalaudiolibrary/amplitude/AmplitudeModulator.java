package com.darzalgames.zalaudiolibrary.amplitude;

import java.util.function.UnaryOperator;

/**
 * A modulator for amplitudes
 */
public class AmplitudeModulator implements UnaryOperator<Float> {

	private final float modulationAmplitude;
	private final float modulationFrequency;

	/**
	 * @param modulationAmplitude How much the modulator will affect the amplitude. 0 not at all, 1 the amplitude will be a sine wave going between -1 and 1
	 * @param modulationFrequency The frequency at which the amplitude modulates
	 */
	public AmplitudeModulator(float modulationAmplitude, float modulationFrequency) {
		if(modulationAmplitude < 0 || modulationAmplitude > 1) {
			throw new IllegalArgumentException("Modulation Amplitude must be within [0,1]: " + modulationAmplitude);
		}
		this.modulationAmplitude = modulationAmplitude;
		this.modulationFrequency = modulationFrequency;
	}

	@Override
	public Float apply(Float t) {
		float modulationPhase = (t * modulationFrequency);
		float modulationComponent = (float) Math.sin(Math.PI *2 * modulationPhase);
		return (modulationComponent * modulationAmplitude) + (1-modulationAmplitude);
	}

	public Envelope modulateEnvelope(Envelope envelope) {
		return (envelopeDuration, currentTime) -> apply(currentTime) * envelope.getEnvelope(envelopeDuration, currentTime);
	}

}
