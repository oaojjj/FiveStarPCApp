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
import main.java.socket.MyClientSocket;
import main.java.socket.MyServerSocket;
import main.java.thread.ServerThread;
import main.java.view.frame.AdminFrame;
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
			
			// 피시 번호 골랐는지 체크
			if (info[0] == null && !info[1].equals("admin")) {
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "피시번호를 선택해주세요.");
				return;
			}
			
			try {
				if (DBController.checkLogin(info[1], info[2])) {
					
					// 사용자가 admin인지 체크
					if (관리자_로그인(info[1]))
						return;

					// 회원 정보를 불러와서 멤버 정보에 저장
					MemberDTO.setMemberDTO(dbcon.selectId(info[1]));
					
					// 사용자의 남은 시간이 없는 경우
					if (MemberDTO.getMemberDTO().getSaveTime() == 0) {
						JOptionPane.showMessageDialog(FrameManger.getHomeFrame(),
								"<html>남은 시간이 없습니다.<br>충전하고 다시 시도 해주세요.<html>");
					} else {
						// 사용자 시간이 남아있으면 로그인 진행
						MyClientSocket client = new MyClientSocket(info[0], MemberDTO.getMemberDTO().getName(),
								MemberDTO.getMemberDTO().getSaveTime());
						
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

	boolean 관리자_로그인(String id) {
		String adminID = "admin";
		if (adminID.equals(id)) {
			
			// 서버소켓 생성
			ServerThread serverThread = new ServerThread();
			serverThread.start();
			
			// 관리자 모드 접속 진행
			JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "관리자 모드로 접속합니다. ");
			FrameManger.getHomeFrame().dispose();
			AdminFrame adminFrame = new AdminFrame();
			FrameManger.setAdminFrame(adminFrame);
			return true;
		}
		return false;
	}
}
