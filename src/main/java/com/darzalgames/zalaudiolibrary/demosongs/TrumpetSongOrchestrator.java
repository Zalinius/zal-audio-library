package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;

public class TrumpetSongOrchestrator extends SongOrchestrator {

	private final TrumpetSong trumpetSong;

	public TrumpetSongOrchestrator(TrumpetSong trumpetSong) {
		super(4);
		this.trumpetSong = trumpetSong;
	}

	@Override
	public void orchestrateSong() {
		processBeats(1);

		processMeasures(8);

		processMeasures(8);
		processMeasures(1);
		processBeats(3);

	}

}
