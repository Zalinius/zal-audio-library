package com.darzalgames.zalaudiolibrary.composing;

import com.darzalgames.darzalcommon.math.Fraction;

public record NoteDuration(Fraction inBeats) {

	public static final NoteDuration SIXTEENTH = new NoteDuration(new Fraction(1, 4));
	public static final NoteDuration EIGHTH = new NoteDuration(new Fraction(1, 2));
	public static final NoteDuration QUARTER = new NoteDuration(1);
	public static final NoteDuration HALF = new NoteDuration(2);
	public static final NoteDuration WHOLE = new NoteDuration(4);

	public static final NoteDuration EIGHTH_DOT = new NoteDuration(new Fraction(3, 4));
	public static final NoteDuration QUARTER_DOT = new NoteDuration(new Fraction(3, 2));
	public static final NoteDuration HALF_DOT = new NoteDuration(3);
	public static final NoteDuration WHOLE_DOT = new NoteDuration(6);

	public static final NoteDuration TWELTH = new NoteDuration(new Fraction(1, 3));
	public static final NoteDuration SIXTH = new NoteDuration(new Fraction(2, 3));
	public static final NoteDuration THIRD = new NoteDuration(new Fraction(4, 3));

	public NoteDuration(int durationInBeats) {
		this(new Fraction(durationInBeats));
	}
}
