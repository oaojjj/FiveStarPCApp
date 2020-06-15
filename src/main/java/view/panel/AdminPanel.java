package main.java.view.panel;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.java.common.setting.Setting;

public class AdminPanel extends JPanel {
	BackgroundPanel backgroundPanel;

	public AdminPanel() {
		setSize(Setting.getFHDSize());
		setLayout(null);
		setBackground(Color.BLACK);

		setBackgroundImage("src/main/resource/admin_background2.png");
	}

	private void setBackgroundImage(String path) {
		try {
			// 배경 이미지 만들기
			Image image = ImageIO.read(new File(path));
			BackgroundPanel background = new BackgroundPanel(image, 1600, 900);
			background.setBounds((1920 - 1600) / 2, (1080 - 900) / 2, 1600, 900);
			background.setBackground(Color.DARK_GRAY);
			add(background);

		} catch (IOException e) {
			System.out.println("배경 이미지 불러오기 실패");
		}
	}

}
