package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import main.java.common.dao.DBController;
import main.java.common.dao.DBManager;
import main.java.common.dto.MemberDTO;
import main.java.view.frame.MemberInfoFrame;

public class AdminEventListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();
		if (bt.getText().equals("재고물품")) {

		} else {
			try {
				DBController dbcon = DBManager.getInstance();

				ArrayList<MemberDTO> dto = dbcon.selectAll();

				MemberInfoFrame infoFrame = new MemberInfoFrame(dto);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
