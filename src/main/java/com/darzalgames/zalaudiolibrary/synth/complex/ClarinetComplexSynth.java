package com.darzalgames.zalaudiolibrary.synth.complex;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArbitraryEnvelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class ClarinetComplexSynth implements ComplexSynth {

	private static final float AMPLITUDE_NORMALIZER = 0.66f;

	private final float durationMultiplier;

	public ClarinetComplexSynth() {
		this(1f);
	}

	public ClarinetComplexSynth(float durationMultiplier) {
		this.durationMultiplier = durationMultiplier;
	}

	@Override
	public List<Partial>  makePartials() {
		List<Partial> partials = new ArrayList<>();

		List<List<Tuple<Float, Float>>> envelopes = new ArrayList<>();
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.007f, 0.0f), new Tuple<>(0.02f, 0.006f), new Tuple<>(0.032f, 0.073f), new Tuple<>(0.048f, 0.445f), new Tuple<>(0.199f, 0.361f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.024f, 0.0f), new Tuple<>(0.043f, 0.022f), new Tuple<>(0.104f, 0.002f), new Tuple<>(0.193f, 0.004f), new Tuple<>(0.238f, 0.01f), new Tuple<>(0.301f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.015f, 0.0f), new Tuple<>(0.037f, 0.012f), new Tuple<>(0.048f, 0.159f), new Tuple<>(0.204f, 0.122f), new Tuple<>(0.286f, 0.017f), new Tuple<>(0.309f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.009f, 0.0f), new Tuple<>(0.019f, 0.002f), new Tuple<>(0.024f, 0.0f), new Tuple<>(0.039f, 0.0f), new Tuple<>(0.046f, 0.026f), new Tuple<>(0.103f, 0.003f), new Tuple<>(0.167f, 0.005f), new Tuple<>(0.229f, 0.01f), new Tuple<>(0.291f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.006f, 0.0f), new Tuple<>(0.018f, 0.005f), new Tuple<>(0.025f, 0.0f), new Tuple<>(0.039f, 0.0f), new Tuple<>(0.054f, 0.375f), new Tuple<>(0.212f, 0.21f), new Tuple<>(0.266f, 0.02f), new Tuple<>(0.295f, 0.0f), new Tuple<>(0.33f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.011f, 0.0f), new Tuple<>(0.015f, 0.0f), new Tuple<>(0.041f, 0.0f), new Tuple<>(0.048f, 0.025f), new Tuple<>(0.108f, 0.004f), new Tuple<>(0.216f, 0.012f), new Tuple<>(0.282f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.018f, 0.001f), new Tuple<>(0.024f, 0.0f), new Tuple<>(0.042f, 0.0f), new Tuple<>(0.052f, 0.108f), new Tuple<>(0.127f, 0.046f), new Tuple<>(0.177f, 0.042f), new Tuple<>(0.253f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.014f, 0.001f), new Tuple<>(0.023f, 0.0f), new Tuple<>(0.043f, 0.0f), new Tuple<>(0.052f, 0.083f), new Tuple<>(0.11f, 0.017f), new Tuple<>(0.199f, 0.018f), new Tuple<>(0.242f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.005f, 0.0f), new Tuple<>(0.02f, 0.002f), new Tuple<>(0.021f, 0.003f), new Tuple<>(0.027f, 0.0f), new Tuple<>(0.042f, 0.0f), new Tuple<>(0.055f, 0.127f), new Tuple<>(0.132f, 0.073f), new Tuple<>(0.163f, 0.071f), new Tuple<>(0.255f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.005f, 0.0f), new Tuple<>(0.023f, 0.001f), new Tuple<>(0.03f, 0.0f), new Tuple<>(0.047f, 0.0f), new Tuple<>(0.054f, 0.032f), new Tuple<>(0.092f, 0.017f), new Tuple<>(0.232f, 0.007f), new Tuple<>(0.247f, 0.0f), new Tuple<>(0.33f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.023f, 0.001f), new Tuple<>(0.028f, 0.0f), new Tuple<>(0.039f, 0.0f), new Tuple<>(0.059f, 0.044f), new Tuple<>(0.122f, 0.026f), new Tuple<>(0.153f, 0.026f), new Tuple<>(0.262f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.01f, 0.002f), new Tuple<>(0.021f, 0.0f), new Tuple<>(0.046f, 0.0f), new Tuple<>(0.052f, 0.053f), new Tuple<>(0.158f, 0.009f), new Tuple<>(0.206f, 0.028f), new Tuple<>(0.255f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.016f, 0.001f), new Tuple<>(0.029f, 0.0f), new Tuple<>(0.041f, 0.0f), new Tuple<>(0.046f, 0.024f), new Tuple<>(0.052f, 0.008f), new Tuple<>(0.077f, 0.057f), new Tuple<>(0.192f, 0.008f), new Tuple<>(0.25f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.016f, 0.0f), new Tuple<>(0.02f, 0.0f), new Tuple<>(0.048f, 0.0f), new Tuple<>(0.056f, 0.038f), new Tuple<>(0.11f, 0.003f), new Tuple<>(0.188f, 0.014f), new Tuple<>(0.228f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.005f, 0.0f), new Tuple<>(0.023f, 0.001f), new Tuple<>(0.028f, 0.0f), new Tuple<>(0.05f, 0.0f), new Tuple<>(0.077f, 0.014f), new Tuple<>(0.202f, 0.001f), new Tuple<>(0.219f, 0.002f), new Tuple<>(0.247f, 0.0f), new Tuple<>(0.33f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.014f, 0.0f), new Tuple<>(0.024f, 0.0f), new Tuple<>(0.038f, 0.0f), new Tuple<>(0.064f, 0.012f), new Tuple<>(0.104f, 0.004f), new Tuple<>(0.145f, 0.004f), new Tuple<>(0.215f, 0.001f), new Tuple<>(0.238f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.058f, 0.0f), new Tuple<>(0.095f, 0.012f), new Tuple<>(0.136f, 0.013f), new Tuple<>(0.201f, 0.001f), new Tuple<>(0.22f, 0.003f), new Tuple<>(0.233f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.05f, 0.0f), new Tuple<>(0.061f, 0.005f), new Tuple<>(0.1f, 0.0f), new Tuple<>(0.141f, 0.004f), new Tuple<>(0.185f, 0.002f), new Tuple<>(0.208f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.058f, 0.0f), new Tuple<>(0.063f, 0.001f), new Tuple<>(0.085f, 0.0f), new Tuple<>(0.14f, 0.001f), new Tuple<>(0.171f, 0.0f), new Tuple<>(0.183f, 0.0f), new Tuple<>(0.33f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.05f, 0.0f), new Tuple<>(0.064f, 0.005f), new Tuple<>(0.103f, 0.001f), new Tuple<>(0.139f, 0.001f), new Tuple<>(0.177f, 0.002f), new Tuple<>(0.219f, 0.0f), new Tuple<>(0.33f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.07f, 0.0f), new Tuple<>(0.079f, 0.003f), new Tuple<>(0.113f, 0.003f), new Tuple<>(0.141f, 0.001f), new Tuple<>(0.206f, 0.001f), new Tuple<>(0.219f, 0.0f), new Tuple<>(0.33f, 0.0f)));


		int totalHarmonics = envelopes.size();

		for (int i = 0; i < totalHarmonics; i++) {
			int frequencyMultiple = i+1;

			List<Tuple<Float, Float>> envelopeData = envelopes.get(i);
			envelopeData = envelopeData.stream().map(tuple -> new Tuple<>(tuple.e()*durationMultiplier, tuple.f())).toList();

			Envelope envelope = new ArbitraryEnvelope(envelopeData);

			Partial harmonicPartial = new Partial(Synth.sine(), frequencyMultiple, AMPLITUDE_NORMALIZER, envelope, i);
			partials.add(harmonicPartial);
		}

		return partials;
	}


}
