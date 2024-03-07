/**
 * @author smehta
 * 
 */
package com.buildpiper.utils;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	private DriverFactory() {

	}

	private static DriverFactory instance  = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}


	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driver.get();
	}

	public boolean isDriverExist() {
		if(getInstance()==null) {return false;}
		return true;
	}
	
	public void setDriver(WebDriver driverParm) {
		driver.set(driverParm);
		//driver.set(createRemoteInstance(null));

	}


	public void closeBrowser() {
		try {
		driver.get().quit();
		driver.remove();
		}catch(Exception e) {
			
		}
	}

	private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
		RemoteWebDriver remoteWebDriver = null;
		try {
			String gridURL = String.format("http://%s:%s",Configuration.get("gridUrl"),Configuration.get("gridPort"));
			remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
		} catch (java.net.MalformedURLException e) {
			// logger.log(Level.SEVERE, "Grid URL is invalid or Grid is not available");
			// logger.log(Level.SEVERE, String.format("Browser: %s", capability.getBrowserName()), e);
		} catch (IllegalArgumentException e) {
			// logger.log(Level.SEVERE, String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
		}

		return remoteWebDriver;
	}

}
