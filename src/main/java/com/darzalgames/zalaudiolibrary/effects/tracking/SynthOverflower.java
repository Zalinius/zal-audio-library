package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SynthOverflower implements TrackEffect {

	private final float overflowAmplitude;

	public SynthOverflower(float overflowAmplitude) {
		if(overflowAmplitude < 0) {
			throw new IllegalArgumentException("overflow amplitude must be non-negative: " + overflowAmplitude);
		}
		this.overflowAmplitude = overflowAmplitude;
	}

	@Override
	public MusicalInstant apply(MusicalInstant instant) {
		Synth overflowedSynth = overflowSynth(instant.synth(), overflowAmplitude);

		return new MusicalInstant(overflowedSynth, instant.pitch(), instant.duration(), instant.envelope(), instant.amplitude(), instant.id());
	}

	public static Synth overflowSynth(Synth original, float overflowAmplitude) {
		UnaryOperator<Float> originalSynth = original.getWaveFunction();
		UnaryOperator<Float> overflowedSynth = x -> {
			float value = originalSynth.apply(x);
			if(value < -overflowAmplitude) {
				float overflow = value - overflowAmplitude;
				value = overflowAmplitude - overflow;
			}
			else if(value > overflowAmplitude) {
				float overflow = value - overflowAmplitude;
				value = -overflowAmplitude + overflow;
			}

			return value;
		};
		return new Synth(overflowedSynth);
	}

}
