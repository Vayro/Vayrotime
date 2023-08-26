package com.lawranta.SubPanels;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.table.*;

import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.Globals.Global;
import com.lawranta.Globals.SetGlobalFont;
import com.lawranta.DatabaseModels.AttendanceModel;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.services.AttendanceService;
import com.lawranta.sqllite.AttendanceDAO;
import com.lawranta.sqllite.EmployeeDAO;
import java.awt.BorderLayout;

public class currentSessionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8531913155956205564L;
	private JTable table;
	static AttendanceService aSer=new AttendanceService();
	/**
	 * Create the panel.
	 */
	public currentSessionPanel(EmployeeModel em, boolean sort) {
		setBackground(new Color(255, 217, 217));
		SetGlobalFont.setUIFont(new javax.swing.plaf.FontUIResource(Global.timesFont12));
		/*
		 * JTextPane timeListText = new JTextPane(); timeListText.setBackground(new
		 * Color(255, 217, 217)); timeListText.setText(""); timeListText.setBounds(0, 0,
		 * 514, 367); timeListText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new
		 * Color(0, 0, 0))); timeListText.setEditable(false); add(timeListText);
		 */

		// set text content
	
		
		ArrayList<AttendanceModel> timeInfoList = AttendanceService.pullTimeData(em.getId(),null,null);
		System.out.print("time info list contains:" + timeInfoList.size() + "\n");

		String[] headers = { "Date", "Clock-in", "Clock-Out", "Subtotal" };

		Object[][] data = new Object[timeInfoList.size()][4];

		for (int i = 0; i < timeInfoList.size(); i++) {

			data[i][0] = timeInfoList.get(i).getDate();
			data[i][1] = timeInfoList.get(i).getStartTime();
			data[i][2] = timeInfoList.get(i).getEndTime();
			data[i][3] = timeInfoList.get(i).getSubTotal();
			System.out.println("loaded " + i+1 + " lines");

		}
		
		Object[][] sorted = ( Arrays.copyOf(data, data.length));

		
		
	
		
		if(sort) {
			
			Arrays.sort(sorted, (a, b) -> a[0].toString().compareTo(b[0].toString()));
		}else
		{
			Arrays.sort(sorted, (a, b) -> b[0].toString().compareTo(a[0].toString()));
			
		}
		
		
		
		
		JScrollPane scroll = new JScrollPane();
		JTable table = new JTable(sorted, headers) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7087360881226226551L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};

		JTableHeader header = table.getTableHeader();
		setLayout(new BorderLayout(0, 0));

		scroll.setViewportView(table);

		add(scroll);

		setVisible(true);

	}
}
