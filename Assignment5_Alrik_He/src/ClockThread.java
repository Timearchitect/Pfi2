import java.util.Calendar;

public class ClockThread extends Thread implements Runnable {
		private ClockInterface clockInterface;
		private Calendar cal;
		
	public ClockThread(ClockInterface _ic){
		this.clockInterface=_ic;
	}
	
	public void run() {
		boolean run = true;
		while(!run){
			try{
				cal=Calendar.getInstance();
				clockInterface.update(cal.get(0), cal.get(1), cal.get(2));
				Thread.sleep(1000);
			}catch(Exception e){
				
			}
		}
		
	}
	

}
