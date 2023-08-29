package com.mindtree.testcases;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mindtree.pages.HomePage;
import com.mindtree.utility.ExcelReader;
import com.mindtree.utility.PropUtils;
import com.mindtree.utility.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class IPLHomePageTestcases extends BaseTest{

	public static HomePage hp = null;
	public String testName;
	public String classname;
	public String excelPath;
	public String sheetName;
	HashMap<String,String> excelData = null;
	
	@BeforeMethod
	public void testInitiation(Method method) {
		
		//clearing assertion errors
		assertion.clearErrorLog();
		
		//test initialization
		testName = method.getName();
		
		
		sheetName = "TestDataSheet";
		
		//report initialization
		report = extent.startTest(testName+" Test");
		
		
		
		
	}
	
	@Test(dataProvider = "homePageData")
	public void iplHomePageValidation(String Execution, String Testcase) throws Exception {
		
		try {
			excelData = ExcelReader.getXLSXvalues(excelPath, sheetName, Testcase);
			
			String url = ExcelReader.getValueFromExcel(excelData, "Url");
			String browser = ExcelReader.getValueFromExcel(excelData, "browser");
			
			navigateToUrl(browser, url);
			
			//page initialization
			hp = new HomePage(driver);
			
			Thread.sleep(20000);
			
			hp.verifyMatchesLnk();
			hp.verifyLogoLnk();
			
			assertion.assertAll();
			assertion.clearErrorLog();
		}catch(Exception e) {
			e.printStackTrace();
			report.log(LogStatus.FAIL,e.getMessage());
			assertion.fail("TestCaseFailed");
		}
	}
	
	@Test(dataProvider = "homePageData")
	public void iplHomePageValidationTestCase2(String Execution, String Testcase) throws Exception {
		
		try {
			excelData = ExcelReader.getXLSXvalues(excelPath, sheetName, Testcase);
			
			String url = ExcelReader.getValueFromExcel(excelData, "Url");
			String browser = ExcelReader.getValueFromExcel(excelData, "browser");
			
			navigateToUrl(browser, url);
			
			//page initialization
			hp = new HomePage(driver);
			
			Thread.sleep(20000);
			
			hp.verifyMatchesLnk();
			hp.verifyLogoLnk();
			
			assertion.assertAll();
			assertion.clearErrorLog();
		}catch(Exception e) {
			e.printStackTrace();
			report.log(LogStatus.FAIL,e.getMessage());
			assertion.fail("TestCaseFailed");
		}
	}
	
	
	@DataProvider(name="homePageData")
	public Object[][] homePageDetail() throws Exception{
		
		
		PropUtils.loadProjectDetails();
		
		
		excelPath = System.getProperty("EXCEL_PATH")+"testDataIpl.xlsx";
		sheetName = "homePage_Config";
		
		
		Object[][] testobjArray = ExcelReader.getTableArray(excelPath,sheetName);
		
		return (testobjArray);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
	
		try {
			
			/*
			 * if(result.getStatus() == ITestResult.FAILURE) {
			 * report.log(LogStatus.FAIL,"Failed Testcase is :"+result.getName()); }else
			 * if(result.getStatus() == ITestResult.SUCCESS) {
			 * //report.log(LogStatus.PASS,"Passed Testcase is :"+result.getName()); }else
			 * if(result.getStatus() == ITestResult.SKIP) {
			 * report.log(LogStatus.SKIP,"Skipped Testcase is :"+result.getName()); }
			 */
			
			driver.quit();
			report.log(LogStatus.PASS, "Browser closed successfully");
			extent.endTest(report);
			extent.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
