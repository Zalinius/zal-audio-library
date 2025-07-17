package com.darzalgames.zalaudiolibrary.composing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NoteDurationTest {

	@Test
	void threeThirdNotess_areEqualToAWholeNote() {
		NoteDuration third = NoteDuration.THIRD;
		NoteDuration whole = NoteDuration.WHOLE;

		assertEquals(whole.getDurationInBeats(), third.getDurationInBeats().scale(3));
	}

}
