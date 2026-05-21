package com.darzalgames.zalaudiolibrary.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.composing.Pitch;

class KeyboardConfigurationTest {

	@Test
	void colemak_correspondsToCorrectKeysAndNotes() {
		KeyboardConfiguration keyboardLayout = new KeyboardConfiguration(Pitch.C3);

		Map<Character, Pitch> colemak = keyboardLayout.colemak();

		assertEquals(24, colemak.size());

		assertEquals(Pitch.C5, colemak.get('q'));
		assertEquals(Pitch.D5, colemak.get('w'));
		assertEquals(Pitch.E5, colemak.get('f'));
		assertEquals(Pitch.F5, colemak.get('p'));
		assertEquals(Pitch.G5, colemak.get('l'));
		assertEquals(Pitch.A5, colemak.get('u'));
		assertEquals(Pitch.B5, colemak.get('y'));
		assertEquals(Pitch.C6, colemak.get(';'));

		assertEquals(Pitch.C4, colemak.get('a'));
		assertEquals(Pitch.D4, colemak.get('r'));
		assertEquals(Pitch.E4, colemak.get('s'));
		assertEquals(Pitch.F4, colemak.get('t'));
		assertEquals(Pitch.G4, colemak.get('n'));
		assertEquals(Pitch.A4, colemak.get('e'));
		assertEquals(Pitch.B4, colemak.get('i'));
		assertEquals(Pitch.C5, colemak.get('o'));

		assertEquals(Pitch.C3, colemak.get('z'));
		assertEquals(Pitch.D3, colemak.get('x'));
		assertEquals(Pitch.E3, colemak.get('c'));
		assertEquals(Pitch.F3, colemak.get('d'));
		assertEquals(Pitch.G3, colemak.get('h'));
		assertEquals(Pitch.A3, colemak.get(','));
		assertEquals(Pitch.B3, colemak.get('.'));
		assertEquals(Pitch.C4, colemak.get('/'));
	}

}
