package com.mindtree.pageLocators;

public class IbiboHomePageLocator {

	public static final String lnk_hotels = ".//ul[@class='happy-nav']//a[contains(@href,'/hotels/')]";
	public static final String txt_location = ".//input[@data-testid='home-autosuggest-input']";
	public static final String txt_guest = ".//input[contains(@class,'CitySearchInput')]";
	public static final String txt_adultCount = ".//h4[@data-testid='adult-count']";
	public static final String lnk_adultIncrease = ".//span[@data-testid='button-adult-add']";
	public static final String lnk_adultDecrease = ".//span[@data-testid='button-adult-dec']";
	public static final String lnk_searchBtn = ".//button[@data-testid='searchHotelBtn']";
	public static final String btn_loginModalClose = ".//span[contains(@class,'icClose')]";
	public static final String searchResults = ".//div[@class='infinite-scroll-component ']//div[contains(@class,'HotelCardstyles__HotelNameWrapper')]/h4";
	public static final String txt_mblNumber = ".//input[contains(@class,'loginCont__input')]";
	public static final String btn_Done = ".//button[contains(text(),'Done')]";
	public static final String txt_checkInStart ="(.//h4[contains(@class,'CheckInDate')])[1]";
	public static final String txt_checkInEnd ="(.//h4[contains(@class,'CheckInDate')])[2]";
	public static final String btn_nxtArrow =".//span[@data-testid='calendarRightArrowBtn']";
	public static final String lbl_curCalMonth =".//span[@data-testid='currentCalendarMonthName']";
	public static final String btn_curMnthDates = "(.//div[contains(@class,'dcalendar-newstyles__CalendarMain')])[1]//ul[contains(@class,'dcalendar-newstyles__Date')]//li[contains(@class,'date_is_selectable_true')]/span";
	
	
}
