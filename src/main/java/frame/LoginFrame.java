package main.java.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.java.frame.panel.LoginPanel;

public class LoginFrame extends JFrame {
	public static int SCREEN_WIDTH, SCREEN_HEIGHT;
	JLayeredPane lp;
	int pcNumber = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new LoginFrame();
	}

	public LoginFrame() {
		setTitle("오성PC방");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initScreenSize();
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setUndecorated(false); // 배경 테두리
		setLayout(null);

		// UI
		lp = new JLayeredPane();
		lp.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		lp.setLayout(null);

		introLabel();

		LoginPanel loginPanel = new LoginPanel();
		lp.add(loginPanel);

		add(lp);
		setVisible(true);
	}

	void introLabel() {
		JLabel pcNumLabel = new JLabel(pcNumber + "번 PC");
		pcNumLabel.setFont(new Font("바탕", Font.BOLD, 70));
		pcNumLabel.setBounds(SCREEN_WIDTH / 2 + SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2 - 400, 400, 200);
		lp.add(pcNumLabel);
	}

	// 윈도우 전체사이즈 알아내는 메소드
	private void initScreenSize() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = res.width;
		SCREEN_HEIGHT = res.height;
		// SCREEN_WIDTH=1680;
		// SCREEN_HEIGHT=1050;
	}

}
