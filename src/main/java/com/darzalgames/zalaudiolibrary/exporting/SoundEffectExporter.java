package com.darzalgames.zalaudiolibrary.exporting;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.darzalcommon.functional.Do;
import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.BlankSong;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavEncoderOutputStream;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavUtils;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;

public class SoundEffectExporter {

	public static void exportSound(SoundEffect soundEffect, String directory, String name) {
		System.out.print("Exporting: " + name + " . . . ");

		String relativeExportPath = directory + File.separator + name + ".wav";
		File file = new File(directory);
		file.mkdir();

		try (WavEncoderOutputStream wavEncoderOutputStream = new WavEncoderOutputStream(new FileOutputStream(relativeExportPath), soundEffectMetaData(name, directory))) {
			AudioPipeline audioPipeline = new AudioPipeline(wavEncoderOutputStream, 0f, 1f);
			audioPipeline.requestChangeSong(new BlankSong());
			audioPipeline.requestSoundEffect(soundEffect);

			int steps = (int) Math.ceil(soundEffect.duration() / AudioConstants.STEP_DURATION_IN_SECONDS);

			Do.xTimes(steps, audioPipeline::processMusicStep);

			wavEncoderOutputStream.writeWavToOutputStream();
			Tuple<List<String>, Float> maxPeak = audioPipeline.getMaxPeak();
			if (maxPeak.f() > 1f) {
				System.out.print("PEAKING: " + maxPeak.f() + " at " + maxPeak.e());
			} else {
				System.out.print("(max peak: " + maxPeak.f() + ") ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("done!");

	}

	private static Map<String, String> soundEffectMetaData(String name, String directory) {
		return WavUtils.makeWavMetadata(name, "Zalinius", directory, 0, 0, "sfx");
	}
}
