package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;

public class TrumpetSongOrchestrator extends SongOrchestrator {

	public TrumpetSongOrchestrator() {
		super(4);
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
