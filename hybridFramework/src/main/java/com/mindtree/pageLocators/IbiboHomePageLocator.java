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


}
