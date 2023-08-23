package com.lawranta.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class testPanel extends JPanel {
	private JTextField txtNigger;

	/**
	 * Create the panel.
	 */
	public testPanel() {
		
		txtNigger = new JTextField();
		txtNigger.setText("nigger");
		add(txtNigger);
		txtNigger.setColumns(10);
				
	}

}
