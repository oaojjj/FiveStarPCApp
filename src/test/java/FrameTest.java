package test.java;

import javax.swing.JFrame;

import main.java.view.panel.SignUpPanel;

public class FrameTest extends JFrame {
	public FrameTest() {
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		SignUpPanel signUpPanel = new SignUpPanel();
		add(signUpPanel);

		setVisible(true);
	}

	public static void main(String[] args) {
		new FrameTest();
	}
}
