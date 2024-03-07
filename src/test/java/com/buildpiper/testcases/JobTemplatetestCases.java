package com.buildpiper.testcases;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.JobTemplatePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class JobTemplatetestCases extends BaseTest {

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
	public void v3TemplateActions() {

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new JobTemplatePage().V3MultipleIntegrationSupport(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "templateName", 2),
				reader.getCellData("MicroServiceData", "buildImageName", 2),
				reader.getCellData("MicroServiceData", "manifestExecuteImageName", 2),
				reader.getCellData("MicroServiceData", "s3FileUploadImageName", 2),
				reader.getCellData("MicroServiceData", "s3FileDownloadImageName", 2),
				reader.getCellData("MicroServiceData", "manageRemoteProcessImageName", 2),
				reader.getCellData("MicroServiceData", "secureCopyImageName", 2),
				reader.getCellData("MicroServiceData", "k8ManifestApplyImageName", 2),
				reader.getCellData("MicroServiceData", "googleChatNotificationImageName", 2),
				reader.getCellData("MicroServiceData", "cloneRepositoryImageName", 2),
				reader.getCellData("MicroServiceData", "dockerImageBuildName", 2),
				reader.getCellData("MicroServiceData", "workSpaceName", 2),
				reader.getCellData("MicroServiceData", "instructionsName", 2),
				reader.getCellData("MicroServiceData", "step1", 2), reader.getCellData("MicroServiceData", "step2", 2),
				reader.getCellData("MicroServiceData", "step3", 2), reader.getCellData("MicroServiceData", "step4", 2),
				reader.getCellData("MicroServiceData", "step5", 2), reader.getCellData("MicroServiceData", "step6", 2),
				reader.getCellData("MicroServiceData", "step7", 2), reader.getCellData("MicroServiceData", "step8", 2),
				reader.getCellData("MicroServiceData", "step9", 2),
				reader.getCellData("MicroServiceData", "step10", 2),
				reader.getCellData("MicroServiceData", "variableName", 2),
				reader.getCellData("MicroServiceData", "variableValue", 2));
	}

}