package com.lawranta.modifiers;

import java.awt.Component;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ClickableHeaderRenderer extends JLabel implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5341450862279706455L;

	public ClickableHeaderRenderer(int i) {
		// setFont(new Font("Consolas", Font.BOLD, 14));
	//	setBackground(Color.RED);
		switch (i) {
		case 0: {
			setBackground(Color.DARK_GRAY);
			break;
		}
		case 1: {
			setBackground(Color.LIGHT_GRAY);
			break;
		}

		}

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		return this;
	}

}
