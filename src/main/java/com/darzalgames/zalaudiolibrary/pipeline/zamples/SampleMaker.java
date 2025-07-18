package com.darzalgames.zalaudiolibrary.pipeline.zamples;

import java.util.concurrent.atomic.AtomicReference;

import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.VolumeListener;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;

public class SampleMaker implements VolumeListener {

	private final AtomicReference<Float> musicVolume;
	private final AtomicReference<Float> soundVolume;


	public SampleMaker(Float musicVolume, Float soundVolume) {
		this.musicVolume = new AtomicReference<>(musicVolume);
		this.soundVolume = new AtomicReference<>(soundVolume);
	}

	public float[] makeSongSamples(SimpleSound simpleSound) {
		float songVolume = musicVolume.get();
		float[] samples = new float[(int) (AudioConstants.SAMPLING_RATE * simpleSound.getDuration())];

		for (int i = 0; i < samples.length; i++) {
			float t = i/(float)AudioConstants.SAMPLING_RATE;
			float x = simpleSound.getFrequency() * t % 1f;

			float sample = simpleSound.getTimbre().f(x) * simpleSound.getEnvelope().getEnvelope(simpleSound.getDuration(), t);
			samples[i] = sample * songVolume;
		}

		return samples;
	}

	@Override
	public void setMusicVolume(float volume) {
		musicVolume.set(volume);
	}

	@Override
	public void setSoundEffectVolume(float volume) {
		soundVolume.set(volume);
	}
}
