import java.util.Calendar;

public class ClockThread implements Runnable {
		private ClockInterface clockInterface;
		private Calendar cal;
		private volatile boolean  run;
		
	public ClockThread(ClockInterface _ic){
		this.clockInterface=_ic;
		run = true;
	}
	
	public void run() {
		System.out.println("running function!!");
		
		while(run){// loopar infinite
			try{
				cal=Calendar.getInstance();
				System.out.println("run thread");
				clockInterface.update(cal.get(cal.HOUR_OF_DAY), cal.get(cal.MINUTE), cal.get(cal.SECOND));
				Thread.sleep(999);
			}catch(Exception e){
				
			}
		}
	}
	
	public void stopRunning()
	{
	    run = false;
	}
}
