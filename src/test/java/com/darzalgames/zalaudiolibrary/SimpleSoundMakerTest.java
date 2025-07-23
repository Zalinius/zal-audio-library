package com.darzalgames.zalaudiolibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSoundMaker;

class SimpleSoundMakerTest {

	@Test
	void extrapolateStartTime_startTimeIsCurrentTime_returnsCurrentTime() {
		Fraction startingBeat = new Fraction(0);
		float currentBeat = 0f;
		float currentTime = 0f;
		float bps = 1f;

		float startTime = SimpleSoundMaker.extrapolateStartTime(startingBeat, currentBeat, currentTime, bps);

		assertEquals(0, startTime);
	}

	@Test
	void extrapolateStartTime_startBeatBeforeCurrentBeat_returnsStartBeatTime() {
		Fraction noteStartingBeat = new Fraction(0);
		float currentBeat = 0.25f;
		float currentTime = 0.25f;
		float bps = 1f;

		float startTime = SimpleSoundMaker.extrapolateStartTime(noteStartingBeat, currentBeat, currentTime, bps);

		assertEquals(0, startTime);
	}

	@Test
	void extrapolateStartTime_startBeatBeforeCurrentBeatLaterInSong_returnsStartBeatTime() {
		Fraction noteStartingBeat = new Fraction(5, 2);
		float currentBeat = 2.625f;
		float currentTime = 2.625f;
		float bps = 1f;

		float startTime = SimpleSoundMaker.extrapolateStartTime(noteStartingBeat, currentBeat, currentTime, bps);

		assertEquals(2.5, startTime);
	}

	@Test
	void extrapolateStartTime_startBeatBeforeCurrentBeatWithBps2_returnsResultAccountingForBps() {
		Fraction noteStartingBeat = new Fraction(5, 2);
		float currentBeat = 2.625f;
		float currentTime = 2.625f;
		float bps = 2f;

		float startTime = SimpleSoundMaker.extrapolateStartTime(noteStartingBeat, currentBeat, currentTime, bps);

		assertEquals(2.5625f, startTime);
	}

}
