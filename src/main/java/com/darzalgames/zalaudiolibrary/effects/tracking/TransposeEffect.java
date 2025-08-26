package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.function.Function;

import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class TransposeEffect extends SimpleMusicalEffect {

	private final Function<Pitch, Pitch> transposeFunction;

	public TransposeEffect(Function<Pitch, Pitch> transposeFunction) {
		this.transposeFunction = transposeFunction;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant original) {
		Pitch pitch = transposeFunction.apply(original.pitch());
		return new MusicalInstant(original.synth(), pitch, original.duration(), original.envelope(), original.amplitude(), original.id());
	}

}
