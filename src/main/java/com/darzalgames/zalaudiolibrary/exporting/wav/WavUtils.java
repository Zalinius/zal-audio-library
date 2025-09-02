package com.darzalgames.zalaudiolibrary.exporting.wav;

import java.util.HashMap;
import java.util.Map;

public class WavUtils {

	public static Map<String, String> makeWavMetadata (String title, String artist, String album, int track, int year, String genre) {
		Map<String, String> metadata = new HashMap<>();
		metadata.put("INAM", title + "\0");
		metadata.put("IART", artist + "\0");
		metadata.put("IPRD", album + "\0");
		metadata.put("IPRT", track + "\0");
		metadata.put("ICRD", year + "\0");
		metadata.put("IGNR", genre + "\0");

		return metadata;
	}

}
