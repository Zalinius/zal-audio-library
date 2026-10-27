package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.List;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

/**
 * A musical effect convenience class for MusicalEffects which don't create new MusicalInstants
 */
public abstract class SimpleMusicalEffect extends MusicalEffect {

	@Override
	public final List<MusicalInstant> effect(MusicalInstant musicalInstant) {
		return List.of(applySimpleEffect(musicalInstant));
	}

	/**
	 * Transforms a musical instant
	 * @param musicalInstant The to be transformed
	 * @return A modified musical instant
	 */
	public abstract MusicalInstant applySimpleEffect(MusicalInstant musicalInstant);

}
