package com.darzalgames.zalaudiolibrary.exporting;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavEncoderOutputStream;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;

public class SongExporter {

	public void export(AlbumExportingInformation album) {
		List<SongExportingInformation> songs = album.getSongs();

		System.out.println("Exporting album " + album.toString());
		for (Iterator<SongExportingInformation> it = songs.iterator(); it.hasNext();) {
			SongExportingInformation songInfo = it.next();
			SongOrchestrator orchestrator = songInfo.getOrchestrator();
			Song song = songInfo.getSong();

			System.out.print("Exporting: " + song.getSongName() + " . . . ");

			String relativeExportPath = album.getAlbumTitle() + File.separator + song.getSongName() + ".wav";
			File file = new File(album.getAlbumTitle());
			file.mkdir();
			try (WavEncoderOutputStream wavEncoderOutputStream = new WavEncoderOutputStream(new FileOutputStream(relativeExportPath), songInfo.getMetadata())) {
				AudioPipeline audioPipeline = new AudioPipeline(song, wavEncoderOutputStream, 1f, 0f);
				orchestrator.setAudioPipeline(audioPipeline);
				orchestrator.orchestrateSong();
				wavEncoderOutputStream.writeWavToOutputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("done!");

		}
		System.out.println("Exporting complete for album " + album.toString());
	}

}
