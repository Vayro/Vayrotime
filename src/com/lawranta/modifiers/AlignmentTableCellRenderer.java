package com.lawranta.modifiers;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AlignmentTableCellRenderer implements TableCellRenderer {
	 
	  private final TableCellRenderer wrappedRenderer;
	  private final JLabel label;
	 
	  public AlignmentTableCellRenderer(TableCellRenderer wrappedRenderer) {
	    if (!(wrappedRenderer instanceof JLabel)) {
	      throw new IllegalArgumentException("The supplied renderer must inherit from JLabel");
	    }
	    this.wrappedRenderer = wrappedRenderer;
	    this.label = (JLabel) wrappedRenderer;
	  }
	 
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value,
	          boolean isSelected, boolean hasFocus, int row, int column) {
	    wrappedRenderer.getTableCellRendererComponent(table, value,
	            isSelected, hasFocus, row, column);
	    label.setHorizontalAlignment(column % 2 == 0 ? JLabel.LEFT : JLabel.RIGHT);
	    return label;
	  }
	}