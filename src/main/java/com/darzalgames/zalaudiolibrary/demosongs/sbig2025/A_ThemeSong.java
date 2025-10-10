package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;

public class A_ThemeSong extends Song {

	public A_ThemeSong() {
		super("Ancient Legend");

		final float mainAmplitude = 0.55f;
		final float drumAmplitude = 0.25f;

		SequentialTrack mainTrack = new SequentialTrack(getSongName(), "main", Sbig2025Album.MAIN, mainAmplitude);
		addTrack(mainTrack);

		// RepeatingTrack bassTrack = createTrack(Instruments.BASS_DRONE, "bass");

		// Sad state
		mainTrack.addNote(NoteDuration.HALF, Pitch.E3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3);

		mainTrack.addNote(NoteDuration.HALF, Pitch.A3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G3);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F3);

		mainTrack.addNote(NoteDuration.HALF_DOT, Pitch.E3);
		mainTrack.addSilence(NoteDuration.QUARTER);

		mainTrack.addNote(NoteDuration.HALF, Pitch.E3, Pitch.E4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G3, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.B4);

		mainTrack.addNote(NoteDuration.HALF, Pitch.D4, Pitch.D5);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4, Pitch.C5);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.B3, Pitch.B4);

		mainTrack.addNote(NoteDuration.HALF, Pitch.A3, Pitch.A4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G3, Pitch.G4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.F3, Pitch.F4);

		mainTrack.addNote(NoteDuration.HALF_DOT, Pitch.E3, Pitch.E4);
		mainTrack.addSilence(NoteDuration.QUARTER);

		// The story
		SequentialTrack drumTrack = createTrack("drum", Sbig2025Album.RHYTHM, drumAmplitude);
		drumTrack.padWithSilence(mainTrack.lengthInBeats());

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.E4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addSilence(NoteDuration.EIGHTH);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.SIXTEENTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.SIXTEENTH, Pitch.E3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addSilence(NoteDuration.EIGHTH);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C5);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.B4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.B4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.A4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.D4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.E3);

		// Preparation!
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.F4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.D4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.D4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);

		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		mainTrack.addNote(NoteDuration.QUARTER, Pitch.C4);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);

		// final upswing
		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.A4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.A4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C5);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.A4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.QUARTER, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);

		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);

		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A4);

		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C5);

		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		drumTrack.addNote(NoteDuration.EIGHTH, Pitch.C3);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.C4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.E4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.G4);
		mainTrack.addNote(NoteDuration.EIGHTH, Pitch.A4);

		drumTrack.addNote(NoteDuration.HALF, Pitch.C3);
		mainTrack.addNote(NoteDuration.HALF, Pitch.C4, Pitch.C5);

		drumTrack.addSilence(NoteDuration.WHOLE);
		mainTrack.addSilence(NoteDuration.WHOLE);
	}

}
