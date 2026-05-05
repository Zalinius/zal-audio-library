package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class SpringSong extends AbstractRainbowSeedSong {

	public SpringSong() {
		super("Spring");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(2, 3), 1.5f), TriangleEnvelope.quadratic(0.01f));
	}

	@Override
	public List<MusicalEffect> mainMusicalEffects() {
		return List.of(new TransposeEffect(Pitch::octaveDown));
	}

	@Override
	public List<NoteDuration> getMainNoteDuration() {
		return List.of(NoteDuration.QUARTER, NoteDuration.HALF_DOT);
	}

	@Override
	public Instrument getSecondaryInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 3), 1f), ArEnvelope.quadratic(0.01f, 0.49f));
	}

}
