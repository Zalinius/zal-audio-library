package com.darzalgames.zalaudiolibrary;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.synth.Synth;

public class DigitalSynthesizer {

	public static final int SAMPLING_RATE = 44100; //hz

	public static void main(String[] args) throws LineUnavailableException {

		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();

		audioConsumer.writeSamples(makePulse(Synth.sine(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.sinePower(2), 400f));
		audioConsumer.writeSamples(makePulse(Synth.sinePower(3), 400f));
		audioConsumer.writeSamples(makePulse(Synth.sinePower(4), 400f));
		audioConsumer.writeSamples(makePulse(Synth.sinePower(5), 400f));
		audioConsumer.writeSamples(makePulse(Synth.zero(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.triangle(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.zero(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.square(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.zero(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.saw(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.zero(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.pulse(0.25f), 400f));
		audioConsumer.writeSamples(makePulse(Synth.zero(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.whiteNoise(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.zero(), 400f));
		audioConsumer.writeSamples(makePulse(Synth.brownianNoise(0.9f), 400f));
		audioConsumer.close();

	}

	public static float[] makePulse(Synth synth, float frequency) {
		return makePulse(synth, frequency, 1f);
	}

	public static float[] makePulse(Synth synth, float frequency, float duration) {
		float[] samples = new float[(int) (SAMPLING_RATE * duration)];

		for (int i = 0; i < samples.length; i++) {
			float t = i/(float)SAMPLING_RATE;
			float x = frequency * t % 1f;

			float sample = synth.f(x);
			samples[i] = sample;
		}

		return samples;
	}

	public static TwoByteSampleAdapter getJavaAudioConsumer() throws LineUnavailableException {
		AudioFormat af = new AudioFormat(SAMPLING_RATE, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(af, SAMPLING_RATE);

		return new TwoByteSampleAdapter(line);
	}

}
