package main.java.thread;

import main.java.socket.MyServerSocket;

public class ServerThread extends Thread {
	@Override
	public void run() {
		MyServerSocket myServer = new MyServerSocket();
	}
}
