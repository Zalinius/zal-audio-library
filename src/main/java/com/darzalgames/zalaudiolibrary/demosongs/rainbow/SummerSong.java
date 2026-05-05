package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.List;
import java.util.Set;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.effects.tracking.AmplifierEffect;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.effects.tracking.OvertoneEffect;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class SummerSong extends AbstractRainbowSeedSong {

	public SummerSong() {
		super("Summer");
	}

	@Override
	public Instrument getMainInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(2, 1), 1.5f), TriangleEnvelope.quadratic(0.04f));
	}

	@Override
	public List<MusicalEffect> mainMusicalEffects() {
		return List.of(
				OvertoneEffect.evenOvertoneEffect(p -> Set.of(p, p.octaveUp())),
				new AmplifierEffect(0.5f)
		);
	}

	@Override
	public List<NoteDuration> getMainNoteDuration() {
		return List.of(NoteDuration.QUARTER, NoteDuration.QUARTER, NoteDuration.QUARTER, NoteDuration.QUARTER);
	}

	@Override
	public Instrument getSecondaryInstrument() {
		return new Instrument(SynthFactory.rationalFrequencyModulator(new Fraction(1, 3), 1f), ArEnvelope.quadratic(0.01f, 0.49f));
	}

}
