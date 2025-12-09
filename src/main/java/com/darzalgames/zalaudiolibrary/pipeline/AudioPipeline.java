package com.darzalgames.zalaudiolibrary.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.VolumeListener;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.validation.CompositionError;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSoundMaker;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.TimedSimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.AudioConsumer;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.SampleMaker;

/**
 * An audio pipeline with multiple steps
 * <ol type="1">
 * <li>Composition</li>
 * <li>Musical Instants</li>
 * <li>Simple Sounds</li>
 * <li>Samples sent to Audio Consumer</li>
 * </ol>
 */
public class AudioPipeline extends Thread {

	private final AtomicBoolean shouldStop;

	private final SimpleSoundMaker simpleSoundMaker;
	private final SampleMaker sampler; // Creates Samples from Simple Sounds
	private final AudioConsumer audioConsumer; // receives Samples
	private final BpsController bpsController;

	private Song song;
	private float beatCounter;
	private float secondsCounter;

	private final Collection<AudioActor> audioActors;

	public AudioPipeline(AudioConsumer audioConsumer, float musicVolume, float soundVolume) {
		this(audioConsumer, musicVolume, soundVolume, List.of());
	}

	public AudioPipeline(AudioConsumer audioConsumer, float musicVolume, float soundVolume, Collection<AudioActor> audioActors) {
		shouldStop = new AtomicBoolean(false);
		bpsController = new BpsController(1f);
		simpleSoundMaker = new SimpleSoundMaker();
		sampler = new SampleMaker(musicVolume, soundVolume);
		this.audioConsumer = audioConsumer;

		beatCounter = 0f;
		secondsCounter = 0f;

		this.audioActors = new ArrayList<>(audioActors);

		setDaemon(true);
	}

	public void addAudioActor(AudioActor audioActor) {
		audioActors.add(audioActor);
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
			Tuple<List<String>, Float> maxPeak = sampler.getMaxPeak();
			if (maxPeak.f() > 1f) {
				System.out.println("Music Thread Stopped. PEAKING! Max: " + maxPeak.f() + " at " + maxPeak.e());
			} else {
				System.out.println("Music Thread Stopped. Max Peak: " + maxPeak.f() + " at " + maxPeak.e());
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Tuple<List<String>, Float> getMaxPeak() {
		return sampler.getMaxPeak();
	}

	public void processMusicStep() {
		final float stepBPS = bpsController.updateAndGetBPS(AudioConstants.STEP_DURATION_IN_SECONDS);

		final int beatNumber = (int) beatCounter;
		final float beatIncrementDuringMusicStep = AudioConstants.STEP_DURATION_IN_SECONDS * stepBPS;
		final float stepIntervalStartInBeats = beatCounter;

		audioActors.forEach(actor -> actor.update(AudioConstants.STEP_DURATION_IN_SECONDS));

		List<TimedMusicalInstant> musicalInstantsActive = song.getMusicalInstantsActiveThisBeatInclusive(beatNumber);
		List<TimedSimpleSound> simpleSoundsActive = simpleSoundMaker.makeSimpleSounds(musicalInstantsActive, stepBPS, stepIntervalStartInBeats, secondsCounter);

		float[] nextSample = sampler.makeSamples(simpleSoundsActive, AudioConstants.SAMPLES_PER_STEP, secondsCounter, song.getSampleEffects());
		audioConsumer.writeSamples(nextSample);

		beatCounter += beatIncrementDuringMusicStep;
		secondsCounter += AudioConstants.STEP_DURATION_IN_SECONDS;
	}

	public void changeSong(Song newSong) {
		newSong.setBpsAcceptor(bpsController);
		bpsController.resetBPS(newSong.getInitialBps());
		song = newSong;
		List<CompositionError> songErrors = song.validate();
		if (!songErrors.isEmpty()) {
			StringBuilder sb = new StringBuilder("Song invalid: " + song.getSongName() + ", errors: " + songErrors.size());
			songErrors.forEach(error -> sb.append("\n" + error.getError()));
			throw new IllegalArgumentException(sb.toString());
		}
	}

	public float getBeatCounter() {
		return beatCounter;
	}

	public VolumeListener getVolumeListener() {
		return sampler;
	}
}
