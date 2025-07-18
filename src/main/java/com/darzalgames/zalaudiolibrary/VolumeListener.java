package com.darzalgames.zalaudiolibrary;

/**
 * An interface for listening and reacting to changes in audio volume
 */
public interface VolumeListener {

	/**
	 * calls all volume setting functions with volume
	 * @param volume A multiplier for all volumes, within [0,1]
	 */
	default void setAllVolumes(float volume) {
		setMusicVolume(volume);
		setSoundEffectVolume(volume);
	}

	/**
	 * Sets the music volume
	 * @param volume A multiplier for music volume, within [0,1]
	 */
	void setMusicVolume(float volume);

	/**
	 * Sets the sound effect volume
	 * @param volume A multiplier for sound effect volume, within [0,1]
	 */
	void setSoundEffectVolume(float volume);
}
