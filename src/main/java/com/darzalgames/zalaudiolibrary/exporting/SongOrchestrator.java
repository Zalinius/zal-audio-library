package com.darzalgames.zalaudiolibrary.exporting;

import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;

public abstract class SongOrchestrator {

	private final int beatsPerMeasure;
	private AudioPipeline audioPipeline;

	public SongOrchestrator(int beatsPerMeasure) {
		this.beatsPerMeasure = beatsPerMeasure;
	}

	public void setAudioPipeline(AudioPipeline audioPipeline) {
		this.audioPipeline = audioPipeline;
	}

	public final void processMeasures(int count) {
		processBeats(count * beatsPerMeasure);
	}

	public final void processBeats(int count) {
		int currentBeat = Math.round(audioPipeline.getBeatCounter());
		int targetBeat = currentBeat + count;

		while (audioPipeline.getBeatCounter() < targetBeat) {
			audioPipeline.processMusicStep();
		}
	}

	public abstract void orchestrateSong();

}
