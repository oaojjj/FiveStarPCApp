package main.java.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import main.java.controller.manager.FrameManger;
import main.java.view.frame.AdminFrame;

public class ServerPC extends Thread {
	public static final String SERVER_IP = "pcbangtuto1.iptime.org";
	public static final int PORT_NUMBER = 9999;
	private static ServerPC server;

	private ServerReceiver receiver = null;
	private ServerSocket serverSocket = null;
	private Socket socket = null;

	// 싱글톤 - 서버는 하나만 구동
	private ServerPC() {
	}

	public static ServerPC getInstance() {
		if (server == null) {
			server = new ServerPC();
		}
		return server;
	}

	// 서버 시작
	@Override
	public void run() {
		if (serverSocket == null)
			try {
				serverSocket = new ServerSocket(PORT_NUMBER);
				System.out.println("서버 실행");
				// 사용자를 계속 받음
				while (true) {
					// 접속자 대기중
					socket = serverSocket.accept();
					System.out.println("SERVER : " + socket.getInetAddress() + " 접속");

					// 연결되면 새로운 쓰레드 통신
					receiver = new ServerReceiver(socket);
					receiver.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
				if (!serverSocket.isClosed())
					off();
			}
	}

	// 서버 닫기
	public void off() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ServerReceiver extends Thread {
		private AdminFrame adminFrame = FrameManger.getAdminFrame();

		private Socket socket;
		private BufferedReader in = null;
		private BufferedWriter out = null;

		public ServerReceiver(Socket s) {
			socket = s;

			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 클라이언트와 지속적으로 통신
		@Override
		public void run() {
			int pc, time;
			String name, command;

			try {
				String userData = in.readLine();
				StringTokenizer st = new StringTokenizer(userData, "/");

				// 유저 데이터(피시번호, 이름, 남은시간)
				pc = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				time = Integer.parseInt(st.nextToken());
				command = st.nextToken();

				while (in != null) {
					switch (command) {
					case "login":
						login(pc, name, time);
						break;
					case "logout":
						break;
					case "message":
						break;
					case "order":
						break;
					default:
						break;
					}
					command = in.readLine();
				}
			} catch (IOException e1) {
				System.out.println("서버 소켓 유저 데이터 입력 에러");
			}
		}

		private void login(int pc, String name, int time) {
			adminFrame.getSeatPanel().pcPanel[pc].setOn(name, time);
		}
	}
}
