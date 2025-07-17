package com.darzalgames.zalaudiolibrary.pipeline.sounds;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SimpleSound {

	private final Synth timbre;
	private final float duration; // in seconds
	private final float frequency;
	private final Envelope envelope;

	public SimpleSound(Synth timbre, float duration, float frequency, Envelope envelope) {
		this.timbre = timbre;
		this.duration = duration;
		this.frequency = frequency;
		this.envelope = envelope;
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

	public Envelope getEnvelope() {
		return envelope;
	}

}
