package com.lawranta.panels;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.Timer;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import com.lawranta.SubPanels.currentSessionPanel;
import com.lawranta.frames.PanelContainerFrame;
import com.lawranta.sqllite.AttendanceDAO;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.Color;
import javax.swing.JTextArea;

public class EmployeeFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean clocked = false;
	private static EmployeeDAO cDB;
	private static PinPanel INITIALIZE;
	boolean sort=false;
	private PanelContainerFrame frame;
	
	// private String PIN;

	/**
	 * Create the frame.
	 */
	public EmployeeFrame(EmployeeDAO passedDB, PanelContainerFrame frame)

	{
	    super();
	    this.frame = frame;
		// loaded from database
		cDB = passedDB;
		cDB.setEmployeeInfo();

		if (cDB.getStatus().equals("in")) {
			clocked = true;
		} else {
			clocked = false;
		}

		setVisible(true);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


setBorder(new EmptyBorder(5, 5, 5, 5));

setLayout(null);

		JLabel lblClocked = new JLabel("Clocked " + cDB.getStatus());
		lblClocked.setHorizontalAlignment(SwingConstants.CENTER);
		lblClocked.setBounds(179, 45, 162, 14);
add(lblClocked);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		JLabel lblDate = new JLabel(dtf.format(now));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(193, 117, 148, 14);
add(lblDate);

		JLabel lblTime = new JLabel(currentTime());
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(10, 131, 514, 14);
add(lblTime);

		JLabel lblName = new JLabel(cDB.getName());
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(5, 11, 524, 23);
add(lblName);

		JButton btnClockIn = new JButton("Clock-In");
		btnClockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clocked = true;

				clockIn(currentTime());
				frame.PanelChange(new PinPanel(frame));
				setVisible(false);
			}
		});
		btnClockIn.setBounds(5, 693, 524, 23);
add(btnClockIn);

		JButton btnClockOut = new JButton("Clock-Out");
		btnClockOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clocked = false;

				try {
					clockOut(currentTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				frame.PanelChange(new PinPanel(frame));
				setVisible(false);
			}
		});
		btnClockOut.setBounds(5, 727, 524, 23);
add(btnClockOut);

		// Employee time sessions

		JPanel employeeContent = new currentSessionPanel(cDB, sort);
		employeeContent.setBounds(10, 315, 514, 367);
add(employeeContent);
		
		JButton btnSort = new JButton("sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				if(sort) {
					sort=false;
				}else
				{
					sort=true;
				}
				
				
				
				
				remove(employeeContent);
				JPanel employeeContent = new currentSessionPanel(cDB, sort);
				employeeContent.setBounds(10, 315, 514, 367);
				add(employeeContent);
				
			}
		});
		
		btnSort.setBounds(10, 281, 89, 23);
		add(btnSort);
		
		
		
		
		
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(405, 281, 119, 23);
		add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

		
				frame.PanelChange(new PinPanel(frame));
				setVisible(false);
			}
		});
		
		
		
		
		
		

		if (clocked == false) {
			btnClockIn.setVisible(true);
			btnClockOut.setVisible(false);
			clocked = true;

		} else {

			btnClockIn.setVisible(false);
			btnClockOut.setVisible(true);
			clocked = false;

		}

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// System.out.println(currentTime());
				lblTime.setText(currentTime());
			}
		};
		Timer t = new Timer(1000, taskPerformer);
		t.start();

	}

	/*
	 * public EmployeeFrame() { // TODO Auto-generated constructor stub
	 * System.out.println("error, no employee passed"); }
	 */

	private void clockIn(String time) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		cDB.setStatus("in");
		cDB.dbUpdateEmployeeStatus("in");
		AttendanceDAO dBA = new AttendanceDAO(cDB, dtf.format(now));
		dBA.clockIn(currentTime());

	}

	private void clockOut(String time) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		cDB.setStatus("out");
		cDB.dbUpdateEmployeeStatus("out");
		AttendanceDAO dBA = new AttendanceDAO(cDB, dtf.format(now));

		dBA.setEmployeeID(cDB.getId());
		dBA.setDate(dtf.format(now));
		dBA.clockOut(currentTime());
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
}
