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
		
		for(int i = 0; i < data.length; i++)
		{
			if(data[i][1]!=null) {
				
				
				WorkGroupModel wgm= new WorkGroupModel();
				wgm.setId(Integer.parseInt((String) data[i][1]));
				wgm.setGroupName((String) data[i][2]);
				wgm.setLocation((String) data[i][3]);
				
				updateList.add(wgm);
				
			}else
			{
				
				WorkGroupModel wgm= new WorkGroupModel();
			//	wgm.setId(Integer.parseInt((String) data[i][1]));
				wgm.setGroupName((String) data[i][2]);
				wgm.setLocation((String) data[i][3]);
				
				
				
				insertList.add(wgm);
				
			}
			
			
		//update list first
			
			
			
			String sql = "UPDATE Workgroup SET workClass = ?, location = ? WHERE ID = ?  ";
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
