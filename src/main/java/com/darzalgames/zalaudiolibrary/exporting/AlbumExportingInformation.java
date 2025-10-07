package com.darzalgames.zalaudiolibrary.exporting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.exporting.wav.WavUtils;

public class AlbumExportingInformation {

	public static final String VGM_GENRE = "Video game music";
	public static final String DARZAL_ARTIST = "DarZal Games";

	private final String artist;
	private final String albumTitle;
	private final int year;
	private final String genre;

	private final List<SongExportingInformation> songs;

	public AlbumExportingInformation(String albumTitle, int year) {
		artist = DARZAL_ARTIST;
		this.albumTitle = albumTitle;
		this.year = year;
		genre = VGM_GENRE;
		songs = new ArrayList<>();
	}

	public void addSong(Song song, SongOrchestrator songOrchestrator) {
		int songNumber = songs.size() + 1;
		Map<String, String> songMetadata = WavUtils.makeWavMetadata(song.getSongName(), artist, albumTitle, songNumber, year, genre);
		SongExportingInformation exportingInformation = new SongExportingInformation(song, songOrchestrator, songMetadata);
		songs.add(exportingInformation);
	}

	public List<SongExportingInformation> getSongs() {
		return songs;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	@Override
	public String toString() {
		return albumTitle + " - " + artist + ": " + songs.size() + " tracks";
	}

}
