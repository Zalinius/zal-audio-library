package com.darzalgames.zalaudiolibrary.pipeline.sounds;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public record SimpleSound(
		/** The synth of the sound */
		Synth timbre,
		/** The base frequency in hertz of the sound */
		Pitch frequency,
		/** The modulator function which multiplies the frequency of the sound */
		UnaryOperator<Float> frequencyModulator,
		/** The duration in seconds of the sound */
		float duration,
		/** The envelope for the sound */
		Envelope envelope,
		/** The amplitude multiplier for the sound */
		float amplitude,
		/** The unique id for the sound */
		String id) {

	/**
	 * Computes the net amplitude of the sound at a given time in it's duration
	 * @param time the time in seconds, in [0, duration]
	 * @return the amplitude envelope of the sound at that point
	 */
	public float computeAmplitude(float time) {
		return amplitude * envelope.getEnvelope(duration, time);
	}

	/**
	 * Computes the frequency op the sound at a given time
	 * @param time the time in seconds, in [0, duration]
	 * @return the frequency at that time, in hertz
	 */
	public float computeFrequency(float time) {
		return frequency.getFrequency() * frequencyModulator.apply(time);
	}

}
