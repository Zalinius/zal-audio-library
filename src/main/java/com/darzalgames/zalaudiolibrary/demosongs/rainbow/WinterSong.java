package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class WinterSong extends AbstractRainbowSeedSong {

	public WinterSong() {
		super("Winter");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.sine(), TriangleEnvelope.quadratic(0.2f));
	}

	@Override
	public Instrument getSecondaryInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 3), 1f), ArEnvelope.quadratic(0.01f, 0.29f));
	}

	@Override
	public List<MusicalEffect> secondaryMusicalEffects() {
		return List.of(new TransposeEffect(Pitch::octaveDown));
	}

}
