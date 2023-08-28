package com.lawranta.Globals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesCFG {
	static Properties prop=new Properties();
	
	public static void cfgLoad() {
		
		
		
	try {
		FileInputStream ip= new FileInputStream(Global.cfgPath);
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

	public static void mainCFG() throws IOException {
		Properties prop=new Properties();
		File cfg = new File(Global.cfgPath);
		cfg.createNewFile(); // if file already exists will do nothing 
		FileInputStream ip= new FileInputStream(Global.cfgPath);
		prop.load(ip);
		if(!prop.containsKey("adminPin"))
		{
			FileOutputStream op = new FileOutputStream(Global.cfgPath);
			System.out.println("setting admin pin to default");
		
			prop.setProperty("adminPin", "000000");
			prop.store(op, "Configurations");
			
		}
		
	}

	public static void consistPin(String newPin) throws IOException {
		// TODO Auto-generated method stub
		Properties prop=new Properties();
		File cfg = new File(Global.cfgPath);
		cfg.createNewFile(); // if file already exists will do nothing 
		FileInputStream ip= new FileInputStream(Global.cfgPath);
		prop.load(ip);
		
			FileOutputStream op = new FileOutputStream(Global.cfgPath);
			System.out.println("setting admin pin to default");
		
			prop.setProperty("adminPin", newPin);
			prop.store(op, "adminPin updated");
			System.out.println("Admin pin updated.");
			
		
	}
	
	
}
