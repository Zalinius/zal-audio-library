package com.darzalgames.zalaudiolibrary.demosongs;

import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.EIGHTH;
import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.QUARTER;
import static com.darzalgames.zalaudiolibrary.composing.NoteDuration.SIXTEENTH;
import static com.darzalgames.zalaudiolibrary.composing.Pitch.*;

import java.util.List;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.composing.tracks.SequentialTrack;
import com.darzalgames.zalaudiolibrary.composing.tracks.SixteenthRhythmTrack;
import com.darzalgames.zalaudiolibrary.demosongs.sbig2025.Sbig2025Album;
import com.darzalgames.zalaudiolibrary.effects.tracking.TransposeEffect;

public class ManagersVacationSong extends Song {

	private final SequentialTrack main;
	private final TransposeEffect mainTransposer;
	private final SixteenthRhythmTrack sixteenthRhythmTrack;
	private final TransposeEffect rhythmTransposer;

	public ManagersVacationSong() {
		super("Manager's Vacation");

		main = new SequentialTrack(getSongName(), "main", Sbig2025Album.GUITAR, 0.75f);
		addTrack(main);
		mainTransposer = new TransposeEffect(p -> p);
		main.addMusicalEffect(mainTransposer);

		composeIntro();
		main.setRepetitionPoint();

		sixteenthRhythmTrack = new SixteenthRhythmTrack(getSongName(), "rhythm", Sbig2025Album.RHYTHM, 0.3f, List.of(false, false, true, false), Pitch.C3);
		addTrack(sixteenthRhythmTrack);
		rhythmTransposer = new TransposeEffect(p -> p);
		sixteenthRhythmTrack.addMusicalEffect(rhythmTransposer);
		sixteenthRhythmTrack.padWithSilence(main.lengthInBeats());

		composeChorus();
		composeVerse1();
		composeChorus();
		composeVerse2();
	}

	private void composeIntro() {
		main.addNote(NoteDuration.QUARTER, G3);
		main.addNote(QUARTER, E3);
		main.addNote(QUARTER, C3);
		main.addNote(QUARTER, G2);

		main.addNote(QUARTER, G3);
		main.addNote(EIGHTH, E3);
		main.addNote(SIXTEENTH, F3);
		main.addNote(SIXTEENTH, E3);
		main.addNote(EIGHTH, D3);
		main.addNote(SIXTEENTH, C3);
		main.addNote(EIGHTH, B2);
		main.addNote(SIXTEENTH, A2);
		main.addNote(EIGHTH, G2);

		main.addNote(QUARTER, C4);
		main.addNote(QUARTER, G3);
		main.addNote(QUARTER, E3);
		main.addNote(QUARTER, C3);

		main.addNote(QUARTER, C4);
		main.addNote(EIGHTH, G3);
		main.addNote(SIXTEENTH, A3);
		main.addNote(SIXTEENTH, G3);
		main.addNote(EIGHTH, F3);
		main.addNote(SIXTEENTH, G3);
		main.addNote(EIGHTH, A3);
		main.addNote(SIXTEENTH, B3);
		main.addNote(EIGHTH, C4);
	}

	private void composeChorus() {
		main.addNote(QUARTER, G3);
		main.addNote(QUARTER, E3);
		main.addNote(QUARTER, C3);
		main.addNote(QUARTER, G2);

		main.addNote(QUARTER, G3);
		main.addNote(EIGHTH, E3);
		main.addNote(SIXTEENTH, F3);
		main.addNote(SIXTEENTH, E3);
		main.addNote(EIGHTH, D3);
		main.addNote(SIXTEENTH, C3);
		main.addNote(EIGHTH, B2);
		main.addNote(SIXTEENTH, A2);
		main.addNote(EIGHTH, G2);

		main.addNote(QUARTER, C4);
		main.addNote(QUARTER, G3);
		main.addNote(QUARTER, E3);
		main.addNote(QUARTER, C3);

		main.addNote(QUARTER, C4);
		main.addNote(EIGHTH, G3);
		main.addNote(SIXTEENTH, A3);
		main.addNote(SIXTEENTH, G3);
		main.addNote(EIGHTH, F3);
		main.addNote(SIXTEENTH, G3);
		main.addNote(EIGHTH, A3);
		main.addNote(SIXTEENTH, B3);
		main.addNote(EIGHTH, C4);
	}

	private void composeVerse1() {
		main.addNote(QUARTER, E3);
		main.addNote(QUARTER, G3);
		main.addNote(QUARTER, F3);
		main.addNote(QUARTER, C3);

		main.addNote(QUARTER, D3);
		main.addNote(EIGHTH, D3);
		main.addNote(EIGHTH, C3);
		main.addNote(EIGHTH, D3);
		main.addNote(EIGHTH, E3);
		main.addNote(QUARTER, F3);

		main.addNote(QUARTER, E3);
		main.addNote(EIGHTH, E3);
		main.addNote(EIGHTH, D3);
		main.addNote(EIGHTH, E3);
		main.addNote(EIGHTH, F3);
		main.addNote(QUARTER, G3);

		main.addNote(QUARTER, G3);
		main.addNote(EIGHTH, F3);
		main.addNote(EIGHTH, D3);
		main.addNote(EIGHTH, C3);
		main.addNote(EIGHTH, C3);
		main.addNote(QUARTER, C3);
	}

