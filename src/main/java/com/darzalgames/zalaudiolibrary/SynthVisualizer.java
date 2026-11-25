package com.darzalgames.zalaudiolibrary;

import java.awt.*;
import java.awt.geom.AffineTransform;

import com.darzalgames.zalaudiolibrary.demosongs.SynthTesterSong;
import com.darzalgames.zalaudiolibrary.pipeline.AudioActor;
import com.darzalgames.zalaudiolibrary.synth.Synth;

public class SynthVisualizer extends Frame implements AudioActor {

	private static final int INTERPOLATION_RESOLUTION = 100;

	private Dimension bufferDimensions;
	private Image bufferImage;
	private Graphics2D bufferGraphics;

	private final SynthTesterSong synthSupplier;
	private Synth activeSynth;

	public SynthVisualizer(SynthTesterSong synthSupplier) {
		super("Z.A.L. Synth Visualizer");

		setSize(500, 500);
		setLocationRelativeTo(null);
		bufferDimensions = getSize();
		setBackground(Color.DARK_GRAY);
		setUndecorated(true);

		this.synthSupplier = synthSupplier;
		activeSynth = synthSupplier.getActiveSynth(0);

		setVisible(true);
	}

	// We are overriding this to enforce buffering
	@Override
	public final void update(Graphics g) {
		paint(g);
	}

	@Override
	public final void paint(Graphics g) {
		if (!bufferDimensions.equals(getSize()) || bufferImage == null || bufferGraphics == null) {
			resetBuffer();
		}

		if (bufferGraphics != null) {
			// this clears the offscreen image, not the onscreen one
			AffineTransform trans = bufferGraphics.getTransform();
			int offSetX = (int) trans.getTranslateX();
			int offSetY = (int) trans.getTranslateY();
			bufferGraphics.clearRect(-offSetX - 50, -offSetY - 50, bufferDimensions.width + 50, bufferDimensions.height + 50);

			// calls the paintbuffer method with
			// the offscreen graphics as a param
			paintVisualizer(bufferGraphics);

			// we finally paint the offscreen image onto the onscreen image
			g.drawImage(bufferImage, 0, 0, this);
		}

	}

	public void paintVisualizer(Graphics2D g) {
		int width = bufferDimensions.width;
		int height = bufferDimensions.height;

		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawLine(width / 2, 0, width / 2, height);
		g.drawLine(0, height / 2, width, height / 2);

		g.setColor(Color.ORANGE);
		g.setStroke(new BasicStroke(3));

		for (int i = 0; i != INTERPOLATION_RESOLUTION; i++) {
			float t = i / (float) (INTERPOLATION_RESOLUTION); // t goes from 0 to 1
			float tPrime = (i + 1) / (float) (INTERPOLATION_RESOLUTION);
			float synthValue = activeSynth.f(t);
			float synthValuePrime = activeSynth.f(tPrime);

			int x0 = (int) (t * width);
			int x1 = (int) (tPrime * width);
			int y0 = (int) ((synthValue / 2f + 0.5f) * height);
			int y1 = (int) ((synthValuePrime / 2f + 0.5f) * height);

			g.drawLine(x0, y0, x1, y1);
		}

	}

	private final void resetBuffer() {
		// always keep track of the image size
		bufferDimensions = getSize();

		// clean up the previous image
		if (bufferGraphics != null) {
			bufferGraphics.dispose();
			bufferGraphics = null;
		}
		if (bufferImage != null) {
			bufferImage.flush();
			bufferImage = null;
		}

		// create the new image with the size of the panel
		bufferImage = createImage(bufferDimensions.width, bufferDimensions.height);
		bufferGraphics = (Graphics2D) bufferImage.getGraphics();
		bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	// --- Audio ---

	@Override
	public void update(float delta) {
		repaint();
	}

	@Override
	public void updateBps(float newBps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBeat(int beatNumber) {
		activeSynth = synthSupplier.getActiveSynth(beatNumber);
	}

}
