package com.darzalgames.zalaudiolibrary.pipeline.instants;

import com.darzalgames.darzalcommon.math.Fraction;

public class TimedMusicalInstant {

	private final Fraction startingBeat;
	private final MusicalInstant musicalInstant;

	public TimedMusicalInstant(Fraction startingBeat, MusicalInstant musicalInstant) {
		this.startingBeat = startingBeat;
		this.musicalInstant = musicalInstant;
	}

	public Fraction getStartingBeat() {
		return startingBeat;
	}

	public MusicalInstant getMusicalInstant() {
		return musicalInstant;
	}
}
