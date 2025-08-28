package com.darzalgames.zalaudiolibrary.effects.tracking;

import java.util.function.Function;

import com.darzalgames.zalaudiolibrary.composing.ComplexPitch;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.pipeline.instants.MusicalInstant;

public class TransposeEffect extends SimpleMusicalEffect {

	private final Function<Pitch, Pitch> transposeFunction;

	public TransposeEffect(Function<Pitch, Pitch> transposeFunction) {
		this.transposeFunction = transposeFunction;
	}

	@Override
	public MusicalInstant applySimpleEffect(MusicalInstant original) {
		Pitch pitch = transposeFunction.apply(original.pitch().getBasePitch());
		ComplexPitch complexPitch = new ComplexPitch(pitch, original.pitch().getPitchModulator());
		return new MusicalInstant(original.synth(), complexPitch, original.duration(), original.envelope(), original.amplitude(), original.id());
	}

}
