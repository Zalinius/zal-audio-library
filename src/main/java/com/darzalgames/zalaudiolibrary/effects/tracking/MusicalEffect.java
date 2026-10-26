package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.List;
import java.util.function.Function;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public abstract class MusicalEffect implements Function<MusicalInstant, List<MusicalInstant>> {

	private boolean enabled; // TODO should this be transient?

	public MusicalEffect() {
		enabled = true;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
