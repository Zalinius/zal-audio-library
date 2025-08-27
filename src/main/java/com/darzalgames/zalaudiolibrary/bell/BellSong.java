package com.darzalgames.zalaudiolibrary.bell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.zalaudiolibrary.bell.BellExperiment.Partial;
import com.darzalgames.zalaudiolibrary.composing.*;

public class BellSong extends Song {

	public static final Pitch F4s = Pitch.F4.sharpen();
	public static final Pitch G4s = Pitch.G4.sharpen();

	private final List<Track> bellTracks;
	private final List<Partial> bellPartials;

	public BellSong() {
		super("Bell Song", 2f);

		bellPartials = BellExperiment.makePartials(1);
		bellTracks = new ArrayList<>();
		for (Iterator<Partial> it = bellPartials.iterator(); it.hasNext();) {
			Partial bellPartial = it.next();
			Track track = createTrack("bell " + bellPartial.getPartialIndex(), new Instrument(bellPartial.getSynth(), bellPartial.getEnvelope()), bellPartial.getAmplitude());
			bellTracks.add(track);
		}


		ringBell(NoteDuration.QUARTER_DOT, Pitch.E4);
		ringBell(NoteDuration.EIGHTH, Pitch.E4);
		ringBell(NoteDuration.QUARTER, Pitch.A4);
		ringBell(NoteDuration.QUARTER, G4s);

		ringBell(NoteDuration.QUARTER_DOT, Pitch.D4);
		ringBell(NoteDuration.EIGHTH, Pitch.D4);
		ringBell(NoteDuration.QUARTER, F4s);
		ringBell(NoteDuration.QUARTER, Pitch.E4);


		ringBell(NoteDuration.QUARTER, Pitch.E4);
		ringBell(NoteDuration.QUARTER_DOT, Pitch.E4);
		ringBell(NoteDuration.EIGHTH, Pitch.A4);
		ringBell(NoteDuration.QUARTER, Pitch.A4);


		ringBell(NoteDuration.QUARTER, Pitch.D4);
		ringBell(NoteDuration.QUARTER_DOT, Pitch.D4);
		ringBell(NoteDuration.EIGHTH, Pitch.G4);
		ringBell(NoteDuration.QUARTER, Pitch.G4);


		ringBell(NoteDuration.QUARTER, Pitch.E4);
		ringBell(NoteDuration.QUARTER_DOT, Pitch.E4);
		ringBell(NoteDuration.EIGHTH, Pitch.A4);
		ringBell(NoteDuration.QUARTER, Pitch.A4);

		ringBell(NoteDuration.QUARTER, Pitch.D4);
		ringBell(NoteDuration.QUARTER_DOT, Pitch.D4);
		ringBell(NoteDuration.EIGHTH, Pitch.G4);
		ringBell(NoteDuration.EIGHTH, Pitch.G4);
		ringBell(NoteDuration.EIGHTH, F4s);

		ringBell(NoteDuration.QUARTER_DOT, Pitch.E4);
		ringBell(NoteDuration.EIGHTH, Pitch.E4);
		ringBell(NoteDuration.QUARTER, Pitch.A4);
		ringBell(NoteDuration.QUARTER, G4s);

		ringBell(NoteDuration.QUARTER_DOT, Pitch.D4);
		ringBell(NoteDuration.EIGHTH, Pitch.D4);
		ringBell(NoteDuration.QUARTER, F4s);
		ringBell(NoteDuration.QUARTER, Pitch.E4);


		ringBell(NoteDuration.QUARTER_DOT, Pitch.E4);
		ringBell(NoteDuration.EIGHTH, Pitch.E4);
		ringBell(NoteDuration.QUARTER, Pitch.A4);
		ringBell(NoteDuration.QUARTER, G4s);

		ringBell(NoteDuration.QUARTER_DOT, Pitch.B4);
		ringBell(NoteDuration.EIGHTH, Pitch.B4);
		ringBell(NoteDuration.EIGHTH, Pitch.G4);
		ringBell(NoteDuration.EIGHTH, Pitch.G4);
		ringBell(NoteDuration.EIGHTH, F4s);
		ringBell(NoteDuration.EIGHTH, F4s);

		ringBell(NoteDuration.WHOLE, Pitch.E4);

	}

	public void ringBell(NoteDuration duration, Pitch pitch) {
		for (int i = 0; i < bellTracks.size(); i++) {
			Pitch inharmonicPitch = bellPartials.get(i).getPartialPitch(pitch);
			bellTracks.get(i).addNote(duration, inharmonicPitch);
		}
	}

	public void silenceBell(NoteDuration duration) {
		bellTracks.forEach(track -> track.addSilence(duration));
	}

}
