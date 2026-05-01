package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class WinterSong extends AbstractRainbowSeedSong {

	public WinterSong() {
		super("Winter");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 3), 1f), ArEnvelope.quadratic(0.01f, 0.49f));
	}

}
