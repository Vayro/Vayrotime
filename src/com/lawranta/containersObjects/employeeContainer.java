package com.lawranta.containersObjects;

public class employeeContainer {
	int ID;
	String firstName;
	String lastName;
	String workGroup;
	String clockedStatus;
	String pincode;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getWorkGroup() {
		return workGroup;
	}
	public void setWorkGroup(String workGroup) {
		this.workGroup = workGroup;
	}
	public String getClockedStatus() {
		return clockedStatus;
	}
	public void setClockedStatus(String clockedStatus) {
		this.clockedStatus = clockedStatus;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	public void setAll(	
	String firstName_x,
	String lastName_x,
	String workGroup_x,
	String clockedStatus_x,
	String pincode_x) {
		
		setFirstName(firstName_x);
		setLastName(lastName_x);
		setWorkGroup(workGroup_x);
		setClockedStatus(clockedStatus_x);
		setPincode(pincode_x);
		
		
		
		
		// TODO Auto-generated method stub
		
	}

}
