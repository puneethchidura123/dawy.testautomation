package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class BookAppointmentScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@text='']")
	private WebElement backArrow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='CalenderScreenHeaderTitle']")
	private WebElement bookAppointmentLabel;
	
	@FindBy(xpath="//android.widget.TextView[@text='Select Time']")
	private WebElement selectTimeDropdown;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='CalenderScreenFooterBtn']")
	private WebElement confirm_bookingButton;
	
	public BookAppointmentScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public void selectAppointmentTime()
	{
		clickOnWebElement(selectTimeDropdown);
		//selecting 3:00 PM
		clickOnWebElement("xpath","//android.widget.TextView[@text='8:15 PM']");
	}
	
	public void confirmBooking()
	{
		clickOnWebElement(confirm_bookingButton);
	}
	
	public void bookAppointment()
	{
		selectAppointmentTime();
		confirmBooking();
	}
	
	
	public BookingSummaryScreen bookAppointmentAndReturnBookingSummary()
	{
		selectAppointmentTime();
		confirmBooking();
		
		return new BookingSummaryScreen(androidDriver);
	}
	

}
