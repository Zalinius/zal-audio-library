package com.darzalgames.zalaudiolibrary;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;

public class DigitalSynthesizer {

	public static void main(String[] args) throws LineUnavailableException, InterruptedException {

		Song testsong = new TestSong();
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();

		AudioPipeline audioPipeline = new AudioPipeline(testsong, audioConsumer, 1f, 1f);

		audioPipeline.start();

		Thread.sleep(10000);
		audioPipeline.shutdown();

	}


	public static TwoByteSampleAdapter getJavaAudioConsumer() throws LineUnavailableException {
		AudioFormat af = new AudioFormat(AudioConstants.SAMPLING_RATE, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(af, AudioConstants.SAMPLING_RATE);

		return new TwoByteSampleAdapter(line);
	}

}
