package main.java.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientSocket {
	private static MyClientSocket clientSocket;
	BufferedReader in = null;
	BufferedWriter out = null;
	Socket socket = null;

	// 피시번호, 사용자 이름, 사용자 남은 시간 불러옴
	public MyClientSocket(String info, String name, int time) {
		clientSocket = this;
		try {
			// 피시 번호
			int pc = Integer.parseInt(info) - 1;

			// 클라이언트 소켓 생성
			socket = new Socket("pcbangtuto1.iptime.org", 9999);

			// 사용자 정보 아웃스트림
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 사용자 정보
			String message = pc + "/" + name + "/" + time + "\n";
			out.write(message);
			out.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void socketClose() {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MyClientSocket getClientSocket() {
		return clientSocket;
	}

}
