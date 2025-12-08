package com.darzalgames.zalaudiolibrary.synth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * A synthetic sound, built on a wave function, with domain [0,1] and range (typically) [-1,1]
 */
public class Synth {

	private final UnaryOperator<Float> waveFunction;

	/**
	 * Constructs a synth from a wave function
	 * @param waveFunction forming the basis of the synth
	 */
	public Synth(UnaryOperator<Float> waveFunction) {
		this.waveFunction = waveFunction;
	}

	/**
	 * Computes the synth value at a given point
	 * @param x the progress through the synth's period, between [0,1]
	 * @return A wave height, between [-1, 1]
	 * @throws IllegalArgumentException if x is not within [0,1]
	 */
	public float f(float x) {
		if (x < 0f || x > 1f) {
			throw new IllegalArgumentException("Argument must be in range [0,1] : " + x);
		}
		return waveFunction.apply(x);
	}

	public UnaryOperator<Float> getWaveFunction() {
		return waveFunction;
	}

	/**
	 * Constructs a sine synth
	 * @return A sine synth
	 */
	public static Synth sine() {
		return new Synth(WaveFunctions.getSinWaveFunction());
	}

	/**
	 * Constructs a square synth
	 * @return a square synth
	 */
	public static Synth square() {
		return new Synth(WaveFunctions.getSquareWaveFunction());
	}

	/**
	 * Constructs a sawtooth synth
	 * @return a sawtooth synth
	 */
	public static Synth saw() {
		return new Synth(WaveFunctions.getSawtoothWaveFunction());
	}

	/**
	 * Constructs a triangle synth
	 * @return a triangle synth
	 */
	public static Synth triangle() {
		return new Synth(WaveFunctions.getTriangleWaveFunction());
	}

	/**
	 * Constructs a band-limited sawtooth synth, made from a number of sine harmonics
	 * @param harmonics the number of sine harmonics
	 * @return a band-limited sawtooth wave
	 */
	public static Synth bandLimitedSawTooth(int harmonics) {
		return new Synth(WaveFunctions.getBandLimitedSawtoothWaveFunction(harmonics));
	}

	/**
	 * Constructs a pulse wave, with the positive pulse having the given modulation as it's width
	 * @param modulation the width of the positive side of the pulse
	 * @return a pulse wave
	 */
	public static Synth pulse(float modulation) {
		return new Synth(WaveFunctions.getPulseWaveFunction(modulation));
	}

	/**
	 * Constructs a sine power wave
	 * @param power the power to which the sine is raised. The higher the power the sharper the wave
	 * @return a sine power wave
	 */
	public static Synth sinePower(int power) {
		return new Synth(WaveFunctions.getSinPowerWaveFunction(power));
	}

	/**
	 * Constructs a flatter sine synth
	 * @return A flat sine synth
	 */
	public static Synth flatSine(int power) {
		return new Synth(WaveFunctions.getFlatSinWaveFunction(power));
	}

	/**
	 * Constructs a 0-wave
	 * @return a zero wave
	 */
	public static final Synth zero() {
		return new Synth(WaveFunctions.getNullWaveFunction());
	}

	/**
	 * Constructs a non-repeating white noise synth
	 * @return a white noise synth
	 */
	public static final Synth whiteNoise() {
		return new Synth(WaveFunctions.getWhiteNoiseFunction());
	}

	/**
	 * Constructs a non-repeating brownian noise synth
	 * @param continuity the bass-iness of the synth between [0,1].<br>
	 *                   Higher values are more bassy, lower values are less so and closer sounding to white noise
	 * @return a brown noise synth
	 */
	public static Synth brownianNoise(float continuity) {
		return new Synth(WaveFunctions.getBrownianNoiseFunction(continuity));
	}

	/**
	 * Creates a new synth by adding together many others through additive synthesis
	 * @param synths the synths to combine
	 * @return a combined synth
	 */
	public static Synth additiveSynthesis(Synth... synths) {
		Collection<Synth> synthCollection = Arrays.asList(synths);

		return new Synth(x -> {
			float y = 0;
			for (Iterator<Synth> it = synthCollection.iterator(); it.hasNext();) {
				Synth synth = it.next();
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
	public static Synth overtone(Synth synth, float overtoneRatio) {
		return new Synth(WaveFunctions.getOvertoneFunction(synth, overtoneRatio));
	}

}
