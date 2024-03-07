package com.buildpiper.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.JobExecutionActivity;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

@Listeners(com.buildpiper.report.ExtentReportListener.class)


public class JobExecutionActivityTestcases extends BaseTest {
	
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
	public void PaginationCheck() {
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(5);
		new JobExecutionActivity()
		// .SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		 .PaginationCheck("10");
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void ActivityFilterby_Status() {
		ArrayList<String> stausList = new ArrayList<String>();
		stausList.add("Success");
		stausList.add("Failed");
		stausList.add("Revoked");
		//stausList.add("InQueue");
		//stausList.add("Running");
		
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(3);
		new JobExecutionActivity()
		//.SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		.ActivityFilterby_Status(stausList);
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void ActivityFilterby_Environment() {
		ArrayList<String> envList = new ArrayList<String>();
		envList.add("dev");
		envList.add("qa");
		envList.add("uat");
		envList.add("prod");
		
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(3);
		new JobExecutionActivity()
		.SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		.ActivityFilterby_Environment(envList);
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void ActivityFilterby_App() {
				
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(3);
		new JobExecutionActivity()
		.SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		.ActivityFilterby_App(reader.getCellData("MicroServiceData", "applicationName", 2));
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void ActivityFilterby_Users() {
				
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(3);
		new JobExecutionActivity()
		.SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		.ActivityFilterby_Users("Super Admin");
		
	}
	@Test(groups = { "Regression" }, priority = 0)
	public void VerifyServiceLink() {
		
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(3);
		new JobExecutionActivity()
		.SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		.VerifyServiceLink();
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void VerifyResetandClearSelection() {
		
		new PreRequisitesPage().SelectJobExecutionActivityMenu();
		ui_wait(3);
		new JobExecutionActivity()
		.SelectProject(reader.getCellData("MicroServiceData", "applicationName", 2))
		.VerifyResetLink()
		.VerifyClearSelection();
		
	}
}
