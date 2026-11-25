package com.darzalgames.zalaudiolibrary.demosongs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.InstrumentChanger;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class SynthTesterSong extends Song {

	private final List<Synth> synths;
	private final SequentialTrack mainTrack;

	public SynthTesterSong(Synth... synthsToUse) {
		this(Arrays.asList(synthsToUse));
	}

	public SynthTesterSong(Function<Float, Synth> interpolatedSynths) {
		this(interpolateSynths(interpolatedSynths, 8));
	}

	public SynthTesterSong(Supplier<Synth> synthSupplier) {
		this(interpolateSynths(a -> synthSupplier.get(), 8));
	}

	public SynthTesterSong(List<Synth> synths) {
		super("Synth Tester", 1f);
		this.synths = synths;

		Envelope percussive = ArEnvelope.quadratic(0.01f, 0.09f);
		Envelope sustained = AdsrEnvelope.quadratic(0.01f, 0.09f, 0.3f, 0.25f);

		Instrument blankInstrument = new Instrument(SynthFactory.zero(), ConstantEnvelope.zeroEnvelope());

		mainTrack = new SequentialTrack(getSongName(), "main", blankInstrument, 0.5f);
		addTrack(mainTrack);
		final Pitch baseNote = Pitch.C3;
		Pitch currentNote = baseNote;

		for (int i = 0; i != 2 * synths.size(); i++) {
			Synth toUse = synths.get(i % synths.size());
			if (i == 8) {
				currentNote = baseNote.octaveUp();
			}
			mainTrack.addNote(NoteDuration.QUARTER, new InstrumentChanger(new Instrument(toUse, percussive)), currentNote);

		}

		currentNote = baseNote;
		for (int i = 0; i != 2 * synths.size(); i++) {
			Synth toUse = synths.get(i % synths.size());
			if (i == 8) {
				currentNote = baseNote.octaveUp();
			}
			mainTrack.addNote(NoteDuration.QUARTER, new InstrumentChanger(new Instrument(toUse, sustained)), currentNote);
		}
	}

	private static List<Synth> interpolateSynths(Function<Float, Synth> interpolatedSynths, int count) {
		List<Synth> synths = new ArrayList<>();

		for (int i = 0; i != count; i++) {
			float interpolant = i / ((float) count - 1);
			synths.add(interpolatedSynths.apply(interpolant));
		}

		return synths;
	}

	public Synth getActiveSynth(int beat) {
		beat = beat % mainTrack.lengthInBeats().numerator();
		return synths.get(beat % synths.size());
	}

}
