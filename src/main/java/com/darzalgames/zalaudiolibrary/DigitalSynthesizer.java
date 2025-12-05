package com.darzalgames.zalaudiolibrary;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.DemoAlbum;
import com.darzalgames.zalaudiolibrary.demosongs.ScratchPadSong;
import com.darzalgames.zalaudiolibrary.demosongs.sbig2025.Sbig2025Album;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.AudioConsumer;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;

public class DigitalSynthesizer {

	public static void main(String[] args) throws LineUnavailableException, InterruptedException {
//		runSong(new BellSong());
//		runSong(new TrumpetSong());
//		runSong(new A_ThemeSong());
//		runSong(new TestLoopSoundArtifactSong());
//		runSong(new ManagersVacationSong());

//		playKickDrumDemo();

//		runSong(new ScratchPadSong());
		exportAlbum(ScratchPadSong.scratchAlbum());

//		exportDemoAlbum();
//		exportSbig2025Album();
	}

	public static void playKickDrumDemo() throws LineUnavailableException, InterruptedException {
		AudioConsumer audio = getJavaAudioConsumer();

		for (int drumIndex = 0; drumIndex != 100; drumIndex++) {

			float[] sample = new float[AudioConstants.SAMPLING_RATE];

			for (int i = 0; i < sample.length; i++) {
				float drumDuration = sample.length / (float) AudioConstants.SAMPLING_RATE;
				float time = i / (float) AudioConstants.SAMPLING_RATE;
				Pitch pitch = Pitch.G2;
				float frequency = pitch.getFrequency() * (1 - (time / drumDuration));

				Envelope envelope = ArEnvelope.quadratic(0.01f, drumDuration - 0.01f);

				float value = (float) Math.sin(2 * Math.PI * frequency * time);
				value *= envelope.getEnvelope(1f, time);
				sample[i] = value;
			}

			audio.writeSamples(sample);

		}

		Thread.sleep(100_000);
	}

	public static void runSong(Song song) throws LineUnavailableException, InterruptedException {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(song, audioConsumer, 1f, 1f);

		System.out.println("Playing \"" + song.getSongName() + "\"");

		audioPipeline.start();
		Thread.sleep(Long.MAX_VALUE);
		audioPipeline.shutdown();
	}

	public static void exportAlbum(AlbumExportingInformation album) {
		SongExporter songExporter = new SongExporter();
		songExporter.export(album);
	}

	public static void exportDemoAlbum() {
		exportAlbum(new DemoAlbum());
	}

	public static void exportSbig2025Album() {
		exportAlbum(new Sbig2025Album());
	}

	public static TwoByteSampleAdapter getJavaAudioConsumer() throws LineUnavailableException {
		AudioFormat af = new AudioFormat(AudioConstants.SAMPLING_RATE, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(af, AudioConstants.SAMPLING_RATE);

		return new TwoByteSampleAdapter(line);
	}

}
