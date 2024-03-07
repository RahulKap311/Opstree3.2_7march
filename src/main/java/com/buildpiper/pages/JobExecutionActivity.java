package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.buildpiper.base.BasePage;
import com.buildpiper.report.Log;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.Pause;

/**
 * @author sagarT
 *
 */
public class JobExecutionActivity extends BasePage {
	
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

//----------------------Common Locators-------------
	@FindBy(xpath = "//li//button[@class='app-cluster-button']//span[1][@title]")
	List<WebElement> poc_qaProjectLink;
	@FindBy(xpath = "//input[@name='page_number']")
	WebElement pagenumberTextbox;
	@FindBy(xpath = "//button[contains(@class,'btn btn-with-icon pagination-btn btn-not-allowed btn-round cursor-not-allowed text-grey-')]")
	WebElement firstpageLeftarrow;
	@FindBy(xpath = "//button[contains(@class,'btn btn-with-icon btn-round pagination-btn btn-not-allowed cursor-not-allowed text-grey-')]")
	WebElement lastpageRightarrow;
	@FindBy(xpath = "//button[@class='btn btn-with-icon pagination-btn btn-round box_shadow']")
	WebElement leftArrowButton;
	@FindBy(xpath = "//button[@class='btn btn-with-icon btn-round pagination-btn box_shadow']")
	WebElement rightArrowButton;
	@FindBy(xpath = "//input[@name='page_number']/../following-sibling::span")
	WebElement lastPageNumber;
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root']//span[text()='More']")
	WebElement moreButton;
	@FindBy(xpath = "//div[contains(@class,'selection-clear-container d-flex align-')]//label[text()='Clear Selection']")
	WebElement clearSelectionButton;
	@FindBy(xpath = "//label[text()='Reset']")
	WebElement resetButton;
	@FindBy(xpath = "//input[@name='status']")
	WebElement statusCheckbox;
	@FindBy(xpath = "//input[@name='user_id']")
	WebElement usersCheckbox;
	@FindBy(xpath = "//input[@name='project_name']")
	WebElement appCheckbox;
	@FindBy(xpath = "//input[@name='env_master']")
	WebElement environmentTypeCheckbox;
	@FindBy(xpath = "//input[@name='job_type']")
	WebElement jobTypeCheckbox;
	
	//-------------------------Status Locators
	@FindBy(xpath = "//div[@class=' dropdown-container-adv d-flex align-center space-between']/div/label[text()='Status']")
	WebElement statusDropDown;	
	@FindBy(xpath = "//div[@class=' dropdown-container-adv d-flex align-center space-between']/div/label[text()='Job Type']")
	WebElement jobTypeDropDown;
	@FindBy(xpath = "//input[@name='SUCCESS']")
	WebElement statusSuccessCheckbox;
	@FindBy(xpath = "//input[@name='FAILED']")
	WebElement statusFailedCheckbox;
	@FindBy(xpath = "//input[@name='RUNNING']")
	WebElement statusRunningCheckbox;
	@FindBy(xpath = "//input[@name='IN_QUEUE']")
	WebElement statusInQueueCheckbox;
	@FindBy(xpath = "//input[@name='REVOKED']")
	WebElement statusRevokedCheckbox;
	@FindBy(xpath = "//div[@class='single-row-item-four']//span/span")
	List<WebElement> activityStatusList;
	
	//-----------------------Environment Locators-----------
	@FindBy(xpath = "//input[@name='dev']")
	WebElement envDevCheckbox;
	@FindBy(xpath = "//input[@name='qa']")
	WebElement envQACheckbox;
	@FindBy(xpath = "//input[@name='uat']")
	WebElement envUatCheckbox;
	@FindBy(xpath = "//input[@name='staging']")
	WebElement envStagingCheckbox;
	@FindBy(xpath = "//input[@name='prod']")
	WebElement envProdCheckbox;
	@FindBy(xpath = "//div[@class='single-row-item-three']//span[text()='Component Env']/following-sibling::span[1]")
	List<WebElement> activityEnvironmentList;
	@FindBy(xpath = "//div[@class=' dropdown-container-adv d-flex align-center space-between']/div/label[text()='Environment Type']")
	WebElement environmentTypeDropDown;
	
