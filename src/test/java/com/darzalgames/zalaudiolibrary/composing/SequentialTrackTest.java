package com.darzalgames.zalaudiolibrary.composing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

class SequentialTrackTest {

	@Test
	void lengthInBeats_onEmptyTrack_returns0() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		assertEquals(new Fraction(0), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithQuarterNote_returns1() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addSilence(NoteDuration.QUARTER);

		assertEquals(new Fraction(1), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithWholeNote_returns4() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addSilence(NoteDuration.WHOLE);

		assertEquals(new Fraction(4), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithQuarterAndHalfNote_returns3() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addSilence(NoteDuration.QUARTER);
		track.addSilence(NoteDuration.HALF);

		assertEquals(new Fraction(3), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithSixteenthNote_returnsOneQuarter() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addSilence(NoteDuration.SIXTEENTH);

		assertEquals(new Fraction(1, 4), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithThirdNote_returnsFourThirds() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addSilence(NoteDuration.THIRD);

		assertEquals(new Fraction(4, 3), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithSixteenthAndThirdNote_returnsNineteedTwelths() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addSilence(NoteDuration.SIXTEENTH);
		track.addSilence(NoteDuration.THIRD);

		assertEquals(new Fraction(19, 12), track.lengthInBeats());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on0AndWholeNote_returnsSingleInstant() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on0AndTwoSequentialQuarterNotes_returnsBothNotes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.QUARTER, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER, Pitch.C5, ConstantEnvelope.zeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(1), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(1).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_onQuarterDotNotesAndFirstBeat_returns2Notes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C5, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C6, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C7, ConstantEnvelope.zeroEnvelope());

		List<TimedMusicalInstant> activeInstantsAt1 = track.getMusicalInstantsActiveThisBeatInclusive(1);
		assertEquals(2, activeInstantsAt1.size());
		assertEquals(new Fraction(0), activeInstantsAt1.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstantsAt1.get(0).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(3,2), activeInstantsAt1.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstantsAt1.get(1).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_at0With8SequentialSixteenthNotes_returnsFirst5Notes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.D4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.E4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.F4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.G4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.A4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.B4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.C5, ConstantEnvelope.zeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(5, activeInstants.size());
		assertEquals(new Fraction(0, 4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(1, 4), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.D4, activeInstants.get(1).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(2, 4), activeInstants.get(2).startingBeat());
		assertEquals(Pitch.E4, activeInstants.get(2).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(3, 4), activeInstants.get(3).startingBeat());
		assertEquals(Pitch.F4, activeInstants.get(3).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(4, 4), activeInstants.get(4).startingBeat());
		assertEquals(Pitch.G4, activeInstants.get(4).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on7AndTwoWholeNotes_returnsSecondAndThenFirstByWrappingAroundTrack() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C5, ConstantEnvelope.zeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(7);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(0).musicalInstant().pitch().getBasePitch());
		assertEquals(new Fraction(0), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(1).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_inMiddleOfWholeNote_returnsThatNote() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C5, ConstantEnvelope.zeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(1);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_with4BeatTrackAnd6BeatsIn_returnsLoopedNoteWith4AsStartBeat() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, ConstantEnvelope.zeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(6);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch().getBasePitch());
	}

	@Test
	void isTrackLengthValid_onEmptyTrack_returnsFalse() {
		assertFalse(new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope())).isValid());
	}

	@Test
	void isTrackLengthValid_onTrackWithFractionalNumberOfBeats_returnsFalse() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));
		track.addSilence(NoteDuration.THIRD);

		assertFalse(track.isValid());
	}

	@Test
	void isTrackLengthValid_onTrackWithIntegerNumberOfBeats_returnsTrue() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()));
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.QUARTER);

		assertTrue(track.isValid());
	}

}
