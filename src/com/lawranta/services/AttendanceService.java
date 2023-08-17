package com.lawranta.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.lawranta.DatabaseModels.AttendanceModel;
import com.lawranta.DatabaseModels.EmployeeModel;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.sqllite.AttendanceDAO;

public class AttendanceService {
	
	
	public static ArrayList<AttendanceModel> pullTimeData(int employeeID, String firstDate, String secondDate){
		
		
		
		
		
		return AttendanceDAO.pullTimeData(employeeID,  firstDate,  secondDate);
		
		
		
	}

	public static void clockIn(EmployeeModel em, String currentTime, String date) {
		// TODO Auto-generated method stub
		
		AttendanceModel am = new AttendanceModel(em);
		am.setDate(date);
		am.setEmployeeID(em.getId());
		am.setName(em.getName());
		
		AttendanceDAO.clockIn(am, currentTime);
	}

	public static void clockOut(EmployeeModel em, String date, String time) throws ParseException {
		// TODO Auto-generated method stub
		AttendanceModel dBA = new AttendanceModel(em);

		dBA.setEmployeeID(em.getId());
		dBA.setDate(date);
		AttendanceDAO.clockOut(dBA, time, date);
		
	}

	public static void deleteLogs(EmployeeModel e) {
		// TODO Auto-generated method stub
		AttendanceModel a = new AttendanceModel(e);
		
		AttendanceDAO.deleteLogs(a);
	}
	
	
	
	
	

}