	//---------------------------------App Locator
	@FindBy(xpath = "//div[@class=' dropdown-container-adv d-flex align-center space-between']/div/label[text()='App']")
	WebElement AppDropDown;
	@FindBy(xpath = "(//span[@class='MuiIconButton-label']/input)[1]")
	WebElement perfeasytestingCheckbox;
	@FindBy(xpath = "//div[@class='single-row-item-three']//span[text()='Project']/following-sibling::span[1]")
	List<WebElement> activityAppList;
	
	//---------------------------------Users Locator
	@FindBy(xpath = "//div[@class=' dropdown-container-adv d-flex align-center space-between']/div/label[text()='Users']")
	WebElement usersDropDown;
	@FindBy(xpath = "//input[@name='1']")
	WebElement user1Checkbox;
	@FindBy(xpath = "//div[@class='single-row-item-two']//p[text()='Triggred by:']/span")
	List<WebElement> activityUserList;
	
	//-----------Component Locators
	@FindBy(xpath = "(//div[@class='single-row-item-three']//span[text()='Component']/following-sibling::a)[1]")
	WebElement componentListFirstLink;
	@FindBy(xpath = "(//div[@class='single-row-item-three']//span[text()='Component']/following-sibling::a)[1]/span")
	WebElement componentListFirstLinkName;
	@FindBy(xpath = "//div[(@class='heading-section-env')]/div/following-sibling::div/div/div")
	WebElement servicenameHeading;
	
	/**
	 * 
	 */
	public JobExecutionActivity() {


	}
	
