package com.darzalgames.zalaudiolibrary.synth.complex.trumpetExperiment;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;

public class ArbitrarySustainedEnvelope implements Envelope {

	private final NavigableMap<Float, Float> points;
	private final int sustainIndex;
	private final float preSustainDuration;
	private final float postSustainDuration;
	private final float sustainLevel;

	/**
	 * Creates an ArbitraryEnvelope
	 * @param envelopePoints A list of time-amplitude pairs, in seconds and normalized amplitude
	 */
	public ArbitrarySustainedEnvelope(List<Tuple<Float, Float>> envelopePoints, int sustainIndex) {
		points = new TreeMap<>();
		envelopePoints.forEach(point -> points.put(point.e(), point.f()));
		this.sustainIndex = sustainIndex;
		preSustainDuration = envelopePoints.get(sustainIndex).e();
		postSustainDuration = envelopePoints.getLast().e() - preSustainDuration;
		sustainLevel = envelopePoints.get(sustainIndex).f();
	}

	@Override
	public float getEnvelope(float envelopeDuration, float currentTime) {
		float sustainTime = envelopeDuration - preSustainDuration - postSustainDuration;
		if(points.floorKey(currentTime) == null) {
			return 0f;
		}
		if((currentTime > preSustainDuration + sustainTime) && (points.ceilingKey(currentTime - sustainTime) == null)) {
			return 0f;
		}

		if(currentTime >= preSustainDuration && currentTime <= preSustainDuration + sustainTime) {
			return sustainLevel;
		}
		else if(currentTime < preSustainDuration || currentTime > preSustainDuration + sustainTime) {
			if(currentTime > preSustainDuration + sustainTime && sustainTime >0) {
				currentTime = currentTime-sustainTime;
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
		else {
			return sustainLevel;
		}

	}

}
