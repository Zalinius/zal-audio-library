package com.darzalgames.zalaudiolibrary.sampling;

import javax.sound.sampled.SourceDataLine;

/**
 * An audio consumer that converts its float sample input into two-byte short samples, for use with the Java Sound API
 */
public class TwoByteSampleAdapter implements AudioConsumer {

	private final SourceDataLine line;

	/**
	 * Constructs an adapter with a SourceDataLine to write adapted samples to
	 * @param line the SourceDataLine to adapt
	 */
	public TwoByteSampleAdapter(SourceDataLine line) {
		this.line = line;
		this.line.start();
	}

	@Override
	public void writeSamples(float[] samples) {
		byte[] shortSamples = new byte[2 * samples.length]; //2 bytes per sample

		for (int i = 0; i < samples.length; i++) {
			float floatSample = samples[i];

			int intSample = (int)(floatSample * 32767);
			shortSamples[2*i] = (byte)(intSample & 0xFF);
			shortSamples[2*i+1] = (byte)((intSample >> 8) & 0xFF);
		}

		line.write(shortSamples, 0, shortSamples.length);
	}

	@Override
	public void close() {
		line.drain();
		line.stop();
		line.close();
	}


}
