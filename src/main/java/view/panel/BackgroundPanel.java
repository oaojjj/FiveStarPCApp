package main.java.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	Image image;
	int width, height;

	public BackgroundPanel(Image i, int w, int h) {
		image = i;
		width = w;
		height = h;
	}

	public BackgroundPanel(Image i) {
		image = i;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (width > 0 && height > 0)
			g.drawImage(image, 0, 0, width, height, null);
		else
			g.drawImage(image, 0, 0, null);
	}
}
