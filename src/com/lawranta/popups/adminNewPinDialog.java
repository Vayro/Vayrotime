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

import com.lawranta.Globals.Global;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class adminNewPinDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField pinField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			adminNewPinDialog dialog = new adminNewPinDialog();
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
	 */
	public adminNewPinDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setMinimumSize(new Dimension(10, 32));
		contentPanel.setPreferredSize(new Dimension(10, 32));
		contentPanel.setMaximumSize(new Dimension(240, 64));
		contentPanel.setSize(new Dimension(240, 240));
		contentPanel.setBorder(new TitledBorder(null, "Enter new Pin.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
	
		
		{
			JLabel parameterLabel = new JLabel("Must be 6 numerical digits.");
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
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
