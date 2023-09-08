package com.lawranta.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.Globals.*;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.containersObjects.employeeContainer;

public class EmployeeDAO {

	/**
	 * Connect to the test.db database
	 * 
	 * @return the Connection object
	 */
	private static Connection connect() {

		Connection conn = DataConn.getConnection();

		return conn;

	}

	public boolean checkPIN(String pin) {

		String checksql = "SELECT EXISTS(\n" + "SELECT 1 FROM Employees WHERE pinCode = '" + pin + "'" + ")\n";

		// check pin exists

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();

				ResultSet check = stmt.executeQuery(checksql)) {
			String verified = check.getString(1).toString();

			if (verified.equals("1")) {
				System.out.println("PIN EXISTS");
				close(null, null, conn);
				return true;
			} else
				System.out.println("PIN INCORRECT");
			{
			}
			close(null, null, conn);
			return false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	/**
	 * select all rows in the warehouses table
	 */

	public static void setEmployeeInfo(EmployeeModel e) {

		String sql = "SELECT * FROM Employees WHERE pinCode = '" + e.getPIN() + "'";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			// while (rs.next()) {
			System.out.println(rs.getInt("id") + "\t" + rs.getString("pinCode") + "\t" + rs.getString("clockedStatus")
					+ "\t" + rs.getString("firstName") + "\t" + rs.getString("lastName"));

			// }

			e.setId(rs.getInt("id"));
			e.setName(rs.getString("lastName") + ", " + rs.getString("firstName"));
			e.setStatus(rs.getString("clockedStatus"));
			close(rs, null, conn);
		} catch (SQLException er) {
			System.out.println(er.getMessage());
		}

	}

	public static void dbUpdateEmployeeStatus(EmployeeModel em, String status) {

		String sql = "UPDATE Employees SET clockedStatus = '" + status + "'  " + "WHERE pinCode = '" + em.getPIN()
				+ "'";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			/*
			 * // set the corresponding param pstmt.setString(1, status); pstmt.setString(2,
			 * this.PIN);
			 */
			// update
			pstmt.executeUpdate();
			close(null, pstmt, conn);
		} catch (SQLException er) {
			System.out.println(er.getMessage());
		}
	}

	public ArrayList<EmployeeModel> getEmployees() {
		System.out.println("retrieving employees");
		String sql = "SELECT * FROM Employees";
		ArrayList<EmployeeModel> employeeList = new ArrayList<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				EmployeeModel newLine = new EmployeeModel();
				newLine.setID(rs.getInt("id"));
				newLine.setLastName(rs.getString("lastName"));
				newLine.setFirstName(rs.getString("firstName"));
				newLine.setWorkGroup(rs.getString("workClass"));
				newLine.setClockedStatus(rs.getString("clockedStatus"));
				newLine.setPincode(rs.getString("pinCode"));
				employeeList.add(newLine);
				System.out.println(rs.getString("pinCode"));

			}
			close(rs, null, conn);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employeeList;

	}

	public static void addNewEmployee(EmployeeModel x) {
		System.out.println("adding employee");

		String sql = "INSERT INTO EMPLOYEES (lastName, firstName, workClass, clockedStatus, pinCode)\n" + "VALUES ("
				+ "'" + x.getLastName() + "'" + "," + "'" + x.getFirstName() + "'" + "," + "'" + x.getWorkGroup() + "'"
				+ "," + "'" + x.getClockedStatus() + "'" + "," + "'" + x.getPincode() + "' );";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);

		) {

			pstmt.execute();
			close(null, pstmt, conn);
		} catch (SQLException ey) {
			System.out.println(ey.getMessage());
		}

	}

	public static EmployeeModel setEmployeeByID(EmployeeModel em, int ID) {

		String sql = "SELECT * FROM Employees WHERE id = '" + ID + "'";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			// while (rs.next()) {
			System.out.println(rs.getInt("id") + "\t" + rs.getString("pinCode") + "\t" + rs.getString("clockedStatus")
					+ "\t" + rs.getString("firstName") + "\t" + rs.getString("lastName"));

			// }

			em.setId(rs.getInt("id"));
			em.setName(rs.getString("lastName") + ", " + rs.getString("firstName"));
			em.setStatus(rs.getString("clockedStatus"));
			close(rs, null, conn);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return em;

	}

	public static void deleteEmployee(EmployeeModel em) {

		String sql = "Delete FROM Employees WHERE id = " + em.getId() + "";

		try {
			Connection conn = connect();
			Statement stmt;

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			close(null, null, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateByID(int primaryKey, String status) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Employees SET clockedStatus = '" + status + "'  " + "WHERE ID = " + primaryKey;

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			/*
			 * // set the corresponding param pstmt.setString(1, status); pstmt.setString(2,
			 * this.PIN);
			 */
			// update
			pstmt.executeUpdate();
		} catch (SQLException er) {
			System.out.println(er.getMessage());
		}

	}

	public static void updateAllInfoByModel(EmployeeModel e) {
		// TODO Auto-generated method stub
		System.out.println("made it to DAO... " + e.getFirstName() + ", " + e.getFirstName() + ", " + e.getWorkGroup()
				+ ", " + e.getPincode() + ", " + e.getClockedStatus() + ", " + e.getID() + ", ");
		String sql = "UPDATE Employees SET firstName=?, lastName=?,workClass=?,pinCode=?, clockedStatus = ? WHERE ID=? ";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, e.getFirstName());
			pstmt.setString(2, e.getLastName());
			pstmt.setString(3, e.getWorkGroup());
			pstmt.setString(4, e.getPincode());
			pstmt.setString(5, e.getClockedStatus());
			pstmt.setInt(6, e.getID());
			pstmt.executeUpdate();
			close(null, pstmt, conn);
		} catch (SQLException er) {
			System.out.println(er.getMessage());
		}

	}

	public static boolean checkDuplicatePin(String pin, int PrimaryKey) {
		// TODO Auto-generated method stub
		String sql = "	SELECT * FROM Employees WHERE pinCode = ?;";

		Connection conn = connect();
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, pin);
			ResultSet rs = p.executeQuery();

			if (rs.next()) {

				if (rs.getInt(1) == PrimaryKey)

				{
					close(rs, p, conn);
					return false;
				} else {
					close(rs, p, conn);
					return true;
				}
				// pin already exists
			} else {
				close(rs, p, conn);
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	static void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

}
