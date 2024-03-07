package com.buildpiper.testcases;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.buildpiper.base.BaseTest;
import com.buildpiper.listeners.RetryCountIfFailed;
import com.buildpiper.pages.HomePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.RepoIntegrationPage;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.XlsReadData;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class RepoIntegrationTestCases extends BaseTest {
	
//    XlsReadData reader = new XlsReadData(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\BuildPiperTestData.xlsx");
	ExcelUtility reader = new ExcelUtility();
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
	 @BeforeMethod
	    public void StartDriver() {
	    	new LoginPage().login(config.username(), config.password());
	    	ui_wait(5);
	    }
	   @AfterMethod
	    public void StopDriver() {
	    	ui_getUIDriver().quit();
	    }
	
	@Test(groups = { "Regression" },priority = 0)
//	@RetryCountIfFailed(2)
	public void AddRepo() {
		
		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new RepoIntegrationPage().AddRepoOption1(reader.getCellData("RepoIntegrationData", "inetgrationType", 2),reader.getCellData("RepoIntegrationData", "secretName1", 2),reader.getCellData("RepoIntegrationData", "gitURL", 2),reader.getCellData("RepoIntegrationData", "ValidationMessage", 2));

	}
	
	@Test(groups = { "Regression" },priority = 0)
//	@RetryCountIfFailed(2)
	public void AddSecretsOnAddRepoPage() {
		
		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new RepoIntegrationPage().AddSecret(reader.getCellData("RepoIntegrationData", "inetgrationType", 2),reader.getCellData("RepoIntegrationData", "secretName1", 2),reader.getCellData("RepoIntegrationData", "secretType", 2),reader.getCellData("RepoIntegrationData", "userName", 2),reader.getCellData("RepoIntegrationData", "accessToken", 2),reader.getCellData("RepoIntegrationData", "awsAccessKey", 2),reader.getCellData("RepoIntegrationData", "password", 2),reader.getCellData("RepoIntegrationData", "sshKey", 2),reader.getCellData("RepoIntegrationData", "tokenValue", 2),reader.getCellData("RepoIntegrationData", "awsSecretKey", 2));

	}

}