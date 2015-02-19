import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Screen extends Canvas implements ClockInterface {

	public Graphics g;
	private int hue=0;
	private int hour , minute , second;
	private int radius,hourArrow=50,minuteArrow=100,secondArrow=150;
	private float angle;
	
	
	
	public Screen(){
		//this.print(g);
	}
	
	@Override
	public void setBounds(int x, int y, int width,int  height){
		this.radius=width/2;
		super.setBounds(x, y, width, height);
		
	}
	
	public void paint(Graphics _g){
		_g.setColor(Color.getHSBColor((float)1/360*hue, 1, 1));
		_g.fillOval(0, 0,this.getWidth(), this.getHeight());
		_g.setColor(Color.black);
		_g.drawLine(radius, radius, (int)(hourArrow*Math.cos(Math.toRadians(angle))+radius),(int)(hourArrow*Math.sin(Math.toRadians(angle))+radius));
		_g.drawLine(radius, radius, (int)(minuteArrow*Math.cos(Math.toRadians(angle))+radius),(int)(minuteArrow*Math.sin(Math.toRadians(angle))+radius));
		_g.drawLine(radius, radius, (int)(secondArrow*Math.cos(Math.toRadians(angle))+radius),(int)(secondArrow*Math.sin(Math.toRadians(angle))+radius));
		//System.out.println((int)10*Math.sin(Math.toRadians(angle))+radius+"   y coord");
		hue=hue+10;
		System.out.println("repaint!!");
	}
	
	//@Override
	public void update(int _hour, int _minute, int _second) {
		// TODO Auto-generated method stub
		this.hour=_hour;
		this.minute=_minute;
		this.second=_second;
		this.angle=(360/60)*_second-90;
		System.out.println("update!! "+angle);

	}


	
}
