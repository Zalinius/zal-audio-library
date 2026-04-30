package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.PeriodicSynth;

public class SynthExploder extends SimpleMusicalEffect {

	private final float explodeThreshold;

	public SynthExploder(float explodeThreshold) {
		if (explodeThreshold < 0) {
			throw new IllegalArgumentException("explode threshold must be non-negative: " + explodeThreshold);
		}
		this.explodeThreshold = explodeThreshold;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant instant) {
		PeriodicSynth explodedSynth = explodeSynth(instant.synth(), explodeThreshold);

		return new MusicalInstant(explodedSynth, instant.pitch(), instant.frequencyModulator(), instant.duration(), instant.envelope(), instant.amplitude(), instant.id());
	}

	public static PeriodicSynth explodeSynth(PeriodicSynth original, float explodeThreshold) {
		UnaryOperator<Float> originalSynth = original.getWaveFunction();
		UnaryOperator<Float> explodedSynth = x -> {
			float value = originalSynth.apply(x);
			if (value < 0 && value > -explodeThreshold) {
				value = -explodeThreshold;
			} else if (value >= 0 && value < explodeThreshold) {
				value = explodeThreshold;
			}

			return value;
		};
		return new PeriodicSynth(explodedSynth);
	}

}
