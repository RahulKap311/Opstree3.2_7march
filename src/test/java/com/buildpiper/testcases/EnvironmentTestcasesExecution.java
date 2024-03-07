package com.buildpiper.testcases;

import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.listeners.RetryCountIfFailed;
import com.buildpiper.pages.BuildPipeLinePage;
import com.buildpiper.pages.EnvironmentCreationPage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.pages.ServiceCreationPage;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.XlsReadData;

/**
 * @author: SagarT
 * @reviewer: @
 * 
 *
 */
@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class EnvironmentTestcasesExecution extends BaseTest {

	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
    XlsReadData reader = new XlsReadData(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\BuildPiperTestData.xlsx");

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
	@RetryCountIfFailed(2)
	public void CreateEnvironment() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("qa");
		//list.add("dev");
		//list.add("staging");
		//list.add("uat");
		//list.add("prod");
		
		ArrayList<String> configTypelist = new ArrayList<String>();
		//configTypelist.add("Upload Custom Manifest file / GitOps");
		configTypelist.add("Guided Form");
		//configTypelist.add("Use Editor");

		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		//new ServiceCreationPage().accountPreRequisites();
		new EnvironmentCreationPage().CreateAndValidateEnvironment(reader.getCellData("Environment", "applicationName", 2),list,reader.getCellData("Environment", "selectCluster", 2),reader.getCellData("Environment", "selectNamespace", 2),reader.getCellData("Environment", "selectRegistry", 2));
		//new EnvironmentCreationPage().EnvironmentConfigurationAndAssociateServices(reader.getCellData("Environment", "applicationName", 2),configTypelist,reader.getCellData("Environment", "associateConfigurationType", 2),reader.getCellData("Environment", "existingEnv", 2));
		//new EnvironmentCreationPage().CreateAndValidateEnvironment("poc-qa",list,"new-saas-cluster","poc-non-prod","nexus");

	}
	@Test(groups = { "Regression" },priority = 0)
	@RetryCountIfFailed(2)
	public void CreateEnvironmentwithBPConfigMap() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("qa");
		//list.add("dev");
		//list.add("staging");
		//list.add("uat");
		//list.add("prod");
		
		ArrayList<String> configTypelist = new ArrayList<String>();
		//configTypelist.add("Upload Custom Manifest file / GitOps");
		configTypelist.add("Guided Form");
		  String Git="test_config : https://github.com/sagarthakurnm/Test_config";
	      String Branch="main";
	      String Path="config/key_value.txt";
		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new EnvironmentCreationPage().CreateEnvironmentandBPManagedConfigMap(reader.getCellData("Environment", "applicationName", 2),list,reader.getCellData("Environment", "selectCluster", 2),reader.getCellData("Environment", "selectNamespace", 2),reader.getCellData("Environment", "selectRegistry", 2),Git,Branch,Path);
		
	}

	@Test(groups = { "Regression" },priority = 0)
	@RetryCountIfFailed(2)
	public void CreateEnvironmentwithSecret() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("qa");
		//list.add("dev");
		//list.add("staging");
		//list.add("uat");
		//list.add("prod");
		
		ArrayList<String> configTypelist = new ArrayList<String>();
		//configTypelist.add("Upload Custom Manifest file / GitOps");
		configTypelist.add("Guided Form");
		String Git="test_config : https://github.com/sagarthakurnm/Test_config";
	    String Branch="main";
	    String Path="config/key_value.txt";

		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new EnvironmentCreationPage().CreateEnvironmentwithSecret(reader.getCellData("Environment", "applicationName", 2),list,reader.getCellData("Environment", "selectCluster", 2),reader.getCellData("Environment", "selectNamespace", 2),reader.getCellData("Environment", "selectRegistry", 2),Git,Branch,Path);
		
	}

	@Test(groups = { "Regression" },priority = 0)
	@RetryCountIfFailed(2)
	public void CreateEnvironmentwithSecret_KeyValuePair() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("qa");
		//list.add("dev");
		//list.add("staging");
		//list.add("uat");
		//list.add("prod");
		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new EnvironmentCreationPage().CreateEnvironmentwithSecret_KeyValuePair(reader.getCellData("Environment", "applicationName", 2),list,reader.getCellData("Environment", "selectCluster", 2),reader.getCellData("Environment", "selectNamespace", 2),reader.getCellData("Environment", "selectRegistry", 2),"key1","value1");
		
	}

	
	@Test(groups = { "Regression" },priority = 1)
	@RetryCountIfFailed(2)
	public void ConfigureEnvironment() {

		ArrayList<String> configTypelist = new ArrayList<String>();
		//configTypelist.add("Upload Custom Manifest file / GitOps");
		configTypelist.add("Guided Form");
		//configTypelist.add("Use Editor");

		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new EnvironmentCreationPage().EnvironmentConfigurationAndAssociateServices(reader.getCellData("Environment", "applicationName", 2),configTypelist,reader.getCellData("Environment", "associateConfigurationType", 2),reader.getCellData("Environment", "existingEnv", 2));
		//new EnvironmentCreationPage().EnvironmentConfigurationAndAssociateServices("poc-qa",configTypelist,"Upload a file containing Key Values","dev-8yi1sr");

	}
	
	@Test(groups = { "Regression" },priority = 2)
	@RetryCountIfFailed(2)
	public void ConfigureSecrets() {

		ArrayList<String> secretKeyTypelist = new ArrayList<String>();
		//secretKeyTypelist.add("Upload Custom Manifest file / GitOps");
		secretKeyTypelist.add("Guided Form");
		//secretKeyTypelist.add("Use Editor");
		
		ArrayList<String> secretTypeList = new ArrayList<String>();
		//secretTypeList.add("fileupload");
		secretTypeList.add("manual_value");
		//secretTypeList.add("vcs");

	//	new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		new EnvironmentCreationPage().EnvironmentSecrets(reader.getCellData("Environment", "applicationName", 2),reader.getCellData("Environment", "existingEnv", 2),secretKeyTypelist,secretTypeList);
		//new EnvironmentCreationPage().EnvironmentSecrets("poc-qa",configTypelist,"Upload a file containing Key Values","dev-8yi1sr");

	}
	
	@Test(groups = { "Regression" },priority = 3)
	@RetryCountIfFailed(2)
	public void DowntimeScaleUp() {

		ArrayList<String> userList = new ArrayList<String>();
		userList.add("DEVOPS");
		userList.add("DEV");
		userList.add("QA");
		
		ArrayList<String> secretTypeList = new ArrayList<String>();
		//secretTypeList.add("fileupload");
		secretTypeList.add("manual_value");
		//secretTypeList.add("vcs");
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		//new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new EnvironmentCreationPage().EnvironmentDownTimeSchedulerScaleUp(reader.getCellData("Environment", "applicationName", 5),reader.getCellData("Environment", "existingEnv", 5),reader.getCellData("Environment", "timeZone", 5),userList);
		//new EnvironmentCreationPage().EnvironmentDownTimeScheduler("poc-qa",configTypelist,"Upload a file containing Key Values","dev-8yi1sr");

	}
	
	
	@Test(groups = { "Regression" },priority = 4)
	@RetryCountIfFailed(2)
	public void DowntimeScaleDown() {

		ArrayList<String> userList = new ArrayList<String>();
		userList.add("DEVOPS");
		userList.add("DEV");
		userList.add("QA");
		new PreRequisitesPage().switchUser();
		ui_wait(5);
	//	new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new EnvironmentCreationPage().EnvironmentDownTimeSchedulerScaleDown(reader.getCellData("Environment", "applicationName", 6),reader.getCellData("Environment", "existingEnv", 6),reader.getCellData("Environment", "timeZone", 6),userList);
		//new EnvironmentCreationPage().EnvironmentDownTimeScheduler("poc-qa",configTypelist,"Upload a file containing Key Values","dev-8yi1sr");

	}
	
	@Test(groups = { "Regression" },priority = 5)
	@RetryCountIfFailed(2)
	public void CreateEnvironmentNegativeTest1() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("qa");
		//list.add("dev");
		//list.add("staging");
		//list.add("uat");
		//list.add("prod");

		new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new EnvironmentCreationPage().CreateAndValidateEnvironmentNegativeTest1(reader.getCellData("Environment", "applicationName", 5),list,reader.getCellData("Environment", "selectCluster", 5),reader.getCellData("Environment", "selectNamespace", 5),reader.getCellData("Environment", "selectRegistry", 5),reader.getCellData("Environment", "existingEnv", 5));
		//new EnvironmentCreationPage().CreateAndValidateEnvironment("poc-qa",list,"new-saas-cluster","poc-non-prod","nexus");

	}
	
	@Test(groups = { "Regression" },priority = 6)
	@RetryCountIfFailed(2)
	public void DowntimeEnvNegativeTest1() {

		ArrayList<String> userList = new ArrayList<String>();
		userList.add("DEVOPS");
		userList.add("DEV");
		userList.add("QA");

		new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new EnvironmentCreationPage().EnvironmentDownTimeSchedulerScaleDownNegativeTest2(reader.getCellData("Environment", "applicationName", 5),reader.getCellData("Environment", "existingEnv", 5),reader.getCellData("Environment", "timeZone", 5),userList,reader.getCellData("Environment", "existingSchedulerName", 5));
		//new EnvironmentCreationPage().EnvironmentDownTimeScheduler("poc-qa",configTypelist,"Upload a file containing Key Values","dev-8yi1sr");

	}
	
	@Test(groups = { "Regression" },priority = 7)
	@RetryCountIfFailed(2)
	public void DowntimeEnvNegativeTest2() {

		ArrayList<String> userList = new ArrayList<String>();
		userList.add("DEVOPS");
		userList.add("DEV");
		userList.add("QA");

		new LoginPage().login(reader.getCellData("UserData", "username", 2), reader.getCellData("UserData", "password", 2));
		new EnvironmentCreationPage().EnvironmentDownTimeSchedulerScaleDownNegativeTest3(reader.getCellData("Environment", "applicationName", 5),reader.getCellData("Environment", "existingEnv", 5),reader.getCellData("Environment", "timeZone", 5),userList);
		//new EnvironmentCreationPage().EnvironmentDownTimeScheduler("poc-qa",configTypelist,"Upload a file containing Key Values","dev-8yi1sr");

	}
	
	@Test(groups = { "Regression" }, priority = 0)
