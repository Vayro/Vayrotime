package com.lawranta.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lawranta.Globals.Global;

public class DataConn {
	//my attempt at a Singleton 
	
	 private static Connection conn = null;
	
	
	public static Connection newConnection() {
	// SQLite connection string
		System.out.println("Attempting new connection");
			String url = "jdbc:sqlite:" + Global.dbPath;
			conn = null;
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
			
				System.out.println("Can't connect: " + e.getMessage());
			}
		

	return conn;
	}
	
    public static Connection getConnection()
    {
    	System.out.println("Using DataConn class to get connection to database...");
    	conn = newConnection();
        return conn;
    }
	
	
}
