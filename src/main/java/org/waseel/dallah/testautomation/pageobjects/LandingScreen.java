package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import org.tinylog.Logger;

import io.appium.java_client.android.AndroidDriver;

public class LandingScreen extends AndroidActions{

	
	private AndroidDriver androidDriver;
	
	@FindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.View/android.view.View[5]/android.view.ViewGroup")
	private WebElement profile;
	
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='CustomInputTextInput']")
	private WebElement Find_a_doctor_or_hospitalSearchBar;
	
	public LandingScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public void navigateToSignInScreen()
	{
		clickOnWebElement(profile);
	}
	
	public SignInScreen navigateToSignInScreenAndReturnSignInScreen()
	{
		Logger.info("SignIn Screen ");
		clickOnWebElement(profile);
		return new SignInScreen(androidDriver);
	}
	
	public void navigateToSearchPageFromeLandingPage()
	{
		clickOnWebElement(Find_a_doctor_or_hospitalSearchBar);
	}
	
	public void openProfileDetails()
	{
		clickOnWebElement(profile);
	}
	
	public ProfileDetails openProfileDetailsAndReturnProfileDetailsScreen()
	{
		clickOnWebElement(profile);
		
		return new ProfileDetails(androidDriver);
	}
}
