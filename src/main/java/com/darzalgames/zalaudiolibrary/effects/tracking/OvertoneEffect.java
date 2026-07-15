package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class OvertoneEffect implements MusicalEffect {

	private final Function<Pitch, Map<Pitch, Float>> chordFunction;
	private transient boolean enabled;

	public OvertoneEffect(Function<Pitch, Map<Pitch, Float>> chordFunction) {
		this.chordFunction = p -> {
			if (p == Pitch.NONE) {
				return Map.of(Pitch.NONE, 0f);
			} else {
				return chordFunction.apply(p);
			}
		};
		enabled = true;
	}

	public static OvertoneEffect evenOvertoneEffect(Function<Pitch, Set<Pitch>> chord) {
		Function<Pitch, Map<Pitch, Float>> chordFunction = p -> chord
				.apply(p)
				.stream()
				.collect(Collectors.toMap(pitch -> pitch, pitch -> 1f));

		return new OvertoneEffect(chordFunction);
	}

	public static OvertoneEffect tritoneDown() {
		Function<Pitch, Set<Pitch>> chord = original -> {
			Pitch tritoneNote = original.toneDown().toneDown().toneDown();
			return Set.of(original, tritoneNote);
		};
		return evenOvertoneEffect(chord);
	}

	@Override
	public List<MusicalInstant> apply(MusicalInstant original) {
		Pitch originalPitch = original.pitch();
		Map<Pitch, Float> pitchAmplitudes;
		if (isEnabled()) {
			pitchAmplitudes = chordFunction.apply(originalPitch);
		} else {
			pitchAmplitudes = Map.of(originalPitch, 1f);
		}
		SortedSet<Pitch> sortedPitches = new TreeSet<>(pitchAmplitudes.keySet());
		List<MusicalInstant> modifiedMusicalInstants = new ArrayList<>();

		Iterator<Pitch> pitchIt = sortedPitches.iterator();
		Pitch fundamentalPitch = pitchIt.next();

		modifiedMusicalInstants.add(new MusicalInstant(original.synth(), fundamentalPitch, original.frequencyModulator(), original.duration(), original.envelope(), original.amplitude() * pitchAmplitudes.get(fundamentalPitch), original.id()));
		int overtoneIndex = 1;
		while (pitchIt.hasNext()) {
			Pitch pitch = pitchIt.next();
			modifiedMusicalInstants.add(new MusicalInstant(original.synth(), pitch, original.frequencyModulator(), original.duration(), original.envelope(), original.amplitude() * pitchAmplitudes.get(pitch), original.id() + " overtone " + overtoneIndex));
			overtoneIndex++;
		}

		return modifiedMusicalInstants;
	}

	public static OvertoneEffect octaveChord() {
		return evenOvertoneEffect(p -> Set.of(p, p.octaveUp()));
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
