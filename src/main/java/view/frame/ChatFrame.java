package main.java.view.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.glass.events.WindowEvent;

import main.java.common.setting.Setting;
import main.java.socket.ClientPC;
import main.java.socket.ServerPC;
import main.java.socket.ServerPC.ServerReceiver;

// 시간이 촉박해서 하나의 클래스에서 전부 처리........
public class ChatFrame extends JFrame implements ActionListener, Runnable {
	ServerReceiver sr;

	private JPanel displayPanel, inputPanel;
	private JTextArea taDisplay;
	private JTextField tfInput;

	private int pc;
	private String name;
	private String sendMsg, receiveMsg;
	private BufferedReader in;
	private BufferedWriter out;

	private boolean flag;
	private String exit = "#WA%DFGAK!)#(!@#JSDAFJSSAFds";

	public ChatFrame(int pc, String name, BufferedReader in, BufferedWriter out) {
		this.pc = pc;
		this.name = name;
		this.in = in;
		this.out = out;

		setSize(300, 400);
		if (name.equals("server"))
			setTitle(pc + "번 PC로 부터 문의");
		else
			setTitle("관리자 문의 채팅");

		setLayout(new BorderLayout());
		setLocation(800, 300);

		// 채팅화면
		displayPanel = new JPanel();
		displayPanel.setLayout(new FlowLayout());
		taDisplay = new JTextArea(18, 18);
		taDisplay.setFont(Setting.getBasicFont());
		taDisplay.setEditable(false);
		taDisplay.setLineWrap(true);

		// 스크롤
		JScrollPane scrollPane = new JScrollPane(taDisplay);
		displayPanel.add(scrollPane);

		// 입력창
		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		tfInput = new JTextField(18);
		tfInput.setFont(Setting.getBasicFont());
		tfInput.addActionListener(this);
		inputPanel.add(tfInput);

		add(displayPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		setVisible(true);

		// 쓰레드 너무 어려워서 삽질
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				setVisible(false);
				try {
					flag = false;
					out.write(exit + "\n");
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (name.equals("server")) {
					synchronized (sr) {
						sr.notify();
					}
				}
			}
		});
	}

	// 채팅 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sendMsg = tfInput.getText();
			setText(name, sendMsg);
			
			sendMsg += "\n";
			out.write(sendMsg);
			out.flush();

			tfInput.setText("");
		} catch (IOException e1) {
			System.out.println("채팅 입력 에러");
		}

	}

	@Override
	public void run() {
		flag = true;
		try {
			while (flag) {
				receiveMsg = in.readLine();
				if (receiveMsg.equals(exit))
					break;
				if (name.equals("server"))
					setText("client", receiveMsg);
				else
					setText("server", receiveMsg);
			}
		} catch (IOException e) {
			System.out.println("채팅 입출력 에러");
		}
	}

	private void setText(String name, String msg) {
		if (name.equals("server")) {
			taDisplay.append("관리자 : " + msg + "\n");
		} else {
			taDisplay.append(pc + "번 손님 : " + msg + "\n");
		}
	}

	public void setReceiver(ServerReceiver sr) {
		this.sr = sr;
	}

}
