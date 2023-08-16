package org.waseel.dallah.testautomation.reusables;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends GenericUIUtilites{
	
	IOSDriver iOSDriver;
	
	public IOSActions(IOSDriver iOSDriver)
	{
		super(iOSDriver);
		this.iOSDriver=iOSDriver;
	}
	
	// below are the generic utilites related to ios mobile app
	//start
	public void longPressOnIosApp(IOSDriver iOSDriver,WebElement webElement,long duration)
	{
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("element", ((RemoteWebElement)webElement).getId());
		parameters.put("duration",duration);
		iOSDriver.executeScript("mobile:touchAndHold",parameters);
	}
	
	public void scrollOnIosApp(IOSDriver iOSDriver,WebElement webElement)
	{
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("element", ((RemoteWebElement) webElement).getId());
		parameters.put("direction","down");
		parameters.put("toVisible",true);
		iOSDriver.executeScript("mobile:scroll",parameters);
	}
	
	public void moveSlider(IOSDriver iOSDriver,WebElement webElement,int percentage)
	{
		
		//example for slider :: https://iosexample.com/tag/slider/ 
		// percentage should be 0 to 1 range (0,0.25,0.5,0.75,1)
		webElement.sendKeys(percentage+"%");
	}
	
	public void swipeOnAElement(IOSDriver iOSDriver,WebElement webElement,String direction)
	{
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("direction", direction);
		iOSDriver.executeScript("mobile:swipe",parameters);
	}
	
	public void swipe(IOSDriver iOSDriver,String direction)
	{
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("direction", direction);
		iOSDriver.executeScript("mobile:swipe",parameters);
	}
	//end


}
