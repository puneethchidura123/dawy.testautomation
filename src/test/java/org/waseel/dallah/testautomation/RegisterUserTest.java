package org.waseel.dallah.testautomation;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.pageobjects.FirstSignUpScreen;
import org.waseel.dallah.testautomation.pageobjects.LandingScreen;
import org.waseel.dallah.testautomation.pageobjects.SecondSignUpScreen;
import org.waseel.dallah.testautomation.pageobjects.SignInScreen;
import org.waseel.dallah.testautomation.pageobjects.SignUpSucessPopup;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;
import java.util.Properties;
import java.net.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class RegisterUserTest extends AndroidBaseTest{


	private LandingScreen landingScreen;
	private SignInScreen signInScreen;
	private FirstSignUpScreen firstSignUpScreen;
	private SecondSignUpScreen secondSignUpScreen;
	private SignUpSucessPopup signUpSucessPopup;

	Properties  properties;
	FileInputStream fileInputStream;



	@Test
	public void signUp() throws Exception
	{
		Logger.info("signup test");
		Logger.info("starting the signup test");

		landingScreen = new LandingScreen(androidDriver);
		//click in on profile
		signInScreen = landingScreen.navigateToSignInScreenAndReturnSignInScreen();
		// Click on Register now
		firstSignUpScreen = signInScreen.navigateToSignUpScreenAndReturnFirstSignUpScreen();


		properties = new Properties();
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\TestDataFiles\\SignUpTestdata.properties");
		properties.load(fileInputStream);

		//From now on we will automate on sign up screen
		String National_IdIqama_ID_number = properties.getProperty("National_Id/Iqama_ID_number"); 
		String Full_Name = properties.getProperty("Full_Name");
		String Email = properties.getProperty("Email");
		String Phone_number= properties.getProperty("Phone_number");
		String Nationality = properties.getProperty("Nationality");
		String Date_of_Birth = properties.getProperty("Date_of_Birth");
		String Password = properties.getProperty("Password");

		//Entering nationalId or Iqama Id Number
		firstSignUpScreen.setNationIdORIqamaNumber(National_IdIqama_ID_number);
		secondSignUpScreen = firstSignUpScreen.clickNextAndProceedToSecondSignUpScreenAndReturnSecondSignUpScreen();
		secondSignUpScreen.signUp(Full_Name, Email, Phone_number, Nationality, Date_of_Birth, Password);
		
		signUpSucessPopup = new SignUpSucessPopup(androidDriver);
		signUpSucessPopup.clickOkButton();
		Thread.sleep(50000);
	}



}
