package com.buildpiper.capabilities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface uicapabilities {
	public void ui_keyPress(String key);
	public void ui_click(WebElement element, String fieldName);
	public void ui_setvalue(WebElement element, String fieldName, String valueToBeSent);
	public void ui_launch(String url);
	public WebDriver ui_getUIDriver();
	public void ui_closeDriver();
	public void ui_waitTillElementIsVisibleAndClickable(WebElement element);
	public boolean ui_IsElementDisplay(WebElement locator);
	public boolean ui_IsElementPresent(String xpath) ;
	public boolean ui_IsElementPresent(WebElement element,String time);
	public boolean ui_selectValueFromDropdown(String locator, String text);
	public void ui_selectValueFromDropDownByText(WebElement element, String value);
	public void ui_selectValueFromDropDownByXPath(WebElement element, String value);
	public void ui_selectValueFromDropDownByIndex(WebElement element, int value);
	public void ui_selectValueFromDropDownByValue(WebElement element, String value);	
	public void ui_waitForElementToDisAppear(List<WebElement> id);
	public void ui_switchToFrame(WebElement frame);
	public void ui_switchToNewWindow();
	public void ui_isElementContainsText(WebElement frame,String value);
	public void ui_waitForPageLoad();
	public List<String> ui_getTextForElements(String selector);
	public void executeJavascript(String script);
	public void ui_scrollTo(int xpos, int ypos) ;
	public List<WebElement> ui_getElementsWithXpath(String selector);
	public WebElement ui_getElementWithXpath(String selector);
	public void ui_validateSubstring(String substring, String string);
	public boolean ui_validateElementContainsText(String selector, String substring);
	public void ui_clearAndSetValue(WebElement webElement, String string);
	public void ui_zoom(String zoomlevel);
	public void ui_clickHoldAndDrop(WebElement sourceElement,WebElement targetElement);
	void ui_selectValueFromDropdown(WebElement element, WebElement parentLocator, String text);
}
