package com.mindtree.pageLocators;

public class IbiboHotelPageLocator {

	public static final String lnk_hotelTitle = ".//h1[@itemprop='name']";
	public static final String lnk_roomOptions =".//a[@data-testid='navigation-header-cta-#rooms']"; 
	public static final String section_roomOpt =".//div[@data-testid='detail-roomSelection-room']";
	public static final String lnk_Amenities =".//a[@data-testid='navigation-header-cta-#amenities']";
	public static final String section_amenities =".//div[@id='amenities']";
	public static final String lnk_guestReviews =".//a[@data-testid='navigation-header-cta-#guest-reviews']";
	public static final String section_guestRev =".//div[@id='guest-reviews']";
	public static final String lnk_propertyPolicies =".//a[@data-testid='navigation-header-cta-#hotel-policies']";
	public static final String section_propPolicies =".//div[@id='hotel-policies']";
	public static final String lnk_location =".//a[@data-testid='navigation-header-cta-#location']";
	public static final String section_location =".//*[@id='location']";
	public static final String lnk_QA =".//a[@data-testid='navigation-header-cta-#QnA']";
	public static final String section_QA =".//div[@id='QnA']";
	public static final String lnk_similarProp =".//a[@data-testid='navigation-header-cta-#similar-hotels']";
	public static final String section_similarProp =".//div[@id='similar-hotels']";
	public static final String btn_selRoom =".//button[@data-testid='selectRoomBtn']";
	public static final String txt_firstName =".//input[@data-guestdetailsinnerwrapid='first-name']";
	public static final String txt_lastName =".//input[@data-guestdetailsinnerwrapid='last-name']";
	public static final String txt_email =".//input[@data-guestdetailsinnerwrapid='guest-details-email']";
	public static final String txt_phoneNum =".//input[@data-guestdetailsinnerwrapid='guest-details-phone']";
	public static final String txt_billingAddr =".//input[@id='Billing Address']";
	public static final String txt_pincode =".//input[@id='Pincode']";
	public static final String chk_confirmation =".//input[@id='confirm_check']/..";
	public static final String btn_proceedToPay =".//span[contains(text(),'Proceed To Payment Options')]/..";
	public static final String btn_creditDebitOption =".//span[contains(text(),'Credit/Debit')]";
	public static final String txt_cardNum =".//input[@id='cardNumber']";
	public static final String txt_nameOnCard =".//input[@id='nameOnCard']";
	public static final String txt_cvv =".//input[@id='cardCvv']";
	public static final String txt_expMonth =".//input[@id='expiryMonth']";
	public static final String txt_expYear =".//input[@id='expiryYear']";
	public static final String opt_4 =".//input[@id='expiryMonth']/following-sibling::div//li[4]";
	public static final String opt_2025 =".//input[@id='expiryYear']/following-sibling::div//li[4]";
	public static final String lbl_invalidCard = ".//p[contains(text(),'Card number is not valid')]";
	
	
}
