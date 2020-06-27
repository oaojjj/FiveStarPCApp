package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.java.common.dao.DBController;
import main.java.common.dao.DBManager;
import main.java.common.dto.MemberDTO;
import main.java.controller.manager.FrameManger;
import main.java.socket.ClientPC;
import main.java.socket.ServerPC;
import main.java.view.frame.AdminFrame;
import main.java.view.frame.HomeFrame;
import main.java.view.frame.UserFrame;

/*
 * 로그인, 회원가입, 회원찾기 버튼 이벤트 리스너
 */

// TODO 같은 번호를 골랐을 경우 처리 해야함
public class HomeEventListener implements ActionListener {
	DBController dbcon = DBManager.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();

		if (bt.getText().equals("로그인")) {

			// 로그인 정보를 요청하면 pc번호, id, password가 데이터로 넘어옴
			String[] info = FrameManger.getHomeFrame().getLoginPanel().getLoginInfo();
			String pc = info[0];
			String id = info[1];
			String password = info[2];

			// 피시 번호 골랐는지 체크
			if (pc == null && !id.equals("admin")) {
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "피시번호를 선택해주세요.");
				return;
			}

			try {
				if (DBController.checkLogin(id, password)) {

					// 사용자가 admin인지 체크
					if (adminLogin(id))
						return;

					// 회원 정보를 불러와서 멤버 정보에 저장
					MemberDTO.setMemberDTO(dbcon.selectId(id));

					// 사용자의 남은 시간이 없는 경우
					if (MemberDTO.getMemberDTO().getSaveTime() == 0) {
						JOptionPane.showMessageDialog(FrameManger.getHomeFrame(),
								"<html>남은 시간이 없습니다.<br>충전하고 다시 시도 해주세요.<html>");
					} else {
						// 사용시간이 남아있으면 로그인 진행
						// 서버로 접속한 PC정보와 사용자의 정보 전송
						// 클라이언트 소켓 생성
						ClientPC clientSocket = new ClientPC(pc, MemberDTO.getMemberDTO().getName(),
								MemberDTO.getMemberDTO().getSaveTime());
						clientSocket.start();

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

	// 관리자 로그인 메소드
	boolean adminLogin(String id) {
		String adminID = "admin";

		if (adminID.equals(id)) {
			// 관리자 모드 접속 진행
			JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "관리자 모드로 접속합니다. ");

			// 서버 구동
			ServerPC serverPC = ServerPC.getInstance();
			serverPC.start();

			FrameManger.getHomeFrame().dispose();
			AdminFrame adminFrame = new AdminFrame();
			FrameManger.setAdminFrame(adminFrame);
			return true;
		}
		return false;
	}
}
