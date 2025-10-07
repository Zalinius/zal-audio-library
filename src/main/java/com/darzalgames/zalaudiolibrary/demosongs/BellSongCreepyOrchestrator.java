package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;

public class BellSongCreepyOrchestrator extends SongOrchestrator {

	private final BellSong bellSong;

	public BellSongCreepyOrchestrator(BellSong bellSong) {
		super(4);
		this.bellSong = bellSong;
	}

	@Override
	public void orchestrateSong() {
		processMeasures(2);

		bellSong.backwards1();
		bellSong.changeBPSGradually(1.8f, 1f);
		processMeasures(2);

		bellSong.backwards1();
		bellSong.backwards2();
		bellSong.changeBPSGradually(1.6f, 1f);
		processMeasures(2);

		bellSong.backwards1();
		bellSong.backwards2();
		bellSong.changeBPSGradually(1.4f, 1f);
		processMeasures(2);

		bellSong.backwards1();
		bellSong.changeBPSGradually(1.2f, 1f);
		processMeasures(2);

		bellSong.changeBPSGradually(1.f, 1f);
		bellSong.makeSquare();
		processMeasures(1);
	}

}
