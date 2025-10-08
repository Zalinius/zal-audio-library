package com.darzalgames.zalaudiolibrary.demosongs.sbig2025;

import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AdsrEnvelope;
import com.darzalgames.zalaudiolibrary.amplitude.sustained.AsrEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongOrchestrator;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class Sbig2025Album extends AlbumExportingInformation {

	public static final Instrument MAIN = new Instrument(Synth.pulse(.15f), AdsrEnvelope.linear(.01f, .1f, .5f, .1f));
	public static final Instrument RHYTHM = new Instrument(Synth.pulse(.15f), ArEnvelope.linear(.02f, .1f));
	public static final Instrument BASS_DRONE = new Instrument(Synth.pulse(.2f), AsrEnvelope.linear(.05f, .1f));

	public Sbig2025Album() {
		super("Demon Lord's Demise : ~Rebirth~", 2025);

		addSong(new A_ThemeSong(), new SimpleOrchestrator(15));
		addSong(new B_MenuSong(), new SimpleOrchestrator(6 * 4));
	}

	private static class SimpleOrchestrator extends SongOrchestrator {

		private final int measures;

		public SimpleOrchestrator(int measures) {
			super(4);
			if (measures <= 0) {
				throw new IllegalArgumentException("measures must be positive: " + measures);
			}
			this.measures = measures;
		}

		@Override
		public void orchestrateSong() {
			processMeasures(measures);
		}

	}

}
