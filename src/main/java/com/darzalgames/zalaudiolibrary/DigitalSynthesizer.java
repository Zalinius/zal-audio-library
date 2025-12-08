package com.darzalgames.zalaudiolibrary;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
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
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class DigitalSynthesizer {

	public static void main(String[] args) throws Exception {
//		runSong(new BellSong());
//		runSong(new TrumpetSong());
//		runSong(new A_ThemeSong());
//		runSong(new TestLoopSoundArtifactSong());
//		runSong(new ManagersVacationSong());

//		playKickDrumDemo();

		runSong(new ScratchPadSong());
//		exportAlbum(ScratchPadSong.scratchAlbum());

//		exportDemoAlbum();
//		exportSbig2025Album();
	}

	public static void playKickDrumDemo() throws Exception {
		AudioConsumer audio = getJavaAudioConsumer();

		for (int drumIndex = 0; drumIndex != 10; drumIndex++) {
			float desiredDrumDuration = 0.25f;
			Pitch pitch = Pitch.G2;

			Instrument kickDrum = Instrument.kickDrum(desiredDrumDuration);
			float[] sample = new float[(int) (AudioConstants.SAMPLING_RATE * (desiredDrumDuration))];
			for (int i = 0; i < sample.length; i++) {
				float time = i / (float) AudioConstants.SAMPLING_RATE;
				Envelope envelope = kickDrum.envelope();
				float frequency = pitch.getFrequency() * kickDrum.frequencyModulator().apply(time);
				Synth synth = kickDrum.synth();
				float value = synth.f((frequency * time) % 1f);
				value *= envelope.getEnvelope(desiredDrumDuration, time);
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
