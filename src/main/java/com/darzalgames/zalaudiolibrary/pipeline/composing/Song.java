package com.darzalgames.zalaudiolibrary.pipeline.composing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.darzalcommon.data.LoopingIterator;
import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.AsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public abstract class Song implements Iterable<MusicalInstant>{

	private final List<MusicalInstant> melody;

	public Song() {
		melody = new ArrayList<>();
	}

	public void addNote(Synth synth, NoteDuration duration, Pitch pitch) {
		MusicalInstant newInstant = new MusicalInstant(synth, duration, pitch, new AsrEnvelope(0.05f, 1f, 0.05f));
		melody.add(newInstant);
	}

	public void addSilence(NoteDuration duration) {
		addNote(Synth.zero(), duration, Pitch.NONE);
	}

	public Fraction lengthInBeats() {
		return melody.stream().map(instant -> instant.getDuration().getDurationInBeats()).reduce(new Fraction(), Fraction::add);
	}

	@Override
	public Iterator<MusicalInstant> iterator() {
		return new LoopingIterator<>(melody);
	}



}
