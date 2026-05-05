package com.darzalgames.zalaudiolibrary.demosongs.rainbow;

import java.util.List;

import com.darzalgames.darzalcommon.functional.Do;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;

public abstract class AbstractRainbowSeedSong extends Song {

	private final SequentialTrack mainTrack;
	private final SequentialTrack secondaryTrack;

	AbstractRainbowSeedSong(String name) {
		super(name, 2f);

		mainTrack = new SequentialTrack(name, "main", getMainInstrument(), getMainAmplitude());
		mainMusicalEffects().forEach(mainTrack::addMusicalEffect);
		addTrack(mainTrack);

		secondaryTrack = new SequentialTrack(name, "secondary", getSecondaryInstrument(), getSecondaryAmplitude());
		secondaryMusicalEffects().forEach(secondaryTrack::addMusicalEffect);
		addTrack(secondaryTrack);

		addSecondaryMeasure(Pitch.C3, Pitch.A2);
		addSecondaryMeasure(Pitch.C3, Pitch.A2);

		addSecondaryMeasure(Pitch.B2, Pitch.G2);
		addSecondaryMeasure(Pitch.B2, Pitch.G2);

		addSecondaryMeasure(Pitch.A2, Pitch.F2);
		addSecondaryMeasure(Pitch.A2, Pitch.F2);

		addSecondaryMeasure(Pitch.B2, Pitch.G2);
		addSecondaryMeasure(Pitch.B2, Pitch.G2);

		addSecondaryMeasure(Pitch.C3, Pitch.E3);
		addSecondaryMeasure(Pitch.C3, Pitch.E3);

		addSecondaryMeasure(Pitch.B2, Pitch.D3);
		addSecondaryMeasure(Pitch.B2, Pitch.D3);

		addSecondaryMeasure(Pitch.A2, Pitch.C3);
		addSecondaryMeasure(Pitch.A2, Pitch.C3);

		addSecondaryMeasure(Pitch.B2, Pitch.D3);
		addSecondaryMeasure(Pitch.B2, Pitch.D3);

		addMainMeasure(Pitch.E4);
		addMainMeasure(Pitch.D4);
		addMainMeasure(Pitch.E4);
		addMainMeasure(Pitch.F4);

		addMainMeasure(Pitch.E4, Pitch.G4);
		addMainMeasure(Pitch.D4, Pitch.F4);
		addMainMeasure(Pitch.E4, Pitch.G4);
		addMainMeasure(Pitch.F4, Pitch.A4);

	}

	private void addMainMeasure(Pitch pitch, Pitch... chord) {
		getMainNoteDuration().forEach(note -> mainTrack.addNote(note, pitch, chord));
	}

	private void addSecondaryMeasure(Pitch first, Pitch second) {

		Do.xTimes(4, () -> {
			secondaryTrack.addNote(NoteDuration.EIGHTH, first);
			secondaryTrack.addNote(NoteDuration.EIGHTH, second);
		});

	}

	// main track
	public abstract Instrument getMainInstrument();

	public List<NoteDuration> getMainNoteDuration() {
		return List.of(NoteDuration.WHOLE);
	}

	public List<MusicalEffect> mainMusicalEffects() {
		return List.of();
	}

	public float getMainAmplitude() {
		return 0.3f;
	}

	// secondary track
	public abstract Instrument getSecondaryInstrument();

	public List<MusicalEffect> secondaryMusicalEffects() {
		return List.of();
	}

	public float getSecondaryAmplitude() {
		return 0.3f;
	}

}
