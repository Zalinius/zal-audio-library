package com.darzalgames.zalaudiolibrary.composing.tracks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.TestTools;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

class SixteenthRhythmTrackTest {

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on4SixteenthTrack_returnsAllNotes() {
		List<Boolean> rhythm = List.of(true, true, true, true);
		SixteenthRhythmTrack rhythmTrack = new SixteenthRhythmTrack("song", "track", TestTools.TEST_INSTRUMENT, 1f, rhythm, Pitch.NONE);

		List<TimedMusicalInstant> activeInstants = rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(4, activeInstants.size());
		assertEquals(new Fraction(0, 4), activeInstants.get(0).startingBeat());
		assertEquals(new Fraction(1, 4), activeInstants.get(1).startingBeat());
		assertEquals(new Fraction(2, 4), activeInstants.get(2).startingBeat());
		assertEquals(new Fraction(3, 4), activeInstants.get(3).startingBeat());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on2BeatSixteenthTrack_returnsTwoNotes() {
		List<Boolean> rhythm = List.of(true, false, true, false);
		SixteenthRhythmTrack rhythmTrack = new SixteenthRhythmTrack("song", "track", TestTools.TEST_INSTRUMENT, 1f, rhythm, Pitch.NONE);

		List<TimedMusicalInstant> activeInstants = rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(0, 4), activeInstants.get(0).startingBeat());
		assertEquals(new Fraction(2, 4), activeInstants.get(1).startingBeat());
	}

}
