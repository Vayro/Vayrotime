package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.lawranta.Globals.Global;

import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class SuccessDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919144569437039368L;
	private final JPanel contentPanel = new JPanel();
	private JLabel errorTextField;
	String errorString="Success!";

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
		setSize(new Dimension(340, 140));
		setPreferredSize(new Dimension(340, 140));
		this.errorString=errorString;
		setMaximumSize(new Dimension(340, 500));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Sucess!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
	
		{
			JPanel internalPanel = new JPanel();
			contentPanel.add(internalPanel, BorderLayout.NORTH);
			internalPanel.setLayout(new BoxLayout(internalPanel, BoxLayout.X_AXIS));
			
			
			
			
			{
				
				
				
				
				
				Image i = null;
				try {
					i = ImageIO.read(new File(Global.succImgPath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
				i=i.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
				
				 JLabel picLabel = new JLabel(new ImageIcon(i));
				 picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
				 internalPanel.add(picLabel);
				
				
				
				
				
				
				
				
				errorTextField = new JLabel();
				errorTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
				errorTextField.setHorizontalAlignment(SwingConstants.CENTER);
				errorTextField.setText(this.errorString);
				internalPanel.add(errorTextField);
				
				
			}
			
			
			
			
			
			
			
			
			
			
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
