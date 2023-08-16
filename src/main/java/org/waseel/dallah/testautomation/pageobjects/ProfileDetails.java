package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class ProfileDetails extends AndroidActions {

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@text='Family members']")
	private WebElement familyMembers;
	
	public ProfileDetails(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public void navigateToFamilyMembersScreen()
	{
		clickOnWebElement(familyMembers);
	}
	
	public FamilyMembersScreen navigateToFamilyMembersScreenAndReturnFamilyMembersScreen()
	{
		clickOnWebElement(familyMembers);
		return new FamilyMembersScreen(androidDriver);
	}
	
}
