package com.darzalgames.zalaudiolibrary.demosongs;

import java.util.ArrayList;
import java.util.Collection;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.EnvelopeReverser;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class TrainSong extends Song {

	private final Collection<SequentialTrack> reversibleTracks;

	public TrainSong() {
		super("train", 10);
		reversibleTracks = new ArrayList<>();

		SequentialTrack bassDrumTrack = new SequentialTrack(getSongName(), "bassDrum", Instrument.kickDrum(0.2f), 0.5f);
		addTrack(bassDrumTrack);

		bassDrumTrack.addNote(NoteDuration.QUARTER, Pitch.B2);
		bassDrumTrack.addSilence(NoteDuration.QUARTER);
		bassDrumTrack.addNote(NoteDuration.QUARTER, Pitch.B2);
		bassDrumTrack.addSilence(NoteDuration.QUARTER);

		SequentialTrack highSnareTrack = new SequentialTrack(getSongName(), "highSnare", new Instrument(SynthFactory.brownianNoise(0.1f), ArEnvelope.quadratic(0.01f, 0.09f)), 0.1f);
		SequentialTrack lowSnareTrack = new SequentialTrack(getSongName(), "lowSnare", new Instrument(SynthFactory.brownianNoise(0.5f), ArEnvelope.quadratic(0.01f, 0.14f)), 0.2f);
		addTrack(highSnareTrack);
		addTrack(lowSnareTrack);
		highSnareTrack.addNote(NoteDuration.QUARTER, Pitch.NONE);
		highSnareTrack.addNote(NoteDuration.QUARTER, Pitch.NONE);
		highSnareTrack.addSilence(NoteDuration.QUARTER);
		highSnareTrack.addSilence(NoteDuration.QUARTER);

		lowSnareTrack.addSilence(NoteDuration.QUARTER);
		lowSnareTrack.addSilence(NoteDuration.QUARTER);
		lowSnareTrack.addNote(NoteDuration.QUARTER, Pitch.NONE);
		lowSnareTrack.addNote(NoteDuration.QUARTER, Pitch.NONE);

		SequentialTrack bassTrack = new SequentialTrack(getSongName(), "bassBacking", new Instrument(SynthFactory.bandLimitedSawTooth(3), TriangleEnvelope.linear(0.1f)), 0.2f);
		addTrack(bassTrack);
		reversibleTracks.add(bassTrack);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.E2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C2);
		bassTrack.addSilence(NoteDuration.HALF);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.F2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C2);
		bassTrack.addSilence(NoteDuration.HALF);
		bassTrack.addNote(NoteDuration.HALF, Pitch.E2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.E2);
		bassTrack.addSilence(NoteDuration.HALF);
		bassTrack.addNote(NoteDuration.HALF, Pitch.F2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.C2);
		bassTrack.addNote(NoteDuration.HALF, Pitch.F2);
		bassTrack.addSilence(NoteDuration.HALF);

//		bassDrumTrack.addMusicalEffect(new EnvelopeReverser());
//		highSnareTrack.addMusicalEffect(new EnvelopeReverser());
//		lowSnareTrack.addMusicalEffect(new EnvelopeReverser());
		bassTrack.addMusicalEffect(new EnvelopeReverser());

	}

}
