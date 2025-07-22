package com.darzalgames.zalaudiolibrary.pipeline.instants;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public record MusicalInstant(Synth synth, Pitch pitch, NoteDuration duration, Envelope envelope, float amplitude, String id) {}