//	@RetryCountIfFailed(2)
	public void EnvironmentLevelSearch() throws Exception {
    String Environment="dev-";
    String EnvName="QA";

		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(4);
		new EnvironmentCreationPage().SeacrhEnvironment(
				reader.getCellData("Environment", "applicationName", 2),Environment);
		//new EnvironmentCreationPage().SeacrhEnvironmentOverviewbyEnvironment(EnvName);
	}	
	
	@Test(groups = { "Regression" }, priority = 0)
//	@RetryCountIfFailed(2)
	public void DeleteEnvironment() throws Exception {
    ArrayList<String> list = new ArrayList<String>();
	list.add("qa");
	// list.add("dev");
	// list.add("staging");
	// list.add("uat");
	// list.add("prod");

	ArrayList<String> configTypelist = new ArrayList<String>();
	// configTypelist.add("Upload Custom Manifest file / GitOps");
	configTypelist.add("Guided Form");
	// configTypelist.add("Use Editor");
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(4);
		new EnvironmentCreationPage().DeleteEmptyEnvironment(
				reader.getCellData("Environment", "applicationName", 2), list,
				reader.getCellData("Environment", "selectCluster", 2),
				reader.getCellData("Environment", "selectNamespace", 2),
				reader.getCellData("Environment", "selectRegistry", 2));
		/*ui_wait(5);
		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(4);
		new EnvironmentCreationPage().DeleteNonEmptyEnvironment(
				reader.getCellData("Environment", "applicationName", 2), list,
				reader.getCellData("Environment", "selectCluster", 2),
				reader.getCellData("Environment", "selectNamespace", 2),
				reader.getCellData("Environment", "selectRegistry", 2));*/
	}

	@Test(groups = { "Regression" }, priority = 0)
