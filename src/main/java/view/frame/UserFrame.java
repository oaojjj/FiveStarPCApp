package main.java.view.frame;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import main.java.common.setting.Setting;
import main.java.view.panel.BackgroundPanel;
import main.java.view.panel.UserPanel;

public class UserFrame extends JFrame {
	public static int SCREEN_WIDTH = 350, SCREEN_HEIGH = 200;

	UserPanel userPanel;
	BackgroundPanel backgroundPanel;

	public UserFrame() {
		setSize(SCREEN_WIDTH, SCREEN_HEIGH);
		setLocation(Setting.SCREEN_WIDTH - 370, 20);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		userPanel = new UserPanel();

		setBackgroundImage("userpanel_background2.png");
		backgroundPanel.add(userPanel);

		setVisible(true);
	}

	// 배경 이미지 만들기
	private void setBackgroundImage(String path) {
		URL imageURL = getClass().getClassLoader().getResource(path);
		ImageIcon imageIcon = new ImageIcon(imageURL);
		backgroundPanel = new BackgroundPanel(imageIcon.getImage(), 350, 200);
		backgroundPanel.setBackground(new Color(54, 62, 72));
		add(backgroundPanel);
	}

	public UserPanel getUserPanel() {
		return userPanel;
	}

}
