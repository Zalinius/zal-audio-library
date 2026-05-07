package com.darzalgames.zalaudiolibrary.sfx;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.TimedSimpleSound;

/**
 * A class for representing sound effects to be played in an AudioPipeline
 * These sounds are meant to operate independently from music, and aren't affect by changes in tempo
 */
public class SoundEffect {

	private final String name;
	private final List<TimedSimpleSound> innerSounds;

	/**
	 * Creates a sound effect
	 * @param name   the name of the effect
	 * @param sounds inital sounds, with no delay
	 */
	public SoundEffect(String name, SimpleSound... sounds) {
		this.name = name;
		innerSounds = new ArrayList<>();
		for (SimpleSound simpleSound : sounds) {
			addSound(simpleSound);
		}
	}

	/**
	 * Adds a sound to the sound effect with no delay
	 * @param sound the sound to add
	 */
	public void addSound(SimpleSound sound) {
		addSound(sound, 0);
	}

	/**
	 * Adds a sound to the sound effect with the specified delay
	 * @param sound     the sound to add
	 * @param startTime the delay on the sound in seconds. Must be non-negative
	 */
	public void addSound(SimpleSound sound, float startTime) {
		validateDuplicateId(sound);
		if (startTime < 0) {
			throw new IllegalArgumentException("startTime for sound must be non-negative: " + startTime);
		}
		innerSounds.add(new TimedSimpleSound(startTime, sound));
	}

	/**
	 * Computes the total duration of the sound effect, accounting for delays in sounds
	 * @return the duration in seconds
	 */
	public float duration() {
		return innerSounds.stream()
				.map(sound -> sound.startTime() + sound.simpleSound().duration())
				.reduce(0f, Float::max);
	}

	/**
	 * Gets the TimedSimpleSounds that make up this sound effect
	 * @return An list of TimedSimpleSounds, in the order they were added to the SoundEffect
	 */
	public List<TimedSimpleSound> getInnerSounds() {
		return innerSounds;
	}

	/**
	 * Gets the name of the Sound Effect
	 * @return The name of the Sound Effect
	 */
	public String getSoundName() {
		return name;
	}

	private void validateDuplicateId(SimpleSound newSound) {
		if (innerSounds.stream().anyMatch(sound -> sound.simpleSound().id().equals(newSound.id()))) {
			throw new IllegalArgumentException("Duplicate ID in sound effect: " + newSound.id());
		}
	}

	/**
	 * Creates a blank SoundEffect, one with no inner sounds. It is safe to play in an AudioPipeline
	 * @return a blank SoundEffect
	 */
	public static SoundEffect blank() {
		return new SoundEffect("blank");
	}

}
