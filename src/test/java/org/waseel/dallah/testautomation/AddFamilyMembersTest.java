package org.waseel.dallah.testautomation;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.pageobjects.AddFamilyMembersScreen;
import org.waseel.dallah.testautomation.pageobjects.FamilyMembersScreen;
import org.waseel.dallah.testautomation.pageobjects.LandingScreen;
import org.waseel.dallah.testautomation.pageobjects.ProfileDetails;
import org.waseel.dallah.testautomation.pageobjects.SignInScreen;

public class AddFamilyMembersTest extends AndroidBaseTest {
	private LandingScreen landingScreen;
	private SignInScreen signInScreen;
	Properties  properties;
	FileInputStream fileInputStream;
	private FamilyMembersScreen familyMembersScreen;
	private AddFamilyMembersScreen addFamilyMembersScreen;
	private ProfileDetails profileDetails;
	
	@BeforeMethod
	public void dontAllowAccessToLocation()
	{
		//don't allow access to location
		androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
		//clicking on "ok" in popup
		androidDriver.findElement(By.id("android:id/button1")).click();
	}
	
	@Test
	public void addFamilyMembersTest() throws Exception
	{
        Logger.info("Entered ADD Family Members");
		SoftAssert softAssert = new SoftAssert();
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
		
		//signInScreen = landingScreen.navigateToSignInScreenAndReturnSignInScreen();

		properties = new Properties();
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\TestDataFiles\\AddMemberTestData.properties");
		properties.load(fileInputStream);
		
		profileDetails = landingScreen.openProfileDetailsAndReturnProfileDetailsScreen();
		familyMembersScreen = profileDetails.navigateToFamilyMembersScreenAndReturnFamilyMembersScreen();
		addFamilyMembersScreen = familyMembersScreen.navigateToAddFamilyMembersScreenAndReturnAddFamilyMembersScreen();
		String nationalId = properties.getProperty("National_Id/Iqama_ID_number"); 
		String Full_Name = properties.getProperty("Full_Name");

		String Nationality = properties.getProperty("Nationality");
		String Date_of_Birth = properties.getProperty("Date_of_Birth");
		addFamilyMembersScreen.addMember(nationalId, Full_Name, Nationality, Date_of_Birth);
        Logger.info("Hello World TinyLog! Added");

		addFamilyMembersScreen.clickOnOkButton();
		
		Assert.assertEquals(addFamilyMembersScreen.getSuccessMessage().trim(), "Add family member successfully");

		
	}
	
}
