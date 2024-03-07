package com.buildpiper.utils;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.buildpiper.base.Capability;


public class Pause extends Capability{

	public static String V_SMALL="3";
	public static String SMALL="5";
	public static String LOW="15";
	public static String MEDIUM="30";
	public static String HIGH="60";
	public static String V_HIGH="100";
    private static WebDriver jsWaitDriver;
	private static WebDriverWait jsWait;
	private static JavascriptExecutor jsExec;

	//Get the driver from relevant test
	public static void setDriver (WebDriver driver) {
		jsWaitDriver = driver;
		jsWait = new WebDriverWait(jsWaitDriver, 10);
		jsExec = (JavascriptExecutor) jsWaitDriver;
	
	}

	//Wait for JQuery Load
	public static void waitForJQueryLoad() {
		//Wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) jsWaitDriver)
				.executeScript("return jQuery.active") == 0);

		//Get JQuery is Ready
		boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

		//Wait JQuery until it is Ready!
		if(!jqueryReady) {
			System.out.println("JQuery is NOT Ready!");
			//Wait for jQuery to load
			jsWait.until(jQueryLoad);
		} else {
			System.out.println("JQuery is Ready!");
		}
	}

	//Wait for Angular Load
	public static void waitForAngularLoad() {
		WebDriverWait wait = new WebDriverWait(jsWaitDriver,15);
		JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

		String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

		//Wait for ANGULAR to load
		ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
				.executeScript(angularReadyScript).toString());

		//Get Angular is Ready
		boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

		//Wait ANGULAR until it is Ready!
		if(!angularReady) {
			System.out.println("ANGULAR is NOT Ready!");
			//Wait for Angular to load
			wait.until(angularLoad);
		} else {
			System.out.println("ANGULAR is Ready!");
		}
	}

	//Wait Until JS Ready
	public static void waitUntilJSReady() {
		WebDriverWait wait = new WebDriverWait(jsWaitDriver,15);
		JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

		//Wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) jsWaitDriver)
				.executeScript("return document.readyState").toString().equals("complete");

		//Get JS is Ready
		boolean jsReady =  (Boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");

		//Wait Javascript until it is Ready!
		if(!jsReady) {
			System.out.println("JS in NOT Ready!");
			//Wait for Javascript to load
			wait.until(jsLoad);
		} else {
			System.out.println("JS is Ready!");
		}
	}

	//Wait Until JQuery and JS Ready
	public static void waitUntilJQueryReady() {
		JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

		//First check that JQuery is defined on the page. If it is, then wait AJAX
		Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
		if (jQueryDefined == true) {
			//Pre Wait for stability (Optional)
			sleep(20);

			//Wait JQuery Load
			waitForJQueryLoad();

			//Wait JS Load
			waitUntilJSReady();

			//Post Wait for stability (Optional)
			sleep(20);
		}  else {
			//System.out.println("jQuery is not defined on this site!");
		}
	}

	//Wait Until Angular and JS Ready
	public static void waitUntilAngularReady() {
		JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

		//First check that ANGULAR is defined on the page. If it is, then wait ANGULAR
		Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
		if (!angularUnDefined) {
			Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
			if(!angularInjectorUnDefined) {
				//Pre Wait for stability (Optional)
				sleep(20);

				//Wait Angular Load
				waitForAngularLoad();

				//Wait JS Load
				waitUntilJSReady();

				//Post Wait for stability (Optional)
				sleep(20);
			} else {
				System.out.println("Angular injector is not defined on this site!");
			}
		}  else {
			//System.out.println("Angular is not defined on this site!");
		}
	}

	//Wait Until JQuery Angular and JS is ready
	public static void waitJQueryAngular(WebDriver driver) {
		try {
		setDriver(driver);
		waitUntilJQueryReady();
		waitUntilAngularReady();
		}catch(Exception e)
		{
			Reporter.log("Exception while waiting for page load", 0, true);
		}
	}

	public static void sleep (long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(WebElement element, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(List<WebElement> selectRegistry, String value) {
		// TODO Auto-generated method stub
		
	}

}

