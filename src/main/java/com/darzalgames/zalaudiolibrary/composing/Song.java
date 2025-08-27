package com.darzalgames.zalaudiolibrary.composing;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.effects.sampling.SampleEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

public abstract class Song {

	private final String songName;
	private final float initialBps;
	private final List<Track> tracks;

	private final List<SampleEffect> sampleEffects;

	public Song(String songName) {
		this(songName, 1f);
	}

	public Song(String songName, float initialBps) {
		this.songName = songName;
		this.initialBps = initialBps;
		tracks = new ArrayList<>();
		sampleEffects = new ArrayList<>();
	}

	public Track createTrack(String trackName, Instrument instrument) {
		return createTrack(trackName, instrument, 1f);
	}

	public Track createTrack(String trackName, Instrument instrument, float amplitude) {
		Track track = new Track(songName, trackName, instrument, amplitude);
		tracks.add(track);
		return track;
	}

	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat){
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

	public boolean isValid() {
		return tracks.stream().allMatch(Track::isValid);
	}

	public float getInitialBps() {
		return initialBps;
	}

	public String getSongName() {
		return songName;
	}

}
