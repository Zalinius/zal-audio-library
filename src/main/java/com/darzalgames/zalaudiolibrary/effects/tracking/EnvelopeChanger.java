package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class EnvelopeChanger extends SimpleMusicalEffect {

	private Envelope envelope;

	public EnvelopeChanger(Envelope initialEnvelope) {
		envelope = initialEnvelope;
	}

	public void setEnvelope(Envelope newEnvelope) {
		envelope = newEnvelope;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant mi) {
		return new MusicalInstant(mi.synth(), mi.pitch(), mi.frequencyModulator(), mi.duration(), envelope, mi.amplitude(), mi.id());
	}

}
