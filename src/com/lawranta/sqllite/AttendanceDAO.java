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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.lawranta.Globals.*;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.services.daytimeDifferance;
import com.lawranta.DatabaseModels.AttendanceModel;

public class AttendanceDAO {

	/*
	 * id integer PRIMARY KEY,\n" + " date text NOT NULL,\n" +
	 * "	employeeID integer NOT NULL,\n" + "	startTime text NOT NULL,\n" +
	 * "	endTime text NOT NULL,\n" + "	subTotal text NOT NULL,\n"
	 */




	public AttendanceDAO() {
		// TODO Auto-generated constructor stub
		//nothing
	}

	// connect db
	private static Connection connect() {
		// SQLite connection string

		Connection conn = DataConn.getConnection();

		return conn;

	}

	public static void clockIn(AttendanceModel am, String time) {

		String sql = "INSERT INTO Attendance (date, employeeID, lastName, startTime)\n" + "VALUES (" + "'" + am.getDate() + "'"
				+ "," + am.getEmployeeID() + "," + "'" + am.getName() + "'" + "," + "'" + time + "' );";
		String sql2 = "SELECT last_insert_rowid() AS LAST FROM Attendance";
		am.setStartTime(time);

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);

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

	public static void clockOut(AttendanceModel am, String now, String date) throws ParseException {
		String select = "SELECT MAX(id) AS LAST FROM Attendance";
		String maxID;
		am.setEndTime(now);

		// Select SQL Statement

		try (Connection conn = connect();

				PreparedStatement pstmt = conn.prepareStatement(select);

		) {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			String lastID = rs.getString("LAST");
			int id = (Integer.parseInt(lastID));
			am.setPrimaryKey(id);
			System.out.println("Primary key for Attendance is " + lastID);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

//ADD TIME TO SELECTED 

		System.out.println("Primary Key is " + am.getPrimaryKey());
		String sql = "UPDATE Attendance SET endTime = '" + now + "' WHERE ID=" + am.getPrimaryKey();
		try (Connection conn = connect();

				PreparedStatement pstmt = conn.prepareStatement(sql);

		) {

			System.out.println("Added " + now + " to primaryKey " + am.getPrimaryKey());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

//Pull Start Time
		String pullTime = "SELECT * FROM Attendance where ID =" + am.getPrimaryKey();
		try (Connection conn = connect();

				PreparedStatement pstmt2 = conn.prepareStatement(pullTime);
				ResultSet sTime = pstmt2.executeQuery();) {
			am.setStartTime(sTime.getString("startTime"));

//calculate subTotal
			System.out.println(am.getStartTime() + "-" + am.getEndTime());

			/*SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date x1 = format.parse(am.getStartTime());
			Date x2 = format.parse(am.getEndTime());*/
			
			
			
			
//convert milliseconds
			String converted =  daytimeDifferance.calculate(am.getStartTime(),am.getEndTime());

//update sub total

			String sqlSubTotal = "UPDATE Attendance SET subTotal = '" + converted + "' WHERE ID=" + am.getPrimaryKey();

			PreparedStatement substmt = conn.prepareStatement(sqlSubTotal);
			System.out.println("Added " + now + " to primaryKey " + am.getPrimaryKey());
			substmt.executeUpdate();

			System.out.println(converted);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
	}

	
	public static AttendanceModel setModelwithID(int ID) {
		
		
		System.out.println("Select * FROM Attendance WHERE id = " + ID );
		String sql = "SELECT * FROM Attendance WHERE id =?";

		AttendanceModel aM = new AttendanceModel();
		
		
		try {
			Connection conn = connect();
			Statement stmt;
			 PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			
			ResultSet rs =	ps.executeQuery();

			while (rs.next()) {
				
				aM.setPrimaryKey(rs.getInt("id"));;
				aM.setDate(rs.getString("date"));
				aM.setStartTime(rs.getString("startTime"));
				aM.setEndTime(rs.getString("endTime"));
				aM.setSubTotal(rs.getString("subTotal"));
				aM.setEmployeeID(rs.getInt("employeeID"));
				aM.setName(rs.getString("lastName"));
			
				// System.out.println("looped");
			}
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		e.printStackTrace();
		}

		
		
		
		
		
		
		
		
		System.out.println(aM.toString());
		
		return aM;
	
		
	}
	
	
	
	
	
	
	
	public static ArrayList<AttendanceModel> pullTimeData(int employeeID, String firstDate, String secondDate) {


		
		String sql = new String();

		if (employeeID != 0 && firstDate == null && secondDate == null) {
			System.out.println("Employee ID is: " + employeeID);
			sql = "SELECT * FROM Attendance WHERE employeeID= " + employeeID;
		} else if (employeeID == 0 && firstDate == null && secondDate == null) {
			System.out.println("Getting all employee logs");
			sql = "SELECT * FROM Attendance";
		} else if (employeeID == 0 && firstDate != null && secondDate != null) {
			
			
			
			System.out.println("Getting all employee logs between " + firstDate  + " and " + secondDate);
			sql = "SELECT * FROM Attendance WHERE date BETWEEN '" + firstDate  + "' AND '" + secondDate + "'";
		}else if (employeeID != 0 && firstDate != null && secondDate != null) {
			
			System.out.println("Getting employee logs for employee id: " + employeeID +" between " + firstDate  + " and " + secondDate);
			sql = "SELECT * FROM Attendance WHERE employeeID= " + employeeID + " AND date BETWEEN '" + firstDate  + "' AND '" + secondDate + "'";
		}
		
		
		
		

		ArrayList<AttendanceModel> timeInfoList = new ArrayList<>();
		System.out.println("Trying to pull attendance data");

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				AttendanceModel newLine = new AttendanceModel();
				newLine.setPrimaryKey(rs.getInt("id"));;
				newLine.setDate(rs.getString("date"));
				newLine.setStartTime(rs.getString("startTime"));
				newLine.setEndTime(rs.getString("endTime"));
				newLine.setSubTotal(rs.getString("subTotal"));
				newLine.setEmployeeID(rs.getInt("employeeID"));
				newLine.setName(rs.getString("lastName"));
				timeInfoList.add(newLine);
				// System.out.println("looped");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return timeInfoList;

	}

	public static void deleteLogs(AttendanceModel am) {
		System.out.println("Delete FROM Attendance WHERE employeeid = " + am.getEmployeeID() + "");
		String sql = "Delete FROM Attendance WHERE employeeid = " + am.getEmployeeID() + "";

		try {
			Connection conn = connect();
			Statement stmt;

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
