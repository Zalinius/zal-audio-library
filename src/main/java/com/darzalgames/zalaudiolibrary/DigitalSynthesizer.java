package com.darzalgames.zalaudiolibrary;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import javax.sound.sampled.*;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.ScratchPadSong;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavEncoderOutputStream;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavUtils;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class DigitalSynthesizer {

	public static void main(String[] args) throws Exception {
//		runSong(new BellSong());
//		runSong(new TrumpetSong());
//		runSong(new A_ThemeSong());
//		runSong(new TestLoopSoundArtifactSong());
//		runSong(new ManagersVacationSong());

		runSong(new ScratchPadSong());
//		exportAlbum(ScratchPadSong.scratchAlbum());
	}

	public static void runSong(Song song) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
//		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
//		audioPipeline.changeSong(song);
//
//		System.out.println("Playing \"" + song.getSongName() + "\"");
//
//		audioPipeline.getVolumeListener().setMusicVolume(0);
//		audioPipeline.start();
//		Do.xTimesWithI(100, i -> {
//			SimpleSound simpleSound = bwipSound(i);
//			Executors.newSingleThreadScheduledExecutor().schedule(
//					() -> audioPipeline.playSoundEffectNow(simpleSound), i * 250, TimeUnit.MILLISECONDS
//			);
//		});
//		audioConsumer.writeSamples(phaseModulation());
		Map<String, String> metadata = WavUtils.makeWavMetadata("FM", "zal", "test", 1, 2025, "test");
		WavEncoderOutputStream out = new WavEncoderOutputStream(new FileOutputStream("test.wav"), metadata);
		out.writeSamples(phaseModulation());
		out.writeWavToOutputStream();
		out.close();
//		Thread.sleep(Long.MAX_VALUE);
//		audioPipeline.shutdown();
	}

	public static SimpleSound bwipSound(int i) {
		float duration = 1f;
		Pitch basePitch = List.of(Pitch.C4, Pitch.D4, Pitch.E4, Pitch.F4, Pitch.G4, Pitch.A4, Pitch.B4, Pitch.C5).get(i % 8).octaveDown();
		float modulatorFrequency = basePitch.getFrequency() * 1.5f;
		float modulatorAmplitude = 0.05f;

		UnaryOperator<Float> modulator = t -> 1 + (modulatorAmplitude * (float) Math.sin(modulatorFrequency * t * Math.PI * 2));
//		return new SimpleSound(Synth.sine(), basePitch, modulator, duration, ArEnvelope.quadratic(0.05f, 0.5f), 0.5f, "bwip" + i);
		return new SimpleSound(Synth.bandLimitedSawTooth(1), Pitch.A3, t -> 1f, 10, ArEnvelope.linear(0.01f, 0.1f), 0.5f, "bwip" + i);
	}

	public static float[] phaseModulation() {
		float carrierAmplitude = 0.5f;
		float modulationIndex = (float) Math.PI;
		float fundamentalFrequency = 440f;
		Fraction frequencyRatio = new Fraction(99, 100);
		float duration = 0.1f;

		float carrierFrequency = fundamentalFrequency * frequencyRatio.numerator();
		float modulationFrequency = fundamentalFrequency * frequencyRatio.denominator();

		float samplingRate = AudioConstants.SAMPLING_RATE;
		int samples = (int) (duration * samplingRate);
		float[] sampleArray = new float[samples];

		for (int i = 0; i < sampleArray.length; i++) {
			float t = i / samplingRate;

			double value = carrierAmplitude * Math.sin(2 * Math.PI * carrierFrequency * t + modulationIndex * Math.sin(2 * Math.PI * modulationFrequency * t));

			sampleArray[i] = (float) value;
		}

		return sampleArray;

	}

	public static Instrument modulated() {
		Pitch basePitch = Pitch.A3;
		float modulatorFrequency = basePitch.getFrequency() * 1.5f;
		float modulatorAmplitude = 0.01f;

		UnaryOperator<Float> modulator = t -> 1 + (modulatorAmplitude * (float) Math.sin(modulatorFrequency * t * Math.PI * 2));
		return new Instrument(Synth.sine(), ArEnvelope.quadratic(0.05f, 0.25f), modulator);
	}

	public static void exportAlbum(AlbumExportingInformation album) {
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
