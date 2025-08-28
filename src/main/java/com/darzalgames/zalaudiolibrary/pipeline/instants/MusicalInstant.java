package com.darzalgames.zalaudiolibrary.pipeline.instants;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.ComplexPitch;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public record MusicalInstant(Synth synth, ComplexPitch pitch, NoteDuration duration, Envelope envelope, float amplitude, String id) {

	public MusicalInstant(Synth synth, Pitch pitch, NoteDuration duration, Envelope envelope, float amplitude, String id) {
		this(synth, new ComplexPitch(pitch, new ConstantEnvelope(1)), duration, envelope, amplitude, id);
	}

}
