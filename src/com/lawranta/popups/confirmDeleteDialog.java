package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.SubPanels.AdminSubPanel;
import com.lawranta.containersObjects.employeeContainer;
import com.lawranta.services.AttendanceService;
import com.lawranta.services.EmployeeService;
import com.lawranta.sqllite.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class confirmDeleteDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7009067707150189635L;
	private final JPanel contentPanel = new JPanel();
	EmployeeModel e = new EmployeeModel();
	boolean confirm = false;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

	
	}
	

	
	public confirmDeleteDialog(int id) {

		setTitle("Delete Employee");
		setBounds(100, 100, 336, 284);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.rowHeights = new int[] {0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JTextArea textArea = new JTextArea();
			textArea.setWrapStyleWord(true);
			textArea.setOpaque(false);
			textArea.setPreferredSize(new Dimension(300, 100));
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			//textArea.setPreferredSize(100, 100);
		
			textArea.setText("The folloing employee and employee's attendance logs will be deleted. This cannot be undone.");
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridwidth = 0;
			gbc_textArea.gridheight = 0;
			gbc_textArea.insets = new Insets(0, 0, 0, 5);
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 0;
			contentPanel.add(textArea, gbc_textArea);
			
			JLabel lblName = new JLabel("New label");
			lblName.setText("First Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 2;
			contentPanel.add(lblName, gbc_lblName);
			String employee;

			e=EmployeeService.setEmployeeByID(id);
			employee=e.getName();
			lblName.setText(employee);
			
		}
		
			
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Delete");
				okButton.setActionCommand("Delete");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(actionListener);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(actionListener);
			}
		}
	
		
		
		

		
		
		
		
	
	pack();
	}
	
	
	
	
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println("Clicked = " + str);
			act(str);
		}
	};
	
	
	

	public void act(String action){
	
		switch (action) {
		case "Delete": {
	
			EmployeeService.deleteEmployee(e);
			AttendanceService.deleteLogs(e);
			
			

			
			confirm=true;
			
			dispose();
			
			
			
		}
			break;

		case "Cancel": {
			confirm=false;
		
			dispose();

			break;
		}
		}
		
		
		
		
	}
	
	public Boolean resultB() {

	    return confirm;
	}

}
