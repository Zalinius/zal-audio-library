package com.darzalgames.zalaudiolibrary.composing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.effects.tracking.MusicalEffect;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

public class ChordTrack implements Track {

	private final List<SequentialTrack> innerTracks;
	private final String songName;
	private final String trackName;
	private final Instrument instrument;
	private final float amplitude;

	public ChordTrack(String songName, String trackName, Instrument instrument, float amplitude) {
		innerTracks = new ArrayList<>();
		innerTracks.add(new SequentialTrack(songName, trackName, instrument, amplitude));
		this.songName = songName;
		this.trackName = trackName;
		this.instrument = instrument;
		this.amplitude = amplitude;
	}

	public void addNote(NoteDuration duration, Pitch... pitchArray) {
		List<Pitch> pitches = Arrays.asList(pitchArray);
		createInnerTracks(pitches.size());
		catchUpTracks();

		for (int i = 0; i < pitches.size(); i++) {
			SequentialTrack track = innerTracks.get(i);
			track.addNote(duration, pitches.get(i));
		}

		catchUpTracks();

	}

	public void addSilence(NoteDuration noteDuration) {
		addNote(noteDuration, Pitch.NONE);
	}

	private void createInnerTracks(int count) {
		for (int i = 0; i < count; i++) {
			if (i >= innerTracks.size() - 1) {
				SequentialTrack sequentialTrack = new SequentialTrack(songName, trackName + " i", instrument, amplitude);
				innerTracks.add(sequentialTrack);
			}
		}
	}

	private void catchUpTracks() {
		Fraction currentLengthInBeats = lengthInBeats();
		innerTracks.forEach(track -> {
			if (track.lengthInBeats().isLessThan(currentLengthInBeats)) {
				Fraction beatsToCatchup = Fraction.subtract(currentLengthInBeats, track.lengthInBeats());
				track.padWithSilence(beatsToCatchup);
			}
		});
	}

	@Override
	public void padWithSilence(Fraction beats) {
		addSilence(new NoteDuration(beats));
	}

	public Fraction lengthInBeats() {
		return innerTracks.get(0).lengthInBeats();
	}

	@Override
	public List<TimedMusicalInstant> getMusicalInstantsActiveThisBeatInclusive(int startBeat) {
		return innerTracks
				.stream()
				.map(track -> track.getMusicalInstantsActiveThisBeatInclusive(startBeat))
				.flatMap(List::stream)
				.toList();
	}

	@Override
	public void addMusicalEffect(MusicalEffect musicalEffect) {
		innerTracks.forEach(track -> track.addMusicalEffect(musicalEffect));
	}

	@Override
	public boolean isValid() {
		boolean individualTracksValid = innerTracks.stream().allMatch(SequentialTrack::isValid);
		Fraction trackLength = lengthInBeats();
		boolean allTracksSameLength = innerTracks.stream().allMatch(track -> track.lengthInBeats().equals(trackLength));
		return individualTracksValid && allTracksSameLength;
	}

}
