package com.darzalgames.zalaudiolibrary;

public interface AudioConsumer extends AutoCloseable {

	void writeSamples(float[] samples);

}
