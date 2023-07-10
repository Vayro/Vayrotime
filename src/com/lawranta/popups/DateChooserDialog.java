package com.lawranta.popups;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;




public class DateChooserDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 490719954257157168L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
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
	 */
	public DateChooserDialog() {
	
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
		
		    
		    
		    
		    DatePicker fromDate = new DatePicker();
		    
		    		    
		    		    
		    		    
		    		GridBagConstraints gbc_fromDate = new GridBagConstraints();
		    		gbc_fromDate.fill = GridBagConstraints.BOTH;
		    		gbc_fromDate.insets = new Insets(0, 0, 5, 0);
		    		gbc_fromDate.gridx = 1;
		    		gbc_fromDate.gridy = 0;
		    		contentPanel.add(fromDate, gbc_fromDate);
		
		
		
		
		
		

		
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			JLabel lblTo = new JLabel("To");
			GridBagConstraints gbc_lblTo = new GridBagConstraints();
			gbc_lblTo.insets = new Insets(0, 0, 0, 5);
			gbc_lblTo.gridx = 0;
			gbc_lblTo.gridy = 1;
			contentPanel.add(lblTo, gbc_lblTo);
		}
		DatePicker toDate = new DatePicker();
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
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
}
