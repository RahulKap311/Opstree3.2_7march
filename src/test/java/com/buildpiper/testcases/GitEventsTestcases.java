package com.buildpiper.testcases;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.GitEventsPage;
import com.buildpiper.pages.JobTemplatePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.RandomStrings;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class GitEventsTestcases extends BaseTest {

	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();
	 @BeforeMethod
	    public void StartDriver() {
	    	new LoginPage().login(config.username(), config.password());
	    	ui_wait(5);
	    }
	   @AfterMethod
	    public void StopDriver() {
	    ui_getUIDriver().quit();
	    }

	@Test(groups = { "Regression" }, priority = 0)
//	@RetryCountIfFailed(2)
	public void AddNewListener() {
	String ListenerName="Test-NewListener"+RandomStrings.generateRandomString(4);
	int RowNumber=reader.getRowByTestCaseName("GitEvents", "AddNewListener");
	String gitURL=reader.getCellData("GitEvents", "gitURL", RowNumber);
	String BranchName=reader.getCellData("GitEvents", "BranchName", RowNumber);
	String ApplicationName=reader.getCellData("GitEvents", "ApplicationName", RowNumber);
	String Environment=reader.getCellData("GitEvents", "Environment", RowNumber);
	String Service=reader.getCellData("GitEvents", "Service", RowNumber);
	String Template=reader.getCellData("GitEvents", "Template", RowNumber);
	String Job=reader.getCellData("GitEvents", "Job", RowNumber);
	new PreRequisitesPage().switchUser();
	ui_wait(5);
	new GitEventsPage().AddListener(ListenerName,gitURL,BranchName,ApplicationName,Environment,Service,Template,Job);
	}
	
}
