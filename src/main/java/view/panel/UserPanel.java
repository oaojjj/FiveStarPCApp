package main.java.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.controller.UserEventListener;
import main.java.controller.manager.FrameManger;
import main.java.thread.FeeThread;

public class UserPanel extends JPanel {
	JButton btStop, btShutdown, btOrder, btMsg;
	JPanel infoPanel, btPanel, eastPanel;

	Color customOrange = new Color(246, 89, 69);
	Color customGray = new Color(45, 62, 72);

	private JLabel helloLabel, nameLabel, userNameLabel, timeLabel, feeLabel, moneyLabel, userTimeLabel;

	String userName;
	String userFee;
	String userTime;

	FeeThread feeThread;

	public UserPanel() {
		setLayout(new BorderLayout(1, 1));
		setPreferredSize(new Dimension(350, 200));

		initUserInfo();

		// 피시방 번호 ui
		JPanel helloPanel = new JPanel();
		helloLabel = new JLabel("오성PC " + "No." + FrameManger.getHomeFrame().getLoginPanel().getPcNumber());
		helloLabel.setFont(new Font("바탕", Font.BOLD, 16));
		helloLabel.setForeground(Color.WHITE);

		helloPanel.add(helloLabel);
		helloPanel.setBackground(customGray);
		helloPanel.setBorder(new LineBorder(Color.GRAY, 2));

		// 사용자 정보 ui
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 2, 0, 10));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(new LineBorder(Color.GRAY, 2));

		nameLabel = new JLabel("사용자", JLabel.CENTER);
		userNameLabel = new JLabel(userName + "님", JLabel.RIGHT);
		nameLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userNameLabel.setFont(Setting.getBasicFont());

		// 후불도 하려고 했으나.. 요즘은 선불만 있어서 선불만 만듬
		moneyLabel = new JLabel("요금", JLabel.CENTER);
		feeLabel = new JLabel("선불", JLabel.RIGHT);
		moneyLabel.setFont(new Font("바탕", Font.BOLD, 16));
		feeLabel.setFont(Setting.getBasicFont());

		timeLabel = new JLabel("시간", JLabel.CENTER);
		userTimeLabel = new JLabel("00:00:00", JLabel.RIGHT);
		timeLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userTimeLabel.setFont(Setting.getBasicFont());

		infoPanel.add(nameLabel);
		infoPanel.add(userNameLabel);
		infoPanel.add(moneyLabel);
		infoPanel.add(feeLabel);
		infoPanel.add(timeLabel);
		infoPanel.add(userTimeLabel);

		// 버튼ui
		btPanel = new JPanel();
		btPanel.setBorder(BorderFactory.createEmptyBorder(0, 170, 10, 0));
		btPanel.setBackground(customGray);
		btStop = new JButton("일시중지");
		btStop.setBackground(Color.LIGHT_GRAY);

		btShutdown = new JButton(new ImageIcon(getClass().getClassLoader().getResource("button/shutdown_button.png")));
		setBtnSetting(btShutdown);
		btShutdown.setPressedIcon(
				new ImageIcon(getClass().getClassLoader().getResource("button/common_button_pressed.png")));

		UserEventListener userEventListener = new UserEventListener();

		// 스탑 버튼은 자리이동이나 5분정도 시간 멈추면서 화면도 잠김
		// TODO 나중에 구현
		btStop.addActionListener(userEventListener);
		// 종료 버튼은 시간 save되고 컴퓨터 종료
		btShutdown.addActionListener(userEventListener);

		btPanel.add(btStop);
		btPanel.add(btShutdown);

		// east
		eastPanel = new JPanel();
		eastPanel.setBackground(customGray);
		eastPanel.setLayout(new GridLayout(2, 1, 0, 10));
		eastPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 15));

		btOrder = new JButton("주문");
		btOrder.addActionListener(userEventListener);

		btMsg = new JButton("문의");
		btMsg.addActionListener(userEventListener);

		eastPanel.add(btOrder);
		eastPanel.add(btMsg);

		add(helloPanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
		add(btPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);

		// 쓰레드 (사용시간)
		feeThread = new FeeThread(userTimeLabel, MemberDTO.getMemberDTO().getSaveTime(), true);
		feeThread.start();
		// ThreadManger.setFeeThread(feeThread);
	}

	void setBtnSetting(JButton b) {
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
	}

	JLabel createLabel(String name) {
		JLabel j = new JLabel(name, JLabel.CENTER);
		j.setFont(Setting.getBasicFont());
		return j;
	}

	private void initUserInfo() {
		userName = MemberDTO.getMemberDTO().getName();
	}

}