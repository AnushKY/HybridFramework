package com.mindtree.pageLocators;

public class IbiboSearchPageLocator {

	public static final String lnk_firstHotel = "(.//div[@class='infinite-scroll-component ']//div[contains(@class,'HotelCardstyles__HotelNameWrapper')]/h4)[1]";
	public static final String searchResults = ".//div[@class='infinite-scroll-component ']//div[contains(@class,'HotelCardstyles__HotelNameWrapper')]/h4";
	public static final String btn_goStaysClose = ".//a[@class='close']";
	public static final String chk_upTo2000 = " .//span[contains(text(),'Upto ₹2000')]/preceding-sibling::*[local-name()='svg']";
	public static final String chk_payAtHotel = " .//span[contains(text(),'Pay At Hotel')]//preceding-sibling::*[local-name()='svg']";
	public static final String chk_4rating = "(.//span[contains(@class,'AverageReviewText') and contains(text(),'4')]/../../preceding-sibling::*[local-name()='svg'])[2]";
	public static final String searchPriceResults = ".//p[@itemprop='priceRange']";
	public static final String searchPriceRating = ".//span[@itemprop='ratingValue']";
}
