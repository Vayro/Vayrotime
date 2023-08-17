package com.lawranta.DatabaseModels;

import com.lawranta.services.EmployeeService;

public class EmployeeModel {

	String PIN;
	String name;
	int id;
	String status="out";

	public EmployeeModel(String enteredPin) {
		// TODO Auto-generated constructor stub
		PIN = enteredPin;
	}
	
	public EmployeeModel() {
		System.out.println("penisesss");

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
	
	
	
	
}
