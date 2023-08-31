package com.mindtree.testcases;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mindtree.pages.HomePage;
import com.mindtree.pages.IbiboHomepage;
import com.mindtree.pages.IbiboSearchPage;
import com.mindtree.utility.ExcelReader;
import com.mindtree.utility.PropUtils;
import com.mindtree.utility.SeleniumUtils;
import com.mindtree.utility.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class GoIbiboTestCases extends BaseTest{

	public static IbiboHomepage hp = null;
	public static IbiboSearchPage sp = null;
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
	
	
	@Test(dataProvider = "goibiboTestData") 
	  public void searchHotelAndLogHotelName(String Execution, String Testcase) {
	  
	  try { 
		  excelData = ExcelReader.getXLSXvalues(excelPath, sheetName,Testcase);
		  } catch (Exception e) { // TODO Auto-generated catch block
			  e.printStackTrace(); 
	  }
	  
	  String url = ExcelReader.getValueFromExcel(excelData, "Url");
	  String browser  = ExcelReader.getValueFromExcel(excelData, "browser");
	  
	  navigateToUrl(browser, url); 
	  SeleniumUtils.setImplicitWait(60);
	  
	  //page initialization 
	  hp = new IbiboHomepage(driver); 
	  sp = new IbiboSearchPage(driver);
	  
	  try { 
		  Thread.sleep(10000); 
		  } catch (InterruptedException e){  
	  e.printStackTrace(); 
	  }
	  
	  hp.closeLoginPopup(); 
	  hp.verifyAndClickOnHotelsLink(); 
	  String city =  ExcelReader.getValueFromExcel(excelData, "city");
	  hp.enterTheDestinationAndSelectCity(city);
	  hp.selectTheNumberOfAdults();
	  hp.clickOnSearchButton();
	  sp.verifySearchResultsTitle();
	  assertion.assertAll();
	  
	  assertion.clearErrorLog();
	  }
	 
	
	
	@Test(dataProvider = "goibiboTestData")
	public void searchHotelAndVerifyHotelDetail(String Execution, String Testcase) {
		
			try {
				System.out.println(Testcase);
				excelData = ExcelReader.getXLSXvalues(excelPath, sheetName, Testcase);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String url = ExcelReader.getValueFromExcel(excelData, "Url");
			String browser = ExcelReader.getValueFromExcel(excelData, "browser");
			
			navigateToUrl(browser, url);
			SeleniumUtils.setImplicitWait(60);
			
			//page initialization
			hp = new IbiboHomepage(driver);
			sp = new IbiboSearchPage(driver);
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			hp.closeLoginPopup();
			hp.verifyAndClickOnHotelsLink();
			String city = ExcelReader.getValueFromExcel(excelData, "city");
			hp.enterTheDestinationAndSelectCity(city);
			hp.selectTheNumberOfAdults();
			hp.clickOnSearchButton();
			sp.verifySearchResultsTitle();
			sp.clickOnFirstSearchResult();
			sp.verifySearchPageFunctionality();
			
			assertion.assertAll();
	}
	
	@Test(dataProvider = "goibiboTestData")
	public void searchHotelAndApplyFilter(String Execution, String Testcase) {
		
			try {
				System.out.println(Testcase);
				excelData = ExcelReader.getXLSXvalues(excelPath, sheetName, Testcase);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String url = ExcelReader.getValueFromExcel(excelData, "Url");
			String browser = ExcelReader.getValueFromExcel(excelData, "browser");
			
			navigateToUrl(browser, url);
			SeleniumUtils.setImplicitWait(60);
			
			//page initialization
			hp = new IbiboHomepage(driver);
			sp = new IbiboSearchPage(driver);
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			hp.closeLoginPopup();
			hp.verifyAndClickOnHotelsLink();
			String city = ExcelReader.getValueFromExcel(excelData, "city");
			hp.enterTheDestinationAndSelectCity(city);
			hp.selectTheNumberOfAdults();
			hp.clickOnSearchButton();
			sp.verifySearchResultsTitle();
			sp.applyRatingfilter();
			sp.verifySearchResultsRating(4);
			sp.applyPricefilter();
			sp.verifySearchResultsPrice(2000);
			
			assertion.assertAll();
	}
	
	
	
	@DataProvider(name="goibiboTestData")
	public Object[][] homePageDetail() throws Exception{
		
		
		PropUtils.loadProjectDetails();
		
		
		excelPath = System.getProperty("EXCEL_PATH")+"testDataHybrid.xlsx";
		sheetName = "goibibo_Config";
		
		
		Object[][] testobjArray = ExcelReader.getTableArray(excelPath,sheetName);
		
		return (testobjArray);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
	
		try {
			System.out.println(result.getStatus());
			
			  if(result.getStatus() == ITestResult.FAILURE) {
			  report.log(LogStatus.FAIL,"Failed Testcase is :"+result.getName()); 
			  }else if(result.getStatus() == ITestResult.SUCCESS) {
			  report.log(LogStatus.PASS,"Passed Testcase is :"+result.getName()); 
			  }else if(result.getStatus() == ITestResult.SKIP) {
			  report.log(LogStatus.SKIP,"Skipped Testcase is :"+result.getName()); 
			  }
			 
			
			driver.quit();
			report.log(LogStatus.PASS, "Browser closed successfully");
			extent.endTest(report);
			extent.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	
}
