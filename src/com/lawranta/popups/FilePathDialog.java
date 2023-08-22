package com.lawranta.popups;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FilePathDialog extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private final JFileChooser contentPanel = new JFileChooser();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FilePathDialog dialog = new FilePathDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FilePathDialog() {
		setBounds(100, 100, 450, 300);

		setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		
		
		setFileFilter(new FileFilter(){
			
			public java.lang.String getDescription(){
		        return "Comma Delimitted, (*.csv)";
		}  @Override
		    public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        } else {
	            return f.getName().toLowerCase().endsWith(".csv");
	        }
	    
		}
		/*
		addChoosableFileFilter(new FileFilter() {
		    public String getDescription() {
		        return "Comma Delimitted, (*.csv)";
		    }
		    @Override
		    public boolean accept(File f) {
		        if (f.isDirectory()) {
		            return true;
		        } else {
		            return f.getName().toLowerCase().endsWith(".csv");
		        }
		    }

			
			
		});*/
		
		
		
		
		
	});}}


