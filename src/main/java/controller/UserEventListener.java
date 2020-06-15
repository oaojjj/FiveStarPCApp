package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.view.frame.HomeFrame;

public class UserEventListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		// 5분동안 화면잠금 (자리이동, 잠깐 일보고 올 때) 5분뒤에는 꺼짐
		if (button.getText().equals("사용중지")) {

		} else {
			// 종료버튼 컴퓨터가 꺼지면서 남은시간 세이브
			// 컴퓨터 종료는 나중에 구현
			try {
				// 종료버튼을 눌렀을때 쓰레드 중지시키고 남은 시간 얻어와서 업데이트
				int time = ThreadManger.getFeeThread().threadStop().getMyDate().getSaveTime();
				DBController.saveTime(MemberDTO.getMemberDTO().getId(), time);
				// 종료 구현하기전에 그냥 홈 프레임으로 넘어가게
				FrameManger.getUserFrame().dispose();
				HomeFrame homeFrame = new HomeFrame();
				FrameManger.setHomeFrame(homeFrame);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
