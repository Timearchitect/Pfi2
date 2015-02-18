
public class ClockLogic implements ClockInterface {
	private DigitalClockGUI clockGUI;
	private int alarmHour;
	private int alarmMinute;
	private int alarmSecond;
	public Thread thread;
	
	public ClockLogic(DigitalClockGUI _clockGUI) {
		System.out.println("clockLogic constructed!");
		 thread= new Thread(new ClockThread(this));
		thread.start();
		//thread.run();
	this.clockGUI = _clockGUI;
	}

	public void setAlarmHour(int _alarmHour, int _alarmMinute, int _alarmSecond) {
		this.alarmHour = _alarmHour;
		this.alarmMinute = _alarmMinute;
		this.alarmSecond = _alarmSecond;

	}

	public void clearAlarm() {
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
		clockGUI.timeLabel.setText(s);

	}
	
	
	
	
}
