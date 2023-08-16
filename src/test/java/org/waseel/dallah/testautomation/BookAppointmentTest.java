package org.waseel.dallah.testautomation;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.pageobjects.LandingScreen;
import org.waseel.dallah.testautomation.pageobjects.SearchScreen;
import org.waseel.dallah.testautomation.pageobjects.SignInScreen;
import org.waseel.dallah.testautomation.pageobjects.DoctorProfileScreen;
import org.waseel.dallah.testautomation.pageobjects.BookAppointmentScreen;
import org.waseel.dallah.testautomation.pageobjects.BookingConfirmationScreen;
import org.waseel.dallah.testautomation.pageobjects.BookingSummaryScreen;

public class BookAppointmentTest extends AndroidBaseTest{
	
	private LandingScreen landingScreen;
	private SignInScreen signInScreen;
	private SearchScreen searchScreen;
	private DoctorProfileScreen doctorProfileScreen;
	private BookAppointmentScreen bookAppointmentScreen;
	private BookingSummaryScreen bookingSummaryScreen;
	private BookingConfirmationScreen bookingConfirmationScreen;
	
	
	Properties  properties;
	FileInputStream fileInputStream;
	
	@BeforeMethod
	public void dontAllowAccessToLocation()
	{
		Logger.info("Before Mentod in Book Appointment Test : ");
		//don't allow access to location
		androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
		//clicking on "ok" in popup
		androidDriver.findElement(By.id("android:id/button1")).click();
	}
	
	@Test
	public void bookAppointmentTest() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
        Logger.info("Entered Book Appointment");
		landingScreen = new LandingScreen(androidDriver);
		//clicking on profile
		signInScreen = landingScreen.navigateToSignInScreenAndReturnSignInScreen();
		//checking if control is on sign in screen
		Assert.assertTrue(signInScreen.checkIfControlIsInSignInScreen(),"oops.... unfortunately the current control is not sign in screen. Aborting the test automation execution");
		
		properties = new Properties();
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\TestDataFiles\\SignInTestdata.properties");
		properties.load(fileInputStream);
		
		String Phone_numberOREmailORUsername = properties.getProperty("Phone_numberOREmailORUsername"); 
		String Password = properties.getProperty("Password");
		
		signInScreen.signIn(Phone_numberOREmailORUsername, Password);
		
		
		// do assertion here post login to check if teh control is navigated to right screen or not
		
		
		// clicking on "only this time"  to allow dallah to access device's location
		androidActions.clickOnWebElement("xpath", "//android.widget.Button[@text='ONLY THIS TIME']");
		//extracting users name from landing page
		String userNameFromLandingPagePostSignIn =  androidActions.getWebElementText(androidActions.createWebElement("xpath","(//android.widget.TextView[@resource-id='HomeScreenGreetings'])[2]"));
		softAssert.assertTrue(userNameFromLandingPagePostSignIn.equalsIgnoreCase(properties.getProperty("Name")));
		softAssert.assertAll("Asserting all from signInTest" );
		
		
		
		// from now bookAppointmentTest automations starts. 
		
		landingScreen.navigateToSearchPageFromeLandingPage();
		searchScreen = new SearchScreen(androidDriver);
		Assert.assertTrue(searchScreen.isControlOnSearchPage(),"oops.... unfortunately the current control is not search screen. Aborting the test automation execution");
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\TestDataFiles\\BookAppointmentTestdata.properties");
		properties.load(fileInputStream);
		
		String Doctor_Name = properties.getProperty("Doctor_Name");
		searchScreen.searchForDoctor(Doctor_Name);
		Thread.sleep(40000);
		doctorProfileScreen = searchScreen.selectDoctorAndReturnDoctorProfileScreen(Doctor_Name);
		bookAppointmentScreen = doctorProfileScreen.bookAppointmentAndReturnBookAppointmentScreen();
		bookingSummaryScreen = bookAppointmentScreen.bookAppointmentAndReturnBookingSummary();
		Thread.sleep(40000);
		bookingConfirmationScreen = bookingSummaryScreen.optcashinfacilityAndReturnBookingConfirmationScreen();
		
		Logger.info("Booked Appointment with Doctot : {}", Doctor_Name);
	}
	
	

}
