package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SynthChanger extends SimpleMusicalEffect {

	private Synth synth;

	public SynthChanger(Synth initialSynth) {
		synth = initialSynth;
	}

	public void setSynth(Synth newSynth) {
		synth = newSynth;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant mi) {
		return new MusicalInstant(synth, mi.pitch(), mi.frequencyModulator(), mi.duration(), mi.envelope(), mi.amplitude(), mi.id());
	}

}
