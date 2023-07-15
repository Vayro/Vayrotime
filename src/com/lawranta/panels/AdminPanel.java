package com.lawranta.panels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lawranta.Globals.Global;
import com.lawranta.SubPanels.AdminSubPanel;
import com.lawranta.SubPanels.currentSessionPanel;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.frames.*;
import com.lawranta.popups.DateChooserDialog;
import com.lawranta.popups.TimeLogPopupMenu;
import com.lawranta.popups.newEmployeeDialog;
import com.lawranta.sqllite.DbAttendance;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AdminPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 323277068294353532L;
	private AdminPanel owner;
	protected AdminPanel INITIALIZE;
	public JPanel asp;
	public JPanel employeeContent;
	public JPanel contents;
	public PanelContainerFrame frame;
	public String [] fromToDates = new String[2];
	public int logID=0;
	/**
	 * Create the frame.
	 */
	public AdminPanel(PanelContainerFrame frame) {
		this.frame=frame;
		owner=this;
		
		
		setVisible(true);
		setBounds(400, 400, 550, 800);
		setBackground(new Color(0, 0, 0));
		setLayout(null);

		JPopupMenu pm = new TimeLogPopupMenu(this);
		
		JLabel lblNewLabel = new JLabel("Admins Only");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(Global.header2Font);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(10, 11, 514, 67);
		add(lblNewLabel);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(405, 281, 119, 23);
		add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.PanelChange(new PinPanel(frame));
				setVisible(false);
			}
		});

		employeeContent = new JPanel();
		employeeContent.setBounds(10, 315, 514, 440);
		add(employeeContent);

		JButton btnListEmployees = new JButton("Employees");
		btnListEmployees.setBounds(405, 247, 119, 23);
		btnListEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				relistEmployees();
			}
		});
		add(btnListEmployees);

		JButton btnAdminPin = new JButton("Admin PIN");
		btnAdminPin.setBounds(405, 213, 119, 23);
		add(btnAdminPin);

		JButton btnTimeLogs = new JButton("Time Logs");
		btnTimeLogs.setBounds(10, 247, 119, 23);
		btnTimeLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.setVisible(true);
				((TimeLogPopupMenu) pm).addPopup(owner, pm, e);
				pm.setPopupSize(btnTimeLogs.getSize().width, 100);
				pm.show(owner, btnTimeLogs.getLocation().x, btnTimeLogs.getLocation().y+20);
				
				
			}
			});
		add(btnTimeLogs);

		JButton btnClear = new JButton("Clear Dates");
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fromToDates[0]=null;
				fromToDates[1]=null;
				relistLogs(logID, fromToDates[0], 	fromToDates[1]);
				
			}
		});
		btnClear.setBounds(139, 280, 119, 23);
		

		JButton btnNewEmployee = new JButton("New Employee");
		btnNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openNewEmployeeDialog();

			}
		});
		btnNewEmployee.setBounds(276, 247, 119, 23);
		add(btnNewEmployee);

		JButton btnDates = new JButton("Dates");
		btnDates.setBounds(139, 247, 119, 23);
		btnDates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openDateChooserDialog();
				btnClear.setVisible(true);
				add(btnClear);
				
			}
		});
		add(btnDates);

	}
	
	
	
	
	
	






	protected void openDateChooserDialog() {
		// TODO Auto-generated method stub
		JDialog dateChooser = new DateChooserDialog(owner);
		dateChooser.setModalityType(ModalityType.APPLICATION_MODAL);
		dateChooser.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dateChooser.setVisible(true);
		
		relistLogs(logID, fromToDates[0].toString(), fromToDates[1].toString());
		
	}












	public void openNewEmployeeDialog() {
		
		
		
		
		
		
					JDialog newEmployee = new newEmployeeDialog(owner, this, frame);
					newEmployee.setModalityType(ModalityType.APPLICATION_MODAL);
					newEmployee.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					newEmployee.setVisible(true);
					AdminSubPanel asp=(AdminSubPanel) new AdminSubPanel(frame).employeeList(this);
					subPanelChange(asp);
		
		
		
		
		
	}
	
	
	
	public void relistLogs(int id, String firstDate, String secondDate) {
		logID=id;
		if (contents!=null) {contents.removeAll();}
		JPanel asp=(AdminSubPanel) new AdminSubPanel(frame).employeeList(owner);
		System.out.println("Trying to refresh employee list...");
		System.out.println("ID: " + id + " firstDate: " + firstDate + " secondDate: " + secondDate);
		
		
		ArrayList<attendanceContainer> logList = new DbAttendance().pullTimeData(id, firstDate, secondDate);
		asp= new AdminSubPanel(frame).logList(this, logList);
		subPanelChange(asp);
		
		
		
	}
	
	
	
	
	
	public void relistEmployees(){

		if (contents!=null) {contents.removeAll();}
		JPanel asp=(AdminSubPanel) new AdminSubPanel(frame).employeeList(owner);
		System.out.println("Trying to refresh employee list...");
		subPanelChange(asp);
	}
	
	
	
	
	
	
	
	

	public void subPanelChange(JPanel c) {
		
		
		
		
		if (contents!=null) {contents.removeAll();}
		contents = c;
		
		
		
	//	contents.setBounds(0, 0, 514, 367);
		employeeContent.removeAll();
		employeeContent.revalidate();
		employeeContent.repaint();
		employeeContent.add(contents);
		setVisible(true);
	
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
