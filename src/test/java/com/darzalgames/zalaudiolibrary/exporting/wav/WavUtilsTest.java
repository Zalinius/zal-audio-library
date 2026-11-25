package com.darzalgames.zalaudiolibrary.exporting.wav;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

class WavUtilsTest {

	@Test
	void makeWavMetadata_mapDataHasCorrectKeysAndAreNullTerminatedStrings() throws Exception {
		Map<String, String> metadata = WavUtils.makeWavMetadata("title", "artist name", "album name", 0, 2023, "genre");

		assertEquals(6, metadata.size());

		assertEquals("title\0", metadata.get("INAM"));
		assertEquals("artist name\0", metadata.get("IART"));
		assertEquals("album name\0", metadata.get("IPRD"));
		assertEquals("0\0", metadata.get("IPRT"));
		assertEquals("2023\0", metadata.get("ICRD"));
		assertEquals("genre\0", metadata.get("IGNR"));
	}

	@Test
	void makeBlankWavMetadata_createsCorrectMapWithEmptyValuesAndNullTerminatedStrings() {
		Map<String, String> metadata = WavUtils.makeBlankWavMetadata();

		assertEquals(6, metadata.size());

		assertEquals("\0", metadata.get("INAM"));
		assertEquals("\0", metadata.get("IART"));
		assertEquals("\0", metadata.get("IPRD"));
		assertEquals("\0", metadata.get("IPRT"));
		assertEquals("\0", metadata.get("ICRD"));
		assertEquals("\0", metadata.get("IGNR"));
	}

}
