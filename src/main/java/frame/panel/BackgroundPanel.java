package main.java.frame.panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	Image image;
	int width, height;

	public BackgroundPanel(Image i, int w, int h) {
		image = i;
		width = w;
		height = h;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, width, height, null);
	}
}
