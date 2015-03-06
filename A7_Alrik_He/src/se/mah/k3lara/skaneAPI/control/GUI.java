package se.mah.k3lara.skaneAPI.control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import se.mah.k3lara.skaneAPI.control.Constants;
import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Line;
import se.mah.k3lara.skaneAPI.model.Lines;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Canvas;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JLabel;


public class GUI extends JFrame implements Runnable {

	private JPanel contentPane= new JPanel();
	public static AnimateThread s;
	public JTextArea towardsTxtArea = new JTextArea();
	public JTextArea departTextArea = new JTextArea();
	public  JPanel canvas = new  JPanel();
	public JTextArea stationTextArea = new JTextArea();
	public JTextArea starsTextArea = new JTextArea();
	public JButton btnTest = new JButton("search");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
						frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
						search(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		LineThread t = new LineThread(); // nested
		t.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 328);
		contentPane.setBackground(Color.BLACK);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		canvas.setBackground(new Color(255,255,255,0));
		canvas.setBounds(188, 126, 100, 100);
		contentPane.add(canvas);
		stationTextArea.setTabSize(20);
		stationTextArea.setSize(new Dimension(0, 9));
		stationTextArea.setPreferredSize(new Dimension(4, 30));
		stationTextArea.setMinimumSize(new Dimension(4, 35));
		stationTextArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 12));
		stationTextArea.setForeground(Color.ORANGE);
		stationTextArea.setBackground(Color.BLACK);
		stationTextArea.setEditable(false);
		stationTextArea.setBounds(49, 71, 37, 209);
		
		contentPane.add(stationTextArea);
		towardsTxtArea.setTabSize(20);
		towardsTxtArea.setSize(new Dimension(0, 9));
		towardsTxtArea.setPreferredSize(new Dimension(4, 30));
		towardsTxtArea.setMinimumSize(new Dimension(4, 35));
		towardsTxtArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 12));
		towardsTxtArea.setForeground(Color.ORANGE);
		towardsTxtArea.setBackground(Color.BLACK);
		towardsTxtArea.setEditable(false);

		towardsTxtArea.setBounds(86, 71, 336, 209);
		contentPane.add(towardsTxtArea);
		departTextArea.setTabSize(20);
		departTextArea.setSize(new Dimension(0, 9));
		departTextArea.setPreferredSize(new Dimension(4, 30));
		departTextArea.setMinimumSize(new Dimension(4, 35));
		departTextArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 12));
		departTextArea.setForeground(Color.ORANGE);
		departTextArea.setBackground(Color.BLACK);
		departTextArea.setEditable(false);
		

		departTextArea.setBounds(422, 71, 59, 209);
		contentPane.add(departTextArea);
		

		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s = new AnimateThread(GUI.this);
				s.start();
				//StationThread t = new StationThread(GUI.this);
				LineThread t = new LineThread();
				t.start();
			}
		});
		btnTest.setBounds(191, 297, 97, 25);
		contentPane.add(btnTest);
		starsTextArea.setTabSize(20);
		starsTextArea.setSize(new Dimension(0, 9));
		starsTextArea.setPreferredSize(new Dimension(4, 30));
		starsTextArea.setMinimumSize(new Dimension(4, 35));
		starsTextArea.setForeground(Color.ORANGE);
		starsTextArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 12));
		starsTextArea.setEditable(false);
		starsTextArea.setBackground(Color.BLACK);
		starsTextArea.setBounds(12, 71, 37, 209);
		
		contentPane.add(starsTextArea);
		
		JLabel lblLinje = new JLabel("Linje");
		lblLinje.setForeground(Color.LIGHT_GRAY);
		lblLinje.setFont(new Font("Arial", Font.BOLD, 13));
		lblLinje.setBounds(12, 43, 56, 16);
		contentPane.add(lblLinje);
		
		JLabel lblNewLabel = new JLabel("Destination");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(123, 42, 165, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Avg\u00E5r");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(414, 42, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblUbtshallen = new JLabel("Ub\u00E5tshallen");
		lblUbtshallen.setFont(new Font("Arial", Font.PLAIN, 24));
		lblUbtshallen.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbtshallen.setForeground(Color.LIGHT_GRAY);
		lblUbtshallen.setBounds(0, 0, 494, 40);
		contentPane.add(lblUbtshallen);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void search(GUI frame){

		s = new AnimateThread(frame);
		s.start();
	//	StationThread t = new StationThread(frame); 
		
		//t.start();
		
	}
	
	
 public class LineThread extends Thread {


		private volatile boolean running;
		private String searchURL;

		private Lines lines;
		private Calendar cal;
		private int noticeTime=1;
		
		public LineThread() {
			super();
			this.running = true;
			cal=Calendar.getInstance();
			System.out.println("lineThread");
		}



		public void run(){
			while(true){
			cal=Calendar.getInstance();
	
			lines = Parser.getStationResults(new Station("80046"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.kill();

			towardsTxtArea.setText("");
			for (Line l : lines.getLines()) {
				int lateTime=0;
				if(l.getDepTimeDeviation()!=""){
				 lateTime= Integer.valueOf(l.getDepTimeDeviation());
				}
				System.out.println(lateTime);
				int lineHour=l.getDepTime().get(Calendar.HOUR_OF_DAY);
				int lineMinute=l.getDepTime().get(Calendar.MINUTE) + lateTime; // med förseningar
				System.out.println(cal.get(Calendar.MINUTE));
				System.out.println(l.getDepTime().get(Calendar.MINUTE));
				if(cal.get(Calendar.HOUR_OF_DAY)==lineHour && cal.get(Calendar.MINUTE)+noticeTime>=lineMinute){
				starsTextArea.append("*\n");
				if((lineHour-cal.get(Calendar.MINUTE))<=0){
					departTextArea.append("0 min\n");
				}else{
				departTextArea.append(( lineHour-cal.get(Calendar.MINUTE))+" min\n" );
				}
				}else{
				starsTextArea.append("\n");
				departTextArea.append(Helpers.to2Digits(lineHour) +":"+Helpers.to2Digits(lineMinute) +"\n" );
				}
				stationTextArea.append(l.getLine()+"\n");
				towardsTxtArea.append(l.getDestination()+"\n");
			}
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.running=true;
			}
		}

		
	}
}



