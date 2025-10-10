package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.demosongs.sbig2025.TestLoopSoundArtifactSong;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;

public class DemoAlbum extends AlbumExportingInformation {

	public DemoAlbum() {
		super("Z.A.L. demo", 2025);

		BellSong bellSong = new BellSong();
		addSong(bellSong, new BellSongCreepyOrchestrator(bellSong));

		TrumpetSong trumpetSong = new TrumpetSong();
		addSong(trumpetSong, new TrumpetSongOrchestrator());

		addSong(new TestLoopSoundArtifactSong(), new SimpleOrchestrator(1));
	}

	private static class SimpleOrchestrator extends SongOrchestrator {

		private final int measures;

		public SimpleOrchestrator(int measures) {
			super(4);
			if (measures <= 0) {
				throw new IllegalArgumentException("measures must be positive: " + measures);
			}
			this.measures = measures;
		}

		@Override
		public void orchestrateSong() {
			processMeasures(measures);
		}

	}

}
