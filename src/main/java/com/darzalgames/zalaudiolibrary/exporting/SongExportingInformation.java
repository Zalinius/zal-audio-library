package com.darzalgames.zalaudiolibrary.exporting;

import java.util.Map;

import com.darzalgames.zalaudiolibrary.composing.Song;

public class SongExportingInformation {
	private final Song song;
	private final SongOrchestrator orchestrator;
	private final Map<String, String> metadata;

	public SongExportingInformation(Song song, SongOrchestrator orchestrator, Map<String, String> metadata) {
		this.song = song;
		this.orchestrator = orchestrator;
		this.metadata = metadata;
	}

	public Song getSong() {
		return song;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public SongOrchestrator getOrchestrator() {
		return orchestrator;
	}

}
