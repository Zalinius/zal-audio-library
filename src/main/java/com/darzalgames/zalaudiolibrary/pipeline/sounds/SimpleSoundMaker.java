package com.darzalgames.zalaudiolibrary.pipeline.sounds;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SimpleSoundMaker {

	public SimpleSound makeSimpleSound(MusicalInstant musicalInstant, float bps) {
		Synth synth = musicalInstant.getSynth();
		float frequency = musicalInstant.getNote().getFrequency();
		float durationInSeconds = musicalInstant.getDuration().getDurationInBeats().toFloat() / bps;
		Envelope envelope = musicalInstant.getEnvelope();

		return new SimpleSound(synth, durationInSeconds, frequency, envelope);
	}

}
