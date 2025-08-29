package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.*;
import java.util.function.Function;

import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class OvertoneEffect implements MusicalEffect {

	private final Function<Pitch, Set<Pitch>> chordFunction;

	public OvertoneEffect(Function<Pitch, Set<Pitch>> chordFunction) {
		this.chordFunction = p -> {
			if(p == Pitch.NONE) {
				return Set.of(Pitch.NONE);
			}
			else {
				return chordFunction.apply(p);
			}

		};
	}

	@Override
	public List<MusicalInstant> apply(MusicalInstant original) {
		Pitch originalPitch = original.pitch();
		SortedSet<Pitch> sortedPitches = new TreeSet<>(chordFunction.apply(originalPitch));
		List<MusicalInstant> modifiedMusicalInstants = new ArrayList<>();

		Iterator<Pitch> pitchIt = sortedPitches.iterator();
		Pitch fundamentalPitch = pitchIt.next();

		modifiedMusicalInstants.add(new MusicalInstant(original.synth(), fundamentalPitch, original.duration(), original.envelope(), original.amplitude(), original.id()));
		int overtoneIndex = 1;
		while (pitchIt.hasNext()) {
			Pitch pitch = pitchIt.next();
			modifiedMusicalInstants.add(new MusicalInstant(original.synth(), pitch, original.duration(), original.envelope(), original.amplitude(), original.id() + " overtone " + overtoneIndex));
			overtoneIndex++;
		}

		return modifiedMusicalInstants;
	}

	public static OvertoneEffect octaveChord() {
		return new OvertoneEffect(p -> Set.of(p, p.octaveUp()));
	}

}
