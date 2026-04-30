package com.darzalgames.zalaudiolibrary;

import java.util.List;
import java.util.function.UnaryOperator;

import javax.sound.sampled.*;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.demosongs.ScratchPadSong;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class DigitalSynthesizer {

	public static void main(String[] args) throws Exception {
//		runSong(new BellSong());
//		runSong(new TrumpetSong());
//		runSong(new A_ThemeSong());
//		runSong(new TestLoopSoundArtifactSong());
//		runSong(new ManagersVacationSong());

		runSong(new ScratchPadSong());
//		exportAlbum(ScratchPadSong.scratchAlbum());
//		exportAlbum(dumbAlbum());
	}

	private static class TestSong extends Song {

		private final SequentialTrack mainTrack;

		public TestSong() {
			super("Test Step Boundary", 1f);

			mainTrack = new SequentialTrack(getSongName(), "main", new Instrument(SynthFactory.sine(), new ConstantEnvelope(1f)), 0.5f);
			addTrack(mainTrack);

			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);

			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
			mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		}
	}

	public static void runSong(Song song) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
		audioPipeline.changeSong(song);

		System.out.println("Playing \"" + song.getSongName() + "\"");

		audioPipeline.start();
//		audioConsumer.writeSamples(phaseModulation());
//		Map<String, String> metadata = WavUtils.makeWavMetadata("FM", "zal", "test", 1, 2025, "test");
//		WavEncoderOutputStream out1 = new WavEncoderOutputStream(new FileOutputStream("fmTest.wav"), metadata);
//		out1.writeSamples(phaseModulationArray());
//		out1.writeWavToOutputStream();
//		out1.close();
//
//		metadata = WavUtils.makeWavMetadata("Sine", "zal", "test", 2, 2025, "test");
//		out1 = new WavEncoderOutputStream(new FileOutputStream("sineTest.wav"), metadata);
//		out1.writeSamples(sineArray());
//		out1.writeWavToOutputStream();
//		out1.close();
		Thread.sleep(Long.MAX_VALUE);
		audioPipeline.shutdown();
	}

	public static SimpleSound bwipSound(int i) {
		float duration = 1f;
		Pitch basePitch = List.of(Pitch.C4, Pitch.D4, Pitch.E4, Pitch.F4, Pitch.G4, Pitch.A4, Pitch.B4, Pitch.C5).get(i % 8).octaveDown();
		float modulatorFrequency = basePitch.getFrequency() * 1.5f;
		float modulatorAmplitude = 0.05f;

		UnaryOperator<Float> modulator = t -> 1 + (modulatorAmplitude * (float) Math.sin(modulatorFrequency * t * Math.PI * 2));
//		return new SimpleSound(Synth.sine(), basePitch, modulator, duration, ArEnvelope.quadratic(0.05f, 0.5f), 0.5f, "bwip" + i);
		return new SimpleSound(SynthFactory.bandLimitedSawTooth(1), Pitch.A3, t -> 1f, 10, ArEnvelope.linear(0.01f, 0.1f), 0.5f, "bwip" + i);
	}

	public static float[] phaseModulationArray() {
		float carrierAmplitude = 0.5f;
		float fundamentalFrequency = 100f;
		float duration = 1f;

		// These two values define the timbre of fm synthesis
		float modulationIndex = (float) Math.PI; // ratio of modulation amplitude over modulation frequency
		Fraction frequencyRatio = new Fraction(99, 100); // ratio of carrier frequency over modulation frequency

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

	public static float[] sineArray() {
		float carrierAmplitude = 0.5f;
		float fundamentalFrequency = 100f;
		float duration = 1f;

		float samplingRate = AudioConstants.SAMPLING_RATE;
		int samples = (int) (duration * samplingRate);
		float[] sampleArray = new float[samples];

		for (int i = 0; i < sampleArray.length; i++) {
			float t = i / samplingRate;

			double value = carrierAmplitude * Math.sin(2 * Math.PI * fundamentalFrequency * t);

			sampleArray[i] = (float) value;
		}

		return sampleArray;

	}

	public static Instrument modulated() {
		Pitch basePitch = Pitch.A3;
		float modulatorFrequency = basePitch.getFrequency() * 1.5f;
		float modulatorAmplitude = 0.01f;

		UnaryOperator<Float> modulator = t -> 1 + (modulatorAmplitude * (float) Math.sin(modulatorFrequency * t * Math.PI * 2));
		return new Instrument(SynthFactory.sine(), ArEnvelope.quadratic(0.05f, 0.25f), modulator);
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
