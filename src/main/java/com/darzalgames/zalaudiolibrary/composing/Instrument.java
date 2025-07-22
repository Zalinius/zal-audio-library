package com.darzalgames.zalaudiolibrary.composing;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public record Instrument(Synth synth, Envelope envelope) {}
