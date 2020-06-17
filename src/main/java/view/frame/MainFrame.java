package main.java.view.frame;

import main.java.controller.manager.FrameManger;

public class MainFrame {
	public static void main(String[] args) {
		HomeFrame homeFrame = new HomeFrame();
		FrameManger.setHomeFrame(homeFrame);
	}
}
