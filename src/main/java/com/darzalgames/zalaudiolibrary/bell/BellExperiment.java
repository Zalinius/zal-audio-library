package com.darzalgames.zalaudiolibrary.bell;

import java.util.ArrayList;
import java.util.List;

// An attempt to recreate this sound https://en.wikipedia.org/wiki/File:Additive_synthesis_bell.ogg
public class BellExperiment {


	public static List<Partial> makePartials(){
		List<Partial> partials = new ArrayList<>();

		float[] frequencyMultiples = {1.0f, 1.6766169f, 2.1741292f, 2.2537313f, 2.3830845f, 2.4925373f, 3.3731344f, 4.402985f, 5.437811f, 1.1641791f, 2.7363183f, 6.970149f, 8.40796f, 8.855721f, 9.960199f, 12.184079f, 13.1890545f, 14.373135f, 14.935324f, 16.169155f, 18.40796f};
		float[] wobbleAmplitudes = {0f, 1f, .1f, .1f, .1f, 1, .1f, .2f, .2f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		float[] wobbleFrequencies = {0, 2.8f, 9.2f, 9.5f, 10f, 4.8f, 9f, 8.8f, 7.1f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		float[] amplitudeScales = {-32f, -34.5f, -39.8f, -50.2f, -45.6f, -35.6f, -35.7f, -36.3f, -38.4f, -45,-45,-45,-45,-45,-45,-45,-45,-50,-50,-50,-50};
		float[] decayRate = {.85f, .8f, 1, 1, 1, 1, 1.25f, 1.5f, 1.5f, 4, 3.5f, 4, 4, 4, 4, 4, 5, 3.5f, 5, 6, 6};

		int partialCount = frequencyMultiples.length;

		for (int i = 0; i < partialCount; i++) {
			partials.add(new Partial(frequencyMultiples[i], wobbleAmplitudes[i], wobbleFrequencies[i], amplitudeScales[i], decayRate[i]));
		}

		return partials;
	}


	public static class Partial {
		private final float frequencyMultiplier;
		private final float amplitude; //in decibels, the amplitude of this sound in particular

		private final float amplitudeModulationAmplitude;
		private final float amplitudeModulationFrequency;

		private final float decayRate;

		public Partial(float frequencyMultiplier, float wobbleAmplitude, float wobbleFrequency, float amplitudeScale, float decayRate) {
			this.frequencyMultiplier = frequencyMultiplier;
			amplitudeModulationAmplitude = wobbleAmplitude;
			amplitudeModulationFrequency = wobbleFrequency;
			amplitude = amplitudeScale;
			this.decayRate = decayRate * 1f; //TODO increasing the decay rate can make a cowbell like sound or other pitched percussion
		}



		public float computeSample(float t, float fundamentalFrequency) {
			float baseAmplitude = decibelToAmplitude(amplitude); //compute the base amplitude

			//this is a multiplier, but it only reduces the amplitude, never makes it louder
			//For example, with amplitudeModulationAmplitude==0, the multiplier will always be 1,
			//  and with amplitudeModulationAmplitude==1, the multiplier will oscilate within [-1,1]

			float amplitudeModulationPhase = (float) (2*Math.PI * amplitudeModulationFrequency * t);
			float amplitudeModulation = (float) Math.sin(amplitudeModulationPhase) * amplitudeModulationAmplitude + (1-amplitudeModulationAmplitude);
			//after 1 second
			float envelope;
			if(t < 1f) {
				envelope = (float) (Math.exp(-t * decayRate) * (1 - Math.exp(-t * 1000)));
			}
			else {
				envelope = (float) Math.exp(-t * decayRate);
			}

			float toneComponent = (float) Math.sin(2*Math.PI * t * frequencyMultiplier * fundamentalFrequency);

			float smartSample = baseAmplitude * amplitudeModulation * envelope * toneComponent;

			return 8 * smartSample; //TODO why multiply by 8? is the original just too quiet??
		}




		public float getFrequency() {
			return frequencyMultiplier;
		}



		public float getWobbleAmplitude() {
			return amplitudeModulationAmplitude;
		}



		public float getWobbleFrequency() {
			return amplitudeModulationFrequency;
		}



		public float getAmplitudeScale() {
			return amplitude;
		}



		public float getDecayRate() {
			return decayRate;
		}

	}

	public static float decibelToAmplitude(float decibel) {
		return (float) Math.pow(10, decibel/20);
	}



}
