package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;

public class E_BossTime extends Song {

	public E_BossTime() {
		super("Boss Time", 2.5f);

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, 0.7f);
		addTrack(mainTrack);
		TransposeEffect mainTranspose = new TransposeEffect(Pitch::down);
		mainTrack.addMusicalEffect(mainTranspose);

		SequentialTrack bassTrack = new SequentialTrack(getSongName(), "bass", Sbig2025Album.BASS_DRONE, 0.3f);
		addTrack(bassTrack);
		TransposeEffect bassTranspose = new TransposeEffect(p -> p.down().octaveDown());
		bassTrack.addMusicalEffect(bassTranspose);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.E4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.G4);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.G3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.C5);
		mainTrack.addSilence(NoteDuration.EIGHTH);

		bassTrack.addNote(NoteDuration.WHOLE_DOT, Pitch.E3);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);
		mainTrack.addNote(NoteDuration.EIGHTH_DOT, Pitch.C4);
		mainTrack.addSilence(NoteDuration.EIGHTH_DOT);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.G4);
		mainTrack.addSilence(NoteDuration.EIGHTH);
	}

}
