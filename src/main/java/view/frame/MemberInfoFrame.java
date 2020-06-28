package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import main.java.common.dto.MemberDTO;
import main.java.common.utill.MyDate;

public class MemberInfoFrame extends JFrame {
	private JPanel memberInfoPanel[];

	public MemberInfoFrame(ArrayList<MemberDTO> list) {
		setTitle("회원 정보 조회");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// 대충 화면 중앙
		setLocation(700, 200);

		JPanel panel = new JPanel(new GridLayout(list.size() + 1, 1));
		JScrollPane sp = new JScrollPane(panel);

		memberInfoPanel = new JPanel[list.size() + 1];

		memberInfoPanel[0] = new JPanel();
		memberInfoPanel[0].setLayout(new GridLayout(1, 4));

		memberInfoPanel[0].add(createLabel("이름"));
		memberInfoPanel[0].add(createLabel("아이디"));
		memberInfoPanel[0].add(createLabel("이메일"));
		memberInfoPanel[0].add(createLabel("남은시간"));
		memberInfoPanel[0].setBackground(Color.LIGHT_GRAY);
		panel.add(memberInfoPanel[0]);

		for (int i = 0; i < list.size(); i++) {
			MemberDTO dto = list.get(i);

			MyDate m = new MyDate(dto.getSaveTime());

			memberInfoPanel[i + 1] = new JPanel();
			memberInfoPanel[i + 1].setLayout(new GridLayout(1, 4));
			memberInfoPanel[i + 1].add(createLabel(dto.getName()));
			memberInfoPanel[i + 1].add(createLabel(dto.getId()));
			memberInfoPanel[i + 1].add(createLabel(dto.getEmail()));
			memberInfoPanel[i + 1].add(createLabel(m.toString()));
			panel.add(memberInfoPanel[i + 1]);
		}

		add(sp, BorderLayout.NORTH);
		setVisible(true);
	}

	private JLabel createLabel(String s) {
		JLabel j = new JLabel(s, JLabel.CENTER);
		j.setBorder(new LineBorder(Color.GRAY, 1));
		return j;
	}
}
