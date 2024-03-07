package com.buildpiper.testcases;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.buildpiper.base.BaseTest;
import com.buildpiper.listeners.RetryCountIfFailed;
import com.buildpiper.pages.HomePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.RepoIntegrationPage;
import com.buildpiper.pages.SuperAdminPage;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.XlsReadData;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class SuperAdminTestCases extends BaseTest {
	
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();
	
	@Test(groups = { "Regression" },priority = 0)
//	@RetryCountIfFailed(2)
	public void userBulkUpload() {
		
		new LoginPage().login(config.username(), config.password());
		new SuperAdminPage().uploadBulkUser();

	}
	
	@Test(groups = { "Regression" },priority = 0)
//	@RetryCountIfFailed(2)
	public void clusterOverview() {
		
		new LoginPage().login(config.username(), config.password());
		new SuperAdminPage().ClusterOverview();

	}

}