package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class FamilyMembersScreen extends AndroidActions {

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='FamilyMembersScreenAddMemberBtn']")
	private WebElement addFamilyMembers;
	
	public FamilyMembersScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public void navigateToAddFamilyMembersScreen()
	{
		clickOnWebElement(addFamilyMembers);
	}
	

	public AddFamilyMembersScreen navigateToAddFamilyMembersScreenAndReturnAddFamilyMembersScreen()
	{
		clickOnWebElement(addFamilyMembers);
		return new AddFamilyMembersScreen(androidDriver);
	}
	

}
