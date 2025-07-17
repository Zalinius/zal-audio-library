package com.darzalgames.zalaudiolibrary.pipeline;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import com.darzalgames.zalaudiolibrary.pipeline.composing.Song;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSoundMaker;
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

	private final Iterator<MusicalInstant> song;
	private final SimpleSoundMaker simpleSoundMaker;
	private final SampleMaker sampler; //Creates Samples from Simple Sounds
	private final AudioConsumer audioConsumer; //receives Samples

	private final float bps = 1f;

	public AudioPipeline(Song song, AudioConsumer audioConsumer) {
		shouldStop = new AtomicBoolean(false);
		this.song = song.iterator();
		simpleSoundMaker = new SimpleSoundMaker();
		sampler = new SampleMaker();
		this.audioConsumer = audioConsumer;

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
		MusicalInstant nextMusicalInstant = song.next();
		SimpleSound nextSimpleSound = simpleSoundMaker.makeSimpleSound(nextMusicalInstant, bps);
		float[] nextSample = sampler.makeSamples(nextSimpleSound);
		audioConsumer.writeSamples(nextSample);
	}

}
