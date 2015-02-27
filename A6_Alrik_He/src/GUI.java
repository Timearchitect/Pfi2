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


public class GUI extends JFrame implements Runnable {

	private JPanel contentPane= new JPanel();
	public JTextField fromText = new JTextField();
	public JTextField toText = new JTextField();
	JButton searchBtn = new JButton("S\u00F6k");
	JScrollPane scrollPane = new JScrollPane();
	JTextArea textArea = new JTextArea();
	public static AnimateThread s;
	public  JPanel canvas = new  JPanel();
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
		setBounds(100, 100, 501, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		fromText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!fromText.getText().equals("") && !toText.getText().equals("")){
					searchBtn.setText("Sök resor");

				}else if(!fromText.getText().equals("") || !toText.getText().equals("")){
					searchBtn.setText("Sök station ");
				}else{	
					searchBtn.setText("sök ");
				}	
			}
		});

		fromText.setText("");
		fromText.setBounds(12, 14, 144, 22);
		contentPane.add(fromText);
		fromText.setColumns(10);
		toText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!fromText.getText().equals("") && !toText.getText().equals("")){
					searchBtn.setText("Sök resor");

				}else if(!fromText.getText().equals("") || !toText.getText().equals("")){
					searchBtn.setText("Sök station ");
				}else{
					
					searchBtn.setText("sök ");
				}
		
				
			}
		});

		
		toText.setText("");
		toText.setBounds(168, 14, 143, 22);
		contentPane.add(toText);
		toText.setColumns(10);
		
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s = new AnimateThread(GUI.this);
				s.start();
				String searchURL = Constants.getURL(fromText.getText(),toText.getText(),5); //Malmö C = 80000,  Lund C, 81216 Malmö Gatorg 80100, Hässleholm C 93070
				System.out.println(searchURL);
				System.out.println("// Results when searching:");
				if(!fromText.getText().equals("") && !toText.getText().equals("") ){
				ParserThread t = new ParserThread(GUI.this, searchURL);
				t.start();
				}else{
				StationThread t2 = new StationThread(GUI.this);
				t2.start();
				}
			}
		});
		searchBtn.setBounds(323, 13, 148, 25);
		contentPane.add(searchBtn);
		canvas.setBounds(200, 100, 100, 100);
		canvas.setBackground(new Color(1f,1f,1f,0f ));
		canvas.setVisible(true);
		contentPane.add(canvas);
		

		scrollPane.setBounds(12, 51, 459, 191);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(textArea);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void animate(String string) {
			textArea.setText(string);
	}
}
