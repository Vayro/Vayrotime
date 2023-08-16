package com.lawranta.SubPanels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.lang.model.element.Element;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.containersObjects.employeeContainer;
import com.lawranta.frames.PanelContainerFrame;
import com.lawranta.modifiers.*;
import com.lawranta.panels.AdminPanel;
import com.lawranta.panels.PinPanel;
import com.lawranta.popups.*;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.FlowLayout;

public class AdminSubPanel extends JPanel {
	public AdminSubPanel(PanelContainerFrame frame) {
		super();
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5215442045267403248L;
	JTable table;
	EmployeeDAO cDb = new EmployeeDAO();
	private AdminPanel parentPanel;

	/**
	 * Create the panel.
	 * @param logList 
	 */

	JTableHeader header;
	MouseListener actionListener = new MouseListener() {
		public void mouseClicked(MouseEvent event) {
			int col = table.columnAtPoint(event.getPoint());
	        String name = table.getColumnName(col);
	        System.out.println("Column index selected " + col + " " + name);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int col = table.columnAtPoint(e.getPoint());
table.getColumnModel().getColumn(col).setHeaderRenderer(new ClickableHeaderRenderer(0));;
		}
			

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		
			int col = table.columnAtPoint(e.getPoint());
table.getColumnModel().getColumn(col).setHeaderRenderer(new ClickableHeaderRenderer(1));;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

			
		}};
	
	
	public JPanel logList(AdminPanel parentPanel, ArrayList<attendanceContainer> logList) {
		

		
		this.parentPanel = parentPanel;
		
		System.out.print("Retrieved: " + logList.size() + "\n");
		String[] headers = { "ID", "EmployeeID", "Employee", "Date","Start Time", "End Time", "Subtotal" };
		Object[][] data = new Object[logList.size()][7];
		
		
		
		for (int i = 0; i < logList.size(); i++) {

			data[i][0] = logList.get(i).getPrimaryKey();
			data[i][1] = logList.get(i).getEmployeeID();
			data[i][2] = logList.get(i).getName();
			data[i][3] = logList.get(i).getDate();
			data[i][4] = logList.get(i).getStartTime();
			data[i][5] = logList.get(i).getEndTime();
			data[i][6] = logList.get(i).getSubTotal();

			int y = i + 1;

			System.out.println("loaded " + y + " lines");

		}
		
		
		Object[][] sorted = (Arrays.copyOf(data, data.length));

		JScrollPane scroll = new JScrollPane();
		table = new JTable(sorted, headers) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5288212106116261772L;
	

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};
		//DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	//	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int x = 0; x < headers.length; x++) {
			//table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(x).setHeaderRenderer(new ClickableHeaderRenderer(1));
		}
		;

		header = table.getTableHeader();
		//header.setBackground(Color.LIGHT_GRAY);
		header.addMouseListener( actionListener);

		//scroll.setBounds(0, 0, 100,100); // <-- THIS

		scroll.setViewportView(table);

		add(scroll);

		setVisible(true);
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public JPanel employeeList(AdminPanel parentPanel) {

		this.parentPanel = parentPanel;

		ArrayList<employeeContainer> employeeList = cDb.getEmployees();
		System.out.print("Employees:" + employeeList.size() + "\n");
		String[] headers = { "ID", "Last Name", "First Name", "Workgroup", "Status", "PinCode", "Delete" };
		Object[][] data = new Object[employeeList.size()][7];

		for (int i = 0; i < employeeList.size(); i++) {

			data[i][0] = employeeList.get(i).getID();
			data[i][1] = employeeList.get(i).getLastName();
			data[i][2] = employeeList.get(i).getFirstName();
			data[i][3] = employeeList.get(i).getWorkGroup();
			data[i][4] = employeeList.get(i).getClockedStatus();
			data[i][5] = employeeList.get(i).getPincode();
			data[i][6] = "X";
			int y = i + 1;

			System.out.println("loaded " + y + " lines");

		}

		Object[][] sorted = (Arrays.copyOf(data, data.length));

		JScrollPane scroll = new JScrollPane();
		table = new JTable(sorted, headers) {
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
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int x = 0; x < 7; x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		;

		header = table.getTableHeader();
		header.setBackground(Color.LIGHT_GRAY);
		//scroll.setBounds(0, 0, 514, 367); // <-- THIS

		scroll.setViewportView(table);

		add(scroll);

		setVisible(true);

		// Table Listener

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// System.out.println("double clicked");
					String cellData = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
					System.out.println(cellData);
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), (0)).toString());

					switch (cellData) {
					case "X": {
						delete(id);
					}

						break;
					case "": {

					}
						break;

					}

				}
			}
		});

		return this;
	}

	private void delete(int id) {

		JDialog delete = new confirmDeleteDialog(id);
		delete.setVisible(true);
		boolean returnB = ((confirmDeleteDialog) delete).resultB();

		if (returnB) {

			
			parentPanel.employeeContent.removeAll();
			parentPanel.employeeContent.setVisible(false);
			// AdminPanel test = (AdminPanel) SwingUtilities.getUnwrappedParent(this);
			// JPanel thisPanel = this;
		//	((AdminPanel) parentPanel).relistEmployees();
			setVisible(false);
			removeAll();


		}
		
		
		setVisible(false);
		removeAll();
		parentPanel.relistEmployees();
		
	}
	
	
	
	
	
	
	
	
	private void action(String str) {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
