package main.java.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import main.java.controller.manager.FrameManger;
import main.java.view.frame.AdminFrame;
import main.java.view.frame.ChatFrame;

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

	public ServerReceiver getReceiver() {
		return receiver;
	}

	// 서버 닫기
	public void off() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ServerReceiver extends Thread {
		private AdminFrame adminFrame = FrameManger.getAdminFrame();
		private ServerReceiver sr;

		private Socket socket;
		private BufferedReader in = null;
		private BufferedWriter out = null;

		private boolean flag;
		private boolean chatFlag = false;

		private ChatFrame chatFrame = null;
		private Thread chatThread = null;

		public ServerReceiver(Socket s) {
			socket = s;
			flag = true;
			sr = this;

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
				command = st.nextToken();
				pc = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				time = Integer.parseInt(st.nextToken());

				while (flag) {

					if (command == null)
						command = in.readLine();

					switch (command) {
					case "login":
						login(pc, name, time);
						break;
					case "logout":
						logout(pc);
						break;
					case "chat":
						chat(pc);
						break;
					case "order":
						order(pc);
						break;
					default:
						break;
					}
					command = null;
				}
			} catch (IOException e1) {
				System.out.println("서버 소켓 유저 데이터 입력 에러");
			}
		}

		private void order(int pc) {
			JOptionPane.showMessageDialog(FrameManger.getAdminFrame(), pc + "번 PC 주문이 들어왔습니다.");
		}

		private void login(int pc, String name, int time) {
			adminFrame.getSeatPanel().pcPanel[pc].setOn(name, time);
		}

		private void logout(int pc) {

			adminFrame.getSeatPanel().pcPanel[pc].setOff();
			receiver.off();
			JOptionPane.showMessageDialog(FrameManger.getAdminFrame(), (pc + 1) + "번 PC 사용 종료");
		}

		synchronized private void chat(int pc) {
			try {
				if (chatFrame == null) {
					chatFrame = new ChatFrame(pc + 1, "server", in, out);
					chatFrame.setReceiver(sr);
				} else {
					if (chatFlag) {
						chatFlag = false;
						chatFrame.setVisible(true);
					}
				}
				chatThread = new Thread(chatFrame);
				chatThread.start();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void chatOff() {
			chatFlag = true;
		}

		public Thread getChatThread() {
			return chatThread;
		}

		public void off() {
			flag = false;
		}
	}

}
