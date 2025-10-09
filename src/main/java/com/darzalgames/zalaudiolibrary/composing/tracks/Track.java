package com.darzalgames.zalaudiolibrary.composing.tracks;

import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

public interface Track {

	/**
	 * Gets all musical instants playing during any part of this beat, inclusively (aka, if an instant begins on the very start of the next beat, it is included in the interval)
	 * @param startBeat an integer start beat. Must be non-negative
	 * @return An ordered list of TimedMusicalInstants which are active on the inclusive interval [startBeat, startBeat+1]
	 */
	List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat);

	void padWithSilence(Fraction beats);

	void addMusicalEffect(MusicalEffect musicalEffect);

	boolean isValid();
}
