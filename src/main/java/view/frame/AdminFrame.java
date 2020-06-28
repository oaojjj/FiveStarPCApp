package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.common.setting.Setting;
import main.java.controller.AdminEventListener;
import main.java.view.panel.SeatPanel;

//피시방 컴퓨터 한대당 쓰레드로 표현
//사용자의 로그인 요청 -> 디비에서 로그인체크  -> 체크되면 서버에 알맞은 피시번호랑 연결해라고 요청 -> 연결

public class AdminFrame extends JFrame {
	private SeatPanel seatPanel;
	private JPanel eventPaenl;

	private JButton btMemberInfo, btItem;

	public AdminFrame() {
		setSize(Setting.getFHDSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setUndecorated(false); // 배경 테두리 제거

		seatPanel = new SeatPanel();

		eventPaenl = new JPanel();
		eventPaenl.setLayout(new GridLayout(3, 1, 20, 20));
		eventPaenl.setBorder(BorderFactory.createEmptyBorder(200, 20, 500, 20));
		eventPaenl.setBackground(new Color(54, 62, 72));

		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("button/member_info_button2.png"));
		Image orignImage = imageIcon.getImage();
		Image changedImage = orignImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

		ImageIcon imageIcon1 = new ImageIcon(
				getClass().getClassLoader().getResource("button/member_info_button_pressed.png"));
		Image orignImage1 = imageIcon1.getImage();
		Image changedImage1 = orignImage1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

		btMemberInfo = new JButton("회원조회", new ImageIcon(changedImage));
		btMemberInfo.setPressedIcon(new ImageIcon(changedImage1));
		btMemberInfo.setForeground(Color.WHITE);
		btMemberInfo.setBackground(new Color(47, 50, 60));
		btMemberInfo.setPreferredSize(new Dimension(40, 40));
		btMemberInfo.setVerticalTextPosition(JButton.BOTTOM);
		btMemberInfo.setHorizontalTextPosition(JButton.CENTER);
		btMemberInfo.setFont(Setting.getBasicFont());
		btMemberInfo.setBorderPainted(false);
		btMemberInfo.setFocusPainted(false);
		// btMemberInfo.setContentAreaFilled(false); 버튼 모양? 없애기

		btItem = new JButton("재고물품");
		btItem.setForeground(Color.white);
		btItem.setBorderPainted(false);
		btItem.setFocusPainted(false);
		btItem.setBackground(new Color(47, 50, 60));
		btItem.setFont(Setting.getBasicFont());

		AdminEventListener adminEventListener = new AdminEventListener();
		btMemberInfo.addActionListener(adminEventListener);
		btItem.addActionListener(adminEventListener);
		
		eventPaenl.add(btMemberInfo);
		eventPaenl.add(btItem);

		add(eventPaenl, BorderLayout.EAST);
		add(seatPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	public SeatPanel getSeatPanel() {
		return seatPanel;
	}

}
