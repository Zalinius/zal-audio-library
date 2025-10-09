package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import java.util.List;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.composing.tracks.SixteenthRhythmTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;

public class D_OverworldSong extends Song {

	public D_OverworldSong() {
		super("Village", 1.5f);

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.GUITAR, 0.7f);
		addTrack(mainTrack);
		TransposeEffect mainTrackTranspose = new TransposeEffect(p -> p);
		mainTrack.addMusicalEffect(mainTrackTranspose);

		SixteenthRhythmTrack rhythmTrack = new SixteenthRhythmTrack(getSongName(), "rhythm", Sbig2025Album.RHYTHM, 0.2f, List.of(true, true, true, false), Pitch.C3);
		addTrack(rhythmTrack);
		TransposeEffect rhythmTranspose = new TransposeEffect(p -> p);
		rhythmTrack.addMusicalEffect(rhythmTranspose);

		SequentialTrack fluteTrack = new SequentialTrack(getSongName(), "flute", Sbig2025Album.FLUTE, 1f);
		addTrack(fluteTrack);
		TransposeEffect fluteTrackTranspose = new TransposeEffect(p -> p);
		fluteTrack.addMusicalEffect(fluteTrackTranspose);

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
}
