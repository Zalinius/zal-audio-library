package com.darzalgames.zalaudiolibrary.pipeline.instants;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class MusicalInstant {

	private final Synth synth;
	private final NoteDuration duration;
	private final Pitch note;
	private final Envelope envelope;

	public MusicalInstant(Synth synth, NoteDuration duration, Pitch note, Envelope envelope) {
		this.synth = synth;
		this.duration = duration;
		this.note = note;
		this.envelope = envelope;
	}

	public Synth getSynth() {
		return synth;
	}

	public NoteDuration getDuration() {
		return duration;
	}

	public Pitch getNote() {
		return note;
	}

	public Envelope getEnvelope() {
		return envelope;
	}



}
