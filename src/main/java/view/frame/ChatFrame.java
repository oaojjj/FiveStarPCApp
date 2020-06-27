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

	boolean flag = true;

	public ChatFrame(int pc, String name, BufferedReader in, BufferedWriter out) {
		this.pc = pc;
		this.name = name;
		this.in = in;
		this.out = out;

		setSize(300, 400);
		if (name.equals("admin"))
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

		// 쓰레드 너무 어려워서 삽질
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					flag = false;
					out.write("\n");
					out.flush();
					if (name.equals("admin")) {
						synchronized (sr) {
							sr.notify();
						}
					}
					super.windowClosing(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setVisible(true);
	}

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

	// 시간이 촉박해서 코드가 아주 마음에 안듬
	@Override
	public void run() {
		try {
			while (flag) {
				receiveMsg = in.readLine();
				if (name.equals("admin"))
					setText("user", receiveMsg);
				else
					setText("admin", receiveMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setText(String name, String msg) {
		if (name.equals("admin")) {
			taDisplay.append("관리자 : " + msg + "\n");
		} else {
			taDisplay.append(pc + "번 손님 : " + msg + "\n");
		}
	}

	public void setReceiver(ServerReceiver sr) {
		this.sr = sr;
	}

}
