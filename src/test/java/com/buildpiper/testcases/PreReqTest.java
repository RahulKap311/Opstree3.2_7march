package com.buildpiper.testcases;

import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.EnvironmentCreationPage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.pages.RepoIntegrationPage;
import com.buildpiper.pages.SuperAdminPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class PreReqTest extends BaseTest {

	@FindBy(xpath = "//h3[text()='There are no Code Repositories Integrated']")
	WebElement noDataText;
	
	@FindBy(xpath = "//a[@href='/secrets/list']")
	WebElement secretsLink;

	ArrayList<String> list = new ArrayList<String>();

	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();

	@Test(groups = { "Regression" }, priority = 0)
	public void OnboardApplicaton() {

		new LoginPage().login(config.username(), config.password());
		new SuperAdminPage().searchApplication(reader.getCellData("MicroServiceData", "applicationName", 2));
		new SuperAdminPage().createPreReqApplication(reader.getCellData("MicroServiceData", "applicationName", 2));

	}

	@Test(groups = { "Regression" }, priority = 1)
	public void OnboardEnvironment() {

		list.add("qa");
		// list.add("dev");
		// list.add("staging");
		// list.add("uat");
		// list.add("prod");

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new EnvironmentCreationPage().searchEnvironment(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new EnvironmentCreationPage().CreateAndValidateEnvironment(
				reader.getCellData("Environment", "applicationName", 2), list,
				reader.getCellData("Environment", "selectCluster", 2),
				reader.getCellData("Environment", "selectNamespace", 2),
				reader.getCellData("Environment", "selectRegistry", 2));

	}

	@Test(groups = { "Regression" }, priority = 1)
	public void OnboardRepo1() {

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new RepoIntegrationPage().SearchExisitingRepo(reader.getCellData("RepoIntegrationData", "existingRepo", 2));
		boolean switchTypeCheck = ui_IsElementPresent(noDataText, "5");
		if (switchTypeCheck == true) {
			ui_click(secretsLink, "secrets button under side bar");
			new RepoIntegrationPage().AddRepoOption1(reader.getCellData("RepoIntegrationData", "inetgrationType", 2),
					reader.getCellData("RepoIntegrationData", "secretName1", 2),
					reader.getCellData("RepoIntegrationData", "onboardGit1", 2),
					reader.getCellData("RepoIntegrationData", "ValidationMessage", 2));
		}else {
			System.out.println("Repo already exists");
		}

	}

	@Test(groups = { "Regression" }, priority = 2)
	public void OnboardRepo2() {
		
		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new RepoIntegrationPage().SearchExisitingRepo(reader.getCellData("RepoIntegrationData", "existingRepo2", 2));
		boolean switchTypeCheck2 = ui_IsElementPresent(noDataText, "5");
		if (switchTypeCheck2 == true) {
			new RepoIntegrationPage().AddRepoOption1(reader.getCellData("RepoIntegrationData", "inetgrationType", 2),
					reader.getCellData("RepoIntegrationData", "secretName1", 2),
					reader.getCellData("RepoIntegrationData", "onboardGit2", 2),
					reader.getCellData("RepoIntegrationData", "ValidationMessage", 2));
		} else {
			System.out.println("Repo already exists");
		}
	}

}