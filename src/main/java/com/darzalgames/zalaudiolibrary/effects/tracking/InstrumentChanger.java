package com.darzalgames.zalaudiolibrary.effects.tracking;

import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class InstrumentChanger extends SimpleMusicalEffect {

	private Instrument newInstrument;

	public InstrumentChanger(Instrument initialInstrument) {
		newInstrument = initialInstrument;
	}

	public void setInstrument(Instrument newInstrument) {
		this.newInstrument = newInstrument;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant mi) {
		return new MusicalInstant(newInstrument.synth(), mi.pitch(), mi.frequencyModulator(), mi.duration(), newInstrument.envelope(), mi.amplitude(), mi.id());
	}

}
