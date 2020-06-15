package main.java.controller;

import main.java.view.frame.HomeFrame;
import main.java.view.frame.UserFrame;

public class FrameManger {
	private static HomeFrame homeFrame;
	private static UserFrame userFrame;

	public static HomeFrame getHomeFrame() {
		return homeFrame;
	}

	public static UserFrame getUserFrame() {
		return userFrame;
	}

	public static void setHomeFrame(HomeFrame homeFrame) {
		FrameManger.homeFrame = homeFrame;
	}

	public static void setUserFrame(UserFrame userFrame) {
		FrameManger.userFrame = userFrame;
	}

}
