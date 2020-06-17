package main.java.view.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.common.dao.DBController;
import main.java.common.dao.DBManager;
import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.common.utill.GridBagConstaintsUtill;
import main.java.controller.SignUpListener;
import main.java.view.frame.HomeFrame;

public class SignUpPanel extends JPanel {
	private String saveID;
	private boolean checkedID = false;

	private JPanel signUpPanel;
	private JTextField tfName, tfID, tfEmail;
	private JLabel lbName, lbID, lbPassword, lbEmail;
	private JPasswordField pfPassword;
	private JButton btCommit, btIdCheck, btCancle;

	public SignUpPanel() {
		setSize(Setting.getScreenSize());
		setLayout(null);

		signUpPanel = new JPanel();
		signUpPanel.setBorder((new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 6))));
		signUpPanel.setBounds(1920 / 3 + 100, 1080 / 5, 400, 500);
		signUpPanel.setLayout(new GridBagLayout());
		GridBagConstaintsUtill gUtill = new GridBagConstaintsUtill(new GridBagConstraints(), signUpPanel,
				new Insets(25, 10, 25, 10));

		// GridBagConstraints 설정
		gUtill.gbc.fill = GridBagConstraints.BOTH;
		gUtill.gbc.weightx = 1;
		gUtill.gbc.weighty = 1;

		lbName = createLabel("이름");
		tfName = new JTextField(20);
		gUtill.gbAdd(lbName, 0, 0, 1, 1, 0.3);
		gUtill.gbAdd(tfName, 1, 0, 2, 1, 0.7);

		lbID = createLabel("아이디");
		tfID = new JTextField(20);
		btIdCheck = new JButton("중복검사");
		btIdCheck.setSize(50, 50);
		gUtill.gbAdd(lbID, 0, 1, 1, 1, 0.3);
		gUtill.gbAdd(tfID, 1, 1, 1, 1, 0.6);
		gUtill.gbAdd(btIdCheck, 2, 1, 1, 1, 0.1);

		lbPassword = createLabel("비밀번호");
		pfPassword = new JPasswordField(20);
		gUtill.gbAdd(lbPassword, 0, 2, 1, 1, 0.3);
		gUtill.gbAdd(pfPassword, 1, 2, 2, 1, 0.7);

		lbEmail = createLabel("email");
		tfEmail = new JTextField(20);
		gUtill.gbAdd(lbEmail, 0, 3, 1, 1, 0.3);
		gUtill.gbAdd(tfEmail, 1, 3, 2, 1, 0.7);

		btCommit = new JButton("회원가입");
		btCancle = new JButton("취소");
		btCommit.setBackground(Color.WHITE);
		btCancle.setBackground(Color.LIGHT_GRAY);
		gUtill.gbAdd(btCancle, 0, 4, 1, 1, 0.3);
		gUtill.gbAdd(btCommit, 1, 4, 2, 1, 0.7);

		// 리스너 달기
		btIdCheck.addActionListener(new SignUpListener());
		btCommit.addActionListener(new SignUpListener());
		btCancle.addActionListener(new SignUpListener());

		add(signUpPanel);
	}

	JLabel createLabel(String name) {
		JLabel j = new JLabel(name, JLabel.CENTER);
		j.setFont(Setting.getBasicFont());
		return j;
	}

	// 중복 아이디 검사 메소드
	// TODO 컨트롤러에 집어넣기
	public boolean checkID(String id) {
		try {
			checkedID = DBController.checkID(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(signUpPanel, "관리자에게 문의해주세요.");
		}
		if (checkedID) {
			JOptionPane.showMessageDialog(signUpPanel, "중복되는 아이디입니다.");
			return false;
		} else {
			JOptionPane.showMessageDialog(signUpPanel, "사용 가능한 아이디입니다.");
			saveID = id;
			return true;
		}
	}

	

	public MemberDTO getSignUpData() {
		String name = tfName.getText();
		String id = tfID.getText();
		String password = new String(pfPassword.getPassword());
		String email = tfEmail.getText();
		return new MemberDTO(name, id, password, email, 0);
	}

	public JTextField getTfID() {
		return tfID;
	}

	public String getSaveID() {
		return saveID;
	}

	public void setSaveID(String saveID) {
		this.saveID = saveID;
	}

	public boolean getCheckedID() {
		return checkedID;
	}

	public void setCheckedID(boolean checkedID) {
		this.checkedID = checkedID;
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		resetData();
	}

	void resetData() {
		tfName.setText("");
		tfID.setText("");
		pfPassword.setText("");
		tfEmail.setText("");
	}

}
