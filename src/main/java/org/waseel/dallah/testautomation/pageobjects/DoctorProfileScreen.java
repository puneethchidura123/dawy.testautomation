package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class DoctorProfileScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='doctorProfileBookBtn']")
	private WebElement bookAppointmentButton;
	
	public DoctorProfileScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public void bookAppointment()
	{
		clickOnWebElement(bookAppointmentButton);
	}
	
	public BookAppointmentScreen bookAppointmentAndReturnBookAppointmentScreen()
	{
		clickOnWebElement(bookAppointmentButton);
		return new BookAppointmentScreen(androidDriver);
	}

}
