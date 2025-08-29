package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.function.UnaryOperator;

import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class TransposeEffect extends SimpleMusicalEffect {

	private final UnaryOperator<Pitch> transposeFunction;

	public TransposeEffect(UnaryOperator<Pitch> transposeFunction) {
		this.transposeFunction = transposeFunction;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant original) {
		Pitch pitch = transposeFunction.apply(original.pitch());
		return new MusicalInstant(original.synth(), pitch, original.duration(), original.envelope(), original.amplitude(), original.id());
	}

}
