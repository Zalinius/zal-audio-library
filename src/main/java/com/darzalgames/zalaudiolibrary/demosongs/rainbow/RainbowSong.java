package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class RainbowSong extends AbstractRainbowSeedSong {

	public RainbowSong() {
		super("Rainbow");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 1), 1.5f), TriangleEnvelope.linear(0.02f));
	}

	@Override
	public Instrument getSecondaryInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 1), 1f), ArEnvelope.quadratic(0.01f, 0.49f));
	}

	@Override
	public List<NoteDuration> getMainNoteDuration() {
		return List.of(NoteDuration.QUARTER, NoteDuration.SIXTH, NoteDuration.SIXTH, NoteDuration.SIXTH, NoteDuration.QUARTER);
	}

	@Override
	public float getMainAmplitude() {
		return 0.5f;
	}

}
