package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Listeners;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.RandomStrings;

public class PreRequisitesPage extends BasePage {

	@FindBy(xpath = "//p[@class='inactive-portal cursor-pointer']")
	WebElement switchToUSer;
	
	@FindBy(xpath = "//p[@class='inactive-portal cursor-pointer'][text()=' ADMIN PORTAL']")
	WebElement switchToAdmin;

	@FindBy(xpath = "//div[@aria-controls='simple-menu']//div//div[2]//p")
	WebElement userMenuAppBar;

	@FindBy(xpath = "//a[@href='/integration/containerRegistries']")
	WebElement containerRegistryBtn;

	@FindBy(xpath = "(//button[@class='test-btn'])[1]")
	WebElement testConnection;

	@FindBy(xpath = "//div[contains(@class,'connected text connected')]")
	WebElement connectionStatusText;
	
	@FindBy(xpath = "//span[text()='Job Execution History']")
	WebElement jobexecutionActivity;

	public PreRequisitesPage() {

	}

	public PreRequisitesPage accountPreRequisites() {

		ui_click(userMenuAppBar, "userMenuAppBar");
		boolean switchTypeCheck = ui_IsElementPresent(switchToUSer, "5");
		if (switchTypeCheck == true) {
			ui_click(containerRegistryBtn, "clicks on container registry");
			ui_click(testConnection, "clicks on first container registry test");
			ui_IsElementDisplay(ui_waitForElementToDisplay(connectionStatusText, Pause.MEDIUM));
			Assert.assertEquals(connectionStatusText.getText().trim(), "CONNECTED", "connection status validated");
			ui_wait(5);

		}
		return this;

	}
	
	public PreRequisitesPage switchToAdmin() {

		ui_click(userMenuAppBar, "userMenuAppBar");
		ui_click(switchToAdmin, "switching to user account");

		return this;
	}

	public PreRequisitesPage switchUser() {

		ui_click(userMenuAppBar, "userMenuAppBar");
		ui_click(switchToUSer, "switching to user account");

		return this;
	}
	
	@FindBy(xpath = "//span[text()='System Settings']")
	WebElement systemSettings;
	
	public PreRequisitesPage systemSettings() {

		ui_click(userMenuAppBar, "userMenuAppBar");
		ui_click(systemSettings, "Open System Settings");

		return this;
	}

	@FindBy(xpath = "//table[@class='table table-responsive']//tr")
	List<WebElement> workerRowContainer;

	public PreRequisitesPage defaultQueueTest(String adminDeployApiWorker, String adminPublicApiWorker,
			String bpBuildWorker, String bpDeployWorker, String deployApiWorker, String publicApiWorker,
			String schedulerWorker, String versioningWorker) {

		boolean queueStatus = true;
		for (WebElement queueContainer : workerRowContainer) {
			String workerText = queueContainer.getText();
			System.out.println(workerText);
			if (!(workerText.contains(adminDeployApiWorker) || workerText.contains(adminPublicApiWorker)
					|| workerText.contains(bpBuildWorker) || workerText.contains(bpDeployWorker)
					|| workerText.contains(deployApiWorker) || workerText.contains(publicApiWorker)
					|| workerText.contains(schedulerWorker) || workerText.contains(versioningWorker))) {
				queueStatus = false;
				break;
			}
		}
		Assert.assertTrue(queueStatus, "Unable to  validate the Worker queue container Table");
		
        Assert.assertEquals(workerRowContainer.size(), 9, "Queue table size is incorrect");

		return this;
	}
	
	public PreRequisitesPage SelectJobExecutionActivityMenu() {

		ui_click(userMenuAppBar, "userMenuAppBar");
		ui_click(jobexecutionActivity, "select Job Execution Activity Menu");

		return this;
	}

}