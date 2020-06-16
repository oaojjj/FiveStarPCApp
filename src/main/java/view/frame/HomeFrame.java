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

	private LoginPanel loginPanel;
	private SignUpPanel signUpPanel;

	public HomeFrame() {
		setTitle("오성PC방");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initSetting();
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setUndecorated(true); // 배경 테두리 제거
		setLayout(null);

		loginPanel = new LoginPanel();

		signUpPanel = new SignUpPanel();
		signUpPanel.setVisible(false);

		add(signUpPanel);
		add(loginPanel);
		setVisible(true);
	}

	// PC방 컴퓨터수 세팅
	private void initSetting() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		Setting.setScreenSize(res.width, res.height);
		Setting.setPC(Setting.pcCount);
		SCREEN_WIDTH = res.width;
		SCREEN_HEIGHT = res.height;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public SignUpPanel getSignUpPanel() {
		return signUpPanel;
	}

}
