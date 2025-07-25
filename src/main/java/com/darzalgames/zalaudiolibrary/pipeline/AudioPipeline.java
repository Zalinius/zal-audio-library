package com.darzalgames.zalaudiolibrary.pipeline;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.BpsController;
import com.darzalgames.zalaudiolibrary.VolumeListener;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSoundMaker;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.TimedSimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.AudioConsumer;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.SampleMaker;

/**
 * An audio pipeline with multiple steps
 * <ol type="1">
 *  <li>Composition</li>
 *  <li>Musical Instants</li>
 *  <li>Simple Sounds</li>
 *  <li>Samples sent to Audio Consumer</li>
 * </ol>
 */
public class AudioPipeline extends Thread {

	private final AtomicBoolean shouldStop;

	private final Song song;
	private final SimpleSoundMaker simpleSoundMaker;
	private final SampleMaker sampler; //Creates Samples from Simple Sounds
	private final AudioConsumer audioConsumer; //receives Samples

	private final BpsController bpsController;

	private float beatCounter;
	private float secondsCounter;

	public AudioPipeline(Song song, AudioConsumer audioConsumer, float musicVolume, float soundVolume) {
		shouldStop = new AtomicBoolean(false);
		this.song = song;
		simpleSoundMaker = new SimpleSoundMaker();
		sampler = new SampleMaker(musicVolume, soundVolume);
		this.audioConsumer = audioConsumer;

		bpsController = new BpsController(song.getInitialBps());


		beatCounter = 0f;
		secondsCounter = 0f;

		setDaemon(true);
	}

	@Override
	public void run() {
		while (!shouldStop.get()) {
			processMusicStep();
		}

		try {
			audioConsumer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		shouldStop.set(true);
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void processMusicStep() {
		final float stepBPS = bpsController.updateAndGetBPS(AudioConstants.STEP_DURATION_IN_SECONDS);

		final int beatNumber = (int) beatCounter;
		final float beatIncrementDuringMusicStep = AudioConstants.STEP_DURATION_IN_SECONDS * stepBPS;
		final float stepIntervalStartInBeats = beatCounter;

		List<TimedMusicalInstant> musicalInstantsActive = song.getMusicalInstantsActiveThisBeatInclusive(beatNumber);
		List<TimedSimpleSound> simpleSoundsActive = simpleSoundMaker.makeSimpleSounds(musicalInstantsActive, stepBPS, stepIntervalStartInBeats, secondsCounter);

		float[] nextSample = sampler.makeSamples(simpleSoundsActive, AudioConstants.SAMPLES_PER_STEP, secondsCounter);
		audioConsumer.writeSamples(nextSample);

		beatCounter += beatIncrementDuringMusicStep;
		secondsCounter += AudioConstants.STEP_DURATION_IN_SECONDS;
	}

	public VolumeListener getVolumeListener() {
		return sampler;
	}
}
