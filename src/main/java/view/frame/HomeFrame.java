package main.java.view.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.view.panel.LoginPanel;
import main.java.view.panel.SignUpPanel;

public class HomeFrame extends JFrame {
	public static int SCREEN_WIDTH, SCREEN_HEIGHT;

	private JLayeredPane lp;

	private static HomeFrame homeFrame;
	private static LoginPanel loginPanel;
	private static SignUpPanel signUpPanel;

	public static void main(String[] args) {
		homeFrame = new HomeFrame();
	}

	public HomeFrame() {
		setTitle("오성PC방");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initSetting();
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setUndecorated(false); // 배경 테두리 제거
		setLayout(null);

		// UI
		// lp = new JLayeredPane();
		// lp.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		// lp.setLayout(null);

		loginPanel = new LoginPanel();
		// lp.add(loginPanel);
		signUpPanel = new SignUpPanel();

		add(signUpPanel);
		signUpPanel.setVisible(false);

		add(loginPanel);
		setVisible(true);
	}

	private void initSetting() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		Setting.setScreenSize(res.width, res.height);
		SCREEN_WIDTH = res.width;
		SCREEN_HEIGHT = res.height;
	}

	public static LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public static HomeFrame getHomeFrame() {
		return homeFrame;
	}

	public static SignUpPanel getSignUpPanel() {
		return signUpPanel;
	}

}
