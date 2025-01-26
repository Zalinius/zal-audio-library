package com.darzalgames.zalaudiolibrary.time;

import java.util.Objects;

/**
 * An immutable instant in musical time, unrelated to real time.
 * Defined by a time signature, the number of sixteenths in a beat, and an amount of sixteenths
 */
public class TimeInstant {
	
	private static final int DEFAULT_SIXTEENTHS_PER_BEAT = 4;
	
	private final TimeSignature timeSignature;
	private final int sixteenthsPerBeat;

	private final int totalSixteenths;

	/**
	 * Creates a Time Instant in 4/4 time
	 * @param totalSixteenths The total sixteenths at this instant
	 */
	public TimeInstant(int totalSixteenths) {
		this(totalSixteenths, TimeSignature.TIME_4_4);
	}

	/**
	 * Creates a Time Instant in with the specified time signature
	 * @param timeSignature The TimeSignature used to interpret this instant
	 * @param totalSixteenths The total sixteenths at this instant
	 */
	public TimeInstant(int totalSixteenths, TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
		this.sixteenthsPerBeat = DEFAULT_SIXTEENTHS_PER_BEAT;
		this.totalSixteenths = totalSixteenths;
	}
	
	/**
	 * @return A time instant one sixteenth later
	 */
	public TimeInstant next() {
		return new TimeInstant(totalSixteenths + 1, timeSignature);
	}
	
	/**
	 * @return The total sixteenths at this instant
	 */
	public int getTotalSixteenths() {
		return totalSixteenths;
	}
	
	/**
	 * @return The number of beats in this time instant, rounded down
	 */
	public int getTotalBeats() {
		return totalSixteenths/sixteenthsPerBeat;
	}
	
	//TODO this works, but is wrong. The 15 is especially problematic, and is compensating for issue with the new music pipeline
	/**
	 * @return Whether this instant is the first note in a measure
	 */
	public boolean isStartOfMeasure() {
		return totalSixteenths % (timeSignature.getBeatsPerMeasure() * DEFAULT_SIXTEENTHS_PER_BEAT) == 0; //TODO account for other time signatures
	}
	
	/**
	 * @return True if this TimeInstant is at the start of a beat, False otherwise
	 */
	public boolean isStartOfBeat() {
		return totalSixteenths % sixteenthsPerBeat == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalSixteenths);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeInstant other = (TimeInstant) obj;
		return totalSixteenths == other.totalSixteenths;
	}
	
}
