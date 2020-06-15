package main.java.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.java.common.setting.Setting;

public class UserPanel extends JPanel implements ActionListener {
	JButton btStop, btShutdown;

	public UserPanel() {
		setSize(400, 300);
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.DARK_GRAY, 8, true));

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 2, 10, 10));

		JLabel helloLabel = new JLabel("오성PC방 입니다. 어서오세요~", JLabel.CENTER);
		helloLabel.setFont(new Font("바탕", Font.BOLD, 20));

		JLabel nameLabel = new JLabel("사용자", JLabel.CENTER);
		JLabel userNameLabel = new JLabel("권오성님");
		nameLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userNameLabel.setFont(Setting.getBasicFont());
		infoPanel.add(nameLabel);
		infoPanel.add(userNameLabel);

		// 시간과 요금은 쓰레드 써야함
		JLabel titmeLabel = new JLabel("요금", JLabel.CENTER);
		JLabel userMoneyLabel = new JLabel("1000원");
		titmeLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userMoneyLabel.setFont(Setting.getBasicFont());
		infoPanel.add(titmeLabel);
		infoPanel.add(userMoneyLabel);

		JLabel moneyLabel = new JLabel("시간", JLabel.CENTER);
		JLabel userTimeLabel = new JLabel("00:00:00");
		moneyLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userTimeLabel.setFont(Setting.getBasicFont());
		infoPanel.add(moneyLabel);
		infoPanel.add(userTimeLabel);

		JPanel btmPanel = new JPanel();
		btStop = new JButton("사용중지");
		
		btShutdown = new JButton(new ImageIcon("src/main/resource/button/shutdown_button.png"));
		btShutdown.setBorderPainted(false);
		btShutdown.setFocusPainted(false);
		btShutdown.setContentAreaFilled(false);
		btShutdown.setPressedIcon(new ImageIcon("src/main/resource/button/button_pressed.png"));

		btStop.addActionListener(this);
		btShutdown.addActionListener(this);

		btmPanel.add(new JLabel("                                                                  "));
		btmPanel.add(btStop);
		btmPanel.add(btShutdown);
		btmPanel.setBackground(Color.darkGray);

		add(helloLabel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
		add(btmPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
