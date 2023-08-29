package com.mindtree.utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class TestListeners extends BaseTest implements ITestListener{
	
	public void onTestStart(ITestResult result) {

		System.out.println("Test case "+result.getName() +" started");
		
	  }
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case "+result.getName() +" passed");
		//report.log(LogStatus.PASS,"Passed Testcase from listeners is :"+result.getName());
	  }
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case "+result.getName() +" failed");
	  }
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test case "+result.getName() +" skipped");
	  }

}
