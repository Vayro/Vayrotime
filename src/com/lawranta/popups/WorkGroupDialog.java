package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import com.lawranta.DatabaseModels.WorkGroupModel;
import com.lawranta.DatabaseModels.AttendanceModel;
import com.lawranta.services.AttendanceService;
import com.lawranta.services.WorkGroupService;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class WorkGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		try {
			WorkGroupDialog dialog = new WorkGroupDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WorkGroupDialog() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		boolean sort = false;
		setModal(true);
		contentPanel.setVisible(true);
		
		//scroll
		{
			ArrayList<WorkGroupModel> groupList = WorkGroupService.pullGroups();
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportBorder(new TitledBorder(null, "Workgroups", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			String[] headers = { "ID", "Class Name", "Location" };
			Object[][] data = new Object[groupList.size()][3];
			
			scroll.setVisible(true);
			
			
			for (int i = 0; i < groupList.size(); i++) {

				data[i][0] = groupList.get(i).getId();
				data[i][1] = groupList.get(i).getGroupName();
				data[i][2] = groupList.get(i).getLocation();
				System.out.println("loaded " + i+1 + " lines");

			}
			
			
			
			
			
			
			
			
			
			
			Object[][] sorted = ( Arrays.copyOf(data, data.length));
			
			if(sort) {
				
				Arrays.sort(sorted, (a, b) -> a[0].toString().compareTo(b[0].toString()));
			}else
			{
				Arrays.sort(sorted, (a, b) -> b[0].toString().compareTo(a[0].toString()));
				
			}
			
	
			
			JTable table = new JTable(sorted, headers) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 7087360881226226551L;

				boolean[] canEdit = new boolean[] { false, true, true};

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			
			
			
			JTableHeader header = table.getTableHeader();
			setLayout(new BorderLayout(0, 0));
			table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
			scroll.setViewportView(table);
			
			setVisible(true);
			
			
			table.getColumnModel().getColumn(0).setPreferredWidth(16);
		      DefaultTableCellRenderer   cellRenderer = new DefaultTableCellRenderer();
		      cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		      table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		      contentPanel.setLayout(new GridLayout(1, 1, 0, 0));

			
			
		      contentPanel.add(scroll);

			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
