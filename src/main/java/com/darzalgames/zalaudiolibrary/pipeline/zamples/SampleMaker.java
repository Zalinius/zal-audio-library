package com.darzalgames.zalaudiolibrary.pipeline.zamples;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.VolumeListener;
import com.darzalgames.zalaudiolibrary.effects.sampling.SampleEffect;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.TimedSimpleSound;

public class SampleMaker implements VolumeListener {

	private final AtomicReference<Float> musicVolume;
	private final AtomicReference<Float> soundVolume;
	private final Map<String, Float> phaseMap;

	private float maxAbsolutePeak;

	public SampleMaker(Float musicVolume, Float soundVolume) {
		this.musicVolume = new AtomicReference<>(musicVolume);
		this.soundVolume = new AtomicReference<>(soundVolume);
		phaseMap = new HashMap<>();
	}

	public float[] makeSamples(List<TimedSimpleSound>  simpleSounds, int sampleCount, float samplingStartTime, List<SampleEffect> samplingEffects) {
		float[] sampleBuffer = new float[sampleCount];
		float currentMusicVolume = musicVolume.get();

		for (Iterator<TimedSimpleSound> it = simpleSounds.iterator(); it.hasNext();) {
			TimedSimpleSound timedSimpleSound = it.next();
			SimpleSound simpleSound = timedSimpleSound.simpleSound();

			float phaseAtMinus1 = phaseMap.getOrDefault(simpleSound.id(), 0f);
			float alpha = phaseAtMinus1;
			float beta = computeBeta(samplingStartTime, timedSimpleSound);
			float phi = alpha - beta;

			for (int i = 0; i < sampleBuffer.length; i++) {
				float t = samplingStartTime - timedSimpleSound.startTime() + i * AudioConstants.SAMPLE_DURATION;

				float amplitude = simpleSound.computeAmplitude(t);
				float frequency = simpleSound.computeFrequency(t);

				//This is the wave phase, on interval [0,1[
				float waveProgress = frequency * t + phi;
				float moduloedWaveProgress = waveProgress - (float) Math.floor(waveProgress);
				float waveValue = simpleSound.timbre().f(moduloedWaveProgress);

				sampleBuffer[i] += currentMusicVolume * amplitude * waveValue;

				for (Iterator<SampleEffect> effectIt = samplingEffects.iterator(); effectIt.hasNext();) {
					SampleEffect sampleEffect = effectIt.next();
					sampleBuffer[i] = sampleEffect.apply(sampleBuffer[i]);
				}

				if(i == sampleCount - 1) {
					phaseMap.put(simpleSound.id(), moduloedWaveProgress);
				}
			}
		}

		for (int i = 0; i < sampleBuffer.length; i++) {
			maxAbsolutePeak = Math.max(maxAbsolutePeak, Math.abs(sampleBuffer[i]));
		}

		return sampleBuffer;
	}

	private static float computeBeta(float samplingStartTime, TimedSimpleSound timedSimpleSound) {
		SimpleSound simpleSound = timedSimpleSound.simpleSound();
		float t = samplingStartTime - timedSimpleSound.startTime() + 0 * AudioConstants.SAMPLE_DURATION;

		float frequency = simpleSound.computeFrequency(t);

		//This is the wave phase, on interval [0,1[
		float waveProgress = frequency * t;
		return waveProgress - (float)Math.floor(waveProgress);
	}

	public static float computeCurrentTimeForSimpleSoundForSampleIndex(final float currentAbsoluteTime, final float absoluteSimpleSoundStartTime, int sampleIndex, float sampleDuration) {
		return (currentAbsoluteTime - absoluteSimpleSoundStartTime) + sampleIndex * sampleDuration;
	}

	@Override
	public void setMusicVolume(float volume) {
		musicVolume.set(volume);
	}

	@Override
	public void setSoundEffectVolume(float volume) {
		soundVolume.set(volume);
	}

	public float getMaxPeak() {
		return maxAbsolutePeak;
	}

}
