import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Screen extends Canvas implements ClockInterface {
	public DigitalClockGUI clockGUI;
	public Graphics g;
	private int hue=0;
	private int hour , minute , second;
	private Arrow hourArrow,minuteArrow,secondArrow;
	private int radius;
	private float angle;
	
	
	public Screen(DigitalClockGUI _clockGUI){
	 clockGUI=_clockGUI;
		//this.print(g);
		hourArrow=new Arrow(5,40,0);
		minuteArrow=new Arrow(3,60,0);
		secondArrow=new Arrow(1,80,0);
	}
	
	@Override
	public void setBounds(int x, int y, int width,int  height){
		this.radius=width/2;
		super.setBounds(x, y, width, height);		
	}
	
	public void paint(Graphics _g){
		  Graphics2D g2 = (Graphics2D) _g;
		  
		    g2.setColor(Color.getHSBColor((float)1/360*hue, 1, 1));
		    g2.fillOval(0, 0,this.getWidth(), this.getHeight());
		    g2.setColor(Color.black);
		    g2.setStroke(new BasicStroke(5));
		drawRomanNumbers(g2);
		g2.setStroke(new BasicStroke(hourArrow.thickness));
		g2.drawLine(radius, radius, (int)(hourArrow.length*Math.cos(Math.toRadians(hourArrow.getAngle()))+radius),(int)(hourArrow.length*Math.sin(Math.toRadians(hourArrow.getAngle()))+radius));
		g2.setStroke(new BasicStroke(minuteArrow.thickness));
		g2.drawLine(radius, radius, (int)(minuteArrow.length*Math.cos(Math.toRadians(minuteArrow.getAngle()))+radius),(int)(minuteArrow.length*Math.sin(Math.toRadians(minuteArrow.getAngle()))+radius));
		g2.setStroke(new BasicStroke(secondArrow.thickness));
		g2.drawLine(radius, radius, (int)(secondArrow.length*Math.cos(Math.toRadians(secondArrow.getAngle()))+radius),(int)(secondArrow.length*Math.sin(Math.toRadians(secondArrow.getAngle()))+radius));
		//System.out.println((int)10*Math.sin(Math.toRadians(angle))+radius+" y coord");
		hue++;
		System.out.println("repaint!!");
	}
	
	public void update(){
		this.setBackground(clockGUI.contentPane.getBackground());
		repaint();
	}
	//@Override
	public void update(int _hour, int _minute, int _second) {
		// TODO Auto-generated method stub
		this.hour=_hour;
		this.minute=_minute;
		this.second=_second;
		//this.angle=(360/60)*_second-90;
		this.setBackground(clockGUI.contentPane.getBackground());
		hourArrow.setAngle((float)((360/24)*_hour-90));
		System.out.println("hour: "+String.valueOf(secondArrow.getAngle()));
		minuteArrow.setAngle((float)((360/60)*_minute-90));
		System.out.println("minute: "+String.valueOf(minuteArrow.getAngle()));
		secondArrow.setAngle((float)((360/60)*_second-90));
		System.out.println("second: "+String.valueOf(hourArrow.getAngle()));
		System.out.println("update!! "+angle);
		repaint();
	}

 private void drawRomanNumbers(Graphics2D g2){ 
	 int length=20;
	 float angle=0;
	 g2.setStroke(new BasicStroke(4));
	 for(angle=0 ; angle< 360 ; angle+=(360/12)){
		g2.drawLine((int)((radius-length)*Math.cos(Math.toRadians(angle))+radius), (int)((radius-length)*Math.sin(Math.toRadians(angle))+radius), (int)(radius*Math.cos(Math.toRadians(angle))+radius),(int)(radius*Math.sin(Math.toRadians(angle))+radius));
	 }
	 
 }
	
}
