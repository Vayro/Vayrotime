package com.lawranta.services;

import java.util.ArrayList;

import com.lawranta.DatabaseModels.WorkGroupModel;
import com.lawranta.sqllite.WorkGroupDAO;

public class WorkGroupService {

	public static ArrayList<WorkGroupModel> pullGroups() {
		// TODO Auto-generated method stub
		
		ArrayList<WorkGroupModel> groupList=WorkGroupDAO.retrieveAll();
		
		
		return groupList;
	}

}
