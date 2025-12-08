package com.darzalgames.zalaudiolibrary.pipeline.instants;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.NoteDuration;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public record MusicalInstant(Synth synth, Pitch pitch, UnaryOperator<Float> frequencyModulator, NoteDuration duration, Envelope envelope, float amplitude, String id) {}
