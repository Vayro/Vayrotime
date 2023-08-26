package com.lawranta.Globals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesCFG {
	static Properties prop=new Properties();
	
	public static void cfgLoad() {
		
		
		
	try {
		FileInputStream ip= new FileInputStream("src\\null.cfg");
		prop.load(ip);
		System.out.println(prop.getProperty("adminPin"));
		
		
	} catch (FileNotFoundException e) {
		
		System.out.println(e);
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println(e);
		e.printStackTrace();
	}
	
	}

	public static void setGlobals() {
		Global.adminPin=prop.getProperty("adminPin");
		System.out.println("ADMIN PIN: " + Global.adminPin);
		
	}
	
	
}
