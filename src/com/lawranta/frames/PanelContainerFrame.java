package com.lawranta.frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;
import javax.swing.border.EmptyBorder;

import com.lawranta.frames.*;
import com.lawranta.panels.PinPanel;


public class PanelContainerFrame extends JFrame {

	public JPanel contentPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelContainerFrame frame = new PanelContainerFrame();
			
		
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanelContainerFrame() {
		
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(400, 400, 550, 800);
		
		setLocationRelativeTo(null);
		
		
		

	
		
		
		contentPane=new JPanel();
		
		contentPane.setVisible(true);
		panel=new PinPanel(this);		

		PanelChange(panel);
		
	
		
		
	}
	
	public void PanelChange(JPanel panel) {
		
		
		panel.setVisible(true);
		contentPane.removeAll();
		contentPane.add(panel);
		setContentPane(contentPane);
	//	pack();
	}
	
	

}
