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
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.RandomStrings;

public class SuperAdminPage extends BasePage {

	@FindBy(xpath = "//span[text()='User Management']")
	WebElement userManagementBtn;

	@FindBy(xpath = "//span[text()='Manage Users']")
	WebElement manageUsers;

	@FindBy(xpath = "//a[@title='Upload User Sheet']")
	WebElement uploadBulkUserSheet;

	@FindBy(xpath = "//span[text()='file_upload']")
	WebElement fileUpload;

	@FindBy(xpath = "//input[@class='file-upload-input']")
	WebElement fileUploadclick;
	
	@FindBy(xpath = "//button[@class='btn btn-submit' and text()='Upload Sheet']")
	WebElement UploadedFileBtn;
	
	@FindBy(xpath = "//span[text()='refresh']")
	WebElement refreshBtn;
	
	@FindBy(xpath = "(//div[text()='User imported Successfully'])[1]")
	WebElement userImportedTxt;
	
	@FindBy(xpath = "(//span[text()='File upload Status: '])[1]//span")
	WebElement fileuploadStatusTxt;
	
	@FindBy(xpath = "(//span[text()='Failed: ']/..//span[text()='0'])[1]")
	WebElement failedUserCount;
	
	@FindBy(xpath = "(//a[text()='sample-data.csv'])[1]")
	WebElement viewUserLogs;
	
	@FindBy(xpath = "//input[@name='name' and @placeholder='Name']")
	WebElement searchUser;
	
	@FindBy(xpath = "//span[text()='edit']")
	WebElement editUser;

	public SuperAdminPage() {

	}
	
	String userFilePath = System.getProperty("user.dir")+ "\\src\\test\\resources\\testfiles\\upload\\UploadBulkUser\\sample-data.csv";

	public SuperAdminPage uploadBulkUser() {
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(userManagementBtn, Pause.MEDIUM));
		ui_click(userManagementBtn, "clicks on user Management button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(manageUsers, Pause.MEDIUM));
		ui_click(manageUsers, "clicks on manage Users button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(uploadBulkUserSheet, Pause.MEDIUM));
		ui_click(uploadBulkUserSheet, "clicks on upload Bulk User Sheet button");
		ui_click(fileUpload, "clicks on upload file button");
		ui_click(fileUploadclick, "clicks on upload file button");
		ui_wait(5);
		ui_FileUpload(Configuration.get("browser"), userFilePath);
		ui_wait(15);
		ui_click(UploadedFileBtn, "clicks on upload file after uploading csv");
		ui_getUIDriver().switchTo().defaultContent();
		ui_wait(20);
		ui_click(refreshBtn, "clicks on refreshBtn");
		ui_IsElementDisplay(ui_waitForElementToDisplay(userImportedTxt, Pause.MEDIUM));
		Assert.assertEquals(fileuploadStatusTxt.getText().trim(), "SUCCESS", "file upload status");
		ui_IsElementDisplay(ui_waitForElementToDisplay(failedUserCount, Pause.MEDIUM));
//		ui_click(viewUserLogs, "clicks on csv name recently added");
//		ui_switchToNewWindow();
		ui_IsElementDisplay(ui_waitForElementToDisplay(userManagementBtn, Pause.MEDIUM));
		ui_click(userManagementBtn, "clicks on user Management button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(manageUsers, Pause.MEDIUM));
		ui_click(manageUsers, "clicks on manage Users button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchUser, Pause.MEDIUM));
		ui_clearAndSetValue(searchUser, "Test User");
		searchUser.sendKeys(Keys.ENTER);
		ui_click(editUser, "user clicks on edit user icon");
		
		return this;

	}
	
	@FindBy(xpath = "//span[text()='hercules-cluster']")
	WebElement clusterName;
	
	@FindBy(xpath = "//span[text()='Cluster Overview']")
	WebElement clusterOverview;
	
	@FindBy(xpath = "//div[@class='general-data']//span[text()='Connected']")
	WebElement clusterConnectionStatus;
	
	public SuperAdminPage ClusterOverview() {
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(clusterName, Pause.MEDIUM));
		ui_click(clusterName, "clicks on cluster Name button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(clusterOverview, Pause.MEDIUM));
		ui_click(clusterOverview, "clicks on cluster Overview button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(clusterConnectionStatus, Pause.MEDIUM));

		return this;
	}
	
	@FindBy(xpath = "//div[text()='Buildpiper Versioning ']")
	WebElement buildPiperVersioning;
	
	@FindBy(xpath = "//select[@name='BP_VERSIONING_IS_ENABLE']")
	WebElement enableDisableVersioning;
	
	@FindBy(xpath = "//button[text()='Apply immediately']")
	WebElement ApplySettingImmediately;
	
	public SuperAdminPage BuildPiperVersioningDisable() {
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperVersioning, Pause.MEDIUM));
		ui_click(buildPiperVersioning, "clicks on buildPiper Versioning button");
		Select dropdown = new Select(enableDisableVersioning);
		dropdown.selectByVisibleText("false");
		ui_click(ApplySettingImmediately, "clicks on Apply Setting Immediately button");

		return this;
	}
	
	public SuperAdminPage BuildPiperVersioningEnable() {
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperVersioning, Pause.MEDIUM));
		ui_click(buildPiperVersioning, "clicks on buildPiper Versioning button");
		Select dropdown = new Select(enableDisableVersioning);
		dropdown.selectByVisibleText("true");
		ui_click(ApplySettingImmediately, "clicks on Apply Setting Immediately button");

		return this;
	}
	
