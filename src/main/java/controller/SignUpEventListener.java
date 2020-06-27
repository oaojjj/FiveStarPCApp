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
import main.java.view.frame.HomeFrame;
import main.java.view.panel.SignUpPanel;

public class SignUpEventListener implements ActionListener {
	private SignUpPanel panel;
	private MemberDTO memberDTO;

	private DBController dbCon = DBManager.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		String userID, saveID;

		JButton bt = (JButton) e.getSource();

		panel = FrameManger.getHomeFrame().getSignUpPanel();
		memberDTO = panel.getSignUpData();

		saveID = panel.getSaveID();

		if (bt.getText().equals("중복검사")) {
			userID = memberDTO.getId();
			// 아이디 공백 검사
			if (checkValue(userID))
				checkID(userID);
		} else if (bt.getText().equals("회원가입")) {
			String name = memberDTO.getName();
			String password = memberDTO.getPassword();
			String email = memberDTO.getEmail();

			// 중복아이디가 아니라면
			if (panel.isCheckedID()) {

				// 공백란이 있는지 체크
				if (checkValue(name, saveID, password, email)) {

					// 중복체크를 하고 다시 아이디를 바꾼 경우 체크
					if (saveID.equals(panel.getTfID().getText())) {
						try {
							// 최종 회원가입
							dbCon.insert(memberDTO);
							JOptionPane.showMessageDialog(panel, "회원가입이 완료되었습니다.");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(panel, "관리자에게 문의해주세요.");
						} finally {
							FrameManger.getHomeFrame().getSignUpPanel().setVisible(false);
							FrameManger.getHomeFrame().getLoginPanel().setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(panel, "아이디가 변경되었으니 중복검사를 다시 해주세요.");
						panel.setCheckedID(false);
					}
				}
			} else {
				JOptionPane.showMessageDialog(panel, "아이디 중복검사를 해주세요.");
			}
		} else if (bt.getText().equals("취소")) {
			FrameManger.getHomeFrame().getSignUpPanel().setVisible(false);
			FrameManger.getHomeFrame().getLoginPanel().setVisible(true);
		}
	}

	// 유저 데이터 공백 체크 메소드
	public boolean checkValue(String... s) {
		for (String string : s) {
			if (string.equals("")) {
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "공백란을  채워주세요.");
				return false;
			}
		}
		return true;
	}

	// 중복 아이디 검사 메소드
	public void checkID(String id) {
		try {
			boolean flag = DBController.checkID(id);

			if (flag) {
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "사용 가능한 아이디입니다.");
				panel.setSaveID(id);
				panel.setCheckedID(true);
			} else {
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "중복되는 아이디입니다.");
				panel.setCheckedID(false);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "관리자에게 문의해주세요.");
		}
	}

}
