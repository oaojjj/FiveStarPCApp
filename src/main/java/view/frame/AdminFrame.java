package main.java.view.frame;

import java.awt.BorderLayout;


import javax.swing.JFrame;

import main.java.common.setting.Setting;
import main.java.view.panel.SeatPanel;

//피시방 컴퓨터 한대당 쓰레드로 표현
//사용자의 로그인 요청 -> 디비에서 로그인체크  -> 체크되면 서버에 알맞은 피시번호랑 연결해라고 요청 -> 연결

public class AdminFrame extends JFrame {
	public AdminFrame() {
		setSize(Setting.getFHDSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		SeatPanel adminPanel = new SeatPanel();

		add(adminPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AdminFrame();
	}
}
