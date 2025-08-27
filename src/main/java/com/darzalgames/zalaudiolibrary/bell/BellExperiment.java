package com.darzalgames.zalaudiolibrary.bell;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.amplitude.AmplitudeModulator;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.PercussiveEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

// An attempt to recreate this sound https://en.wikipedia.org/wiki/File:Additive_synthesis_bell.ogg
public class BellExperiment {


	public static List<Partial> makePartials(float durationMultiplier){
		List<Partial> partials = new ArrayList<>();

		float[] frequencyMultiples = {1.0f, 1.6766169f, 2.1741292f, 2.2537313f, 2.3830845f, 2.4925373f, 3.3731344f, 4.402985f, 5.437811f, 1.1641791f, 2.7363183f, 6.970149f, 8.40796f, 8.855721f, 9.960199f, 12.184079f, 13.1890545f, 14.373135f, 14.935324f, 16.169155f, 18.40796f};
		float[] releaseTimes= {3.5f, 3.75f, 3.0f, 3.0f, 3.0f, 3.0f, 2.4f, 2.0f, 2.0f, 0.75f, 0.85f, 0.75f, 0.75f, 0.75f, 0.75f, 0.75f, 0.6f, 0.85f, 0.6f, 0.5f, 0.5f};
		float[] amplitudes = {0.25f, 0.18f, 0.10f, 0.03f, 0.05f, 0.16f, 0.16f, 0.15f, 0.12f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.03f, 0.03f, 0.03f, 0.03f};

		float[] wobbleAmplitudes = {0f, 1f, .1f, .1f, .1f, 1, .1f, .2f, .2f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		float[] wobbleFrequencies = {0, 2.8f, 9.2f, 9.5f, 10f, 4.8f, 9f, 8.8f, 7.1f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		int partialCount = frequencyMultiples.length;

		for (int i = 0; i < partialCount; i++) {
			partials.add(new Partial(frequencyMultiples[i], wobbleAmplitudes[i], wobbleFrequencies[i], amplitudes[i], releaseTimes[i] * durationMultiplier, i));
		}

		return partials;
	}


	public static class Partial {
		private final Synth synth;
		private final float frequencyMultiplier;
		private final float amplitude;
		private final Envelope envelope;

		private final float amplitudeModulationAmplitude;
		private final float amplitudeModulationFrequency;

		private final int partialIndex;


		public Partial(float frequencyMultiplier, float wobbleAmplitude, float wobbleFrequency, float amplitude, float releaseTime, int partialIndex) {
			synth = Synth.sine();
			this.frequencyMultiplier = frequencyMultiplier;
			amplitudeModulationAmplitude = wobbleAmplitude;
			amplitudeModulationFrequency = wobbleFrequency;
			this.amplitude = amplitude;
			PercussiveEnvelope baseEnvelope = ArEnvelope.quadratic(0.001f, releaseTime-0.001f);
			AmplitudeModulator amplitudeModulator = new AmplitudeModulator(amplitudeModulationAmplitude, amplitudeModulationFrequency);
			envelope = amplitudeModulator.modulateEnvelope(baseEnvelope);
			this.partialIndex = partialIndex;
		}

		public float computeSample(float t, float fundamentalFrequency) {
			float toneComponent = synth.f((t * frequencyMultiplier * fundamentalFrequency)%1);

			return amplitude * envelope.getEnvelope(t, t) * toneComponent;
		}


		public Synth getSynth() {
			return synth;
		}

		public Envelope getEnvelope() {
			return envelope;
		}

		public int getPartialIndex() {
			return partialIndex;
		}

		public float getAmplitude() {
			return amplitude;
		}

		public Pitch getPartialPitch(Pitch fundamental) {
			return Pitch.makePitch(fundamental.getName() + "x" + frequencyMultiplier, fundamental.getFrequency() * frequencyMultiplier);
		}
	}

	public static float decibelToAmplitude(float decibel) {
		return (float) Math.pow(10, decibel/20);
	}

}
