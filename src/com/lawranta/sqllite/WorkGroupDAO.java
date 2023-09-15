package com.lawranta.sqllite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lawranta.DatabaseModels.WorkGroupModel;

public class WorkGroupDAO {

	private static Connection connect() {

		Connection conn = DataConn.getConnection();

		return conn;

	}

	public static ArrayList<WorkGroupModel> retrieveAll() {
		// TODO Auto-generated method stub
		System.out.println("retrieving groups");
		String sql = "SELECT * FROM Workgroup";
		ArrayList<WorkGroupModel> retrievedList = new ArrayList<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				WorkGroupModel newLine = new WorkGroupModel();
				newLine.setId(rs.getInt("id"));

				newLine.setGroupName(rs.getString("workClass"));

				newLine.setLocation(rs.getString("location"));
				retrievedList.add(newLine);

			}
			close(rs, null, conn);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return retrievedList;
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

	public static void updateGroups(Object[][] data) {
		// TODO Auto-generated method stub
		System.out.println("saving groups to DB");

		ArrayList<WorkGroupModel> updateList = new ArrayList<>();
		ArrayList<WorkGroupModel> insertList = new ArrayList<>();

		for (int i = 0; i < data.length; i++) {
			if (data[i][0] != null) {
				System.out.println("update ID:"+ (Integer) data[i][0]);
				WorkGroupModel wgm = new WorkGroupModel();
				wgm.setId((Integer) data[i][0]);
				wgm.setGroupName(data[i][1].toString());
				wgm.setLocation(data[i][2].toString());

				updateList.add(wgm);

			} else {

				WorkGroupModel wgm = new WorkGroupModel();
				// wgm.setId(Integer.parseInt((String) data[i][1]));
				wgm.setGroupName(data[i][1].toString());
				wgm.setLocation( data[i][2].toString());

				insertList.add(wgm);

			}
		}

		// update list first

		String sql = "UPDATE Workgroup SET workClass = ?, location = ? WHERE id = ?  ";

		Connection conn = connect();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < updateList.size(); i++) {

				int id = updateList.get(i).getId();
				pstmt.setString(1, updateList.get(i).getGroupName());
				pstmt.setString(2, updateList.get(i).getLocation());
		
				pstmt.setInt(3, updateList.get(i).getId());
				System.out.println(pstmt);
				System.out.println("updating ID:"+ id);
				System.out.println(pstmt.executeUpdate());
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (insertList.size() > 0) {

			try {
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO Workgroup (workClass, location) VALUES (?,?)  ");

				for (int i = 0; i < insertList.size(); i++) {

					pstmt.setString(1, insertList.get(i).getGroupName());
					pstmt.setString(2, insertList.get(i).getLocation());

					pstmt.executeUpdate();
					
				}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

}
