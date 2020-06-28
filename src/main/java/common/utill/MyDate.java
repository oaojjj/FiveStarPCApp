package main.java.common.utill;

public class MyDate {
	int hour;
	int minute;
	int second;
	boolean flag;
	String mTime;

	public MyDate(int sec) {
		calTime(sec);
		if (sec > 0) {
			flag = true;
		}
	}

	public MyDate(int h, int m, int s) {
		hour = h;
		minute = m;
		second = s;
	}

	public int getSaveTime() {
		return second + (hour * 3600) + (minute * 60);
	}

	public void calTime(int sec) {
		hour = sec / 3600;
		minute = sec % 3600 / 60;
		second = sec % 3600 % 60;
	}

	public void sleepTime() {

		if (second > 0) {
			second--;
		} else {
			if (minute > 0) {
				minute--;
				second = 59;
			} else {
				hour--;
				minute = 59;
				second = 59;
				if (hour == -1) {
					flag = false;
				}
			}
		}
	}

	// 시간 포맷으로 하니까 24시간을 넘으면 표현할 수 가 없어서 로직을 짬..
	@Override
	public String toString() {
		formatTime(hour, minute, second);
		return mTime;
	}

	// 나중에 스트링빌더같은거 써야할 듯
	public void formatTime(int... time) {
		mTime = "";
		for (int i : time) {
			if (i < 10) {
				mTime = mTime + "0" + i + ":";
			} else {
				mTime += i + ":";
			}
		}
		mTime = mTime.substring(0, mTime.length() - 1);
	}

	public boolean flag() {
		return flag;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
