package main.java.thread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.java.common.dto.MemberDTO;
import main.java.common.utill.MyDate;
import main.java.controller.manager.FrameManger;
import main.java.view.frame.HomeFrame;

/*
 * 사용자 데이터 베이스 접근 후 시간이 있다면 그 시간을 남은 시간에 출력 만약 남은 시간이 없다면 컴퓨터 종료(HomeFrame)
 */

// mode-true: 사용자 패널, mode-false: 어드민 패널
public class FeeThread extends Thread {
	private JLabel timeLabel;
	private MyDate myDate;
	boolean flag;
	boolean mode;
	int time;

	public FeeThread(JLabel timeLabel, int time, boolean mode) {
		this.timeLabel = timeLabel;
		this.mode = mode;
		this.time = time;
		flag = true;
	}

	@Override
	public void run() {
		// 초를 시간으로 변환
		myDate = new MyDate(time);

		while (flag) {
			if (myDate.flag()) {
				try {
					timeLabel.setText(myDate.toString());

					// 1초
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 1초씩 줄이기
				myDate.sleepTime();
			} else {
				if (mode) {
					JOptionPane.showMessageDialog(FrameManger.getUserFrame(), "사용시간이 끝났습니다. 3초후 종료됩니다!");
					try {
						Thread.sleep(3000);
						// 로그인 창 띄우기
						FrameManger.getUserFrame().dispose();
						HomeFrame homeFrame = new HomeFrame();
						FrameManger.setHomeFrame(homeFrame);
						return;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	public FeeThread threadStop() {
		flag = false;
		return this;
	}

	public MyDate getMyDate() {
		return myDate;
	}
}
