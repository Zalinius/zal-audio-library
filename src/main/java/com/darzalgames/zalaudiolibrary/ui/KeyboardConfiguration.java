package com.darzalgames.zalaudiolibrary.ui;

import java.util.*;

import com.darzalgames.zalaudiolibrary.composing.Pitch;

public class KeyboardConfiguration {

	private static final List<Character> COLEMAK_KEYS = List.of(
			'z', 'x', 'c', 'd', 'h', ',', '.', '/',
			'a', 'r', 's', 't', 'n', 'e', 'i', 'o',
			'q', 'w', 'f', 'p', 'l', 'u', 'y', ';'
	);

	private static final List<Character> DVORAK_KEYS = List.of(
			';', 'q', 'j', 'k', 'm', 'w', 'v', 'z',
			'a', 'o', 'e', 'u', 'h', 't', 'n', 's',
			'\'', ',', '.', 'p', 'g', 'c', 'r', 'l'
	);

	private static final List<Character> QWERTY_KEYS = List.of(
			'z', 'x', 'c', 'v', 'm', ',', '.', '/',
			'a', 's', 'd', 'f', 'j', 'k', 'l', ';',
			'q', 'w', 'e', 'r', 'u', 'i', 'o', 'p'
	);

	private final Pitch bottomPitch;

	public KeyboardConfiguration(Pitch bottomPitch) {
		this.bottomPitch = bottomPitch;
	}

	public Map<Character, Pitch> qwerty() {
		return makeConfigurationForLayout(QWERTY_KEYS);
	}

	public Map<Character, Pitch> dvorak() {
		return makeConfigurationForLayout(DVORAK_KEYS);
	}

	public Map<Character, Pitch> colemak() {
		return makeConfigurationForLayout(COLEMAK_KEYS);
	}

	private Map<Character, Pitch> makeConfigurationForLayout(List<Character> layout) {
		Map<Character, Pitch> keyPitchMap = new LinkedHashMap<>();

		Pitch currentPitch = bottomPitch;

		for (Iterator<Character> keyIt = layout.iterator(); keyIt.hasNext();) {
			Character character = keyIt.next();

			keyPitchMap.put(character, currentPitch);

			if ((currentPitch.octaveDown() == bottomPitch
					|| currentPitch.octaveDown().octaveDown() == bottomPitch
					|| currentPitch.octaveDown().octaveDown().octaveDown() == bottomPitch)
					&& keyIt.hasNext()) {
				character = keyIt.next();
				keyPitchMap.put(character, currentPitch);
			}
			currentPitch = currentPitch.up();
		}

		return keyPitchMap;
	}

	public static Map<Character, Pitch> reorderForVisuals(Map<Character, Pitch> original) {
		List<Character> characters = new ArrayList<>(original.keySet());
		List<Character> reordered = new ArrayList<>(characters.subList(16, 24));

		reordered.addAll(characters.subList(8, 16));
		reordered.addAll(characters.subList(0, 8));

		Map<Character, Pitch> reorderedMap = new LinkedHashMap<>();
		reordered.forEach(c -> reorderedMap.put(c, original.get(c)));

		return reorderedMap;
	}

}
