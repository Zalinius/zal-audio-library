package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.List;
import java.util.function.Function;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

/**
 * An abstract class which represents functional transformations on MusicalInstants
 */
public abstract class MusicalEffect implements Function<MusicalInstant, List<MusicalInstant>> {

	/**
	 * A pass-through musical effect, which has no effect
	 */
	public static final MusicalEffect NONE = new MusicalEffect() {
		@Override
		public List<MusicalInstant> effect(MusicalInstant musicalInstant) {
			return List.of(musicalInstant);
		}
	};

	private boolean enabled; // TODO should this be transient?

	/**
	 * Creates an enabled MusicalEffect
	 */
	public MusicalEffect() {
		enabled = true;
	}

	/**
	 * Sets the state of the musical effect
	 * @param enabled whether or not the effect should be enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the state of the musical effect
	 * @return Gets whether or not the effect should be enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public final List<MusicalInstant> apply(MusicalInstant musicalInstant) {
		if (enabled) {
			return effect(musicalInstant);
		} else {
			return List.of(musicalInstant);
		}
	}

	public abstract List<MusicalInstant> effect(MusicalInstant musicalInstant);

}
