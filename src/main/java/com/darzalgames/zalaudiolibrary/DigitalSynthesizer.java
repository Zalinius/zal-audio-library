package com.darzalgames.zalaudiolibrary;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.DemoAlbum;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;

public class DigitalSynthesizer {

	public static void main(String[] args) {
		// runSong(new BellSong());
		// runSong(new TrumpetSong());

		exportDemoAlbum();

	}

	public static void runSong(Song song) throws LineUnavailableException, InterruptedException {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(song, audioConsumer, 1f, 1f);

		audioPipeline.start();

		Thread.sleep(16000);
		audioPipeline.shutdown();
	}

	public static void exportDemoAlbum() {
		AlbumExportingInformation album = new DemoAlbum();
		SongExporter songExporter = new SongExporter();

		songExporter.export(album);

	}

	public static TwoByteSampleAdapter getJavaAudioConsumer() throws LineUnavailableException {
		AudioFormat af = new AudioFormat(AudioConstants.SAMPLING_RATE, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(af, AudioConstants.SAMPLING_RATE);

		return new TwoByteSampleAdapter(line);
	}

}
