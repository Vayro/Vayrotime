package com.lawranta.DatabaseModels;

import com.lawranta.sqllite.EmployeeDAO;

public class AttendanceModel {
	
	int primaryKey;
	String date;
	int employeeID;
	String startTime;
	String endTime;
	String subTotal;
	String name;
	
	public AttendanceModel(EmployeeModel e) {
		// TODO Auto-generated constructor stub
		setEmployeeID(e.getId());
		setName(e.getName());
		
	}
	
	
	
	
	public AttendanceModel() {
		// TODO Auto-generated constructor stub
	}
	public int getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int i) {
		this.employeeID = i;
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
	
	
	public String printInfo() {
		
		String info;
		
		info=date + " " + startTime + " " + endTime + " " + subTotal;
		
		
		return info;
	}
	
	public String[] infoArray()
			{
		String[] info = {date,startTime,endTime,subTotal};
		
		
		
				return info;
		
		
		
			}
	
	
	
	public String toString() {
		
		
		return 	primaryKey + ","+
		 date + "," +
		 employeeID + "," +
		 startTime + "," +
		 endTime + "," +
		 subTotal + "," +
		 name;
		
		
	}

}
