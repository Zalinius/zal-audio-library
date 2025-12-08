package com.darzalgames.zalaudiolibrary.pipeline.sounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;
import com.darzalgames.zalaudiolibrary.pipeline.instants.TimedMusicalInstant;

public class SimpleSoundMaker {

	/**
	 * Makes simple sounds from Musical instants, converting their timing in beats to timing in seconds
	 * @param musicalInstants the musical instants
	 * @param bps             the bps used for conversion
	 * @param currentBeat     The current exact beat of the song, may be fractional
	 * @param currentTime     the current time of the song in seconds
	 * @return The SimpleSounds active during this beat, created from Musical Instants, with their start time in seconds instead of beats
	 */
	public List<TimedSimpleSound> makeSimpleSounds(List<TimedMusicalInstant> musicalInstants, float bps, float currentBeat, float currentTime) {
		List<TimedSimpleSound> timedSimpleSounds = new ArrayList<>();

		for (Iterator<TimedMusicalInstant> iterator = musicalInstants.iterator(); iterator.hasNext();) {
			TimedMusicalInstant timedMusicalInstant = iterator.next();
			MusicalInstant musicalInstant = timedMusicalInstant.musicalInstant();

			float simpleSoundStartTime = extrapolateStartTime(timedMusicalInstant.startingBeat(), currentBeat, currentTime, bps);

			SimpleSound simpleSound = new SimpleSound(
					musicalInstant.synth(),
					musicalInstant.pitch(),
					musicalInstant.frequencyModulator(),
					musicalInstant.duration().inBeats().toFloat() / bps,
					musicalInstant.envelope(),
					musicalInstant.amplitude(),
					musicalInstant.id()
			);

			TimedSimpleSound timedSimpleSound = new TimedSimpleSound(simpleSoundStartTime, simpleSound);
			timedSimpleSounds.add(timedSimpleSound);
		}

		return timedSimpleSounds;
	}

	public static float extrapolateStartTime(Fraction startingBeat, float beatProgress, float currentTime, float bps) {
		return currentTime - (beatProgress - startingBeat.toFloat()) / bps;
	}

}
