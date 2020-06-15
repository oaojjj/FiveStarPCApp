package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.common.utill.DBManager;
import main.java.view.panel.SignUpPanel;

public class SignUpListener implements ActionListener {
	private SignUpPanel panel;
	private MemberDTO memberDTO;
	DBController dbCon = DBManager.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();

		panel = FrameManger.getHomeFrame().getSignUpPanel();

		memberDTO = panel.getSignUpData();
		String saveID = panel.getSaveID();
		String id;

		if (bt.getText().equals("중복검사")) {
			id = memberDTO.getId();
			if (panel.checkValue(id))
				panel.setCheckedID(panel.checkID(id));

		} else if (bt.getText().equals("회원가입")) {
			String name = memberDTO.getName();
			String password = memberDTO.getPassword();
			String email = memberDTO.getEmail();
			if (panel.getCheckedID()) {

				// 공백란이 있는지 체크
				if (panel.checkValue(name, saveID, password, email)) {
					// 중복체크를 하고 다시 아이디를 바꾼 경우 체크
					if (saveID.equals(panel.getTfID().getText())) {
						try {
							dbCon.insert(memberDTO);
							JOptionPane.showMessageDialog(panel, "회원가입이 완료되었습니다.");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(panel, "문제 발생! 카운터에 문의하세요.");
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
}
