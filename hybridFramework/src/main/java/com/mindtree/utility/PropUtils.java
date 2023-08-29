package com.mindtree.utility;

import java.io.*;
import java.util.Properties;

public class PropUtils {

	public static String getProperty(String propFilePath, String strKey) {
		Properties prop = new Properties();
		String strValue = "";
		
		try {
			
			prop.load(new FileInputStream(propFilePath));
			strValue = prop.getProperty(strKey);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return strValue;
	}
	
	public static void loadProjectDetails() {
		String projPath = FileUtil.getCanonicalPath();
		System.setProperty("TESTMAIN_PATH",projPath+"\\src\\main\\java");
		System.setProperty("TESTSCRIPT_PATH",projPath+"\\src\\test\\java");
		System.setProperty("TESTMAINRESOURCE_PATH",projPath+"\\src\\main\\resources");
		System.setProperty("TESTSCRIPTRESOURCE_PATH",projPath+"\\src\\test\\resources");
		System.setProperty("PROJ_HOME",projPath);
		System.setProperty("REPORT_PATH",projPath+"\\test-output\\Reports");
		System.setProperty("EXCEL_PATH",System.getProperty("TESTSCRIPTRESOURCE_PATH")+"\\ExcelSheet\\");
	}
	
	
	public static void main(String args[]) {
		loadProjectDetails();
		//String projPath = getProperty(System.getProperty("PROPERTIES_FILEPATH")+"\\Configuration.properties","PROJ_HOME");
		System.out.println(System.getProperty("REPORT_PATH"));
		
	}
}
