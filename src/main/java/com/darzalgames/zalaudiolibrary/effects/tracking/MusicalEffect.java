package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.List;
import java.util.function.Function;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public interface MusicalEffect extends Function<MusicalInstant, List<MusicalInstant>> {}
