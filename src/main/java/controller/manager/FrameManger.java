package main.java.controller.manager;

import main.java.view.frame.AdminFrame;
import main.java.view.frame.HomeFrame;
import main.java.view.frame.UserFrame;

public class FrameManger {
	private static HomeFrame homeFrame;
	private static UserFrame userFrame;
	private static AdminFrame adminFrame;

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

	public static AdminFrame getAdminFrame() {
		return adminFrame;
	}

	public static void setAdminFrame(AdminFrame adminFrame) {
		FrameManger.adminFrame = adminFrame;
	}

}
