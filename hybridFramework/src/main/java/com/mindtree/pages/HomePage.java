package com.mindtree.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import com.mindtree.selenium.Element;
import com.mindtree.utility.BaseTest;
import com.mindtree.utility.SeleniumUtils;
import com.relevantcodes.extentreports.ExtentTest;

import com.mindtree.pageLocators.HomePageLocator;

public class HomePage extends BaseTest{

	
WebDriver driver ;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath = HomePageLocator.lnk_matches)
	WebElement lnk_matches;
	
	@FindBy(xpath = HomePageLocator.lnk_logo)
	WebElement lnk_logo;
	
	public void verifyMatchesLnk() {
		SeleniumUtils.visible(driver,lnk_matches,report,"Verfying Matches link");
	}
	
	public void verifyLogoLnk() {
		SeleniumUtils.visible(driver,lnk_logo,report,"Verfying Logo");
	}
	
	
	
}
