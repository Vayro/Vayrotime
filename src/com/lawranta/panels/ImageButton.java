package com.lawranta.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.lawranta.Globals.Global;

public class ImageButton extends JButton {
	public ImageButton(Font font, String f, String f_pressed, String text) {

		setText(text);
		setIcon(new ImageIcon(AdminPinPanel.class.getResource(f)));
		setPressedIcon(new ImageIcon(AdminPinPanel.class.getResource(f_pressed)));
		setBackground(new Color(0, 0, 0));
		setForeground(Color.RED);
		setFont(Global.analogFont32f);
		setForeground(Color.RED);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setMinimumSize(new Dimension(16, 16));
		setPreferredSize(new Dimension(64, 64));
		setMaximumSize(new Dimension(64, 64));
		
	}

}
