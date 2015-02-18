
public class ClockLogic implements ClockInterface {
	private DigitalClockGUI clockGUI;
	private int alarmHour;
	private int alarmMinute;
	private int alarmSecond;
  
	
	public ClockLogic(DigitalClockGUI _clockGUI) {
	//	super();
	
	this.clockGUI = _clockGUI;
	}


	public void setAlarmHour(int _alarmHour,int _alarmMinute,int _alarmSecond) {
		this.alarmHour = _alarmHour;
		this.alarmMinute = _alarmMinute;
		this.alarmSecond = _alarmSecond;
		
		
	}
	
	public void clearAlarm(){
		

	}


	@Override
	public void update(int _hour, int _minute, int _second) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
