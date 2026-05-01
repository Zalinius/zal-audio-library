package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;

public class RainbowSeedAlbum extends AlbumExportingInformation {

	public RainbowSeedAlbum() {
		super("Rainbow Seed OST", 2026);

		int songLengthInMeasures = 12;

		SongOrchestrator standardOrchestrator = new SongOrchestrator(4) {
			@Override
			public void orchestrateSong() {
				processMeasures(songLengthInMeasures);
			}
		};

		addSong(new WinterSong(), standardOrchestrator);
		addSong(new SpringSong(), standardOrchestrator);
	}

}
