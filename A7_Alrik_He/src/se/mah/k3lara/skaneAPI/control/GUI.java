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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 304);
		contentPane.setBackground(Color.BLACK);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		canvas.setBackground(new Color(255,255,255,0));
		canvas.setBounds(188, 96, 100, 100);
		contentPane.add(canvas);
		stationTextArea.setTabSize(20);
		stationTextArea.setSize(new Dimension(0, 9));
		stationTextArea.setPreferredSize(new Dimension(4, 30));
		stationTextArea.setMinimumSize(new Dimension(4, 35));
		stationTextArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 10));
		stationTextArea.setForeground(Color.ORANGE);
		stationTextArea.setBackground(Color.BLACK);
		stationTextArea.setEditable(false);
		stationTextArea.setBounds(49, 41, 37, 209);
		
		contentPane.add(stationTextArea);
		towardsTxtArea.setTabSize(20);
		towardsTxtArea.setSize(new Dimension(0, 9));
		towardsTxtArea.setPreferredSize(new Dimension(4, 30));
		towardsTxtArea.setMinimumSize(new Dimension(4, 35));
		towardsTxtArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 10));
		towardsTxtArea.setForeground(Color.ORANGE);
		towardsTxtArea.setBackground(Color.BLACK);
		towardsTxtArea.setEditable(false);

		towardsTxtArea.setBounds(86, 41, 336, 209);
		contentPane.add(towardsTxtArea);
		departTextArea.setTabSize(20);
		departTextArea.setSize(new Dimension(0, 9));
		departTextArea.setPreferredSize(new Dimension(4, 30));
		departTextArea.setMinimumSize(new Dimension(4, 35));
		departTextArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 10));
		departTextArea.setForeground(Color.ORANGE);
		departTextArea.setBackground(Color.BLACK);
		departTextArea.setEditable(false);
		

		departTextArea.setBounds(422, 41, 59, 209);
		contentPane.add(departTextArea);
		

		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s = new AnimateThread(GUI.this);
				s.start();
				StationThread t = new StationThread(GUI.this);
				t.start();
			}
		});
		btnTest.setBounds(188, 248, 97, 25);
		contentPane.add(btnTest);
		starsTextArea.setTabSize(20);
		starsTextArea.setSize(new Dimension(0, 9));
		starsTextArea.setPreferredSize(new Dimension(4, 30));
		starsTextArea.setMinimumSize(new Dimension(4, 35));
		starsTextArea.setForeground(Color.ORANGE);
		starsTextArea.setFont(new Font("Swis721 Blk BT", Font.BOLD, 10));
		starsTextArea.setEditable(false);
		starsTextArea.setBackground(Color.BLACK);
		starsTextArea.setBounds(12, 41, 37, 209);
		
		contentPane.add(starsTextArea);
		
		JLabel lblLinje = new JLabel("Linje");
		lblLinje.setForeground(Color.LIGHT_GRAY);
		lblLinje.setFont(new Font("Arial", Font.BOLD, 13));
		lblLinje.setBounds(12, 13, 56, 16);
		contentPane.add(lblLinje);
		
		JLabel lblNewLabel = new JLabel("Destination");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(123, 12, 165, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Avg\u00E5r");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(414, 12, 56, 16);
		contentPane.add(lblNewLabel_1);

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void search(GUI frame){

		s = new AnimateThread(frame);
		s.start();
		StationThread t = new StationThread(frame);
		t.start();
		
	}
}



