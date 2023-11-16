package com.lawranta.popups;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.lawranta.Globals.Global;
import com.lawranta.SubPanels.AdminSubPanel;
import com.lawranta.containersObjects.employeeContainer;
import com.lawranta.panels.AdminPanel;
import com.lawranta.sqllite.EmployeeDAO;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JLabel;




public class DateChooserDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 490719954257157168L;
	private final JPanel contentPanel = new JPanel();
	private AdminPanel owner;
	 DatePicker fromDate;
	 DatePicker toDate;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			DateChooserDialog dialog = new DateChooserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param owner 
	 * @param owner 
	 */
	public DateChooserDialog(AdminPanel owner) {
	this.owner = owner;
		setBounds(0, 0, 350, 150);
		setPreferredSize(new Dimension(350, 150));
		setMinimumSize(new Dimension(350, 150));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		

	  
		
		
		 TimePickerSettings timeSettings = new TimePickerSettings();
		    timeSettings.setFormatForDisplayTime(PickerUtilities.createFormatterFromPatternString(
		            "HH:mm:ss.SSS", timeSettings.getLocale()));
		    timeSettings.setFormatForMenuTimes(PickerUtilities.createFormatterFromPatternString(
		            "HH:mm", timeSettings.getLocale()));
		    TimePicker timePicker = new TimePicker(timeSettings);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {100, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		    {
		    	JLabel lblFrom = new JLabel("From");
		    	GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		    	gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		    	gbc_lblFrom.gridx = 0;
		    	gbc_lblFrom.gridy = 0;
		    	contentPanel.add(lblFrom, gbc_lblFrom);
		    }
		
		    
		    
		    
		    fromDate = new DatePicker();
		    
		    		    
		    		    
		    		    
		    		GridBagConstraints gbc_fromDate = new GridBagConstraints();
		    		gbc_fromDate.fill = GridBagConstraints.BOTH;
		    		gbc_fromDate.insets = new Insets(0, 0, 5, 0);
		    		gbc_fromDate.gridx = 1;
		    		gbc_fromDate.gridy = 0;
		    		contentPanel.add(fromDate, gbc_fromDate);
		
		
		
		
		
		    		ActionListener actionListener = new ActionListener() {
		    			public void actionPerformed(ActionEvent event) {
		    				String str = event.getActionCommand();
		    				System.out.println("Clicked = " + str);
		    				action(str);
		    			}
		    		};

		
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			JLabel lblTo = new JLabel("To");
			GridBagConstraints gbc_lblTo = new GridBagConstraints();
			gbc_lblTo.insets = new Insets(0, 0, 0, 5);
			gbc_lblTo.gridx = 0;
			gbc_lblTo.gridy = 1;
			contentPanel.add(lblTo, gbc_lblTo);
		}
		toDate = new DatePicker();
		
		
		GridBagConstraints gbc_toDate = new GridBagConstraints();
		gbc_toDate.fill = GridBagConstraints.BOTH;
		gbc_toDate.gridx = 1;
		gbc_toDate.gridy = 1;
		contentPanel.add(toDate, gbc_toDate);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(actionListener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(actionListener);
				buttonPane.add(cancelButton);
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}	
	
	
	
	private void action(String str) {
	

		
		
		
		
		
		if(str.equals("OK")) {
			boolean b = validCheck();
			if (b == false) {
				System.out.println("dates invalid");
				Global.showError("Invalid dates!");
			} else {

				System.out.println("dates valid");
				
				dispose();
			}
			
		}
		else
		{
			dispose();

			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
	}
	
	private boolean validCheck() {
	
		String fd = fromDate.getText();
		String td = toDate.getText();
		
		LocalDate validFromDate = null;
		LocalDate validToDate = null;
		
		if(!fd.isEmpty() && !td.isEmpty() ){		
			
		
			
		    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MMMM d, u", Locale.ENGLISH);
     
			validFromDate = LocalDate.parse((CharSequence) fd, sdf);

			 validToDate = LocalDate.parse((CharSequence) td, sdf);
	
			
			//check if date 2 is larger than date 1
			if(validFromDate.compareTo(validToDate) < 0) {
		         System.out.println("Date 1 occurs before Date 2");
		         System.out.println("Date 1: " + validFromDate + " Date 2: " + validToDate);
		         //return true and send valid dates over
		 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		 		LocalDate vfD = validFromDate;
		 		LocalDate vtD = validToDate;
		         
		         owner.fromToDates[0]=dtf.format(vfD);
		         owner.fromToDates[1]=dtf.format(vtD);
		         return true;
			}
			
			else
			{
				
				return false;
			}
        	

		
		
			
		
		}
		
		
		
		
		
		
		
		
		
		
		
	return false;	
	}
	
	
	
	
	
	


}
