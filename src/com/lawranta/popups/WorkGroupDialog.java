package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;

public class WorkGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTest;
	JTable table;
	Object[][] data;
	String[] headers= { "ID", "Class Name", "Location" };
	JScrollPane scroll ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			WorkGroupDialog dialog = new WorkGroupDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			// dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WorkGroupDialog() {
		//super(SwingUtilities.getWindowAncestor(owner), "Custom Dialog", Dialog.ModalityType.APPLICATION_MODAL);
       // this.adminPanel = adminPanel;
       // this.frame = frame;
		setTitle("Add New Employee");
		setPreferredSize(new Dimension(500, 125));
		setMinimumSize(new Dimension(500, 500));
		// setBounds(100, 100, 450, 150);
		setModal(true);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Manage Workgroups", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setMinimumSize(new Dimension(100, 200));
		contentPanel.setLayout(new GridLayout(1, 1, 0, 0));
		boolean sort = true;
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{


			
			
			
			
			
			ArrayList<WorkGroupModel> groupList = WorkGroupService.pullGroups();
			scroll = new JScrollPane();
			//scroll.setViewportBorder(new TitledBorder(null, "Workgroups", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			data = new Object[groupList.size()][3];
			
			
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
			
	
			
			table = new JTable(sorted, headers) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 7087360881226226551L;

				boolean[] canEdit = new boolean[] { false, true, true};

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			table.setPreferredScrollableViewportSize(new Dimension(450, 200));
			
			
			
			JTableHeader header = table.getTableHeader();
			table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
			scroll.setViewportView(table);

			
			
			
			table.getColumnModel().getColumn(0).setPreferredWidth(16);
		      DefaultTableCellRenderer   cellRenderer = new DefaultTableCellRenderer();
		      cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		      table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
	

		      
		      
			
		      
			
		      scroll.setVisible(true);
		      contentPanel.add(scroll);

		      
	
		    	  
		    	  
		    	  
		    	  
					JPanel addPane = new JPanel();
					addPane.setLayout(new FlowLayout(FlowLayout.CENTER));
	getContentPane().add(addPane, BorderLayout.CENTER);{  
		    	  
		  	JButton addButton= new JButton("Add New Group");
			addButton.setActionCommand("add");
			addPane.add(addButton);
			addButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					
					Object[][] newLineData=new Object[data.length+1][3];
					
				 for(int i=0;i<data.length;i++) {
					 

					 for(int y=0;y<3;y++) {
						 
						 newLineData[i][y]=data[i][y];
						 
					 }
					 
					 
					 
				 }
					
					
					data=newLineData;
					
					
					relistWorkgroups();
					
					
							
					
					
				
				}
				
				
				
				
			});
		      
			

			
			
	}
		}
		setResizable(true);
		//scroll
		

		
		
		
		
		
		
		
		
		
		
		
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
			{
				JButton okButton = new JButton("Save");
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
	
	
	
	
	void relistWorkgroups(){
		
		table = new JTable(data, headers)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 7087360881226226551L;

			boolean[] canEdit = new boolean[] { false, true, true};
			 
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		//scroll.removeAll();
		table.setPreferredScrollableViewportSize(new Dimension(450, 200));
		table.getColumnModel().getColumn(0).setPreferredWidth(16);
	      DefaultTableCellRenderer   cellRenderer = new DefaultTableCellRenderer();
	      cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	      table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		scroll.setViewportView(table);
		
		
	}
	
	

}
