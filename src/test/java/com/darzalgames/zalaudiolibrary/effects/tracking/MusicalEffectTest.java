package com.darzalgames.zalaudiolibrary.effects.tracking;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	void apply_whenDisabled_returnsOriginal() {
		MusicalEffect musicalEffect = new MusicalEffect() {
			@Override
			public List<MusicalInstant> effect(MusicalInstant musicalInstant) {
				return null;
			}
		};
		musicalEffect.setEnabled(false);
		MusicalInstant musicalInstant = new MusicalInstant(null, null, null, null, null, 0, null);

		List<MusicalInstant> effectedMusicalInstant = musicalEffect.apply(musicalInstant);

		assertEquals(1, effectedMusicalInstant.size());
		assertSame(effectedMusicalInstant.getFirst(), musicalInstant);
	}

}
