package com.darzalgames.zalaudiolibrary.composing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;

import com.darzalgames.darzalcommon.data.ListFactory;
import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;
import com.darzalgames.zalaudiolibrary.synth.complex.ComplexSynth;
import com.darzalgames.zalaudiolibrary.synth.complex.Partial;

public class CompositeTrack implements Track {

	private final List<SequentialTrack> tracks;
	private final List<Partial> partials;

	public CompositeTrack(ComplexSynth complexSynth, String songName, String trackName, float amplitude) {
		tracks = new ArrayList<>();
		partials = complexSynth.makePartials();
		for (Iterator<Partial> it = partials.iterator(); it.hasNext();) {
			Partial partial = it.next();
			SequentialTrack track = new SequentialTrack(songName, trackName + ":" + partial.getPartialIndex(), new Instrument(partial.getSynth(), partial.getEnvelope()), partial.getAmplitude() * amplitude);
			tracks.add(track);
		}
	}

	public void addNote(NoteDuration duration, Pitch pitch) {
		for (int i = 0; i < tracks.size(); i++) {
			Pitch partialPitch = partials.get(i).getPartialPitch(pitch);
			tracks.get(i).addNote(duration, partialPitch);
		}
	}

	public void addSilence(NoteDuration duration) {
		tracks.forEach(track -> track.addSilence(duration));
	}

	@Override
	public void padWithSilence(Fraction beats) {
		addSilence(new NoteDuration(beats));
	}

	@Override
	public void addMusicalEffect(MusicalEffect musicalEffect) {
		tracks.forEach(track -> track.addMusicalEffect(musicalEffect));
	}

	public void addMusicalEffect(IntFunction<MusicalEffect> indexedMusicalEffect) {
		for (int i = 0; i < partials.size(); i++) {
			MusicalEffect musicalEffect = indexedMusicalEffect.apply(i);
			tracks.get(i).addMusicalEffect(musicalEffect);
		}
	}

	@Override
	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat) {
		List<List<TimedMusicalInstant>> allPartialInstants = tracks.stream().map(track -> track.getMusicalInstantsActiveThisBeatInclusive(startBeat)).toList();

		return ListFactory.flattenListOfLists(allPartialInstants);
	}

	@Override
	public boolean isValid() {
		boolean nestedTracksValid = tracks.stream().allMatch(Track::isValid);
		boolean tracksSyncedWithPartials = !tracks.isEmpty() && tracks.size() == partials.size();
		return nestedTracksValid && tracksSyncedWithPartials;
	}

}
