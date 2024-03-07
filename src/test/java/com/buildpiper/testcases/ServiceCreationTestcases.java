package com.buildpiper.testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.BuildConfigurationPage;
import com.buildpiper.pages.BuildDeployAlternatePage;
import com.buildpiper.pages.DeployConfigurationPage;
import com.buildpiper.pages.EnvironmentCreationPage;
import com.buildpiper.pages.HomePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.pages.ServiceCreationPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.testDataUtil;

/**
 * @author: SagarT
 * @reviewer: @
 * 
 *
 */

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class ServiceCreationTestcases extends BaseTest {

	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

//	@DataProvider
//	public Iterator<Object[]> createServiceTestData() {
//		ArrayList<Object[]> CreateServiceData = testDataUtil.getMicroServiceData();
//		return CreateServiceData.iterator();
//	}

	ExcelUtility reader = new ExcelUtility();

//	ArrayList<String> languageList = new ArrayList<String>();
//	ArrayList<String> chipList = new ArrayList<String>();
//	ArrayList<String> list = new ArrayList<String>();
	
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

		//new LoginPage().login(config.username(), config.password());
		//ui_wait(4);
//		new HomePage().HealthQueue();
//		new PreRequisitesPage().defaultQueueTest(reader.getCellData("userPreReqData", "adminDeployWorker", 2),
//				reader.getCellData("userPreReqData", "adminPublicApiWorker", 2),
//				reader.getCellData("userPreReqData", "bpBuildWorker", 2),
//				reader.getCellData("userPreReqData", "bpDeployWorker", 2),
//				reader.getCellData("userPreReqData", "deployApiWorker", 2),
//				reader.getCellData("userPreReqData", "publicApiWorker", 2),
//				reader.getCellData("userPreReqData", "schedulerWorker", 2),
//				reader.getCellData("userPreReqData", "versioningWorker", 2));
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
//		new BuildConfigurationPage().approveConfiguartions();
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
//		new BuildConfigurationPage().approveConfiguartionsDeploy();
		new ServiceCreationPage().addNewEnvironmentToService(reader.getCellData("MicroServiceData", "toEnv", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2),
				reader.getCellData("MicroServiceData", "cloneText", 2),
				reader.getCellData("MicroServiceData", "envCloneValue", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2));
//		new BuildConfigurationPage().approveConfiguartions();
//		new DeployConfigurationPage().CreateAndValidateDeployConfig(
//				reader.getCellData("MicroServiceData", "AccessType", 2),
//				reader.getCellData("MicroServiceData", "AccessName", 2),
//				reader.getCellData("MicroServiceData", "portNumber", 2),
//				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
//				reader.getCellData("MicroServiceData", "configName", 2));
//		new BuildConfigurationPage().approveConfiguartionsDeploy();
	}
		
	@Test(groups = { "Regression" }, priority = 0)
	public void EditandDeleteService() {
		ServiceCreationPage servicepage=new ServiceCreationPage();
		String EditService="";
		ArrayList<String> chipList = new ArrayList<String>();
//		chipList.add(" All");
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
	    new PreRequisitesPage().switchUser();
	    ui_wait(4);
		int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "createServ");
		servicepage.buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", RowNumber),
		reader.getCellData("MicroServiceData", "envName", RowNumber),
		reader.getCellData("MicroServiceData", "buildRadioButtonName", RowNumber), list,
		reader.getCellData("MicroServiceData", "JobTemplateValue", RowNumber));
		EditService=servicepage.servicename+"1";
		servicepage.editService(reader.getCellData("MicroServiceData", "applicationName", RowNumber),EditService);
		servicepage.deleteService(EditService);
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void EditandDeleteServiceEnvironment() {
		ServiceCreationPage servicepage=new ServiceCreationPage();
		BuildConfigurationPage buildConfig=new BuildConfigurationPage();
		DeployConfigurationPage deployConfig=new DeployConfigurationPage();
		ArrayList<String> chipList = new ArrayList<String>();
//		chipList.add(" All");
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
	    new PreRequisitesPage().switchUser();
	    ui_wait(4);
		int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "createServ");
		servicepage.buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", RowNumber),
		reader.getCellData("MicroServiceData", "envName", RowNumber),
		reader.getCellData("MicroServiceData", "buildRadioButtonName", RowNumber), list,
		reader.getCellData("MicroServiceData", "JobTemplateValue", RowNumber));
		buildConfig.CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", RowNumber),
				reader.getCellData("MicroServiceData", "BranchName", RowNumber),
				reader.getCellData("MicroServiceData", "FilePath", RowNumber),
				reader.getCellData("MicroServiceData", "DockerFilePath", RowNumber), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", RowNumber),
				reader.getCellData("MicroServiceData", "envName", RowNumber));
		deployConfig.CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", RowNumber),
				reader.getCellData("MicroServiceData", "AccessName", RowNumber),
				reader.getCellData("MicroServiceData", "portNumber", RowNumber),
				reader.getCellData("MicroServiceData", "TargetPort", RowNumber), serviceButton,
				reader.getCellData("MicroServiceData", "configName", RowNumber));
		String servicename=servicepage.servicename;
		servicepage.editandDeleteEnvironment(servicename);
		
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void createHPAService() {
		ServiceCreationPage servicepage=new ServiceCreationPage();
		BuildConfigurationPage buildConfig=new BuildConfigurationPage();
		DeployConfigurationPage deployConfig=new DeployConfigurationPage();
		ArrayList<String> chipList = new ArrayList<String>();
//		chipList.add(" All");
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
	    new PreRequisitesPage().switchUser();
	    ui_wait(4);
		int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "createServ");
		servicepage.buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", RowNumber),
				reader.getCellData("MicroServiceData", "envName", RowNumber),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", RowNumber), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", RowNumber));
		buildConfig.CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", RowNumber),
				reader.getCellData("MicroServiceData", "BranchName", RowNumber),
				reader.getCellData("MicroServiceData", "FilePath", RowNumber),
				reader.getCellData("MicroServiceData", "DockerFilePath", RowNumber), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", RowNumber),
				reader.getCellData("MicroServiceData", "envName", RowNumber));
		deployConfig.CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", RowNumber),
				reader.getCellData("MicroServiceData", "AccessName", RowNumber),
				reader.getCellData("MicroServiceData", "portNumber", RowNumber),
				reader.getCellData("MicroServiceData", "TargetPort", RowNumber), serviceButton,
				reader.getCellData("MicroServiceData", "configName", RowNumber));
		servicepage.CreateHPA(servicepage.servicename, "2", "4", "40", "60");
	}

	
	@Test(groups = { "Regression" }, priority = 0)
