import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DigitalClockGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClockLogic clockLogic= new ClockLogic(this);	
	private JPanel contentPane=new JPanel();
	private JTextField hoursText = new JTextField();
	private JTextField minutesText = new JTextField();
	private JTextField secondsText= new JTextField();
	private JLabel timeLabel = new JLabel("currentTime");
	private JLabel alarmLabel = new JLabel("Alarm");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalClockGUI frame = new DigitalClockGUI();
					//ClockThread thread= new ClockThread();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DigitalClockGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 300);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		timeLabel.setBounds(414, 40, 120, 16);
		contentPane.add(timeLabel);

		alarmLabel.setBounds(414, 81, 56, 16);
		contentPane.add(alarmLabel);

		hoursText.setText("hours");
		hoursText.setBounds(124, 188, 116, 22);
		contentPane.add(hoursText);
		hoursText.setColumns(10);
		

		minutesText.setText("minute");
		minutesText.setBounds(291, 188, 116, 22);
		contentPane.add(minutesText);
		minutesText.setColumns(10);
		
		secondsText.setText("seconds");
		secondsText.setBounds(434, 188, 116, 22);
		contentPane.add(secondsText);
		secondsText.setColumns(10);
		
	}
	
	public void setTimeOnLabel(String time){
		timeLabel.setText("");
		
	}
	public void alarm(boolean active){
		
		alarmLabel.setText("");
		
	}
}
