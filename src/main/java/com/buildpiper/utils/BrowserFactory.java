package com.buildpiper.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	public WebDriver getBrowserInstance(String browser){

		RemoteWebDriver driver = null;

		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			//Added for Headless
			if(Configuration.get("headless").equalsIgnoreCase("yes")) {
				options.addArguments("--headless");
				 options.addArguments("--disable-gpu");
			}
			
		   // options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			String resolution=Configuration.get("BrowserSize");
			String[] parts = resolution.split("[xX]");
		    Dimension screenRes = new Dimension(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		    // Set browser resolution
			driver.manage().window().setSize(screenRes);
			

		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			try {
				if(Configuration.get("grid").equalsIgnoreCase("no")){
					driver = new FirefoxDriver();
				}
				else{driver = new RemoteWebDriver(new URL("http:192.168.225.219:4444/wd/hub"), DesiredCapabilities.firefox());}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		        

		}else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions eoption= new EdgeOptions();
			if(Configuration.get("grid").equalsIgnoreCase("no")){
				driver= new EdgeDriver(eoption);
			}	
		}else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
		    InternetExplorerOptions iOptions = new InternetExplorerOptions();
			iOptions.addCommandSwitches("-private");
			if(Configuration.get("grid").equalsIgnoreCase("no")){
				driver = new InternetExplorerDriver(iOptions);
			}
			
			
		}
		return driver;
	}


}
