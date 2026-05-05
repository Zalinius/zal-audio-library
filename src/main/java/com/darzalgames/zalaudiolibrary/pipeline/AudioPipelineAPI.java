package com.darzalgames.zalaudiolibrary.pipeline;

import com.darzalgames.zalaudiolibrary.VolumeListener;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;

/**
 * A restrictive interface that exposes thread safe methods for the AudioPipeline
 */
public interface AudioPipelineAPI extends VolumeListener {

	void start();

	void shutdown();

	void addAudioActor(AudioActor audioActor);

	void requestChangeSong(Song newSong);

	void requestSoundEffect(SoundEffect soundEffect);

}
