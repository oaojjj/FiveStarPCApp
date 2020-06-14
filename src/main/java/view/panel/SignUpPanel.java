package main.java.view.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.common.dao.DBController;
import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.common.utill.DBManager;
import main.java.view.frame.HomeFrame;

public class SignUpPanel extends JPanel {
	private JPanel signUpPanel;
	private JTextField tfName, tfID, tfEmail;
	private JLabel lbName, lbID, lbPassword, lbEmail;
	private JPasswordField pfPassword;
	private JButton btCommit, btIdCheck, btCancle;

	private GridBagConstraints gbc;

	private String saveID;
	private MemberDTO memberDTO;
	private DBController dbcon = DBManager.getInstance();

	private boolean checkedID = false;

	public SignUpPanel() {
		// setSize(Setting.getScreenSize());
		setSize(1920, 1080);
		setLayout(null);

		signUpPanel = new JPanel();
		signUpPanel.setBorder((new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 6))));
		signUpPanel.setBounds(1920 / 3 + 100, 1080 / 5, 400, 500);
		signUpPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		// GridBagConstraints 설정
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		lbName = createLabel("이름");
		tfName = new JTextField(20);
		gbAdd(lbName, 0, 0, 1, 1, 0.3);
		gbAdd(tfName, 1, 0, 2, 1, 0.7);

		lbID = createLabel("아이디");
		tfID = new JTextField(20);
		btIdCheck = new JButton("중복검사");
		btIdCheck.setSize(50, 50);
		gbAdd(lbID, 0, 1, 1, 1, 0.3);
		gbAdd(tfID, 1, 1, 1, 1, 0.6);
		gbAdd(btIdCheck, 2, 1, 1, 1, 0.1);

		lbPassword = createLabel("비밀번호");
		pfPassword = new JPasswordField(20);
		gbAdd(lbPassword, 0, 2, 1, 1, 0.3);
		gbAdd(pfPassword, 1, 2, 2, 1, 0.7);

		lbEmail = createLabel("email");
		tfEmail = new JTextField(20);
		gbAdd(lbEmail, 0, 3, 1, 1, 0.3);
		gbAdd(tfEmail, 1, 3, 2, 1, 0.7);

		btCommit = new JButton("회원가입");
		btCancle = new JButton("취소");
		btCommit.setBackground(Color.WHITE);
		btCancle.setBackground(Color.LIGHT_GRAY);
		gbAdd(btCancle, 0, 4, 1, 1, 0.3);
		gbAdd(btCommit, 1, 4, 2, 1, 0.7);

		// 리스너 달기
		btIdCheck.addActionListener(new SignUpListener());
		btCommit.addActionListener(new SignUpListener());
		btCancle.addActionListener(new SignUpListener());

		add(signUpPanel);
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h, double wx) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = wx;
		gbc.insets = new Insets(25, 10, 25, 10);
		signUpPanel.add(c, gbc);
	}

	JLabel createLabel(String name) {
		JLabel j = new JLabel(name, JLabel.CENTER);
		j.setFont(Setting.getBasicFont());
		return j;
	}

	// TODO 컨트롤러로 따로 뺄까 생각중
	class SignUpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();

			if (bt.getText().equals("중복검사")) {
				String id = tfID.getText();
				if (checkValue(id))
					checkedID = checkID(id);

			} else if (bt.getText().equals("회원가입")) {
				String name = tfName.getText();
				String password = new String(pfPassword.getPassword());
				String email = tfEmail.getText();
				if (checkedID) {

					// 공백란이 있는지 체크
					if (checkValue(name, saveID, password, email)) {

						// 중복체크를 하고 다시 아이디를 바꾼 경우 체크
						if (saveID.equals(tfID.getText())) {
							memberDTO = new MemberDTO(name, saveID, password, email);
							dbcon.insert(memberDTO);
							JOptionPane.showMessageDialog(signUpPanel, "회원가입이 완료되었습니다.");
							signUpPanel.setVisible(false);
							HomeFrame.getLoginPanel().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(signUpPanel, "아이디가 변경되었으니 중복검사를 다시 해주세요.");
							checkedID = false;
						}
					}
				} else {
					JOptionPane.showMessageDialog(signUpPanel, "아이디 중복검사를 해주세요.");
				}
			} else if (bt.getText().equals("취소")) {
				HomeFrame.getSignUpPanel().setVisible(false);
				HomeFrame.getLoginPanel().setVisible(true);
			}
		}
	}

	// 중복 아이디 검사 메소드
	boolean checkID(String id) {
		checkedID = dbcon.checkID(id);
		if (checkedID) {
			JOptionPane.showMessageDialog(signUpPanel, "중복되는 아이디입니다.");
			return false;
		} else {
			JOptionPane.showMessageDialog(signUpPanel, "사용 가능한 아이디입니다.");
			saveID = id;
			return true;
		}
	}

	// 유저 데이터 공백 체크 메소드
	boolean checkValue(String... s) {
		for (String string : s) {
			if (string.equals("")) {
				JOptionPane.showMessageDialog(signUpPanel, "공백란을  채워주세요.");
				return false;
			}
		}
		return true;
	}

}
