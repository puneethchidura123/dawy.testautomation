package org.waseel.dallah.testautomation.reusables;

import java.awt.Point;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class AndroidActions extends GenericUIUtilites {

	AndroidDriver androidDriver;

	public AndroidActions(AndroidDriver androidDriver)
	{
		super(androidDriver);
		this.androidDriver=androidDriver;
	}

	public void scrollToText(String text)
	{
		//scrolling to the required text
		String escapedtext = "\"" + text + "\"";
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text("+escapedtext+"))"));
	}
	
	public void scrollToBottom() {
		String pageSource = "";
		while(!isEndOfPage(pageSource))
		{
			pageSource = androidDriver.getPageSource();
			
		}
	}
	
	public void scroll()
	{
		System.out.println("inside scroll method");
		Dimension screenDimension = androidDriver.manage().window().getSize();
		System.out.println("Screen Width :: "+screenDimension.getWidth());
		System.out.println("Screen Height :: "+screenDimension.getHeight());
		Point midPoint = new Point(screenDimension.getWidth()/2, screenDimension.getHeight()/2);
		System.out.println("midpoint :: "+"("+midPoint.x+","+midPoint.y+")");
		int bottom = midPoint.y + (int)(midPoint.y*0.5);
		int top = midPoint.y - (int)(midPoint.y*0.25);
		
		Point start = new Point(midPoint.x, bottom);
		Point end = new Point(midPoint.x, top);
		
		swipe(start,end,Duration.ofMillis(400));
	}
	
	public void perormScroll()
	{
		System.out.println("inside scroll method");
		Dimension screenDimension = androidDriver.manage().window().getSize();
		System.out.println("Screen Width :: "+screenDimension.getWidth());
		System.out.println("Screen Height :: "+screenDimension.getHeight());
		Point midPoint = new Point(screenDimension.getWidth()/2, screenDimension.getHeight()/2);
		System.out.println("midpoint :: "+"("+midPoint.x+","+midPoint.y+")");
		int bottom = midPoint.y + (int)(midPoint.y*0.5);
		int top = midPoint.y - (int)(midPoint.y*0.25);
		
		Point start = new Point(midPoint.x, bottom);
		Point end = new Point(midPoint.x, top);
		
		PointOption startPoint = PointOption.point(midPoint.x, bottom);
		PointOption endPoint = PointOption.point(midPoint.x, top);
		
		scrollInAndroid(startPoint, endPoint);
	}
	
	public void scrollInAndroid(PointOption startPoint,PointOption endPoint)
	{
		System.out.println("inside scrollInAndroid");
		new AndroidTouchAction((PerformsTouchActions)androidDriver)
		.press(startPoint)
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(endPoint)
		.release().perform();
	}
	
	
	public void swipe(Point start,Point end, Duration duration)
	{
		System.out.println("inside swipe method");
		PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(input, 0);
		swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x,start.y));
		swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		
		swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x,end.y));
		swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		((AppiumDriver)androidDriver).perform(ImmutableList.of(swipe));
		
	}
	
	public void scrollTill(String text)
	{
		
	}
	
	public void scrollTill(WebElement webElement)
	{
		
	}
	
	public boolean isEndOfPage(String pageSource)
	{
		return pageSource.equals(androidDriver.getPageSource());
	}


	// below are some utilites related to keyboard actions
	//start
	public void hitEnterFromKeyboard()
	{
		androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}
	//end
}
