import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ClockLogic implements ClockInterface {
	private DigitalClockGUI clockGUI;
	private int alarmHour;
	private int alarmMinute;
	private int alarmSecond;
	private final int MIN_HOUR=0,MAX_HOUR=24;
	private final int MIN=0,MAX=59;
	public Thread thread;
	private boolean alarm;
	
	public ClockLogic(DigitalClockGUI _clockGUI) {
		System.out.println("clockLogic constructed!");
		 thread= new Thread(new ClockThread(this));
		thread.start();
		//thread.run();
	this.clockGUI = _clockGUI;
	}

	public void setAlarmHour(int _alarmHour, int _alarmMinute, int _alarmSecond) {
		try{
			if (MIN_HOUR > _alarmHour || _alarmHour > MAX_HOUR) {
				System.out.println("hour");
				JOptionPane.showMessageDialog(null, "wrong hour","obs!!",  JOptionPane.ERROR_MESSAGE); 
			} else {
				this.alarmHour = _alarmHour;
			}
			if (MIN > _alarmMinute || _alarmMinute > MAX) {
				System.out.println("minute");
				JOptionPane.showMessageDialog(null,  "wrong minutes","obs!!", JOptionPane.ERROR_MESSAGE); 
			} else {
				this.alarmMinute = _alarmMinute;
			}
			if (MIN > _alarmSecond || _alarmSecond > MAX) {
				System.out.println("seconds");
				JOptionPane.showMessageDialog(null, "wrong seconds","obs!!",  JOptionPane.ERROR_MESSAGE); 
			} else {
				this.alarmSecond = _alarmSecond;
			}
			clockGUI.alarmLabel.setText(String.format("%02d", this.alarmHour) + ":" + String.format("%02d", this.alarmMinute)
					+ ":" + String.format("%02d", this.alarmSecond));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "ojsan", "wrong time", JOptionPane.ERROR_MESSAGE); 

			
		}
	}

	public void clearAlarm() {
		clockGUI.alarmLabel.setText("");
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


		if (alarmHour == _hour && alarmMinute == _minute && alarmSecond == _second) {
			alarm = true;
		}
		if (alarm) {
			clockGUI.contentPane.setBackground(Color.RED);

			clockGUI.requestFocus();
			//clockGUI.toFront();
			clockGUI.setExtendedState(JFrame.ICONIFIED);
			clockGUI.toFront();
			
//			clockGUI.setExtendedState(clockGUI.ICONIFIED);
//			clockGUI.setExtendedState(clockGUI.NORMAL);
//			clockGUI.setExtendedState(clockGUI.getExtendedState() | JFrame.ICONIFIED);
//			clockGUI.setExtendedState(clockGUI.getExtendedState() & (~JFrame.ICONIFIED));

		} 
		clockGUI.timeLabel.setText(s);
		clockGUI.canvas.update(_hour, _minute, _second);// include repaint

	}
	
	public void closeAlarm(){
		System.out.println("alarm turned off!!!");
	alarm=false;	
	clockGUI.contentPane.setBackground(Color.white);

	}
	
	
}
