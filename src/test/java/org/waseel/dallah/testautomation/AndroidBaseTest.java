package org.waseel.dallah.testautomation;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.tinylog.Logger;
import org.waseel.dallah.testautomation.pageobjects.LandingScreen;
import org.waseel.dallah.testautomation.reusables.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidBaseTest {
	
	

	public AndroidDriver androidDriver;
	AndroidActions androidActions;

	@BeforeTest
	public void setUp() throws Exception
	{
        Logger.info("Before Test");
		Properties  properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\configuration_data.properties");
		properties.load(fileInputStream);

		UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
		uiAutomator2Options.setDeviceName(properties.getProperty("Android_Device_Name"));
		uiAutomator2Options.setApp(System.getProperty("user.dir")+"\\Resources\\Android\\Dawy_0807-1.apk");

		uiAutomator2Options.setAvdLaunchTimeout(Duration.ofSeconds(25));

		androidDriver = new AndroidDriver(new URL(properties.getProperty("Appium_Server_Url")),uiAutomator2Options);
		androidActions = new AndroidActions(androidDriver);
		androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("Implicit_Wait_In_Seconds"))));
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("after test");
	}

	@BeforeMethod
	public void dontAllowAccessToLocation()
	{
		//don't allow access to location
		androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
		//clicking on "ok" in popup
		androidDriver.findElement(By.id("android:id/button1")).click();
	}
	
	public LandingScreen dontAllowAccessToLocationAndReturnLandingScreen()
	{
		//don't allow access to location
		androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
		//clicking on "ok" in popup
		androidDriver.findElement(By.id("android:id/button1")).click();
		return new LandingScreen(androidDriver);
	}
	
	

}