//	@RetryCountIfFailed(2)
	public void CreateEnvironemntsWithVersioning() throws Exception {
		String AppName=reader.getCellData("Environment", "applicationName", 2);
		String BranchName=reader.getCellData("Environment", "selectCluster", 2);
		String NameSpace=reader.getCellData("Environment", "selectNamespace", 2);
		String Registry=reader.getCellData("Environment", "selectRegistry", 2);
		
    ArrayList<String> list = new ArrayList<String>();
	list.add("DEV");
	list.add("QA");
	list.add("UAT");
	list.add("STAGING");
    //list.add("PROD");

		//new LoginPage().login(config.username(), config.password());
		//Verify Environment Versioning is Enabled
		for(int i=0;i<list.size();i++) {
			ui_wait(4);
			new EnvironmentCreationPage().ChangeVersioning("Deploy",list.get(i),"false");
			ui_wait(4);
			new EnvironmentCreationPage().ChangeVersioning("Build",list.get(i),"false");
			ui_wait(4);	
			new PreRequisitesPage().switchUser();
			new EnvironmentCreationPage().AddEnvironemntsWithVersioning(AppName,list.get(i),BranchName,NameSpace,Registry,"false");
			new PreRequisitesPage().switchToAdmin();
		}
		//Verify Environment Versioning is Dissable
		for(int i=0;i<list.size();i++) {
			ui_wait(4);
			new EnvironmentCreationPage().ChangeVersioning("Deploy",list.get(i),"true");
			ui_wait(4);
			new EnvironmentCreationPage().ChangeVersioning("Build",list.get(i),"true");
			ui_wait(4);	
			new PreRequisitesPage().switchUser();
			new EnvironmentCreationPage().AddEnvironemntsWithVersioning(AppName,list.get(i),BranchName,NameSpace,Registry,"true");
			new PreRequisitesPage().switchToAdmin();
		}
		
	
	
	}


}

