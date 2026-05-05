package com.darzalgames.zalaudiolibrary;

import java.util.Collection;

import javax.sound.sampled.*;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.rainbow.RainbowSoundEffects;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;

public class DigitalSynthesizer {

	public static void main(String[] args) throws Exception {
//		playSong(new BellSong());
//		playSong(new TrumpetSong());
//		playSong(new A_ThemeSong());
//		playSong(new ManagersVacationSong());
//		playSong(new ScratchPadSong());
//
//		playSong(new SpringSong());
//		playSong(new SummerSong());
//		playSong(new AutumnSong());
//		playSong(new WinterSong());
//		playSong(new RainbowSong());
//		exportAlbum(new RainbowSeedAlbum());
		playSoundEffects(RainbowSoundEffects.soundEffects());

//		Collection<SoundEffect> soundEffects = RainbowSoundEffects.soundEffects();
//		soundEffects.forEach(sfx -> SoundEffectExporter.exportSound(sfx, "Rainbow Seed Sounds", sfx.getSoundName()));

		// exportAlbum(ScratchPadSong.scratchAlbum());
	}

	public static void playSong(Song song) throws Exception {
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

	public static void playSoundEffects(Collection<SoundEffect> soundEffects) {
		soundEffects.forEach(sfx -> {
			try {
				playSoundEffect(sfx);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
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
