package com.lawranta.initializers;

import java.io.IOException;

import com.lawranta.Globals.PropertiesCFG;
import com.lawranta.frames.*;
import com.lawranta.sqllite.CreateDB;




public class MainClass {
	private static PanelContainerFrame INITIALIZE;

	public static void main(String[] args) throws IOException {
		{
			 
			//launch javafx

			
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