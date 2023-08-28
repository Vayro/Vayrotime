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
import javax.swing.BoxLayout;
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

import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.Globals.Global;
import com.lawranta.SubPanels.currentSessionPanel;
import com.lawranta.frames.PanelContainerFrame;
import com.lawranta.services.AttendanceService;
import com.lawranta.services.EmployeeService;
import com.lawranta.sqllite.AttendanceDAO;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

public class EmployeeFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean clocked = false;
	private static EmployeeModel em;
	private static PinPanel INITIALIZE;
	boolean sort = false;
	private PanelContainerFrame frame;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDateTime now = LocalDateTime.now();
	JPanel employeeContent;
	// private String PIN;
	

	/**
	 * Create the frame.
	 */
	public EmployeeFrame(EmployeeModel passedE, PanelContainerFrame frame)

	{
		super();
		this.frame = frame;
		// loaded from database
		em = passedE;
		em.setEmployeeInfo();

		if (em.getStatus().equals("in")) {
			clocked = true;
		} else {
			clocked = false;
		}

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(128, 32767));
		panel.setBorder(null);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 5, 5));
		JLabel lblClocked = new JLabel("Clocked " + em.getStatus());
		panel.add(lblClocked);
		lblClocked.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblClocked.setHorizontalAlignment(SwingConstants.CENTER);
		lblClocked.setBounds(179, 45, 162, 14);
		JLabel lblDate = new JLabel(dtf.format(now));
		panel.add(lblDate);
		lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(193, 117, 148, 14);
		lblDate.setFont(Global.analogFont16f);

		JLabel lblTime = new JLabel(currentTime());
		panel.add(lblTime);
		lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(Global.analogFont16f);
		lblTime.setBounds(10, 131, 514, 14);

		JLabel lblName = new JLabel(em.getName());
		panel.add(lblName);
		lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(5, 11, 524, 23);

		JButton btnClockIn = new JButton("Clock-In");
		btnClockIn.setPreferredSize(new Dimension(64, 25));
		btnClockIn.setMaximumSize(new Dimension(64, 25));
		btnClockIn.setMinimumSize(new Dimension(64, 25));
		panel.add(btnClockIn);
		btnClockIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clocked = true;

				clockIn(currentTime());
				frame.PanelChange(new PinPanel(frame));
				setVisible(false);
			}
		});
		btnClockIn.setBounds(5, 693, 524, 23);

		JButton btnClockOut = new JButton("Clock-Out");
		panel.add(btnClockOut);
		btnClockOut.setAlignmentX(Component.CENTER_ALIGNMENT);
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

		add(Global.padding(32));

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		now = LocalDateTime.now();

	

		JButton btnSort = new JButton("sort");
		btnSort.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (sort) {
					sort = false;
				} else {
					sort = true;
				}

				//remove(employeeContent);
				//remove(btnSort);
				//JPanel employeeContent = new currentSessionPanel(em, sort);
				// employeeContent.setBounds(10, 315, 514, 367);
				//add(employeeContent);
				toggleSort();

			}
		});

		btnSort.setBounds(10, 281, 89, 23);
		add(btnSort);
		Global.padding(32);
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogOut.setBounds(405, 281, 119, 23);
		add(btnLogOut); 
		Global.padding(32);
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

		
		
		
		
		// Employee time sessions

		employeeContent = new currentSessionPanel(em, sort);
		employeeContent.setBounds(10, 315, 514, 367);
		add(employeeContent);
		
		
		
		
		
		
	}

	/*
	 * public EmployeeFrame() { // TODO Auto-generated constructor stub
	 * System.out.println("error, no employee passed"); }
	 */

	private void clockIn(String time) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		em.setStatus("in");
		EmployeeService.setStatus(em, "in");
		AttendanceService.clockIn(em, currentTime(), dtf.format(now));

	}

	private void clockOut(String time) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		em.setStatus("out");
		EmployeeService.dbUpdateEmployeeStatus(em, "out");
		AttendanceService.clockOut(em, dtf.format(now), currentTime());

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
	
	
	
	public void toggleSort() {
		remove(employeeContent);
		employeeContent = new currentSessionPanel(em, sort);
		add(employeeContent);
	
	}
	
}
