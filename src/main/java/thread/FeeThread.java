package main.java.thread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.java.common.dto.MemberDTO;
import main.java.common.utill.MyDate;
import main.java.controller.FrameManger;
import main.java.view.frame.HomeFrame;

public class FeeThread extends Thread {
	private JLabel timeLabel;
	private MyDate myDate;
	boolean flag;

	public FeeThread(JLabel timeLabel) {
		this.timeLabel = timeLabel;
		flag = true;
	}

	/*
	 * 사용자 데이터 베이스 접근 후 시간이 있다면 그 시간을 남은 시간에 출력 만약 남은 시간이 없다면(후불)
	 */

	@Override
	public void run() {
		int sec = MemberDTO.getMemberDTO().getSaveTime();
		myDate = new MyDate(sec);
		while (flag) {
			try {
				if (myDate.flag()) {
					timeLabel.setText(myDate.toString());

					// 1초
					Thread.sleep(1000);
					// 1초씩 줄이기
					myDate.sleepTime();
				} else {
					JOptionPane.showMessageDialog(FrameManger.getUserFrame(), "사용시간이 끝났습니다.");
					// 로그인 창 띄우기
					FrameManger.getUserFrame().dispose();
					// TODO 이부분은 메인프레임에서 처리해야 할듯
					HomeFrame homeFrame = new HomeFrame();
					FrameManger.setHomeFrame(homeFrame);
					return;
				}

			} catch (InterruptedException e) {
				return;
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
