package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class BookingSummaryScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@text='']")
	private WebElement backArrow;
	
	@FindBy(xpath="//android.widget.TextView[@text='Booking Summary']")
	private WebElement bookingSummaryLabel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='FormattedDate']")
	private WebElement bookingDateLabel;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='Time']")
	private WebElement bookingTimeLabel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='SearchItemFullName']")
	private WebElement doctorNameLabel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='SearchItemProviderName']")
	private WebElement providerNameLabel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='SearchItemDepartmentName']")
	private WebElement departmentNameLabel;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='BookingSumCashBtn']")
	private WebElement cash_in_FacilityButton;
	
	public String getBookingDateLabel() {
		return getWebElementText(bookingDateLabel);
	}

	public String getBookingTimeLabel() {
		return getWebElementText(bookingTimeLabel);
	}

	public String getDoctorNameLabel() {
		return getWebElementText(doctorNameLabel);
	}

	public String getProviderNameLabel() {
		return getWebElementText(providerNameLabel);
	}

	public String getDepartmentNameLabel() {
		return getWebElementText(departmentNameLabel);
	}
	
	public BookingSummaryScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
		
	}
	
	public void optcashinfacility() {
		scrollToText("Cash in facility");
		clickOnWebElement(cash_in_FacilityButton);
	}
	
	public BookingConfirmationScreen optcashinfacilityAndReturnBookingConfirmationScreen() {
		//scrollToText("Cash in facility");
		//scrollToBottom();
		Logger.info("starting to scroll");
		//perormScroll();
		scroll();
		Logger.info("scrolling completed successfully");
		clickOnWebElement(cash_in_FacilityButton);
		
		return new BookingConfirmationScreen(androidDriver);
	}

}
