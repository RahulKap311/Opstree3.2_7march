package com.buildpiper.capabilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.buildpiper.report.Log;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.DriverFactory;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.StringUtility;
import com.buildpiper.utils.TimeUtililty;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.*;



public class UICapability implements uicapabilities{


	public void ui_click(String element, String fieldName) {
		// TODO Auto-generated method stub
		if(ui_IsElementPresent(element)) {
			ui_click(ui_getElementWithXpath(element), fieldName);
		}
	}

	public void ui_click(WebElement element, String fieldName) {
		try {
			ui_scrollToElement(ui_waitForElementToDisplay(element,Long.valueOf(Pause.MEDIUM))).click();

		} catch (Exception e) {

			try {
				ui_waitForPageLoad();
				ui_javaScriptClick(element,fieldName);
			}catch(Exception t) {
				ui_waitForElementToDisplay(element,Long.valueOf(Pause.MEDIUM));
				if(ui_IsElementDisplay(element))
				{   
					//Action Click

				}
				else {

					ui_javaScriptClick(element,fieldName); 
				}

			}

			//String ar[]=element.toString().split("->");
			Log.info(" Element :"+element+" is not clickable using selenium click", 2);
		}
	}

	public void ui_javaScriptClick(WebElement element, String fieldName) {

		try {
			ui_scrollToElement(element);
			JavascriptExecutor executor = (JavascriptExecutor)ui_getUIDriver();
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			//String ar[]=element.toString().split("->");
			Reporter.log(" Element :"+element+" is not clickable using javaScript", 0, true);
		}

	}
	
	public void ui_setvalue(WebElement element, String fieldName, String valueToBeSent) {
		try {
			if(valueToBeSent==null) {
				Reporter.log("Value for Element "+fieldName+" is null ");
				Assert.fail("Value for Element "+fieldName+" is null ");
			}

			ui_waitForElementToDisplay(element, Long.valueOf(Pause.MEDIUM)).sendKeys(valueToBeSent);

		} catch (Exception e) {
			Reporter.log("Element "+fieldName+" Not present on screen", 1, true);
			
			try {
				ui_waitForElementToDisplay(element, Long.valueOf(Pause.MEDIUM)).sendKeys(valueToBeSent);	
			}catch(Exception exp) {Assert.fail("Element "+fieldName+" Not present on screen, Exception: "+exp);}
		}

	}


	public void ui_waitForPageLoad() {
		Pause.waitJQueryAngular(ui_getUIDriver());	
	}


	/**
	 * Launch the web browser with starting url
	 * 
	 * @param url
	 *    to launch
	 */



	public void ui_launch(String url) {
		//Reporter.log("Executing " + new Throwable().getStackTrace()[0].getMethodName());
		ui_getUIDriver().get(url);
		//ui_waitForPageLoad();
		//ui_getUIDriver().manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

	}

	/**
	 * Returns the existing webdriver instance
	 * 
	 * @return active driver
	 */

	public WebDriver ui_getUIDriver() {
		return DriverFactory.getInstance().getDriver();

	}

	/**
	 * Returns the existing webdriver instance
	 * 
	 * @return active driver
	 */

	public boolean ui_isDriverExist() {
		return DriverFactory.getInstance().isDriverExist();

	}

	/**
	 * closes the ui Webdriver
	 * 
	 */

	public void ui_closeDriver() {
		DriverFactory.getInstance().closeBrowser();
	}

