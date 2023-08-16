package org.waseel.dallah.testautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class SearchScreen  extends AndroidActions{

	private AndroidDriver androidDriver;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='NavigationBarTitle']")
	private WebElement searchLabel;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='CustomInputLeftElement']")
	private WebElement searchMagnifierIcon;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='CustomInputTextInput']")
	private WebElement find_a_doctor_or_hospitalTextbox;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='CustomInputRightElement']")
	private WebElement filtersIcon;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='DoctorsTabBtn']")
	private WebElement doctorsButton;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='HospitalsTabBtn']")
	private WebElement hospitalsButton;
	
	public SearchScreen(AndroidDriver androidDriver) {
		super(androidDriver);
		this.androidDriver=androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	public boolean isControlOnSearchPage()
	{
		boolean isControlOnSearchPage = false;
		boolean issearchLabelVisible = checkIfElementIsDisplayed(searchLabel);
		boolean issearchMagnifierIconVisible = checkIfElementIsDisplayed(searchMagnifierIcon);
		boolean isfind_a_doctor_or_hospitalTextboxVisible = checkIfElementIsDisplayed(find_a_doctor_or_hospitalTextbox);
		boolean isfiltersIconVisible = checkIfElementIsDisplayed(filtersIcon);
		boolean isdoctorsButtonVisible = checkIfElementIsDisplayed(doctorsButton);
		boolean ishospitalsButtonVisible = checkIfElementIsDisplayed(hospitalsButton);
		
		isControlOnSearchPage = (issearchLabelVisible & issearchMagnifierIconVisible & isfind_a_doctor_or_hospitalTextboxVisible & isfiltersIconVisible
				& isdoctorsButtonVisible & ishospitalsButtonVisible);
		
		return isControlOnSearchPage;
	}
	
	public void searchForDoctor(String doctor_name)
	{
		if(isDoctorAvailableInList(doctor_name))
		{
			
		}
		else
		{
			enterText(find_a_doctor_or_hospitalTextbox, doctor_name);
			hitEnterFromKeyboard();
			//write code to make sure the doctor record is available in the search options list
		}
		
	}
	
	public boolean isDoctorAvailableInList(String doctor_name)
	{
		boolean isDoctorAvailableInList = false;
		List<WebElement> DoctorsRecords = androidDriver.findElements(By.xpath("//android.view.ViewGroup[@resource-id='SearchItemTouchable']"));
		for(WebElement doctorsRecord : DoctorsRecords)
		{
			WebElement CurrentDoctorRecord = doctorsRecord;
			String currentDoctorName = CurrentDoctorRecord.findElement(By.xpath("//android.widget.TextView[@resource-id='SearchItemFullName']")).getText();
			if(currentDoctorName.equals(doctor_name))
			{
				isDoctorAvailableInList = true;
			}
		}
		return isDoctorAvailableInList;
	}
	
	public void selectDoctor(String doctor_name)
	{
		List<WebElement> DoctorsRecords = androidDriver.findElements(By.xpath("//android.view.ViewGroup[@resource-id='SearchItemTouchable']"));
		for(WebElement doctorsRecord : DoctorsRecords)
		{
			WebElement CurrentDoctorRecord = doctorsRecord;
			String currentDoctorName = CurrentDoctorRecord.findElement(By.xpath("//android.widget.TextView[@resource-id='SearchItemFullName']")).getText();
			if(currentDoctorName.equals(doctor_name))
			{
				//clicking on the required doctor arrow
				clickOnWebElement(CurrentDoctorRecord.findElement(By.xpath("android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")));
			}
		}
	}
	
	public DoctorProfileScreen selectDoctorAndReturnDoctorProfileScreen(String doctor_name) throws Exception
	{
		Thread.sleep(10000);
		
		List<WebElement> DoctorsRecords = androidDriver.findElements(By.xpath("//android.view.ViewGroup[@resource-id='SearchItemTouchable']"));
		for(WebElement doctorsRecord : DoctorsRecords)
		{
			WebElement CurrentDoctorRecord = doctorsRecord;
			String currentDoctorName = null ;
			try
			{
				currentDoctorName = CurrentDoctorRecord.findElement(By.xpath("//android.widget.TextView[@resource-id='SearchItemFullName']")).getText();
			}
			catch(Exception e)
			{
				System.out.println("exception occured while getting current doctor name");
			}
			if(currentDoctorName.equals(doctor_name))
			{
				//clicking on the required doctor arrow
				clickOnWebElement(CurrentDoctorRecord.findElement(By.xpath("android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")));
			}
		}
		
		return new DoctorProfileScreen(androidDriver);
	}

}
