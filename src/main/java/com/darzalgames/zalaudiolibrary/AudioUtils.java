package com.darzalgames.zalaudiolibrary;

public class AudioUtils {

	private AudioUtils() {}

	public static float decibelToAmplitude(float decibel) {
		return (float) Math.pow(10, decibel/20);
	}

}
