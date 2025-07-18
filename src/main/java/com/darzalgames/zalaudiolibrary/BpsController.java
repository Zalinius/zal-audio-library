package com.darzalgames.zalaudiolibrary;

public class BpsController {

	private float targetBps;

	private float lastBps;
	private float transitionDuration;
	private float timeInTransition;

	public BpsController(float initialBps) {
		targetBps = initialBps;
		lastBps = initialBps;
		transitionDuration = 0f;
		timeInTransition = 0f;
	}

	public synchronized void setTargetBPS(float newTargetBps, float transitionTime) {
		lastBps = targetBps;
		targetBps = newTargetBps;
		transitionDuration = transitionTime;
		timeInTransition = 0f;
	}

	public synchronized float updateAndGetBPS(float delta) {
		if(timeInTransition != transitionDuration) {

			timeInTransition += delta;
			if(timeInTransition > transitionDuration) {
				timeInTransition = transitionDuration;
			}
			float interpolant = timeInTransition / transitionDuration;
			return (targetBps - lastBps) * interpolant + lastBps;
		}
		else {
			return targetBps;
		}
	}

	public synchronized void resetBPS(float bps) {
		setTargetBPS(bps, 0);
	}

	public synchronized float getBPS() {
		return updateAndGetBPS(0);
	}


}
