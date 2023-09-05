package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.lawranta.DatabaseModels.AttendanceModel;
import com.lawranta.Globals.ConsoleColors;
import com.lawranta.Globals.Global;
import com.lawranta.panels.AdminPanel;
import com.lawranta.services.AttendanceService;
import com.lawranta.services.daytimeDifferance;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class EditRecordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField infoField;
	private JButton dateButton;
	private JButton startTimeButton;
	private JButton editEndTimeButton;
	private JButton deleteButton;
	Object[][] data = new Object[1][7];
	JTable infoTable ;
	TimePicker endTimePicker;
	TimePicker startTimePicker;
	DatePicker startDatePicker;
	DatePicker endDatePicker;
	JLabel validateLabel;
	JButton okButton ;
	JComponent owner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditRecordDialog dialog = new EditRecordDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditRecordDialog(AttendanceModel aM, JComponent owner) {
		this.owner=owner;
		setMinimumSize(new Dimension(500, 500));
	setSize(new Dimension(480, 480));
	setPreferredSize(new Dimension(480, 480));
	setResizable(false);
	//	setMinimumSize(new Dimension(320, 240));
	//	setSize(new Dimension(500, 200));
	//	setPreferredSize(new Dimension(700, 200));
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setPreferredSize(new Dimension(480, 384));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setVisible(true);

		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			
			
			
			 TimePickerSettings timeSettings = new TimePickerSettings();
			    timeSettings.setFormatForDisplayTime(PickerUtilities.createFormatterFromPatternString(
			            "HH:mm:ss", timeSettings.getLocale()));
			    timeSettings.setFormatForMenuTimes(PickerUtilities.createFormatterFromPatternString(
			            "HH:mm", timeSettings.getLocale()));
			
			    
			  DatePickerSettings dateSettings = new DatePickerSettings();  
			  dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
			  DatePickerSettings dateSettings2 = new DatePickerSettings();  
			  dateSettings2.setFormatForDatesCommonEra("yyyy-MM-dd");
			
			
			String[] headers = { "ID", "EmployeeID", "Employee", "Date","Start Time", "End Time", "Subtotal" };

			data[0][0] = aM.getPrimaryKey();
			data[0][1] = aM.getEmployeeID();
			data[0][2] = aM.getName().replace(", ",", ");
			data[0][3] = aM.getDate();
			data[0][4] = aM.getStartTime();
			data[0][5] = aM.getEndTime();
			data[0][6] = aM.getSubTotal();
			
			
			
			

			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] {80, 80, 80, 80, 80, 80};
			gbl_contentPanel.rowHeights = new int[] {100, 200, 64, 64, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
			contentPanel.setLayout(gbl_contentPanel);
			
			
			
			
			
			infoTable = new JTable(data,headers){
			
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] canEdit = new boolean[]{
	                    false, false, false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	            
	            
	         /*   @Override
	            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
	                Component comp = super.prepareRenderer(renderer, row, col);
	       
	                
	             /*   old code
	              * 
	              * if (col<=3 ) {
	                    comp.setBackground(Color.LIGHT_GRAY);
	                
	                } else {
	                   comp.setBackground(Color.white);
	                }
	                return comp;
	            }
	            */
	            
	            
	            
	};
			infoTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	
				
				
				
				JScrollPane scrollPane = new JScrollPane(infoTable);
				scrollPane.setMinimumSize(new Dimension(23, 64));
				scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 6;
				gbc_scrollPane.anchor = GridBagConstraints.NORTH;
				gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				contentPanel.add(scrollPane, gbc_scrollPane);		
				{
					JPanel startTimePanel = new JPanel();
	
					startTimePanel.setBorder(new TitledBorder(null, "Start Time", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					GridBagConstraints gbc_startTimePanel = new GridBagConstraints();
					gbc_startTimePanel.insets = new Insets(0, 0, 0, 0);
					gbc_startTimePanel.gridwidth = 3;
					gbc_startTimePanel.anchor = GridBagConstraints.WEST;
					gbc_startTimePanel.fill = GridBagConstraints.BOTH;
					gbc_startTimePanel.gridx = 0;
					gbc_startTimePanel.gridy = 1;
					contentPanel.add(startTimePanel, gbc_startTimePanel);
					
					
					
					    startTimePicker = new TimePicker(timeSettings);
					   startTimePicker.setMaximumSize(new Dimension(200, 32));
					    startTimePicker.setSize(new Dimension(228, 64));
					    startTimePicker.setText(aM.getStartTime().split(" ")[1]);
					startTimePanel.setLayout(new BoxLayout(startTimePanel, BoxLayout.Y_AXIS));
			
					startDatePicker = new DatePicker(dateSettings2);
					startDatePicker.setText(aM.getStartTime().split(" ")[0]);
					startDatePicker.setMaximumSize(new Dimension(200, 32));
					startTimePanel.add(new JLabel("Clock-in Date"));
					startTimePanel.add(startDatePicker);
					startTimePanel.add(new JLabel("24 hr time:"));
					startTimePanel.add(startTimePicker);
					
					
					
					
					
				}
			
			{
				JPanel endTimePanel = new JPanel();

				endTimePanel.setBorder(new TitledBorder(null, "End Time", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_endTimePanel = new GridBagConstraints();
				gbc_endTimePanel.anchor = GridBagConstraints.EAST;
				gbc_endTimePanel.insets = new Insets(0, 0, 0, 0);
				gbc_endTimePanel.gridwidth = 3;
				gbc_endTimePanel.fill = GridBagConstraints.BOTH;
				gbc_endTimePanel.gridx = 3;
				gbc_endTimePanel.gridy = 1;
				contentPanel.add(endTimePanel, gbc_endTimePanel);
				
				
				endTimePicker = new TimePicker(timeSettings);
				endTimePicker.setMaximumSize(new Dimension(200, 32));
				endTimePicker.setSize(new Dimension(228, 64));
				endTimePicker.setText(aM.getEndTime().split(" ")[1]);
				endTimePanel.setLayout(new BoxLayout(endTimePanel, BoxLayout.Y_AXIS));
		
			
				endDatePicker = new DatePicker(dateSettings);
				endDatePicker.setText(aM.getEndTime().split(" ")[0]);
				endDatePicker.setMaximumSize(new Dimension(200, 32));
	
				endTimePanel.add(new JLabel("Clock-out Date"));
				endTimePanel.add(endDatePicker);
				endTimePanel.add(new JLabel("24 hr time:"));
			endTimePanel.add(endTimePicker);
			
				
				
				
				
			}
		}
		{
			JLabel infoLabel = new JLabel("*Time MUST be in YYYY-MM-SS HH:MM:SS format");
			infoLabel.setMinimumSize(new Dimension(235, 32));
			infoLabel.setMaximumSize(new Dimension(235, 140));
			infoLabel.setPreferredSize(new Dimension(480, 32));
			GridBagConstraints gbc_infoLabel = new GridBagConstraints();
			gbc_infoLabel.insets = new Insets(0, 0, 5, 0);
			gbc_infoLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_infoLabel.gridwidth = 6;
			gbc_infoLabel.gridx = 0;
			gbc_infoLabel.gridy = 2;
			contentPanel.add(infoLabel, gbc_infoLabel);
			
			validateLabel = new JLabel("must be validated before confirming");
			validateLabel.setBackground(new Color(51, 51, 51));
			validateLabel.setForeground(new Color(255, 0, 204));
			infoLabel.setMinimumSize(new Dimension(235, 32));
			infoLabel.setMaximumSize(new Dimension(235, 140));
			infoLabel.setPreferredSize(new Dimension(480, 32));
			GridBagConstraints gbc_validateLabel = new GridBagConstraints();
			gbc_validateLabel.anchor = GridBagConstraints.NORTH;
			gbc_validateLabel.insets = new Insets(0, 0, 5, 0);
			gbc_validateLabel.gridwidth = 6;
			gbc_validateLabel.gridx = 0;
			gbc_validateLabel.gridy = 3;
			contentPanel.add(validateLabel, gbc_validateLabel);
		}
		
		
	

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnValidateButton = new JButton("Calculate Subtotal");
				btnValidateButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						validCheck();
						
					}});
				
				buttonPane.add(btnValidateButton);
			}
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						commitChanges(validCheck(),aM);
						
						
					}
					
					
					
					
					
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	String validCheck(){
		
		String subTotal = validateLabel.getText() ;
		try {
		 subTotal = daytimeDifferance.calculate(startDatePicker.getText()+" "+startTimePicker.getText(),endDatePicker.getText()+" "+endTimePicker.getText());
		
		if(subTotal.startsWith("-")) {
			
			throw new Exception("Negative time: " + subTotal );
		}
		
		
		okButton.setEnabled(true);
		
		
		}
		catch(Exception e){
			
			Global.showError(e.getMessage());
		}
		
		
		
		validateLabel.setText("SubTotal: " + subTotal);
	    infoTable.getSelectionModel().clearSelection();
		
		
		/*	
		for(int i=0;i<7;i++) {
		System.out.println(data[0][i]);
		}
		
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(data[0][4].toString(),dtf);
		LocalDateTime endTime = LocalDateTime.parse(data[0][5].toString(),dtf);
		
		System.out.println("Parsed " + startTime + "and" + endTime);*/
		
		
		
		return subTotal;
		
		
	}
	
	
	
	
	void commitChanges(String subTotal, AttendanceModel am){
		am.setSubTotal(subTotal);
		am.setStartTime(startDatePicker.getText()+" "+startTimePicker.getText());
		am.setEndTime(endDatePicker.getText()+" "+endTimePicker.getText());
		System.out.println(ConsoleColors.YELLOW + am.toString() + ConsoleColors.RESET);
		AttendanceService.updateByModel(am);
		((AdminPanel) owner).relistLogs(0, null, null);
		dispose();

		
		
	}
	
	
	
	
	//validate try to format and validate 
	
	
	
	

}
