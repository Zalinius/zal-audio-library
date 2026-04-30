package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.effects.sampling.SampleOverflower;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SynthOverflower extends SimpleMusicalEffect {

	private final float overflowAmplitude;

	public SynthOverflower(float overflowAmplitude) {
		if (overflowAmplitude < 0) {
			throw new IllegalArgumentException("overflow amplitude must be non-negative: " + overflowAmplitude);
		}
		this.overflowAmplitude = overflowAmplitude;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant instant) {
		Synth overflowedSynth = overflowSynth(instant.synth(), overflowAmplitude);

		return new MusicalInstant(overflowedSynth, instant.pitch(), instant.frequencyModulator(), instant.duration(), instant.envelope(), instant.amplitude(), instant.id());
	}

	public static Synth overflowSynth(Synth original, float overflowAmplitude) {
		UnaryOperator<Float> originalSynth = original.getWaveFunction();
		UnaryOperator<Float> overflowedSynth = x -> {
			SampleOverflower sampleOverflower = new SampleOverflower(overflowAmplitude);
			return sampleOverflower.apply(originalSynth.apply(x));
		};
		return new Synth(overflowedSynth);
	}

}
