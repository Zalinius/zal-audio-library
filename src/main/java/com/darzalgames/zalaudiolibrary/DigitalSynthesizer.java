package com.darzalgames.zalaudiolibrary;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javax.sound.sampled.*;

import com.darzalgames.darzalcommon.functional.Do;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.ScratchPadSong;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
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

	public static void runSong(Song song) throws LineUnavailableException, InterruptedException {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
		audioPipeline.changeSong(song);

		System.out.println("Playing \"" + song.getSongName() + "\"");

		audioPipeline.getVolumeListener().setMusicVolume(0);
		audioPipeline.start();
		Do.xTimesWithI(100, i -> {
			SimpleSound simpleSound = bwipSound(i);
			Executors.newSingleThreadScheduledExecutor().schedule(
					() -> audioPipeline.playSoundEffectNow(simpleSound), i * 250, TimeUnit.MILLISECONDS
			);
		});
		Thread.sleep(Long.MAX_VALUE);
		audioPipeline.shutdown();
	}

	public static SimpleSound bwipSound(int i) {
		float duration = 1f;
		Pitch basePitch = List.of(Pitch.C4, Pitch.D4, Pitch.E4, Pitch.F4, Pitch.G4, Pitch.A4, Pitch.B4, Pitch.C5).get(i % 8).octaveDown();
		float modulatorFrequency = basePitch.getFrequency() * 1.5f;
		float modulatorAmplitude = 0.05f;

		UnaryOperator<Float> modulator = t -> 1 + (modulatorAmplitude * (float) Math.sin(modulatorFrequency * t * Math.PI * 2));
		return new SimpleSound(Synth.sine(), basePitch, modulator, duration, ArEnvelope.quadratic(0.05f, 0.5f), 0.5f, "bwip" + i);
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
