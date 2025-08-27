package com.darzalgames.zalaudiolibrary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.bell.BellExperiment;
import com.darzalgames.zalaudiolibrary.bell.BellExperiment.Partial;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;

public class DigitalSynthesizer {

	public static void main(String[] args) throws Exception {

		//		runSong(new TestSong());

		ringBells();


	}

	public static void runSong(Song song) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(song, audioConsumer, 1f, 1f);

		audioPipeline.start();

		Thread.sleep(20000);
		audioPipeline.shutdown();
	}

	public static void ringBells() throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		int duration = 3;
		float[] bellTest = new float[duration * AudioConstants.SAMPLING_RATE];

		List<Pitch> pitches = Arrays.asList(Pitch.C3, Pitch.E3, Pitch.G3, Pitch.C4, Pitch.C4, Pitch.E4, Pitch.G4, Pitch.C5, Pitch.C5, Pitch.E5, Pitch.G5, Pitch.C6);

		List<Partial> partials = BellExperiment.makePartials();

		for (Iterator<Pitch> pitchIt = pitches.iterator(); pitchIt.hasNext();) {
			Pitch pitch = pitchIt.next();


			float max = 0;
			for (int i = 0; i < bellTest.length; i++) {
				bellTest[i] = 0;
				float t = i / (float) AudioConstants.SAMPLING_RATE;
				for (Iterator<Partial> it = partials.iterator(); it.hasNext();) {
					Partial partial = it.next();

					//advance wobble

					bellTest[i] += partial.computeSample(t, pitch.getFrequency());
					max = Math.max(max, Math.abs(bellTest[i]));

				}

			}

			System.out.println("max amplitude: " + max);
			audioConsumer.writeSamples(bellTest);
		}
	}

	public static TwoByteSampleAdapter getJavaAudioConsumer() throws LineUnavailableException {
		AudioFormat af = new AudioFormat(AudioConstants.SAMPLING_RATE, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(af, AudioConstants.SAMPLING_RATE);

		return new TwoByteSampleAdapter(line);
	}

}
