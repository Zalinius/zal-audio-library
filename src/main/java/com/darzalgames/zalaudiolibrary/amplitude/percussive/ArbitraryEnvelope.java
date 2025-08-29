package com.darzalgames.zalaudiolibrary.amplitude.percussive;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.darzalgames.darzalcommon.data.Tuple;

public class ArbitraryEnvelope implements PercussiveEnvelope {

	private final NavigableMap<Float, Float> points;

	/**
	 * Creates an ArbitraryEnvelope
	 * @param envelopePoints A list of time-amplitude pairs, in seconds and normalized amplitude
	 */
	public ArbitraryEnvelope(List<Tuple<Float, Float>> envelopePoints) {
		points = new TreeMap<>();
		envelopePoints.forEach(point -> points.put(point.e(), point.f()));
	}


	@Override
	public float getEnvelope(float currentTime) {
		if(points.floorKey(currentTime) == null || points.ceilingKey(currentTime) == null) {
			return 0f;
		}

		float intervalStart = points.floorKey(currentTime);
		float startValue = points.get(intervalStart);
		float intervalEnd = points.ceilingKey(currentTime);
		float endValue = points.get(intervalEnd);

		if(intervalStart == intervalEnd) {
			return startValue;
		}

		float intervalProgress = (currentTime - intervalStart) / (intervalEnd - intervalStart);

		return startValue * (1-intervalProgress) + endValue * intervalProgress;
	}

}
