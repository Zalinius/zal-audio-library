package com.darzalgames.zalaudiolibrary.composing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.composing.validation.CompositionError;
import com.darzalgames.zalaudiolibrary.composing.validation.TrackEmptyError;
import com.darzalgames.zalaudiolibrary.composing.validation.TrackFractionalError;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

class SequentialTrackTest {

	@Test
	void lengthInBeats_onEmptyTrack_returns0() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		assertEquals(new Fraction(0), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithQuarterNote_returns1() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addSilence(NoteDuration.QUARTER);

		assertEquals(new Fraction(1), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithWholeNote_returns4() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addSilence(NoteDuration.WHOLE);

		assertEquals(new Fraction(4), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithQuarterAndHalfNote_returns3() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addSilence(NoteDuration.QUARTER);
		track.addSilence(NoteDuration.HALF);

		assertEquals(new Fraction(3), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithSixteenthNote_returnsOneQuarter() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addSilence(NoteDuration.SIXTEENTH);

		assertEquals(new Fraction(1, 4), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithThirdNote_returnsFourThirds() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addSilence(NoteDuration.THIRD);

		assertEquals(new Fraction(4, 3), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithSixteenthAndThirdNote_returnsNineteedTwelths() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addSilence(NoteDuration.SIXTEENTH);
		track.addSilence(NoteDuration.THIRD);

		assertEquals(new Fraction(19, 12), track.lengthInBeats());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on0AndWholeNote_returnsSingleInstant() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.WHOLE, Pitch.C4);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on0AndTwoSequentialQuarterNotes_returnsBothNotes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.QUARTER, Pitch.C4);
		track.addNote(NoteDuration.QUARTER, Pitch.C5);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(1), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_onQuarterDotNotesAndFirstBeat_returns2Notes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.QUARTER_DOT, Pitch.C4);
		track.addNote(NoteDuration.QUARTER_DOT, Pitch.C5);
		track.addNote(NoteDuration.QUARTER_DOT, Pitch.C6);
		track.addNote(NoteDuration.QUARTER_DOT, Pitch.C7);

		List<TimedMusicalInstant> activeInstantsAt1 = track.getMusicalInstantsActiveThisBeatInclusive(1);
		assertEquals(2, activeInstantsAt1.size());
		assertEquals(new Fraction(0), activeInstantsAt1.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstantsAt1.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(3, 2), activeInstantsAt1.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstantsAt1.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_at0With8SequentialSixteenthNotes_returnsFirst5Notes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.D4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.E4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.F4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.G4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.A4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.B4);
		track.addNote(NoteDuration.SIXTEENTH, Pitch.C5);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(5, activeInstants.size());
		assertEquals(new Fraction(0, 4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(1, 4), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.D4, activeInstants.get(1).musicalInstant().pitch());
		assertEquals(new Fraction(2, 4), activeInstants.get(2).startingBeat());
		assertEquals(Pitch.E4, activeInstants.get(2).musicalInstant().pitch());
		assertEquals(new Fraction(3, 4), activeInstants.get(3).startingBeat());
		assertEquals(Pitch.F4, activeInstants.get(3).musicalInstant().pitch());
		assertEquals(new Fraction(4, 4), activeInstants.get(4).startingBeat());
		assertEquals(Pitch.G4, activeInstants.get(4).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on7AndTwoWholeNotes_returnsSecondAndThenFirstByWrappingAroundTrack() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.WHOLE, Pitch.C4);
		track.addNote(NoteDuration.WHOLE, Pitch.C5);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(7);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(0), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_inMiddleOfWholeNote_returnsThatNote() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.WHOLE, Pitch.C4);
		track.addNote(NoteDuration.WHOLE, Pitch.C5);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(1);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_with4BeatTrackAnd6BeatsIn_returnsLoopedNoteWith4AsStartBeat() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.WHOLE, Pitch.C4);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(6);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void isTrackLengthValid_onEmptyTrack_returnsFalse() {
		SequentialTrack sequentialTrack = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		List<CompositionError> errors = sequentialTrack.validate();

		assertEquals(1, errors.size());
		assertEquals(TrackEmptyError.class, errors.get(0).getClass());
	}

	@Test
	void isTrackLengthValid_onTrackWithFractionalNumberOfBeats_returnsFalse() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);
		track.addSilence(NoteDuration.THIRD);

		List<CompositionError> errors = track.validate();

		assertEquals(1, errors.size());
		assertEquals(TrackFractionalError.class, errors.get(0).getClass());
	}

