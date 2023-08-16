package org.waseel.dallah.testautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class SecondSignUpScreen extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@text='Enter other details']")
	private WebElement enter_Other_DetailsLabel;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[1]")
	private WebElement full_NameTextBox;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[2]")
	private WebElement emailTextBox;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[3]")
	private WebElement phoneNumberTextBox;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='NationalityList']")
	private WebElement nationalityDropDown;
	
	@FindBy(xpath="//android.widget.Button[@content-desc='Choose Service']/android.view.ViewGroup/android.widget.ImageView")
	private WebElement nationalityDropDownArrow;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='DatePickerPressableInput']")
	private WebElement date_of_BirthTextbox;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='DatePickerPressableInputIcon']")
	private WebElement date_of_BirthCalenderIcon;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[4]")
	private WebElement passwordTextbox;
	
	@FindBy(xpath="//android.widget.TextView[@text='']")
	private WebElement passwordEye;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='SignupBtn']")
	private WebElement sign_UpButton;
	
	public SecondSignUpScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public boolean isControlOnSecondSignUpScreen()
	{
		boolean isControlOnSecondSignUpScreen = false;
		isControlOnSecondSignUpScreen = checkIfElementIsDisplayed(enter_Other_DetailsLabel);
		return isControlOnSecondSignUpScreen;
	}
	
	public String getFull_Name() {
		return getWebElementText(full_NameTextBox);
	}

	public void setFull_Name(String full_name) {
		if(createWebElement("xpath", "//android.widget.TextView[@text='Full Name']").isDisplayed())
		{
			System.out.println("SignupScreenTitle is visible");
			System.out.println("getWebElementText(full_NameTextBox) ::"+getWebElementText(full_NameTextBox));
			System.out.println("getWebElementText(full_NameTextBox).equals(\"Your full name\") :: "+getWebElementText(full_NameTextBox).equals("Your full name"));
			if(getWebElementText(full_NameTextBox).equals("Your full name"))
			{
				enterText(full_NameTextBox,full_name);
			}
			else
			{
				System.out.println("as name already exists, not overriding existing name");
			}
		}
	}

	public String getEmail() {
		return getWebElementText(emailTextBox);
	}

	public void setEmail(String email) {
		if(getWebElementText(emailTextBox).equals("Your email"))
		{
			enterText(emailTextBox,email);
		}
		else
		{
			Logger.error("as email already exists, not overriding existing email");
		}
	}

	public String getPhoneNumber(){
		return getWebElementText(phoneNumberTextBox);
	}

	public void setPhoneNumber(String phone_number) {
		if(getWebElementText(phoneNumberTextBox).equals("Your phone number"))
		{
			enterText(phoneNumberTextBox,phone_number);
		}
		else
		{
			Logger.error("as phone_number already exists, not overriding existing phone_number");
		}
	}

	public String getNationalityInDropDown() {
		return nationalityDropDown.getAttribute("text");
	}

	public void setNationality(String nationality) {
		if(getAttributeFromDom(nationalityDropDown,"text").equals("Your nationality"))
		{
			//clicking on drodown arrow
			clickOnWebElement(nationalityDropDownArrow);
			scrollToText(nationality);
			//clicking on the required nationality option
			clickOnWebElement(createWebElement("xpath", "//android.widget.TextView[@text='"+nationality+"']"));
			scrollToText("Sign In");
		}
		else
		{
			System.out.println("as nationality already exists, not overriding existing nationality");
		}
	}

	public String getDate_of_Birth() {
		return date_of_BirthTextbox.getAttribute("text");
	}

	public void setDate_of_Birth(String date_of_birth) throws Exception {
		
		if(getDate_of_Birth().equals("Your birth date"))
		{
			//clicking on calender icon
			clickOnWebElement(date_of_BirthCalenderIcon);
			
			//clicking on year in opened calender
			clickOnWebElement("xpath","/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]");
			
			String[] dateOfBirthComponents = date_of_birth.split(" ");
			
			String year = dateOfBirthComponents[2];
			String month = dateOfBirthComponents[1];
			String day = dateOfBirthComponents[0];
			
			//gathering list of appeared years 
			List<WebElement> YearWebElements = androidDriver.findElements(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']"));
			
			boolean isRequiredPresent = false;
			
			while(!isRequiredPresent)
			{
				for(WebElement currentYear : YearWebElements)
				{
					if(currentYear.getText().equals(year))
					{
						isRequiredPresent = true;
						clickOnWebElement(currentYear);
						break;
					}
				}
				if(!isRequiredPresent)
				{
					//clicking on the first year at index 0 in the appeared list 
					clickOnWebElement(YearWebElements.get(0));
					
					//clicking on year in opened calender i.e the first year at index 0 in the appeared list before
					clickOnWebElement("xpath","/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]");
					//gathering list of appeared years
					YearWebElements = androidDriver.findElements(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']"));
				}
				else
				{
					break;
				}
			}
			
			//reading current month
			WebElement currentMonthWebElement = createWebElement("xpath", "//android.view.View[@resource-id='android:id/month_view']");
			String[] array = currentMonthWebElement.findElements(By.className("android.view.View")).get(0).getAttribute("content-desc").split(" ");
			String currentMonth = array[1];
			Logger.info("currentMonth  :: "+currentMonth);
			
			while(!currentMonth.equals(month))
			{
				if(compareMonths(month,currentMonth)<0)
				{
					//clicking on previous month
					clickOnWebElement("xpath","//android.widget.ImageButton[@content-desc=\"Previous month\"]");
				}
				
				if(compareMonths(month,currentMonth)>0)
				{
					//clicking on next month
					clickOnWebElement("xpath","//android.widget.ImageButton[@content-desc=\"Next month\"]");
				}
				
				currentMonthWebElement = createWebElement("xpath", "//android.view.View[@resource-id='android:id/month_view']");
				array = currentMonthWebElement.findElements(By.className("android.view.View")).get(0).getAttribute("content-desc").split(" ");
				currentMonth = array[1];
			}
			
			List<WebElement> Dates = currentMonthWebElement.findElements(By.className("android.view.View"));
			for(WebElement date : Dates)
			{
				String[] fullDate = date.getAttribute("content-desc").split(" "); 
				System.out.println("day :: "+fullDate[0]);
				if(fullDate[0].equals(day))
				{
					clickOnWebElement(date);
					break;
				}
			}
			
			//clicking on OK button
			clickOnWebElement("xpath","//android.widget.Button[@text='OK']");

		}
		else
		{
			System.out.println("as date_of_birth already exists, not overriding existing date_of_birth");
		}
	}

	public String getPassword() {
		return passwordTextbox.getAttribute("text");
	}

	public void setPassword(String password) {
		if(getPassword().equals("Your password"))
		{
			enterText(passwordTextbox, password);
		}
		else
		{
			System.out.println("as password already exists, not overriding existing password");
		}
		
	}
	
	public void clickOnSignUpButton()
	{
		clickOnWebElement(sign_UpButton);
	}
	
	public void signUp(String full_name,String email,String phone_number,String nationality,String date_of_birth,String password) throws Exception
	{
		setFull_Name(full_name);
		setEmail(email);
		setPhoneNumber(phone_number);
		setNationality(nationality);
		setDate_of_Birth(date_of_birth);
		
		Thread.sleep(25000);
		setPassword(password);
		clickOnSignUpButton();
	}


}