	private void composeVerse2() {
		main.addNote(QUARTER, E3);
		main.addNote(QUARTER, C3);
		main.addNote(QUARTER, G3);
		main.addNote(QUARTER, E3);

		main.addNote(EIGHTH, F3);
		main.addNote(EIGHTH, G3);
		main.addNote(EIGHTH, A3);
		main.addNote(EIGHTH, C4);
		main.addNote(EIGHTH, B3);
		main.addNote(EIGHTH, A3);
		main.addNote(QUARTER, G3);

		main.addNote(QUARTER, A3);
		main.addNote(QUARTER, F3);
		main.addNote(QUARTER, G3);
		main.addNote(QUARTER, E3);

		main.addNote(EIGHTH, G3);
		main.addNote(EIGHTH, F3);
		main.addNote(EIGHTH, E3);
		main.addNote(EIGHTH, D3);
		main.addNote(EIGHTH, C3);
		main.addNote(EIGHTH, C3);
		main.addNote(QUARTER, C3);

	}

	public void onTimeSpeakerChanged(int day, String timeOfDay, String speaker) {
		setDaysMusic(day, timeOfDay);
		if ("SHADOWY_FIGURE".equals(speaker)) {
			cultistShowsUp();
		}
	}

	private void cultistShowsUp() {
		sixteenthRhythmTrack.setRhythm(false, true, false, true);
		mainTransposer.setTranspose(Pitch::down);
		rhythmTransposer.setTranspose(Pitch::down);
		changeBPSGradually(0.9f, 2);
	}

	private void setDaysMusic(int day, String timeOfDay) {

		switch (day) {
		case 1:
			sixteenthRhythmTrack.setRhythm(false, false, true, false);
			break;
		case 2: // Mean Heroes
			switch (timeOfDay) {
			case "AM":
				sixteenthRhythmTrack.setRhythm(false, false, true, true);
				mainTransposer.setTranspose(Pitch::up);
				rhythmTransposer.setTranspose(Pitch::up);
				changeBPSGradually(1.1f);
				break;
			default:

				break;
			}
			break;
		case 3: // Cultist
			switch (timeOfDay) {
			case "AM":
				cultistShowsUp();
				break;
			default:
				break;
			}
			break;
		case 4: // Weird Person Plumn
			switch (timeOfDay) {
			case "AM":
				sixteenthRhythmTrack.setRhythm(false, true, true, false, false, false, true, true);
				mainTransposer.setTranspose(p -> p.up().up().up());
				rhythmTransposer.resetTranspose();
				changeBPSGradually(1f);
				break;
			default:
				break;
			}
			break;
		case 5:
			switch (timeOfDay) {
			case "AM": // Millimer and Robin yeesh
				sixteenthRhythmTrack.setRhythm(true, false, true, true);
				mainTransposer.resetTranspose();
				rhythmTransposer.setTranspose(p -> p.up().up().up().up());
				changeBPSGradually(1.1f);
				break;
			case "PM": // Elizar threat
				sixteenthRhythmTrack.setRhythm(false, false, true, true);
				mainTransposer.setTranspose(Pitch::up);
				rhythmTransposer.setTranspose(Pitch::up);
				changeBPSGradually(1.1f);
				break;

			default:
				break;
			}

			break;
		case 6:// The two job Interviews
			switch (timeOfDay) {
			case "AM": // STANNI
				changeBPSGradually(0.8f);
				sixteenthRhythmTrack.setRhythm(false, false, true, false);
				mainTransposer.resetTranspose();
				rhythmTransposer.resetTranspose();
				break;
			case "PM": // ?#$@?$
				changeBPSGradually(1.2f);
				sixteenthRhythmTrack.setRhythm(false, true, true, false, false, false, true, true);
				mainTransposer.setTranspose(Pitch::octaveDown);
				rhythmTransposer.setTranspose(Pitch::octaveUp);
				break;

			default:
				break;
			}

			break;
		case 7: // Angry troll grandson, manager returns
			switch (timeOfDay) {
			case "AM": // Troll grandson
				mainTransposer.setTranspose(p -> p.down().down().down());
				rhythmTransposer.resetTranspose();
				changeBPSGradually(1f);
				sixteenthRhythmTrack.setRhythm(false, false, true, true);
				break;
			case "MIDNIGHT": // Manager returns
				sixteenthRhythmTrack.setRhythm(false, false, true, false);
				mainTransposer.resetTranspose();
				rhythmTransposer.resetTranspose();
				changeBPSGradually(1f);
				break;
			default:
				break;
			}

			break;

		default:
			sixteenthRhythmTrack.setRhythm(false, false, true, false);
			break;
		}
	}

}
