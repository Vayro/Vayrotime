package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {100, 100, 100, 100};
		gbl_contentPanel.rowHeights = new int[] {40, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			startTimeButton = new JButton("Start Time");
		}
		{
			infoField = new JTextField();
			infoField.setMinimumSize(new Dimension(50, 20));
			infoField.setPreferredSize(new Dimension(100, 20));
			infoField.setColumns(10);
		}
		GridBagConstraints gbc_infoField = new GridBagConstraints();
		gbc_infoField.gridwidth = 4;
		gbc_infoField.fill = GridBagConstraints.BOTH;
		gbc_infoField.gridx = 0;
		gbc_infoField.gridy = 0;
		contentPanel.add(infoField, gbc_infoField);
		{
			dateButton = new JButton("Edit Date");
		}
		GridBagConstraints gbc_dateButton = new GridBagConstraints();
		gbc_dateButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateButton.anchor = GridBagConstraints.WEST;
		gbc_dateButton.insets = new Insets(0, 0, 0, 5);
		gbc_dateButton.gridx = 0;
		gbc_dateButton.gridy = 1;
		contentPanel.add(dateButton, gbc_dateButton);
		GridBagConstraints gbc_startTimeButton = new GridBagConstraints();
		gbc_startTimeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_startTimeButton.anchor = GridBagConstraints.WEST;
		gbc_startTimeButton.insets = new Insets(0, 0, 0, 5);
		gbc_startTimeButton.gridx = 1;
		gbc_startTimeButton.gridy = 1;
		contentPanel.add(startTimeButton, gbc_startTimeButton);
		{
			deleteButton = new JButton("Delete Record");
		}
		{
			editEndTimeButton = new JButton("End Time");
		}
		GridBagConstraints gbc_editEndTimeButton = new GridBagConstraints();
		gbc_editEndTimeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editEndTimeButton.anchor = GridBagConstraints.WEST;
		gbc_editEndTimeButton.insets = new Insets(0, 0, 0, 5);
		gbc_editEndTimeButton.gridx = 2;
		gbc_editEndTimeButton.gridy = 1;
		contentPanel.add(editEndTimeButton, gbc_editEndTimeButton);
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteButton.anchor = GridBagConstraints.WEST;
		gbc_deleteButton.gridx = 3;
		gbc_deleteButton.gridy = 1;
		contentPanel.add(deleteButton, gbc_deleteButton);

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