	@FindBy(xpath = "//span[text()='Manage Application']")
	WebElement manageApplicationBtn;
	
	@FindBy(xpath = "//input[@placeholder='Name']")
	WebElement searchApplicationName;
	
	@FindBy(xpath = "//a[text()='Add Application']")
	WebElement addApplication;
	
	@FindBy(xpath = "//input[@placeholder='Application Name']")
	WebElement inputApplciationName;
	
	@FindBy(xpath = "//textarea[@placeholder='Application Description']")
	WebElement inputApplciationDesc;
	
	@FindBy(xpath = "//select[@name='clusters']")
	WebElement selectCluster;
	
	@FindBy(xpath = "//button[text()='Add Application']")
	WebElement submitApplicationIntakeBtn;
	
	@FindBy(xpath = "//div[text()='No Application found with the name : ']")
	WebElement noDataText;
	
	public SuperAdminPage searchApplication(String appName) {
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(manageApplicationBtn, Pause.MEDIUM));
		ui_click(manageApplicationBtn, "clicks on manage Application button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchApplicationName, Pause.MEDIUM));
		ui_clearAndSetValue(searchApplicationName, appName);
		searchApplicationName.sendKeys(Keys.ENTER);
		return this;

	}

	
	public SuperAdminPage createPreReqApplication(String appName) {
		
		boolean switchTypeCheck = ui_IsElementPresent(noDataText, "5");
		if (switchTypeCheck == true) {
		ui_IsElementDisplay(ui_waitForElementToDisplay(addApplication, Pause.MEDIUM));
		ui_click(addApplication, "clicks on Add Application button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(inputApplciationName, Pause.MEDIUM));
        ui_clearAndSetValue(inputApplciationName, appName);
		ui_IsElementDisplay(ui_waitForElementToDisplay(inputApplciationDesc, Pause.MEDIUM));
        ui_clearAndSetValue(inputApplciationDesc, "automation testing application");
		Select dropdown = new Select(selectCluster);
		dropdown.selectByVisibleText("hercules-cluster");
		ui_click(submitApplicationIntakeBtn, "clicks on submit Application Intake button");
		}else {
			System.out.println("Application already exists");
		}
		return this;
	}

}