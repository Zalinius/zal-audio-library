package com.darzalgames.zalaudiolibrary.synth.complex;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.data.Tuple;
import com.darzalgames.zalaudiolibrary.amplitude.Envelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.synth.Synth;
import com.darzalgames.zalaudiolibrary.synth.complex.trumpetExperiment.ArbitrarySustainedEnvelope;

public class TrumpetComplexSynth implements ComplexSynth {



	@Override
	public List<Partial>  makePartials() {
		List<Partial> partials = new ArrayList<>();

		List<List<Tuple<Float, Float>>> envelopes = new ArrayList<>();
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.02f, 0.305f), new Tuple<>(0.036f, 0.338f), new Tuple<>(0.141f, 0.288f), new Tuple<>(0.237f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.025f, 0.317f), new Tuple<>(0.039f, 0.361f), new Tuple<>(0.123f, 0.295f), new Tuple<>(0.222f, 0.00f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.019f, 0.1f), new Tuple<>(0.034f, 0.369f), new Tuple<>(0.111f, 0.342f), new Tuple<>(0.207f, 0.00f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.003f, 0.0f), new Tuple<>(0.024f, 0.113f), new Tuple<>(0.029f, 0.257f), new Tuple<>(0.118f, 0.231f), new Tuple<>(0.187f, 0.035f), new Tuple<>(0.235f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.027f, 0.052f), new Tuple<>(0.034f, 0.13f), new Tuple<>(0.11f, 0.126f), new Tuple<>(0.191f, 0.013f), new Tuple<>(0.234f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.046f, 0.083f), new Tuple<>(0.064f, 0.1f), new Tuple<>(0.1f, 0.1f), new Tuple<>(0.189f, 0.011f), new Tuple<>(0.221f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.037f, 0.039f), new Tuple<>(0.045f, 0.077f), new Tuple<>(0.11f, 0.079f), new Tuple<>(0.176f, 0.011f), new Tuple<>(0.205f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.028f, 0.017f), new Tuple<>(0.043f, 0.071f), new Tuple<>(0.109f, 0.066f), new Tuple<>(0.172f, 0.008f), new Tuple<>(0.201f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.029f, 0.016f), new Tuple<>(0.043f, 0.053f), new Tuple<>(0.054f, 0.066f), new Tuple<>(0.105f, 0.064f), new Tuple<>(0.165f, 0.007f), new Tuple<>(0.191f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.027f, 0.006f), new Tuple<>(0.041f, 0.025f), new Tuple<>(0.056f, 0.029f), new Tuple<>(0.072f, 0.022f), new Tuple<>(0.095f, 0.024f), new Tuple<>(0.18f, 0.0f), new Tuple<>(0.36f, 0.0f)));

		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.002f, 0.0f), new Tuple<>(0.037f, 0.006f), new Tuple<>(0.055f, 0.025f), new Tuple<>(0.088f, 0.029f), new Tuple<>(0.114f, 0.028f), new Tuple<>(0.164f, 0.003f), new Tuple<>(0.186f, 0.0f)));
		envelopes.add(List.of(new Tuple<>(0.0f, 0.0f), new Tuple<>(0.007f, 0.0f), new Tuple<>(0.039f, 0.003f), new Tuple<>(0.043f, 0.008f), new Tuple<>(0.088f, 0.011f), new Tuple<>(0.118f, 0.009f), new Tuple<>(0.138f, 0.003f), new Tuple<>(0.165f, 0.0f)));

		List<Integer> sustainIndices = List.of(3, 4, 4, 4, 3, 3, 3, 4, 5, 5, 5, 5);


		int harmonics = envelopes.size();
		float shortener = 1f;

		for (int i = 0; i < harmonics; i++) {
			int frequencyMultiple = i+1;

			List<Tuple<Float, Float>> envelopeData = envelopes.get(i);
			envelopeData = envelopeData.stream().map(tuple -> new Tuple<>(tuple.e()*shortener, tuple.f())).toList();

			Envelope envelope = new ArbitrarySustainedEnvelope(envelopeData, sustainIndices.get(i));
			//			envelope = new ArbitraryEnvelope(envelopeData);

			Partial harmonicPartial = new Partial(Synth.sine(), frequencyMultiple, 0.05f, envelope, i);
			partials.add(harmonicPartial);
		}

		return partials;
	}

	public static void maino(String[] args) {
		float baseFrequency = 311.1270f;
		float timeAtBaseFrequency = .100f;


		String[] allData = """
				AMP 1 = (0,0) (20,305) (36,338) (141,288) (237,80) (360,0)
				FREQ 1 = (0,321) (16,324) (32,312) (109,310) (317,314) (360,310)
				AMP 2 = (0,0) (3,0) (25,317) (39,361) (123,295) (222,40) (326,0) (360,0)
				FREQ 2 = (0,0) (2,0) (3,607) (16,657) (24,621) (133,621) (275,628) (326,628) (327,0) (360,0)
				AMP 3 =(0,0) (2,0) (19,100) (34,369) (111,342) (207,41) (273,0) (360,0)
				FREQ 3 = (0,0) (2,977) (5,782) (15,987) (24,932) (128,932) (217,936) (273,945) (276,0) (360,0)
				AMP 4= (0,0) (3,0) (24,113) (29,257) (118,231) (187,35) (235,0) (360,0)
				FREQ 4 = (0,0) (2,0) (3,718) (16,1335) (24,1243) (108,1240) (199,1248) (235,1248) (236,0) (360,0)
				AMP 5 = (0,0) (27,52) (34,130) (110,126) (191,13) (234,0) (360,0)
				FREQ 5 = (0,1225) (9,1569) (12,1269) (21,1573) (37,1553) (97,1552) (181,1556) (234,1566) (235,0) (360,0)
				AMP 6 = (0,0) (46,83) (64,100) (100,100) (189,11) (221,0) (360,0)
				FREQ 6 = (0,1483) (12,1572) (23,1988) (33,1864) (114,1864) (177,1868) (221,1879)(222,0) (360,0)
				AMP 7=(0,0) (37,39) (45,77) (110,79) (176,11) (205,0) (207,0) (360,0)
				FREQ 7 = (0,1792) (9,1612) (29,2242) (36,2174) (93,2176) (126,2170) (205,2188) (207,0) (360,0)
				AMP 8= (0,0) (2,0) (28,17) (43,71) (109,66) (172,8) (201,0) (360,0)
				FREQ 8= (0,0) (2,1590) (29,2539) (36,2491) (114,2481) (153,2489) (201,2491) (203,0) (360,0)
				AMP 9 = (0,0) (2,0) (29,16) (43,53) (54,66) (105,64) (165,7) (191,0) (360,0)
				FREQ 9 = (0,0) (2,1993) (25,2121) (32,2821) (37,2796) (84,2798) (105,2792) (191,2797) (192,0) (360,0)
				AMP 10 =(0,0) (27,6) (41,25) (56,29) (72,22) (95,24) (180,0) (360,0)
				FREQ 10 = (0,1742) (12,1849) (32,3131) (37,3111) (114,3103) (164,3116) (180,3116) (181,0) (360,0)
				AMP 11 = (0,0) (2,0) (37,6) (55,25) (88,29) (114,28) (164,3) (186,0) (360,0)
				FREQ 11 = (0,0) (2,1398) (41,3419) (42,3419) (91,3419) (106,3406) (150,3421) (186,3421) (187,0) (360,0)
				AMP 12 = (0,0) (7,0) (39,3) (43,8) (88,11) (118,9) (138,3) (165,0) (360,0)
				FREQ 12 = (0,0) (6,0) (7,1806) (23,2942) (36,2759) (37,3746) (60,3723) (84,3731) (110,3721) (1566,3741) (165,3620)
				""".split("\n");

		for (int i = 0; i < allData.length; i++) {
			if(i%2 == 0) {
				//				parsePoints(allData[i]);
			}
			else {
				parseFrequencyPoints(allData[i], Pitch.E4.flatten().getFrequency());
			}
		}

	}

	private static List<Tuple<Float, Float>> parsePoints(String line) {
		String cleanedLine = line.split("=")[1].trim();
		String[] tokens = cleanedLine.replace("(","").replace(")","").split(" ");

		List<Tuple<Float, Float>> points = new ArrayList<>();
		System.out.print("envelopes.add(List.of(");

		for (int i = 0; i < tokens.length; i++) {
			String[] pair = tokens[i].split(",");
			float time = Integer.parseInt(pair[0]);
			float amplitude = Integer.parseInt(pair[1]);
			Tuple<Float, Float> point = new Tuple<>(time/1000, amplitude/1000);
			points.add(point);

			System.out.print("new Tuple<>("+time/1000f+"f, "+amplitude/1000f+"f)");
			if(i != tokens.length - 1) {
				System.out.print(", ");
			}
		}

		System.out.println("));");
		return points;
	}

	private static List<Tuple<Float, Float>> parseFrequencyPoints(String line, float fundamentalFrequency) {
		String cleanedLine = line.split("=")[1].trim();
		String[] tokens = cleanedLine.replace("(","").replace(")","").split(" ");

		List<Tuple<Float, Float>> points = new ArrayList<>();
		System.out.print("frequencyEnvelopes.add(List.of(");

		for (int i = 0; i < tokens.length; i++) {
			String[] pair = tokens[i].split(",");
			float time = Integer.parseInt(pair[0]);
			float frequency = Integer.parseInt(pair[1]);
			float frequencyMultiplier = frequency / fundamentalFrequency;
			Tuple<Float, Float> point = new Tuple<>(time/1000, frequencyMultiplier);
			points.add(point);

			System.out.print("new Tuple<>("+time/1000f+"f, "+(frequencyMultiplier	)+"f)");
			if(i != tokens.length - 1) {
				System.out.print(", ");
			}
		}

		System.out.println("));");
		return points;
	}

}
