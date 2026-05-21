package com.darzalgames.zalaudiolibrary;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.darzalgames.darzalcommon.math.Fraction;
import com.darzalgames.zalaudiolibrary.amplitude.percussive.ArEnvelope;
import com.darzalgames.zalaudiolibrary.composing.Pitch;
import com.darzalgames.zalaudiolibrary.composing.Song;
import com.darzalgames.zalaudiolibrary.exporting.AlbumExportingInformation;
import com.darzalgames.zalaudiolibrary.exporting.SongExporter;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipeline;
import com.darzalgames.zalaudiolibrary.pipeline.AudioPipelineAPI;
import com.darzalgames.zalaudiolibrary.pipeline.sounds.SimpleSound;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.TwoByteSampleAdapter;
import com.darzalgames.zalaudiolibrary.sfx.SoundEffect;
import com.darzalgames.zalaudiolibrary.synth.SynthFactory;
import com.darzalgames.zalaudiolibrary.ui.KeyboardConfiguration;

public class DigitalSynthesizer extends JFrame implements KeyListener {

	public static final double PADDING_TO_HEIGHT_RATIO = 0.025;
	public static final double FONT_TO_HEIGHT_RATIO = 0.015;
	public static final double SCREEN_TO_HEIGHT_RATIO = 0.75;

	public static final Pitch BOTTOM_PITCH = Pitch.C3;

	public static void main(String[] args) throws Exception {
		DigitalSynthesizer digitalSynthesizer = new DigitalSynthesizer();

//		playSong(new BellSong());
//		playSong(new TrumpetSong());
//		playSong(new A_ThemeSong());
//		playSong(new ManagersVacationSong());
//		playSong(new ScratchPadSong());
//
//		playSong(new SpringSong());
//		playSong(new SummerSong());
//		playSong(new AutumnSong());
//		playSong(new WinterSong());
//		playSong(new RainbowSong());
//		exportAlbum(new RainbowSeedAlbum());
//		playSoundEffects(RainbowSoundEffects.soundEffects());

//		SoundEffectExporter.exportSounds(RainbowSoundEffects.soundEffects(), "Rainbow Seed Sounds");

//		exportAlbum(ScratchPadSong.scratchAlbum());
	}

	private final AudioPipelineAPI audioPipeline;
	private final Map<Character, Runnable> keyRunnables;
	private final Map<Character, Boolean> keyStates;

	private int keyIdCounter;

	public DigitalSynthesizer() throws LineUnavailableException {
		super("Z.A.L. Digital Synthesizer!");

		audioPipeline = new AudioPipeline(getJavaAudioConsumer(), 1f, 1f);
		audioPipeline.requestChangeSong(new BlankSong());

		keyRunnables = new HashMap<>();
		keyStates = new HashMap<>();

		keyIdCounter = 0;

		JFrame.setDefaultLookAndFeelDecorated(true);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int size = (int) (0.8f * gd.getDisplayMode().getHeight());
		setSize(size, size);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new ProgramCleanup());

		setLayout(new BorderLayout(getSmallPadding(), getSmallPadding()));

		JPanel informationPanel = new JPanel();
		add(informationPanel, BorderLayout.NORTH);
		GridLayout gridLayout = new GridLayout(1, 1);
		informationPanel.setLayout(gridLayout);

		JLabel welcomeLabel = new JLabel("Welcome to the Z.A.L. Digital Synthesizer");
		informationPanel.add(welcomeLabel);

		KeyboardConfiguration keyboardConfiguration = new KeyboardConfiguration(BOTTOM_PITCH);
		Map<Character, Pitch> keys = keyboardConfiguration.colemak();

		JPanel keyboard = new JPanel();
		Border buttonPadding = BorderFactory.createEmptyBorder(getSmallPadding(), getLargePadding(), getLargePadding(), getLargePadding());
		keyboard.setBorder(buttonPadding);
		add(keyboard, BorderLayout.CENTER);
		keyboard.setLayout(new GridLayout(3, 8));

		for (Map.Entry<Character, Pitch> entry : keys.entrySet()) {
			Character key = entry.getKey();
			Pitch pitch = entry.getValue();

			Runnable playPitch = () -> {
				String id = "key " + pitch.getName() + " no. " + keyIdCounter;
				keyIdCounter++;
				SimpleSound sine = new SimpleSound(SynthFactory.rationalFrequencyModulator(new Fraction(1), 1f), pitch, t -> 1f, 0.5f, ArEnvelope.linear(0.01f, 0.49f), 0.25f, id);
				SoundEffect pianoKeySound = new SoundEffect("key " + pitch.getName(), sine);
				audioPipeline.requestSoundEffect(pianoKeySound);
			};

			keyRunnables.put(key, playPitch);

			JButton pianoKey = new JButton(pitch.getName());
			pianoKey.addActionListener(
					e -> {
						keyRunnables.get(key).run();
					}
			);
			pianoKey.addKeyListener(this);
			keyboard.add(pianoKey);

		}

		addKeyListener(this);
		audioPipeline.start();
		setVisible(true);
	}

	private class ProgramCleanup extends WindowAdapter {

		@Override
		public void windowClosed(WindowEvent e) {
			audioPipeline.shutdown();
		}
	}

	public static void playSong(Song song) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
		audioPipeline.requestChangeSong(song);

		System.out.println("Playing \"" + song.getSongName() + "\"");

		audioPipeline.start();
		Thread.sleep(Long.MAX_VALUE);
		audioPipeline.shutdown();
	}

	public static void exportAlbum(AlbumExportingInformation album) {
		SongExporter songExporter = new SongExporter();
		songExporter.export(album);
	}

	public static void playSoundEffects(Collection<SoundEffect> soundEffects) {
		soundEffects.forEach(sfx -> {
			try {
				playSoundEffect(sfx);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void playSoundEffect(SoundEffect soundEffect) throws Exception {
		TwoByteSampleAdapter audioConsumer = getJavaAudioConsumer();
		AudioPipeline audioPipeline = new AudioPipeline(audioConsumer, 1f, 1f);
		audioPipeline.requestChangeSong(new BlankSong());

		System.out.println("Playing \"" + soundEffect.getSoundName() + "\"");

		audioPipeline.start();
		audioPipeline.requestSoundEffect(soundEffect);
		Thread.sleep((long) Math.ceil(soundEffect.duration() * 1000));
		audioPipeline.shutdown();
	}

	public static TwoByteSampleAdapter getJavaAudioConsumer() throws LineUnavailableException {
		AudioFormat af = new AudioFormat(AudioConstants.SAMPLING_RATE, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
		line.open(af, AudioConstants.SAMPLING_RATE);

		return new TwoByteSampleAdapter(line);
	}

	private int getSmallPadding() {
		return (int) (PADDING_TO_HEIGHT_RATIO * getHeight());
	}

	private int getLargePadding() {
		return 2 * getSmallPadding();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		if (keyRunnables.containsKey(key)) {
			boolean keyAlreadyPressed = keyStates.getOrDefault(key, false);
			if (!keyAlreadyPressed) {
				keyStates.put(key, true);
				keyRunnables.get(key).run();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		boolean keyAlreadyPressed = keyStates.getOrDefault(key, true);
		if (keyAlreadyPressed) {
			keyStates.put(key, false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
