package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.java.common.dao.DBController;
import main.java.common.utill.DBManager;
import main.java.view.frame.HomeFrame;
import main.java.view.frame.UserFrame;

public class HomeListener implements ActionListener {

	DBController dbcon = DBManager.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();

		if (bt.getText().equals("로그인")) {
			String[] info = HomeFrame.getLoginPanel().getLoginInfo();
			if (dbcon.checkLogin(info[0], info[1])) {
				HomeFrame.getHomeFrame().dispose();
				new UserFrame();
			} else {
				JOptionPane.showMessageDialog(HomeFrame.getHomeFrame(), "아이디 또는 비밀번호가 틀렸습니다.");
			}
		} else if (bt.getText().equals("회원가입")) {
			HomeFrame.getLoginPanel().setVisible(false);
			HomeFrame.getSignUpPanel().setVisible(true);
		} else if (bt.getText().equals("회원찾기")) {
			// TODO 회원찾기 나중에 구현
		}
	}
}
