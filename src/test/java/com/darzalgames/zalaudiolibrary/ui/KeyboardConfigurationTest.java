package com.darzalgames.zalaudiolibrary.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
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

	@Test
	void qwerty_correspondsToCorrectKeysAndNotes() {
		KeyboardConfiguration keyboardLayout = new KeyboardConfiguration(Pitch.C3);

		Map<Character, Pitch> qwerty = keyboardLayout.qwerty();

		assertEquals(24, qwerty.size());

		assertEquals(Pitch.C5, qwerty.get('q'));
		assertEquals(Pitch.D5, qwerty.get('w'));
		assertEquals(Pitch.E5, qwerty.get('e'));
		assertEquals(Pitch.F5, qwerty.get('r'));
		assertEquals(Pitch.G5, qwerty.get('u'));
		assertEquals(Pitch.A5, qwerty.get('i'));
		assertEquals(Pitch.B5, qwerty.get('o'));
		assertEquals(Pitch.C6, qwerty.get('p'));

		assertEquals(Pitch.C4, qwerty.get('a'));
		assertEquals(Pitch.D4, qwerty.get('s'));
		assertEquals(Pitch.E4, qwerty.get('d'));
		assertEquals(Pitch.F4, qwerty.get('f'));
		assertEquals(Pitch.G4, qwerty.get('j'));
		assertEquals(Pitch.A4, qwerty.get('k'));
		assertEquals(Pitch.B4, qwerty.get('l'));
		assertEquals(Pitch.C5, qwerty.get(';'));

		assertEquals(Pitch.C3, qwerty.get('z'));
		assertEquals(Pitch.D3, qwerty.get('x'));
		assertEquals(Pitch.E3, qwerty.get('c'));
		assertEquals(Pitch.F3, qwerty.get('v'));
		assertEquals(Pitch.G3, qwerty.get('m'));
		assertEquals(Pitch.A3, qwerty.get(','));
		assertEquals(Pitch.B3, qwerty.get('.'));
		assertEquals(Pitch.C4, qwerty.get('/'));
	}

	@Test
	void dvorak_correspondsToCorrectKeysAndNotes() {
		KeyboardConfiguration keyboardLayout = new KeyboardConfiguration(Pitch.C3);

		Map<Character, Pitch> dvorak = keyboardLayout.dvorak();

		assertEquals(24, dvorak.size());

		assertEquals(Pitch.C5, dvorak.get('\''));
		assertEquals(Pitch.D5, dvorak.get(','));
		assertEquals(Pitch.E5, dvorak.get('.'));
		assertEquals(Pitch.F5, dvorak.get('p'));
		assertEquals(Pitch.G5, dvorak.get('g'));
		assertEquals(Pitch.A5, dvorak.get('c'));
		assertEquals(Pitch.B5, dvorak.get('r'));
		assertEquals(Pitch.C6, dvorak.get('l'));

		assertEquals(Pitch.C4, dvorak.get('a'));
		assertEquals(Pitch.D4, dvorak.get('o'));
		assertEquals(Pitch.E4, dvorak.get('e'));
		assertEquals(Pitch.F4, dvorak.get('u'));
		assertEquals(Pitch.G4, dvorak.get('h'));
		assertEquals(Pitch.A4, dvorak.get('t'));
		assertEquals(Pitch.B4, dvorak.get('n'));
		assertEquals(Pitch.C5, dvorak.get('s'));

		assertEquals(Pitch.C3, dvorak.get(';'));
		assertEquals(Pitch.D3, dvorak.get('q'));
		assertEquals(Pitch.E3, dvorak.get('j'));
		assertEquals(Pitch.F3, dvorak.get('k'));
		assertEquals(Pitch.G3, dvorak.get('m'));
		assertEquals(Pitch.A3, dvorak.get('w'));
		assertEquals(Pitch.B3, dvorak.get('v'));
		assertEquals(Pitch.C4, dvorak.get('z'));
	}

	@Test
	void reorderForVisuals_onColemak_correspondsToColemakLayoutOrder() {
		KeyboardConfiguration keyboardLayout = new KeyboardConfiguration(Pitch.C3);

		Map<Character, Pitch> reordered = KeyboardConfiguration.reorderForVisuals(keyboardLayout.colemak());
		List<Character> charOrder = new ArrayList<>(reordered.keySet());

		assertEquals(24, reordered.size());

		assertEquals('q', charOrder.get(0));
		assertEquals(Pitch.C5, reordered.get('q'));
		assertEquals('w', charOrder.get(1));
		assertEquals(Pitch.D5, reordered.get('w'));
		assertEquals('f', charOrder.get(2));
		assertEquals(Pitch.E5, reordered.get('f'));
		assertEquals('p', charOrder.get(3));
		assertEquals(Pitch.F5, reordered.get('p'));
		assertEquals('l', charOrder.get(4));
		assertEquals(Pitch.G5, reordered.get('l'));
		assertEquals('u', charOrder.get(5));
		assertEquals(Pitch.A5, reordered.get('u'));
		assertEquals('y', charOrder.get(6));
		assertEquals(Pitch.B5, reordered.get('y'));
		assertEquals(';', charOrder.get(7));
		assertEquals(Pitch.C6, reordered.get(';'));

		assertEquals('a', charOrder.get(8));
		assertEquals(Pitch.C4, reordered.get('a'));
		assertEquals('r', charOrder.get(9));
		assertEquals(Pitch.D4, reordered.get('r'));
		assertEquals('s', charOrder.get(10));
		assertEquals(Pitch.E4, reordered.get('s'));
		assertEquals('t', charOrder.get(11));
		assertEquals(Pitch.F4, reordered.get('t'));
		assertEquals('n', charOrder.get(12));
		assertEquals(Pitch.G4, reordered.get('n'));
		assertEquals('e', charOrder.get(13));
		assertEquals(Pitch.A4, reordered.get('e'));
		assertEquals('i', charOrder.get(14));
		assertEquals(Pitch.B4, reordered.get('i'));
		assertEquals('o', charOrder.get(15));
		assertEquals(Pitch.C5, reordered.get('o'));

		assertEquals('z', charOrder.get(16));
		assertEquals(Pitch.C3, reordered.get('z'));
		assertEquals('x', charOrder.get(17));
		assertEquals(Pitch.D3, reordered.get('x'));
		assertEquals('c', charOrder.get(18));
		assertEquals(Pitch.E3, reordered.get('c'));
		assertEquals('d', charOrder.get(19));
		assertEquals(Pitch.F3, reordered.get('d'));
		assertEquals('h', charOrder.get(20));
		assertEquals(Pitch.G3, reordered.get('h'));
		assertEquals(',', charOrder.get(21));
		assertEquals(Pitch.A3, reordered.get(','));
		assertEquals('.', charOrder.get(22));
		assertEquals(Pitch.B3, reordered.get('.'));
		assertEquals('/', charOrder.get(23));
		assertEquals(Pitch.C4, reordered.get('/'));
	}

}
