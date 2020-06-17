package main.java.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientSocket {
	// BufferedReader in = null;
	BufferedWriter out = null;
	Socket socket = null;

	public MyClientSocket(String info, String name, int time) {
		try {
			int pc = Integer.parseInt(info) - 1;
			socket = new Socket("pcbangtuto1.iptime.org", 9999);
			// in = new BufferedReader(new
			// InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String message = pc + "/" + name + "/" + time;
			out.write(message);
			out.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
