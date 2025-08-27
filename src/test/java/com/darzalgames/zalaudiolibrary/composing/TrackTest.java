package com.darzalgames.zalaudiolibrary.composing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.ZeroEnvelope;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

class TrackTest {

	@Test
	void lengthInBeats_onEmptyTrack_returns0() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		assertEquals(new Fraction(0), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithQuarterNote_returns1() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addSilence(NoteDuration.QUARTER);

		assertEquals(new Fraction(1), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithWholeNote_returns4() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addSilence(NoteDuration.WHOLE);

		assertEquals(new Fraction(4), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithQuarterAndHalfNote_returns3() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addSilence(NoteDuration.QUARTER);
		track.addSilence(NoteDuration.HALF);

		assertEquals(new Fraction(3), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithSixteenthNote_returnsOneQuarter() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addSilence(NoteDuration.SIXTEENTH);

		assertEquals(new Fraction(1, 4), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithThirdNote_returnsFourThirds() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addSilence(NoteDuration.THIRD);

		assertEquals(new Fraction(4, 3), track.lengthInBeats());
	}

	@Test
	void lengthInBeats_onTrackWithSixteenthAndThirdNote_returnsNineteedTwelths() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addSilence(NoteDuration.SIXTEENTH);
		track.addSilence(NoteDuration.THIRD);

		assertEquals(new Fraction(19, 12), track.lengthInBeats());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on0AndWholeNote_returnsSingleInstant() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, new ZeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_on0AndTwoSequentialQuarterNotes_returnsBothNotes() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.QUARTER, Pitch.C4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER, Pitch.C5, new ZeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(0);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(1), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_onQuarterDotNotesAndFirstBeat_returns2Notes() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C5, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C6, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.QUARTER_DOT, Pitch.C7, new ZeroEnvelope());

		List<TimedMusicalInstant> activeInstantsAt1 = track.getMusicalInstantsActiveThisBeatInclusive(1);
		assertEquals(2, activeInstantsAt1.size());
		assertEquals(new Fraction(0), activeInstantsAt1.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstantsAt1.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(3,2), activeInstantsAt1.get(1).startingBeat());
		assertEquals(Pitch.C5, activeInstantsAt1.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_at0With8SequentialSixteenthNotes_returnsFirst5Notes() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.C4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.D4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.E4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.F4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.G4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.A4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.B4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.SIXTEENTH, Pitch.C5, new ZeroEnvelope());
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
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C5, new ZeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(7);

		assertEquals(2, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C5, activeInstants.get(0).musicalInstant().pitch());
		assertEquals(new Fraction(0), activeInstants.get(1).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(1).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_inMiddleOfWholeNote_returnsThatNote() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, new ZeroEnvelope());
		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C5, new ZeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(1);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(0), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void getMusicalInstantsActiveThisBeatInclusive_with4BeatTrackAnd6BeatsIn_returnsLoopedNoteWith4AsStartBeat() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));

		track.addNote(Synth.zero(), NoteDuration.WHOLE, Pitch.C4, new ZeroEnvelope());
		List<TimedMusicalInstant> activeInstants = track.getMusicalInstantsActiveThisBeatInclusive(6);

		assertEquals(1, activeInstants.size());
		assertEquals(new Fraction(4), activeInstants.get(0).startingBeat());
		assertEquals(Pitch.C4, activeInstants.get(0).musicalInstant().pitch());
	}

	@Test
	void isTrackLengthValid_onEmptyTrack_returnsFalse() {
		assertFalse(new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope())).isValid());
	}

	@Test
	void isTrackLengthValid_onTrackWithFractionalNumberOfBeats_returnsFalse() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));
		track.addSilence(NoteDuration.THIRD);

		assertFalse(track.isValid());
	}

	@Test
	void isTrackLengthValid_onTrackWithIntegerNumberOfBeats_returnsTrue() {
		Track track = new Track("song", "track", new Instrument(Synth.zero(), new ZeroEnvelope()));
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.THIRD);
		track.addSilence(NoteDuration.QUARTER);

		assertTrue(track.isValid());
	}

}
