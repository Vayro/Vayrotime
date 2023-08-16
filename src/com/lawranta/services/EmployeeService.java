package com.lawranta.services;

import com.lawranta.sqllite.EmployeeDAO;

public class EmployeeService {
	
	public boolean databaseCompare(String enteredPin) {
		
		System.out.println("Using esmployee service. ");
		EmployeeDAO cDB = new EmployeeDAO();
		return cDB.checkPIN(enteredPin);
		
		
	}
	
	
	

}
