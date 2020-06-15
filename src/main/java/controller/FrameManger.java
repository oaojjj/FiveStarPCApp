package main.java.controller;

import main.java.view.frame.HomeFrame;
import main.java.view.frame.UserFrame;

public class FrameManger {
	private static HomeFrame homeFrame;
	private static UserFrame userFrame;

	public static void main(String[] args) {
		homeFrame = new HomeFrame();
	}

	public static HomeFrame getHomeFrame() {
		return homeFrame;
	}

	public static UserFrame getUserFrame() {
		return userFrame;
	}

}
