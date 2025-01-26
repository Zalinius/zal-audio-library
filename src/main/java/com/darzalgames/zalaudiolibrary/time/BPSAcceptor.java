package com.darzalgames.zalaudiolibrary.time;

/**
 * A consumer-style interface for requesting speed changes to a music engine
 */
public interface BPSAcceptor {
	/**
	 * Request a change to the speed of a music engine over a linear transition period.
	 * @param bps The speed in beats per second
	 * @param transitionTime the time the change should occur over, in seconds
	 */
	void requestChangeBPS(float bps, float transitionTime);
	
}
