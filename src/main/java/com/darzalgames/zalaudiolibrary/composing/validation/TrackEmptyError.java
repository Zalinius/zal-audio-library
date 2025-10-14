package com.darzalgames.zalaudiolibrary.composing.validation;

import com.darzalgames.zalaudiolibrary.composing.tracks.Track;

public class TrackEmptyError implements CompositionError {

	private final Track emptyTrack;

	public TrackEmptyError(Track emptyTrack) {
		this.emptyTrack = emptyTrack;
	}

	@Override
	public String getError() {
		return "Track " + emptyTrack.getTrackName() + " is empty";
	}

}
