package com.darzalgames.zalaudiolibrary.exporting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.zalaudiolibrary.pipeline.zamples.AudioConsumer;

public class MultiOutputStream implements AudioConsumer {

	private final List<AudioConsumer> outputs;

	public MultiOutputStream(AudioConsumer... audioConsumers) {
		outputs = Arrays.asList(audioConsumers);
	}

	@Override
	public void writeSamples(float[] samples) {
		outputs.forEach(output -> output.writeSamples(samples));
	}

	@Override
	public void close() throws Exception {
		for (Iterator<AudioConsumer> it = outputs.iterator(); it.hasNext();) {
			AudioConsumer audioConsumer = it.next();
			audioConsumer.close();
		}
	}

}
