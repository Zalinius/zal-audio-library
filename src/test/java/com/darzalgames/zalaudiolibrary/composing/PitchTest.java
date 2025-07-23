package com.darzalgames.zalaudiolibrary.composing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PitchTest {

	@Test
	void up_onNaturalPitch_returnsNextNaturalNoteUp() {
		assertEquals(Pitch.D1, Pitch.C1.up());
		assertEquals(Pitch.A1, Pitch.G1.up());
		assertEquals(Pitch.C2, Pitch.B1.up());
		assertEquals(Pitch.D2, Pitch.C2.up());
		assertEquals(Pitch.E2, Pitch.D2.up());
		assertEquals(Pitch.F2, Pitch.E2.up());
		assertEquals(Pitch.G2, Pitch.F2.up());
	}

	@Test
	void down_onNaturalPitch_returnsNextNaturalNoteDown() {
		assertEquals(Pitch.C1, Pitch.D1.down());
		assertEquals(Pitch.G1, Pitch.A1.down());
		assertEquals(Pitch.B1, Pitch.C2.down());
		assertEquals(Pitch.C2, Pitch.D2.down());
		assertEquals(Pitch.D2, Pitch.E2.down());
		assertEquals(Pitch.E2, Pitch.F2.down());
		assertEquals(Pitch.F2, Pitch.G2.down());
	}

	@Test
	void up_onSharpPitch_returnsNextNaturalNoteUp() {
		Pitch d3sharp = Pitch.makePitch("d3s", 155.56f);
		assertEquals(Pitch.E3, d3sharp.up());
	}

	@Test
	void down_onSharpPitch_returnsNextNaturalNoteDown() {
		Pitch d3sharp = Pitch.makePitch("d3s", 155.56f);
		assertEquals(Pitch.D3, d3sharp.down());
	}

	@Test
	void octaveUp_onNaturalPitch_returnsNoteOneOctaveHigher() {
		assertEquals(Pitch.C2, Pitch.C1.octaveUp());
		assertEquals(Pitch.C3, Pitch.C2.octaveUp());
		assertEquals(Pitch.D3, Pitch.D2.octaveUp());
		assertEquals(Pitch.E3, Pitch.E2.octaveUp());
	}

	@Test
	void octaveDown_onNaturalPitch_returnsNoteOneOctaveLower() {
		assertEquals(Pitch.C1, Pitch.C2.octaveDown());
		assertEquals(Pitch.C2, Pitch.C3.octaveDown());
		assertEquals(Pitch.D2, Pitch.D3.octaveDown());
		assertEquals(Pitch.E2, Pitch.E3.octaveDown());
	}

	@Test
	void up_onHighestPitch_returnsNone() {
		assertEquals(Pitch.NONE, Pitch.C8.up());
	}

	@Test
	void octaveUp_onHighestPitch_returnsNone() {
		assertEquals(Pitch.NONE, Pitch.C8.octaveUp());
	}

	@Test
	void down_onLowestPitch_returnsNone() {
		assertEquals(Pitch.NONE, Pitch.C0.down());
	}

	@Test
	void octaveDown_onLowestPitch_returnsNone() {
		assertEquals(Pitch.NONE, Pitch.C0.octaveDown());
	}


}
