package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class SpringSong extends AbstractRainbowSeedSong {

	public SpringSong() {
		super("Spring");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(3), 3f), AdsrEnvelope.quadratic(0.01f, 0.04f, 0.3f, 0.1f));
	}
}
