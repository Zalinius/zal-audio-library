package com.darzalgames.zalaudiolibrary.composing;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

class SongTest {

	@Test
	void addTrack_withIncorrectSongNameInTrack_throwsIllegalArgumentException() {
		Song song = new Song("songName") {
		};

		SequentialTrack track1 = new SequentialTrack("songName", "track1", null, 0);
		SequentialTrack track2 = new SequentialTrack("wrongName", "track2", null, 0);

		assertDoesNotThrow(() -> song.addTrack(track1));
		assertThrows(IllegalArgumentException.class, () -> song.addTrack(track2));
	}

	@Test
	void addTrack_withPreviouslyAddedTrackSharingId_throwsIllegalArgumentException() {
		Song song = new Song("songName") {
		};

		SequentialTrack track1 = new SequentialTrack("songName", "track1", null, 0);
		SequentialTrack track2 = new SequentialTrack("songName", "track1", null, 0);

		assertDoesNotThrow(() -> song.addTrack(track1));
		assertThrows(IllegalArgumentException.class, () -> song.addTrack(track2));
	}

}
