package com.lawranta.SubPanels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.lang.model.element.Element;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.lawranta.DatabaseModels.AttendanceModel;
import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.Globals.ConsoleColors;
import com.lawranta.Globals.Global;
import com.lawranta.Globals.SetGlobalFont;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.containersObjects.employeeContainer;
import com.lawranta.frames.PanelContainerFrame;
import com.lawranta.modifiers.*;
import com.lawranta.panels.AdminPanel;
import com.lawranta.panels.PinPanel;
import com.lawranta.popups.*;
import com.lawranta.services.AttendanceService;
import com.lawranta.services.EmployeeService;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.FlowLayout;
import java.io.File;

public class AdminSubPanel extends JPanel {
	public AdminSubPanel(PanelContainerFrame frame) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		SetGlobalFont.setUIFont(new javax.swing.plaf.FontUIResource(Global.timesFont12));
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5215442045267403248L;
	JTable table;
	EmployeeDAO cDb = new EmployeeDAO();
	private AdminPanel parentPanel;
	private PanelContainerFrame frame;
	private float totalHours = 0;
	/**
	 * Create the panel.
	 * 
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
			table.getColumnModel().getColumn(col).setHeaderRenderer(new ClickableHeaderRenderer(0));
			;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			int col = table.columnAtPoint(e.getPoint());
			table.getColumnModel().getColumn(col).setHeaderRenderer(new ClickableHeaderRenderer(1));
			;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	public JPanel logList(AdminPanel parentPanel, ArrayList<AttendanceModel> logList, PanelContainerFrame frame) {

		this.parentPanel = parentPanel;
		this.frame = frame;

		System.out.print("Retrieved: " + logList.size() + "\n");
		String[] headers = { "ID", "EmployeeID", "Employee", "Date", "Start Time", "End Time", "Subtotal" };
		Object[][] data = new Object[logList.size()][7];

		for (int i = 0; i < logList.size(); i++) {

			data[i][0] = logList.get(i).getPrimaryKey();
			data[i][1] = logList.get(i).getEmployeeID();
			data[i][2] = logList.get(i).getName().replace(", ", ", ");
			data[i][3] = logList.get(i).getDate();
			data[i][4] = logList.get(i).getStartTime();
			data[i][5] = logList.get(i).getEndTime();
			data[i][6] = logList.get(i).getSubTotal();

			int y = i + 1;

			// parse subtotals and calculate total
			parseSub(logList.get(i).getSubTotal());

			System.out.println("loaded " + y + " lines");

		}
		System.out.println("Final Total hours: " + totalHours);
		parentPanel.totalHours = totalHours;

		Object[][] sorted = (Arrays.copyOf(data, data.length));

		JScrollPane scroll = new JScrollPane();
		this.table = new JTable(sorted, headers) {
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
		// DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		// centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int x = 0; x < headers.length; x++) {
			// table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
			this.table.getColumnModel().getColumn(x).setHeaderRenderer(new ClickableHeaderRenderer(1));
		}
		;

		header = this.table.getTableHeader();
		// header.setBackground(Color.LIGHT_GRAY);
		header.addMouseListener(actionListener);

		// scroll.setBounds(0, 0, 100,100); // <-- THIS

		scroll.setViewportView(this.table);

		add(scroll);

		setVisible(true);

		// make clickable

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int x = 0; x < 7; x++) {
			this.table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		;

		table.getColumnModel().getColumn(0).setPreferredWidth(16);
		table.getColumnModel().getColumn(1).setPreferredWidth(16);

		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// System.out.println("double clicked");
					try {
						String cellData = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn())
								.toString();
						System.out.println(cellData);

						int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), (0)).toString());

						System.out.println(table.getValueAt(table.getSelectedRow(), (0)).toString());

						// launch edit dialog
						AttendanceModel byId = AttendanceService.getModelByID(id);

						EditRecordDialog editRecordDialog = new EditRecordDialog(byId, parentPanel);
						editRecordDialog.setVisible(true);
					} catch (Exception exc) {
						Global.showError("Cannot edit while employee is still clocked in. \n");

					}

				}
			}
		});

		return this;
	}

	private void parseSub(String subTotal) {
		// TODO Auto-generated method stub

		// calculate subTotal
		System.out.println("adding " + subTotal + " to Total (" + totalHours + ")");

		if (subTotal != null) {

			String[] hourMinSec = subTotal.split(":");
			float hour = Integer.parseInt(hourMinSec[0]);
			float mins = Integer.parseInt(hourMinSec[1]);
			float sec = Integer.parseInt(hourMinSec[2]);
			float minsInHours = mins / 60;
			float secsInHours = (sec / 60) / 60;
			float parsed = hour + minsInHours + secsInHours;

			System.out.println(subTotal + " parsed to " + parsed);

			totalHours += parsed;

		}

		System.out.println("Total hours: " + totalHours);

	}

	public JPanel employeeList(AdminPanel parentPanel) {

		this.parentPanel = parentPanel;

		ArrayList<EmployeeModel> employeeList = cDb.getEmployees();
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
		this.table = new JTable(sorted, headers) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7087360881226226551L;
			boolean[] canEdit = new boolean[] { false, true, true, false, false, true, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		};

		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int x = 0; x < 7; x++) {
			this.table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		;

		header = this.table.getTableHeader();
		header.setBackground(Color.LIGHT_GRAY);
		// scroll.setBounds(0, 0, 514, 367); // <-- THIS

		scroll.setViewportView(this.table);

		add(scroll);

		setVisible(true);

		table.getColumnModel().getColumn(0).setPreferredWidth(16);

		//clocked menu
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
		JPopupMenu clockStatusMenu = new JPopupMenu();
		JMenuItem m1=new JMenuItem();
		JMenuItem m2=new JMenuItem("Clock-out (" + dtf.format(LocalTime.now())+ ")");
		JMenuItem m3=new JMenuItem("Cancel");
		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data[table.getSelectedRow() ][4]="in";
			}
			
			
			
		});
		m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(ConsoleColors.BLUE + "Value at: " + table.getSelectedRow() + "," + table.getSelectedColumn() + " is " + table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString() + ConsoleColors.RESET);
				data[table.getSelectedRow() ][4]="out";
				
				int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String passedStart=data[table.getSelectedRow()][4].toString();
				
				int i =table.getSelectedRow();
				EmployeeModel em = new EmployeeModel();
				em.setAll(data[i][2].toString(), data[i][1].toString(), data[i][3].toString(), "out", data[i][5].toString());
				em.setID(Integer.parseInt(data[i][0].toString()));
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String endTime=LocalDateTime.now().format(dtf);
				
				try {
					AttendanceService.clockOut(em,passedStart, endTime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				EmployeeService.dbUpdateEmployeeStatus(id, "out");
		
				
				
				
			}
			
			
			
		});
		
		
		
		
		
		// Table Listener

		this.table.addMouseListener(new MouseAdapter() {
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
					case "in": {
						m1.setText("Clock Out (" + dtf.format(LocalTime.now()) + ")");
						clockStatusMenu.removeAll();
						clockStatusMenu.add(new JLabel("Change Clocked Status"));
						clockStatusMenu.add(m2);
						clockStatusMenu.add(m3);
						clockStatusMenu.setVisible(true);
clockStatusMenu.show(getParent(), e.getX(), e.getY());

				
						
						
						

					}

						break;

					case "out": {

					}
					m1.setText("Clock In (" + dtf.format(LocalTime.now()) + ")");
					clockStatusMenu.removeAll();
					clockStatusMenu.add(new JLabel("Change Clocked Status"));
					clockStatusMenu.add(m1);
					clockStatusMenu.add(m3);
					clockStatusMenu.setVisible(true);
					clockStatusMenu.show(getParent(), e.getX(), e.getY());
					
						break;

					}

				}
			}
		});

		return this;
	}

	private void delete(int id) {

		JDialog delete = new confirmDeleteDialog(id, parentPanel, frame);
		delete.setVisible(true);
		boolean returnB = ((confirmDeleteDialog) delete).resultB();

		if (returnB) {

			// parentPanel.employeeContent.removeAll();
			// parentPanel.employeeContent.setVisible(false);
			// AdminPanel test = (AdminPanel) SwingUtilities.getUnwrappedParent(this);
			// JPanel thisPanel = this;
			// parentPanel.relistEmployees();
			System.out.println("cancelled?");
			// setVisible(false);
			// removeAll();

		}

		// setVisible(false);
		// removeAll();
		// parentPanel.relistEmployees();

	}

	public boolean exportList(float totalHours2, String parsedTotal) {

		String defaultFileName = Global.today + "_" + parentPanel.timeLogFilter + ".csv";

		System.out.println("Trying to Export: " + table.toString());

		String absPath = "csv.csv";

		FilePathDialog path = new FilePathDialog();
		path.setSelectedFile(new File(defaultFileName));
		Integer opt = path.showSaveDialog(this);

		if (opt == JFileChooser.APPROVE_OPTION) {
			// get selected pathfile
			File f = path.getSelectedFile();
			absPath = f.getAbsolutePath();

			if (!f.getAbsolutePath().endsWith(".csv")) {

				absPath = f.getAbsolutePath() + ".csv";
			}

			System.out.print(absPath);

		}

//write to CSV

		try {

			TableModel model = this.table.getModel();
			FileWriter csv = new FileWriter(new File(absPath));

			for (int i = 0; i < model.getColumnCount(); i++) {
				csv.write(model.getColumnName(i) + ",");
			}

			csv.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {

					if (model.getValueAt(i, j) != null && model.getValueAt(i, j).toString().indexOf(',') < 0) {
						csv.write(model.getValueAt(i, j).toString() + ",");
					}

					else if (model.getValueAt(i, j) != null)

					{
						csv.write("\"" + model.getValueAt(i, j).toString() + "\" " + ",");
					}

				}
				csv.write("\n");
			}

			csv.write("\n\n,,,,,,,Total: ," + totalHours2 + "," + parsedTotal);

			csv.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			Global.showError(e.getMessage());
		}
		return false;

	}

}
