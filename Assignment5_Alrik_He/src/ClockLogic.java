import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClockLogic implements ClockInterface,Runnable {
	private DigitalClockGUI clockGUI;
	private int alarmHour;
	private int alarmMinute;
	private int alarmSecond;
	private static final int MIN_HOUR = 0, MAX_HOUR = 24;
	private static final int MIN = 0, MAX = 59;
	public Thread thread;
	private boolean alarm;

	public ClockLogic(DigitalClockGUI _clockGUI) {
		System.out.println("clockLogic constructed!");
		thread = new Thread(new ClockThread(this));
		thread.start();
		// thread.run();
		this.clockGUI = _clockGUI;
	}

	public void setAlarmHour(int _alarmHour, int _alarmMinute, int _alarmSecond) {
		try {
			if (MIN_HOUR > _alarmHour || _alarmHour > MAX_HOUR) {
				System.out.println("hour");
				JOptionPane.showMessageDialog(null, "wrong hour", "obs!!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.alarmHour = _alarmHour;
			}
			if (MIN > _alarmMinute || _alarmMinute > MAX) {
				System.out.println("minute");
				JOptionPane.showMessageDialog(null, "wrong minutes", "obs!!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.alarmMinute = _alarmMinute;
				
			}
			if (MIN > _alarmSecond || _alarmSecond > MAX) {
				System.out.println("seconds");
				JOptionPane.showMessageDialog(null, "wrong seconds", "obs!!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.alarmSecond = _alarmSecond;
			}
//			clockGUI.alarmLabel.setText(String.format("%02d", this.alarmHour)
//					+ ":" + String.format("%02d", this.alarmMinute) + ":"
//					+ String.format("%02d", this.alarmSecond));
			clockGUI.alarmLabel.setText(String.format("%02d:%02d:%02d",alarmHour,alarmMinute,alarmSecond));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "OBS!!!", "wrong time",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void clearAlarm() {
		clockGUI.alarmLabel.setText("");
		clockGUI.setAlarmActive(false);
		clockGUI.changeIcon();
		this.alarmHour = 0;
		this.alarmMinute = 0;
		this.alarmSecond = 0;
	}

	@Override
	public void update(int _hour, int _minute, int _second) {
		String s = String.format("%02d", _hour) + ":"
				+ String.format("%02d", _minute) + ":"
				+ String.format("%02d", _second);
		System.out.println(s);

		if (alarmHour == _hour && alarmMinute == _minute
				&& alarmSecond == _second) {
			this.alarm = true;
			clockGUI.setAlarmActive(true);
			clockGUI.changeIcon();
		}
		if (alarm) {
			clockGUI.contentPane.setBackground(Color.RED);

			if(clockGUI.minimized){
				if(	!clockGUI.isFocused())clockGUI.requestFocus();
				clockGUI.toFront();
				//clockGUI.setExtendedState(JFrame.ICONIFIED);
				//clockGUI.toFront();
			}

		}
		clockGUI.timeLabel.setText(s);
		clockGUI.canvas.update(_hour, _minute, _second);// include repaint
	}

	public void closeAlarm() {
		System.out.println("alarm turned off!!!");
		alarm = false;
		clockGUI.setAlarmActive(false);
		clockGUI.changeIcon();
		clockGUI.contentPane.setBackground(Color.white);
	}

	@Override
	public void run() {
		
		
	}

}
