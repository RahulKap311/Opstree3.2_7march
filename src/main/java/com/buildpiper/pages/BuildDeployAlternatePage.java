package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;

public class BuildDeployAlternatePage extends BasePage {
	
	@FindBy(xpath = "//button//div//span[@class='flaticon-expand-arrow']/../../..//div//button//p[text()='Build / Deploy']")
	WebElement buildDeploy;

	@FindBy(xpath = "//li//button[@class='app-cluster-button']//span[1][@title]")
	List<WebElement> poc_qaProjectLink;
	
	@FindBy(xpath = "//select[@name='env_name' and @class='select']")
	WebElement selectEnv;
	
	@FindBy(xpath = "//select[@name='service_id' and @class='select']")
	WebElement selectService;
	
	@FindBy(xpath = "//div[@class='input-component']//input[@name='build_deploy' and @type='radio']")
	WebElement buildDeployRadioBtn;
	
	@FindBy(xpath = "(//div[@class='input-component']//input[@name='build_deploy' and @type='radio'])[2]/..")
	WebElement DeployRadioBtn;

	@FindBy(xpath = "//input[@id='branch' and @name='branch']")
	WebElement inputBranchName;
	
	@FindBy(xpath = "//button[@title='Clear']")
	WebElement clearBranchName;
	
	@FindBy(xpath = "//button[@class='btn btn-submit'][text()='Build']")
	WebElement buildBtn;
	
	@FindBy(xpath = "//button[@class='btn btn-submit'][text()='Deploy']")
	WebElement deployBtn;
	
	@FindBy(xpath = "//input[@name='deploy_tag' and @placeholder='Enter value']")
	WebElement tagName;
	
	@FindBy(xpath = "//div[@class='msg-div']//span[@class='color-success']")
	List<WebElement> SuccessMsgBuild;
	
	@FindBy(xpath = "//button[@class='btn btn-primary' and text()='Continue']")
	WebElement continueBtn;

	public BuildDeployAlternatePage() {

	}
	

	public BuildDeployAlternatePage TriggerAlternateMethod(String appName,String env,String service,String branchName) {
		
		boolean projectSelection = false;
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for(WebElement element:poc_qaProjectLink) {
			if(element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if(projectSelection) {
			
			//////////////////// Build Details //////////////////////////////////
			ui_wait(5);
			ui_click(buildDeploy, "Poc_QA buildDeploy");
			ui_wait(8);
			Select dropdown = new Select(selectEnv);
			dropdown.selectByVisibleText(env);
			Select dropdown1 = new Select(selectService);
			dropdown1.selectByVisibleText(service);
			if (buildDeployRadioBtn.getAttribute("value").equals("Build"))
				ui_click(buildDeployRadioBtn, "Poc_QA buildRadioBtn");
			ui_click(inputBranchName, "Poc_QA inputBranchName");
			ui_wait(5);

			ui_click(clearBranchName, "ClicksclearField");

			ui_clearAndSetValue(inputBranchName, branchName);
			ui_click(buildBtn, "clicks build button");
			ui_wait(90);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.V_HIGH));
			ui_click(continueBtn, "Click continueBtn");
	        for (int i = 0; i < SuccessMsgBuild.size(); i++) {

	        	Assert.assertEquals(SuccessMsgBuild.get(i).getText().trim(), "Success", "Build run successfully");

	        }
			
			//////////////////////////// Deploy Details /////////////////////////////
	        ui_wait(5);
			ui_click(buildDeploy, "Poc_QA buildDeploy");
			ui_wait(3);
			Select dropdown3 = new Select(selectEnv);
			dropdown3.selectByVisibleText(env);
			Select dropdown4 = new Select(selectService);
			dropdown4.selectByVisibleText(service);
			ui_click(DeployRadioBtn, "Poc_QA DeployRadioBtn");
			ui_wait(5);
			ui_clearAndSetValue(tagName, "testTag");
			ui_click(deployBtn, "clicks deploy button");
			
		}
		return this;
	
   }
}
