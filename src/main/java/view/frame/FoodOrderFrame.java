package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.common.dao.FoodController;
import main.java.common.dto.FoodDTO;
import main.java.common.setting.Setting;
import main.java.socket.ClientPC;

public class FoodOrderFrame extends JFrame implements ActionListener {
	private ArrayList<FoodDTO> mList;
	private JPanel mainPanel, orderPanel, buttonPanel, foodPanel;
	private JButton btOrder, btFood[];
	private JLabel moneyLabel;
	private int c, money;

	private HashMap<String, Integer> hashMap;

	public FoodOrderFrame(ArrayList<FoodDTO> list) {
		hashMap = new HashMap<>();
		mList = list;
		setTitle("음식 주문");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		// 대충 화면 중앙
		setLocation(700, 200);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, list.size(), 5, 5));
		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 3), "음식 종류"));

		btFood = new JButton[list.size()];
		for (int i = 0; i < list.size(); i++) {
			btFood[i] = new JButton("<html>" + list.get(i).getName() + "<br>" + list.get(i).getMoney() + "원<html>");
			btFood[i].setFont(Setting.getBasicFont());
			btFood[i].addActionListener(this);
			mainPanel.add(btFood[i]);
		}

		foodPanel = new JPanel(new GridLayout(list.size() + 10, 1));
		foodPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 3), "주문 사항"));
		JScrollPane sp = new JScrollPane(foodPanel);

		// 무슨 음식 골랐는지
		orderPanel = new JPanel();
		orderPanel.setLayout(new GridLayout(1, 3));

		orderPanel.add(createLabel("음식"));
		orderPanel.add(createLabel("가격"));
		orderPanel.add(createLabel("수량"));
		orderPanel.setBackground(Color.LIGHT_GRAY);
		foodPanel.add(orderPanel);

		buttonPanel = new JPanel();

		moneyLabel = new JLabel("0");
		moneyLabel.setFont(Setting.getBasicFont());
		buttonPanel.add(moneyLabel);
		buttonPanel.add(new JLabel("원"));

		btOrder = new JButton("결제");
		btOrder.setFont(Setting.getBasicFont());
		btOrder.addActionListener(this);

		buttonPanel.add(btOrder);

		add(mainPanel, BorderLayout.NORTH);
		add(foodPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		FoodController.edit().readFile();
		new FoodOrderFrame(FoodController.edit().getFoodList());
	}

	private JLabel createLabel(String s) {
		JLabel j = new JLabel(s, JLabel.CENTER);
		j.setBorder(new LineBorder(Color.GRAY, 1));
		return j;
	}

	private void addPanel(FoodDTO f) {
		JPanel fp = new JPanel();
		fp.setLayout(new GridLayout(1, 3));
		fp.add(createLabel(f.getName()));
		fp.add(createLabel(f.getMoney()));
		fp.add(createLabel("1"));
		foodPanel.add(fp);
		c++;
		validate();
		repaint();
	}

	private void updatePanel(FoodDTO f, int i) {
		int cnt = Integer.parseInt(((JLabel) ((JPanel) foodPanel.getComponent(i)).getComponent(2)).getText());

		((JLabel) ((JPanel) foodPanel.getComponent(i)).getComponent(0)).setText(f.getName());
		((JLabel) ((JPanel) foodPanel.getComponent(i)).getComponent(1)).setText(f.getMoney());
		((JLabel) ((JPanel) foodPanel.getComponent(i)).getComponent(2)).setText(String.valueOf(cnt + 1));
		validate();
		repaint();
	}

	// 버튼 눌렀을 때
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();

		if (bt.getText().equals("결제")) {
			ClientPC.getPC().order(hashMap, money);
			JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");
			dispose();
			return;
		}

		// 밑으로는 전부다 음식 눌렀을 때 주문사항에 추가
		StringTokenizer st = new StringTokenizer(bt.getText(), "<html>");
		String name = st.nextToken();

		FoodDTO food = FoodController.edit().getFood(name);

		money += Integer.parseInt(food.getMoney());
		moneyLabel.setText(String.valueOf(money));

		if (hashMap.get(food.getName()) != null) {
			hashMap.put(food.getName(), hashMap.get(food.getName()) + 1);
		} else {
			hashMap.put(food.getName(), 1);
		}

		/*
		 * for (String key : hashMap.keySet()) { Integer value =
		 * hashMap.get(key); System.out.println("[key]:" + key + ", [value]:" +
		 * value); }
		 */

		for (int i = 0; i <= c; i++) {
			if (food.getName().equals(((JLabel) ((JPanel) foodPanel.getComponent(i)).getComponent(0)).getText())) {
				updatePanel(food, i);

				return;
			}
		}
		addPanel(food);
	}
}
