package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.controller.manager.FrameManger;
import main.java.controller.manager.ThreadManger;
import main.java.socket.ClientPC;
import main.java.view.frame.HomeFrame;

public class UserEventListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		// 5분동안 화면잠금 (자리이동, 잠깐 일보고 올 때) 5분뒤에는 로그아웃되게 구현할듯
		if (button.getText().equals("일시중지")) {

		}
		// 사용자가 사용중지(컴퓨터 종료 등) 할 때
		else {
			try {
				// 관리자 화면에 있는 사용자 정보 표시를 setOff 설정
				

				// 종료버튼 컴퓨터가 꺼지면서 남은시간 세이브, 컴퓨터 종료는 나중에 구현
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
