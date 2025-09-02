package com.darzalgames.zalaudiolibrary.exporting;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.demosongs.BellSong;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavEncoderOutputStream;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;

public class SongExporter {

	public void export(AlbumExportingInformation album) {
		List<SongExportingInformation> songs = album.getSongs();

		System.out.println("Exporting album (" + songs.size() + " songs)");
		for (Iterator<SongExportingInformation> it = songs.iterator(); it.hasNext();) {
			SongExportingInformation songInfo = it.next();
			SongOrchestrator orchestrator = songInfo.getOrchestrator();
			Song song = songInfo.getSong();

			System.out.print("Exporting: " + song.getSongName() + " . . . ");

			try (WavEncoderOutputStream wavEncoderOutputStream = new WavEncoderOutputStream(new FileOutputStream(song.getSongName() + ".wav"), songInfo.getMetadata())){
				AudioPipeline audioPipeline = new AudioPipeline(song, wavEncoderOutputStream, 1f, 0f);
				orchestrator.setAudioPipeline(audioPipeline);
				orchestrator.orchestrateSong();
				wavEncoderOutputStream.writeWavToOutputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("done!");

		}
	}

	public static void main(String[] args) {
		AlbumExportingInformation album = new AlbumExportingInformation("Test Album", 2025);
		BellSong bellSong = new BellSong();

		SongOrchestrator bellOrchestrator = new SongOrchestrator(4) {

			@Override
			public void orchestrateSong() {
				processMeasures(2);

				bellSong.backwards1();
				processMeasures(2);

				bellSong.backwards1();
				bellSong.backwards2();
				processMeasures(2);

				bellSong.backwards1();
				bellSong.backwards2();
				processMeasures(2);

				bellSong.backwards1();
				processMeasures(2);

				bellSong.makeSquare();
				processMeasures(1);
			}
		};

		album.addSong(bellSong, bellOrchestrator);

		SongExporter songExporter = new SongExporter();

		songExporter.export(album);
		System.out.println("done");

	}



}
