package com.lawranta.panels;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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

import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.Globals.Global;
import com.lawranta.frames.*;
import com.lawranta.panels.*;
import com.lawranta.services.EmployeeService;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Cursor;

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
		setBackground(Color.LIGHT_GRAY);

		    this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			//setBackground(Color.WHITE);
		//	setBorder(new EmptyBorder(5, 5, 5, 5));
		//setLayout(null);
		lblTitle = new JLabel("Vayrotime");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(Global.headerFont);
		//add(lblTitle);

		DisplayImage();
		
		
		// date time
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDateTime now = LocalDateTime.now();

				JLabel lblDate = new JLabel(dtf.format(now));
				lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDate);

				JLabel lblTime = new JLabel("");
				lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTime);

				// System.out.println(currentTime());
				lblTime.setText(currentTime());

				JTextArea output = new JTextArea();
				output.setVisible(false);
				output.setAlignmentY(Component.BOTTOM_ALIGNMENT);
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

		
		
		
			
		
				// PIN FIELD

				passwordField = new JPasswordField();
				passwordField.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				passwordField.setHorizontalAlignment(SwingConstants.CENTER);
				passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));

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
				
				
				//set focus to password field
				frame.addWindowListener( new WindowAdapter() {
				    public void windowOpened( WindowEvent e ){
				    	passwordField.requestFocus();
				    }
				}); 
				

		add(passwordField);
		
				
				
				
				
				
		
		
		
		
		
		JPanel keyPadPanel = new JPanel();
		keyPadPanel.setFont(Global.analogFont);
		keyPadPanel.setSize(new Dimension(480, 480));
		keyPadPanel.setPreferredSize(new Dimension(480, 480));
		keyPadPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
add(keyPadPanel);
		keyPadPanel.setLayout(new GridLayout(5, 3, 5, 5));

		JButton btn1 = new JButton("1");
		btn1.setFont(Global.analogFont);
		btn1.setMinimumSize(new Dimension(16, 16));
		btn1.setSize(new Dimension(16, 16));
		btn1.setPreferredSize(new Dimension(32, 32));
		btn1.setMaximumSize(new Dimension(32, 32));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPress(KeyEvent.VK_1);

			}
		});
		keyPadPanel.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setSize(new Dimension(16, 16));
		btn2.setMinimumSize(new Dimension(16, 16));
		btn2.setPreferredSize(new Dimension(32, 32));
		btn2.setMaximumSize(new Dimension(32, 32));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_2);

			}
		});
		keyPadPanel.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setSize(new Dimension(16, 16));
		btn3.setMinimumSize(new Dimension(16, 16));
		btn3.setPreferredSize(new Dimension(32, 32));
		btn3.setMaximumSize(new Dimension(32, 32));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_3);

			}
		});
		keyPadPanel.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.setSize(new Dimension(16, 16));
		btn4.setMinimumSize(new Dimension(16, 16));
		btn4.setPreferredSize(new Dimension(32, 32));
		btn4.setMaximumSize(new Dimension(32, 32));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_4);

			}
		});
		keyPadPanel.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setSize(new Dimension(16, 16));
		btn5.setMinimumSize(new Dimension(16, 16));
		btn5.setPreferredSize(new Dimension(32, 32));
		btn5.setMaximumSize(new Dimension(32, 32));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_5);

			}
		});
		keyPadPanel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setSize(new Dimension(16, 16));
		btn6.setMinimumSize(new Dimension(16, 16));
		btn6.setPreferredSize(new Dimension(32, 32));
		btn6.setMaximumSize(new Dimension(32, 32));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_6);

			}
		});
		keyPadPanel.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.setSize(new Dimension(16, 16));
		btn7.setMinimumSize(new Dimension(16, 16));
		btn7.setPreferredSize(new Dimension(32, 32));
		btn7.setMaximumSize(new Dimension(32, 32));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_7);

			}
		});
		keyPadPanel.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setSize(new Dimension(16, 16));
		btn8.setMinimumSize(new Dimension(16, 16));
		btn8.setPreferredSize(new Dimension(32, 32));
		btn8.setMaximumSize(new Dimension(32, 32));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_8);

			}
		});
		keyPadPanel.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setSize(new Dimension(16, 16));
		btn9.setMinimumSize(new Dimension(16, 16));
		btn9.setPreferredSize(new Dimension(32, 32));
		btn9.setMaximumSize(new Dimension(32, 32));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_9);

			}
		});
		keyPadPanel.add(btn9);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setSize(new Dimension(16, 16));
		keyPadPanel.add(lblNewLabel);

		JButton btn0 = new JButton("0");
		btn0.setSize(new Dimension(16, 16));
		btn0.setMinimumSize(new Dimension(16, 16));
		btn0.setPreferredSize(new Dimension(32, 32));
		btn0.setMaximumSize(new Dimension(32, 32));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(KeyEvent.VK_0);

			}
		});
		keyPadPanel.add(btn0);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setSize(new Dimension(16, 16));
		keyPadPanel.add(lblNewLabel_1);

		

		JButton btnAdmin = new JButton("admin");
		btnAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
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
add(btnAdmin);

		
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

		if (EmployeeService.databaseCompare(strPin)) {
			// if YES, move to employee screen
			System.out.println("Pin Valid");
			
			
			EmployeeModel e = new EmployeeModel(strPin);
			
			
			frame.PanelChange(new EmployeeFrame(e,frame));
			setVisible(false);
			System.out.println("Launching private employee frame");
			setVisible(false);
			//dispose();
			
		} else {
			// incorrect pin entered, reset password field

			System.out.println("incorrect pin. (" + strPin + ")");

		}

	}
	
	 public void DisplayImage() 
	    {
		Image i = null;
		try {
			i = ImageIO.read(new File("src\\images\\timeline.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i=i.getScaledInstance(480, 106, Image.SCALE_DEFAULT);
		
		 JLabel picLabel = new JLabel(new ImageIcon(i));
		 picLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		 picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		 add(picLabel);
	    }
	
	


}
