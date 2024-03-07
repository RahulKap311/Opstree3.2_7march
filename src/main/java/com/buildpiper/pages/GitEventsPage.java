package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.RandomStrings;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

public class GitEventsPage extends BasePage {
	
	@FindBy(xpath = "//li//button[contains(@class,'main-nav-1')]//span[1][@title]")
	List<WebElement> poc_qaProjectLink;
	@FindBy(xpath = "//li//button[contains(@class,'main-menu hover-effect ')]/p[text()='Integrations']")
	WebElement integrationsLink;
	@FindBy(xpath = "//button[@class='sub-menu']/p[text()='Git Events']")
	WebElement gitEventsLink;
	@FindBy(xpath = "//button[text()=' Add Listener']")
	WebElement addListenerButton;
	@FindBy(xpath = "//input[@placeholder='Please give a name to Event Listener']")
	WebElement addListenerNameField;
	@FindBy(xpath = "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiAutocomplete-inputRoot MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-adornedEnd MuiOutlinedInput-adornedEnd']")
	WebElement gitUrlEnterDropdown;

	@FindBy(xpath = "//input[@name='git_url']")
	WebElement gitUrlInputField;
	@FindBy(xpath = "//button[@title='Clear']")
	WebElement clearClickGitDropDown;
	@FindBy(xpath = "//ul[@id='git_url-popup']//li")
	List<WebElement> autoCompleteURLList;

	@FindBy(xpath = "//*[text()='Load Branches']")
	WebElement loadBranchesBtn;

	@FindBy(xpath = "//select[@name='branch_name']")
	WebElement selectBranchName;
	@FindBy(xpath = "//div[contains(@class,'select_events')]/div")
	WebElement selectEventname;
	@FindBy(xpath = "//input[@name='2000']/..")
	WebElement pushHookEventCheckbox;
	@FindBy(xpath = "//ul[@class='MuiAutocomplete-listbox']/li[1]")
	WebElement autoCompleteEvenetList;
	@FindBy(xpath = "//select[@name='project_id']")
	WebElement selectApplication;
	@FindBy(xpath = "//select[@name='project_env_id']")
	WebElement selectEnvironment;
	@FindBy(xpath = "//select[@name='service_id']")
	WebElement selectService;
	@FindBy(xpath = "//select[@name='project_job_template_id']")
	WebElement selectJobTemplate;
	@FindBy(xpath = "//select[@name='action']")
	WebElement selectJob;
	@FindBy(xpath = "(//input[@name='deployment_name'])[2]/..")
	WebElement selectDeploymentCheckbox;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveListener;
	@FindBy(xpath = "(//span[contains(@class,'events-text')]/span)[1]")
	WebElement eventText;
	@FindBy(xpath = "(//h5[text()='Branch Name: ']/following-sibling::span)[1]")
	WebElement branchNameText;
	@FindBy(xpath = "(//h5[text()='Action: ']/following-sibling::span)[1]")
	WebElement actionText;
	@FindBy(xpath = "(//div[@class='events']//button[@class='MuiButtonBase-root MuiIconButton-root']/span[1])[1]")
	WebElement firstKebobIcon;
	@FindBy(xpath = "//span[contains(text(),'Delete')]")
	WebElement deleteNewEvent;
	@FindBy(xpath = "//input[@placeholder='Please enter the reason to delete']")
	WebElement deleteConfirmation;
	@FindBy(xpath = "//button[text()='Delete']")
	WebElement deleteButton;
	
		public GitEventsPage() {

	}



	public GitEventsPage AddListener(String ListenerName,String gitURL,String BranchName,String ApplicationName,String Environment,String Service,String Template,String Job) {

		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(integrationsLink, Pause.MEDIUM));
	    ui_click(integrationsLink, "integrations Link Link");
	    ui_click(gitEventsLink, "git Events Link Link");
	    projectSelection = true;
	
		if (projectSelection) {
			ui_wait(2);
			ui_IsElementDisplay(ui_waitForElementToDisplay(addListenerButton, Pause.MEDIUM));
			ui_click(addListenerButton, "Add New Listener Button");
			ui_wait(2);
			ui_setvalue(addListenerNameField, "addListenerNameField", ListenerName);
			ui_wait(2);
			ui_IsElementDisplay(ui_waitForElementToDisplay(gitUrlEnterDropdown, Pause.MEDIUM));
			ui_click(gitUrlInputField, "Poc_QA gitUrlInputField");
			ui_wait(5);

			ui_click(clearClickGitDropDown, "ClicksclearField");

			ui_clearAndSetValue(gitUrlInputField, gitURL);
			ui_wait(2);
			ui_clickfromList(autoCompleteURLList, gitURL);
			ui_wait(5);

			ui_IsElementDisplay(ui_waitForElementToDisplay(loadBranchesBtn, Pause.MEDIUM));
			ui_click(loadBranchesBtn, "Poc_QA loadBranchesBtn");
			ui_wait(10);
			ui_selectValueFromDropDownByXPath(selectBranchName, "Selects Branch Name");
			Select dropdown = new Select(selectBranchName);
			dropdown.selectByVisibleText(BranchName);
			ui_wait(3);
			ui_click(selectEventname, "Select Event name");
			ui_click(pushHookEventCheckbox, "pushHook EventCheckbox");			
			ui_wait(2);
			ui_click(selectEventname, "Select Event name");
			ui_wait(2);
			Select application=new Select(selectApplication);
			application.selectByVisibleText(ApplicationName);
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(selectEnvironment, Pause.MEDIUM));
			ui_wait(2);
			Select env=new Select(selectEnvironment);
			env.selectByVisibleText(Environment);
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(selectService, Pause.MEDIUM));
			Select service=new Select(selectService);
			service.selectByVisibleText(Service);
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobTemplate, Pause.MEDIUM));
			Select jobTemplate=new Select(selectJobTemplate);
			jobTemplate.selectByVisibleText(Template);
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(selectJob, Pause.MEDIUM));
			Select job=new Select(selectJob);
			job.selectByVisibleText(Job);
			ui_wait(4);
			ui_click(selectDeploymentCheckbox, "select Deployment Checkbox");
			ui_wait(2);
            ui_click(saveListener, "Save Listener");
        	ui_wait(20);
        	Assert.assertEquals(eventText.getText(), "Push Hook");
        	Assert.assertEquals(branchNameText.getText(), "master");
        	Assert.assertEquals(actionText.getText(), "build_and_deploy");
        	ui_wait(1);
			ui_click(firstKebobIcon, "step KebobIcon");
			ui_wait(1);
			if(ui_IsElementDisplay(deleteNewEvent)) {
			ui_click(deleteNewEvent, "Delete Created Event");
			ui_wait(2);	
			}			
			ui_setvalue(deleteConfirmation, "deleteConfirmation", "Automation");
			ui_wait(2);
			ui_click(deleteButton, "delete Button");
		}

		return this;

	}
	
}