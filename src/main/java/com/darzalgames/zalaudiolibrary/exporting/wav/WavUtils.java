package com.darzalgames.zalaudiolibrary.exporting.wav;

import java.util.HashMap;
import java.util.Map;

/**
 * Some little helpers for working with the Wav format
 */
public class WavUtils {

	private WavUtils() {}

	/**
	 * Creates the basic music wav metadata for a track
	 * @param title  The track's title
	 * @param artist The track's artist
	 * @param album  The albums's artist
	 * @param year   The track's release year
	 * @param genre  The track's genre
	 * @return A wav metadata map for the track
	 */
	public static Map<String, String> makeWavMetadata(String title, String artist, String album, int track, int year, String genre) {
		Map<String, String> metadata = new HashMap<>();
		metadata.put("INAM", title + "\0");
		metadata.put("IART", artist + "\0");
		metadata.put("IPRD", album + "\0");
		metadata.put("IPRT", track + "\0");
		metadata.put("ICRD", year + "\0");
		metadata.put("IGNR", genre + "\0");

		return metadata;
	}

	/**
	 * Creates a blank wav metadata map, populated with empty strings
	 * @return a blank wav metadata map
	 */
	public static Map<String, String> makeBlankWavMetadata() {
		Map<String, String> metadata = new HashMap<>();
		metadata.put("INAM", "\0");
		metadata.put("IART", "\0");
		metadata.put("IPRD", "\0");
		metadata.put("IPRT", "\0");
		metadata.put("ICRD", "\0");
		metadata.put("IGNR", "\0");

		return metadata;
	}

}
