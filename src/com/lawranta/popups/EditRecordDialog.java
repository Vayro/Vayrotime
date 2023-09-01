package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.lawranta.DatabaseModels.AttendanceModel;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class EditRecordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField infoField;
	private JButton dateButton;
	private JButton startTimeButton;
	private JButton editEndTimeButton;
	private JButton deleteButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditRecordDialog dialog = new EditRecordDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditRecordDialog(AttendanceModel aM) {
		setPreferredSize(new Dimension(400, 200));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			
			
	
			
			
			
			String[] headers = { "ID", "EmployeeID", "Employee", "Date","Start Time", "End Time", "Subtotal" };
			Object[][] data = new Object[1][7];
			data[0][0] = aM.getPrimaryKey();
			data[0][1] = aM.getEmployeeID();
			data[0][2] = aM.getName().replace(", ",", ");
			data[0][3] = aM.getDate();
			data[0][4] = aM.getStartTime();
			data[0][5] = aM.getEndTime();
			data[0][6] = aM.getSubTotal();
			contentPanel.setLayout(new BorderLayout(0, 0));
			
			JTable infoTable = new JTable(data,headers){

	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] canEdit = new boolean[]{
	                    false, false, false, false, true,true,true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	            
	            
	            @Override
	            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
	                Component comp = super.prepareRenderer(renderer, row, col);
	     
	                
	                if (col<=3 ) {
	                    comp.setBackground(Color.LIGHT_GRAY);
	                
	                } else {
	                   comp.setBackground(Color.white);
	                }
	                return comp;
	            }
	            
	            
	            
	            
	};
			
			
			
			
			
			JScrollPane scrollPane = new JScrollPane(infoTable);
			contentPanel.add(scrollPane);
		}
		
		
	

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Calculate Subtotal");
				buttonPane.add(btnNewButton);
			}
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
