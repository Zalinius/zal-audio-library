package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.PeriodicSynth;

public class SynthChanger extends SimpleMusicalEffect {

	private final PeriodicSynth newSynth;

	public SynthChanger(PeriodicSynth newSynth) {
		this.newSynth = newSynth;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant original) {
		return new MusicalInstant(newSynth, original.pitch(), original.frequencyModulator(), original.duration(), original.envelope(), original.amplitude(), original.id());
	}

}
