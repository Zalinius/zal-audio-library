package com.darzalgames.zalaudiolibrary.synth.complex;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.amplitude.AmplitudeModulator;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.PercussiveEnvelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

// An attempt to recreate this sound https://en.wikipedia.org/wiki/File:Additive_synthesis_bell.ogg
public class BellComplexSynth implements ComplexSynth {

	private final float releaseMultiplier;
	private final Synth synth;

	public BellComplexSynth() {
		this(1f);
	}

	public BellComplexSynth(float releaseMultiplier) {
		this(releaseMultiplier, Synth.sine());
	}

	public BellComplexSynth(float releaseMultiplier, Synth synth) {
		this.releaseMultiplier = releaseMultiplier;
		this.synth = synth;
	}

	@Override
	public List<Partial> makePartials(){
		List<Partial> partials = new ArrayList<>();

		float[] frequencyMultiples = {1.0f, 1.6766169f, 2.1741292f, 2.2537313f, 2.3830845f, 2.4925373f, 3.3731344f, 4.402985f, 5.437811f, 1.1641791f, 2.7363183f, 6.970149f, 8.40796f, 8.855721f, 9.960199f, 12.184079f, 13.1890545f, 14.373135f, 14.935324f, 16.169155f, 18.40796f};
		float[] releaseTimes= {3.5f, 3.75f, 3.0f, 3.0f, 3.0f, 3.0f, 2.4f, 2.0f, 2.0f, 0.75f, 0.85f, 0.75f, 0.75f, 0.75f, 0.75f, 0.75f, 0.6f, 0.85f, 0.6f, 0.5f, 0.5f};
		float[] amplitudes = {0.25f, 0.18f, 0.10f, 0.03f, 0.05f, 0.16f, 0.16f, 0.15f, 0.12f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 0.03f, 0.03f, 0.03f, 0.03f};

		float[] wobbleAmplitudes = {0f, 1f, .1f, .1f, .1f, 1, .1f, .2f, .2f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		float[] wobbleFrequencies = {0, 2.8f, 9.2f, 9.5f, 10f, 4.8f, 9f, 8.8f, 7.1f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		int partialCount = frequencyMultiples.length;

		for (int i = 0; i < partialCount; i++) {
			float releaseTime = releaseTimes[i] * releaseMultiplier;
			PercussiveEnvelope baseEnvelope = ArEnvelope.quadratic(0.001f, releaseTime-0.001f);
			AmplitudeModulator amplitudeModulator = new AmplitudeModulator(wobbleAmplitudes[i], wobbleFrequencies[i]);
			Envelope envelope = amplitudeModulator.modulateEnvelope(baseEnvelope);

			partials.add(new Partial(synth, frequencyMultiples[i], amplitudes[i], envelope, i));
		}

		return partials;
	}
}
