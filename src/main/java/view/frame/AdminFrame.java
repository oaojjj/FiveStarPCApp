package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import main.java.common.setting.Setting;
import main.java.view.panel.AdminPanel;

public class AdminFrame extends JFrame {
	public AdminFrame() {
		setSize(Setting.getFHDSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		AdminPanel adminPanel = new AdminPanel();
		/*
		 * JButton test = new JButton("test"); test.setPreferredSize(new
		 * Dimension(50, 50));
		 * 
		 * add(test, BorderLayout.SOUTH);
		 */

		add(adminPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AdminFrame();
	}
}
