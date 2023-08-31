package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.lawranta.Globals.Global;

import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ErrorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea errorTextField;
	String errorString="404!";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog dialog = new ErrorDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorDialog(String errorString) {
		setSize(new Dimension(340, 140));
		setPreferredSize(new Dimension(340, 140));
		this.errorString=errorString;
		setMaximumSize(new Dimension(340, 500));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(10, 20));
		contentPanel.setBorder(new TitledBorder(null, "Error!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		

		
		{
			
			
			JPanel internalPanel = new JPanel();
			contentPanel.add(internalPanel, BorderLayout.NORTH);
			internalPanel.setLayout(new BoxLayout(internalPanel, BoxLayout.X_AXIS));
			setModal(true);
			
			
			
			Image i = null;
			try {
				i = ImageIO.read(new File(Global.errorImgPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
			i=i.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
			
			 JLabel picLabel = new JLabel(new ImageIcon(i));
			 picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
			 internalPanel.add(picLabel);
			
			
			
			
			 JScrollPane scroll = new JScrollPane();
				scroll.createVerticalScrollBar();

			errorTextField = new JTextArea();
			//errorTextField.setPreferredSize(new Dimension(5, 44));
			errorTextField.setEditable(false);
		errorTextField.setWrapStyleWord(true);
			errorTextField.setLineWrap(true);
			errorTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		//	errorTextField.setHorizontalAlignment(SwingConstants.CENTER);
			
		errorTextField.setText(this.errorString);
			//.add(errorTextField);
			scroll.setViewportView(errorTextField);
			scroll.createVerticalScrollBar();
			//scroll.getViewport().setViewPosition( new Point(0, 0) );
			errorTextField.setSelectionStart(0);
			errorTextField.setSelectionEnd(0);
			internalPanel.add(scroll);
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cButton = new JButton("Confirm");
				cButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent t) {
						dispose();
						
					}
					
					
				});
				buttonPane.add(cButton);
				getRootPane().setDefaultButton(cButton);
			}

		}
	}

}
