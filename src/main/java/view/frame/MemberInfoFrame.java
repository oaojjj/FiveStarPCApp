package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
		setSize(600, 500);
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
		memberInfoPanel[0].add(createLabel("누적금액"));
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
			memberInfoPanel[i + 1].add(createLabel(String.valueOf(dto.getFee())));
			panel.add(memberInfoPanel[i + 1]);
		}

		add(sp, BorderLayout.NORTH);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}
		});

	}

	private JLabel createLabel(String s) {
		JLabel j = new JLabel(s, JLabel.CENTER);
		j.setBorder(new LineBorder(Color.GRAY, 1));
		return j;
	}
}
