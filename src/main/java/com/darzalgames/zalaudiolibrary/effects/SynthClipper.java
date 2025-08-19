package com.darzalgames.zalaudiolibrary.effects;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SynthClipper implements TrackEffect {

	private final float clipAmplitude;

	public SynthClipper(float clipAmplitude) {
		if(clipAmplitude < 0) {
			throw new IllegalArgumentException("clip amplitude must be non-negative: " + clipAmplitude);
		}
		this.clipAmplitude = clipAmplitude;
	}

	@Override
	public MusicalInstant apply(MusicalInstant instant) {
		Synth clippedSynth = clipSynth(instant.synth(), clipAmplitude);

		return new MusicalInstant(clippedSynth, instant.pitch(), instant.duration(), instant.envelope(), instant.amplitude(), instant.id());
	}

	public static Synth clipSynth(Synth original, float clipAmplitude) {
		UnaryOperator<Float> originalSynth = original.getWaveFunction();
		UnaryOperator<Float> clippedSynth = x -> {
			float value = originalSynth.apply(x);
			if(value < -clipAmplitude) {
				value = -clipAmplitude;
			}
			else if(value > clipAmplitude) {
				value = clipAmplitude;
			}

			return value;
		};
		return new Synth(clippedSynth);
	}

}
