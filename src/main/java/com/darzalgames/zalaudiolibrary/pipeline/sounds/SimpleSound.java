package com.darzalgames.zalaudiolibrary.pipeline.sounds;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SimpleSound {

	private final Synth timbre;
	private final float frequency;
	private final float duration; // in seconds
	private final Envelope envelope;
	private final float amplitude;
	private final String id;

	public SimpleSound(Synth timbre, float frequency, float duration, Envelope envelope, float amplitude, String id) {
		this.timbre = timbre;
		this.frequency = frequency;
		this.duration = duration;
		this.envelope = envelope;
		this.amplitude = amplitude;
		this.id = id;
	}

	public Synth getTimbre() {
		return timbre;
	}

	public float getDuration() {
		return duration;
	}

	public float getFrequency() {
		return frequency;
	}

	public String getId() {
		return id;
	}

	public float computeAmplitude(float time) {
		return amplitude * envelope.getEnvelope(duration, time);
	}

	public float computeFrequency(float time) {
		return frequency;
	}

}
