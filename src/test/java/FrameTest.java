package test.java;

import javax.swing.JFrame;

import main.java.view.frame.UserFrame;
import main.java.view.panel.UserPanel;

public class FrameTest extends JFrame {
	public FrameTest() {
		setSize(400, 300);
		setLocation(1920 - 410, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setUndecorated(true);

		UserPanel panel = new UserPanel();
		panel.setVisible(true);
		add(panel);

		setVisible(true);
	}

	public static void main(String[] args) {
		new UserFrame();
	}
}
