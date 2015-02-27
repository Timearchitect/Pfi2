import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;


public class AnimateThread extends Thread {

	private GUI gui;
	private volatile boolean running = true;
	private int angle;
	private BasicStroke stroke = new BasicStroke(10);
	private Color purple = new Color(200, 20, 150);

	public AnimateThread(GUI gui) {
		super();
		this.gui = gui;
	}

	private void animateWait() {

		angle++;
		if (angle > 360) {
				angle=0;
			}
			
			Graphics2D g2=	(Graphics2D) gui.canvas.getGraphics();
						// g2.setBackground(Color.black);
						//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-0.1f));
						g2.setColor(purple);
						g2.setStroke(stroke);
						g2.drawArc((int) (0+stroke.getLineWidth()/2), (int) (0+stroke.getLineWidth()/2),(int) (gui.canvas.getWidth()-stroke.getLineWidth()), (int)(gui.canvas.getHeight()-stroke.getLineWidth()), -(angle*15), 50);
						g2.setColor(Color.WHITE);
					g2.drawArc((int) (0+stroke.getLineWidth()/2), (int) (0+stroke.getLineWidth()/2),(int) (gui.canvas.getWidth()-stroke.getLineWidth()), (int)(gui.canvas.getHeight()-stroke.getLineWidth()), -(angle*15), 30);
						//g2.fillOval(0, 0, 30, 30);
						//g2.drawOval(0, 50, 30, 30);		
						//g2.fillRect(50, 0, 30, 30);
						//g2.drawRect(50, 50, 30, 30);

						//g2.draw(new Ellipse2D.Double(0, 100, 30, 30));
						gui.canvas.paint(g2);
			
			}



	public void run(){
		//gui.animate("Searching");
		
		//System.out.println("running");
		String dots="";
		while(running){
		//System.out.println("running");
		//gui.animate("Searching");
		
		animateWait();
		
		if(dots.equals("......")){dots="";}
		if(angle%20 ==5)dots+=".";
		gui.textArea.setText("Searching"+dots);
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}


	}
	
 public void kill(){
	 this.running=false;
	 
 }
	
}
