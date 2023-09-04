package com.mindtree.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.pageLocators.IbiboHomePageLocator;
import com.mindtree.pageLocators.IbiboHotelPageLocator;
import com.mindtree.pageLocators.IbiboSearchPageLocator;
import com.mindtree.utility.BaseTest;
import com.mindtree.utility.SeleniumUtils;
import com.relevantcodes.extentreports.LogStatus;

public class IbiboSearchPage extends  BaseTest {

	WebDriver driver ;

	public IbiboSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	public void verifySearchResultsTitle() {

		List<WebElement> searchResults = driver.findElements(By.xpath(IbiboSearchPageLocator.searchResults));
		String text = "";
		report.log(LogStatus.PASS, "Number of initial loaded search results in the page is "+searchResults.size());
		for(int i=1;i<=searchResults.size();i++) {
			text = driver.findElement(By.xpath("(.//div[@class='infinite-scroll-component ']//div[contains(@class,'HotelCardstyles__HotelNameWrapper')]/h4)["+i+"]")).getText();
			report.log(LogStatus.PASS, "Number "+i +" "+text);
			System.out.println("Number "+i +" "+text);
		}
	}


	
	@FindBy(xpath = IbiboSearchPageLocator.btn_goStaysClose)
	WebElement btn_goStaysClose;
	
	@FindBy(xpath = IbiboSearchPageLocator.lnk_firstHotel)
	WebElement lnk_firstHotel;
	
	public String firstHotelName = "";

	public void clickOnFirstSearchResult() {
		firstHotelName = SeleniumUtils.getText(driver, lnk_firstHotel, report, "First hotel name");
		SeleniumUtils.click(driver, btn_goStaysClose, report, "Clicked on close button in GoStays popup");
		SeleniumUtils.click(driver, lnk_firstHotel, report, "Clicked on first hotels link in the search result");
	}
	
	@FindBy(xpath = IbiboHotelPageLocator.lnk_hotelTitle)
	WebElement lnk_hotelTitle;
	
	public void verifyTheHotelTitle() {
		assertion.assertEquals(firstHotelName,SeleniumUtils.getText(driver, lnk_hotelTitle, report, "Verifying the hotel title"));
	}
	
	@FindBy(xpath = IbiboHotelPageLocator.lnk_roomOptions)
	WebElement lnk_roomOptions;
	
	@FindBy(xpath = IbiboHotelPageLocator.section_roomOpt)
	WebElement section_roomOpt;
	
	public void verifyRoomOptions() {
		SeleniumUtils.click(driver, lnk_roomOptions, report, "clicked on Room Options");
		SeleniumUtils.visible(driver, section_roomOpt, report, "Room options section is displayed");
		report.log(LogStatus.PASS,report.addBase64ScreenShot(SeleniumUtils.captureScreen(driver, "room_image")));
	
	}
	
	@FindBy(xpath = IbiboHotelPageLocator.lnk_location)
	WebElement lnk_location;
	
	@FindBy(xpath = IbiboHotelPageLocator.section_location)
	WebElement section_location;
	
	public void verifyLocation() {
		SeleniumUtils.click(driver, lnk_location, report, "clicked on Locations link in tab");
		SeleniumUtils.visible(driver, section_location, report, "Location section is displayed");
	}
	
	@FindBy(xpath = IbiboHotelPageLocator.lnk_guestReviews)
	WebElement lnk_guestReviews;
	
	@FindBy(xpath = IbiboHotelPageLocator.section_guestRev)
	WebElement section_guestRev;
	
	public void verifyguestReview() {
		SeleniumUtils.click(driver, lnk_guestReviews, report, "clicked on guet review Option in the tab");
		SeleniumUtils.visible(driver, section_guestRev, report, "guest review section is displayed");
	}
	
	@FindBy(xpath = IbiboHotelPageLocator.lnk_QA)
	WebElement lnk_QA;
	
	@FindBy(xpath = IbiboHotelPageLocator.section_QA)
	WebElement section_QA;
	
