package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.List;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public abstract class SimpleMusicalEffect extends MusicalEffect {

	@Override
	public final List<MusicalInstant> effect(MusicalInstant musicalInstant) {
		return List.of(applySimpleEffect(musicalInstant));
	}

	public abstract MusicalInstant applySimpleEffect(MusicalInstant musicalInstant);

}
