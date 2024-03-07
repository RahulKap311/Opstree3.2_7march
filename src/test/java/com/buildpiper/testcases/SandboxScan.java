package com.buildpiper.testcases;

import java.text.ParseException;
import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.mozilla.javascript.tools.shell.Environment;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.BuildConfigurationPage;
import com.buildpiper.pages.BuildPipeLinePage;
import com.buildpiper.pages.DeployConfigurationPage;
import com.buildpiper.pages.EnvironmentCreationPage;
import com.buildpiper.pages.HomePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.pages.ServiceCreationPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.Pause;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class SandboxScan extends BaseTest {
	
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();
	
	 @BeforeMethod
	    public void StartDriver() {
	   // 	new LoginPage().login(config.username(), config.password());
	    	ui_wait(5);
	    }
	 @AfterMethod
	   public void StopDriver() {
    	ui_getUIDriver().quit();
	    }
	 
	 @Test(groups = { "Regression" }, priority = 0)
//		@RetryCountIfFailed(2)
		public void ServiceOverviewSearchUI() throws Exception {
		int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "ServiceOverviewSearchUI");
			//new LoginPage().login(config.username(), config.password());
		
		ArrayList<String> logtabs1 = new ArrayList<String>();
		    logtabs1.add("Cloning Repository");
		    logtabs1.add("Pre Hooks Executing");
		    logtabs1.add("Cred Scanner");
		    logtabs1.add("Build Docker Image");
		    logtabs1.add("Push docker image");
		    logtabs1.add("Post Hooks Executing");
		    logtabs1.add("IMAGE_CLEANUP");
		    logtabs1.add("WORKSPACE_PUBLISHER");
		    
			ArrayList<String> logtabs1_1 = new ArrayList<String>();
			logtabs1_1.add("Cloning Repository");
			logtabs1_1.add("Pre Hooks Executing");
			logtabs1_1.add("Trivy-File-Secret-Scanning");
			logtabs1_1.add("Trivy-File-license-Scanning");
			logtabs1_1.add("Trivy-File-vuln-Scanning");		
			logtabs1_1.add("Cred Scanner");
			logtabs1_1.add("Bandit-Scan");
			logtabs1_1.add("Sonar-Scanner");
		    
		    ArrayList<String> logtabs2 = new ArrayList<String>();
		    logtabs2.add("Cloning Repository");
		    logtabs2.add("Pre Hooks Executing");
		    logtabs2.add("Cred Scanner");
		    logtabs2.add("Build Docker Image");
		    logtabs2.add("Push docker image");
		    logtabs2.add("Post Hooks Executing");
		    logtabs2.add("IMAGE_CLEANUP");
		    logtabs2.add("WORKSPACE_PUBLISHER");
		    
			ArrayList<String> logtabs3 = new ArrayList<String>();
			logtabs3.add("Cloning Repository");
			logtabs3.add("Pre Hooks Executing");
			logtabs3.add("Cred Scanner");
			logtabs3.add("Build Docker Image");
			logtabs3.add("Push docker image");
			logtabs3.add("Post Hooks Executing");
			logtabs3.add("Slack Notify");
			logtabs3.add("IMAGE_CLEANUP");
			logtabs3.add("WORKSPACE_PUBLISHER");
			
		
		    
			new PreRequisitesPage().switchUser();
			ui_wait(5);
			new ServiceCreationPage().SearchServiceandValidateUI(
					reader.getCellData("MicroServiceData", "applicationName", RowNumber),reader.getCellData("MicroServiceData", "serviceName", RowNumber),logtabs1,logtabs2,logtabs3);
			ui_wait(2);
			new PreRequisitesPage().switchToAdmin();
			ui_wait(4);
			new PreRequisitesPage().switchUser();
			ui_wait(4);
			new ServiceCreationPage().SearchServiceandValidateUI(
					reader.getCellData("MicroServiceData", "applicationName", RowNumber+1),reader.getCellData("MicroServiceData", "serviceName", RowNumber+1),logtabs1_1,logtabs2,logtabs3);
		
		}	
	 
		@Test(groups = { "Regression" }, priority = 0)
//		@RetryCountIfFailed(2)
		public void VerifyEnvironmentDetail() throws Exception {
			  String Environment="qa-main";
			    String EnvName="QA";

					//new LoginPage().login(config.username(), config.password());
					new PreRequisitesPage().switchUser();
					ui_wait(5);
					new EnvironmentCreationPage().VerifyEnvironmentDetail(
							reader.getCellData("Environment", "applicationName", 2),Environment);
					//new EnvironmentCreationPage().SeacrhEnvironmentOverviewbyEnvironment(EnvName);
				}

	
	@Test(groups = { "Regression" },priority = 0)
	public void JiraExtensivePipeline_RunwithParameter() throws ParseException {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "JiraExtensivePipeline_RunwithParameter");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().JiraExtensivePipeline_RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(2);
		new PreRequisitesPage().switchToAdmin();
		ui_wait(2);
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().VerifyPipelineOrderonHistory(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
	
		ui_wait(3);
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void MobileCI_DEV_RunwithParameter() throws ParseException {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "MobileCI_DEV_RunwithParameter");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().MobileCI_DEV_RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(2);
		new PreRequisitesPage().switchToAdmin();
		ui_wait(2);
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().VerifyPipelineOrderonHistory(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
	
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void MobileCI_PROD_RunwithParameter() throws ParseException {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "MobileCI_PROD_RunwithParameter");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().MobileCI_PROD_RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(2);
		new PreRequisitesPage().switchToAdmin();
		ui_wait(2);
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().VerifyPipelineOrderonHistory(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
	
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void Jira() {
		ui_wait(5);
	
		new BuildPipeLinePage().Jira();
		ui_wait(3);
	}

}