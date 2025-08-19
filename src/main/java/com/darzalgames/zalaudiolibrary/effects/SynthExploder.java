package com.darzalgames.zalaudiolibrary.effects;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SynthExploder implements TrackEffect {

	private final float explodeThreshold;

	public SynthExploder(float explodeThreshold) {
		if(explodeThreshold < 0) {
			throw new IllegalArgumentException("explode threshold must be non-negative: " + explodeThreshold);
		}
		this.explodeThreshold = explodeThreshold;
	}

	@Override
	public MusicalInstant apply(MusicalInstant instant) {
		Synth explodedSynth = explodeSynth(instant.synth(), explodeThreshold);

		return new MusicalInstant(explodedSynth, instant.pitch(), instant.duration(), instant.envelope(), instant.amplitude(), instant.id());
	}

	public static Synth explodeSynth(Synth original, float explodeThreshold) {
		UnaryOperator<Float> originalSynth = original.getWaveFunction();
		UnaryOperator<Float> explodedSynth = x -> {
			float value = originalSynth.apply(x);
			if(value < 0 && value > -explodeThreshold) {
				value = -explodeThreshold;
			}
			else if(value >= 0 && value < explodeThreshold) {
				value = explodeThreshold;
			}

			return value;
		};
		return new Synth(explodedSynth);
	}

}
