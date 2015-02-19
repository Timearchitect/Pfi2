import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class DigitalClockGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DigitalClockGUI frame;
	private ClockLogic clockLogic= new ClockLogic(this);	
	public JPanel contentPane=new JPanel();
	private JTextField hoursText = new JTextField();
	private JTextField minutesText = new JTextField();
	private JTextField secondsText= new JTextField();
	public JLabel timeLabel = new JLabel("currentTime");
	public JLabel alarmLabel = new JLabel("Alarm");
	public Screen canvas = new Screen();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalClockGUI frame = new DigitalClockGUI();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					//frame.addDefaultCloseOperation(frame.clockLogic.thread.stop());
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				//frame.clockLogic.thread.stop();
	              System.out.println("Closed");
	              arg0.getWindow().dispose();
	             // frame.clockLogic.thread.destroy();
			}
		});
		this.setTitle("Alarm clock!!!");
		
		setBounds(100, 100, 383, 261);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Color color =contentPane.getBackground();
		System.out.println(color);
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		timeLabel.setBounds(204, 33, 194, 35);
		contentPane.add(timeLabel);

		alarmLabel.setBounds(204, 81, 56, 16);
		contentPane.add(alarmLabel);
		hoursText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hoursText.selectAll();
			}
		});

		hoursText.setText("hours");
		hoursText.setBounds(131, 186, 41, 22);
		contentPane.add(hoursText);
		hoursText.setColumns(10);
		
		minutesText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minutesText.selectAll();
			}
		});

		minutesText.setText("minute");
		minutesText.setBounds(184, 186, 49, 22);
		contentPane.add(minutesText);
		minutesText.setColumns(10);
		
		secondsText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				secondsText.selectAll();
			}
		});
		
		secondsText.setText("seconds");
		secondsText.setBounds(245, 186, 56, 22);
		contentPane.add(secondsText);
		secondsText.setColumns(10);
		

		canvas.setBounds(10, 10, 170, 170);
		contentPane.add(canvas);
		
		JButton Alarm = new JButton("set Alarm");
		Alarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				clockLogic.setAlarmHour(Integer.parseInt(hoursText.getText()),Integer.parseInt(minutesText.getText()),Integer.parseInt(secondsText.getText()));
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "wrong numbers on the time fields.", "wrong time", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		Alarm.setBounds(204, 120, 97, 25);
		contentPane.add(Alarm);
		
		JButton btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockLogic.clearAlarm();
				clockLogic.closeAlarm();
			}
		});
		btnClear.setBounds(204, 155, 97, 25);
		contentPane.add(btnClear);
		
	}
	
	public void setTimeOnLabel(String time){
		timeLabel.setText("");
		
	}
	public void alarm(boolean active){
		alarmLabel.setText("");
		
	}
}
