package main.java.controller.manager;

import main.java.thread.FeeThread;

public class ThreadManger {
	private static FeeThread feeThread;

	public static FeeThread getFeeThread() {
		return feeThread;
	}

	public static void setFeeThread(FeeThread feeThread) {
		ThreadManger.feeThread = feeThread;
	}

}
