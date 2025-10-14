package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import java.util.List;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.composing.tracks.SixteenthRhythmTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;

public class D_OverworldSong extends Song {

	private final SixteenthRhythmTrack rhythmTrack;
	private final TransposeEffect mainTranspose;
	private final TransposeEffect rhythmTranspose;
	private final TransposeEffect fluteTranspose;

	public D_OverworldSong() {
		super("Village", 1.5f);

		final float mainAmplitude = 0.65f;
		final float rhythmAmplitude = 0.2f;
		final float fluteAmplitude = 0.75f;

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.GUITAR, mainAmplitude);
		addTrack(mainTrack);
		mainTranspose = new TransposeEffect(p -> p);
		mainTrack.addMusicalEffect(mainTranspose);

		rhythmTrack = new SixteenthRhythmTrack(getSongName(), "rhythm", Sbig2025Album.RHYTHM, rhythmAmplitude, List.of(true, true, true, false), Pitch.C3);
		addTrack(rhythmTrack);
		rhythmTranspose = new TransposeEffect(p -> p);
		rhythmTrack.addMusicalEffect(rhythmTranspose);

		SequentialTrack fluteTrack = new SequentialTrack(getSongName(), "flute", Sbig2025Album.FLUTE, fluteAmplitude);
		addTrack(fluteTrack);
		fluteTranspose = new TransposeEffect(p -> p);
		fluteTrack.addMusicalEffect(fluteTranspose);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G2);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G2);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.HALF, Pitch.G2);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A2);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G2);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E2);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E2);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addNote(NoteDuration.HALF, Pitch.G2);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G2);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G2);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.HALF, Pitch.E3);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		mainTrack.addSilence(NoteDuration.EIGHTH);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		fluteTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G3);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C4);

		// flute bridge
		fluteTrack.addNote(NoteDuration.HALF, Pitch.C5);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.G4);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.A4);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.G4);

		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A2);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);

		fluteTrack.addNote(NoteDuration.HALF, Pitch.F4);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.D4);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.E4);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.G4);

		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F2);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D2);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E2);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G2);

		fluteTrack.addNote(NoteDuration.HALF, Pitch.C5);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.E5);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.D5);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.F5);

		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F3);

		fluteTrack.addNote(NoteDuration.HALF, Pitch.E5);
		fluteTrack.addNote(NoteDuration.HALF, Pitch.D5);
		fluteTrack.addNote(NoteDuration.WHOLE, Pitch.C5);

		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D3);
		mainTrack.addSilence(NoteDuration.QUARTER);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
	}

	public void changeToVillageStyle() {
		rhythmTrack.setRhythm(true, true, true, false);
		mainTranspose.setTranspose(p -> p);
		rhythmTranspose.setTranspose(p -> p);
		fluteTranspose.setTranspose(p -> p);
	}

	public void changeToForestStyle() {
		rhythmTrack.setRhythm(true, false, true, true);
		mainTranspose.setTranspose(p -> p);
		rhythmTranspose.setTranspose(p -> p);
		fluteTranspose.setTranspose(Pitch::octaveDown);
	}

	public void changeToDeepForestStyle() {
		rhythmTrack.setRhythm(true, true, true, true);
		mainTranspose.setTranspose(Pitch::down);
		rhythmTranspose.setTranspose(Pitch::down);
		fluteTranspose.setTranspose(p -> p.octaveDown().down());
	}

}
