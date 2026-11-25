package com.darzalgames.zalaudiolibrary.pipeline;

public interface AudioActor {

	/**
	 *
	 * @param delta the change in seconds
	 */
	void update(float delta);

	/**
	 *
	 * @param newBps the latest bps
	 */
	void updateBps(float newBps);

	/**
	 *
	 * @param beatNumber the beat number that was just changed to
	 */
	void updateBeat(int beatNumber);

}
