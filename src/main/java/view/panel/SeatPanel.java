package main.java.view.panel;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.java.common.setting.Setting;

public class SeatPanel extends JPanel {
	BackgroundPanel backgroundPanel;
	PcPanel pcPanel[];

	int panalCenterX = (1920 - 1600) / 2;
	int panelCenterY = (1080 - 900) / 2;

	public SeatPanel() {
		setSize(Setting.getFHDSize());
		setLayout(null);
		setBackground(new Color(54,62,72));

		createPcPanel();
		// PcPanel test = new PcPanel(1);
		// test.setBounds(panalCenterX + 35, panelCenterY + 100, 215, 150);

		// add(test);
		setBackgroundImage("src/main/resource/admin_background2.png");
	}

	private void setBackgroundImage(String path) {
		try {
			// 배경 이미지 만들기
			Image image = ImageIO.read(new File(path));
			BackgroundPanel background = new BackgroundPanel(image, 1600, 900);
			background.setBounds(panalCenterX, panelCenterY, 1600, 900);
			background.setBackground(Color.BLACK);
			add(background);

		} catch (IOException e) {
			System.out.println("배경 이미지 불러오기 실패");
		}
	}

	private void createPcPanel() {
		int addX = 0, addY = 0;
		int paddingX = 220, paddingY = 190;
		int cnt = 0;
		// 표현할 수 있는 pc수는 21대
		pcPanel = new PcPanel[Setting.pcCount];
		for (int i = 0; i < Setting.pcCount; i++) {
			pcPanel[i] = new PcPanel(i + 1);
			pcPanel[i].setBounds(panalCenterX + 35 + addX, panelCenterY + 100 + addY, 215, 150);
			add(pcPanel[i]);
			cnt++;
			if (cnt > 6) {
				addY += paddingY;
				addX = 0;
				cnt = 0;
			} else {
				addX += paddingX;
			}
		}
	}

}
