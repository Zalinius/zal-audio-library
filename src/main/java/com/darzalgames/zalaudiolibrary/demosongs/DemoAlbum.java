package com.darzalgames.zalaudiolibrary.demosongs;

import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;

public class DemoAlbum extends AlbumExportingInformation {

	public DemoAlbum() {
		super("Z.A.L. demo", 2025);

		BellSong bellSong = new BellSong();
		addSong(bellSong, new BellSongCreepyOrchestrator(bellSong));

		TrumpetSong trumpetSong = new TrumpetSong();
		addSong(trumpetSong, new TrumpetSongOrchestrator(trumpetSong));
	}
}
