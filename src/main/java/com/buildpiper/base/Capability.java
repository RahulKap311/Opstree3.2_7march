/*
 * @Author: smehta
 * @Description: This class contain all method that framework must support for UI and API automation
 * 
 */
package com.buildpiper.base;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.buildpiper.capabilities.APICapability;
import com.buildpiper.capabilities.UICapability;
import com.buildpiper.capabilities.apicapabilities;
import com.buildpiper.capabilities.uicapabilities;
import com.buildpiper.report.Log;
import com.buildpiper.utils.BrowserFactory;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.DriverFactory;
import com.buildpiper.utils.Pause;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author smehta
 *
 */

public abstract class Capability implements apicapabilities , uicapabilities {
	public static SoftAssert SoftAssertion = new SoftAssert();
	public static Logger lgger = Logger.getLogger(TestBase.class.getName());
	private APICapability apicapability= new APICapability();
	private UICapability uicapability= new UICapability();	
	public static TestCase _session;
	
	/**
	 * @author smehta
	 * 
	 * This method will click webElement on UI
	 *
	 * @param element
	 * @param fieldName
	 * 
	 */
	public void ui_clickIfPresent(WebElement element, String fieldName){
		uicapability.ui_clickIfPresent(element, fieldName);
		Log.info(String.format("Clicking on element %s", fieldName),1);
	}
	
	/**
	 * @author smehta
	 * 
	 * This method will click webElement on UI
	 *
	 * @param element
	 * @param fieldName
	 * 
	 */
	public void ui_click(WebElement element, String fieldName){
		uicapability.ui_click(element, fieldName);
		Log.info(String.format("Clicking on element %s", fieldName),1);
	}

	/**
	 * @author smehta
	 * 
	 * This method will click webElement on UI
	 *
	 * @param element
	 * @param fieldName
	 * 
	 */
	public void ui_click(String element, String fieldName){
		try {		
		WebDriverWait wait = new WebDriverWait(ui_getUIDriver(), Long.valueOf(Pause.LOW));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));}
		catch(Exception e) {
			Log.info(String.format("Clicking on element %s", fieldName),2);	
		}
		uicapability.ui_click(element, fieldName);
		Log.info(String.format("Clicking on element %s", fieldName),2);
	}

	
	
	
	
	/**
	 * @author smehta
	 * 
	 * This method will set value in WebElement
	 * 
	 * @param element
	 * @param fieldName
	 * @param value
	 */
	public void ui_ActionClick(WebElement element, String fieldName){
	
	Actions ob = new Actions(ui_getUIDriver());
	ob.click(element).build().perform();
	Log.info(String.format("Action Clicking to element :", fieldName),2);
	
	}
	
	public void ui_ActionMoveAndClick(WebElement element, String fieldName){
		
		Actions ob = new Actions(ui_getUIDriver());
		ob.moveToElement(element).click(element).build().perform();
		Log.info(String.format("Move and Action Clicking to element :", fieldName),2);
		}
	
