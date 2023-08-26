package com.lawranta.Globals;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

public class Global {
	//database directory
	public static String dbPath = "database/records.db";
	public static Font headerFont = new Font("TimesRoman", Font.PLAIN, 64);
	public static Font header2Font = new Font("TimesRoman", Font.PLAIN, 32);
	private static String analogFontPath="src\\fonts\\Analog.ttf"; 
	public static Font analogFont32f = SetGlobalFont.loadFontFromFile(analogFontPath,32f);
	public static Font analogFont16f = SetGlobalFont.loadFontFromFile(analogFontPath,16f);
	public static Font analogFont12f = SetGlobalFont.loadFontFromFile(analogFontPath,12f);
	public static Font timesFont12 = new Font("TimesRoman", Font.PLAIN, 12);
	public static String adminPin;
	
	
	

	
	//loaded from crumb.dat
	
	public static String lastPath= new String();






	public static Component padding(int i) {
		JLabel padding = new JLabel(" ");
		padding.setPreferredSize(new Dimension(420, i));
		padding.setMinimumSize(new Dimension(420, i));
		padding.setMaximumSize(new Dimension(420, i));
		padding.setSize(new Dimension(420, i));
		padding.setVisible(true);
		padding.setAlignmentX(Component.CENTER_ALIGNMENT);
		padding.setAlignmentY(Component.CENTER_ALIGNMENT);
		return padding;
	}







}


