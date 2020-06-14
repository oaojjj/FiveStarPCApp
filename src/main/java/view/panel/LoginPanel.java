package main.java.view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.common.dto.MemberDTO;
import main.java.common.setting.Setting;
import main.java.controller.HomeListener;
import main.java.view.frame.HomeFrame;

public class LoginPanel extends JPanel {
	private JPanel memberPanel, nonMemberPanel, eventPanel;
	private JTextField tfID, tfCard;
	private JPasswordField pfPassword;
	private JButton btLogin, btForgot, btRegister;

	HomeListener homeListener;

	int loginFormPosX, loginFormPosY;
	int pcNumber = 5;

	public LoginPanel() {
		setSize(Setting.getScreenSize());
		setLayout(null);
		initLoingFormPos();
		introLabel();
		memberForm();
		nonMemberForm();
		eventForm();
		setBackgroundImage("src/main/resource/logo/logo3.png");
		setBackground(new Color(255, 202, 40)); // 머티리얼 색상 #FFCA28
	}

	private void initLoingFormPos() {
		loginFormPosX = HomeFrame.SCREEN_WIDTH / 2 + HomeFrame.SCREEN_WIDTH / 6;
		loginFormPosY = HomeFrame.SCREEN_HEIGHT / 2 + HomeFrame.SCREEN_HEIGHT / 6;
	}

	void introLabel() {
		JLabel pcNumLabel = new JLabel(pcNumber + "번 PC");
		pcNumLabel.setFont(new Font("바탕", Font.BOLD, 70));
		pcNumLabel.setBounds(Setting.SCREEN_WIDTH / 2 + Setting.SCREEN_WIDTH / 4, Setting.SCREEN_HEIGHT / 2 - 400, 400,
				200);
		add(pcNumLabel);
	}

	private void setBackgroundImage(String path) {
		try {
			// 배경 이미지 만들기
			Image image = ImageIO.read(new File(path));
			BackgroundPanel background = new BackgroundPanel(image,
					HomeFrame.SCREEN_WIDTH / 2 + HomeFrame.SCREEN_WIDTH / 4 - 30, HomeFrame.SCREEN_HEIGHT);
			background.setBounds(0, 0, HomeFrame.SCREEN_WIDTH / 2 + HomeFrame.SCREEN_WIDTH / 4 - 30,
					HomeFrame.SCREEN_HEIGHT);
			add(background);
		} catch (IOException e) {
			System.out.println("배경 이미지 불러오기 실패");
		}
	}

	// 회원 로그인 폼
	private void memberForm() {
		memberPanel = new JPanel();
		memberPanel.setBorder((new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 6), "회원 로그인")));
		memberPanel.setLayout(new GridLayout(4, 1, 5, 5));

		memberPanel.add(new JLabel("아이디", JLabel.LEFT));
		tfID = new JTextField(20);
		memberPanel.add(tfID);

		memberPanel.add(new JLabel("비밀번호", JLabel.LEFT));
		pfPassword = new JPasswordField(20);
		memberPanel.add(pfPassword);

		// memberPanel.setOpaque(false);
		memberPanel.setBounds(loginFormPosX, loginFormPosY, 250, 200);
		add(memberPanel);
	}

	// 비회원 로그인 폼
	private void nonMemberForm() {
		nonMemberPanel = new JPanel();
		nonMemberPanel.setBorder((new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 6), "비회원 로그인")));
		nonMemberPanel.setLayout(new GridLayout(4, 1, 0, 10));

		nonMemberPanel.add(new JLabel("카드번호", JLabel.LEFT));
		tfCard = new JTextField(20);
		nonMemberPanel.add(tfCard);

		nonMemberPanel.add(new JLabel("카드번호는 1~20", JLabel.CENTER));
		nonMemberPanel.add(new JLabel("회원가입시 많은 혜택 있습니다. ", JLabel.CENTER));

		// nonMemberPanel.setOpaque(false);
		nonMemberPanel.setBounds(loginFormPosX + 270, loginFormPosY, 250, 200);
		add(nonMemberPanel);
	}

	// 이벤트 폼(로그인, 회원가입 등)
	private void eventForm() {
		eventPanel = new JPanel();
		eventPanel.setLayout(new FlowLayout(0, 20, 0));

		btLogin = new JButton("로그인");
		btLogin.setBackground(Color.WHITE);
		btLogin.setPreferredSize(new Dimension(100, 40));

		btForgot = new JButton("회원찾기");
		btForgot.setBackground(Color.WHITE);
		btForgot.setPreferredSize(new Dimension(100, 40));

		btRegister = new JButton("회원가입");
		btRegister.setBackground(Color.WHITE);
		btRegister.setPreferredSize(new Dimension(100, 40));

		// 리스너 생성
		homeListener = new HomeListener();

		btLogin.addActionListener(homeListener);
		btForgot.addActionListener(homeListener);
		btRegister.addActionListener(homeListener);

		eventPanel.add(btLogin);
		eventPanel.add(btForgot);
		eventPanel.add(btRegister);

		eventPanel.setOpaque(false);
		eventPanel.setBounds(loginFormPosX + 160, loginFormPosY + 230, 500, 40);
		add(eventPanel);
	}

	public String[] getLoginInfo() {
		return new String[] { tfID.getText(), new String(pfPassword.getPassword()) };
	}

}
