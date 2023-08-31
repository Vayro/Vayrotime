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
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;

public class AdminPinPanel extends JPanel {

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
	public AdminPinPanel(PanelContainerFrame frame) {
		setPreferredSize(new Dimension(480, 480));
		setBackground(new Color(58, 58, 58));
		setBorder(new CompoundBorder());
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Global.padding(20));

	/*	JPanel headingPanel = new JPanel();
		headingPanel.setPreferredSize(new Dimension(0, 0));
		headingPanel.setBackground(Color.BLACK);
		add(headingPanel);
*/
		// setBackground(Color.WHITE);
		// setBorder(new EmptyBorder(5, 5, 5, 5));
		// setLayout(null);
		lblTitle = new JLabel("Enter admin PIN");
		lblTitle.setPreferredSize(new Dimension(400, 71));
		lblTitle.setMinimumSize(new Dimension(400, 71));
		lblTitle.setMaximumSize(new Dimension(400, 71));
		add(lblTitle);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(Global.analogFont32f);
		Global.padding(32);


		// set focus to password field
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				passwordField.requestFocus();
			}
		});

		JPanel pwdPinPanel = new JPanel();
		pwdPinPanel.setMaximumSize(new Dimension(256, 288));
		pwdPinPanel.setPreferredSize(new Dimension(256, 288));
		add(pwdPinPanel);
		pwdPinPanel.setLayout(new BoxLayout(pwdPinPanel, BoxLayout.Y_AXIS));

		// DisplayImage();

		// date time

		// PIN FIELD

		passwordField = new JPasswordField();
		pwdPinPanel.add(passwordField);
		passwordField.setMaximumSize(new Dimension(1000, 7));
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
		
		
		
		
		

		JPanel keyPadPanel = new JPanel();
		keyPadPanel.setBackground(Color.DARK_GRAY);
		pwdPinPanel.add(keyPadPanel);
		keyPadPanel.setFont(Global.analogFont32f);
		keyPadPanel.setSize(new Dimension(192, 256));
		keyPadPanel.setPreferredSize(new Dimension(192, 256));
		keyPadPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		keyPadPanel.setLayout(new GridLayout(5, 3, 5, 5));

		JButton btn1 = new JButton("1");

		setIconImage(btn1);
		btn1.setFont(Global.analogFont32f);
		btn1.setMinimumSize(new Dimension(16, 16));

		btn1.setPreferredSize(new Dimension(32, 32));
		btn1.setMaximumSize(new Dimension(32, 32));


		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonPress(KeyEvent.VK_1);

			}
		});
		keyPadPanel.add(btn1);

		JButton btn2 = new JButton("2");
		
		setIconImage(btn2);
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
		setIconImage(btn3);
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
		setIconImage(btn4);
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
		setIconImage(btn5);
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
		setIconImage(btn6);

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
		setIconImage(btn7);

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
		setIconImage(btn8);

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
		btn9.setFont(Global.analogFont32f);
		setIconImage(btn9);
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
		btn0.setFont(Global.analogFont32f);
		setIconImage(btn0);
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

		keyPadPanel.add(lblNewLabel_1);


		
		JButton btnBack = new JButton("Back\r\n");
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = new String(passwordField.getPassword());
				System.out.println("PIN: " + s);

				JPanel panel = new PinPanel(frame);
				frame.PanelChange(panel);
				setVisible(false);

				// dispose();

			}
		});
		btnBack.setVisible(true);
		add(btnBack);

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
		// as soon as 6 digits are entered.
		// if pin is valid, go to next frame

		// check database

		String strPin = new String(charpin);
		System.out.println("Checking if adminPin is valid. (" + strPin + ")");

		if (strPin.equals(Global.adminPin)) {
			// if YES, move to employee screen
			System.out.println("Admin Pin Valid");

			frame.PanelChange(new AdminPanel(this.frame));
			setVisible(false);
			System.out.println("Launching private admin frame");
			setVisible(false);
			// dispose();

		} else {
			// incorrect pin entered, reset password field

			System.out.println("incorrect pin. (" + strPin + ")");

		}

	}

	
	private void setIconImage(JButton btn) {
		btn.setIcon(new ImageIcon(AdminPinPanel.class.getResource("/images/button1.png")));
		btn.setPressedIcon(new ImageIcon(AdminPinPanel.class.getResource("/images/button1.png")));
		btn.setBackground(new Color(0,0,0));
		btn.setForeground(Color.RED);
		btn.setFont(Global.analogFont32f);
		btn.setForeground(Color.RED);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	
}
