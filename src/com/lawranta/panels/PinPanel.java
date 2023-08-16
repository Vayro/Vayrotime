package com.lawranta.panels;
import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.Timer;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.lawranta.Globals.Global;
import com.lawranta.frames.*;
import com.lawranta.panels.*;
import com.lawranta.services.EmployeeService;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.sql.Connection;

import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class PinPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	Font times64 = new Font("TimesRoman", Font.PLAIN, 64);
	private JPasswordField passwordField;
	protected AdminPanel INITIALIZE;
	private PanelContainerFrame frame;
	EmployeeService eSer = new EmployeeService(); 

	/**
	 * Create the frame.
	 */
	public PinPanel(PanelContainerFrame frame) {
		   super();
		    this.frame = frame;
			//setResizable(false);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//setBounds(400, 400, 550, 800);
			//setLocationRelativeTo(null);
			setVisible(true);

			setBackground(Color.WHITE);
			setBorder(new EmptyBorder(5, 5, 5, 5));
			setLayout(null);
		lblTitle = new JLabel("Vayrotime");
		lblTitle.setBounds(10, 11, 519, 82);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(Global.headerFont);
		add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBounds(117, 230, 300, 300);
add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPress(KeyEvent.VK_1);

			}
		});
		panel.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_2);

			}
		});
		panel.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_3);

			}
		});
		panel.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_4);

			}
		});
		panel.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_5);

			}
		});
		panel.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_6);

			}
		});
		panel.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_7);

			}
		});
		panel.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_8);

			}
		});
		panel.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_9);

			}
		});
		panel.add(btn9);

		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_0);

			}
		});
		panel.add(btn0);

		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);

		// PIN FIELD

		passwordField = new JPasswordField();
		passwordField.setBounds(117, 187, 300, 20);

		PlainDocument document = (PlainDocument) passwordField.getDocument();
		document.setDocumentFilter(new DocumentFilter() {

			@Override
			public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

				if (string.length() <= 6) {
					super.replace(fb, offset, length, text, attrs); // To change body of generated methods, choose Tools
																	// | Templates.
				}
				if (string.length() == 6) {

					// This is where we want to check if the PIN entered is valid. It should occur
					// as soon as 6 digits are entered.
					// if pin is valid, go to next frame
					char[] charpin = passwordField.getPassword();
					passwordField.setText("");
					checkPin(charpin);

				}

			}

		});

add(passwordField);

		JButton btnAdmin = new JButton("admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = new String(passwordField.getPassword());
				System.out.println("PIN: " + s);

				JPanel panel=new AdminPanel(frame);
				frame.PanelChange(panel);
				setVisible(false);
				
			//	dispose();

			}
		});
		btnAdmin.setVisible(true);
		btnAdmin.setBounds(222, 541, 89, 23);
add(btnAdmin);

		// date time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		JLabel lblDate = new JLabel(dtf.format(now));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(117, 162, 300, 14);
add(lblDate);

		JLabel lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(10, 137, 514, 14);
add(lblTime);

		// System.out.println(currentTime());
		lblTime.setText(currentTime());

		JTextArea output = new JTextArea();
		output.setBounds(10, 587, 514, 82);
		output.setEditable(false);
	add(output);

		/*
		 * PrintStream printStream = new PrintStream(new CustomOutputStream(output));
		 * 
		 * System.setOut(printStream); System.setErr(printStream);
		 */

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// System.out.println(currentTime());
				lblTime.setText(currentTime());
			}
		};
		Timer t = new Timer(1000, taskPerformer);
		t.start();

	}

	public String currentTime() {
		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int aP = calendar.get(Calendar.AM_PM);
		String currentTime = hours + ":" + checkTime(minutes) + ":" + checkTime(seconds) + " " + amP(aP);
		return currentTime;
	}

	public String checkTime(int t) {
		String time1;
		if (t < 10) {
			time1 = ("0" + t);
		} else {
			time1 = ("" + t);
		}
		return time1;
	}

	public String amP(int ap) {
		String amPm;
		if (ap == 0)
			amPm = "AM";
		else
			amPm = "PM";
		return amPm;
	}

	public void buttonPress(int key) {

		char[] pin;
		pin = passwordField.getPassword();
		int pinLength = pin.length;
		// System.out.println(pinLength);
		if (pinLength < 6) {
			/*
			 * String addString = new String(passwordField.getPassword());
			 * passwordField.setText(addString + new String("1"));
			 */
			passwordField.requestFocus();

			try {
				Robot robot = new Robot();
				robot.keyPress(key);
				robot.keyRelease(key);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}

		}

	}

	public void checkPin(char[] charpin) {
		// This is where we want to check if the PIN entered is valid. It should occur
		// as soon as 4 digits are entered.
		// if pin is valid, go to next frame

		String achtungPin = "372707"; // temp pin

		// check database

		String strPin = new String(charpin);
		System.out.println("Checking if pin is valid. (" + strPin + ")");

		if (eSer.databaseCompare(strPin)) {
			// if YES, move to employee screen
			System.out.println("Pin Valid");
			EmployeeDAO cDB = new EmployeeDAO(strPin);
			frame.PanelChange(new EmployeeFrame(cDB,frame));
			setVisible(false);
			System.out.println("Launching private employee frame");
			setVisible(false);
			//dispose();
			
		} else {
			// incorrect pin entered, reset password field

			System.out.println("incorrect pin. (" + strPin + ")");

		}

	}


}
