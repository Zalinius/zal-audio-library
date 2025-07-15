package com.darzalgames.zalaudiolibrary.time;

import java.util.Objects;

/**
 * An immutable instant in musical time, unrelated to real time.
 * Defined by a time signature, the number of sixteenths in a beat, and an amount of sixteenths
 */
public class TimeInstant {

	private static final int DEFAULT_SIXTEENTHS_PER_BEAT = 4;

	private final TimeSignature timeSignature;
	private final int sixteenthsPerBeat;//TODO isnt this determined by the time signature?

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
		sixteenthsPerBeat = DEFAULT_SIXTEENTHS_PER_BEAT;
		this.totalSixteenths = totalSixteenths;
	}

	/**
	 * Obtains the time instant followint this one
	 * @return A time instant one sixteenth later
	 */
	public TimeInstant next() {
		return new TimeInstant(totalSixteenths + 1, timeSignature);
	}

	/**
	 * The absolute position of this instant in sixteenths
	 * @return The total sixteenths at this instant
	 */
	public int getTotalSixteenths() {
		return totalSixteenths;
	}

	/**
	 * The absolute position of this instant in beats, rounded down
	 * @return The number of beats in this time instant, rounded down
	 */
	public int getTotalBeats() {
		return totalSixteenths/sixteenthsPerBeat;
	}

	/**
	 * Checks if this time instant corresponds to the very beginning of a measure
	 * @return Whether this instant is the first note in a measure
	 */
	public boolean isStartOfMeasure() {
		return totalSixteenths % (timeSignature.getBeatsPerMeasure() * DEFAULT_SIXTEENTHS_PER_BEAT) == 0; //TODO account for other time signatures
	}

	/**
	 * Checks if this time instant corresponds to the very beginning of a beat
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TimeInstant other = (TimeInstant) obj;
		return totalSixteenths == other.totalSixteenths;
	}

}
