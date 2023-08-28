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

public class MainClass {
	@SuppressWarnings("unused")
	private static PanelContainerFrame INITIALIZE;

	public static void main(String[] args) throws IOException {
		{

			// check if database exists
			String filePathString = Global.dbPath;
			System.out.println("Checking if database exists");
			File f = new File(filePathString);
			if (f.exists() && !f.isDirectory()) {
				// do something
				System.out.println("database exists");

				// create database

			} else {
				System.out.println("database does not exist");
				System.out.println("creating database");

				createNewDatabase(Global.dbPath);
				createNewTable();

			}
			
			//create CGF file if it does not exist
			PropertiesCFG.mainCFG();
	

			System.out.println("Initializing Vayrotime");
			PropertiesCFG.cfgLoad();
			PropertiesCFG.setGlobals();
			INITIALIZE = new PanelContainerFrame();
			INITIALIZE.setVisible(true);

		}
		

		
		

	}

	public static void createNewDatabase(String fileName) {

		String url = "jdbc:sqlite:" + fileName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createNewTable() {
		// SQLite connection string
		String url = "jdbc:sqlite:database/records.db";

		// SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS Employees (\n" + "	id integer PRIMARY KEY,\n"
				+ "	firstName text NOT NULL,\n" + "	lastName text NOT NULL,\n" + "	workClass text NOT NULL,\n"
				+ "	clockedStatus text NOT NULL,\n" + "	pinCode integer NOT NULL\n" + ");";

		String sql2 = "CREATE TABLE IF NOT EXISTS Attendance (\n" + "	id integer PRIMARY KEY,\n"
				+ "	date text NOT NULL,\n" + "	employeeID integer NOT NULL,\n" + "lastName text NOT NULL,\n"
				+ "	startTime text NOT NULL,\n" + "	endTime text,\n" + "	subTotal text,\n"
				+ "	FOREIGN KEY(employeeID) REFERENCES Employees(id)\n" + ");";

		String sql3 = "INSERT INTO Employees (firstName, lastName, workClass, clockedStatus, pinCode)\n"
				+ "VALUES ('admin', 'admin', 'supervisor', 'out', '372707');";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			stmt.execute(sql2);
			stmt.execute(sql3);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
