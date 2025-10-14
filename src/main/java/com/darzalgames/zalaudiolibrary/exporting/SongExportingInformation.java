package com.darzalgames.zalaudiolibrary.exporting;

import java.util.Map;

import com.darzalgames.zalaudiolibrary.composing.Song;

public class SongExportingInformation {
	private final Song song;
	private final SongOrchestrator orchestrator;
	private final Map<String, String> metadata;
	private final int index;

	public SongExportingInformation(Song song, SongOrchestrator orchestrator, Map<String, String> metadata, int index) {
		this.song = song;
		this.orchestrator = orchestrator;
		this.metadata = metadata;
		this.index = index;
	}

	public Song getSong() {
		return song;
	}

	public String getIndexedName() {
		String indexString;
		if (index < 10) {
			indexString = 0 + "" + index;
		} else {
			indexString = Integer.toString(index);
		}

		return indexString + " " + song.getSongName();
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public SongOrchestrator getOrchestrator() {
		return orchestrator;
	}

}
