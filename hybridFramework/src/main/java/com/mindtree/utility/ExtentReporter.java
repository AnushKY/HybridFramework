package com.mindtree.utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import java.util.Date;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExtentReporter extends TestNGAssertion{

	public static ExtentReports extent;
	public static ExtentTest report;
	public static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static String methodName = "";
	public static String className = "";
	public static String sysDate = "";
			
	@BeforeSuite
	public void setup() {
		
		PropUtils.loadProjectDetails();
		String classNameWithPackage = this.getClass().getName();
		String classWithPackage[] = classNameWithPackage.split("\\.");
		className = classWithPackage[classWithPackage.length-1];
		Date date = new Date();
		sysDate = sdf.format(date);
		sysDate = sysDate.replace(":","_").replace(" ","_").replace("/","_");
		extent = new ExtentReports(System.getProperty("REPORT_PATH")+className+sysDate+".html",false);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	
	}
	
}
