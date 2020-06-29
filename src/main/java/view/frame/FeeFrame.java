package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.common.utill.MyDate;
import main.java.controller.manager.FrameManger;

public class FeeFrame extends JFrame implements ActionListener {
	private JLabel moneyLable;
	private String id;
	private int time;

	public FeeFrame(String id) {
		this.id = id;
		setTitle("선불 요금 결제");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(700, 250);

		setLayout(new BorderLayout());

		JPanel moneyPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		moneyPanel.setLayout(new GridLayout(2, 2));

		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton bt1000 = new JButton("1000원 : 1시간");
		JButton bt3000 = new JButton("3000원 : 3시간");
		JButton bt5000 = new JButton("5000원 : 6시간");
		JButton bt10000 = new JButton("10000원 : 13시간");

		moneyLable = new JLabel("0");
		moneyLable.setFont(Setting.getBasicFont());
		JButton btPay = new JButton("결제");

		bt1000.addActionListener(this);
		bt3000.addActionListener(this);
		bt5000.addActionListener(this);
		bt10000.addActionListener(this);
		btPay.addActionListener(this);

		moneyPanel.add(bt1000);
		moneyPanel.add(bt3000);
		moneyPanel.add(bt5000);
		moneyPanel.add(bt10000);

		bottomPanel.add(moneyLable);
		bottomPanel.add(new JLabel("원"));
		bottomPanel.add(btPay);

		add(moneyPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();
		int result;
		if (bt.getText().equals("1000원 : 1시간")) {
			result = Integer.parseInt(moneyLable.getText()) + 1000;
			moneyLable.setText(String.valueOf(result));
			time += 1;
		} else if (bt.getText().equals("3000원 : 3시간")) {
			result = Integer.parseInt(moneyLable.getText()) + 3000;
			moneyLable.setText(String.valueOf(result));
			time += 3;
		} else if (bt.getText().equals("5000원 : 6시간")) {
			result = Integer.parseInt(moneyLable.getText()) + 5000;
			moneyLable.setText(String.valueOf(result));
			time += 6;
		} else if (bt.getText().equals("10000원 : 13시간")) {
			result = Integer.parseInt(moneyLable.getText()) + 10000;
			moneyLable.setText(String.valueOf(result));
			time += 13;
		} else { // 결제 버튼
			result = Integer.parseInt(moneyLable.getText());
			try {
				time = time * 3600;
				if (!id.equals("card")) {
					DBController.saveTime(id, new MyDate(time).getSaveTime());
					DBController.payFee(id, result);
				} else {
					MemberDTO.getMemberDTO().setSaveTime(time);
				}
				setVisible(false);
				JOptionPane.showMessageDialog(FrameManger.getHomeFrame(), "충전이 완료 되었습니다!");
				this.dispose();
			} catch (SQLException e1) {
				System.out.println("요금 충전 에러");
			}
		}
	}

	public int getTime() {
		return time;
	}
}
