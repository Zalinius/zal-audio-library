package com.darzalgames.zalaudiolibrary.composing.tracks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.validation.CompositionError;
import com.darzalgames.zalaudiolibrary.composing.validation.TrackFractionalError;
import com.darzalgames.zalaudiolibrary.synth.complex.BellComplexSynth;

class CompositeTrackTest {

	@Test
	void validate_withFractionalErrorsInInnerTracks_propagatesUpToCompositeTrack() {
		CompositeTrack compositeTrack = new CompositeTrack(new BellComplexSynth(), "test song", "test track", 1f);
		compositeTrack.addNote(NoteDuration.SIXTEENTH, Pitch.C4);

		List<CompositionError> errors = compositeTrack.validate();

		assertEquals(21, errors.size());
		for (CompositionError compositionError : errors) {
			assertEquals(TrackFractionalError.class, compositionError.getClass());
		}
	}

}