	public JobExecutionActivity SelectProject(String appName) {
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				break;
			}
		}
		return this;
		}

	public JobExecutionActivity VerifyServiceLink() {
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(componentListFirstLinkName, Pause.MEDIUM));
		String ComponentName=componentListFirstLinkName.getText();
		ui_click(componentListFirstLink, "componentListFirstLink");
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(servicenameHeading, Pause.MEDIUM));
		Assert.assertEquals(ComponentName, servicenameHeading.getText());
		return this;
		}
	
	public JobExecutionActivity VerifyResetLink() {
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(moreButton, Pause.MEDIUM));
		ui_click(moreButton, "More Button");
		ui_click(statusCheckbox, "status Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(usersCheckbox, "users Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(appCheckbox, "app Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(environmentTypeCheckbox, "environmentType Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(jobTypeCheckbox, "jobType Checkbox");
		ui_wait(3);
		Assert.assertEquals(ui_IsElementPresent(statusDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(jobTypeDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(environmentTypeDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(AppDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(usersDropDown, "5"), true);
		ui_wait(3);
		ui_click(resetButton,"reset Button");
		ui_wait(3);
		Assert.assertEquals(ui_IsElementPresent(statusDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(jobTypeDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(environmentTypeDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(AppDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(usersDropDown, "5"), false);
		return this;
		}
	
	public JobExecutionActivity VerifyClearSelection() {
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(moreButton, Pause.MEDIUM));
		ui_click(moreButton, "More Button");
		ui_click(statusCheckbox, "status Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(usersCheckbox, "users Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(appCheckbox, "app Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(environmentTypeCheckbox, "environmentType Checkbox");
		ui_wait(3);
		ui_click(moreButton, "More Button");
		ui_click(jobTypeCheckbox, "jobType Checkbox");
		ui_wait(3);
		Assert.assertEquals(ui_IsElementPresent(statusDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(jobTypeDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(environmentTypeDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(AppDropDown, "5"), true);
		Assert.assertEquals(ui_IsElementPresent(usersDropDown, "5"), true);
		ui_wait(3);
		ui_click(clearSelectionButton,"clearSelection Button");
		ui_wait(3);
		Assert.assertEquals(ui_IsElementPresent(statusDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(jobTypeDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(environmentTypeDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(AppDropDown, "5"), false);
		Assert.assertEquals(ui_IsElementPresent(usersDropDown, "5"), false);
		return this;
		}
	
	public JobExecutionActivity PaginationCheck(String PageNumber) {
	 Assert.assertEquals(ui_IsElementDisplay(firstpageLeftarrow), true);
	 Assert.assertEquals(ui_IsElementPresent(lastpageRightarrow,"5"), false);
	 ui_IsElementDisplay(ui_waitForElementToDisplay(pagenumberTextbox, Pause.MEDIUM));
	 ui_clearAndSetValue(pagenumberTextbox, PageNumber);
	 pagenumberTextbox.sendKeys(Keys.ENTER);
	 ui_wait(4);
	 Assert.assertEquals(ui_IsElementPresent(leftArrowButton,"5"), true);
	 Assert.assertEquals(ui_IsElementPresent(rightArrowButton,"5"), true);
	 
	 String lastNumber=lastPageNumber.getText().replace("of    ", "");
	 ui_clearAndSetValue(pagenumberTextbox, lastNumber);
	 pagenumberTextbox.sendKeys(Keys.ENTER);
	 ui_wait(3);
	 Assert.assertEquals(ui_IsElementPresent(firstpageLeftarrow,"5"), false);	 
	 Assert.assertEquals(ui_IsElementPresent(lastpageRightarrow,"5"), true);
	 ui_clearAndSetValue(pagenumberTextbox, "1");
	 pagenumberTextbox.sendKeys(Keys.ENTER);
	 ui_wait(3);
	 Assert.assertEquals(ui_IsElementDisplay(firstpageLeftarrow), true);
	 Assert.assertEquals(ui_IsElementPresent(lastpageRightarrow,"5"), false);
	return this;
	}
	
	public JobExecutionActivity SelectStatus_MoreDropdown() {
		ui_IsElementDisplay(ui_waitForElementToDisplay(moreButton, Pause.MEDIUM));
		ui_click(moreButton, "More Button");
		ui_wait(1);
		ui_click(statusCheckbox, "Status Checkbox");
		ui_wait(1);
		return this;
	}	
	
	public JobExecutionActivity SelectStatusType(String StatusType) {
	    switch(StatusType){  
	    //Case statements  
	    case "Success":ui_click(statusSuccessCheckbox, "statusSuccessCheckbox");
	    break;  
	    case "Failed":ui_click(statusFailedCheckbox, "statusFailedCheckbox");
	    break; 
	    case "Running":ui_click(statusRunningCheckbox, "statusRunningCheckbox");
	    break; 
	    case "InQueue":ui_click(statusInQueueCheckbox, "statusInQueueCheckbox");
	    break; 
	    case "Revoked":ui_click(statusRevokedCheckbox, "statusRevokedCheckbox");
	    break; 
	    default:ui_click(statusSuccessCheckbox, "statusSuccessCheckbox");
	    }
	return this;
	}
	
	public JobExecutionActivity VerifyActivityStatusList(String Status) {
		ui_wait(10);
		ui_IsElementDisplay(ui_waitForElementToDisplay(activityStatusList.get(0), Pause.MEDIUM));
		for(int i=0;i<activityStatusList.size();i++) {
			String ActivityStatus=activityStatusList.get(i).getText();
			Assert.assertEquals(ActivityStatus, Status);
		}		
		return this;			
	}	

	public JobExecutionActivity ActivityFilterby_Status(ArrayList<String> statusList) {
	SelectStatus_MoreDropdown();
	ui_click(statusDropDown, "statusDropDown");
	for(int i=0;i<statusList.size();i++) {
		if(statusList.get(i).equals("InQueue")) {
			SelectStatusType(statusList.get(i));
			VerifyActivityStatusList("In queue");
		}
		else {
			SelectStatusType(statusList.get(i));
			VerifyActivityStatusList(statusList.get(i));	
		}
		ui_click(clearSelectionButton, "clearSelectionButton");
		ui_click(statusDropDown, "statusDropDown");
	}
		return this;
}

	//-------------------------- Environment Method
	public JobExecutionActivity SelectEnvironment_MoreDropdown() {
		ui_IsElementDisplay(ui_waitForElementToDisplay(moreButton, Pause.MEDIUM));
		ui_click(moreButton, "More Button");
		ui_wait(1);
		ui_click(environmentTypeCheckbox, "environmentType Checkbox");
		ui_wait(1);
		return this;
	}
	public JobExecutionActivity SelectEnvrionmentType(String Envtype) {
	    switch(Envtype){  
	    //Case statements  
	    case "dev":ui_click(envDevCheckbox, "Dev env Checkbox");
	    break;  
	    case "qa":ui_click(envQACheckbox, "QA env Checkbox");
	    break; 
	    case "uat":ui_click(envUatCheckbox, "Uat env Checkbox");
	    break; 
	    case "staging":ui_click(envStagingCheckbox, "Staging env Checkbox");
	    break; 
	    case "prod":ui_click(envProdCheckbox, "Prod env Checkbox");
	    break; 
	    default:ui_click(envDevCheckbox, "Dev env Checkbox");
	    }
	return this;
	}
	
	public JobExecutionActivity VerifyActivityEnvironmentList(String Env) {
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(activityEnvironmentList.get(0), Pause.MEDIUM));
		for(int i=0;i<activityEnvironmentList.size();i++) {
		String ActivityEnvironment=activityEnvironmentList.get(i).getText();
		Assert.assertEquals(ActivityEnvironment.contains(Env), true);
			
		}		
		return this;			
	}
	
	public JobExecutionActivity ActivityFilterby_Environment(ArrayList<String> environmentList) {
	SelectEnvironment_MoreDropdown();
	ui_click(environmentTypeDropDown, "environmentType DropDown");
	for(int i=0;i<environmentList.size();i++) {
	
	  SelectEnvrionmentType(environmentList.get(i));
	  VerifyActivityEnvironmentList(environmentList.get(i));	
	  ui_click(clearSelectionButton, "clearSelectionButton");
	  ui_click(environmentTypeDropDown, "environmentType DropDown");
	}
		return this;
}
	//----------------App Methods-----------
	public JobExecutionActivity SelectApp_MoreDropdown() {
		ui_IsElementDisplay(ui_waitForElementToDisplay(moreButton, Pause.MEDIUM));
		ui_click(moreButton, "More Button");
		ui_wait(1);
		ui_click(appCheckbox, "environmentType Checkbox");
		ui_wait(1);
		return this;
	}
	
	public JobExecutionActivity ActivityFilterby_App(String Appname) {
		SelectApp_MoreDropdown();
		ui_click(AppDropDown, "App DropDown");
		ui_click(perfeasytestingCheckbox, "perfeasytesting Checkbox");
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(activityAppList.get(0), Pause.MEDIUM));
		
		for(int i=0;i<activityAppList.size();i++) {
		String ActivityApp=activityAppList.get(i).getText();
		Assert.assertEquals(ActivityApp, Appname);
		}
		ui_click(clearSelectionButton, "clearSelectionButton");
	return this;
	}
	
	//--------------------Users Methods---------
	
	public JobExecutionActivity SelectUser_MoreDropdown() {
		ui_IsElementDisplay(ui_waitForElementToDisplay(moreButton, Pause.MEDIUM));
		ui_click(moreButton, "More Button");
		ui_wait(1);
		ui_click(usersCheckbox, "UserName Checkbox");
		ui_wait(1);
		return this;
	}
	public JobExecutionActivity ActivityFilterby_Users(String Username) {
		SelectUser_MoreDropdown();
		ui_click(usersDropDown, "User DropDown");
		ui_click(user1Checkbox, "User1 Checkbox");
		ui_wait(10);
		ui_IsElementDisplay(ui_waitForElementToDisplay(activityUserList.get(0), Pause.MEDIUM));
		
		for(int i=0;i<activityUserList.size();i++) {
		String ActivityUser=activityUserList.get(i).getText();
		Assert.assertEquals(ActivityUser, Username);
		}
		ui_click(clearSelectionButton, "clearSelectionButton");
	return this;
	}
	

}
