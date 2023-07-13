package com.lawranta.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.lawranta.Globals.*;
import com.lawranta.containersObjects.attendanceContainer;

public class DbAttendance {

	/*
	 * id integer PRIMARY KEY,\n" + " date text NOT NULL,\n" +
	 * "	employeeID integer NOT NULL,\n" + "	startTime text NOT NULL,\n" +
	 * "	endTime text NOT NULL,\n" + "	subTotal text NOT NULL,\n"
	 */

	int primaryKey;
	String date;
	int employeeID;
	String startTime;
	String endTime;
	String subTotal;
	String name;
	String workGroup;

	public String getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	connectDB cDB;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DbAttendance(connectDB passedDB, String today) {
		cDB = passedDB;
		setDate(today);
		setEmployeeID(cDB.getId());

	}

	public DbAttendance(connectDB passedDB) {
		cDB = passedDB;
		setEmployeeID(cDB.getId());

	}

	public DbAttendance() {
		// TODO Auto-generated constructor stub
		//nothing
	}

	// connect db
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:" + Global.dbPath;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;

	}

	public void clockIn(String time) {

		String sql = "INSERT INTO Attendance (date, employeeID, lastName, startTime)\n" + "VALUES (" + "'" + date + "'"
				+ "," + employeeID + "," + "'" + cDB.getName() + "'" + "," + "'" + time + "' );";
		String sql2 = "SELECT last_insert_rowid() AS LAST FROM Attendance";
		setStartTime(time);

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);

		) {

			// set the corresponding param
			// pstmt.setString(1, status);
			// pstmt.setString(2, this.PIN);
			// update
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void clockOut(String time) throws ParseException {
		String select = "SELECT MAX(id) AS LAST FROM Attendance";
		String maxID;
		setEndTime(time);

		// Select SQL Statement

		try (Connection conn = this.connect();

				PreparedStatement pstmt = conn.prepareStatement(select);

		) {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			String lastID = rs.getString("LAST");
			int id = (Integer.parseInt(lastID));
			setPrimaryKey(id);
			System.out.println("Primary key for Attendance is " + lastID);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

//ADD TIME TO SELECTED 

		System.out.println("Primary Key is " + primaryKey);
		String sql = "UPDATE Attendance SET endTime = '" + time + "' WHERE ID=" + this.primaryKey;
		try (Connection conn = this.connect();

				PreparedStatement pstmt = conn.prepareStatement(sql);

		) {

			System.out.println("Added " + time + " to primaryKey " + primaryKey);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

//Pull Start Time
		String pullTime = "SELECT * FROM Attendance where ID =" + this.primaryKey;
		try (Connection conn = this.connect();

				PreparedStatement pstmt2 = conn.prepareStatement(pullTime);
				ResultSet sTime = pstmt2.executeQuery();) {
			setStartTime(sTime.getString("startTime"));

//calculate subTotal
			System.out.println(startTime + "-" + endTime);

			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date x1 = format.parse(startTime);
			Date x2 = format.parse(endTime);
			long timeElapsed = x2.getTime() - x1.getTime();
//convert milliseconds
			String converted = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeElapsed),
					TimeUnit.MILLISECONDS.toMinutes(timeElapsed)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeElapsed)),
					TimeUnit.MILLISECONDS.toSeconds(timeElapsed)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed)));

//update sub total

			String sqlSubTotal = "UPDATE Attendance SET subTotal = '" + converted + "' WHERE ID=" + this.primaryKey;

			PreparedStatement substmt = conn.prepareStatement(sqlSubTotal);
			System.out.println("Added " + time + " to primaryKey " + primaryKey);
			substmt.executeUpdate();

			System.out.println(converted);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<attendanceContainer> pullTimeData(int employeeID, String firstDate, String secondDate) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String sql = "";

		if (employeeID != 0 && firstDate == null && secondDate == null) {
			System.out.println("Employee ID is: " + employeeID);
			sql = "SELECT * FROM Attendance WHERE employeeID= " + employeeID;
		} else if (employeeID == 0 && firstDate == null && secondDate == null) {
			System.out.println("Getting all employee logs");
			sql = "SELECT * FROM Attendance";
		} else if (employeeID == 0 && firstDate != null && secondDate != null) {
			LocalDate fDate;
			LocalDate tDate;
			fDate =  LocalDate.parse(firstDate, dtf);
			tDate =   LocalDate.parse(secondDate, dtf);
			java.sql.Date sqlStartDate =  new java.sql.Date(fDate.getLong(null));  
			java.sql.Date sqlEndDate =  new java.sql.Date(tDate.getLong(null));  

			
			
			
			
			System.out.println("Getting all employee logs between " + sqlStartDate  + " and " + sqlEndDate);
			sql = "SELECT * FROM Attendance WHERE date BETWEEN '" + sqlStartDate  + "' AND '" + sqlEndDate + "'";
		}

		ArrayList<attendanceContainer> timeInfoList = new ArrayList<>();
		System.out.println("Trying to pull attendance data");

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				attendanceContainer newLine = new attendanceContainer();
				newLine.setPrimaryKey(rs.getInt("id"));;
				newLine.setDate(rs.getString("date"));
				newLine.setStartTime(rs.getString("startTime"));
				newLine.setEndTime(rs.getString("endTime"));
				newLine.setSubTotal(rs.getString("subTotal"));
				newLine.setEmployeeID(rs.getString("employeeID"));
				newLine.setName(rs.getString("lastName"));
				timeInfoList.add(newLine);
				// System.out.println("looped");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return timeInfoList;

	}

	public void deleteLogs() {
		String sql = "Delete * FROM Attendance WHERE id = " + getEmployeeID() + "";

		try {
			Connection conn = this.connect();
			Statement stmt;

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