	public void ui_waitTillElementIsVisibleAndClickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(), 10);
		wait.until(ExpectedConditions.visibilityOf(element));

		wait = new WebDriverWait(ui_getUIDriver(), 5);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}


	public boolean ui_IsElementPresent(By locator) {
		boolean flag = ui_getUIDriver().findElements(locator).size() >= 1;
		//String ar[]=locator.toString().split("->");
		Log.info("Element is present :"+locator, 2);
		return flag;
	}

	public boolean ui_IsElementDisplay(WebElement element) {
		boolean flag = element.isDisplayed();
		//String ar[]=element.toString().split("->");
		Log.info("Element is present :"+element, 2);
		return flag;
	}

	public boolean ui_IsElementPresent(String xpath) {
		boolean flag = ui_getUIDriver().findElements(By.xpath(xpath)).size() >= 1;
		//String ar[]=xpath.toString().split("->");
		Log.info("Element is present :"+xpath, 2);
		return flag;
	}
	
	public boolean ui_IsElementPresent(WebElement element, String time) {
		// TODO Auto-generated method stub
		 String tag=null;
		 Wait<WebElement>  wait = new FluentWait<WebElement>(element).withTimeout(null)
        .withTimeout(Duration.ofSeconds(Integer.parseInt(time)))
        .pollingEvery(Duration.ofMillis(500))
        .ignoring(NoSuchElementException.class);

		 try {
		  tag = (String)wait.until(new Function<WebElement, String>() {
				public String apply(WebElement element) {
					return element.getTagName();
				}
			});
		 }catch(TimeoutException e) {
			 return false; 
		 }
		 if(tag!=null) {return true;}
		 
		 return true;
		 
	}

	public void ui_clickHoldAndDrop(WebElement sourceElement,WebElement targetElement) {
		Actions builder = new Actions(ui_getUIDriver());
		Action dragAndDrop =
				builder.clickAndHold(sourceElement).moveToElement(targetElement).release(targetElement).build();
		ui_wait(2);
		dragAndDrop.perform();

	}
	
	
	public void ui_selectValueFromDropdown(WebElement mainSelector, WebElement optionLocator,String text)
	{
		ui_click(mainSelector, "");
		ui_IsElementPresent(optionLocator, "5");
		ui_wait(2);
		optionLocator.findElement(By.xpath("//*[contains(text(),'"+text+"')]")).click();
		
	}
	public boolean ui_selectValueFromDropdown(String locator, String text)
	{	

		List<WebElement> list=null;
		try {
			WebElement lang = ui_getUIDriver().findElement(By.xpath(locator));
			list = lang.findElements(By.xpath(locator));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (WebElement opt : list)
		{
			String value = opt.getText();
			if(value.equalsIgnoreCase(text)){
				System.out.println("Value clicked ="+value);
				opt.click();
			}
		}
		return true;
	}

	public void ui_selectValueFromDropDownByText(WebElement element, String value) 
	{
		ui_waitForElementToClickable(element, Long.valueOf(Pause.MEDIUM));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public void ui_selectValueFromDropDownByIndex(WebElement element, int value) 
	{
		Select select = new Select(element);
		select.selectByIndex(value);
	}

	public void ui_selectValueFromDropDownByValue(WebElement element, String value) 
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}


	@SuppressWarnings("deprecation")
	public void ui_FileUpload(String browserName, String fileUploadPath) 
	{
		String exeFileP = System.getProperty("user.dir")+"/src/test/resources/FileUpload1.exe";
		try {
			Runtime.getRuntime().exec(exeFileP +" "+browserName+" "+fileUploadPath);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void ui_waitForElementToDisAppear(List<WebElement> id) {
		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(), Long.valueOf(Pause.MEDIUM));
		wait.until(ExpectedConditions.invisibilityOfAllElements(id));
	}

	public WebElement ui_waitForElementToDisplay(WebElement element, long maxTime) {

		try {
		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(),maxTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		//String ar[]=element.toString().split("->");
		Reporter.log("Element :"+element+ "is displayed on screen", 2, true);
		}catch(Exception e) {
			
		}
		
		return element;
	}

	public WebElement ui_waitForElementToClickable(WebElement element, long maxTime) {

		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(),maxTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		//String ar[]=element.toString().split("->");
		Log.info("Element :"+element+ "is displayed on screen", 2);
		return element;
	}
	public void ui_switchToFrame(WebElement frame){
		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(), Long.valueOf(Pause.MEDIUM));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		Log.info("switched to frame"+frame , 2);
	}



	public void ui_wait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Method: waitWebDriver :: exception =  " + e.getMessage());

		}
	}

	public void switchToNewWindow() {
		/*	for (String winHandle : ui_getUIDriver().getWindowHandles()) {
		
		ui_getUIDriver().switchTo().window(winHandle);
	}*/
	
	String parent=ui_getUIDriver().getWindowHandle();

	Set<String>s=ui_getUIDriver().getWindowHandles();

	// Now iterate using Iterator
	Iterator<String> I1= s.iterator();

	while(I1.hasNext())
	{

	String child_window=I1.next();

	if(!parent.equals(child_window))
	{
	ui_getUIDriver().switchTo().window(child_window);

	System.out.println(ui_getUIDriver().switchTo().window(child_window).getTitle());

	ui_getUIDriver().close();
	}

	}
	//switch to the parent window
	ui_getUIDriver().switchTo().window(parent);

	}

	public String ui_takeScreenShot(String folder, String name) {

		System.out.println("EX: "+Configuration.get("executionid"));
		name=StringUtility.returnNullSafe(name);
		String direc = System.getProperty("user.dir")+"/Reports/Screenshots/"+folder;
		File destDir= new File(direc);
		name=name+"_"+TimeUtililty.currentDateAndTime("ddHHmmss");

		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + name + ".jpg");
		try {if(!ui_isDriverExist()) {return "";}
		FileUtils
		.copyFile(((TakesScreenshot) ui_getUIDriver())
				.getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			Reporter.log(e.getMessage());
		}
		Reporter.log(destPath.getAbsolutePath());
		return destPath.getAbsolutePath();
	}

	/**
	 * assertElementNotShown: verifies the selector is no longer present on the page
	 *
	 * @param selector the CSS selector
	 * @param failMessage the message to display
	 */
	public void ui_VerifyElementNotPresent(WebElement element) {
		try {

			ui_waitForElementToDisplay(element,Long.valueOf(Pause.MEDIUM));
			Assert.assertFalse(ui_IsElementDisplay(element), element.toString()+" Not Present");
		} catch (Exception e) {
			// expected exception
			//String ar[]=element.toString().split("->");
			Reporter.log("Element :"+element+" Not Present");
		}
	}



	/**
	 * assertElementPresent: verifies an element matching the selector is found
	 *
	 * @param selector the CSS selector
	 * @return 
	 * @return WebElement of Selector
	 */
	protected WebElement ui_VerifyElementPresent(WebElement element, long time) {
		ui_waitForElementToDisplay(element,time);
		Assert.assertTrue(ui_IsElementDisplay(element));
		return element;
	}

	/**
	 * ui_validateElementContainsText: verifies a selector with the Specified Text Element exists on the page.
	 *
	 * @param selector the CSS selector
	 * @param substring the substring to find
	 * @return the webElement containing the substring
	 */
	public boolean ui_validateElementContainsText(String selector, String substring) {
		for (WebElement element : ui_getElementsWithXpath(selector)) {
			if (element.getText().contains(substring)) {
				return true;
			}
		}
		ui_validateSubstring(substring, ui_VerifyElementPresent(ui_getElementWithXpath(selector),Long.valueOf(Pause.MEDIUM)).getText());
		throw new RuntimeException("an earlier check should have thrown an exception");
	}
	/**
	 * assertSubstring: verifies the substring exists
	 *
	 * @param substring the substring to find
	 * @param string the String to find substring
	 */
	public void ui_validateSubstring(String substring, String string) {
		boolean success = string.contains(substring);
		if (!success) {
			Assert.fail("Expected '" + substring + "' in '" + string + "'");
		}
	}
	/**
	 * ui_getElementWithXpath: is a method that finds an element on a page by a String Locator
	 *
	 * @param selector the CSS selector
	 * @return the WebElement or null if not found
	 */
	public WebElement ui_getElementWithXpath(String selector) {

		try { 
			WebDriverWait wait = new WebDriverWait(ui_getUIDriver(),Long.valueOf(Pause.MEDIUM));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
			return ui_getUIDriver().findElement(By.xpath(selector));
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * findElements: is a method that finds a list of elements on a page by a String Locator
	 *	`
	 * @param selector the CSS selector
	 * @return a list of webElement that contain the className
	 */
	public List<WebElement> ui_getElementsWithXpath(String selector) {

		try {
			return ui_getUIDriver().findElements(By.xpath(selector));
		} catch (NoSuchElementException e) {
			return Collections.emptyList();
		}
	}


	/**
	 * scrollTo: will move the focus of the screen to the specified coordinates
	 *
	 * @param xpos the X locator
	 * @param ypos the Y locator
	 */
	public void ui_scrollTo(int xpos, int ypos) {
		executeJavascript("window.scrollTo(" + xpos + "," + ypos + ")");
	}


	/**
	 * executeJavascript: executes a string of JavaScript
	 *
	 * @param script - string of the javascript to run
	 * @param driver - the current session driver
	 * @return Reply Object from the JS Executor
	 */
	public void executeJavascript(String script) {
		Reporter.log("Executing javascript: " + script);
		JavascriptExecutor js = (JavascriptExecutor)(ui_getUIDriver());
		js.executeScript(script);
	}
	/**
	 * getTextForElements: will return the text for the elements in a list
	 *
	 * @param selector the CSS selector
	 * @return list of strings taken from the list of elements
	 */
	public List<String> ui_getTextForElements(String selector) {
		List<WebElement> elements = ui_getElementsWithXpath(selector);
		List<String> textList = new ArrayList<String>(elements.size());
		for (WebElement element : elements) {
			textList.add(element.getText());
		}
		return textList;
	}


	/**
	 * setInputValue: will set a value passed in as a string into an element that is a type of TEXT input, uses a WebElement
	 *
	 * @param webElement the web element of the Input
	 * @param string the input value
	 */
	public void ui_clearAndSetValue(WebElement webElement, String string) {

		ui_clearText( webElement,  string);
		if (string != null && string.length() > 0) {
			ui_setvalue(webElement, string, string);
		}
	}


	public void ui_clearText(WebElement webElement, String string) {
		// TODO Auto-generated method stub
		try {
			webElement.clear();
		}catch(Exception e)
		{ui_clearUsingJavaScript(webElement, string);
		}
	}

	/**
	 * ui_scrollToElement: will move the focus of the screen to the specified element
	 *
	 * @param webElement the web element of the Input
	 * 
	 */
	public WebElement ui_scrollToElement(WebElement webElement) {
		try {
			((JavascriptExecutor) ui_getUIDriver()).executeScript("arguments[0].scrollIntoView(true);",webElement);
		}catch(Exception e)
		{
			Reporter.log("Exception while scroll to view of element: ", 0, false);
			return null;
		}
		return webElement;
	}

	/**
	 * validate: will validate ui element as per choice
	 *
	 * @param webElement the web element of the Input
	 * @param string the input value
	 */
	public void ui_isElementContainsText(WebElement element, String value) {
		
		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(),Long.valueOf(Pause.MEDIUM));
		wait.until(ExpectedConditions.textToBePresentInElement(element, value));
		//String ar[]=element.toString().split("->");
		Log.info("Element: "+element+" contains text "+value, 2);
	}

	/**
	 * ui_zoom: set the zoom level between .01 to 1
	 *
	 * @param zoomlevel: zoom level from .01 to 1
	 * 
	 */
	public void ui_zoom(String zoomlevel) {
		JavascriptExecutor executor = (JavascriptExecutor)ui_getUIDriver();
		executor.executeScript("document.body.style.zoom = '"+zoomlevel+"'");
	}

	public void ui_clearUsingJavaScript(WebElement webElement, String string) {
		// TODO Auto-generated method stub
		ui_waitForElementToDisplay(webElement, Long.valueOf(Pause.MEDIUM)).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));	
	}

	public void ui_keyPress(String key) {
		// TODO Auto-generated method stub
		Actions ac=new Actions(ui_getUIDriver());
		ac.sendKeys(Keys.ENTER).perform();

	}

	public void ui_clickIfPresent(WebElement element, String fieldName) {
		// TODO Auto-generated method stub
		if(ui_IsElementPresent(element,"time")) {
			ui_click(element, fieldName);
		}
		
	}

	@Override
	public void ui_switchToNewWindow() {
		switchToNewWindow();	
		
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(WebElement element, String value) {
		// TODO Auto-generated method stub
		
	}

	public void ui_setintvalue(WebElement element, String fieldName, int value) {
		// TODO Auto-generated method stub
		
	}	

	

}