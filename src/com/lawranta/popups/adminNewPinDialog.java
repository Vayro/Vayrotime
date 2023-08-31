package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.lawranta.Globals.Global;
import com.lawranta.panels.AdminPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class adminNewPinDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField pinField;
	String pin;
	AdminPanel owner;
	
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		
		
		
		
		try {
			adminNewPinDialog dialog = new adminNewPinDialog(owner, args);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			dialog.setSize(new Dimension(240,180));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	/**
	 * Create the dialog.
	 * @param s 
	 * @return 
	 */
	public adminNewPinDialog(AdminPanel adminPanel, String[] s) {
		this.owner = adminPanel;

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(new Dimension(240,180));
		setLocationRelativeTo(null);
		
		
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setMinimumSize(new Dimension(10, 32));
		contentPanel.setPreferredSize(new Dimension(10, 32));
		contentPanel.setMaximumSize(new Dimension(240, 64));
		contentPanel.setSize(new Dimension(240, 240));
		contentPanel.setBorder(new TitledBorder(null, s[0], TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
	
		
		{
			JLabel parameterLabel = new JLabel(s[1]);
			parameterLabel.setHorizontalAlignment(SwingConstants.CENTER);
			parameterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			contentPanel.add(parameterLabel, BorderLayout.NORTH);
		}
		
		
		
		{
			contentPanel.add(Global.padding(32));
			pinField = new JTextField();
			pinField.setMaximumSize(new Dimension(2147483647, 20));
			pinField.setHorizontalAlignment(SwingConstants.CENTER);
			pinField.setSize(new Dimension(7, 20));
			
			
			
			PlainDocument document = (PlainDocument) pinField.getDocument();
			document.setDocumentFilter(new DocumentFilter() {

				@Override
				public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
						throws BadLocationException {
					String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

					if (string.length() <= 6) {
						super.replace(fb, offset, length, text, attrs); // To change body of generated methods, choose Tools
																		// | Templates.
					}
					if (string.length() == 6) {

						// This is where we want to check if the PIN entered is valid. It should occur
						// as soon as 6 digits are entered.
						// if pin is valid, go to next frame
	
					

					}

				}

			});
			
			
			
			
			
			
			
			
			contentPanel.add(pinField, BorderLayout.CENTER);
			pinField.setColumns(10);
		}
		
		
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
			{
				JButton okButton = new JButton("Confirm");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						pin=pinField.getText();
						System.out.println("Entered PIN: " + pin);
						verify();
						
					}}
				
						
						
						);
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
					
				});
				
				buttonPane.add(cancelButton);
			}
		}
		
		
	}

	protected boolean verify() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 6; i++) {
			 
            // Check if character is
            // not a digit between 0-9
            // then return false
            if (pin.charAt(i) < '0'
                || pin.charAt(i) > '9') {
            	System.out.println("illegal PIN: " + pin);
                return false;
            }
        }
          // If we reach here, that means
          // all characters were digits.
		dispose();
        return true;
    
		
		
		
	}
	
	
	public String getPin() {
		return pin;
	}
	
	

}
