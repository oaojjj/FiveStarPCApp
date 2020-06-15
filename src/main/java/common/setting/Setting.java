package main.java.common.setting;

import java.awt.Dimension;
import java.awt.Font;

public class Setting {
	private static Setting setting;

	// 피시방 컴퓨터 수
	public static String PC[];

	// fullScreenSize
	public static int SCREEN_WIDTH, SCREEN_HEIGHT;
	private static Dimension screenSize;
	private static final String fontString = "바탕";
	private static final Font basicFont = new Font(fontString, Font.PLAIN, 16);

	private Setting() {
	}

	// TODO 굳이 싱글톤할 필요가 있을까? 일단 보류
	public static Setting getInstance() {
		if (setting == null) {
			setting = new Setting();

		}
		return setting;
	}

	public static void setScreenSize(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
		screenSize = new Dimension(w, h);
	}

	public static Dimension getScreenSize() {
		return screenSize;
	}

	public static Dimension getFHDSize() {
		return new Dimension(1920, 1080);
	}

	public static void setPC(int n) {
		if (n != 0) {
			PC = new String[n];
			PC[0] = "PC 선택";
			for (int i = 1; i < n; i++) {
				PC[i] = String.valueOf(i);
			}
		}
	}

	public static Font getBasicFont() {
		return basicFont;
	}

	public static String getFontstring() {
		return fontString;
	}

}
