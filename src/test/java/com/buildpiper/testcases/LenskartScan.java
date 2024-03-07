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

public class LenskartScan extends BaseTest {
	
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
	
	@Test(groups = { "Regression" },priority = 0)
	public void LoginViaCreds() {
		ui_getUIDriver().quit();
		new LoginPage().login(config.username(), config.password());

	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void LoginViaGitLab() {
		ui_getUIDriver().quit();
		new LoginPage().loginPageViaGitLab("devops-reader", "Va8?+.E8MU\"j^9W_");

	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void HealthCheck() {
		
		//new LoginPage().login(config.username(), config.password());
		new HomePage().HealthQueue(config.tag());
		
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void BannerTest() {
		
	//	new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(3);
     	new HomePage().LenskartBanner();
		
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void PagginationofAllModules() {
		
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(3);
		new HomePage().Pagination(reader.getCellData("MicroServiceData", "applicationName", 2));
		
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void UserportalGeneral() {
		
	//	new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(3);
		new HomePage().VerifyAllModulesAccessible(reader.getCellData("MicroServiceData", "applicationName", 2));
		
	}
	/*
	@Test(groups = { "Regression" },priority = 0)
   public void EditService() {
	
	//new LoginPage().login(config.username(), config.password());
	new PreRequisitesPage().switchUser();
	ui_wait(3);
	new ServiceCreationPage().editService(reader.getCellData("MicroServiceData", "applicationName", 2));
	
}*/
	
	@Test(groups = { "Regression" },priority = 0)
	   public void EditPipeline() {
		
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(3);
		new BuildPipeLinePage().editPipeline(reader.getCellData("MicroServiceData", "applicationName", 2));
		
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void HistoryPipeline() {
		
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(3);
		new BuildPipeLinePage().historyPipeline(reader.getCellData("MicroServiceData", "applicationName", 2));
		
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void RunwithParameterPipeline() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "RunwithParameterPipeline");		
		new PreRequisitesPage().switchUser();
		ui_wait(3);
		new BuildPipeLinePage().RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void JobStatusInsidePipleineforMultipleService() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "JobStatusInsidePipleineforMultipleService");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().FindStatusandBranch(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void ReplayPipeline() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "ReplayPipeline");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().ReplayPipeline(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}
	@Test(groups = { "Regression" },priority = 0)
	public void SearchPipelineButtonExistance() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "ReplayPipeline");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().VerifyPipelineButtons(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void PipelineOrderonHistory() throws ParseException {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "ReplayPipeline");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().VerifyPipelineOrderonHistory(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void ManualBuildandDeployHelmService() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "ManualBuildandDeployHelmService");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new ServiceCreationPage().SearchServiceViaRandomStringValue(reader.getCellData("MicroServiceData", "applicationName", RowNumber),reader.getCellData("MicroServiceData", "serviceName", RowNumber));
		ui_wait(10);
		ui_IsElementDisplay(ui_waitForElementToDisplay(new ServiceCreationPage().buildArtifact, Pause.MEDIUM));
		String ArtifactID=new ServiceCreationPage().buildArtifact.getText();
		
		
		//build Service
		ui_wait(2);
		new ServiceCreationPage().buildButton_Click();
		ui_wait(1);
		new ServiceCreationPage().buildButton_Click();
		ui_wait(5);
		new ServiceCreationPage().Verify_EnvironmentandSubEnvironment("DEV",reader.getCellData("MicroServiceData", "envName", RowNumber));
		ui_wait(3);
		new ServiceCreationPage().triggerBuild_Click();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		//new ServiceCreationPage().Verify_buildStatus("RUNNING");
		ui_wait(120);
		new ServiceCreationPage().buildRecentButtonClick();
		ui_switchToNewWindow();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_buildStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeBuildWindow();
		ui_wait(3);		
		
		//Deploy Service
		new ServiceCreationPage().deployService(ArtifactID);
		ui_wait(3);
		new ServiceCreationPage().Verify_deployStatus("RUNNING");
		ui_wait(4);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(60);
		new ServiceCreationPage().deployRecentButtonClick();
		ui_switchToNewWindow();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(30);
		new ServiceCreationPage().Verify_deployStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeDeployWindow();
		ui_wait(3);
		
		//Promote Service		
		//new ServiceCreationPage().promoteService("test",ArtifactID);
		new ServiceCreationPage().promoteService(reader.getCellData("MicroServiceData", "toEnv", 2),ArtifactID);
		ui_wait(3);
		//new ServiceCreationPage().Verify_promoteStatus("RUNNING");	
		ui_wait(4);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(40);
		new ServiceCreationPage().promoteRecentButtonClick();
		ui_switchToNewWindow();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		ui_wait(30);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_promoteStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeDeployWindow();
		ui_wait(3);
				
		new ServiceCreationPage().switchEnvironmentTab("STAGING");
		ui_wait(20);
		String ArtifactID1=new ServiceCreationPage().deployandPromoteartifactID1.getText();
		Assert.assertEquals(ArtifactID, ArtifactID1);
		new ServiceCreationPage().switchEnvironmentTab("DEV");
		ui_wait(10);
		
		//monitoring Service
		 new ServiceCreationPage().monitorService();
		 ui_wait(3);	
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void MultideployService() {

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		new DeployConfigurationPage().SetupOtherDeployment(reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
	}
	
	@Test(groups = { "Regression" },priority = 0)
	   public void EditEnvironment() throws Exception {
		
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(3);
		new EnvironmentCreationPage().editEnvironment(reader.getCellData("MicroServiceData", "applicationName", 2));
		
	}
	

	@Test(groups = { "Regression" },priority = 0)
	public void JiraExtensivePipeline_RunwithParameter() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "JiraExtensivePipeline_RunwithParameter");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().JiraExtensivePipeline_RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void MobileCI_DEV_RunwithParameter() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "MobileCI_DEV_RunwithParameter");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().MobileCI_DEV_RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}
	
	@Test(groups = { "Regression" },priority = 0)
	public void MobileCI_PROD_RunwithParameter() {
		//new LoginPage().login(config.username(), config.password());
		int RowNumber=reader.getRowByTestCaseName("Pipeline", "MobileCI_PROD_RunwithParameter");		
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new BuildPipeLinePage().MobileCI_PROD_RunwithParameter(reader.getCellData("Pipeline", "applicationName", RowNumber),reader.getCellData("Pipeline", "existingPipeline", RowNumber));
		ui_wait(3);
	}

}