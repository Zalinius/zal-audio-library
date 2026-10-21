package com.darzalgames.zalaudiolibrary.demosongs.other;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

import com.darzalgames.darzalcommon.random.RandomExtended;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AsrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.TriangleEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.EnvelopeReverser;
import com.darzalgames.zalaudiolibrary.effects.tracking.OvertoneEffect;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;

public class ScratchPadSong extends Song {

	private final SequentialTrack mainTrack;
	private final SequentialTrack brownNoise;
	private final SequentialTrack wubNoise;
	private final SequentialTrack heartBeat1;
	private final SequentialTrack heartBeat2;

	public final static Instrument HARPSICHORD = new Instrument(
			SynthFactory.pulse(.3f), AdrEnvelope.quadratic(.01f, .09f, .3f),
			t -> 1f - (t / 250)
	);

	public ScratchPadSong() {
		super("Heart Beat", 1f);
		float mainVolume = 0.1f;
		float noiseVolume = 0.01f;
		float drumVolume = 0.2f;

//		Synth synth = SynthFactory.multiplicativeSynthesis(SynthFactory.sine(), SynthFactory.triangle(), SynthFactory.saw());

		mainTrack = new SequentialTrack(getSongName(), "main", HARPSICHORD, mainVolume);
//		addTrack(mainTrack);

		mainTrack.addMusicalEffect(OvertoneEffect.tritoneDown());

		RandomExtended randomExtended = new RandomExtended(0);
		Set<Pitch> pitches = new LinkedHashSet<>(List.of(Pitch.B3, Pitch.D4, Pitch.F4, Pitch.B4, Pitch.D5, Pitch.F5));
		Pitch lastPitch = Pitch.NONE;

		for (int i = 0; i <= 32; i++) {
			Set<Pitch> nonRepeatedPitches = new LinkedHashSet<>(pitches);
			nonRepeatedPitches.remove(lastPitch);
			NoteDuration noteDuration = new NoteDuration(randomExtended.nextInt(8, 16));
			Pitch pitch = randomExtended.getRandomElement(nonRepeatedPitches);
			lastPitch = pitch;

			mainTrack.addNote(noteDuration, pitch);
		}
//		mainTrack.addMusicalEffect(new SynthChanger(synth));

		//
//		Synth bassSynth = SynthFactory.rationalFrequencyModulator(new Fraction(2, 1), 1f);
//		bassTrack = new SequentialTrack(getSongName(), "bass", new Instrument(bassSynth, ArEnvelope.quadratic(.01f, 0.49f)), bassVolume);
//		addTrack(bassTrack);
//		bassTrack.addMusicalEffect(new TransposeEffect(Pitch::octaveDown));
//
		heartBeat1 = new SequentialTrack(getSongName(), "kick drum 1", Instrument.kickDrum(0.3f), drumVolume);
		heartBeat2 = new SequentialTrack(getSongName(), "kick drum 2", Instrument.kickDrum(0.2f), drumVolume);
//		addTrack(heartBeat1);
//		addTrack(heartBeat2);
		heartBeat2.addSilence(NoteDuration.SIXTEENTH);
		heartBeat2.setRepetitionPoint();

		heartBeat1.addNote(NoteDuration.EIGHTH_DOT, Pitch.A2);
		heartBeat1.addNote(NoteDuration.EIGHTH_DOT, Pitch.A2);
		heartBeat1.addNote(NoteDuration.EIGHTH_DOT, Pitch.A2);
		heartBeat1.addNote(NoteDuration.EIGHTH_DOT, Pitch.A2);

		heartBeat2.addNote(NoteDuration.EIGHTH_DOT, Pitch.F2);
		heartBeat2.addNote(NoteDuration.EIGHTH_DOT, Pitch.F2);
		heartBeat2.addNote(NoteDuration.EIGHTH_DOT, Pitch.F2);
		heartBeat2.addNote(NoteDuration.EIGHTH_DOT, Pitch.F2);

		wubNoise = new SequentialTrack(getSongName(), "wub", wubDrum(4f), 0.3f);
		wubNoise.addMusicalEffect(new EnvelopeReverser());
		addTrack(wubNoise);
		Set<Pitch> wubPitches = new LinkedHashSet<>(List.of(Pitch.G2, Pitch.A2, Pitch.B2, Pitch.C3, Pitch.D3, Pitch.E3, Pitch.F3));
		Pitch lastWubPitch = Pitch.NONE;
		for (int i = 0; i <= 32; i++) {
			Set<Pitch> nonRepeatedPitches = new LinkedHashSet<>(wubPitches);
			nonRepeatedPitches.remove(lastWubPitch);
			NoteDuration noteDuration = new NoteDuration(randomExtended.nextInt(1, 5));
			Pitch pitch = randomExtended.getRandomElement(nonRepeatedPitches);
			lastWubPitch = pitch;

			wubNoise.addNote(noteDuration, pitch);
		}

		Envelope noiseEnvelope = AsrEnvelope.linear(0.1f, 0.1f);
		brownNoise = new SequentialTrack(getSongName(), "noise", new Instrument(SynthFactory.brownianNoise(0.9f), noiseEnvelope), noiseVolume);
//		addTrack(brownNoise);
		brownNoise.addNote(NoteDuration.DOUBLE_WHOLE, Pitch.NONE);
	}

	public static AlbumExportingInformation scratchAlbum() {
		AlbumExportingInformation albumExportingInformation = new AlbumExportingInformation("Scratch", 666);
		SongOrchestrator orchestrator = new SongOrchestrator(3) {
			@Override
			public void orchestrateSong() {
				processMeasures(16);
			}
		};
		albumExportingInformation.addSong(new ScratchPadSong(), orchestrator);
		return albumExportingInformation;
	}

	public static Instrument wubDrum(float duration) {
		float samplingDuration = duration * 2;
		Envelope envelope = TriangleEnvelope.quadratic(0.01f);
		Synth synth = SynthFactory.sine();
		UnaryOperator<Float> frequencyModulator = decayingFrequencyModulator(samplingDuration);
		return new Instrument(synth, envelope, frequencyModulator);

	}

	private static UnaryOperator<Float> decayingFrequencyModulator(float duration) {
		return t -> 1f - (t / duration);
	}

}
