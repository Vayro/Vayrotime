package com.lawranta.initializers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.lawranta.Globals.Global;
import com.lawranta.Globals.PropertiesCFG;
import com.lawranta.frames.*;
import com.lawranta.sqllite.CreateDB;




public class MainClass {
	private static PanelContainerFrame INITIALIZE;

	 
	 
	public static void main(String[] args) throws IOException {
		{
			File testFont = new File(Global.analogFontPath);
			if(testFont.exists() && !testFont.isDirectory()) { 
			    System.out.println(Global.analogFontPath + " exists");
			}

			
			try {
					try {
						Global.analogFont= Font.createFont(Font.TRUETYPE_FONT,  MainClass.class.getResourceAsStream(Global.analogFontPath)) ;
						Global.analogFont32f = Global.analogFont.deriveFont(32.0f);
						Global.analogFont16f = Global.analogFont.deriveFont(16.0f);
						Global.analogFont12f = Global.analogFont.deriveFont(12.0f);
					} catch (FontFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}catch (IOException e) {
				
				System.out.println(e.getMessage());
			}

			// check if database exists
			CreateDB.CheckDBExists();
		
			//create CGF file if it does not exist
			PropertiesCFG.mainCFG();
	

			System.out.println("Initializing Vayrotime");
			PropertiesCFG.cfgLoad();
			PropertiesCFG.setGlobals();
			INITIALIZE = new PanelContainerFrame();
			INITIALIZE.setVisible(true);

		}
		

		
		

	}

	

}
