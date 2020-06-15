package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.common.utill.DBManager;
import main.java.view.frame.UserFrame;

/*
 * 로그인, 회원가입, 회원찾기 버튼 이벤트 리스너
 */
public class HomeEventListener implements ActionListener {
	DBController dbcon = DBManager.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();

		if (bt.getText().equals("로그인")) {
			// 로그인 정보를 요청하면 id, password가 데이터로 넘어옴
			String[] info = FrameManger.getHomeFrame().getLoginPanel().getLoginInfo();
			
			try {
				if (DBController.checkLogin(info[0], info[1])) {
					
					// 회원 정보를 불러와서 멤버 정보에 저장
					MemberDTO.setMemberDTO(dbcon.selectId(info[0]));
					if (MemberDTO.getMemberDTO().getSaveTime() == 0) {
						JOptionPane.showMessageDialog(FrameManger.getHomeFrame(),
								"<html>남은 시간이 없습니다.<br>충전하고 다시 시도 해주세요.<html>");
					} else {
						JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "로그인이 되었습니다.");

						// 로그인 완료 후 프레임 종료 유저프레임 생성
						FrameManger.getHomeFrame().dispose();
						UserFrame userFrame = new UserFrame();
						FrameManger.setUserFrame(userFrame);
					}
				} else {
					JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "아이디 또는 비밀번호가 틀렸습니다.");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "관리자에게 문의해주세요.");
			}
		} else if (bt.getText().equals("회원가입")) {
			FrameManger.getHomeFrame().getLoginPanel().setVisible(false);
			FrameManger.getHomeFrame().getSignUpPanel().setVisible(true);
		} else if (bt.getText().equals("회원찾기")) {
			// TODO 회원찾기 나중에 구현
		}
	}
}
