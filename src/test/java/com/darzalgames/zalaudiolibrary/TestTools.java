package com.darzalgames.zalaudiolibrary;

import com.darzalgames.zalaudiolibrary.amplitude.ConstantEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Instrument;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class TestTools {

	public static final Instrument TEST_INSTRUMENT = new Instrument(Synth.zero(), ConstantEnvelope.zeroEnvelope());

}
