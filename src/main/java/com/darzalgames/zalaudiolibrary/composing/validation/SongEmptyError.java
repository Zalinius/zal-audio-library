package com.darzalgames.zalaudiolibrary.composing.validation;

import com.darzalgames.zalaudiolibrary.composing.Song;

public class SongEmptyError implements CompositionError {

	private final Song emptySong;

	public SongEmptyError(Song emptySong) {
		this.emptySong = emptySong;
	}

	@Override
	public String getError() {
		return "Song " + emptySong.getSongName() + " is empty";
	}

}
