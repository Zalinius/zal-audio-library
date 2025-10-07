package com.darzalgames.zalaudiolibrary.synth.complex;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArbitraryEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.ArbitrarySustainedEnvelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

/**
 * Objects of this class are immutable
 */
public class TrumpetComplexSynth implements ComplexSynth {

	private static final float AMPLITUDE_NORMALIZER = 0.84f;

	private final float harmonics;
	private final boolean sustained;

	/**
	 * Constructs a Trumpet synth with full harmonics and sustained notes
	 */
	public TrumpetComplexSynth() {
		this(1f, true);
	}

	/**
	 * Constructs a Trumpet synth with a fraction of the harmonics
	 * @param harmonics what percentage of the harmonics should be included.<br>
	 *                  1.0 gives a full trumpet, around 0.5f gives a muted trumpet, and further approaching 0 will give a sine synth
	 * @param sustained True if the trumpet should have sustain, false if it should be percussive
	 */
	public TrumpetComplexSynth(float harmonics, boolean sustained) {
		this.harmonics = harmonics;
		this.sustained = sustained;
	}

	@Override
	public List<Partial> makePartials() {
		List<Partial> partials = new ArrayList<>();

		List<List<Tuple<Float, Float>>> envelopes = new ArrayList<>();
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.02f, 0.305f), new Tuple<>(0.036f, 0.338f), new Tuple<>(0.141f, 0.288f), new Tuple<>(0.237f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.025f, 0.317f), new Tuple<>(0.039f, 0.361f), new Tuple<>(0.123f, 0.295f), new Tuple<>(0.222f, 0.00f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.019f, 0.1f), new Tuple<>(0.034f, 0.369f), new Tuple<>(0.111f, 0.342f), new Tuple<>(0.207f, 0.00f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.024f, 0.113f), new Tuple<>(0.029f, 0.257f), new Tuple<>(0.118f, 0.231f), new Tuple<>(0.187f, 0.035f), new Tuple<>(0.235f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.027f, 0.052f), new Tuple<>(0.034f, 0.13f), new Tuple<>(0.11f, 0.126f), new Tuple<>(0.191f, 0.013f), new Tuple<>(0.234f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.046f, 0.083f), new Tuple<>(0.064f, 0.1f), new Tuple<>(0.1f, 0.1f), new Tuple<>(0.189f, 0.011f), new Tuple<>(0.221f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.037f, 0.039f), new Tuple<>(0.045f, 0.077f), new Tuple<>(0.11f, 0.079f), new Tuple<>(0.176f, 0.011f), new Tuple<>(0.205f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.028f, 0.017f), new Tuple<>(0.043f, 0.071f), new Tuple<>(0.109f, 0.066f), new Tuple<>(0.172f, 0.008f), new Tuple<>(0.201f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.029f, 0.016f), new Tuple<>(0.043f, 0.053f), new Tuple<>(0.054f, 0.066f), new Tuple<>(0.105f, 0.064f), new Tuple<>(0.165f, 0.007f), new Tuple<>(0.191f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.027f, 0.006f), new Tuple<>(0.041f, 0.025f), new Tuple<>(0.056f, 0.029f), new Tuple<>(0.072f, 0.022f), new Tuple<>(0.095f, 0.024f), new Tuple<>(0.18f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.037f, 0.006f), new Tuple<>(0.055f, 0.025f), new Tuple<>(0.088f, 0.029f), new Tuple<>(0.114f, 0.028f), new Tuple<>(0.164f, 0.003f), new Tuple<>(0.186f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.007f, 0.0f), new Tuple<>(0.039f, 0.003f), new Tuple<>(0.043f, 0.008f), new Tuple<>(0.088f, 0.011f), new Tuple<>(0.118f, 0.009f), new Tuple<>(0.138f, 0.003f), new Tuple<>(0.165f, 0.0f)));

		List<Integer> sustainIndices = List.of(3, 4, 4, 4, 3, 3, 3, 4, 5, 5, 5, 5);

		int totalHarmonics = envelopes.size();
		float shortener = 1f; // TODO incorporate in constructor?

		for (int i = 0; i < totalHarmonics * harmonics; i++) {
			int frequencyMultiple = i + 1;

			List<Tuple<Float, Float>> envelopeData = envelopes.get(i);
			envelopeData = envelopeData.stream().map(tuple -> new Tuple<>(tuple.e() * shortener, tuple.f())).toList();

			Envelope envelope;
			if (sustained) {
				envelope = new ArbitrarySustainedEnvelope(envelopeData, sustainIndices.get(i));
			} else {
				envelope = new ArbitraryEnvelope(envelopeData);
			}

			Partial harmonicPartial = new Partial(Synth.sine(), frequencyMultiple, AMPLITUDE_NORMALIZER, envelope, i);
			partials.add(harmonicPartial);
		}

		return partials;
	}

}
