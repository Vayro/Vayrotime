import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.lawranta.Globals.Global;
import com.lawranta.Globals.PropertiesCFG;
import com.lawranta.frames.*;
import com.lawranta.sqllite.CreateDB;

public class MainClass {
	@SuppressWarnings("unused")
	private static PanelContainerFrame INITIALIZE;

	public static void main(String[] args) throws IOException {
		{

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
