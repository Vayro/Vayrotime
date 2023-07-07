package com.lawranta.containersObjects;

public class attendanceContainer {
	int primaryKey;
	String date;
	String employeeID;
	String startTime;
	String endTime;
	String subTotal;
	String name;
	
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
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
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
	

}
