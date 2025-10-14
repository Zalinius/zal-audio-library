package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;

public class ManagersVacationOrchestrator extends SongOrchestrator {

	private final ManagersVacationSong managersVacationSong;

	public ManagersVacationOrchestrator(ManagersVacationSong managersVacationSong) {
		super(4);
		this.managersVacationSong = managersVacationSong;
	}

	@Override
	public void orchestrateSong() {
		processMeasures(4); // intro

		managersVacationSong.onTimeSpeakerChanged(0, null, null);
		// chorus

		managersVacationSong.onTimeSpeakerChanged(1, "AM", null);
		processMeasures(4);
		managersVacationSong.onTimeSpeakerChanged(2, "AM", null);
		processMeasures(4);

		// verse1
		managersVacationSong.onTimeSpeakerChanged(3, "AM", null);
		processMeasures(4);
		managersVacationSong.onTimeSpeakerChanged(4, "AM", null);
		processMeasures(4);

		// chorus
		managersVacationSong.onTimeSpeakerChanged(5, "AM", null);
		processMeasures(4);
		managersVacationSong.onTimeSpeakerChanged(5, "PM", null);
		processMeasures(4);

		// verse2
		managersVacationSong.onTimeSpeakerChanged(6, "AM", null);
		processMeasures(4);
		managersVacationSong.onTimeSpeakerChanged(6, "PM", null);
		processMeasures(4);

		// chorus
		managersVacationSong.onTimeSpeakerChanged(7, "AM", null);
		processMeasures(4);
		managersVacationSong.onTimeSpeakerChanged(7, "MIDNIGHT", null);
		processMeasures(3);
		processBeats(3);

	}

}
