package org.waseel.dallah.testautomation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class SignInScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@text='']")
	private WebElement backArrow;
	
	@FindBy(xpath="//android.widget.TextView[@text='Sign in to continue']")
	private WebElement sign_in_to_continueLabel;
	
	@FindBy(xpath="//android.widget.TextView[@text='Enter your details']")
	private WebElement enter_your_detailsLabel;
	
	@FindBy(xpath="//android.widget.TextView[@text='Phone number / Email / Username']")
	private WebElement phone_numberOREmailORUsernameLabel;

	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[1]")
	private WebElement phone_numberOREmailORUsernameTextbox;
	
	@FindBy(xpath="//android.widget.TextView[@text='Password']")
	private WebElement passwordLabel;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[2]")
	private WebElement passwordTextbox;
	
	@FindBy(xpath="//android.widget.TextView[@text='']")
	private WebElement passwordEye;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='LoginBtn']")
	private WebElement sign_InButton;
	
	@FindBy(xpath="//android.widget.TextView[@text='Not a member?']")
	private WebElement not_a_memberLabel;
	
	@FindBy(xpath="//android.widget.TextView[@text='Register now']")
	private WebElement register_nowLink;
	
	
	public SignInScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public String getPhone_numberOREmailORUsername() {
		return getWebElementText(phone_numberOREmailORUsernameTextbox);
	}


	public void setPhone_numberOREmailORUsernameTextbox(String phone_numberOREmailORUsername) {
		enterText(phone_numberOREmailORUsernameTextbox, phone_numberOREmailORUsername);
	}


	public String getPassword() {
		return getWebElementText(passwordTextbox);
	}


	public void setPassword(String password) {
		enterText(passwordTextbox, password);
	}
	
	
	public boolean checkIfControlIsInSignInScreen()
	{
		boolean isBackArrowDisplayed = checkIfElementIsDisplayed(backArrow);
		boolean isBackArrowClickable = checkIfElementIsEnabled(backArrow);
		boolean isSign_in_to_continueLabelDisplayed = checkIfElementIsDisplayed(sign_in_to_continueLabel);
		boolean isEnter_your_detailsLabelDisplayed = checkIfElementIsDisplayed(enter_your_detailsLabel);
		boolean isPhone_numberOREmailORUsernameLabelDisplayed = checkIfElementIsDisplayed(phone_numberOREmailORUsernameLabel);
		boolean ispasswordLabelDisplayed = checkIfElementIsDisplayed(passwordLabel);
		boolean isSign_InButtonDisplayed = checkIfElementIsDisplayed(sign_InButton);
		boolean isNot_a_memberLabelDisplayed = checkIfElementIsDisplayed(not_a_memberLabel);
		boolean isregister_nowLinkDisplayed = checkIfElementIsDisplayed(register_nowLink);
		
		return (isBackArrowDisplayed & isBackArrowClickable & isSign_in_to_continueLabelDisplayed & isEnter_your_detailsLabelDisplayed
				& isPhone_numberOREmailORUsernameLabelDisplayed & ispasswordLabelDisplayed & isSign_InButtonDisplayed & isNot_a_memberLabelDisplayed
				& isregister_nowLinkDisplayed);
	}
	
	public void signIn(String phone_numberOREmailORUsername,String password)
	{
		setPhone_numberOREmailORUsernameTextbox(phone_numberOREmailORUsername);
		setPassword(password);
		clickOnWebElement(sign_InButton);
	}
	
	
	public void navigateToSignUpScreen()
	{
		clickOnWebElement(register_nowLink);
	}
	
	public FirstSignUpScreen navigateToSignUpScreenAndReturnFirstSignUpScreen()
	{
		clickOnWebElement(register_nowLink);
		return new FirstSignUpScreen(androidDriver);
	}
	
	public boolean isRegister_nowLinkVisible()
	{
		return checkIfElementIsDisplayed(register_nowLink);
	}

}
