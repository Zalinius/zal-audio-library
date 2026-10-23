package com.darzalgames.zalaudiolibrary.demosongs.thedoor;

import com.darzalgames.darzalcommon.functional.Do;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.*;
import com.darzalgames.zalaudiolibrary.composing.tracks.CompositeTrack;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.EnvelopeReverser;
import com.darzalgames.zalaudiolibrary.effects.tracking.OvertoneEffect;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;
import com.darzalgames.zalaudiolibrary.synth.complex.BellComplexSynth;

public abstract class SpookySong extends Song {

	private static final float MAIN_VOLUME = 0.15f;
	private static final float BELL_VOLUME = 0.3f;
	private static final float HEART_VOLUME = 0.45f;

	private static final Instrument EERIE_HARPSICHORD = new Instrument(
			SynthFactory.pulse(.3f), AdrEnvelope.quadratic(.01f, .09f, .3f),
			t -> 1f - (t / 250)
	);
	private static final Instrument HARPSICHORD = new Instrument(
			SynthFactory.pulse(.3f), AdsrEnvelope.quadratic(.01f, .09f, .3f, 1f),
			t -> 1f - (t / 250)
	);

	private final SequentialTrack mainTrack;
	private final SequentialTrack heartBeat1;
	private final SequentialTrack heartBeat2;
	private final CompositeTrack bell;

	public SpookySong(String songName) {
		super(songName, 1f);

		mainTrack = new SequentialTrack(getSongName(), "main", EERIE_HARPSICHORD, MAIN_VOLUME);
		addTrack(mainTrack);
		mainTrack.addMusicalEffect(OvertoneEffect.tritoneDown());

		heartBeat1 = new SequentialTrack(getSongName(), "heart beat 1", Instrument.kickDrum(0.3f), HEART_VOLUME);
		heartBeat2 = new SequentialTrack(getSongName(), "heart beat 2", Instrument.kickDrum(0.2f), HEART_VOLUME);
		addTrack(heartBeat1);
		addTrack(heartBeat2);

		BellComplexSynth bellComplexSynth = new BellComplexSynth(2f, SynthFactory.sine());
		bell = new CompositeTrack(bellComplexSynth, getSongName(), "bell", BELL_VOLUME);
		EnvelopeReverser envelopeReverser = new EnvelopeReverser();
		bell.addMusicalEffect(envelopeReverser);
		addTrack(bell);

	}

	protected void writeIntro() {
		mainTrack.addNote(NoteDuration.DOUBLE_WHOLE, Pitch.D5);
		mainTrack.addNote(NoteDuration.DOUBLE_WHOLE, Pitch.F4);
		mainTrack.addNote(NoteDuration.DOUBLE_WHOLE, Pitch.F5);
		mainTrack.addNote(NoteDuration.DOUBLE_WHOLE, Pitch.B3);
		mainTrack.addSilence(NoteDuration.DOUBLE_WHOLE); // backwards bell
		mainTrack.addNote(NoteDuration.WHOLE, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.WHOLE, Pitch.B3, Pitch.F4);
		mainTrack.addNote(NoteDuration.WHOLE, Pitch.D4, Pitch.F4);
		mainTrack.addNote(NoteDuration.WHOLE, Pitch.B3, Pitch.B4);
		mainTrack.addNote(NoteDuration.HALF_DOT, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.HALF_DOT, Pitch.B3, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF_DOT, Pitch.D4, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF_DOT, Pitch.B3, Pitch.B4);

		Do.xTimes(mainTrack.lengthInBeats().numerator() / 4, () -> {
			heartBeat1.addSilence(NoteDuration.EIGHTH);
			heartBeat1.addNote(NoteDuration.QUARTER, Pitch.A2);
			heartBeat1.addSilence(NoteDuration.QUARTER);
			heartBeat1.addSilence(NoteDuration.QUARTER);
			heartBeat1.addSilence(NoteDuration.EIGHTH);

			heartBeat2.addSilence(NoteDuration.EIGHTH);
			heartBeat2.addSilence(NoteDuration.QUARTER);
			heartBeat2.addNote(NoteDuration.QUARTER, Pitch.F2);
			heartBeat2.addSilence(NoteDuration.QUARTER);
			heartBeat2.addSilence(NoteDuration.EIGHTH);
		});

		bell.addSilence(NoteDuration.DOUBLE_WHOLE);
		bell.addSilence(NoteDuration.DOUBLE_WHOLE);
		bell.addSilence(NoteDuration.DOUBLE_WHOLE);
		bell.addSilence(NoteDuration.DOUBLE_WHOLE);
		bell.addNote(NoteDuration.DOUBLE_WHOLE, Pitch.B3);
		bell.addSilence(NoteDuration.WHOLE);
		bell.addSilence(NoteDuration.WHOLE);
		bell.addSilence(NoteDuration.WHOLE);
		bell.addSilence(NoteDuration.WHOLE);
		bell.addSilence(NoteDuration.HALF_DOT);
		bell.addSilence(NoteDuration.HALF_DOT);
		bell.addSilence(NoteDuration.HALF_DOT);
		bell.addSilence(NoteDuration.HALF_DOT);
	}

