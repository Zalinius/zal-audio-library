package com.darzalgames.zalaudiolibrary.composing.validation;

import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class TrackFractionalError implements CompositionError {

	private final SequentialTrack fractionalTrack;

	public TrackFractionalError(SequentialTrack fractionalTrack) {
		this.fractionalTrack = fractionalTrack;
	}

	@Override
	public String getError() {
		return "Track " + fractionalTrack.getTrackName() + " has fractional length: " + fractionalTrack.lengthInBeats();
	}

}
