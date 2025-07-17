package com.darzalgames.zalaudiolibrary.composing.time;

import com.darzalgames.darzalcommon.math.Fraction;

/**
 * An immutable instant in musical time, unrelated to real time.
 * Defined by a time signature, the number of sixteenths in a beat, and an amount of sixteenths
 */
public class TimeInstant {

	private final Fraction beats;

	/**
	 * Creates a Time Instant in with the specified time signature
	 * @param beats The total beats at this instant
	 */
	public TimeInstant(Fraction beats) {
		this.beats = beats;
	}

	/**
	 * The absolute position of this instant in beats
	 * @return The number of beats in this time instant
	 */
	public Fraction getTotalBeats() {
		return beats;
	}

	/**
	 * Checks if this time instant corresponds to the very beginning of a measure
	 * @return Whether this instant is the first note in a measure
	 */
	public boolean isStartOfMeasure() {
		return beats.isInteger() && beats.numerator() % 4 == 0;
	}

	/**
	 * Checks if this time instant corresponds to the very beginning of a beat
	 * @return True if this TimeInstant is at the start of a beat, False otherwise
	 */
	public boolean isStartOfBeat() {
		return beats.isInteger();
	}

}
