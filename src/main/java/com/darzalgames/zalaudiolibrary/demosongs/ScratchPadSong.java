package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.*;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import java.util.Set;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.OvertoneEffect;
import com.darzalgames.zalaudiolibrary.effects.tracking.SynthClipper;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class ScratchPadSong extends Song {

	private final SequentialTrack mainTrack;
	private final SequentialTrack bassTrack;

	public ScratchPadSong() {
		// TODO add a slow steady bass drum
		// TODO make the main instrument into a complex synth, with the higher frequencies dropping off faster

		super("Cosmic Waltz", 0.75f);
		float mainVolume = 0.2f * 1.75f;
		float bassVolume = 0.15f * 1.75f;

		AdsrEnvelope mainEnvelope = AdsrEnvelope.quadratic(0.02f, 0.1f, 0.5f, 0.15f);
		ArEnvelope mainAltEnvelope = mainEnvelope.toArEnvelope();
		mainTrack = new SequentialTrack(getSongName(), "main", new Instrument(Synth.sine(), mainEnvelope), mainVolume);
		addTrack(mainTrack);
		bassTrack = new SequentialTrack(getSongName(), "bass", new Instrument(Synth.flatSine(2), ArEnvelope.quadratic(.005f, .3f)), bassVolume);
		addTrack(bassTrack);

		mainTrack.addMusicalEffect(OvertoneEffect.evenOvertoneEffect(p -> Set.of(p, p.octaveDown(), p.octaveDown().octaveDown())));
		mainTrack.addMusicalEffect(new SynthClipper(0.5f));
//		mainTrack.addMusicalEffect(new TransposeEffect(Pitch::down));

		bassTrack.addMusicalEffect(new TransposeEffect(Pitch::octaveDown));
//		bassTrack.addMusicalEffect(new TransposeEffect(Pitch::down));

		// Intro
		mainTrack.addNote(QUARTER_DOT, D4);
		mainTrack.addNote(QUARTER_DOT, F4);
		mainTrack.addNote(QUARTER_DOT, A4);
		mainTrack.addNote(QUARTER_DOT, F4);

		mainTrack.addNote(QUARTER_DOT, D4);
		mainTrack.addNote(QUARTER_DOT, D4, F4);
		mainTrack.addNote(QUARTER_DOT, F4, A4);
		mainTrack.addNote(QUARTER_DOT, D4, A4);

		mainTrack.setRepetitionPoint();
		bassTrack.padWithSilence(mainTrack.lengthInBeats());
		bassTrack.setRepetitionPoint();

		// Part 1a
		standardBassSegment();
		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER_DOT, E4);

		mainTrack.addNote(EIGHTH_DOT, F4);
		mainTrack.addNote(SIXTEENTH, F4);
		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER_DOT, G4);

		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER, A4);

		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER, G4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER_DOT, E4);

		// Part 2b
		standardBassSegment();
		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER_DOT, E4, G4);

		mainTrack.addNote(EIGHTH_DOT, F4);
		mainTrack.addNote(SIXTEENTH, F4);
		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER_DOT, G4, B4);

		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER, A4, D5);

		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER, A4, C5);
		mainTrack.addNote(EIGHTH, G4);
		mainTrack.addNote(QUARTER_DOT, E4, B4);

		// part 2a
		standardBassSegment();
		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER_DOT, E4);

		mainTrack.addNote(EIGHTH_DOT, F4);
		mainTrack.addNote(SIXTEENTH, F4);
		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER_DOT, B4);

		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER, A4);

		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER, A4);
		mainTrack.addNote(EIGHTH, C5);
		mainTrack.addNote(QUARTER_DOT, B4);

		// part 2b
		standardBassSegment();
		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER_DOT, E4, A4);

		mainTrack.addNote(EIGHTH_DOT, F4);
		mainTrack.addNote(SIXTEENTH, F4);
		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER_DOT, B4, D4);

		mainTrack.addNote(EIGHTH_DOT, D4);
		mainTrack.addNote(SIXTEENTH, D4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER, A4, F4);

		mainTrack.addNote(EIGHTH, A4);
		mainTrack.addNote(QUARTER, A4, F4);
		mainTrack.addNote(EIGHTH, C5);
		mainTrack.addNote(QUARTER_DOT, D4, B4);

		// bridge 1
		bassTrack.addSilence(HALF);
		bassTrack.addNote(EIGHTH, D5);
		bassTrack.addNote(EIGHTH, C5);
		mainTrack.addNote(QUARTER, D5);
		mainTrack.addNote(EIGHTH, C5);
		mainTrack.addNote(QUARTER_DOT, A4);

		bassTrack.addSilence(HALF);
		bassTrack.addNote(EIGHTH, C5);
		bassTrack.addNote(EIGHTH, B4);
		mainTrack.addNote(QUARTER, C5);
		mainTrack.addNote(EIGHTH, B4);
		mainTrack.addNote(QUARTER_DOT, G4);

		bassTrack.addSilence(QUARTER);
		bassTrack.addNote(EIGHTH, C5);
		bassTrack.addSilence(QUARTER);
		bassTrack.addNote(EIGHTH, B4);
		mainTrack.addNote(QUARTER_DOT, A4);
		mainTrack.addNote(QUARTER_DOT, G4);

		bassTrack.addSilence(HALF);
		bassTrack.addNote(EIGHTH, E5);
		bassTrack.addNote(EIGHTH, D5);
		mainTrack.addNote(QUARTER, F4);
		mainTrack.addNote(EIGHTH, G4);
		mainTrack.addNote(QUARTER_DOT, E4);

		// bridge2
		bassTrack.addSilence(HALF);
		bassTrack.addNote(EIGHTH, D5, F5);
		bassTrack.addNote(EIGHTH, F5, A5);
		mainTrack.addNote(QUARTER, D5);
		mainTrack.addNote(EIGHTH, E5);
		mainTrack.addNote(QUARTER_DOT, F5);

		bassTrack.addSilence(HALF);
		bassTrack.addNote(EIGHTH, C5, E5);
		bassTrack.addNote(EIGHTH, E5, G5);
		mainTrack.addNote(QUARTER, C5);
		mainTrack.addNote(EIGHTH, D5);
		mainTrack.addNote(QUARTER_DOT, E5);

		bassTrack.addSilence(QUARTER);
		bassTrack.addNote(EIGHTH, F5, A5);
		bassTrack.addSilence(QUARTER);
		bassTrack.addNote(EIGHTH, E5, G5);
		mainTrack.addNote(QUARTER_DOT, D5);
		mainTrack.addNote(QUARTER_DOT, C5);

		bassTrack.addSilence(HALF);
		bassTrack.addSilence(QUARTER);
		mainTrack.addNote(QUARTER, A4);
		mainTrack.addNote(EIGHTH, F4);
		mainTrack.addNote(QUARTER_DOT, D4, D5);
	}

	private void standardBassSegment() {
		bassTrack.addNote(EIGHTH, D3);
		bassTrack.addNote(EIGHTH, F3);
		bassTrack.addNote(EIGHTH, F3);
		bassTrack.addNote(QUARTER_DOT, D3);

		bassTrack.addNote(EIGHTH, D3);
		bassTrack.addNote(EIGHTH, A3);
		bassTrack.addNote(EIGHTH, A3);
		bassTrack.addNote(QUARTER_DOT, G3);

		bassTrack.addNote(EIGHTH, D3);
		bassTrack.addNote(EIGHTH, F3);
		bassTrack.addNote(EIGHTH, F3);
		bassTrack.addNote(QUARTER_DOT, A3);

		bassTrack.addNote(EIGHTH, G3);
		bassTrack.addNote(QUARTER, G3);
		bassTrack.addNote(EIGHTH, F3);
		bassTrack.addNote(QUARTER, E3);
	}

	public static AlbumExportingInformation scratchAlbum() {
		AlbumExportingInformation albumExportingInformation = new AlbumExportingInformation("Scratch", 666);
		SongOrchestrator orchestrator = new SongOrchestrator(3) {
			@Override
			public void orchestrateSong() {
				processMeasures(28);
			}
		};
		albumExportingInformation.addSong(new ScratchPadSong(), orchestrator);
		return albumExportingInformation;
	}

}
