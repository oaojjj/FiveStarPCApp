package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import main.java.common.dao.FoodController;
import main.java.common.dto.FoodDTO;

public class FoodInfoFrame extends JFrame implements ActionListener {
	private JPanel eventPanel, panel;
	private JButton btChange, btAdd, btDelete;

	private ArrayList<FoodDTO> mList;

	public FoodInfoFrame(ArrayList<FoodDTO> list) {
		mList = list;
		setTitle("음식 재고  조회");
		setSize(600, 500);
		setLayout(new BorderLayout());

		// 대충 화면 중앙
		setLocation(700, 200);

		panel = new JPanel(new GridLayout(list.size() + 50, 1));
		JScrollPane sp = new JScrollPane(panel);

		JPanel foodInfoPanel = new JPanel();
		foodInfoPanel.setLayout(new GridLayout(1, 3));

		foodInfoPanel.add(createLabel("이름"));
		foodInfoPanel.add(createLabel("금액"));
		foodInfoPanel.add(createLabel("재고수량"));
		foodInfoPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(foodInfoPanel);

		initPanel(list);

		JPanel btPanel = new JPanel();

		btDelete = new JButton("재고 삭제");
		btAdd = new JButton("재고 추가");
		btChange = new JButton("재고 수정");

		btDelete.addActionListener(this);
		btAdd.addActionListener(this);
		btChange.addActionListener(this);

		btPanel.add(btAdd);
		btPanel.add(btChange);
		btPanel.add(btDelete);

		add(btPanel, BorderLayout.SOUTH);
		add(sp, BorderLayout.CENTER);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}
		});

	}

	private void initPanel(ArrayList<FoodDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			FoodDTO dto = list.get(i);

			JPanel foodInfoPanel = new JPanel();
			foodInfoPanel.setLayout(new GridLayout(1, 4));
			foodInfoPanel.add(createLabel(dto.getName()));
			foodInfoPanel.add(createLabel(dto.getMoney()));
			foodInfoPanel.add(createLabel(dto.getCount()));
			panel.add(foodInfoPanel);
		}
	}

	private void addPanel(FoodDTO food) {
		JPanel foodInfoPanel = new JPanel();
		foodInfoPanel.setLayout(new GridLayout(1, 4));
		foodInfoPanel.add(createLabel(food.getName()));
		foodInfoPanel.add(createLabel(food.getMoney()));
		foodInfoPanel.add(createLabel(food.getCount()));
		panel.add(foodInfoPanel);
		validate();
		repaint();
	}

	private void updatePanel(FoodDTO food, int i) {

		((JLabel) ((JPanel) panel.getComponent(i)).getComponent(0)).setText(food.getName());
		System.out.println(((JLabel) ((JPanel) panel.getComponent(i)).getComponent(0)).getText());
		((JLabel) ((JPanel) panel.getComponent(i)).getComponent(1)).setText(food.getMoney());
		((JLabel) ((JPanel) panel.getComponent(i)).getComponent(2)).setText(food.getCount());
		validate();
		repaint();
	}

	private void deletePanel(int i) {
		((JPanel) panel.getComponent(i)).removeAll();
		validate();
		repaint();
	}

	private JLabel createLabel(String s) {
		JLabel j = new JLabel(s, JLabel.CENTER);
		j.setBorder(new LineBorder(Color.GRAY, 1));
		return j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();
		System.out.println(bt.getText());
		String food;

		if (bt.getText().equals("재고 추가")) {
			food = JOptionPane.showInputDialog(this, "이름/가격/수량순으로 입력하세요.");
			if (food != null) {
				FoodDTO dto = new FoodDTO(food);
				FoodController.edit().addFood(dto);
				addPanel(dto);
			}
		} else if (bt.getText().equals("재고 수정")) {
			food = JOptionPane.showInputDialog(this, "이름/가격/수량순으로 입력하세요.");
			if (food != null) {
				FoodDTO dto = new FoodDTO(food);
				int i = FoodController.edit().changeFood(dto);

				if (i != -1)
					updatePanel(dto, i + 1);
				else
					JOptionPane.showMessageDialog(this, "품목을 똑바로 입력해주세요.");
			}
		} else {
			food = JOptionPane.showInputDialog(this, "이름을 입력하세요.");
			if (food != null) {
				int i = FoodController.edit().deleteFood(food);
				if (i != -1)
					deletePanel(i + 1);
				else
					JOptionPane.showMessageDialog(this, "품목을 똑바로 입력해주세요.");
			}
		}
	}

}
