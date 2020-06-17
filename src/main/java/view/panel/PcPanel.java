package main.java.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.java.common.setting.Setting;

/*
 * -사용자 정보-
 * ?번 피시 - 0
 * 사용여부 - 1
 * 사용자 이름 - 2
 * 사용자 남은 시간 - 3
 * 추가 요금? - 4 확정x
 */

public class PcPanel extends JPanel {
	Image image;
	JPanel userInfoPanel;
	JLabel userInfoLabel[];

	int comNum;

	public PcPanel(int i) {
		comNum = i;
		setLayout(null);
		JPanel imgPanel = new ImgPanel();
		imgPanel.setBounds(0, 0, 220, 150);
		imgPanel.setOpaque(false);
		setImg("seat_off_background2.png");

		// 사용자 정보 패널
		userInfoPanel = new JPanel();
		userInfoPanel.setBounds(0, 5, 190, 130);
		userInfoPanel.setLayout(new GridLayout(5, 1));

		userInfoLabel = new JLabel[5];

		createUserInfoForm();

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);
		layeredPane.setOpaque(false);

		layeredPane.add(imgPanel, new Integer(0), 0);
		layeredPane.add(userInfoPanel, new Integer(1), 0);

		add(layeredPane);

		setVisible(true);
		setOpaque(false);
		setFocusable(true);
	}

	void createUserInfoForm() {
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				// 몇번 피시
				userInfoLabel[0] = new JLabel(String.valueOf("   " + comNum + "번 PC"));
				userInfoLabel[0].setForeground(Color.WHITE);
				userInfoLabel[0].setFont(new Font("바탕", Font.BOLD, 16));
			} else if (i == 1) {
				userInfoLabel[1] = new JLabel("사용가능", JLabel.RIGHT);
				userInfoLabel[1].setForeground(Color.ORANGE);
				userInfoLabel[1].setFont(new Font("바탕", Font.BOLD, 14));
			} else {
				userInfoLabel[i] = new JLabel("-- --", JLabel.RIGHT);
				userInfoLabel[i].setForeground(Color.WHITE);
				userInfoLabel[i].setFont(Setting.getUserInfofont());

			}

			userInfoPanel.add(userInfoLabel[i]);
		}
		userInfoPanel.setOpaque(false);

	}

	public void setImg(String path) {
		URL imageURL = getClass().getClassLoader().getResource(path);
		ImageIcon imageIcon = new ImageIcon(imageURL);
		image = imageIcon.getImage();
		repaint();
	}

	// 이미지 패널
	class ImgPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, null);
		}
	}

	public void setOn(String name, String time) {
		setImg("seat_on_background.png");
		userInfoLabel[1].setText("사용중");
		userInfoLabel[2].setText(name);
		userInfoLabel[2].setForeground(Color.BLACK);
		// 3번 사용자 시간은 쓰레드로 처리해야함
		userInfoLabel[3].setText(time);
		userInfoLabel[3].setForeground(Color.BLACK);
	}

	public void setOff(String name, String time) {
		setImg("seat_on_background.png");
		userInfoLabel[1].setText("사용중");
		userInfoLabel[1].setForeground(Color.BLACK);
		userInfoLabel[2].setText(name);
		userInfoLabel[2].setForeground(Color.BLACK);
		// 3번 사용자 시간은 쓰레드로 처리해야함
	}
}