public void ui_MoveToElement(WebElement element, String fieldName){
	 ((org.openqa.selenium.JavascriptExecutor)ui_getUIDriver()).executeScript("arguments[0].scrollIntoView();", element);
		
		Log.info(String.format("Moving to element %s", fieldName),2);
		}
	//div[@class='service-name']//a[contains(.,'Build-pipeline')]
	
	/**
	 * @author smehta
	 * 
	 * This method will set value in WebElement
	 * 
	 * @param element
	 * @param fieldName
	 * @param value
	 */
	public void ui_setvalue(WebElement element, String fieldName, String value){
		uicapability.ui_setvalue(element, fieldName,value);
		Log.info(String.format("Setting value on element %s", fieldName),2);
	}
	
	public void ui_setintvalue(WebElement element, String fieldName, int value){
		uicapability.ui_setintvalue(element, fieldName,value);
		Log.info(String.format("Setting value on element %s", fieldName),2);
	}
	
	
	public void ui_FileUpload(String browserName, String filePath){
		uicapability.ui_FileUpload(browserName, filePath);
		Log.info(String.format("Uploading the File Path %s", filePath),2);
	}

	/**
	 * @author smehta
	 * 
	 * This method will set driver instance	
	 */
	public void ui_setDriver() {
		if(ui_hasDriverQuit()==false) {return;}
		BrowserFactory bf= new BrowserFactory();
		DriverFactory.getInstance().setDriver(bf.getBrowserInstance(Configuration.get("browser")));	
		ui_getUIDriver().manage().window().maximize();
		_session.setDriver(ui_getUIDriver());
		//Log.info("Setting driver",2);
	}

	/**
	 * @author smehta
	 * 
	 * This method will close driver instance	
	 */
	public boolean ui_hasDriverQuit() {
		try {
			//Log.info("checking if driver exist",1);
			String title = ui_getUIDriver().getTitle();
			if(title != null && !"".equals(title.trim()))
			{
				return false;
			}else {

				boolean flag= ((RemoteWebDriver)ui_getUIDriver()).getSessionId()==null;
				if(flag && ui_getUIDriver() !=null) {
					ui_getUIDriver().close();
					return flag;
				}
				return flag;
			}
		}catch(Exception e) {
			return true;
		}
	}

	/**
	 * @author smehta
	 *
	 *This method will return driver instance
	 */
	public WebDriver ui_getUIDriver() {
		//Log.info("00000 ",2);
		return uicapability.ui_getUIDriver();
	}

	/**
	 * @author smehta
	 *
	 *This method will launch the url
	 */
	public void ui_launch(String url) {
		Log.info("Open URL:"+url,2);
		uicapability.ui_launch(url);
	}

	/**
	 * @author smehta
	 * 
	 * This method will take screenshot and save it in folder
	 * 
	 * @param folder
	 * @param name
	 */

	public void ui_takeScreenShot(String folder,String name) {
		
		uicapability.ui_takeScreenShot(folder,name);
	}



	/**
	 * @author smehta
	 *
	 *This method will close the driver
	 *
	 */
	@Override
	public void ui_closeDriver() {
		// TODO Auto-generated method stub
		//Log.info("closing driver",2);
		//uicapability.ui_closeDriver();
	}

	/**
	 *@author smehta
	 *
	 *This method will wait for element to be clickable
	 *
	 *@param element
	 */
	@Override
	public void ui_waitTillElementIsVisibleAndClickable(WebElement element) {
		// TODO Auto-generated method stub
		uicapability.ui_waitTillElementIsVisibleAndClickable(element);
	}

	/**
	 *@author smehta
	 *
	 *This method will check if element is present on UI
	 *
	 *@param element
	 */
	@Override
	public boolean ui_IsElementDisplay(WebElement element) {
		// TODO Auto-generated method stub
		return uicapability.ui_IsElementDisplay(element);
		
	}

	/**
	 *@author smehta
	 *
	 *This method will check if xpath is present on page
	 *
	 *@param xpath
	 */
	@Override
	public boolean ui_IsElementPresent(String xpath) {
		// TODO Auto-generated method stub
		return uicapability.ui_IsElementPresent(xpath);
	}
	
	/**
	 *@author smehta
	 *
	 *This method will check if xpath is present on page
	 *
	 *@param xpath
	 */
	@Override
	public boolean ui_IsElementPresent(WebElement element,String time) {
		// TODO Auto-generated method stub
		return uicapability.ui_IsElementPresent(element,time);
	}

	/**
	 *@author smehta
	 *
	 *This method will select value from dropdown
	 *
	 *@param locator
	 *@param text
	 *
	 */
	@Override
	public boolean ui_selectValueFromDropdown(String locator, String text) {
		// TODO Auto-generated method stub
		return uicapability.ui_selectValueFromDropdown(locator,text);
	}

	
	@Override
	public void ui_selectValueFromDropdown(WebElement mainLocator ,WebElement parentLocator, String text) {
		// TODO Auto-generated method stub
		    uicapability.ui_selectValueFromDropdown( mainLocator , parentLocator,  text);
	}
	
	/**
	 *@author smehta
	 *
	 *This method will select value from dropdown by text 
	 *
	 *@param element
	 *@param value
	 *
	 */
	@Override
	public void ui_selectValueFromDropDownByText(WebElement element, String value) {
		// TODO Auto-generated method stub
		uicapability.ui_selectValueFromDropDownByText(element,value);

	}

	/**
	 *@author smehta
	 *
	 *This method will clear textbox and set value
	 *
	 *@param webElement
	 *@param string
	 *
	 */


	public void ui_clearAndSetValue(WebElement webElement, String value) {

		uicapability.ui_clearAndSetValue( webElement,  value);
	}

	public void ui_clearAndSetValue(String webElement, String value) {
		// TODO Auto-generated method stub
		uicapability.ui_clearAndSetValue(ui_getElementWithXpath(webElement),  value);
	}
	
	public void ui_clearUsingJavaScript(WebElement webElement, String string) {
		// TODO Auto-generated method stub
		uicapability.ui_clearUsingJavaScript(webElement,  string);
	}
	/**
	 *@author smehta
	 *
	 *This method will select value from dropdown by index
	 *
	 *@param element
	 *@param value
	 *
	 */
	@Override
	public void ui_selectValueFromDropDownByIndex(WebElement element, int value) {
		// TODO Auto-generated method stub
		uicapability.ui_selectValueFromDropDownByIndex(element,value);

	}

	/**
	 *@author smehta
	 *
	 *This method will select value from dropdown by value
	 *
	 *@param element
	 *@param value
	 *
	 */
	@Override
	public void ui_selectValueFromDropDownByValue(WebElement element, String value) {
		// TODO Auto-generated method stub
		uicapability.ui_selectValueFromDropDownByValue(element,value);
	}

	/**
	 *@author smehta
	 *
	 *This method will wait for an element to disappear
	 *
	 *@param id
	 *
	 */
	@Override
	public void ui_waitForElementToDisAppear(List<WebElement> id) {
		// TODO Auto-generated method stub
		uicapability.ui_waitForElementToDisAppear(id);		
	}


	/**
	 *@author smehta
	 *
	 *This method will switch to frame
	 *
	 *@param frame
	 *
	 */
	@Override
	public void ui_switchToFrame(WebElement frame) {
		// TODO Auto-generated method stub
		uicapability.ui_switchToFrame(frame);
		
	}

	@Override
	public void ui_switchToNewWindow() {
		// TODO Auto-generated method stub
		uicapability.switchToNewWindow();	
	}

	
	/**
	 *@author smehta
	 *
	 *This method will wait for certail time before thrwoing excpetion
	 *
	 *@param time
	 *
	 */
	public void ui_wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ui_keyPress(String key) {
		uicapability.ui_keyPress(key);
	}
	
	
	/**
	 *@author smehta
	 *
	 *This method will check by locator if an elemnt is present
	 *
	 *@param locator
	 *
	 */
	public boolean ui_IsElementPresent(By locator) {
		// TODO Auto-generated method stub
		return uicapability.ui_IsElementPresent(locator);
	}

	/**
	 *@author smehta
	 *
	 *This method will wait for an element to appear
	 *
	 *@param element
	 *@param time In seconds
	 *
	 */
	public WebElement ui_waitForElementToDisplay(WebElement element,String time) {
		// TODO Auto-generated method stub
		return uicapability.ui_waitForElementToDisplay(element,Long.valueOf(time));
	}


	@Override
	public void ui_isElementContainsText(WebElement element, String value) {
		// TODO Auto-generated method stub
		uicapability.ui_isElementContainsText( element,  value);
	}

	@Override
	public List<String> ui_getTextForElements(String selector) {
		// TODO Auto-generated method stub
		return  uicapability.ui_getTextForElements(selector);
	}

	@Override
	public void executeJavascript(String script) {
		// TODO Auto-generated method stub
		uicapability.executeJavascript(script);
	}

	@Override
	public void ui_scrollTo(int xpos, int ypos) {
		// TODO Auto-generated method stub
		ui_scrollTo( xpos,  ypos) ;
	}

	@Override
	public List<WebElement> ui_getElementsWithXpath(String selector) {
		// TODO Auto-generated method stub
		return ui_getElementsWithXpath( selector) ;
	}

	@Override
	public WebElement ui_getElementWithXpath(String selector) {
		// TODO Auto-generated method stub
		return uicapability.ui_getElementWithXpath(selector);
	}

	@Override
	public void ui_validateSubstring(String substring, String string) {
		// TODO Auto-generated method stub
		uicapability.ui_validateSubstring( substring,  string);
	}

	@Override
	public boolean ui_validateElementContainsText(String selector, String substring) {
		// TODO Auto-generated method stub
		return uicapability.ui_validateElementContainsText( selector,  substring);
	}

	@Override
	public void ui_clickHoldAndDrop(WebElement sourceElement, WebElement targetElement) {
		// TODO Auto-generated method stub
		 uicapability.ui_clickHoldAndDrop(ui_waitForElementToDisplay(sourceElement, Pause.MEDIUM), ui_waitForElementToDisplay(targetElement, Pause.MEDIUM));
	}
	
	/**
	 * ui_zoom: set the zoom level between .01 to 1 for zoom in and 1+ for zoom out
	 *
	 * @param zoomlevel: zoom level from .01 to 1
	 * 
	 */
	public void ui_zoom(String zoomlevel) {

		uicapability.ui_zoom(zoomlevel);
	}

	/**
	 *@author smehta
	 *
	 *This method will wait till page is completely loaded
	 *
	 *
	 */
	public void ui_waitForPageLoad() {
		uicapability.ui_waitForPageLoad();
	}
	/**
	 *@author smehta
	 *
	 *This method will 
	 *
	 */
	@Override
	public RequestSpecification api_getUser() {
		// TODO Auto-generated method stub
		return apicapability.api_getUser();
	}

	/**
	 *@author smehta
	 *
	 *This method will send GET request
	 *
	 *@param requestspecification
	 *@param endpoint
	 *@param statusCode
	 *
	 */
	@Override
	public Response api_sendGetRequest(RequestSpecification requestspecification, String endpoint, Integer statusCode) {
		// TODO Auto-generated method stub
		return apicapability.api_sendGetRequest( requestspecification,  endpoint,  statusCode);
	}

	/**
	 *@author smehta
	 *
	 *This method will send POST request
	 *
	 *@param requestspecification
	 *@param endpoint
	 *@param payload
	 *
	 */
	@Override
	public Response api_sendPostRequest(RequestSpecification requestspecification, String endpoint, String payload,
			Integer statusCode) {
		// TODO Auto-generated method stub
		return apicapability.api_sendPostRequest( requestspecification,  endpoint,  payload,
				statusCode);
	}

	/**
	 *@author smehta
	 *
	 *This method will send POST request
	 *
	 *@param requestspecification
	 *@param fileName
	 *@param statusCode
	 *
	 */
	@Override
	public Response api_sendPostRequest(RequestSpecification requestspecification, String fileName, Integer statusCode,
			String endpoint) {
		// TODO Auto-generated method stub
		return apicapability.api_sendPostRequest( requestspecification,  fileName,  statusCode,
				endpoint);
	}

	/**
	 *@author smehta
	 *
	 *This method will send DELETE request
	 *
	 *@param requestspecification
	 *@param endpoint
	 *@param statusCode
	 *
	 */
	@Override
	public Response api_sendDeleteRequest(RequestSpecification requestspecification, String endpoint,
			Integer statusCode) {
		// TODO Auto-generated method stub
		return apicapability.api_sendDeleteRequest( requestspecification,  endpoint,
				statusCode);
	}

	/**
	 *@author smehta
	 *
	 *This method will send PUT request
	 *
	 *@param requestspecification
	 *@param endpoint
	 *@param statusCode
	 *
	 */
	@Override
	public Response api_sendPutRequest(RequestSpecification requestspecification, String endpoint, String payload,
			Integer statusCode) {
		// TODO Auto-generated method stub
		return apicapability.api_sendPutRequest( requestspecification,  endpoint,  payload,
				statusCode);
	}

	/**
	 *@author smehta
	 *
	 *This method will validate response with actual and expected
	 *
	 *@param actual
	 *@param expected
	 *
	 */
	@Override
	public void api_validateResponse(Response actual, String expected) {
		// TODO Auto-generated method stub
		apicapability.api_validateResponse( actual,  expected);
	}

	/**
	 *@author smehta
	 *
	 *This method will validate json path value
	 *
	 *@param response
	 *@param jsonPath
	 *@param expectedValue
	 *
	 */
	@Override
	public void api_validateJsonPathValueEquals(Response response, String jsonPath, String expectedValue) {
		// TODO Auto-generated method stub
		apicapability.api_validateJsonPathValueEquals( response,  jsonPath,  expectedValue);
	}

	/**
	 *@author smehta
	 *
	 *This method will validate json path value 
	 *
	 *@param response
	 *@param jsonPath
	 *@param expectedValue
	 *
	 */
	@Override
	public void api_validateJsonPathValueContains(Response response, String jsonPath, String expectedValue) {
		// TODO Auto-generated method stub
		apicapability.api_validateJsonPathValueContains( response,  jsonPath,  expectedValue);
	}

	/**
	 *@author smehta
	 *
	 *This method will get response data jsonpath
	 *
	 *@param response
	 *@param jsonPath
	 *
	 */
	@Override
	public String api_getResponseDataJsonPath(Response response, String jsonPath) {
		// TODO Auto-generated method stub
		return apicapability.api_getResponseDataJsonPath( response,  jsonPath);
	}

	/**
	 *@author smehta
	 *
	 *This method will get response data jsonpath
	 *
	 *@param response
	 *@param jsonPath
	 *
	 */
	@Override
	public String api_getResponseDataJsonPath(String response, String jsonPath) {
		// TODO Auto-generated method stub
		return apicapability.api_getResponseDataJsonPath( response,  jsonPath);
	}

	/**
	 *@author smehta
	 *
	 *This method will 
	 *
	 *@param url
	 *
	 */
	@Override
	public void api_signIn(String url) {
		// TODO Auto-generated method stub
		apicapability.api_signIn(url);
	}

	/**
	 *@author praveen
	 *
	 *This method will 
	 *
	 *@param list
	 *
	 */
	public void ui_selectValuefromList(List<WebElement> WebElement , int value){
		List<WebElement> ListOfCheckBoxes = WebElement ;		
		for (int i=0;i<value;i++) {
			ListOfCheckBoxes.get(i).click();
			ui_wait(10);				
		}	
		
		}
	/**
	 *@author praveen
	 *
	 *This method will 
	 *
	 *@param calender
	 *
	 */
	public void ui_datePickerIconBtn(WebElement calenderIcon,WebElement yearDropDown,String month,WebElement rightNextarrow,WebElement selectday) {
		// TODO Auto-generated method stub
		calenderIcon.click();
		while(true)
		{
			String text = yearDropDown.getText();
		if(text.equals(month))
		{
			break;
		}
		else
		{
			rightNextarrow.click();
		}
	}		
		selectday.click();
			}		
	/**
	 *@author praveen
	 *
	 *This method will 
	 *
	 *@param list
	 *
	 */	
	public void ui_clickfromList(List<WebElement> WebElement){
		List<WebElement> ListOfCheckBoxes = WebElement ;		
			for (WebElement tempEle : ListOfCheckBoxes) {
				ui_wait(2);
			  tempEle.click();				
		}
			
	}	
	public void ui_clickfromList(List<WebElement> WebElement,String value){
		List<WebElement> ListOfCheckBoxes = WebElement ;		
			for (WebElement tempEle : ListOfCheckBoxes) {
				ui_wait(2);
				if(tempEle.getText().contains(value)) {
					  tempEle.click();				
                      break;
				}
		}
			
	}	
}
