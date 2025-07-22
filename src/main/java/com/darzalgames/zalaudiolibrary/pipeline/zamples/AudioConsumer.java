package com.darzalgames.zalaudiolibrary.pipeline.zamples;

/**
 * An interface representing a class that can consume audio samples in the form of float arrays
 */
public interface AudioConsumer extends AutoCloseable {

	/**
	 * Writes a sample buffer to the consumer
	 * @param samples An array of floats whose values are in [-1, 1]
	 */
	void writeSamples(float[] samples);

}
