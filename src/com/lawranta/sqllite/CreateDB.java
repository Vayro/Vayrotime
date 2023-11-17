package com.lawranta.sqllite;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.lawranta.Globals.Global;

public class CreateDB {

	public static void CheckDBExists() {

	//	String filePathString = Global.dbPath;
		System.out.println("Checking if database exists");
		File f = Global.dbFile;
		if (f.exists() && !f.isDirectory()) {
			// do something
			System.out.println("database exists");

			// create database

		} else {
			System.out.println("database does not exist");
			System.out.println("creating database");

			try {
				createNewDatabase(Global.dbFile.getAbsolutePath());
				System.out.println("created at " + Global.dbFile.getAbsolutePath());
			} catch (Exception e) {
				System.out.println("Cannot create database at " + Global.dbFile.getAbsolutePath());
			}
			createNewTable();

		}

	}

	public static void createNewDatabase(String fileName) {
		System.out.println("absolute path..." + Global.dbFile.getAbsolutePath());
		String url = "jdbc:sqlite:" + Global.dbFile.getAbsolutePath();
		System.out.println("creating at..." + url);
			
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage() + "... Cannot create new database ");
		}
	}

	public static void createNewTable() {
		// SQLite connection string
		String url = "jdbc:sqlite:database/records.db";

		// SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS Employees (\n" + "	id integer PRIMARY KEY,\n"
				+ "	firstName text NOT NULL,\n" + "	lastName text NOT NULL,\n" + "	workClassID integer NOT NULL,\n"
				+ "	clockedStatus text NOT NULL,\n" + "	pinCode integer NOT NULL\n,"
				+ "FOREIGN KEY(workClassID) REFERENCES Workgroup(id)" + ");";

		String sql2 = "CREATE TABLE IF NOT EXISTS Attendance (\n" + "	id integer PRIMARY KEY,\n"
				+ "	date text NOT NULL,\n" + "	employeeID integer NOT NULL,\n" + "lastName text NOT NULL,\n"
				+ "	startTime text NOT NULL,\n" + "	endTime text,\n" + "	subTotal text,\n"
				+ "	FOREIGN KEY(employeeID) REFERENCES Employees(id)\n" + ");";

		String sql5 = "INSERT INTO workGroup (workClass)\n" + "VALUES ('Office');";

		String sql3 = "INSERT INTO Employees (firstName, lastName, workClassID, clockedStatus, pinCode)\n"
				+ "VALUES ('admin', 'admin', 1, 'out', '372707');";

		String sql4 = "CREATE TABLE IF NOT EXISTS Workgroup (\n" + "	id integer PRIMARY KEY,\n"
				+ "	workClass text UNIQUE NOT NULL,\n" + "location text DEFAULT 'Main Office' NOT NULL\n" + ");";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql4);
			stmt.execute(sql);
			stmt.execute(sql2);
			stmt.execute(sql3);
			stmt.execute(sql5);
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
