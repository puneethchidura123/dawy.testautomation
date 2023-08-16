package org.waseel.dallah.testautomation.reusables;


import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class GenericUIUtilites {

	WebDriver driver;

	public GenericUIUtilites(WebDriver driver)
	{
		this.driver=driver;
	}

	//below are the generic utilites related to webelement/mobileelement creation
	//start
	public WebElement createWebElement(String locatortype,String locatorvalue)
	{
		WebElement webElement=null;
		try
		{
			switch(locatortype)
			{
			case "xpath":
				webElement = driver.findElement(By.xpath(locatorvalue));
				break;
			case "cssSelector":
				webElement = driver.findElement(By.cssSelector(locatorvalue));
				break;	
			case "iOSClassChain":
				webElement = driver.findElement(AppiumBy.iOSClassChain(locatorvalue));
				break;
			case "iOSNsPredicateString":
				webElement = driver.findElement(AppiumBy.iOSNsPredicateString(locatorvalue));
				break;
			case "accessibilityId":
				webElement = driver.findElement(AppiumBy.accessibilityId(locatorvalue));
				break;
			default:
				System.out.println("the passed locator type :: "+locatortype+"  is not correct OR doesn't match with any of the coded locators type. Please recheck. FYI the accepted locator types are xpath,cssSelector,iOSClassChain,accessibilityId,iOSNsPredicateString[CASE SENSITIVE]");
			}
		}
		catch(Exception e)
		{
			System.out.println("exception caught while clicking on webelement");
			e.printStackTrace();
		}
		return webElement;
	}

	public String getWebElementText(WebElement webElement)
	{
		String text = null;
		text = webElement.getText();
		return text;
	}
	//end

	//end

	// below are the generic utils realted to click action
	//start
	public void clickOnWebElement(WebElement webElement)
	{
		try
		{
			webElement.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("exception caught while clicking on webelement");
		}
	}

	public void clickOnWebElement(String locatortype,String locatorvalue)
	{
		try
		{
			switch(locatortype)
			{
			case "xpath":
				driver.findElement(By.xpath(locatorvalue)).click();
				break;
			case "cssSelector":
				driver.findElement(By.cssSelector(locatorvalue)).click();
				break;	
			case "iOSClassChain":
				driver.findElement(AppiumBy.iOSClassChain(locatorvalue)).click();
				break;
			case "iOSNsPredicateString":
				driver.findElement(AppiumBy.iOSNsPredicateString(locatorvalue)).click();
				break;
			case "accessibilityId":
				driver.findElement(AppiumBy.accessibilityId(locatorvalue)).click();
				break;
			default:
				System.out.println("the passed locator type :: "+locatortype+"  is not correct OR doesn't match with any of the coded locators type. Please recheck. FYI the accepted locator types are xpath,cssSelector,iOSClassChain,accessibilityId,iOSNsPredicateString[CASE SENSITIVE]");
			}
		}
		catch(Exception e)
		{
			System.out.println("exception caught while clicking on webelement");
			e.printStackTrace();
		}
	}
	//end

	//below are the generic utilites related to checkboxed/radio buttons
	//start
	public boolean isCheckBoxEnabled(WebElement webElement)
	{
		boolean isCheckBoxEnabled = false;
		//write code to check if isCheckBoxEnabled
		return isCheckBoxEnabled;
	}

	public boolean isCheckBoxDisabled(WebElement webElement)
	{
		boolean isCheckBoxDisabled = false;
		//write code to check if isCheckBoxDisabled
		return isCheckBoxDisabled;
	}


	public void enableCheckBox(WebElement webElement)
	{
		if(!isCheckBoxEnabled(webElement))
		{
			clickOnWebElement(webElement);
		}
	}

	public void disableCheckBox(WebElement webElement)
	{
		if(isCheckBoxDisabled(webElement))
		{
			clickOnWebElement(webElement);
		}
	}
	//end

	// below are the generic utilites related to type action
	//start
	public void enterText(WebElement webElement,String input)
	{
		try
		{
			webElement.sendKeys(input);
		}
		catch(StaleElementReferenceException staleElementReferenceException)
		{
			System.out.println("stale element reference exception caught while entering text :: "+input);
			staleElementReferenceException.printStackTrace();
			System.out.println("Retrying....");
			WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
			webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.sendKeys(input);
			// implement stale element reference exception handling code
			
		}
		catch(Exception e)
		{
			System.out.println("exception caught while entering text :: "+input);
			e.printStackTrace();
		}
	}

	public void enterText(String locatortype,String locatorvalue,String input)
	{
		try
		{
			switch(locatortype)
			{
			case "xpath":
				driver.findElement(By.xpath(locatorvalue)).sendKeys(input);
				break;
			case "cssSelector":
				driver.findElement(By.cssSelector(locatorvalue)).sendKeys(input);
				break;
			case "iOSNsPredicateString":
				driver.findElement(AppiumBy.iOSNsPredicateString(locatorvalue)).sendKeys(input);
				break;
			case "accessibilityId":
				driver.findElement(AppiumBy.accessibilityId(locatorvalue)).sendKeys(input);
				break;
			default:
				System.out.println("the passed locator type :: "+locatortype+"  is not correct OR doesn't match with any of the coded locators type. Please recheck. FYI the accepted locator types are xpath,cssSelector,iOSNsPredicateString[CASE SENSITIVE]");
			}
		}
		catch(Exception e)
		{
			System.out.println("exception caught while entering text :: "+input);
			e.printStackTrace();
		}
	}

	public String getExistingTextFromTextInput(WebElement webElement)
	{
		String existingText = null;
		try
		{
			existingText = webElement.getText();
		}
		catch(Exception e)
		{
			System.out.println("exception caught while getting text");
			e.printStackTrace();
		}
		return existingText;
	}

	public String getExistingTextFromTextInput(String locatortype,String locatorvalue)
	{
		String existingText = null;
		try
		{
			switch(locatortype)
			{
			case "xpath":
				existingText = driver.findElement(By.xpath(locatorvalue)).getText();
				break;
			case "cssSelector":
				existingText = driver.findElement(By.cssSelector(locatorvalue)).getText();
				break;
			case "iOSNsPredicateString":
				existingText = driver.findElement(AppiumBy.iOSNsPredicateString(locatorvalue)).getText();
				break;
			case "accessibilityId":
				existingText = driver.findElement(AppiumBy.accessibilityId(locatorvalue)).getText();
				break;
			default:
				System.out.println("the passed locator type :: "+locatortype+"  is not correct OR doesn't match with any of the coded locators type. Please recheck. FYI the accepted locator types are xpath,cssSelector,iOSNsPredicateString[CASE SENSITIVE]");
			}
		}
		catch(Exception e)
		{
			System.out.println("exception caught while getting text");
			e.printStackTrace();
		}
		return existingText;
	}
	//end

	// below are the utilites related to json<==>string formatting
	//start
	public List<HashMap<String,String>> covertJsonToListOfHashMap(String jsonFilePath) throws Exception
	{
		String jsonIntoString = 
				FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String,String>> data =
				objectMapper.readValue(jsonIntoString,
						new TypeReference<List<HashMap<String,String>>>() {
				});
		return data;

	}
	//end


	// below are the utilites related to webelement dimensions
	//start
	public Dimension getDimensionsOfWebElement(WebElement webElement)
	{
		Dimension dimension=null;

		try
		{
			dimension = webElement.getSize();
		}
		catch(Exception e)
		{
			System.out.println(" exception occured while getting dimession");
			e.printStackTrace();
		}

		return dimension;
	}

	//end


	//below are some utilites related to wait
	//start
	public void waitForElementToAppear(WebElement webElement,String attributeName,String attributeValue)
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		webDriverWait.until(ExpectedConditions.attributeContains(webElement, attributeName,attributeValue));

	}
	//end

	//below are some utilites related to Activites
	//start
	public void openParticularScreen(String activityName)
	{
		Activity activity = new Activity(activityName,"io.appium."); 
	}
	//end


	//below are some utilites related to javascriptexecutor
	//start
	public JavascriptExecutor getJavascriptExecutorInstance()
	{
		JavascriptExecutor javascriptExecutor = null;
		javascriptExecutor = (JavascriptExecutor) driver;
		return javascriptExecutor;
	}

	public void setAttributeFromDom(WebElement webElement,String attributeName,String requiredValue)
	{
		JavascriptExecutor javascriptExecutor = getJavascriptExecutorInstance();
		if(javascriptExecutor!=null)
		{
			try
			{
				String script = "arguments[0].setAttribute(arguments[1], arguments[2])";
				javascriptExecutor.executeScript(script, webElement, attributeName, requiredValue);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println(" the passed JavascriptExecutor is null at ::"+GenericUIUtilites.class.getName());
		}
	}

	public String getAttributeFromDom(WebElement webElement,String attributeName)
	{
		return webElement.getAttribute(attributeName);
	}
	//end

	//below are the some utilites related to dates
	//start
	public  int compareMonths(String monthName1, String monthName2) {
		try {
			// Convert the month names to the corresponding Month enum objects
			Month enumMonth1 = Month.valueOf(monthName1.toUpperCase());
			Month enumMonth2 = Month.valueOf(monthName2.toUpperCase());

			// Compare the months using their ordinal values
			return enumMonth1.compareTo(enumMonth2);
		} catch (Exception e) {
			// Handle invalid month names if necessary
			throw new IllegalArgumentException("Invalid month name provided");
		}
	}
	//end

	//below are the some utilites related to visibility
	//start
	public boolean checkIfElementIsDisplayed(WebElement webElement)
	{
		boolean isElementDisplayed = false;
		try
		{
			isElementDisplayed = webElement.isDisplayed();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while checking if "+webElement.getText()+" webelement is displayed or not");
		}
		return isElementDisplayed;
	}
	//end
	
	//below are the some utilites related to clickability/enablement
	//start
	public boolean checkIfElementIsEnabled(WebElement webElement)
	{
		boolean isElementEnabled = false;
		try
		{
			isElementEnabled = webElement.isEnabled();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while checking if "+webElement.getText()+" webelement is enabled or not");
		}
		return isElementEnabled;
	}
	//end

	
}