	protected void writeBody() {
		mainTrack.changeInstrument(HARPSICHORD);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B3, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.D4, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B3, Pitch.B4);

		mainTrack.addNote(NoteDuration.HALF, new EnvelopeReverser(), Pitch.F4, Pitch.B4);
		mainTrack.addNote(NoteDuration.QUARTER, new EnvelopeReverser(), Pitch.E4, Pitch.B4);
		mainTrack.addNote(NoteDuration.QUARTER, new EnvelopeReverser(), Pitch.D4, Pitch.B4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C4, Pitch.B4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B3, Pitch.B4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.F4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B3, Pitch.E4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.G4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B3, Pitch.F4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.A4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.F4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.C4);
		mainTrack.addNote(NoteDuration.HALF, Pitch.B2, Pitch.B3, Pitch.B4);

		bell.padWithSilence(mainTrack.loopingLengthInBeats());
		bell.addNote(NoteDuration.WHOLE, Pitch.B3);
		bell.addNote(NoteDuration.WHOLE, Pitch.D4);
		mainTrack.addSilence(NoteDuration.HALF);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.B3, Pitch.D4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.B3, Pitch.D4);
		mainTrack.addSilence(NoteDuration.SIXTEENTH);
		mainTrack.addSilence(NoteDuration.QUARTER);

		mainTrack.addSilence(NoteDuration.HALF);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.D4, Pitch.F4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.D4, Pitch.F4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.D4, Pitch.F4);
		mainTrack.addSilence(NoteDuration.SIXTEENTH);
		mainTrack.addSilence(NoteDuration.QUARTER);

		Do.xTimes(mainTrack.loopingLengthInBeats().numerator() / 4, () -> {
			heartBeat1.addSilence(NoteDuration.EIGHTH);
			heartBeat1.addNote(NoteDuration.QUARTER, Pitch.A2);
			heartBeat1.addSilence(NoteDuration.EIGHTH);
			heartBeat1.addSilence(NoteDuration.EIGHTH);
			heartBeat1.addNote(NoteDuration.QUARTER, Pitch.A2);
			heartBeat1.addSilence(NoteDuration.EIGHTH);

			heartBeat2.addSilence(NoteDuration.EIGHTH);
			heartBeat2.addSilence(NoteDuration.EIGHTH);
			heartBeat2.addNote(NoteDuration.QUARTER, Pitch.F2);
			heartBeat2.addSilence(NoteDuration.EIGHTH);
			heartBeat2.addSilence(NoteDuration.EIGHTH);
			heartBeat2.addNote(NoteDuration.QUARTER, Pitch.F2);
		});

	}

	public static AlbumExportingInformation spookyAlbum() {
		AlbumExportingInformation albumExportingInformation = new AlbumExportingInformation("Spooky Dread", 2026);
		SongOrchestrator introOrchestrator = new SongOrchestrator(4) {
			@Override
			public void orchestrateSong() {
				processMeasures(17); // intro
			}
		};
		SongOrchestrator loopOrchestrator = new SongOrchestrator(4) {
			@Override
			public void orchestrateSong() {
				processMeasures(10); // body
			}
		};
		albumExportingInformation.addSong(new SpookySongIntro(), introOrchestrator);
		albumExportingInformation.addSong(new SpookySongLoop(), loopOrchestrator);
		return albumExportingInformation;
	}

}
