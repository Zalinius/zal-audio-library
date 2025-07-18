package com.darzalgames.zalaudiolibrary.pipeline.composing;

import java.util.*;
import java.util.Map.Entry;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.ZeroEnvelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public abstract class Track {

	private final NavigableMap<Fraction, MusicalInstant> trackMelody;


	public Track() {
		trackMelody = new TreeMap<>();
	}

	public void addNote(Synth synth, NoteDuration duration, Pitch pitch, Envelope envelope) {
		MusicalInstant newInstant = new MusicalInstant(synth, duration, pitch, envelope);
		Fraction newInstantStartBeat = lengthInBeats();
		trackMelody.put(newInstantStartBeat, newInstant);
	}

	public void addSilence(NoteDuration duration) {
		addNote(Synth.zero(), duration, Pitch.NONE, new ZeroEnvelope());
	}

	public Fraction lengthInBeats() {
		return Fraction.add(trackMelody.lastKey(), trackMelody.lastEntry().getValue().getDuration().getDurationInBeats());
	}

	public Collection<TimedMusicalInstant> getMusicalInstantsActiveThisBeatOrNextBeat(int startBeat){
		if(trackMelody.isEmpty()) {
			throw new IllegalStateException("Can't call getMusicalInstantsActiveNow when Track melody empty");
		}

		Fraction currentBeat = new Fraction(startBeat);
		while(currentBeat.isLesserThan(lengthInBeats())) {
			currentBeat = Fraction.subtract(currentBeat, lengthInBeats());
		}

		List<TimedMusicalInstant> allActiveInstants = new ArrayList<>();

		Entry<Fraction, MusicalInstant> instantAtOrBeforeBeat = trackMelody.floorEntry(currentBeat);






		allActiveInstants.add(null);

		return allActiveInstants;
	}



}
