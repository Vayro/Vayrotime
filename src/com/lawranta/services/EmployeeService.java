package com.lawranta.services;

import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.sqllite.EmployeeDAO;

public class EmployeeService {
	
	public static boolean databaseCompare(String enteredPin) {
		
		System.out.println("Using esmployee service. ");
		EmployeeDAO cDB = new EmployeeDAO();
		return cDB.checkPIN(enteredPin);
		
		
	}

	public static void setEmployeeInfo(EmployeeModel e) {
		// TODO Auto-generated method stub
		
		EmployeeDAO.setEmployeeInfo(e);
		
		
	}

	public static void setStatus(EmployeeModel em, String status) {
		// TODO Auto-generated method stub
		
		EmployeeDAO.dbUpdateEmployeeStatus(em, "in");
		
		
	}
	
	public static void dbUpdateEmployeeStatus(EmployeeModel em, String status) {
		
		
		EmployeeDAO.dbUpdateEmployeeStatus(em,  status);
	}

	public static EmployeeModel setEmployeeByID(int id) {
		// TODO Auto-generated method stub
		EmployeeModel e = new EmployeeModel();
		e = EmployeeDAO.setEmployeeByID(e, id);
		return e;
	}

	
	public static void deleteEmployee(EmployeeModel em){
		 
		 EmployeeDAO.deleteEmployee(em);
		 
	 }

	public static void addNewEmployee(EmployeeModel x) {
		
		EmployeeDAO.addNewEmployee(x);
		// TODO Auto-generated method stub
		
	}

	public static void dbUpdateEmployeeStatus(int primaryKey, String status) {
		// TODO Auto-generated method stub
		EmployeeDAO.updateByID(primaryKey, status);
		
	}
	 

	
	

}
