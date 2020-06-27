package main.java.view.panel;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.java.common.setting.Setting;

public class SeatPanel extends JPanel {
	BackgroundPanel backgroundPanel;
	public PcPanel pcPanel[];

	int panalCenterX = (1920 - 1600) / 2;
	int panelCenterY = (1080 - 900) / 2;

	public SeatPanel() {
		setSize(Setting.getFHDSize());
		setLayout(null);
		setBackground(new Color(54, 62, 72));

		createPcPanel();
		setBackgroundImage("admin_background2.png");
	}

	// 배경 이미지 만들기
	private void setBackgroundImage(String path) {
		URL imageURL = getClass().getClassLoader().getResource(path);
		ImageIcon imageIcon = new ImageIcon(imageURL);
		BackgroundPanel background = new BackgroundPanel(imageIcon.getImage(), 1600, 900);
		background.setBounds(panalCenterX, panelCenterY, 1600, 900);
		background.setBackground(Color.BLACK);
		add(background);
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

	public void setOn(int pc, String name, int time) {
		pcPanel[pc].setOn(name, time);
	}

	public void setOff(int pc) {
		pcPanel[pc].setOff();
	}

	public PcPanel[] getPcPanel() {
		return pcPanel;
	}

	public void setPcPanel(PcPanel[] pcPanel) {
		this.pcPanel = pcPanel;
	}
}
