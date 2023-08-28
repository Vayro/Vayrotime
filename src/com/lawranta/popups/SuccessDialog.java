package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class SuccessDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel errorTextField;
	String errorString="404!";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SuccessDialog dialog = new SuccessDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SuccessDialog(String errorString) {
		setSize(new Dimension(240, 140));
		setPreferredSize(new Dimension(240, 140));
		this.errorString=errorString;
		setMaximumSize(new Dimension(240, 500));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Error!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			errorTextField = new JLabel();
			errorTextField.setHorizontalAlignment(SwingConstants.CENTER);
			errorTextField.setText(this.errorString);
			contentPanel.add(errorTextField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
					
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

		}
	}

}