	@Test
	void isTrackLengthValid_onTrackWithIntegerNumberOfBeats_returnsTrue() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.QUARTER);

		List<CompositionError> errors = track.validate();

		assertTrue(errors.isEmpty());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on4AndFourSequentialQuarterNotes_loopsCorrectlyAndreturnsTwoNotes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.QUARTER, Pitch.C4);
		track.addNote(NoteDuration.QUARTER, Pitch.C5);
		track.addNote(NoteDuration.QUARTER, Pitch.C6);
		track.addNote(NoteDuration.QUARTER, Pitch.C7);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(4);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(5), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on8AndFourSequentialQuarterNotes_loopsCorrectlyAndreturnsTwoNotes() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.QUARTER, Pitch.C4);
		track.addNote(NoteDuration.QUARTER, Pitch.C5);
		track.addNote(NoteDuration.QUARTER, Pitch.C6);
		track.addNote(NoteDuration.QUARTER, Pitch.C7);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(8);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(8), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(9), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_withNonZeroRepetitionPoint_loopsCorrectly() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.QUARTER, Pitch.C4);
		track.addNote(NoteDuration.QUARTER, Pitch.C5);
		track.setRepetitionPoint();
		track.addNote(NoteDuration.QUARTER, Pitch.C6);
		track.addNote(NoteDuration.QUARTER, Pitch.C7);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(8);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(8), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C6, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(9), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C7, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_withNonZeroRepetitionPointAndLongIntro_loopsCorrectlyAtMultiplePoints() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);
		track.addSilence(NoteDuration.HALF);
		track.addSilence(NoteDuration.HALF);
		track.addSilence(NoteDuration.HALF);
		track.addSilence(NoteDuration.HALF);
		track.setRepetitionPoint();
		track.addNote(NoteDuration.QUARTER, Pitch.C4);
		track.addNote(NoteDuration.QUARTER, Pitch.C5);
		track.addNote(NoteDuration.QUARTER, Pitch.C6);
		track.addNote(NoteDuration.QUARTER, Pitch.C7);
		track.addNote(NoteDuration.QUARTER, Pitch.D4);
		track.addNote(NoteDuration.QUARTER, Pitch.D5);
		track.addNote(NoteDuration.QUARTER, Pitch.D6);
		track.addNote(NoteDuration.QUARTER, Pitch.D7);

		List<TimedMusicalInstant> activeInstantsAt16 = track.getMusicalInstantsActiveThisBeatInclusive(16);
		List<TimedMusicalInstant> activeInstantsAt24 = track.getMusicalInstantsActiveThisBeatInclusive(24);
		List<TimedMusicalInstant> activeInstantsAt27 = track.getMusicalInstantsActiveThisBeatInclusive(27);

		assertEquals(2, activeInstantsAt16.size());
		assertEquals(new Fraction(16), activeInstantsAt16.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstantsAt16.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(17), activeInstantsAt16.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstantsAt16.get(1).musicalInstant().pitch());

		assertEquals(2, activeInstantsAt24.size());
		assertEquals(new Fraction(24), activeInstantsAt24.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstantsAt24.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(25), activeInstantsAt24.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstantsAt24.get(1).musicalInstant().pitch());

		assertEquals(2, activeInstantsAt27.size());
		assertEquals(new Fraction(27), activeInstantsAt27.get(0).startingBeat());
		assertEquals(Pitch.C7, activeInstantsAt27.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(28), activeInstantsAt27.get(1).startingBeat());
		assertEquals(Pitch.D4, activeInstantsAt27.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_withUnusualNoteSizesAndNonZeroRepetitionPoint_getsCorrectValuesBeforeLoop() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.WHOLE_DOT, Pitch.C3);
		track.addNote(NoteDuration.WHOLE_DOT, Pitch.E3);
		track.setRepetitionPoint();
		track.addNote(NoteDuration.WHOLE_DOT, Pitch.G3);
		track.addNote(NoteDuration.WHOLE_DOT, Pitch.C4);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C3, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeat_on0AndWholeChordedNote_returnsInstantForEachNoteInChord() {
		SequentialTrack track = new SequentialTrack("song", "track", new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope()), 1f);

		track.addNote(NoteDuration.WHOLE, Pitch.C4, Pitch.C5, Pitch.C6);
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(3, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(new Fraction(0), activeInstants.get(1).startingBeat());
		assertEquals(new Fraction(0), activeInstants.get(2).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(Pitch.C5, activeInstants.get(1).musicalInstant().pitch());
		assertEquals(Pitch.C6, activeInstants.get(2).musicalInstant().pitch());
	}

}
