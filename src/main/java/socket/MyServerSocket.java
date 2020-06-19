package main.java.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import main.java.controller.manager.FrameManger;

public class MyServerSocket {
	BufferedReader in = null;
	BufferedWriter out = null;
	ServerSocket serverSocket = null;
	Socket socket = null;
	boolean flag = true;

	// 쓰레드 해야하나?
	public MyServerSocket() {
		try {
			// 서버(어드민) 소켓 생성
			serverSocket = new ServerSocket(9999);
			System.out.println("연결을 기다리고 있습니다.");

			// 무한 루프를 돌면서 사용자의 로그인 신호를 계속 체크
			while (flag) {
				// 클라이언트로부터 연결 요청 대기
				socket = serverSocket.accept();

				// 사용자 컴퓨터가 접속하면 adminFrame에 표현할 사용자 정보를 받아옴
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String inputMsg = in.readLine();

				// 넘어온 정보를 바탕으로 패널 업데이트
				updatePanel(inputMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
				socket.close();
				in.close();
			} catch (IOException e) {
				System.out.println("close 오류");
			}
		}
	}

	void updatePanel(String s) {
		// 넘어오는 정보 -> "pc번호/사용자이름/남은시간"
		StringTokenizer st = new StringTokenizer(s, "/");
		// pc번호
		int pc = Integer.parseInt(st.nextToken());
		// 사용자 이름
		String name = st.nextToken();
		// 남은 시간
		String time = st.nextToken();

		if (name.equals("admin"))
			FrameManger.getAdminFrame().getSeatPanel().setOff(pc);

		FrameManger.getAdminFrame().getSeatPanel().setOn(pc, name, time);
	}

	// 어드민이 화면을 끄거나 컴퓨터를 종료할 때
	void closeSocket() {
		flag = false;
	}
}
