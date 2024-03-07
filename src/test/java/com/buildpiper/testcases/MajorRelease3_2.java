package com.buildpiper.testcases;

import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.configuration.Config;
import com.buildpiper.base.BaseTest;
import com.buildpiper.listeners.RetryCountIfFailed;
import com.buildpiper.pages.BuildConfigurationPage;
import com.buildpiper.pages.BuildPipeLinePage;
import com.buildpiper.pages.DeployConfigurationPage;
import com.buildpiper.pages.EnvironmentCreationPage;
import com.buildpiper.pages.HomePage;
import com.buildpiper.pages.JobTemplatePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.pages.ServiceCreationPage;
import com.buildpiper.pages.SuperAdminPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.Pause;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class MajorRelease3_2 extends BaseTest {
	
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();
	
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
		ui_getUIDriver().quit();
	}
	
	@Test(groups = { "Regression" },priority = 1)
//	@RetryCountIfFailed(2)
	public void userBulkUpload() {
		
		new LoginPage().login(config.username(), config.password());
		new SuperAdminPage().uploadBulkUser();
		new SuperAdminPage().ClusterOverview();
		ui_getUIDriver().quit();

	}
	
	@Test(groups = { "Regression" },priority = 2)
//	@RetryCountIfFailed(2)
	public void BPVersioning() {
		
		ArrayList<String> chipList = new ArrayList<String>();
//		chipList.add(" All");
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");
//		chipList.add("linux/386");
//		chipList.add("linux/riscv64");
//		chipList.add("linux/ppc64le");
//		chipList.add("linux/s390x");
//		chipList.add("linux/arm/v7");
//		chipList.add("linux/arm/v6");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");
//		languageList.add("GOLANG");
//		languageList.add("PHP");
//		languageList.add("PYTHON");
//		languageList.add("NODEJS");
//		languageList.add("OTHER");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");
		
			new LoginPage().login(config.username(), config.password());
			new PreRequisitesPage().systemSettings();
			new SuperAdminPage().BuildPiperVersioningDisable();
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
			new BuildConfigurationPage().validateInUseText();
			new PreRequisitesPage().switchToAdmin();
			new PreRequisitesPage().systemSettings();
			new SuperAdminPage().BuildPiperVersioningEnable();
			ui_getUIDriver().quit();

	}
	
	@Test(groups = { "Regression" },priority = 3)
//	@RetryCountIfFailed(2)
	public void configMapAndSecretVersioning() {
		
		ArrayList<String> configTypelist = new ArrayList<String>();
		//configTypelist.add("Upload Custom Manifest file / GitOps");
		configTypelist.add("Guided Form");
		//configTypelist.add("Use Editor");
		
		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new EnvironmentCreationPage().EnvironmentConfigurationAndAssociateServices(reader.getCellData("Environment", "applicationName", 2),configTypelist,reader.getCellData("Environment", "associateConfigurationType", 2),reader.getCellData("Environment", "existingEnv2", 2));
		ui_getUIDriver().quit();

	}
	
	@Test(groups = { "Regression" }, priority = 4)
	public void createServ() {

		ArrayList<String> chipList = new ArrayList<String>();
//		chipList.add(" All");
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");
//		chipList.add("linux/386");
//		chipList.add("linux/riscv64");
//		chipList.add("linux/ppc64le");
//		chipList.add("linux/s390x");
//		chipList.add("linux/arm/v7");
//		chipList.add("linux/arm/v6");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");
//		languageList.add("GOLANG");
//		languageList.add("PHP");
//		languageList.add("PYTHON");
//		languageList.add("NODEJS");
//		languageList.add("OTHER");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		new LoginPage().login(config.username(), config.password());
		new HomePage().HealthQueue(config.tag());
		new PreRequisitesPage().defaultQueueTest(reader.getCellData("userPreReqData", "adminDeployWorker", 2),
				reader.getCellData("userPreReqData", "adminPublicApiWorker", 2),
				reader.getCellData("userPreReqData", "bpBuildWorker", 2),
				reader.getCellData("userPreReqData", "bpDeployWorker", 2),
				reader.getCellData("userPreReqData", "deployApiWorker", 2),
				reader.getCellData("userPreReqData", "publicApiWorker", 2),
				reader.getCellData("userPreReqData", "schedulerWorker", 2),
				reader.getCellData("userPreReqData", "versioningWorker", 2));
//		new PreRequisitesPage().accountPreRequisites();
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
		new BuildConfigurationPage().approveConfiguartions();
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		new BuildConfigurationPage().approveConfiguartionsDeploy();
//		new ServiceCreationPage().addNewEnvironmentToService(reader.getCellData("MicroServiceData", "toEnv", 2), list,
//				reader.getCellData("MicroServiceData", "JobTemplateValue", 2),
//				reader.getCellData("MicroServiceData", "cloneText", 2),
//				reader.getCellData("MicroServiceData", "envCloneValue", 2),
//				reader.getCellData("MicroServiceData", "BranchName", 2));
		new BuildConfigurationPage().approveConfiguartions();
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		new BuildConfigurationPage().approveConfiguartionsDeploy();
		ui_getUIDriver().quit();

	}
	
	@Test(groups = { "Regression" },priority = 5)
	@RetryCountIfFailed(2)
	public void BuildPipeLine() {
		
		new LoginPage().login(config.username(), config.password());
//		new ServiceCreationPage().accountPreRequisites();
		new PreRequisitesPage().switchUser();
		new BuildPipeLinePage().buildAndValidateConsolePage(reader.getCellData("Pipeline", "applicationName", 2),reader.getCellData("Pipeline", "existingPipeline", 2));
		ui_getUIDriver().quit();

	}
	
	@Test(groups = { "Regression" },priority = 5)
	@RetryCountIfFailed(2)
	public void MultipleIntegrationSupport() {
		
		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new BuildPipeLinePage().buildAndValidateConsolePage(reader.getCellData("Pipeline", "applicationName", 2),reader.getCellData("Pipeline", "existingPipeline", 2));
		ui_getUIDriver().quit();

	}

}