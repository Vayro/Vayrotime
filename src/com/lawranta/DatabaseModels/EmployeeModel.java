package com.lawranta.DatabaseModels;

import com.lawranta.services.EmployeeService;

public class EmployeeModel {

	String PIN;
	String name;
	int id;
	String status="out";
	String firstName;
	String lastName;
	String workGroup;
	String clockedStatus;
	String pincode;

	public EmployeeModel(String enteredPin) {
		// TODO Auto-generated constructor stub
		PIN = enteredPin;
	}
	
	public EmployeeModel() {
		System.out.println("Using employee Model");

	}
	
	public String getPIN() {
		return PIN;
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

	public void setEmployeeInfo() {
		
		EmployeeService.setEmployeeInfo(this);
		
		
		
		
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
