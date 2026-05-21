package com.darzalgames.zalaudiolibrary.ui;

import java.util.*;

import com.darzalgames.zalaudiolibrary.composing.Pitch;

public class KeyboardConfiguration {

	private final Pitch bottomPitch;

	public KeyboardConfiguration(Pitch bottomPitch) {
		this.bottomPitch = bottomPitch;
	}

	public Map<Character, Pitch> colemak() {
		List<Character> keys = List.of(
				'z', 'x', 'c', 'd', 'h', ',', '.', '/',
				'a', 'r', 's', 't', 'n', 'e', 'i', 'o',
				'q', 'w', 'f', 'p', 'l', 'u', 'y', ';'
		);

		Map<Character, Pitch> keyPitchMap = new LinkedHashMap<>();

		Pitch currentPitch = bottomPitch;

		for (Iterator<Character> keyIt = keys.iterator(); keyIt.hasNext();) {
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

}
