package com.darzalgames.zalaudiolibrary;

/**
 * Some constants used throughout the audio processing
 */
public class AudioConstants {

	private AudioConstants() {}

	/**
	 * The standard number of audio samples (data points) in one second of audio <br>
	 * Conveniently, it has many integer factors: 44100 = 2^2 x 3^2 x 5^2 x 7^2 <br>
	 * Notably, it is divisible by 10, 60 and 100
	 */
	public static final int SAMPLING_RATE = 44100; //hz
	public static final float SAMPLE_DURATION = 1 / (float)SAMPLING_RATE;

}
