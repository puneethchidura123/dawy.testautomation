package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class BookingConfirmationScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="(//android.widget.TextView[@index='3'])[1]")
	private WebElement booking_ConfirmedLabel;
	
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
	
	public BookingConfirmationScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
		
	}
	
	public String getBooking_ConfirmedLabel() {
		return getWebElementText(booking_ConfirmedLabel);
	}

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

}
