package com.lawranta.Globals;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Global {
	//database directory
	public static String dbPath = "database/records.db";
	public static Font headerFont = new Font("TimesRoman", Font.PLAIN, 64);
	public static Font header2Font = new Font("TimesRoman", Font.PLAIN, 32);
	
	public static Font analogFont = loadFontAnalog();
	  
	
	
	

	
	//loaded from crumb.dat
	
	public static String lastPath= new String();







public static Font loadFontAnalog(){
	Font analog = null;
	
	try {
		 analog = Font.createFont(Font.TRUETYPE_FONT, new File("src\\fonts\\Analog.tff")).deriveFont(500);
		GraphicsEnvironment  ge =
	    		  GraphicsEnvironment.getLocalGraphicsEnvironment();
		 ge.registerFont(analog);
	} catch (IOException|FontFormatException e)  {
	     System.out.println("Can't find source? " + e);
	}

	
	
	return analog;
}}


