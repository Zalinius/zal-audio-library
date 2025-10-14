package com.darzalgames.zalaudiolibrary.composing.validation;

import com.darzalgames.zalaudiolibrary.composing.tracks.CompositeTrack;

public class CompositeTrackDesynchronization implements CompositionError {

	private final CompositeTrack desynchronizedCompositeTrack;
	private final int tracks;
	private final int partials;

	public CompositeTrackDesynchronization(CompositeTrack desynchronizedCompositeTrack, int tracks, int partials) {
		this.desynchronizedCompositeTrack = desynchronizedCompositeTrack;
		this.tracks = tracks;
		this.partials = partials;
	}

	@Override
	public String getError() {
		return "Composite track " + desynchronizedCompositeTrack.getTrackName() + " desynchronized: " + tracks + " tracks but " + partials + " partials";
	}

}
