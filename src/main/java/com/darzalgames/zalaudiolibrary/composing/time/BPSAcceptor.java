package com.darzalgames.zalaudiolibrary.composing.time;

/**
 * A consumer-style interface for requesting speed changes to a music engine
 */
public interface BPSAcceptor {

	/**
	 * Change the speed of the music engine over a linear transition period.
	 * @param bps The speed in beats per second
	 * @param transitionTime the time the change should occur over, in seconds
	 */
	void setTargetBPS(float bps, float transitionTime);

}
