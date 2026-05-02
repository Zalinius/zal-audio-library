package com.darzalgames.zalaudiolibrary.sfx;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.TimedSimpleSound;

public class SoundEffect {
	private final String name;
	private final List<TimedSimpleSound> innerSounds;

	public SoundEffect(String name) {
		this.name = name;
		innerSounds = new ArrayList<>();
	}

	public void addSound(SimpleSound sound) {
		innerSounds.add(new TimedSimpleSound(0, sound));
	}

	public void addSound(SimpleSound sound, float startTime) {
		innerSounds.add(new TimedSimpleSound(startTime, sound));
	}

	public float duration() {
		return innerSounds.stream()
				.map(sound -> sound.startTime() + sound.simpleSound().duration())
				.reduce(0f, Float::max);
	}

	public List<TimedSimpleSound> getInnerSounds() {
		return innerSounds;
	}

	public String getSoundName() {
		return name;
	}

}
