package com.darzalgames.zalaudiolibrary.composing;

import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

/**
 * A convenience class containing the natural pitches from western musical theory
 */
public class Pitch implements Comparable<Pitch>{

	private final String name;
	private final float frequency;

	private static final NavigableSet<Pitch> naturalPitches = new TreeSet<>();

	public static final Pitch NONE = new Pitch("None", 0f);

	public static final Pitch C0 = new Pitch("C0", 16.35f);
	public static final Pitch D0 = new Pitch("D0", 18.35f);
	public static final Pitch E0 = new Pitch("E0", 20.60f);
	public static final Pitch F0 = new Pitch("F0", 21.83f);
	public static final Pitch G0 = new Pitch("G0", 24.50f);
	public static final Pitch A0 = new Pitch("A0", 27.50f);
	public static final Pitch B0 = new Pitch("B0", 30.87f);

	public static final Pitch C1 = new Pitch("C1", 32.70f);
	public static final Pitch D1 = new Pitch("D1", 36.71f);
	public static final Pitch E1 = new Pitch("E1", 41.20f);
	public static final Pitch F1 = new Pitch("F1", 43.65f);
	public static final Pitch G1 = new Pitch("G1", 49.00f);
	public static final Pitch A1 = new Pitch("A1", 55.00f);
	public static final Pitch B1 = new Pitch("B1", 61.74f);

	public static final Pitch C2 = new Pitch("C2", 65.41f);
	public static final Pitch D2 = new Pitch("D2", 73.42f);
	public static final Pitch E2 = new Pitch("E2", 82.41f);
	public static final Pitch F2 = new Pitch("F2", 87.31f);
	public static final Pitch G2 = new Pitch("G2", 98.00f);
	public static final Pitch A2 = new Pitch("A2",110.00f);
	public static final Pitch B2 = new Pitch("B2",123.47f);

	public static final Pitch C3 = new Pitch("C3",130.81f);
	public static final Pitch D3 = new Pitch("D3",146.83f);
	public static final Pitch E3 = new Pitch("E3",164.81f);
	public static final Pitch F3 = new Pitch("F3",174.61f);
	public static final Pitch G3 = new Pitch("G3",196.00f);
	public static final Pitch A3 = new Pitch("A3",220.00f);
	public static final Pitch B3 = new Pitch("B3",246.94f);

	public static final Pitch C4 = new Pitch("C4",261.63f);
	public static final Pitch D4 = new Pitch("D4",293.66f);
	public static final Pitch E4 = new Pitch("E4",329.63f);
	public static final Pitch F4 = new Pitch("F4",349.23f);
	public static final Pitch G4 = new Pitch("H4",392.00f);
	public static final Pitch A4 = new Pitch("A4",440.00f);
	public static final Pitch B4 = new Pitch("B4",493.88f);

	public static final Pitch C5 = new Pitch("C5",523.25f);
	public static final Pitch D5 = new Pitch("D5",587.33f);
	public static final Pitch E5 = new Pitch("E5",659.25f);
	public static final Pitch F5 = new Pitch("F5",698.46f);
	public static final Pitch G5 = new Pitch("G5",783.99f);
	public static final Pitch A5 = new Pitch("A5",880.00f);
	public static final Pitch B5 = new Pitch("B5",987.77f);

	public static final Pitch C6 = new Pitch("C6",1046.50f);
	public static final Pitch D6 = new Pitch("D6",1174.66f);
	public static final Pitch E6 = new Pitch("E6",1318.51f);
	public static final Pitch F6 = new Pitch("F6",1396.91f);
	public static final Pitch G6 = new Pitch("G6",1567.98f);
	public static final Pitch A6 = new Pitch("A6",1760.00f);
	public static final Pitch B6 = new Pitch("B6",1975.53f);

	public static final Pitch C7 = new Pitch("C7",2093.00f);
	public static final Pitch D7 = new Pitch("D7",2349.30f);
	public static final Pitch E7 = new Pitch("E7",2637.00f);
	public static final Pitch F7 = new Pitch("F7",2793.80f);
	public static final Pitch G7 = new Pitch("G7",3136.00f);
	public static final Pitch A7 = new Pitch("A7",3520.00f);
	public static final Pitch B7 = new Pitch("B7",3951.00f);

	public static final Pitch C8 = new Pitch("C8",4186.00f);


	private Pitch(String name, float frequency) {
		this(name, frequency, true);
	}
	private Pitch(String name, float frequency, boolean natural) {
		this.name = name;
		this.frequency = frequency;
		if(natural) {
			naturalPitches.add(this);
		}
	}

	/**
	 * Create a custom runtime pitch
	 * @param name The name of the pitch
	 * @param frequency the frequency in hertz (hz)
	 * @return A pitch object with the given name an pitch
	 */
	public static Pitch makePitch(String name, float frequency) {
		return new Pitch(name, frequency, false);
	}

	/**
	 * Gets the natural note above this one
	 * @return the pitch one note above this one, or NONE if the resulting pitch is too high
	 */
	public Pitch up() {
		if(this == NONE) {
			return NONE;
		}
		Pitch higherPitch = naturalPitches.higher(this);
		if(higherPitch == null) {
			return NONE;
		}
		return higherPitch;
	}

	/**
	 * Gets the natural note below this one
	 * @return the pitch one note below this one, or NONE if the resulting pitch is too low
	 */
	public Pitch down() {
		if(this == NONE) {
			return NONE;
		}
		Pitch lowerPitch = naturalPitches.lower(this);
		if(lowerPitch == null) {
			return NONE;
		}
		return lowerPitch;
	}

	/**
	 * Gets the pitch an octave higher
	 * @return the pitch one octave above this one, or NONE if the resulting pitch is too high
	 */
	public Pitch octaveUp() {
		return up().up().up().up().up().up().up();
	}

	/**
	 * Gets the pitch an octave lower
	 * @return the pitch one octave below this one, or NONE if the resulting pitch is too low
	 */
	public Pitch octaveDown() {
		return down().down().down().down().down().down().down();
	}


	/**
	 * The frequency of the pitch
	 * @return the frequency in hertz (hz)
	 */
	public float getFrequency() {
		return frequency;
	}

	/**
	 * The name of the pitch
	 * @return the name in scientific pitch notation
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "("+ frequency + "hz)";
	}

	@Override
	public int hashCode() {
		return Objects.hash(frequency);
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
		Pitch other = (Pitch) obj;
		return Float.floatToIntBits(frequency) == Float.floatToIntBits(other.frequency);
	}
	@Override
	public int compareTo(Pitch other) {
		return Float.compare(frequency, other.frequency);
	}


}