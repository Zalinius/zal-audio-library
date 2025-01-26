package com.darzalgames.zalaudiolibrary.synth;

import java.util.Random;
import java.util.function.UnaryOperator;


/**
 * A bunch of wave functions that satisfy:
 * <ul>
	<li>Domain [0,1[</li>
	<li>Range [-1, 1]</li>
	<li>Period and frequency of 1 (when relevant)</li>
   </ul>
 */
public class WaveFunctions {

	/**
	 * @return A square wave function, which starts positive
	 */
	public static UnaryOperator<Float> getSquareWaveFunction() {
		return x -> {
			return (x < 0.5f) ? 1f : -1f;
		};
	}

	/**
	 * @param modulation the ratio of the pulse wave which is positive 
	 * @return A Pulse wave function, which starts positive
	 */
	public static UnaryOperator<Float> getPulseWaveFunction(float modulation) {
		if(modulation < 0 || modulation > 1) {
			throw new IllegalArgumentException("modulation must be between 0 and 1: " + modulation);
		}
		return x -> {
			return (x < modulation) ? 1f : -1f;
		};
	}

	/**
	 * @return A sine wave function, which starts at 0 and is increasing
	 */
	public static UnaryOperator<Float> getSinWaveFunction() {
		return x -> {
			return (float) Math.sin(2 * Math.PI * x);
		};
	}

	/**
	 * @return A triangle wave function, which starts at 0 and is increasing
	 */
	public static UnaryOperator<Float> getTriangleWaveFunction() {
		return x -> {
			if (x <= .25f) {
				return 4 * x;
			} else if (x <= .75f) {
				return -4 * x + 2f;
			} else {
				return 4 * x - 4f;
			}
		};
	}

	/**
	 * @return A saw wave function, which starts at 0 and is increasing
	 */
	public static UnaryOperator<Float> getSawtoothWaveFunction() {
		return x -> {
			if (x <= .5f) {
				return 2 * x;
			} else {
				return 2 * x - 2f;
			}
		};
	}

	/**
	 * A saw wave made from adding sine wave harmonics.<br>
	 * This wave avoids the sharp cliffs of sawtooth waves
	 * @param harmonics The integer-multiple harmonics to include in the wave. If 1, will be identical to a normal sine wave<br>
	 * smaller values will resemble a sine wave, and larger values will resemble a saw wave
	 * @return A band-limited saw wave function, which starts at 0 and is increasing
	 */
	public static UnaryOperator<Float> getBandLimitedSawtoothWaveFunction(int harmonics) {
		if (harmonics < 1) {
			throw new IllegalArgumentException("Harmonics must be at least 1: " + harmonics);
		}
		return x -> {
			float y = 0f;

			for (int i = 1; i <= harmonics; i++) {
				y += (float) (Math.sin(i * 2 * Math.PI * (x-0.5f)) / i);
			}
			
			y = y * -2 / (float)Math.PI; //normalize

			return y;
		};
	}

	/**
	 * @return A non-repeating white noise function
	 */
	public static UnaryOperator<Float> getWhiteNoiseFunction() {
		final Random rand = new Random();
		return x -> {
			return rand.nextFloat(-1f, 1f);
		};
	}

	/**
	 * @return A non-repeating Brownian noise function
	 * @param continuity : how much of the previously generated value should limit the next value.<br>
	 * Higher is smoother and less random. Min 0, Max 1.<br>
	 * The maximum absolute difference between sequential values will be 2*continuity
	 */
	public static UnaryOperator<Float> getBrownianNoiseFunction(float continuity) {
		if(continuity < 0f || continuity > 1f) {
			throw new IllegalArgumentException("continuity must be between 0 and 1: " + continuity);
		}
		return new Brownian(continuity);
	}

	/**
	 * A Brownian noise function, a smoother kind of noise.<br>
	 * A noise function which limits the deviation of a random value from the last generated value.<br>
	 * In practice, this makes the noise sound lower-pitched.
	 */
	private static class Brownian implements UnaryOperator<Float> {
		private final Random rand;
		private float lastOut;
		private float continuity;

		/**
		 * Creates a brownian noise function
		 * @param continuity : how much of the previously generated value should limit the next value.<br>
		 * Higher is smoother and less random. Min 0, Max 1.<br>
		 * A value of 0 will be equivalent to white noise, a value of 1 will be an unchanging value.
		 */
		public Brownian(float continuity) {
			this.rand = new Random();
			this.lastOut = 0f;
			this.continuity = continuity;
		}

		@Override
		public Float apply(Float t) {
			float white = rand.nextFloat(-1, 1);
			float output = (continuity*lastOut + ((1-continuity) * white));
			lastOut = (float) output;
			return lastOut;

		}
	}

	/**
	 * @return A flatline wave
	 */
	public static UnaryOperator<Float> getNullWaveFunction() {
		return x -> 0f;
	}

	/**
	 * @param power The power to raise the sine wave to.<br>
	 * Even powers still creates negative values for x within [0.5,1[
	 * @return A sine wave function raised to a power. It starts at 0 and is increasing
	 */
	public static UnaryOperator<Float> getSinPowerWaveFunction(int power) {
		UnaryOperator<Float> sin = getSinWaveFunction();
		return x -> {
			float sinValue = sin.apply(x);

			if (sinValue > 0f) {
				return (float) Math.pow(sinValue, power);
			} else {
				return (float) -Math.pow(Math.abs(sinValue), power);
			}

		};

	}
	
	/**
	 * @param synth the original Synth
	 * @param overtoneRatio the relative amplitude of the overtone
	 * @return Creates a function with an overtone at double frequency, of a specified amplitude
	 */
	public static UnaryOperator<Float> getOvertoneFunction(Synth synth, float overtoneRatio) {
		return x -> {
			float value = synth.f(x);
			float overtone = synth.f((2f * x)%1f);
			
			return (value + overtoneRatio*overtone) / (1+overtoneRatio);
		};

	}

}