//	@RetryCountIfFailed(2)
	public void EditAndDeployHPA() throws Exception {
		ServiceCreationPage servicecreate=new ServiceCreationPage();
	int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "HPAOtherDeploymentInfo");
	String[] minreplication = {"1","2","3"};
	String[] maxreplication = {"3","4","5"};
	String[] cpuThreshold = {"20","40","60"};
	String[] memoryThreshold = {"40","60","80"};
		
	ui_wait(5);
	for(int i=0;i<minreplication.length;i++)
	{	
	new PreRequisitesPage().switchUser();
		
	servicecreate
		.SearchServiceViaServiceName(reader.getCellData("MicroServiceData", "applicationName", RowNumber),reader.getCellData("MicroServiceData", "serviceName", RowNumber))
		.VerifyOtherDeployDetailInfo(reader.getCellData("MicroServiceData", "serviceName", RowNumber),minreplication[i],maxreplication[i],cpuThreshold[i],memoryThreshold[i])
		.VerifyHPAMonitoring(minreplication[i],maxreplication[i]);
	new PreRequisitesPage().switchToAdmin();
	}
	}	

	@Test(groups = { "Regression" }, priority = 1)
	public void BuildDeployAlternate() {

		//new LoginPage().login(config.username(), config.password());		
		new ServiceCreationPage().accountPreRequisites();
		ui_wait(5);
		new BuildDeployAlternatePage().TriggerAlternateMethod(
				reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "serviceName", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2));

	}

	@Test(groups = { "Regression" }, priority = 2)
	public void CreateSesrviceNegativeTest1() {

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().CreateSesrviceNegativeTest3(
				reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2),
				reader.getCellData("MicroServiceData", "serviceName", 2));

	}

	@Test(groups = { "Regression" }, priority = 3)
	public void createHelmService() {
		ServiceCreationPage servicecreationpage=new ServiceCreationPage();
		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");
//		languageList.add("GOLANG");
//		languageList.add("PHP");
//		languageList.add("PYTHON");
//		languageList.add("NODEJS");
//		languageList.add("OTHER");
		int RowNumber=reader.getRowByTestCaseName("MicroServiceData", "createHelmService");
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		servicecreationpage.CreateHelmNewService(reader.getCellData("MicroServiceData", "applicationName", RowNumber),
				reader.getCellData("MicroServiceData", "envName", RowNumber),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", RowNumber), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", RowNumber),
				reader.getCellData("MicroServiceData", "gitURL", RowNumber),
				reader.getCellData("MicroServiceData", "BranchName", RowNumber),
				reader.getCellData("MicroServiceData", "FilePath", RowNumber),
				reader.getCellData("MicroServiceData", "DockerFilePath", RowNumber), languageList,
				reader.getCellData("MicroServiceData", "preHookPass", RowNumber),
				reader.getCellData("MicroServiceData", "deployEnvName", RowNumber),
				reader.getCellData("MicroServiceData", "key1", RowNumber), reader.getCellData("MicroServiceData", "value1", RowNumber),
				reader.getCellData("MicroServiceData", "key2", RowNumber), reader.getCellData("MicroServiceData", "value2", RowNumber),
				reader.getCellData("MicroServiceData", "key3", RowNumber), reader.getCellData("MicroServiceData", "value3", RowNumber),
				reader.getCellData("MicroServiceData", "uploadTypeName", RowNumber),
				reader.getCellData("MicroServiceData", "deployBranchName", RowNumber),
				reader.getCellData("MicroServiceData", "folderPath", RowNumber),
				reader.getCellData("MicroServiceData", "helmReleaseName", RowNumber),
				reader.getCellData("MicroServiceData", "folderPathValue", RowNumber));
		String Service=servicecreationpage.servicename;
		System.out.println("-----"+Service);
		ui_wait(10);       
		//build Service
		servicecreationpage.SearchServiceViaRandomStringValue(reader.getCellData("MicroServiceData", "applicationName", 2),Service);
		ui_wait(2);
		new ServiceCreationPage().buildButton_Click();
		ui_wait(1);
		//new ServiceCreationPage().buildButton_Click();
		ui_wait(5);
		new ServiceCreationPage().Verify_EnvironmentandSubEnvironment("DEV",reader.getCellData("MicroServiceData", "envName", 2));
		ui_wait(3);
		new ServiceCreationPage().triggerBuild_Click();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		//new ServiceCreationPage().Verify_buildStatus("RUNNING");
		ui_wait(90);
		//new ServiceCreationPage().Verify_buildStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeBuildWindow();
		ui_wait(8);
		new ServiceCreationPage().RefreshService_Click();
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(new ServiceCreationPage().buildArtifact, Pause.MEDIUM));
		String ArtifactID=new ServiceCreationPage().buildArtifact.getText();
		ui_wait(10);
					
		//Deploy Service
		new ServiceCreationPage().deployServicewithMultiDeployment(ArtifactID);
		ui_wait(10);
        //new ServiceCreationPage().Verify_deployStatus("RUNNING");
		ui_wait(4);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(60);
		//new ServiceCreationPage().Verify_deployStatus("RUNNING");
		ui_wait(30);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		//new ServiceCreationPage().Verify_deployStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeDeployWindow();
		ui_wait(3);
	

	}

	@Test(groups = { "Regression" }, priority = 5)
	public void CreateBuildXService() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

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

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		new LoginPage().login(reader.getCellData("UserData", "username", 2),
				reader.getCellData("UserData", "password", 2));
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 4),
				reader.getCellData("MicroServiceData", "envName", 4),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 4), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 4));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 4),
				reader.getCellData("MicroServiceData", "BranchName", 4),
				reader.getCellData("MicroServiceData", "FilePath", 4),
				reader.getCellData("MicroServiceData", "DockerFilePath", 4), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 4),
				reader.getCellData("MicroServiceData", "deployEnvName", 4));
		new DeployConfigurationPage().CreateAndValidateDeployConfig("PRIVATE", "OT", "8080", "8080", serviceButton,
				"autoconfig-mhikniisw");
	}

}
