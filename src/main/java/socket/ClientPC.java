package main.java.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import jdk.internal.instrumentation.ClassInstrumentation;
import main.java.view.frame.ChatFrame;

public class ClientPC extends Thread {
	private static ClientPC clientPC;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;

	private int pc, time;
	private String name;

	private boolean flag = true;

	private ChatFrame chatFrame = null;
	private Thread chatThread = null;

	// 피시번호, 사용자 이름, 사용자 남은 시간 불러옴
	public ClientPC(String pcNumber, String userName, int saveTime) {
		pc = Integer.parseInt(pcNumber) - 1;
		name = userName;
		time = saveTime;
		clientPC = this;

		try {
			socket = new Socket(ServerPC.SERVER_IP, ServerPC.PORT_NUMBER);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("클라이언트 연결 성공");
	}

	@Override
	public void run() {
		try {
			String initData = "login/" + pc + "/" + name + "/" + time + "\n";
			out.write(initData);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void clientClose() {
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ClientPC getPC() {
		return clientPC;
	}

	public Thread getChatThread() {
		return chatThread;
	}

	// 쓰레드로 통신
	synchronized public void chatOn() {
		flag = false;
		try {
			out.write("chat\n");
			out.flush();
			if (chatFrame == null) {
				chatFrame = new ChatFrame(pc + 1, "client", in, out);
			} else {
				chatFrame.setVisible(true);
			}
			chatThread = new Thread(chatFrame);
			chatThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isOff() {
		return flag;
	}

	public void chatOff() {
		flag = true;
	}

	public void logout() {
		try {
			out.write("logout\n");
			out.flush();
			clientClose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}