package com.darzalgames.zalaudiolibrary.composing;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.composing.time.BPSAcceptor;
import com.darzalgames.zalaudiolibrary.composing.tracks.Track;
import com.darzalgames.zalaudiolibrary.composing.validation.CompositionError;
import com.darzalgames.zalaudiolibrary.composing.validation.SongEmptyError;
import com.darzalgames.zalaudiolibrary.effects.sampling.SampleEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

public abstract class Song {

	private final String songName;
	private final float initialBps;
	private final List<Track> tracks;

	private final List<SampleEffect> sampleEffects;

	private BPSAcceptor bpsAcceptor;

	protected Song(String songName) {
		this(songName, 1f);
	}

	protected Song(String songName, float initialBps) {
		this.songName = songName;
		this.initialBps = initialBps;
		tracks = new ArrayList<>();
		sampleEffects = new ArrayList<>();
	}

	public void setBpsAcceptor(BPSAcceptor bpsAcceptor) {
		this.bpsAcceptor = bpsAcceptor;
	}

	public void addTrack(Track track) {
		tracks.add(track);
	}

	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat) {
		List<TimedMusicalInstant> allActiveInstants = new ArrayList<>();

		tracks.forEach(track -> allActiveInstants.addAll(track.getMusicalInstantsActiveThisBeatInclusive(startBeat)));

		return allActiveInstants;
	}

	public void addSampleEffect(SampleEffect sampleEffect) {
		sampleEffects.add(sampleEffect);
	}

	public List<SampleEffect> getSampleEffects() {
		return sampleEffects;
	}

	public List<CompositionError> validate() {
		List<CompositionError> errors = new ArrayList<>();
		if (tracks.isEmpty()) {
			errors.add(new SongEmptyError(this));
		}
		tracks.forEach(track -> errors.addAll(track.validate()));

		return errors;
	}

	public float getInitialBps() {
		return initialBps;
	}

	public String getSongName() {
		return songName;
	}

	// TODO BPS changes seem to create audio artifacts?
	public void changeBPSGradually(float newBPS) {
		bpsAcceptor.setTargetBPS(newBPS, 4f / newBPS);
	}

	public void changeBPSGradually(float newBPS, float transitionTime) {
		bpsAcceptor.setTargetBPS(newBPS, transitionTime);
	}

	public void changeBPSNow(float newBPS) {
		bpsAcceptor.setTargetBPS(newBPS, 0f);
	}

}
