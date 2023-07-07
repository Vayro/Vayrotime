package com.lawranta.sqllite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lawranta.Globals.*;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.containersObjects.employeeContainer;

public class connectDB {

	String PIN;
	String name;
	int id;
	String status="out";

	public connectDB(String enteredPin) {
		// TODO Auto-generated constructor stub
		PIN = enteredPin;
	}
	
	public connectDB() {
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Connect to the test.db database
	 * 
	 * @return the Connection object
	 */
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

	public boolean checkPIN() {
		String checksql = "SELECT EXISTS(\n" + "SELECT 1 FROM Employees WHERE pinCode = '" + PIN + "'" + ")\n";

		// check pin exists

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();

				ResultSet check = stmt.executeQuery(checksql)) {
			String verified = check.getString(1).toString();

			if (verified.equals("1")) {
				System.out.println("PIN EXISTS");
				return true;
			} else
				System.out.println("PIN INCORRECT");
			{
			}

			return false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	/**
	 * select all rows in the warehouses table
	 */

	public void setEmployeeInfo() {

		String sql = "SELECT * FROM Employees WHERE pinCode = '" + PIN + "'";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			// while (rs.next()) {
			System.out.println(rs.getInt("id") + "\t" + rs.getString("pinCode") + "\t" + rs.getString("clockedStatus")
					+ "\t" + rs.getString("firstName") + "\t" + rs.getString("lastName"));

			// }

			setId(rs.getInt("id"));
			setName(rs.getString("lastName") + ", " + rs.getString("firstName"));
			setStatus(rs.getString("clockedStatus"));

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void dbUpdateEmployeeStatus(String status) {
		
		String sql =  "UPDATE Employees SET clockedStatus = '"
				+ status +
				"'  "
                + "WHERE pinCode = '"
				+ this.PIN + "'";
		
		try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

          /*  // set the corresponding param
            pstmt.setString(1, status);
            pstmt.setString(2, this.PIN);*/
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());}
        }
		
	
	
	
	
		public ArrayList<employeeContainer> getEmployees() {
			System.out.println("retrieving employees");
			String sql = "SELECT * FROM Employees";
			ArrayList<employeeContainer> employeeList = new ArrayList<>();
			try (Connection conn = this.connect();
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery(sql)) {

				// loop through the result set
				while (rs.next()) {
					employeeContainer newLine = new employeeContainer();
					newLine.setID(rs.getInt("id"));
					newLine.setLastName(rs.getString("lastName"));
					newLine.setFirstName(rs.getString("firstName"));
					newLine.setWorkGroup(rs.getString("workClass"));
					newLine.setClockedStatus(rs.getString("clockedStatus"));
					newLine.setPincode(rs.getString("pinCode"));
					employeeList.add(newLine);
					System.out.println(rs.getString("pinCode"));
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			return employeeList;

		}
		
		
		
		
		
		public void addNewEmployee(employeeContainer e)
		{
			System.out.println("adding employee");
			
			
			
			
			
			String sql = "INSERT INTO EMPLOYEES (lastName, firstName, workClass, clockedStatus, pinCode)\n" + "VALUES (" + "'" + e.getLastName() + "'"
					+ "," +"'"+ e.getFirstName()+"'" + "," + "'" + e.getWorkGroup() + "'" + "," + "'" + e.getClockedStatus() + "'" + "," + "'"+ e.getPincode()+ "' );";
			
			try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);

					) {

				
						pstmt.execute();

					} catch (SQLException ey) {
						System.out.println(ey.getMessage());
					}

				
		}
		
		
		
		public void setEmployeeByID(int ID) {
			
			String sql = "SELECT * FROM Employees WHERE id = '" + ID + "'";

			try (Connection conn = this.connect();
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery(sql)) {

				// loop through the result set
				// while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("pinCode") + "\t" + rs.getString("clockedStatus")
						+ "\t" + rs.getString("firstName") + "\t" + rs.getString("lastName"));

				// }

				setId(rs.getInt("id"));
				setName(rs.getString("lastName") + ", " + rs.getString("firstName"));
				setStatus(rs.getString("clockedStatus"));

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			
			
		}
		
		public void deleteEmployee(){
			
			String sql = "Delete FROM Employees WHERE id = " + getId() + "";

			
				
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