	public void verifyQA() {
		SeleniumUtils.click(driver, lnk_QA, report, "clicked on QA Options in the tab");
		//SeleniumUtils.visible(driver, section_QA, report, "Qusetion & answers section is displayed");
	}
	
	@FindBy(xpath = IbiboHotelPageLocator.lnk_propertyPolicies)
	WebElement lnk_propertyPolicies;
	
	@FindBy(xpath = IbiboHotelPageLocator.section_propPolicies)
	WebElement section_propPolicies;
	
	public void verifyPropertyPolicies() {
		SeleniumUtils.click(driver, lnk_propertyPolicies, report, "clicked on Room Options");
		SeleniumUtils.visible(driver, section_propPolicies, report, "Room options section is displayed");
	}
	
	public void verifySearchPageFunctionality() {
		
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		
		Iterator<String> itr = s.iterator();
		
		while(itr.hasNext()) {
			String childWindow = itr.next();
			if(!childWindow.equalsIgnoreCase(parent)) {
				driver.switchTo().window(childWindow);
				verifyTheHotelTitle();
				verifyRoomOptions();
				verifyLocation();
				verifyguestReview();
				verifyQA();
				verifyPropertyPolicies();
				driver.close();
			}
		}
		driver.switchTo().window(parent);
		
	}
	
	
	@FindBy(xpath = IbiboSearchPageLocator.chk_4rating)
	WebElement chk_4rating;
	
	public void applyRatingfilter() {
		SeleniumUtils.visible(driver, chk_4rating, report, "4 + rating filter is displayed");
		SeleniumUtils.click(driver, chk_4rating, report, "4 + rating filter is selected");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifySearchResultsRating(int rating) {
		List<WebElement> searchResults = driver.findElements(By.xpath(IbiboSearchPageLocator.searchPriceRating));
		String text = "";
		report.log(LogStatus.PASS, "Number of initial loaded search results in the page is "+searchResults.size());
		for(int i=1;i<=searchResults.size();i++) {
			text = driver.findElement(By.xpath("(.//span[@itemprop='ratingValue'])["+i+"]")).getAttribute("content");
			float actRating = Float.parseFloat(text);
			if(actRating>=rating) {
				report.log(LogStatus.PASS, "Actual rating of the room "+actRating+" is greater than expected rating of the room "+rating);
			}else {
				report.log(LogStatus.FAIL, "Actual rating of the room "+actRating+" is lesser than expected rating of the room "+rating);
				assertion.assertEquals(actRating, rating);
			}
		}
	}
	
	@FindBy(xpath = IbiboSearchPageLocator.chk_upTo2000)
	WebElement chk_upTo2000;
	
	public void applyPricefilter() {
		SeleniumUtils.visible(driver, chk_upTo2000, report, "Up to 2000 price filter is displayed");
		SeleniumUtils.click(driver, chk_upTo2000, report, "Up to 2000 price filter is selected");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifySearchResultsPrice(int price) {
		List<WebElement> searchResults = driver.findElements(By.xpath(IbiboSearchPageLocator.searchPriceResults));
		String text = "";
		report.log(LogStatus.PASS, "Number of initial loaded search results in the page is "+searchResults.size());
		for(int i=1;i<=searchResults.size();i++) {
			text = driver.findElement(By.xpath("(.//p[@itemprop='priceRange'])["+i+"]")).getText();
			int actPrice = Integer.parseInt(text);
			if(actPrice<=price) {
				report.log(LogStatus.PASS, "Actual Price of the room "+actPrice+" is lesser than expected Price of the room "+price);
			}else {
				report.log(LogStatus.FAIL, "Actual Price of the room "+actPrice+" is greater than Actual Price of the room "+price);
				assertion.assertEquals(actPrice, price);
			}
		}
	}
	
	public void filterWithPriceRangeAndVerifyTheResults() {
		SeleniumUtils.click(driver, lnk_propertyPolicies, report, "clicked on Room Options");
		SeleniumUtils.visible(driver, section_propPolicies, report, "Room options section is displayed");
	}
	
	


}