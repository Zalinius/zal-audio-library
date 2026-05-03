package com.darzalgames.zalaudiolibrary;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.rainbow.RainbowSeedAlbum;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;

public class DigitalSynthesizer {

	public static void main(String[] args) throws Exception {
//		runSong(new BellSong());
//		runSong(new TrumpetSong());
//		runSong(new A_ThemeSong());
//		runSong(new ManagersVacationSong());
//		runSong(new ScratchPadSong());

//		runSong(new SpringSong());
//		runSong(new SummerSong());
//		runSong(new AutumnSong());
//		runSong(new WinterSong());
//		runSong(new RainbowSong());
		exportAlbum(new RainbowSeedAlbum());
//		RainbowSoundEffects.soundEffects().forEach(arg0 -> {
//			try {
//				playSoundEffect(arg0);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});

//		Collection<SoundEffect> soundEffects = RainbowSoundEffects.soundEffects();
//		soundEffects.forEach(sfx -> SoundEffectExporter.exportSound(sfx, "Rainbow Seed Sounds", sfx.getSoundName()));

		// exportAlbum(ScratchPadSong.scratchAlbum());
	}

	public static void runSong(Song song) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
		audioPipeline.requestChangeSong(song);

		System.out.println("Playing \"" + song.getSongName() + "\"");

		audioPipeline.start();
		Thread.sleep(Long.MAX_VALUE);
		audioPipeline.shutdown();
	}

	public static void exportAlbum(AlbumExportingInformation album) {
		SongExporter songExporter = new SongExporter();
		songExporter.export(album);
	}

	public static void playSoundEffect(SoundEffect soundEffect) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
		audioPipeline.requestChangeSong(new BlankSong());

		System.out.println("Playing \"" + soundEffect.getSoundName() + "\"");

		audioPipeline.start();
		audioPipeline.requestSoundEffect(soundEffect);
		Thread.sleep((long) Math.ceil(soundEffect.duration() * 1000));
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
