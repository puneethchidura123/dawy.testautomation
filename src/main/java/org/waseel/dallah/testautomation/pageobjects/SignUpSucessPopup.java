package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class SignUpSucessPopup extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='android:id/alertTitle']")
	private WebElement alertTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	private WebElement alertMessage;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	private WebElement okButton;
	
	public SignUpSucessPopup(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public String getAlertTitle() {
		return alertTitle.getText();
	}

	public String getAlertMessage() {
		return alertMessage.getText();
	}

	public void clickOkButton() {
		clickOnWebElement(okButton);
	}

	public String getSuccessMessageFromPopup() {
		// TODO Auto-generated method stub
		return getWebElementText(alertMessage).trim();
	}


}
