package com.darzalgames.zalaudiolibrary.synth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.UnaryOperator;

import com.darzalgames.darzalcommon.math.Fraction;

public class SynthFactory {

	private SynthFactory() {}

	/**
	 * Constructs a sine synth
	 * @return A sine synth
	 */
	public static PeriodicSynth sine() {
		return new PeriodicSynth(WaveFunctions.getSinWaveFunction());
	}

	/**
	 * Constructs a square synth
	 * @return a square synth
	 */
	public static PeriodicSynth square() {
		return new PeriodicSynth(WaveFunctions.getSquareWaveFunction());
	}

	/**
	 * Constructs a sawtooth synth
	 * @return a sawtooth synth
	 */
	public static PeriodicSynth saw() {
		return new PeriodicSynth(WaveFunctions.getSawtoothWaveFunction());
	}

	/**
	 * Constructs a triangle synth
	 * @return a triangle synth
	 */
	public static PeriodicSynth triangle() {
		return new PeriodicSynth(WaveFunctions.getTriangleWaveFunction());
	}

	/**
	 * Constructs a band-limited sawtooth synth, made from a number of sine harmonics
	 * @param harmonics the number of sine harmonics
	 * @return a band-limited sawtooth wave
	 */
	public static PeriodicSynth bandLimitedSawTooth(int harmonics) {
		return new PeriodicSynth(WaveFunctions.getBandLimitedSawtoothWaveFunction(harmonics));
	}

	/**
	 * Constructs a pulse wave, with the positive pulse having the given modulation as it's width
	 * @param modulation the width of the positive side of the pulse
	 * @return a pulse wave
	 */
	public static PeriodicSynth pulse(float modulation) {
		return new PeriodicSynth(WaveFunctions.getPulseWaveFunction(modulation));
	}

	/**
	 * Constructs a sine power wave
	 * @param power the power to which the sine is raised. The higher the power the sharper the wave
	 * @return a sine power wave
	 */
	public static PeriodicSynth sinePower(int power) {
		return new PeriodicSynth(WaveFunctions.getSinPowerWaveFunction(power));
	}

	/**
	 * Constructs a flatter sine synth
	 * @return A flat sine synth
	 */
	public static PeriodicSynth flatSine(int power) {
		return new PeriodicSynth(WaveFunctions.getFlatSinWaveFunction(power));
	}

	public static PeriodicSynth rationalFrequencyModulator(Fraction frequencyRatio, float modulationIndex) {
		float fundamentalFrequency = 1f; // Since it's a synth

		float carrierFrequency = fundamentalFrequency * frequencyRatio.numerator();
		float modulationFrequency = fundamentalFrequency * frequencyRatio.denominator();

		UnaryOperator<Float> fmOperator = t -> ((float) Math.sin(2 * Math.PI * carrierFrequency * t + modulationIndex * Math.sin(2 * Math.PI * modulationFrequency * t)));

		return new PeriodicSynth(fmOperator);
	}

	/**
	 * Constructs a 0-wave
	 * @return a zero wave
	 */
	public static final PeriodicSynth zero() {
		return new PeriodicSynth(WaveFunctions.getNullWaveFunction());
	}

	/**
	 * Constructs a non-repeating white noise synth
	 * @return a white noise synth
	 */
	public static final PeriodicSynth whiteNoise() {
		return new PeriodicSynth(WaveFunctions.getWhiteNoiseFunction());
	}

	/**
	 * Constructs a non-repeating brownian noise synth
	 * @param continuity the bass-iness of the synth between [0,1].<br>
	 *                   Higher values are more bassy, lower values are less so and closer sounding to white noise
	 * @return a brown noise synth
	 */
	public static PeriodicSynth brownianNoise(float continuity) {
		return new PeriodicSynth(WaveFunctions.getBrownianNoiseFunction(continuity));
	}

	/**
	 * Creates a new synth by adding together many others through additive synthesis
	 * @param synths the synths to combine
	 * @return a combined synth
	 */
	public static PeriodicSynth additiveSynthesis(PeriodicSynth... synths) {
		Collection<PeriodicSynth> synthCollection = Arrays.asList(synths);

		return new PeriodicSynth(x -> {
			float y = 0;
			for (Iterator<PeriodicSynth> it = synthCollection.iterator(); it.hasNext();) {
				PeriodicSynth synth = it.next();
				y += synth.f(x);
			}
			return y / synths.length;
		});
	}

	/**
	 * Constructs a new synth from a synth and it's 1st overtone (at double the frequency)
	 * @param synth         the original Synth
	 * @param overtoneRatio the relative amplitude of the overtone to the original wave
	 * @return a overtoned synth
	 */
	public static PeriodicSynth overtone(PeriodicSynth synth, float overtoneRatio) {
		return new PeriodicSynth(WaveFunctions.getOvertoneFunction(synth, overtoneRatio));
	}

}
