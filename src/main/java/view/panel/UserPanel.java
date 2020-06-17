package main.java.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.controller.UserEventListener;
import main.java.controller.manager.FrameManger;
import main.java.controller.manager.ThreadManger;
import main.java.thread.FeeThread;

public class UserPanel extends JPanel {
	JButton btStop, btShutdown;
	JPanel infoPanel, btmPanel;
	private JLabel helloLabel, nameLabel, userNameLabel, timeLabel, feeLabel, moneyLabel, userTimeLabel;

	String userName;
	String userFee;
	String userTime;

	FeeThread feeThread;

	public UserPanel() {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.DARK_GRAY, 8, true));

		initUserInfo();

		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 2, 10, 10));

		// 피시방 이름 같은 거도 변수로 다빼놔야 나중에 유지보수가 편할듯
		// 예를들어 설정을 만들어서 설정에서 바꿀 수 있게
		helloLabel = new JLabel(FrameManger.getHomeFrame().getLoginPanel().getPcNumber() + "번 PC 입니다. 어서오세요~",
				JLabel.CENTER);
		helloLabel.setFont(new Font("바탕", Font.BOLD, 20));

		nameLabel = new JLabel("사용자", JLabel.CENTER);
		userNameLabel = new JLabel(userName + "님");
		nameLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userNameLabel.setFont(Setting.getBasicFont());
		infoPanel.add(nameLabel);
		infoPanel.add(userNameLabel);

		// 후불도 하려고 했으나.. 요즘은 선불만 있어서 선불만 만듬
		moneyLabel = new JLabel("요금", JLabel.CENTER);
		feeLabel = new JLabel("선불");
		moneyLabel.setFont(new Font("바탕", Font.BOLD, 16));
		feeLabel.setFont(Setting.getBasicFont());
		infoPanel.add(moneyLabel);
		infoPanel.add(feeLabel);

		timeLabel = new JLabel("시간", JLabel.CENTER);
		userTimeLabel = new JLabel("00:00:00");
		timeLabel.setFont(new Font("바탕", Font.BOLD, 16));
		userTimeLabel.setFont(Setting.getBasicFont());
		infoPanel.add(timeLabel);
		infoPanel.add(userTimeLabel);

		btmPanel = new JPanel();
		btStop = new JButton("사용중지");

		btShutdown = new JButton(new ImageIcon(getClass().getClassLoader().getResource("button/shutdown_button.png")));
		btShutdown.setBorderPainted(false);
		btShutdown.setFocusPainted(false);
		btShutdown.setContentAreaFilled(false);
		btShutdown.setPressedIcon(
				new ImageIcon(getClass().getClassLoader().getResource("button/common_button_pressed.png")));

		UserEventListener userEventListener = new UserEventListener();

		// 스탑 버튼은 자리이동이나 5분정도 시간 멈추면서 화면도 잠김
		btStop.addActionListener(userEventListener);
		// 종료 버튼은 시간 save되고 컴퓨터 종료
		btShutdown.addActionListener(userEventListener);

		// padding인데 더좋은 방법이 있나봐야함
		btmPanel.add(new JLabel("                                                "));
		btmPanel.add(btStop);
		btmPanel.add(btShutdown);
		btmPanel.setBackground(Color.darkGray);

		add(helloLabel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
		add(btmPanel, BorderLayout.SOUTH);

		// 쓰레드 (요금, 사용시간)
		feeThread = new FeeThread(userTimeLabel);
		feeThread.start();
		ThreadManger.setFeeThread(feeThread);

	}

	// 중복되는 메소드 (signUpPanel)
	JLabel createLabel(String name) {
		JLabel j = new JLabel(name, JLabel.CENTER);
		j.setFont(Setting.getBasicFont());
		return j;
	}

	private void initUserInfo() {
		userName = MemberDTO.getMemberDTO().getName();
	}

}
