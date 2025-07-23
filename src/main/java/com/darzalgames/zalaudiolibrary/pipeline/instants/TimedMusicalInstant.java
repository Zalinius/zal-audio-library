package com.darzalgames.zalaudiolibrary.pipeline.instants;

import com.darzalgames.darzalcommon.math.Fraction;

public record TimedMusicalInstant(Fraction startingBeat, MusicalInstant musicalInstant) {}
