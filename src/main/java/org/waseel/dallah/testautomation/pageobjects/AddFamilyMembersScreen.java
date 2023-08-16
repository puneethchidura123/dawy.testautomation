package org.waseel.dallah.testautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class AddFamilyMembersScreen extends AndroidActions {
	private AndroidDriver androidDriver;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[1]")
	private WebElement full_NameTextBox;
	
	@FindBy(xpath="(//android.widget.EditText[@resource-id='CustomFormControlInput'])[2]")
	private WebElement national_id;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='NationalityList']")
	private WebElement nationalityDropDown;
	
	@FindBy(xpath="//android.widget.Button[@content-desc='Choose Service']/android.view.ViewGroup/android.widget.ImageView")
	private WebElement nationalityDropDownArrow;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='DatePickerPressableInput']")
	private WebElement date_of_BirthTextbox;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='DatePickerPressableInputIcon']")
	private WebElement date_of_BirthCalenderIcon;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='SaveDetailsBtn']")
	private WebElement saveDetails;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='SaveDetailsBtn']")
	private WebElement successMessage;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	private WebElement successButton;
	
	public AddFamilyMembersScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}

	
	public void setFull_Name(String full_name) {
		if(createWebElement("xpath", "//android.widget.EditText[@text='Your full name']").isDisplayed())
		{
//			System.out.println("SignupScreenTitle is visible");
//			System.out.println("getWeb?ElementText(full_NameTextBox) ::"+getWebElementText(full_NameTextBox));
//			System.out.println("getWebElementText(full_NameTextBox).equals(\"Your full name\") :: "+getWebElementText(full_NameTextBox).equals("Your full name"));
			Logger.info("SignupScreenTitle is visible");
			Logger.debug("getWebElementText(full_NameTextBox) :: {}", getWebElementText(full_NameTextBox));
			Logger.info("getWebElementText(full_NameTextBox).equals(\"Your full name\") :: {}", getWebElementText(full_NameTextBox).equals("Your full name"));
			Logger.error("Just test Error Log");
			if(getWebElementText(full_NameTextBox).equals("Your full name"))
			{
				enterText(full_NameTextBox,full_name);
			}
			else
			{
				Logger.error("as name already exists, not overriding existing name");
//				System.out.println("as name already exists, not overriding existing name");
			}
		}
	}
	
	
	public void setNationality(String nationality) {
		if(getAttributeFromDom(nationalityDropDown,"text").equals("Your nationality"))
		{
			//clicking on drodown arrow
			clickOnWebElement(nationalityDropDownArrow);
			scrollToText(nationality);
			//clicking on the required nationality option
			clickOnWebElement(createWebElement("xpath", "//android.widget.TextView[@text='"+nationality+"']"));
//			scrollToText("Sign In");
			System.out.println("Nation set");
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
			System.out.println("currentMonth  :: "+currentMonth);
			
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

	

	public String getNationIdORIqamaNumber() {
		return national_id.getText();
	}

	public void setNational_id(String NationIdORIqamaNumber) {

			System.out.println("getWebElementText(nationIdORIqamaNumber)  :: "+getAttributeFromDom(national_id,"text"));
			if(getWebElementText(national_id).equals("Your national ID / Iqama ID number"))
			{
				enterText(national_id, NationIdORIqamaNumber);
			}
		
	}
	
	
	public void saveDetails(String nationaliId, String full_name,String nationality,String date_of_birth) throws Exception
	{
		setFull_Name(full_name);
		setDate_of_Birth(date_of_birth);
		setNational_id(nationaliId);
		setNationality(nationality);
	}
	
	public void addMember(String nationaliId, String full_name,String nationality,String date_of_birth) throws Exception
	{
		setFull_Name(full_name);
		setDate_of_Birth(date_of_birth);
		setNational_id(nationaliId);
		setNationality(nationality);
		clickOnSaveButton();
	}
	
	public void clickOnSaveButton()
	{
		clickOnWebElement(saveDetails);
	}
	
	public FamilyMembersScreen clickOnOkButton()
	{
		clickOnWebElement(successButton);
		return new FamilyMembersScreen(androidDriver);
	}
	
	public String getSuccessMessage()
	{
		return successMessage.getText();
	}
	
}
