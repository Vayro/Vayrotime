package com.lawranta.popups;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellEditor;
import org.xml.sax.InputSource;

import com.lawranta.SubPanels.AdminSubPanel;
import com.lawranta.containersObjects.*;
import com.lawranta.frames.PanelContainerFrame;
import com.lawranta.panels.AdminPanel;
import com.lawranta.sqllite.EmployeeDAO;

import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import java.awt.Dimension;

public class newEmployeeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private AdminPanel adminPanel;
	public PanelContainerFrame frame;

	/**
	 * Launch the application.
	
	/**
	 * Create the dialog.
	 */
	public newEmployeeDialog(Component owner, AdminPanel adminPanel,PanelContainerFrame frame) {
		super(SwingUtilities.getWindowAncestor(owner), "Custom Dialog", Dialog.ModalityType.APPLICATION_MODAL);
        this.adminPanel = adminPanel;
        this.frame = frame;
		setTitle("Add New Employee");
		setPreferredSize(new Dimension(500, 125));
		setMinimumSize(new Dimension(500, 100));
		// setBounds(100, 100, 450, 150);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setMinimumSize(new Dimension(0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(true);
	
		String[] headers = { "Last Name", "First Name", "Workgroup", "PinCode" };
		Object[][] data = new Object[1][4];
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table = new JTable(data, headers);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		JTableHeader header = table.getTableHeader();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(452, 100));
		scroll.setBounds(0, 0, 514, 367);
		scroll.setViewportView(table);
		contentPanel.add(scroll);
		TableCellEditor numericEditor = new DefaultCellEditor(new JTextField()) {
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				Component editor = super.getTableCellEditorComponent(table, value, isSelected, row, column);

				if (column == 3) {
					// Apply document filter to limit input to numbers and character limit
					JTextField textField = (JTextField) editor;
					((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter(6));
				}

				return editor;
			}
		};

		// Apply the custom cell editor to the desired column
		table.getColumnModel().getColumn(3).setCellEditor(numericEditor);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String str = event.getActionCommand();
				System.out.println("Clicked = " + str);
				action(str);
			}
		};

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("Add");
			okButton.setActionCommand("Add");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			okButton.addActionListener(actionListener);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			cancelButton.addActionListener(actionListener);
		}
		pack();
	}

	private void action(String str) {
		int lastName = 0, firstName = 1, workGroup = 2, pinCode = 3;

		switch (str) {
		case "Add": {
			boolean b = validCheck();
			if (b == false) {
				break;
			} else {

				employeeContainer x = new employeeContainer();
				x.setAll(table.getValueAt(0, firstName).toString(), table.getValueAt(0, lastName).toString(),
						table.getValueAt(0, workGroup).toString(), "out", table.getValueAt(0, pinCode).toString()

				);
				EmployeeDAO eC = new EmployeeDAO();
				eC.addNewEmployee(x);
				adminPanel.subPanelChange(new AdminSubPanel(frame));
				dispose();
			}
		}
			break;

		case "Cancel": {

			dispose();

			break;
		}
		}
	}

	public boolean validCheck() {
		System.out.println("validating");

		if (table.getCellEditor() != null) {
			table.getCellEditor().stopCellEditing();
		}

		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getValueAt(0, i) == null || table.getValueAt(0, i).toString().trim().length() == 0) {
				System.out.println("cell empty");
				return false;
			}

		}

		System.out.println("cells valid");
		return (true);
	}

	class NumericDocumentFilter extends DocumentFilter {
		private final int maxCharacters;

		public NumericDocumentFilter(int maxCharacters) {
			this.maxCharacters = maxCharacters;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
				throws BadLocationException {
			StringBuilder builder = new StringBuilder();
			builder.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
			builder.insert(offset, text);

			if (isNumeric(builder.toString()) && builder.length() <= maxCharacters) {
				super.insertString(fb, offset, text, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			StringBuilder builder = new StringBuilder();
			builder.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
			builder.replace(offset, offset + length, text);

			if (isNumeric(builder.toString()) && builder.length() <= maxCharacters) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		private boolean isNumeric(String text) {
			return text.matches("\\d+");
		}

	}

}
