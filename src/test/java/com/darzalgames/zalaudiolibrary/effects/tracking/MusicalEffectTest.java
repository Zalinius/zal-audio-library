package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

class MusicalEffectTest {

	@Test
	void defaultConstructor_startsEnabled() {
		MusicalEffect musicalEffect = new MusicalEffect() {
			@Override
			public List<MusicalInstant> effect(MusicalInstant musicalInstant) {
				return null;
			}
		};

		assertTrue(musicalEffect.isEnabled());
	}

	@Test
	void setEnabled_true_enablesEffect() {
		MusicalEffect musicalEffect = new MusicalEffect() {
			@Override
			public List<MusicalInstant> effect(MusicalInstant musicalInstant) {
				return null;
			}
		};

		musicalEffect.setEnabled(true);

		assertTrue(musicalEffect.isEnabled());
	}

	@Test
	void setEnabled_false_disablesEffect() {
		MusicalEffect musicalEffect = new MusicalEffect() {
			@Override
			public List<MusicalInstant> effect(MusicalInstant musicalInstant) {
				return null;
			}
		};

		musicalEffect.setEnabled(false);

		assertFalse(musicalEffect.isEnabled());
	}

}
