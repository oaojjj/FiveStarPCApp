package main.java.view.frame;

import javax.swing.JFrame;

public class UserFrame extends JFrame {
	private static UserFrame userFrame;

	public UserFrame() {
		setTitle("피시방에 오신걸 환영합니다^^");
		setSize(400, 300);
		// setLocation(Setting.SCREEN_WIDTH - 300, 50);
		setLocation(1920 - 430, 30);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setUndecorated(true);

		setVisible(true);
	}

	public static void main(String[] args) {
		userFrame = new UserFrame();
	}

	public static UserFrame getUserFrame() {
		return userFrame;
	}

}
