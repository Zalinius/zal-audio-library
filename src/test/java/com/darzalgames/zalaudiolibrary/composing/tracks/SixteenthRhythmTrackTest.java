package com.darzalgames.zalaudiolibrary.composing.tracks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.TestTools;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

class SixteenthRhythmTrackTest {

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_onNoRhythmTrack_returnsNoNotes() {
		List<Boolean> rhythm = List.of(false, false, false, false);
		SixteenthRhythmTrack rhythmTrack = new SixteenthRhythmTrack("song", "track", TestTools.TEST_INSTRUMENT, 1f, rhythm, Pitch.NONE);

		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(0).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(1).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(2).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(3).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(4).size());
	}

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

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_onInitiallySilentSixteenthTrack_returnsNotesOnlyAfterInitialSilence() {
		List<Boolean> rhythm = List.of(true, true, true, true);
		SixteenthRhythmTrack rhythmTrack = new SixteenthRhythmTrack("song", "track", TestTools.TEST_INSTRUMENT, 1f, rhythm, Pitch.NONE);

		rhythmTrack.padWithSilence(new Fraction(8));

		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(0).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(1).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(2).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(3).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(4).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(5).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(6).size());
		assertEquals(0, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(7).size());
		assertEquals(4, rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(8).size());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_withAppliedEffect_hasEffectAppliedOnReturnedNotes() {
		List<Boolean> rhythm = List.of(true, false, true, false);
		Pitch originalPitch = Pitch.C4;
		SixteenthRhythmTrack rhythmTrack = new SixteenthRhythmTrack("song", "track", TestTools.TEST_INSTRUMENT, 1f, rhythm, originalPitch);

		rhythmTrack.addMusicalEffect(new TransposeEffect(Pitch::up));
		List<TimedMusicalInstant> activeInstants = rhythmTrack.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(0, 4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.D4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(2, 4), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.D4, activeInstants.get(1).musicalInstant().pitch());
	}

}
