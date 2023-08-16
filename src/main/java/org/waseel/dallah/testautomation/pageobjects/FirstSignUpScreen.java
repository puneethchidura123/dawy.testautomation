package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class FirstSignUpScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='CustomFormControlInput']")
	private WebElement nationIdORIqamaNumber;
	
	
	@FindBy(xpath="//android.widget.Button[@resource-id='SignupScreenNextBtn']")
	private WebElement nextButton;
	
	public FirstSignUpScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public boolean isControlInFirstSignUpScreen()
	{
		return checkIfElementIsDisplayed(nationIdORIqamaNumber) & checkIfElementIsDisplayed(nextButton);
	}
	
	public String getNationIdORIqamaNumber() {
		String NationIdORIqamaNumber = null;
		if(isControlInFirstSignUpScreen())
		{
			NationIdORIqamaNumber = nationIdORIqamaNumber.getText();
		}
		return nationIdORIqamaNumber.getText();
	}

	public void setNationIdORIqamaNumber(String NationIdORIqamaNumber) {
		if(isControlInFirstSignUpScreen())
		{
			Logger.info("getWebElementText(nationIdORIqamaNumber)  :: {}", getAttributeFromDom(nationIdORIqamaNumber,"text"));
			if(getWebElementText(nationIdORIqamaNumber).equals("Your national ID / Iqama ID number"))
			{
				enterText(nationIdORIqamaNumber, NationIdORIqamaNumber);
			}
			else
			{
				Logger.error("as NationIdORIqamaNumber already exists, not overriding existing NationIdORIqamaNumber");
			}
		}
	}
	
	
	
	public void clickNextAndProceedToSecondSignUpScreen()
	{
		clickOnWebElement(nextButton);
	}
	
	public SecondSignUpScreen clickNextAndProceedToSecondSignUpScreenAndReturnSecondSignUpScreen()
	{
		clickOnWebElement(nextButton);
		return new SecondSignUpScreen(androidDriver);
	}

	public boolean isNationIdORIqamaNumberTextFieldDisplayed()
	{
		return checkIfElementIsDisplayed(nationIdORIqamaNumber);
	}
	
	public boolean isNextButtonAvailable()
	{
		return checkIfElementIsDisplayed(nextButton);
	}
}
