package com.lawranta.Globals;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;

public class SetGlobalFont {
	
	@SuppressWarnings("rawtypes")
	public static final void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 
	
	
	
	
	
	
	public static Font loadFontFromFile(String analogFontPath, float f){
		Font font = null;
		
		try {
			 font = Font.createFont(Font.TRUETYPE_FONT, new File(analogFontPath)).deriveFont(f);
			GraphicsEnvironment  ge =
		    		  GraphicsEnvironment.getLocalGraphicsEnvironment();
			 ge.registerFont(font);
		} catch (IOException|FontFormatException e)  {
		     System.out.println("Can't find source? " + e);
		     
		}

		
		
		return font;
	}
	

}
