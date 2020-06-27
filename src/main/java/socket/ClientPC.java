package main.java.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import jdk.internal.instrumentation.ClassInstrumentation;

public class ClientPC extends Thread {
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;

	private int pc, time;
	private String name;

	// 피시번호, 사용자 이름, 사용자 남은 시간 불러옴
	public ClientPC(String pcNumber, String userName, int saveTime) {
		pc = Integer.parseInt(pcNumber) - 1;
		name = userName;
		time = saveTime;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(ServerPC.SERVER_IP, ServerPC.PORT_NUMBER);
			System.out.println("클라이언트 연결 성공");

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			String initData = pc + "/" + name + "/" + time + "/login\n";
			out.write(initData);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void clientClose() {
		try {
			socket.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void off(int pc) {
		try {
			String msg = pc - 1 + "/" + "admin\n";
			out.write(msg);
			out.flush();
			clientClose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}