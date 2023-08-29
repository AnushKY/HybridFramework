package com.mindtree.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.pageLocators.HomePageLocator;
import com.mindtree.pageLocators.IbiboHomePageLocator;
import com.mindtree.utility.BaseTest;
import com.mindtree.utility.SeleniumUtils;
import com.relevantcodes.extentreports.LogStatus;

public class IbiboHomepage extends BaseTest{

	
WebDriver driver ;
	
	public IbiboHomepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath = IbiboHomePageLocator.lnk_hotels)
	WebElement lnk_hotels;
	
	
	@FindBy(xpath = IbiboHomePageLocator.btn_loginModalClose)
	WebElement btn_loginModalClose;
	
	
	
	public void verifyAndClickOnHotelsLink() {
		SeleniumUtils.visible(driver,lnk_hotels,report,"Verfying hotels link is visible");
		SeleniumUtils.click(driver, lnk_hotels, report, "Clicked on hotels link in the tab");
	}
	
	
	public void closeLoginPopup() {
		SeleniumUtils.explicitWait(driver, btn_loginModalClose, report, "waiting for the element to appear");
		SeleniumUtils.click(driver, btn_loginModalClose, report, "Clicked on close button in login modal");
		
	}
	
	@FindBy(xpath = IbiboHomePageLocator.txt_location)
	WebElement txt_location;
	
	
	
	public void enterTheDestinationAndSelectCity(String city) {
		
		SeleniumUtils.enterValue(driver, txt_location, city, report, "Destination value is filled");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement destOption= driver.findElement(By.xpath(".//ul[contains(@data-testid,'autosuggest-suggestions')]//li[1]"));
		SeleniumUtils.click(driver, destOption, report, "Selected the destination");
		
	}
	
	@FindBy(xpath = IbiboHomePageLocator.txt_guest)
	WebElement txt_guest;
	
	@FindBy(xpath = IbiboHomePageLocator.txt_adultCount)
	WebElement txt_adultCount;
	
	@FindBy(xpath = IbiboHomePageLocator.lnk_adultIncrease)
	WebElement lnk_adultIncrease;
	
	@FindBy(xpath = IbiboHomePageLocator.lnk_adultDecrease)
	WebElement lnk_adultDecrease;
	
	@FindBy(xpath = IbiboHomePageLocator.btn_Done)
	WebElement btn_Done;
	
	public void selectTheNumberOfAdults() {
		
		SeleniumUtils.click(driver, txt_guest, report, "Clicked on guest text box");	
		String numOfAdults = txt_adultCount.getText();
		if(numOfAdults.equalsIgnoreCase("2")) {
			SeleniumUtils.click(driver, lnk_adultDecrease, report, "Decreased the number of adults count to 1");
		}else if(numOfAdults.equalsIgnoreCase("0")) {
			SeleniumUtils.click(driver, lnk_adultIncrease, report, "Increased the number of adults count to 1");
		}
		
		//SeleniumUtils.scrollDown(driver, btn_Done);
		SeleniumUtils.click(driver, btn_Done, report, "Clicked on done button");
		
	}
	
	@FindBy(xpath = IbiboHomePageLocator.lnk_searchBtn)
	WebElement lnk_searchBtn;
	
	public void clickOnSearchButton() {
		//SeleniumUtils.scrollDown(driver, lnk_searchBtn);
		SeleniumUtils.click(driver, lnk_searchBtn, report, "Clicked on search button");
	}
	
	
	public void verifySearchResultsTitle() {
		
		List<WebElement> searchResults = driver.findElements(By.xpath(IbiboHomePageLocator.searchResults));
		String text = "";
		for(int i=1;i<=searchResults.size();i++) {
			text = driver.findElement(By.xpath("(.//div[@class='infinite-scroll-component ']//div[contains(@class,'HotelCardstyles__HotelNameWrapper')]/h4)["+i+"]")).getText();
			report.log(LogStatus.PASS, "Number "+i +" "+text);
			System.out.println("Number "+i +" "+text);
		}
		
	}
	
}
