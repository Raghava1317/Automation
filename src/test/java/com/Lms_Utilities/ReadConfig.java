package com.Lms_Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	//Declaring the Object for Properties
	Properties pro;
	//Initializing The Constructor
	public ReadConfig() {
		//Reading the config.properties
		File src  = new File("./configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			//Initializing the Properties
		    pro = new Properties();
		    //Passing an file object to properties
		    pro.load(fis);
		} 
		catch(Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
		
	}


	//Crating the Method for getting the URL
	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}
	
	//Creating the Method for ChromePath
	public String getChromePath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}
	
	//Creating the Method for EdgePath
	public String getEdgePath() {
		String edgepath = pro.getProperty("edgepath");
		return edgepath;
	}
	
	

}
