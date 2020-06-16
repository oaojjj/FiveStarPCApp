package main.java.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.java.common.setting.Setting;

/*
 * -사용자 정보-
 * 1번 피시+사용여부
 * 사용자 이름
 * 사용자 남은 시간
 * 추가 요금?
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
		setImg("src/main/resource/seat_off_background.png");

		// 사용자 정보 패널
		userInfoPanel = new JPanel();
		userInfoPanel.setBounds(0, 2, 215, 130);
		userInfoPanel.setLayout(new GridLayout(5, 1));

		userInfoLabel = new JLabel[4];

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
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				// 몇번 피시
				userInfoLabel[i] = new JLabel(String.valueOf("   " + comNum + "번 피시"));
				userInfoLabel[i].setForeground(Color.WHITE);
			} else {
				userInfoLabel[i] = new JLabel("", JLabel.CENTER);
				userInfoLabel[i].setForeground(Color.BLACK);

			}
			userInfoLabel[i].setFont(Setting.getUserInfofont());
			userInfoPanel.add(userInfoLabel[i]);
		}
		userInfoPanel.setOpaque(false);

	}

	public void setImg(String path) {
		try {
			image = ImageIO.read(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
