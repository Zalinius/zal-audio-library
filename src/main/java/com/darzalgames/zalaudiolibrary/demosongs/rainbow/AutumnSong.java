package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class AutumnSong extends AbstractRainbowSeedSong {

	public AutumnSong() {
		super("Autumn");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 3), 1f), AdsrEnvelope.quadratic(0.01f, 1.99f, 0.1f, 0.4f));
	}

	@Override
	public List<NoteDuration> getMainNoteDuration() {
		return List.of(NoteDuration.HALF, NoteDuration.HALF);
	}

	@Override
	public Instrument getSecondaryInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 3), 1f), ArEnvelope.linear(0.01f, 0.29f));
	}

	@Override
	public float getMainAmplitude() {
		return 0.2f;
	}

}
