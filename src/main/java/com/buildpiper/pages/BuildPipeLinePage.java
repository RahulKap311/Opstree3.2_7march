package com.buildpiper.pages;
import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.aventstack.extentreports.gherkin.model.Given;
import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.RandomStrings;

import io.restassured.RestAssured;
import junit.framework.Assert;


/**
 * @author sagarT
 * @reviewer:
 *
 */
public class BuildPipeLinePage extends BasePage {

	public String pipelineName = "BasicPipeline" + RandomStrings.generateRandomString(9);
	String StageName1 = "dev" + RandomStrings.generateRandomString(7);
	String StageName2 = "qa" + RandomStrings.generateRandomString(7) + "fromdev";
	String StageName3 = "prod" + RandomStrings.generateRandomString(7) + "fromqa";
	
//	@FindBy(xpath = "//li//button[contains(@class,'main-nav-1')][contains(.,'perfeasy-app')]" )
//	WebElement poc_qaProjectLink;
	
	@FindBy(xpath = "//button//div//span[@class='flaticon-expand-arrow']/../../..//div//button//p[text()='Pipeline Overview']" )
	WebElement pipelineOverviewLink;
	
	//@FindBy(xpath = "//li//button[contains(@class,'app-cluster-button')]//span[@title]")
	@FindBy(xpath = "//li//button[contains(@class,'app-cluster-button')]//span[@title]")
	List<WebElement> poc_qaProjectLink;
	
	
	@FindBy(xpath = "//div[@class='service-name']//a[contains(.,'Build-pipeline')]" )
	WebElement PipelineSubject;
	
	@FindBy(xpath = "//span[@title='Run Pipeline']/button" )
	WebElement executePipeLineButton;
	
	@FindBy(xpath = "//a[@title='view logs']" )
	WebElement viewLogsLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Post Hooks Executing')]" )
	WebElement postHookExecutingSuccessLink;
	
	@FindBy(xpath = "//button[contains(@class,'btn-link-green')][text()=' Switch to User Portal']")
	WebElement switchToUSer;
	
	@FindBy(xpath = "//button[@type='button' and @aria-controls='menu-appbar']//div//div")
	WebElement userMenuAppBar;
	
	@FindBy(xpath = "//div[@class='input-component']//input[@name='name' and @placeholder='Name']")
	WebElement searchPipeLine;
	
	@FindBy(xpath = "//a[@title='View Execution History']/button")
	WebElement pipeLineExecutionHistory;
	
	@FindBy(xpath = "//button[@title='Replay Pipeline']")
	WebElement reExecutePipeLineBtn;
	
	@FindBy(xpath = "//a[contains(@href,'/application/')][contains(@href,'/pipeline/')][contains(@href,'/execution/')]")
	WebElement existingPipeLine;
	@FindBy(xpath = "(//*[contains(@class,'ad-more-search mb-')]/div/div//*[@class='MuiSvgIcon-root'])[1]")
	WebElement searchDropDown;
	@FindBy(xpath = "//input[@class='search-input-si']")
	WebElement searchPipelineInput;
	@FindBy(xpath = "//span[@class='MuiIconButton-label']/input")
	WebElement searchPipelineCheckbox;
	@FindBy(xpath = "//div[@class='flaticon-expand-button']")
	List<WebElement> jobdrops;
	

	/**
	 * 
	 */
	public BuildPipeLinePage() {

	}
	public BuildPipeLinePage buildAndValidateConsolePage(String appName, String existingPipelineName) {
//		ui_click(userMenuAppBar, "userMenuAppBar");		
//		ui_click(switchToUSer, "switching to user account");
		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for(WebElement element:poc_qaProjectLink) {
			if(element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if(projectSelection) {
		ui_wait(4);
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(PipelineSubject, Pause.MEDIUM));
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
		ui_wait(3);
	    ui_click(searchDropDown, "searchDropDown");    
		ui_clearAndSetValue(searchPipelineInput, existingPipelineName);
		ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
		ui_wait(3);
		ui_click(pipeLineExecutionHistory, "view pipeline excution history");
		ui_IsElementDisplay(ui_waitForElementToDisplay(reExecutePipeLineBtn, Pause.MEDIUM));
		ui_click(reExecutePipeLineBtn, "view pipeline re-excution history");
//		ui_click(executePipeLineButton, "Poc_QA execution Start");
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(existingPipeLine, Pause.MEDIUM));
		ui_click(existingPipeLine, "Poc_QA execution Start");
		ui_wait(5);
		ui_click(jobdrops.get(0), "Expend Build");
		ui_IsElementDisplay(ui_waitForElementToDisplay(viewLogsLink, Pause.MEDIUM));
		ui_click(viewLogsLink, "Poc_QA click on View Logs");
		ui_switchToNewWindow();
		String parent=ui_getUIDriver().getWindowHandle();

		Set<String>s=ui_getUIDriver().getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();

		if(!parent.equals(child_window))
		{
		ui_getUIDriver().switchTo().window(child_window);
		ui_IsElementDisplay(ui_waitForElementToDisplay(postHookExecutingSuccessLink, Pause.MEDIUM));
		ui_click(postHookExecutingSuccessLink, "postHookExecuting click for Console Logs");
		ui_wait(10);
		List<String> consoleLogs = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
		boolean status = consoleLogs.get(0).length()>0;
		Reporter.log("Successful Validate the Console logs", status);
		ui_getUIDriver().close();
		}

		}
		//switch to the parent window
		ui_getUIDriver().switchTo().window(parent);
		}
		return this;
	}
	
	@FindBy(xpath = "//a[contains(text(),'Add Pipeline')]")
	WebElement continueBtn;
	
	@FindBy(xpath = "//div//input[@name='name' and @placeholder='Give a name to the pipeline']")
	WebElement addNameToNewPipeline;
	
	@FindBy(xpath = "//select[@name='version' and @class='select']")
	WebElement selectPipelineVersionDropdown;
	
	@FindBy(xpath = "//input[@name='retention_execution_count' and @placeholder='Retention Count']")
	WebElement retentionCountField;
	
	@FindBy(xpath = "//label[@class='MuiFormControlLabel-root']/span[1]")
	List<WebElement> MUIRadioButtonsChecked;
	
	@FindBy(xpath = "//div[contains(@class,'MuiFormGroup')][@role='radiogroup']//input")
	List<WebElement> triggerTypeRadioBtn;
	
	@FindBy(xpath = "//div[@class='input-component']//input[@type='checkbox']")
	List<WebElement> pipelineAssignUserRoleCheckbox;
	
	@FindBy(xpath = "//button[contains(@class,'btn-save btn')][text()='Save']")
	WebElement savePipeline;
	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1']")
	WebElement muiTypographyBody;
	
	
	@FindBy(xpath = "//p[@class='pipeline-name']")
	WebElement validatePipelineName;
	
	@FindBy(xpath = "//button//div[@class='text-btn'][text()='Add New Stage']")
	WebElement addNewStageToPipeline;
	
	@FindBy(xpath = "//input[@name='name' and @placeholder='Stage name goes here']")
	WebElement stageName;
	
	@FindBy(xpath = "//div[@class='btn btn-add'][text()='ADD']")
	WebElement addStageBtn;
	
	@FindBy(xpath = "//div[@class='text-btn'][text()='Add New Job ']")
	WebElement addNewJobBtn;
	
	@FindBy(xpath = "//div[contains(@data-rbd-draggable-id,'stage-qa')]//div[@class='text-btn'][text()='Add New Job ']")
	WebElement addNewJobBtnUnderSecondStage;
	
	@FindBy(xpath = "//div[contains(@data-rbd-draggable-id,'stage-prod')]//div[@class='text-btn'][text()='Add New Job ']")
	WebElement addNewJobBtnUnderThirdStage;
	
	@FindBy(xpath = "//select[@name='task_type' and @class='select']")
	WebElement selectJobType;
	
	@FindBy(xpath = "//select[@name='env' and @class='select']")
	WebElement selectFromEnv;
	
	@FindBy(xpath = "//span[text()='automation-682046mu117xjpt']")
	WebElement serviceComponent;
	
	@FindBy(xpath = "//span[text()='automation-682046mu117xjpt']")
	WebElement serviceComponent1;
	
	@FindBy(xpath = "//select[@name='artifact_source' and @class='select']")
	WebElement selectArtifact;
	
	@FindBy(xpath = "//select[@name='target_env' and @class='select']")
	WebElement targetEnv;
	
	@FindBy(xpath = "//button[contains(@class,'btn-save')]")
	WebElement saveWorkFlowBtn;
	
	public BuildPipeLinePage createBasicPipeline(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser,String jobType,String fromEnv,String jobType2,String toEnv,String ArtifactName,String jobType3,String ArtifactName2,String prodEnv) {
		
		String StageName1 = "dev" + RandomStrings.generateRandomString(7);
		String StageName2 = "qa" + RandomStrings.generateRandomString(7) + "fromdev";
		String StageName3 = "prod" + RandomStrings.generateRandomString(7) + "fromqa";
//		ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
//		ui_click(userMenuAppBar, "userMenuAppBar");		
//		ui_click(switchToUSer, "switching to user account");
		
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
		ui_wait(4);
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_click(continueBtn, "clicks on add pipeline button");
		ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", pipelineName);
		Select dropdown = new Select(selectPipelineVersionDropdown);
		dropdown.selectByVisibleText(versionType);
		ui_clearAndSetValue(retentionCountField, retentionCount);
		
		
		
        for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
        	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
        		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
        		break;
        	}
        }
//		if (triggerTypeRadioBtn.getAttribute("value").equals("Manual"))
//			ui_click(triggerTypeRadioBtn, "Poc_QA triggerTypeRadioBtn");
        //ui_wait(2);
        //ui_MoveToElement(muiTypographyBody, "moving to muiTypographyBodyn");
       
        ui_wait(2);
        for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
        	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
        		
        		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
        	}
        		//.click();        	}
        }
        ui_wait(2);
        ui_click(savePipeline, "clicks on save pipeline button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(validatePipelineName, Pause.MEDIUM));
        String validatePipelineNameActual = validatePipelineName.getText().trim();
        Assert.assertEquals(validatePipelineNameActual, pipelineName);
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewStageToPipeline, Pause.MEDIUM));
        ui_click(addNewStageToPipeline, "adds first stage"); // adds first stage
		ui_IsElementDisplay(ui_waitForElementToDisplay(stageName, Pause.MEDIUM));
        ui_setvalue(stageName, "sets first stage name", StageName1);
        ui_wait(2);
        ui_click(addStageBtn, "adds button for first stage");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtn, Pause.MEDIUM));
        ui_click(addNewJobBtn, "clicks on new job"); // adds build job to dev 
		ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
		Select dropdown1 = new Select(selectJobType);
		dropdown1.selectByVisibleText(jobType);
		Select dropdown2 = new Select(selectFromEnv);
		dropdown2.selectByVisibleText(fromEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
		ui_click(serviceComponent, "selects service component under the env");
		ui_click(addStageBtn, "clicks add stage btn");
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtn, Pause.MEDIUM));
        ui_click(addNewJobBtn, "clicks on new job"); // adds deploy job to dev
		ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
		Select dropdown3 = new Select(selectJobType);
		dropdown3.selectByVisibleText(jobType2);
		Select dropdown4 = new Select(selectFromEnv);
		dropdown4.selectByVisibleText(fromEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent1, Pause.MEDIUM));
		ui_click(serviceComponent1, "selects service component under the env");
		Select dropdown5 = new Select(selectArtifact);
		dropdown5.selectByVisibleText(ArtifactName);
		ui_click(addStageBtn, "clicks add stage btn");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewStageToPipeline, Pause.MEDIUM));
        ui_click(addNewStageToPipeline, "adds second stage"); // adds second stage
		ui_click(addStageBtn, "clicks add stage btn");
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(stageName, Pause.MEDIUM));
        ui_setvalue(stageName, "sets first stage name", StageName2);
        ui_IsElementDisplay(ui_waitForElementToDisplay(addStageBtn, Pause.MEDIUM));
        ui_wait(3);
		ui_click(addStageBtn, "clicks add stage btn");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderSecondStage, Pause.MEDIUM));
        ui_click(addNewJobBtnUnderSecondStage, "clicks on new job"); // adds promote job from dev
		ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
		Select dropdown6 = new Select(selectJobType);
		dropdown6.selectByVisibleText(jobType3);
		Select dropdown7 = new Select(selectFromEnv);
		dropdown7.selectByVisibleText(fromEnv);
		ui_wait(3);
		//ui_IsElementDisplay(ui_waitForElementToDisplay(targetEnv, Pause.MEDIUM));
		Select dropdown8 = new Select(targetEnv);
		dropdown8.selectByVisibleText(toEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
		ui_click(serviceComponent, "selects service component under the env");
		Select dropdown9 = new Select(selectArtifact);
		dropdown9.selectByVisibleText(ArtifactName2);
        ui_click(addStageBtn, "adds button for second stage");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderSecondStage, Pause.MEDIUM));
//        ui_click(addNewJobBtnUnderSecondStage, "clicks on new job"); // adds build job to qa
//		Select dropdown10 = new Select(selectJobType);
//		dropdown10.selectByVisibleText(jobType);
//		Select dropdown11 = new Select(selectFromEnv);
//		dropdown11.selectByVisibleText(toEnv);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
//		ui_click(serviceComponent, "selects service component under the env");
//		ui_click(addStageBtn, "clicks add stage btn");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderSecondStage, Pause.MEDIUM));
        ui_click(addNewJobBtnUnderSecondStage, "clicks on new job"); // adds deploy job to qa
        ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
        ui_wait(3);
		Select dropdown12 = new Select(selectJobType);
		dropdown12.selectByVisibleText(jobType2);
		Select dropdown13 = new Select(selectFromEnv);
		dropdown13.selectByVisibleText(toEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent1, Pause.MEDIUM));
		ui_click(serviceComponent1, "selects service component under the env");
		Select dropdown14 = new Select(selectArtifact);
		dropdown14.selectByVisibleText(ArtifactName2);
		ui_click(addStageBtn, "clicks add stage btn");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewStageToPipeline, Pause.MEDIUM));
        ui_click(addNewStageToPipeline, "adds third stage"); // adds third stage
        ui_setvalue(stageName, "sets first stage name", StageName3);
        ui_IsElementDisplay(ui_waitForElementToDisplay(addStageBtn, Pause.MEDIUM));
        ui_wait(3);
        ui_click(addStageBtn, "adds button for third stage");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderThirdStage, Pause.MEDIUM));
        ui_click(addNewJobBtnUnderThirdStage, "clicks on new job"); // adds promote job from qa
        ui_wait(3);
        ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
		Select dropdown15 = new Select(selectJobType);
		dropdown15.selectByVisibleText(jobType3);
		Select dropdown16 = new Select(selectFromEnv);
		dropdown16.selectByVisibleText(toEnv);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(targetEnv, Pause.MEDIUM));
		ui_wait(3);
		Select dropdown17 = new Select(targetEnv);
		dropdown17.selectByVisibleText(prodEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent1, Pause.MEDIUM));
		ui_click(serviceComponent1, "selects service component under the env");
		Select dropdown18 = new Select(selectArtifact);
		dropdown18.selectByVisibleText(ArtifactName2);
		ui_click(addStageBtn, "clicks add stage btn");

		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderThirdStage, Pause.MEDIUM));
        ui_click(addNewJobBtnUnderThirdStage, "clicks on new job"); // adds deploy job to prod
        ui_wait(3);
        ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
		Select dropdown21 = new Select(selectJobType);
		dropdown21.selectByVisibleText(jobType2);
		Select dropdown22 = new Select(selectFromEnv);
		dropdown22.selectByVisibleText(prodEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
		ui_click(serviceComponent, "selects service component under the env");
		Select dropdown19 = new Select(selectArtifact);
		dropdown19.selectByVisibleText(ArtifactName2);
		ui_click(addStageBtn, "clicks add stage btn");
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(saveWorkFlowBtn, Pause.MEDIUM));
        ui_click(saveWorkFlowBtn, "clicks save workflow btn");
        ui_wait(10);
		}
		return this;
	}	
	
	@FindBy(xpath = "//input[@name='cron_regex' and @placeholder='Enter cron pattern only']")
	WebElement chronPattern;
	@FindBy(xpath = "//input[@name='cron_regex' and @placeholder='Enter cron pattern only' ]/following-sibling::div[@class='error-message']")
	WebElement chronPatternErrorMessage;
	@FindBy(xpath = "//span[@title='Edit Pipeline']/a")
	WebElement editSearchedpipeline;
	@FindBy(xpath = "//input[@name='manual_deploy_tag_select']/following-sibling::span[2]")
	WebElement tagformanualdeploy;
	@FindBy(xpath = "//input[@name='on_success_exit']/following-sibling::span[2]")
	WebElement terminatePipelineOnSuccess;
	@FindBy(xpath = "//input[@name='on_failure_exit']/following-sibling::span[2]")
	WebElement terminatePipelineOnFailure;
	@FindBy(xpath = "//input[@name='no_cache']/following-sibling::span[2]")
	WebElement noCache;
	@FindBy(xpath = "//input[@name='skip_task']/following-sibling::span[2]")
	WebElement skipJob;
	@FindBy(xpath = "//input[@name='mark_success_upon_skip']/following-sibling::span[2]")
	WebElement successUponSkip;
	@FindBy(xpath = "//input[@name='latest_enable']/following-sibling::span[2]")
	WebElement duplicateImage;
	@FindBy(xpath = "//input[@name='deployment_status_check']/following-sibling::span[2]")
	WebElement validateDeploymentSuccess;
	@FindBy(xpath = "//input[@name='is_linked_issue']/following-sibling::span[2]")
	WebElement mapJira;
	@FindBy(xpath = "//input[@name='open']/following-sibling::span[2]")
	WebElement conditionalRule;
	@FindBy(xpath = "//input[@name='suspend_pipeline']/following-sibling::span[2]")
	WebElement suspendpipeline;
	@FindBy(xpath = "//span[text()='Add Conditions']")
	WebElement addConditionButton;
	@FindBy(xpath = "//select[@name='condition_key']")
	WebElement conditionkeyDropdown;
	@FindBy(xpath = "//select[@name='condition_key']/option")
	List<WebElement> conditionkeyDropdownOption;
	@FindBy(xpath = "//button[@class='btn btn-with-icon btn-save']")
	WebElement conditionkeySaveButton;
	@FindBy(xpath = "//select[@name='operation']")
	WebElement jiraoperation;
	@FindBy(xpath = "//select[@name='issuetype']")
	WebElement issuetype;
	@FindBy(xpath = "//input[@name='issue_name']")
	WebElement issuename;
	@FindBy(xpath = "//input[@name='summary']")
	WebElement summary;	
	@FindBy(xpath = "//select[@name='issue_key']")
	WebElement issueKey;
	@FindBy(xpath = "//select[@name='status']")
	WebElement jiraStatus;
	@FindBy(xpath = "//input[@name='comment']")
	WebElement jiraComment;
	
	@FindBy(xpath = "//select[@name='method']")
	WebElement apiMethodDropdown;
	@FindBy(xpath = "//select[@name='rollback_version']")
	WebElement rollbackVersionDropdown;
	@FindBy(xpath = "//input[@name='url']")
	WebElement apiURL;
	@FindBy(xpath = "//input[@name='headers_key_value_pairs']")
	WebElement apiJSON;
	@FindBy(xpath = "//input[@name='request_timeout']")
	WebElement apirequesttimeout;
	@FindBy(xpath = "//input[@name='pipeline_suspend']/following-sibling::span[2]")
	WebElement suspandPipeline;
	@FindBy(xpath = "//input[@name='response_update_context_param']/following-sibling::span[2]")
	WebElement responseupdate_context_param;
	@FindBy(xpath = "//input[@name='cron_regex' and @placeholder='In minutes']")
	WebElement frequencySCMPipeline;
	
	public BuildPipeLinePage AddNewJobButtonClick()
	{
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtn, Pause.MEDIUM));
        ui_click(addNewJobBtn, "clicks on new job"); 
		return this;
	}
	public BuildPipeLinePage AddNewJobButtonClickUnderSecondStage()
	{
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderSecondStage, Pause.MEDIUM));
        ui_click(addNewJobBtnUnderSecondStage, "clicks on new job");
		return this;
	}	
	public BuildPipeLinePage AddNewJobButtonClickUnderThirdStage()
	{
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderThirdStage, Pause.MEDIUM));
        ui_click(addNewJobBtnUnderThirdStage, "clicks on new job");
		return this;
	}	
	public BuildPipeLinePage AddNewStage(String StageName)
	{
		  ui_IsElementDisplay(ui_waitForElementToDisplay(addNewStageToPipeline, Pause.MEDIUM));
	        ui_click(addNewStageToPipeline, "adds second stage"); 
			ui_click(addStageBtn, "clicks add stage btn");
			ui_IsElementDisplay(ui_waitForElementToDisplay(stageName, Pause.MEDIUM));
	        ui_setvalue(stageName, "sets first stage name", StageName);
			ui_click(addStageBtn, "clicks add stage btn");
		return this;
	}	
	
public BuildPipeLinePage createBuildJob(String JobType,String fromEnv,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean NoCache,Boolean SkipJob,Boolean SuccessUponSkip,Boolean DuplicateImage ) {
	 
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown1 = new Select(selectJobType);
	dropdown1.selectByVisibleText(JobType);
	Select dropdown2 = new Select(selectFromEnv);
	dropdown2.selectByVisibleText(fromEnv);
	System.out.println("------"+fromEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
	ui_click(serviceComponent, "selects service component under the env");
	
	if(ConditionRuleSelectorNot.equals(true))
	{
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	Select conditionaldropdown1 = new Select(conditionkeyDropdown);
	conditionaldropdown1.selectByIndex(2);
	}
	else {
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	    //Verify no Condition Rule seen 
	Assert.assertTrue(conditionkeyDropdownOption.size()==1);
	   // ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(NoCache.equals(true)) {
		ui_click(noCache, "noCache");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}
	if(DuplicateImage.equals(true)) {
		ui_click(duplicateImage, "duplicateImage");	
	}    
	ui_click(addStageBtn, "clicks add stage btn");			
		return this;
	}
public BuildPipeLinePage createDeployJob(String jobType2,String fromEnv,String ArtifactName,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean SkipJob,Boolean SuccessUponSkip,Boolean ValidateDeploymentSuccess ) {
	 

	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown3 = new Select(selectJobType);
	dropdown3.selectByVisibleText(jobType2);
	Select dropdown4 = new Select(selectFromEnv);
	dropdown4.selectByVisibleText(fromEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent1, Pause.MEDIUM));
	ui_click(serviceComponent1, "selects service component under the env");
	ui_wait(3);
	Select dropdown5 = new Select(selectArtifact);
	dropdown5.selectByVisibleText(ArtifactName);
	
    if(ConditionRuleSelectorNot.equals(true))
 	{
 	ui_click(conditionalRule, "conditionalRule");
 	ui_click(addConditionButton, "addConditionButton");
 	Select conditionaldropdown1 = new Select(conditionkeyDropdown);
 	conditionaldropdown1.selectByIndex(2);
 	}
 	else {
 	ui_click(conditionalRule, "conditionalRule");
 	ui_click(addConditionButton, "addConditionButton");
 	    //Verify no Condition Rule seen 
 	Assert.assertTrue(conditionkeyDropdownOption.size()==1);
 	   // ui_click(conditionkeySaveButton, "conditionkeySaveButton");
 	}
 	if(TerminatePipelineOnSuccess.equals(true)) {
 		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
 	}
 	if(SkipJob.equals(true)) {
 		ui_click(skipJob, "skipJob");	
 	}
 	if(SuccessUponSkip.equals(true)) {
 		ui_click(successUponSkip, "successUponSkip");	
 	}
 	if(ValidateDeploymentSuccess.equals(true)) {
 		ui_click(validateDeploymentSuccess, "validateDeploymentSuccess");	
 	}    

	ui_click(addStageBtn, "clicks add stage btn");
    ui_wait(3);
    return this;
	}
public BuildPipeLinePage createPromoteJob(String jobType3,String fromEnv,String toEnv,String ArtifactName2,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean SkipJob,Boolean SuccessUponSkip) {
	
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown6 = new Select(selectJobType);
	dropdown6.selectByVisibleText(jobType3);
	Select dropdown7 = new Select(selectFromEnv);
	dropdown7.selectByVisibleText(fromEnv);
	ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(targetEnv, Pause.MEDIUM));
	Select dropdown8 = new Select(targetEnv);
	dropdown8.selectByVisibleText(toEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
	ui_click(serviceComponent, "selects service component under the env");
	Select dropdown9 = new Select(selectArtifact);
	dropdown9.selectByVisibleText(ArtifactName2);    
	if(ConditionRuleSelectorNot.equals(true))
	{
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		Select conditionaldropdown1 = new Select(conditionkeyDropdown);
		conditionaldropdown1.selectByIndex(2);
	}
	else {
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		//Verify no Condition Rule seen 
		Assert.assertTrue(conditionkeyDropdownOption.size()==1);
		// ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}
	ui_click(addStageBtn, "clicks add stage btn");
	ui_wait(3);
	return this;
}

public BuildPipeLinePage createAPIJob(String jobType,String Method,String URL,String JSON,String Timeout,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean SkipJob,Boolean SuccessUponSkip,Boolean SuspendPipeline,Boolean UpdateContextparameter) {
	
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown = new Select(selectJobType);
	dropdown.selectByVisibleText(jobType);
	Select dropdown1 = new Select(apiMethodDropdown);
	dropdown1.selectByVisibleText(Method);
	ui_setvalue(apiURL, "apiURL", URL);
	ui_setvalue(apiJSON, "apiURL", JSON);
	ui_setvalue(apirequesttimeout, "apiURL", Timeout);
	if(ConditionRuleSelectorNot.equals(true))
	{
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		Select conditionaldropdown1 = new Select(conditionkeyDropdown);
		conditionaldropdown1.selectByIndex(2);
	}
	else {
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		//Verify no Condition Rule seen 
		Assert.assertTrue(conditionkeyDropdownOption.size()==1);
		// ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}
	if(SuspendPipeline.equals(true)) {
		ui_click(suspandPipeline, "suspandPipeline");	
	}
	if(UpdateContextparameter.equals(true)) {
		ui_click(responseupdate_context_param, "suspandPipeline");	
	}
	ui_click(addStageBtn, "clicks add stage btn");
	ui_wait(3);
	return this;
}

public BuildPipeLinePage createRollbackJob(String jobType,String RollbackVersion,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean SkipJob,Boolean SuccessUponSkip) {
	
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown = new Select(selectJobType);
	dropdown.selectByVisibleText(jobType);
	Select dropdown1 = new Select(rollbackVersionDropdown);
	dropdown1.selectByVisibleText(RollbackVersion);
	if(ConditionRuleSelectorNot.equals(true))
	{
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		Select conditionaldropdown1 = new Select(conditionkeyDropdown);
		conditionaldropdown1.selectByIndex(2);
	}
	else {
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		//Verify no Condition Rule seen 
		Assert.assertTrue(conditionkeyDropdownOption.size()==1);
		// ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}
	ui_ActionMoveAndClick(addStageBtn, "clicks add stage btn");
	ui_wait(3);
	return this;
}


public BuildPipeLinePage createJiraJob(String JobType,String Jiraoperation,String Issuetype,String Issuename,String Summary,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean SkipJob,Boolean SuccessUponSkip,Boolean MapJira ) {
	
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select jobtypedropdown = new Select(selectJobType);
	jobtypedropdown.selectByVisibleText(JobType);
	
	ui_IsElementDisplay(ui_waitForElementToDisplay(jiraoperation, Pause.MEDIUM));
	Select jiraoperationdropdown = new Select(jiraoperation);
	jiraoperationdropdown.selectByVisibleText(Jiraoperation);
	if(Jiraoperation.equals("Create Ticket"))
	{
	ui_IsElementDisplay(ui_waitForElementToDisplay(issuetype, Pause.MEDIUM));
	Select issuetypedropdown = new Select(issuetype);
	issuetypedropdown.selectByVisibleText(Issuetype);
	ui_setvalue(issuename, "issuename", Issuename);
	ui_setvalue(summary, "issuename", Summary);
	
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");		  
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	  
	}
	if(MapJira.equals(true)) {
		ui_click(mapJira, "mapJira");  
	}
	}
	if(Jiraoperation.equals("Change Status")) {
		Select issuetypedropdown = new Select(issueKey);
		issuetypedropdown.selectByIndex(1);
		Select jiraStatusdropdown = new Select(jiraStatus);
		jiraStatusdropdown.selectByIndex(1);
		if(TerminatePipelineOnSuccess.equals(true)) {
			ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");
		}
		if(SkipJob.equals(true)) {
			ui_click(skipJob, "skipJob");		  
		}
		if(SuccessUponSkip.equals(true)) {
			ui_click(successUponSkip, "successUponSkip");	  
		}
		if(MapJira.equals(true)) {
			ui_click(mapJira, "mapJira");  
		}
	}
	if(Jiraoperation.equals("Add Comment"))
	{
		Select issuetypedropdown = new Select(issueKey);
		issuetypedropdown.selectByIndex(1);
		ui_setvalue(jiraComment, "jiraComment", "Jira Complete");
		
		if(TerminatePipelineOnSuccess.equals(true)) {
			ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");
		}
		if(SkipJob.equals(true)) {
			ui_click(skipJob, "skipJob");		  
		}
		if(SuccessUponSkip.equals(true)) {
			ui_click(successUponSkip, "successUponSkip");	  
		}
		if(MapJira.equals(true)) {
			ui_click(mapJira, "mapJira");  
		}
	}
	
	if(ConditionRuleSelectorNot.equals(true)) {
		//Select conditional Rule
		ui_click(conditionalRule, "conditionalRule");
		ui_click(addConditionButton, "addConditionButton");
		Select conditionaldropdown1 = new Select(conditionkeyDropdown);
		conditionaldropdown1.selectByIndex(2);
	}	
	ui_ActionMoveAndClick(addStageBtn, "clicks add stage btn");
	ui_ActionClick(addStageBtn, "clicks add stage btn");
	
	return this;
}	

public BuildPipeLinePage createConfigMapJob(String JobType,String fromEnv,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean NoCache,Boolean SkipJob,Boolean SuccessUponSkip,Boolean DuplicateImage ) {
	 
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown1 = new Select(selectJobType);
	dropdown1.selectByVisibleText(JobType);
	Select dropdown2 = new Select(selectFromEnv);
	dropdown2.selectByVisibleText(fromEnv);
	System.out.println("------"+fromEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
	ui_click(serviceComponent, "selects service component under the env");
	
	if(ConditionRuleSelectorNot.equals(true))
	{
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	Select conditionaldropdown1 = new Select(conditionkeyDropdown);
	conditionaldropdown1.selectByIndex(2);
	}
	else {
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	    //Verify no Condition Rule seen 
	Assert.assertTrue(conditionkeyDropdownOption.size()==1);
	   // ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(NoCache.equals(true)) {
		ui_click(noCache, "noCache");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}
	if(DuplicateImage.equals(true)) {
		ui_click(duplicateImage, "duplicateImage");	
	}    
	ui_click(addStageBtn, "clicks add stage btn");			
		return this;
	}

public BuildPipeLinePage createDeploySecretsJob(String JobType,String fromEnv,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean NoCache,Boolean SkipJob,Boolean SuccessUponSkip,Boolean DuplicateImage ) {
	 	
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown1 = new Select(selectJobType);
	dropdown1.selectByVisibleText(JobType);
	Select dropdown2 = new Select(selectFromEnv);
	dropdown2.selectByVisibleText(fromEnv);
	System.out.println("------"+fromEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
	ui_click(serviceComponent, "selects service component under the env");
	
	if(ConditionRuleSelectorNot.equals(true))
	{
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	Select conditionaldropdown1 = new Select(conditionkeyDropdown);
	conditionaldropdown1.selectByIndex(2);
	}
	else {
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	    //Verify no Condition Rule seen 
	Assert.assertTrue(conditionkeyDropdownOption.size()==1);
	   // ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(NoCache.equals(true)) {
		ui_click(noCache, "noCache");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}
	if(DuplicateImage.equals(true)) {
		ui_click(duplicateImage, "duplicateImage");	
	}    
	ui_click(addStageBtn, "clicks add stage btn");			
		
	return this;
	}

public BuildPipeLinePage createInegratedTestingJob(String JobType,String fromEnv,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean SkipJob,Boolean SuccessUponSkip) {
	 
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown1 = new Select(selectJobType);
	dropdown1.selectByVisibleText(JobType);
	
	if(ConditionRuleSelectorNot.equals(true))
	{
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	Select conditionaldropdown1 = new Select(conditionkeyDropdown);
	conditionaldropdown1.selectByIndex(2);
	}
	else {
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	    //Verify no Condition Rule seen 
	Assert.assertTrue(conditionkeyDropdownOption.size()==1);
	   // ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	} 
	ui_click(addStageBtn, "clicks add stage btn");			
		return this;
	}

public BuildPipeLinePage createtriggerPipelineJob(String JobType,String fromEnv,Boolean ConditionRuleSelectorNot,Boolean TerminatePipelineOnSuccess,Boolean Suspendpipeline,Boolean SkipJob,Boolean SuccessUponSkip) {
 	
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
	Select dropdown1 = new Select(selectJobType);
	dropdown1.selectByVisibleText(JobType);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
	ui_click(serviceComponent, "selects service component under the env");
	
	if(ConditionRuleSelectorNot.equals(true))
	{
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	Select conditionaldropdown1 = new Select(conditionkeyDropdown);
	conditionaldropdown1.selectByIndex(2);
	}
	else {
	ui_click(conditionalRule, "conditionalRule");
	ui_click(addConditionButton, "addConditionButton");
	    //Verify no Condition Rule seen 
	Assert.assertTrue(conditionkeyDropdownOption.size()==1);
	   // ui_click(conditionkeySaveButton, "conditionkeySaveButton");
	}
	if(TerminatePipelineOnSuccess.equals(true)) {
		ui_click(terminatePipelineOnSuccess, "terminatePipelineOnSuccess");	
	}
	if(Suspendpipeline.equals(true)) {
		ui_click(suspendpipeline, "suspendpipeline");	
	}
	if(SkipJob.equals(true)) {
		ui_click(skipJob, "skipJob");	
	}
	if(SuccessUponSkip.equals(true)) {
		ui_click(successUponSkip, "successUponSkip");	
	}   
	ui_click(addStageBtn, "clicks add stage btn");			
		
	return this;
	}


	
public BuildPipeLinePage createSchedulePipeline(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser,String jobType,String fromEnv,String jobType2,String toEnv,String ArtifactName,String jobType3,String ArtifactName2,String prodEnv) {
		
//		ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
//		ui_click(userMenuAppBar, "userMenuAppBar");		
//		ui_click(switchToUSer, "switching to user account");
		
		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for(WebElement element:poc_qaProjectLink) {
			if(element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if(projectSelection) {
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_click(continueBtn, "clicks on add pipeline button");
		ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", pipelineName);
		Select dropdown = new Select(selectPipelineVersionDropdown);
		dropdown.selectByVisibleText(versionType);
		ui_clearAndSetValue(retentionCountField, retentionCount);
//		String PipelineSearch = pipelineName;
		
		
        for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
        	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
        		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
        		break;
        	}
        }
     	
        ui_wait(5);
        for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
        	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
        		
        		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
        	}
        		//.click();        	}
        }
        ui_wait(5);
        //enter wrong chron Pattern and Verify error message
        ui_setvalue(chronPattern,"Chron Pattern","7787");
        ui_click(savePipeline, "clicks on save pipeline button");
        ui_IsElementDisplay(ui_waitForElementToDisplay(chronPatternErrorMessage, Pause.MEDIUM));
        ui_wait(2);
        ui_clearAndSetValue(chronPattern, "30 14 * * *");
        ui_click(tagformanualdeploy, "Click on Tag");
        ui_click(savePipeline, "clicks on save pipeline button");
        ui_wait(5); 
		// adds first stage		
        ui_click(addNewStageToPipeline, "adds first stage"); 
		ui_IsElementDisplay(ui_waitForElementToDisplay(stageName, Pause.MEDIUM));
        ui_setvalue(stageName, "sets first stage name", StageName1);
        ui_wait(5);
        ui_click(addStageBtn, "adds button for first stage");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtn, Pause.MEDIUM));
		//ui_click(addNewJobBtn, "clicks on new job");  
		
		// adds build job to dev
		AddNewJobButtonClick();
		createBuildJob(jobType, fromEnv, false, true, true, true, true, true);
		
        // adds deploy job to dev
		AddNewJobButtonClick();
		createDeployJob(jobType2, fromEnv, ArtifactName, true, true, true, true, true);
		
//      // adds Jira job to dev
//		AddNewJobButtonClick();
//        createJiraJob("Jira Ticket","Create Ticket","Task","Initiating production release for services","BDS-1",true,true,true,true,true);
//	
        // adds second stage
        AddNewStage(StageName2);
        
        // adds promote job from dev
        AddNewJobButtonClickUnderSecondStage();
    	createPromoteJob(jobType3, fromEnv, toEnv, ArtifactName2, true, true, true, true);
		
       /*
        // adds third stage
    	ui_wait(5);
        AddNewStage(StageName3);
        AddNewJobButtonClickUnderThirdStage();
    	
        // adds promote job from qa to prod
        createPromoteJob(jobType3, toEnv, prodEnv, ArtifactName2, true, true, true, true);
       */
        /*Select dropdown21 = new Select(selectJobType);
		dropdown21.selectByVisibleText(jobType2);
		Select dropdown22 = new Select(selectFromEnv);
		dropdown22.selectByVisibleText(prodEnv);
		ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
		ui_click(serviceComponent, "selects service component under the env");
		Select dropdown19 = new Select(selectArtifact);
		dropdown19.selectByVisibleText(ArtifactName2);
		ui_click(addStageBtn, "clicks add stage btn");*/
		
		
		ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(saveWorkFlowBtn, Pause.MEDIUM));
        ui_click(saveWorkFlowBtn, "clicks save workflow btn");
        ui_wait(10);		
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
		ui_wait(3);
	    ui_click(searchDropDown, "searchDropDown");    
		ui_clearAndSetValue(searchPipelineInput, pipelineName);
		ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");		
		ui_wait(10);
		String validateStatusActual = pipelineStatus.getText().trim();
        Assert.assertEquals(validateStatusActual, "IN USE");
        Assert.assertEquals(servicesToRunList.getText().trim(), "automation-682046mu117xjpt");
        ui_click(pipelineHyperLink, "clicks on pipeline hyperlink");
        ui_click(editPipelineInfo, "clicks on edit pipeline info");
		ui_IsElementDisplay(ui_waitForElementToDisplay(basicInfoText, Pause.MEDIUM));
        Assert.assertEquals(basicInfoText.getText().trim(), "Basic Info");
        ui_click(closeEditTile, "clicks on close pipeline info");
		ui_wait(10);
		ui_IsElementDisplay(ui_waitForElementToDisplay(executePipeLineButton, Pause.MEDIUM));
		ui_click(executePipeLineButton, "pipeline execution Start");
		ui_wait(2);
//		ui_click(executePipeLineButton, "pipeline execution Start");
//		ui_wait(2);
//		ui_click(executePipeLineButton, "pipeline execution Start");
		ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineHyperLink, Pause.MEDIUM));
		ui_click(pipelineHyperLink, "clicks pipeline hyperlink");
		ui_IsElementDisplay(ui_waitForElementToDisplay(viewLogsLink, Pause.MEDIUM));
		ui_click(viewLogsLink, "Poc_QA click on View Logs");
		ui_switchToNewWindow();

		}
		return this;
	}	

public BuildPipeLinePage createBuildandDeployPipeline(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser,String jobType,String fromEnv,String jobType2,String toEnv,String ArtifactName,String jobType3,String ArtifactName2,String prodEnv) {
	
//	ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
//	ui_click(userMenuAppBar, "userMenuAppBar");		
//	ui_click(switchToUSer, "switching to user account");
	
	boolean projectSelection = false;
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
	for(WebElement element:poc_qaProjectLink) {
		if(element.getText().trim().equalsIgnoreCase(appName)) {
			element.click();
			projectSelection = true;
			break;
		}
	}
	if(projectSelection) {
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_click(continueBtn, "clicks on add pipeline button");
		ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", pipelineName);
		Select dropdown = new Select(selectPipelineVersionDropdown);
		dropdown.selectByVisibleText(versionType);
		ui_clearAndSetValue(retentionCountField, retentionCount);
		
		
		
        for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
        	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
        		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
        		break;
        	}
        }

        //ui_wait(2);
       // ui_MoveToElement(muiTypographyBody, "moving to muiTypographyBodyn");
       
        ui_wait(2);
        for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
        	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
        		
        		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
        	}
        }
        ui_wait(5);
        ui_click(savePipeline, "clicks on save pipeline button");   
    
    //Search Created Pipeline
    ui_wait(5);
    ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
    ui_wait(4);
    ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
    ui_click(searchDropDown, "searchDropDown");    
	ui_clearAndSetValue(searchPipelineInput, pipelineName);
	ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
	ui_wait(10);
	//Validate Pipeline Status
	String validateStatus_Actual = pipelineStatus.getText().trim();
    Assert.assertEquals(validateStatus_Actual, "IN USE");
    //Edit Created pipeline
    ui_click(editSearchedpipeline, "clicks on edit pipeline Link");
    
	// adds first stage		
    ui_click(addNewStageToPipeline, "adds first stage"); 
	ui_IsElementDisplay(ui_waitForElementToDisplay(stageName, Pause.MEDIUM));
    ui_setvalue(stageName, "sets first stage name", StageName1);
    ui_wait(5);
    ui_click(addStageBtn, "adds button for first stage");
	ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtn, Pause.MEDIUM));
	//ui_click(addNewJobBtn, "clicks on new job");  
	
	// adds build job to dev
	AddNewJobButtonClick();
	createBuildJob(jobType, fromEnv, false, false, false, false, false, false);
	
	
	ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(saveWorkFlowBtn, Pause.MEDIUM));
    ui_click(saveWorkFlowBtn, "clicks save workflow btn");
    ui_wait(10);
	ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
	ui_wait(3);
    ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
	ui_click(searchDropDown, "searchDropDown");    
	ui_clearAndSetValue(searchPipelineInput, pipelineName);
	ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
	ui_wait(10);
	String validateStatusActual = pipelineStatus.getText().trim();
    Assert.assertEquals(validateStatusActual, "IN USE");
    Assert.assertEquals(servicesToRunList.getText().trim(), "automation-682046mu117xjpt");
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(executePipeLineButton, Pause.MEDIUM));
	ui_click(executePipeLineButton, "pipeline execution Start");
	ui_wait(2);
	ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineHyperLink, Pause.MEDIUM));
	ui_click(pipelineHyperLink, "clicks pipeline hyperlink");
	ui_wait(2);
	assertEquals(buildEnvironment.getText().contains("devcommunity"), true);
	assertEquals(tag.getText().contains("latest"), true);
	assertEquals(buildJobType.getText().contains("Build"), true);	
	ui_wait(2);
	assertEquals(servicename.getText().contains("automation-682046mu117xjpt"), true);	
	ui_IsElementDisplay(ui_waitForElementToDisplay(expendBuildButton, Pause.MEDIUM));
	ui_click(expendBuildButton, "clicks expendBuildButton hyperlink");
	ui_wait(90);
	assertEquals(cloningRepository.getText().contains("success"), true);
	assertEquals(preHooksExecuting.getText().contains("success"), true);
	assertEquals(buildDockerImage.getText().contains("success"), true);
	assertEquals(pushdockerimage.getText().contains("success"), true);
	assertEquals(postHooksExecuting.getText().contains("success"), true);
	assertEquals(branchName.getText().contains("master"), true);
	assertEquals(artifact.getText().contains("latest"), true);
	
	
	}
	return this;
}
public BuildPipeLinePage createSCMPollPipeline(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser,String jobType,String fromEnv,String jobType2,String toEnv,String ArtifactName,String jobType3,String ArtifactName2,String prodEnv) {
	
//	ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
//	ui_click(userMenuAppBar, "userMenuAppBar");		
//	ui_click(switchToUSer, "switching to user account");
	
	boolean projectSelection = false;
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
	for(WebElement element:poc_qaProjectLink) {
		if(element.getText().trim().equalsIgnoreCase(appName)) {
			element.click();
			projectSelection = true;
			break;
		}
	}
	if(projectSelection) {
	ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
	ui_click(continueBtn, "clicks on add pipeline button");
	ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", pipelineName);
	Select dropdown = new Select(selectPipelineVersionDropdown);
	dropdown.selectByVisibleText(versionType);
	ui_clearAndSetValue(retentionCountField, retentionCount);
//	String PipelineSearch = pipelineName;
	
	
    for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
    	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
    		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
    		break;
    	}
    }
 	
    ui_wait(2);
    for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
    	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
    		
    		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
    	}
    		//.click();        	}
    }
    ui_wait(2);
    ui_setvalue(frequencySCMPipeline,"Frequency Pattern","30");
    ui_click(savePipeline, "clicks on save pipeline button");
    //Search Created Pipeline
    ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
    ui_IsElementDisplay(ui_waitForElementToDisplay(searchPipeLine, Pause.MEDIUM));
	ui_clearAndSetValue(searchPipeLine, pipelineName);
	searchPipeLine.sendKeys(Keys.ENTER);
	ui_wait(10);
	//Validate Pipeline Status
	String validateStatus_Actual = pipelineStatus.getText().trim();
    Assert.assertEquals(validateStatus_Actual, "IN USE");
    //Edit Created pipeline
    ui_click(editSearchedpipeline, "clicks on edit pipeline Link");
    
	// adds first stage		
    AddNewStage(StageName1); 
	
	// adds build job to dev
	AddNewJobButtonClick();
	createAPIJob(jobType, "GET","http://122.160.30.218:56911/api/v1/project/6/pipeline/recent/activity/","","30", false, true, true, true, true, true);
	ui_wait(4);
	AddNewJobButtonClick();
	createRollbackJob(jobType2, "-1",true, true, true, true);
	ui_wait(4);
  // adds Jira job to dev
	AddNewJobButtonClick();
    createJiraJob("Jira Ticket","Create Ticket","Task","Initiating production release for services","BDS-1",true,true,true,true,true);
    ui_wait(4);
    AddNewJobButtonClick();
    createJiraJob("Jira Ticket","Add Comment","Task","Initiating production release for services","BDS-1",true,true,true,true,true);
    ui_wait(4);
    // adds second stage
    AddNewStage(StageName2);
    AddNewJobButtonClickUnderSecondStage();
    // adds promote job from dev
	createPromoteJob(jobType3, fromEnv, toEnv, ArtifactName2, true, true, true, true);
	
    // adds deploy job to qa
    
    ui_IsElementDisplay(ui_waitForElementToDisplay(selectJobType, Pause.MEDIUM));
    ui_wait(5);
	Select dropdown12 = new Select(selectJobType);
	dropdown12.selectByVisibleText(jobType2);
	Select dropdown13 = new Select(selectFromEnv);
	dropdown13.selectByVisibleText(toEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent1, Pause.MEDIUM));
	ui_click(serviceComponent1, "selects service component under the env");
	Select dropdown14 = new Select(selectArtifact);
	dropdown14.selectByVisibleText(ArtifactName2);
	ui_click(addStageBtn, "clicks add stage btn");
	ui_IsElementDisplay(ui_waitForElementToDisplay(addNewStageToPipeline, Pause.MEDIUM));
    ui_click(addNewStageToPipeline, "adds third stage"); 
    
    // adds third stage
    
    ui_setvalue(stageName, "sets first stage name", StageName3);
    ui_wait(5);
    ui_click(addStageBtn, "adds button for third stage");
	ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderThirdStage, Pause.MEDIUM));
    ui_click(addNewJobBtnUnderThirdStage, "clicks on new job");
    
    // adds promote job from qa
    
    ui_wait(5);
	Select dropdown15 = new Select(selectJobType);
	dropdown15.selectByVisibleText(jobType3);
	Select dropdown16 = new Select(selectFromEnv);
	dropdown16.selectByVisibleText(toEnv);
//	ui_IsElementDisplay(ui_waitForElementToDisplay(targetEnv, Pause.MEDIUM));
	ui_wait(5);
	Select dropdown17 = new Select(targetEnv);
	dropdown17.selectByVisibleText(prodEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent1, Pause.MEDIUM));
	ui_click(serviceComponent1, "selects service component under the env");
	Select dropdown18 = new Select(selectArtifact);
	dropdown18.selectByVisibleText(ArtifactName2);
	ui_click(addStageBtn, "clicks add stage btn");

	ui_IsElementDisplay(ui_waitForElementToDisplay(addNewJobBtnUnderThirdStage, Pause.MEDIUM));
    ui_click(addNewJobBtnUnderThirdStage, "clicks on new job");
    
    // adds deploy job to prod
    
    ui_wait(5);
	Select dropdown21 = new Select(selectJobType);
	dropdown21.selectByVisibleText(jobType2);
	Select dropdown22 = new Select(selectFromEnv);
	dropdown22.selectByVisibleText(prodEnv);
	ui_IsElementDisplay(ui_waitForElementToDisplay(serviceComponent, Pause.MEDIUM));
	ui_click(serviceComponent, "selects service component under the env");
	Select dropdown19 = new Select(selectArtifact);
	dropdown19.selectByVisibleText(ArtifactName2);
	ui_click(addStageBtn, "clicks add stage btn");
	ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(saveWorkFlowBtn, Pause.MEDIUM));
    ui_click(saveWorkFlowBtn, "clicks save workflow btn");
    ui_wait(10);
	ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
    ui_IsElementDisplay(ui_waitForElementToDisplay(searchPipeLine, Pause.MEDIUM));
	ui_clearAndSetValue(searchPipeLine, pipelineName);
	searchPipeLine.sendKeys(Keys.ENTER);
	ui_wait(10);
	String validateStatusActual = pipelineStatus.getText().trim();
    Assert.assertEquals(validateStatusActual, "IN USE");
    Assert.assertEquals(servicesToRunList.getText().trim(), "automation-682046mu117xjpt");
    ui_click(pipelineHyperLink, "clicks on pipeline hyperlink");
    ui_click(editPipelineInfo, "clicks on edit pipeline info");
	ui_IsElementDisplay(ui_waitForElementToDisplay(basicInfoText, Pause.MEDIUM));
    Assert.assertEquals(basicInfoText.getText().trim(), "Basic Info");
    ui_click(closeEditTile, "clicks on close pipeline info");
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(executePipeLineButton, Pause.MEDIUM));
	ui_click(executePipeLineButton, "pipeline execution Start");
	ui_wait(2);
//	ui_click(executePipeLineButton, "pipeline execution Start");
//	ui_wait(2);
//	ui_click(executePipeLineButton, "pipeline execution Start");
	ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineHyperLink, Pause.MEDIUM));
	ui_click(pipelineHyperLink, "clicks pipeline hyperlink");
	ui_IsElementDisplay(ui_waitForElementToDisplay(viewLogsLink, Pause.MEDIUM));
	ui_click(viewLogsLink, "Poc_QA click on View Logs");
	ui_switchToNewWindow();

	}
	return this;
}	

@FindBy(xpath = "//p//span[text()='IN USE']")
WebElement pipelineStatus;
@FindBy(xpath = "//button[@aria-label='close']")
WebElement closeEditTile;

@FindBy(xpath = "//div[text()='Basic Info']")
WebElement basicInfoText;

@FindBy(xpath = "//button[text()='Edit Basic Info ']")
WebElement editPipelineInfo;

@FindBy(xpath = "//p[text()='Services for the pipeline to run on']/..//div//span")
WebElement servicesToRunList;

@FindBy(xpath = "//span[.='Job Type:']/following-sibling::span")
WebElement buildJobType;
@FindBy(xpath = "//div[@class='flaticon-expand-button']")
WebElement expendBuildButton;
@FindBy(xpath = "//div[@class='flaticon-expand-arrow']")
WebElement expendRunningJobStatus;
@FindBy(xpath = "//div[@class='tag-under-card']")
WebElement buildEnvironment;
@FindBy(xpath = "//div[@class='tag-under-card tag-bg']")
WebElement tag;
@FindBy(xpath = "(//div[@class='service'])[1]")
WebElement servicename;
@FindBy(xpath = "//div[@class='service'][contains(text(),'Cloning Repository')]/following-sibling::div[1]")
WebElement cloningRepository;
@FindBy(xpath = "//div[@class='service'][contains(text(),'Pre Hooks Executing')]/following-sibling::div[1]")
WebElement preHooksExecuting;
@FindBy(xpath = "//div[@class='service'][contains(text(),'Build Docker Image')]/following-sibling::div[1]")
WebElement buildDockerImage;
@FindBy(xpath = "//div[@class='service'][contains(text(),'Push docker image')]/following-sibling::div[1]")
WebElement pushdockerimage;
@FindBy(xpath = "//div[@class='service'][contains(text(),'Post Hooks Executing')]/following-sibling::div[1]")
WebElement postHooksExecuting;
@FindBy(xpath = "//span[@class='tag-image border-bottom'][contains(text(),'Branch Name')]")
WebElement branchName;
@FindBy(xpath = "//span[@class='tag-image'][contains(text(),'Artifact: : ')]")
WebElement artifact;
@FindBy(xpath = "//a[@title='view logs']")
WebElement viewlogs;


	/**
	 * 
	 */

    
	
	@FindBy(xpath = "//input[@name='name' and @placeholder='Name']")
	WebElement searchPipeLineInput;
	
	
	@FindBy(xpath = "//button[@class='btn btn-submit'][text()='Refresh']")
	WebElement refreshpipelinePage;

	@FindBy(xpath = "//a[contains(text(),'BasicPipeline')]")
	WebElement pipelineHyperLink;
	
	public BuildPipeLinePage executeBasicPipeline(String appName,String pipelineNameArg) {
		ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
		ui_click(userMenuAppBar, "userMenuAppBar");		
		ui_click(switchToUSer, "switching to user account");
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
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchPipeLineInput, Pause.MEDIUM));
		ui_clearAndSetValue(searchPipeLineInput, pipelineNameArg);
		searchPipeLineInput.sendKeys(Keys.ENTER);
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(executePipeLineButton, Pause.MEDIUM));
		ui_click(executePipeLineButton, "pipeline execution Start");
		ui_wait(2);
//		ui_click(executePipeLineButton, "pipeline execution Start");
//		ui_wait(2);
//		ui_click(executePipeLineButton, "pipeline execution Start");
		ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineHyperLink, Pause.MEDIUM));
		ui_click(pipelineHyperLink, "clicks pipeline hyperlink");
		ui_IsElementDisplay(ui_waitForElementToDisplay(viewLogsLink, Pause.MEDIUM));
		ui_click(viewLogsLink, "Poc_QA click on View Logs");
		ui_switchToNewWindow();
//		ui_IsElementDisplay(ui_waitForElementToDisplay(postHookExecutingSuccessLink, Pause.MEDIUM));
//		ui_click(postHookExecutingSuccessLink, "postHookExecuting click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status = consoleLogs.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status);
		}
		return this;
	}
	
	String PipelineYAMLFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testfiles\\upload\\UploadYAML\\JiraPipeline.yml";
	
	@FindBy(xpath = "//label[@for='file-input']//span[text()='upload']")
	WebElement uploadYAML;
	
	public BuildPipeLinePage createJiraPipeline(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser) {
		
		String pipelineName1 = "AdvanceJiraPipeline" + RandomStrings.generateRandomString(9);
//		String StageName1 = "dev" + RandomStrings.generateRandomString(7);
//		String StageName2 = "qa" + RandomStrings.generateRandomString(7);
//		String StageName3 = "prod" + RandomStrings.generateRandomString(7);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
//		ui_click(userMenuAppBar, "userMenuAppBar");		
//		ui_click(switchToUSer, "switching to user account");
		
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
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_click(continueBtn, "clicks on add pipeline button");
		ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", pipelineName1);
		Select dropdown = new Select(selectPipelineVersionDropdown);
		dropdown.selectByVisibleText(versionType);
		ui_clearAndSetValue(retentionCountField, retentionCount);

        for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
        	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
        		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
        		break;
        	}
        }
//		if (triggerTypeRadioBtn.getAttribute("value").equals("Manual"))
//			ui_click(triggerTypeRadioBtn, "Poc_QA triggerTypeRadioBtn");
      //ui_wait(2);
      //ui_MoveToElement(muiTypographyBody, "moving to muiTypographyBodyn");
       
        ui_wait(2);
        for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
        	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
        		
        		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
        	}
        }
        ui_wait(2);
        ui_click(savePipeline, "clicks on save pipeline button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(validatePipelineName, Pause.MEDIUM));
        String validatePipelineNameActual = validatePipelineName.getText().trim();
        Assert.assertEquals(validatePipelineNameActual, pipelineName1);
		ui_IsElementDisplay(ui_waitForElementToDisplay(uploadYAML, Pause.MEDIUM));
        ui_click(uploadYAML, "clicks on upload YAML");
		ui_wait(5);
		ui_FileUpload(Configuration.get("browser"), PipelineYAMLFilePath);
		ui_wait(15);
		ui_getUIDriver().switchTo().defaultContent();
		ui_IsElementDisplay(ui_waitForElementToDisplay(saveWorkFlowBtn, Pause.MEDIUM));
        ui_click(saveWorkFlowBtn, "clicks save workflow btn");
		}
		return this;
	}
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-dismissible')]//p")
	WebElement errorMessage;
	
	public BuildPipeLinePage createBasicPipelineNegativeTest4(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser, String existingPipelineName) {
		ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
		ui_click(userMenuAppBar, "userMenuAppBar");		
		ui_click(switchToUSer, "switching to user account");
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
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_click(continueBtn, "clicks on add pipeline button");
		ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", existingPipelineName);
		Select dropdown = new Select(selectPipelineVersionDropdown);
		dropdown.selectByVisibleText(versionType);
		ui_clearAndSetValue(retentionCountField, retentionCount);
		
		
		
        for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
        	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
        		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
        		break;
        	}
        }

        ui_wait(2);
        ui_MoveToElement(muiTypographyBody, "moving to muiTypographyBodyn");
       
        ui_wait(2);
        for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
        	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
        		
        		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
        	}
        }
        ui_wait(2);
        ui_click(savePipeline, "clicks on save pipeline button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(saveWorkFlowBtn, Pause.MEDIUM));
        ui_click(saveWorkFlowBtn, "clicks save workflow btn");
		ui_IsElementDisplay(ui_waitForElementToDisplay(errorMessage, Pause.MEDIUM));
		Assert.assertEquals("There are no jobs in this stage, No stage added", errorMessage.getText().trim());
		}
	    return this;
   }
	
	@FindBy(xpath = "//input[@name='retention_execution_count' and @placeholder='Retention Count' and @class='error']")
	WebElement errorMessage2;
	
	public BuildPipeLinePage createBasicPipelineNegativeTest5(String appName,String versionType,String retentionCount,String triggerType,ArrayList<String>pipelineUser) {
		ui_IsElementDisplay(ui_waitForElementToDisplay(userMenuAppBar, Pause.MEDIUM));
		ui_click(userMenuAppBar, "userMenuAppBar");		
		ui_click(switchToUSer, "switching to user account");
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
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
		ui_click(continueBtn, "clicks on add pipeline button");
		ui_setvalue(addNameToNewPipeline, "Gives unique name to pipeline", pipelineName);
		Select dropdown = new Select(selectPipelineVersionDropdown);
		dropdown.selectByVisibleText(versionType);
		ui_clearAndSetValue(retentionCountField, retentionCount);
		
		
		
        for (int i = 0; i < triggerTypeRadioBtn.size(); i++) {
        	if (triggerType.contains(triggerTypeRadioBtn.get(i).getAttribute("value").trim()) && !(MUIRadioButtonsChecked.get(i).getAttribute("class").contains("Mui-checked"))) {
        		ui_click(triggerTypeRadioBtn.get(i),"Selecting the Radio button named as -"+triggerTypeRadioBtn.get(i).getAttribute("value").trim());
        		break;
        	}
        }

        ui_wait(2);
        ui_MoveToElement(muiTypographyBody, "moving to muiTypographyBodyn");
       
        ui_wait(2);
        for (int i = 0; i < pipelineAssignUserRoleCheckbox.size(); i++) {
        	if (pipelineUser.contains(pipelineAssignUserRoleCheckbox.get(i).getAttribute("value").trim())) {
        		
        		ui_ActionMoveAndClick(pipelineAssignUserRoleCheckbox.get(i),"Clicking on radio Button-"+pipelineAssignUserRoleCheckbox.get(i));
        	}
        }
        ui_wait(2);
        ui_click(savePipeline, "clicks on save pipeline button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(errorMessage2, Pause.MEDIUM));
		}
        return this;
	}
	
	@FindBy(xpath = "//button[contains(@class,'btn btn-danger')]//span[text()=' Manage Failures']")
	WebElement manageFailurePopUp;
	
	public BuildPipeLinePage managePopupTest(String appName, String existingPipelineName) {
		ui_click(userMenuAppBar, "userMenuAppBar");		
		ui_click(switchToUSer, "switching to user account");
		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for(WebElement element:poc_qaProjectLink) {
			if(element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if(projectSelection) {
		ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(PipelineSubject, Pause.MEDIUM));
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchPipeLine, Pause.MEDIUM));
		ui_clearAndSetValue(searchPipeLine, existingPipelineName);
		searchPipeLine.sendKeys(Keys.ENTER);
		ui_click(pipeLineExecutionHistory, "view pipeline excution history");
		ui_IsElementDisplay(ui_waitForElementToDisplay(reExecutePipeLineBtn, Pause.MEDIUM));
		ui_click(reExecutePipeLineBtn, "view pipeline re-excution history");
//		ui_click(executePipeLineButton, "Poc_QA execution Start");
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(existingPipeLine, Pause.MEDIUM));
		ui_click(existingPipeLine, "Poc_QA execution Start");
		ui_IsElementDisplay(ui_waitForElementToDisplay(manageFailurePopUp, Pause.MEDIUM));
		ui_click(manageFailurePopUp, "manage Failure PopUp");
		
		}
		return this;
	}
	
public BuildPipeLinePage searchPipeline(String pipeline) {
		
		ui_wait(5);
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(searchPipeLine, Pause.MEDIUM));
		ui_clearAndSetValue(searchPipeLine, pipeline);
		searchPipeLine.sendKeys(Keys.ENTER);
		
		return this;
	}
	
	@FindBy(xpath = "(//span[@title='Edit Pipeline']/a)[1]")
	 WebElement firstpipelineEditLink;
	@FindBy(xpath = "(//button[@title='View Execution History'])[1]")
	WebElement firstpipelineHistoryLink;
	@FindBy(xpath = "//h6[contains(text(),'Execution History of ')]")
	WebElement pipelineHistoryTitle;
	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit MuiIconButton-edgeEnd'])[2]")
	WebElement pipelineHistoryClose;
	@FindBy(xpath = "(//button[contains(text(),'Run With Parameters')])[1]")
	WebElement firstPipelineRunwithParameter;
	@FindBy(xpath = "//span[contains(@class,'sub-heading-controller')]/span")
	WebElement parameterTagPipeline;
	@FindBy(xpath = "(//input[@name='components'])[2]")
	WebElement firstServiceCheckbox;
	@FindBy(xpath = "//*[text()='Change Branch and tag']")
	WebElement changeBranchandtagLink;
	
public BuildPipeLinePage editPipeline(String appName) {
		
	boolean projectSelection = false;
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
	for(WebElement element:poc_qaProjectLink) {
		if(element.getText().trim().equalsIgnoreCase(appName)) {
			element.click();
			projectSelection = true;
			break;
		}
	}
	if(projectSelection) {
	ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
	ui_click(firstpipelineEditLink, "first pipeline EditLink");
	ui_click(editPipelineInfo, "clicks on edit pipeline info");
	ui_IsElementDisplay(ui_waitForElementToDisplay(basicInfoText, Pause.MEDIUM));
   Assert.assertEquals(basicInfoText.getText().trim(), "Basic Info");
   ui_click(closeEditTile, "clicks on close pipeline info");
	
	}
		return this;
	}

public BuildPipeLinePage historyPipeline(String appName) {
	
	boolean projectSelection = false;
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
	for(WebElement element:poc_qaProjectLink) {
		if(element.getText().trim().equalsIgnoreCase(appName)) {
			element.click();
			projectSelection = true;
			break;
		}
	}
	if(projectSelection) {
	ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
	ui_click(firstpipelineHistoryLink, "first pipeline History Link");	
   Assert.assertEquals(ui_IsElementPresent(pipelineHistoryTitle, "5"),true);
   ui_click(pipelineHistoryClose, "pipeline History Close");	
	}
	return this;
	}

@FindBy(xpath = "//span[@class='flaticon-play-rounded-button']")
WebElement runPipeline;

@FindBy(xpath = "//div[@class='status-chip status-chip-warning' and text()=' Running ']")
WebElement pipelineStatusAfterPlayButton;

public BuildPipeLinePage RunwithParameter(String appName,String pipeline) {
	
	boolean projectSelection = false;
	ui_wait(5);
	ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
	for(WebElement element:poc_qaProjectLink) {
		if(element.getText().trim().equalsIgnoreCase(appName)) {
			element.click();
			projectSelection = true;
			break;
		}
	}
	if(projectSelection) {
	ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
	searchPipeline(pipeline);
	ui_click(firstPipelineRunwithParameter, "first Pipeline RunwithParameter");	
	Assert.assertEquals(parameterTagPipeline.getText(), "Please note: artifact tag has to be unique");
	ui_click(firstServiceCheckbox, "first Service Checkbox");
	ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(changeBranchandtagLink, Pause.MEDIUM));
	ui_click(changeBranchandtagLink, "change Branch and tag Link");
	}
	return this;
	}
	
	@FindBy(xpath = "(//span[@class='stage-name-execution'])[1]")
	WebElement addedStageName1;
	
	@FindBy(xpath = "(//span[@class='stage-name-execution'])[2]")
	WebElement addedStageName2;
	
	@FindBy(xpath = "(//span[@class='stage-name-execution'])[3]")
	WebElement addedStageName3;
	
	public BuildPipeLinePage executeFromOverviewPage() {
		
		ui_wait(5);
		ui_click(runPipeline, "Poc_QA runPipeline");
		ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineStatusAfterPlayButton, Pause.MEDIUM));
		ui_click(existingPipeLine, "clicks on pipeline hyperlink");
		ui_wait(5);
        Assert.assertEquals(addedStageName1.getText().trim(), StageName1);
        Assert.assertEquals(addedStageName2.getText().trim(), StageName2);
        Assert.assertEquals(addedStageName3.getText().trim(), StageName3);

		return this;
	}

	@FindBy(xpath = "//div[@class='service-name']/a")
	WebElement searchPipelinehyperlink;
	@FindBy(xpath = "//a[@title='view logs']/following-sibling::div[1]")
	List<WebElement> buildStatus;
	@FindBy(xpath = "//div[@class='service']")
	List<WebElement> serviceName;
	@FindBy(xpath = "//span[@class='tag-image border-bottom']")
	WebElement buildBranch;
	
	public BuildPipeLinePage FindStatusandBranch(String appName,String pipelinename) {
		boolean projectSelection = false;
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			searchPipeline(pipelinename);
			ui_click(searchPipelinehyperlink, "search Pipeline hyperlink");
			ui_wait(5);
			for(int i=0;i<jobdrops.size();i++) {
				ui_click(jobdrops.get(i), "Expend Job");
				ui_wait(2);
			}
			for(int i=0;i<jobdrops.size();i++) {
				System.out.println("------"+serviceName.get(i).getText());
				Assert.assertEquals(buildStatus.get(i).getText().trim(), "SUCCESS");
				ui_wait(1);
				ui_click(jobdrops.get(i), "");
				ui_wait(2);
				Assert.assertEquals(buildBranch.getText().replace("Branch Name:  ", ""), "master");
			}
		}
		
		return this;
	}
	
	@FindBy(xpath = "//button[text()='Confirm']" )
	WebElement confirmReplaypipeline;
	@FindBy(xpath = "//button[text()='View  Run Parameters']" )
	WebElement viewRunParameters;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[1])[1]" )
	WebElement envtypeRow1;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[2])[1]" )
	WebElement envNameRow1;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[3])[1]" )
	WebElement serviceNameRow1;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[4])[1]" )
	WebElement branchNameRow1;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[5])[1]" )
	WebElement deploymentNameRow1;
	
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[1])[2]" )
	WebElement envtypeRow2;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[2])[2]" )
	WebElement envNameRow2;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[3])[2]" )
	WebElement serviceNameRow2;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[4])[2]" )
	WebElement branchNameRow2;
	@FindBy(xpath = "(//div[@class='sub-env-row']/span[5])[2]" )
	WebElement deploymentNameRow2;
	@FindBy(xpath = "//*[@class='MuiSvgIcon-root color-white MuiSvgIcon-fontSizeLarge']" )
	WebElement closeViewRunParameterWindow;
	
	@FindBy(xpath = "(//button[@title='Replay Pipeline'])[1]")
	WebElement replayPipelinefirstBtn;
	@FindBy(xpath = "//span[@title='Revoke Pipeline']/button")
	WebElement revokePipeline;
	
	
	public BuildPipeLinePage ReplayPipeline(String appName,String pipelinename) {
		boolean projectSelection = false;
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineOverviewLink, Pause.MEDIUM));
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			searchPipeline(pipelinename);
			ui_wait(4);
			ui_click(searchPipelinehyperlink, "search Pipeline hyperlink");
			ui_wait(5);			
			ui_click(viewRunParameters, "View Run Parameters");
			ui_wait(3);	
			//-----------------get View Run Parameters all Row Value
			String EnvTypeRow1=envtypeRow1.getText();
			String EnvNameRow1=envNameRow1.getText();
			String ServiceNameRow1=serviceNameRow1.getText();
			String BranchNameRow1=branchNameRow1.getText();
			String DeploymentNameRow1=deploymentNameRow1.getText();
			
			String EnvTypeRow2=envtypeRow2.getText();
			String EnvNameRow2=envNameRow2.getText();
			String ServiceNameRow2=serviceNameRow2.getText();
			String BranchNameRow2=branchNameRow2.getText();
			String DeploymentNameRow2=deploymentNameRow2.getText();
			ui_wait(3);
			ui_click(closeViewRunParameterWindow, "close ViewRunParameter Window");
			ui_wait(3);
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			searchPipeline(pipelinename);
			ui_click(pipeLineExecutionHistory, "view pipeline excution history");
			ui_IsElementDisplay(ui_waitForElementToDisplay(replayPipelinefirstBtn, Pause.MEDIUM));
			ui_click(replayPipelinefirstBtn, "view pipeline replayPipelinefirstBtn");
			ui_wait(3);
			//-----------------get Replay all Row Value
			String History_EnvTypeRow1=envtypeRow1.getText();
			String History_EnvNameRow1=envNameRow1.getText();
			String History_ServiceNameRow1=serviceNameRow1.getText();
			String History_BranchNameRow1=branchNameRow1.getText();
			String History_DeploymentNameRow1=deploymentNameRow1.getText();
			
			String History_EnvTypeRow2=envtypeRow2.getText();
			String History_EnvNameRow2=envNameRow2.getText();
			String History_ServiceNameRow2=serviceNameRow2.getText();
			String History_BranchNameRow2=branchNameRow2.getText();
			String History_DeploymentNameRow2=deploymentNameRow2.getText();
			
			//---------------Validate View Run Parameter Rows with Replay Pop all Rows
			Assert.assertEquals(EnvTypeRow1, History_EnvTypeRow1);
			Assert.assertEquals(EnvNameRow1, History_EnvNameRow1);
			Assert.assertEquals(ServiceNameRow1, History_ServiceNameRow1);
			Assert.assertEquals(BranchNameRow1, History_BranchNameRow1);
			Assert.assertEquals(DeploymentNameRow1, History_DeploymentNameRow1);
			
			Assert.assertEquals(EnvTypeRow2, History_EnvTypeRow2);
			Assert.assertEquals(EnvNameRow2, History_EnvNameRow2);
			Assert.assertEquals(ServiceNameRow2, History_ServiceNameRow2);
			Assert.assertEquals(BranchNameRow2, History_BranchNameRow2);
			Assert.assertEquals(DeploymentNameRow2, History_DeploymentNameRow2);
			ui_wait(2);
			ui_click(confirmReplaypipeline, "confirm Replaypipeline");
			ui_wait(5);
			ui_click(searchPipelinehyperlink, "search Pipeline hyperlink");
			ui_wait(5);		
			ui_click(viewRunParameters, "View Run Parameters");
			ui_wait(3);	
			//-------------Compare Last Execution Rows with Replay Rows
			String EnvTypeRow1_1=envtypeRow1.getText();
			String EnvNameRow1_1=envNameRow1.getText();
			String ServiceNameRow1_1=serviceNameRow1.getText();
			String BranchNameRow1_1=branchNameRow1.getText();
			String DeploymentNameRow1_1=deploymentNameRow1.getText();
			
			String EnvTypeRow2_1=envtypeRow2.getText();
			String EnvNameRow2_1=envNameRow2.getText();
			String ServiceNameRow2_1=serviceNameRow2.getText();
			String BranchNameRow2_1=branchNameRow2.getText();
			String DeploymentNameRow2_1=deploymentNameRow2.getText();
			ui_wait(3);
			
			Assert.assertEquals(EnvTypeRow1, EnvTypeRow1_1);
			Assert.assertEquals(EnvNameRow1, EnvNameRow1_1);
			Assert.assertEquals(ServiceNameRow1, ServiceNameRow1_1);
			Assert.assertEquals(BranchNameRow1, BranchNameRow1_1);
			Assert.assertEquals(DeploymentNameRow1, DeploymentNameRow1_1);
			
			Assert.assertEquals(EnvTypeRow2, EnvTypeRow2_1);
			Assert.assertEquals(EnvNameRow2, EnvNameRow2_1);
			Assert.assertEquals(ServiceNameRow2, ServiceNameRow2_1);
			Assert.assertEquals(BranchNameRow2, BranchNameRow2_1);
			Assert.assertEquals(DeploymentNameRow2, DeploymentNameRow2_1);
		}
		
		return this;
	}

	@FindBy(xpath = "//div[@class='owner-name']/span[2]")
	List<WebElement> pipilineexecutionTime;
	
	public BuildPipeLinePage VerifyPipelineOrderonHistory(String appName,String pipelinename) throws ParseException {
		boolean projectSelection = false;
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineOverviewLink, Pause.MEDIUM));
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			searchPipeline(pipelinename);
			ui_wait(5);
			ui_click(pipeLineExecutionHistory, "pipeLineExecutionHistory");
			ui_wait(7);
			ArrayList<Date> list=new ArrayList<Date>();
			ArrayList<Date> listBeforeSorting=new ArrayList<Date>();
			  SimpleDateFormat formatter=new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");  
			  
			for(int i=0;i<pipilineexecutionTime.size();i++) {
				String value=pipilineexecutionTime.get(i).getText().trim();
				Date date=formatter.parse(value);
				listBeforeSorting.add(date);
				list.add(date);
			}
			
			Collections.sort(list, Collections.reverseOrder());
			
			for(int i=0;i<list.size();i++) {
				System.out.println(listBeforeSorting.get(i)+"-----------"+list.get(i));
				Assert.assertEquals(listBeforeSorting.get(i), list.get(i));
			}
			
		}
		
		
		return this;
	}

	public BuildPipeLinePage VerifyPipelineButtons(String appName,String pipelinename) {
		boolean projectSelection = false;
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineOverviewLink, Pause.MEDIUM));
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			searchPipeline(pipelinename);
			ui_wait(5);
			Assert.assertEquals(ui_IsElementPresent(executePipeLineButton, "5"), true);
			Assert.assertEquals(ui_IsElementPresent(editSearchedpipeline, "5"), true);
			Assert.assertEquals(ui_IsElementPresent(revokePipeline, "5"), true);
			Assert.assertEquals(ui_IsElementPresent(pipeLineExecutionHistory, "5"), true);
			Assert.assertEquals(ui_IsElementPresent(firstPipelineRunwithParameter, "5"), true);
		}
		
		
		return this;
	}
	
	@FindBy(xpath = "(//input[@name='components'])[1]")
	WebElement allServiceCheckbox;
	@FindBy(xpath = "//button[text()='Run With Parameters']")
	WebElement runwithparameters;
	@FindBy(xpath = "//button[contains(text(),'Approve')]")
	WebElement buttonApprove;
	@FindBy(xpath = "//input[@name='pipelines']/..")
	List<WebElement> pipelineList;
	@FindBy(xpath = "//input[@name='components']/..")
	List<WebElement> runwithparameter_servicelist;
	@FindBy(xpath = "//button[text()='Run Pipeline']")
	WebElement runpipeline;
	@FindBy(xpath = "//div[text()='Summary:']/..//div[2]")
	WebElement summaryJira;
	@FindBy(xpath = "//div[@class=' flaticon-thumb-up-button']")
	List<WebElement> thumbsUp;
	@FindBy(xpath = "//input[@name='comment']")
	WebElement inputCommentOne;
	@FindBy(xpath = "//button[text()='Continue with failures']")
	WebElement continueWithFailure;
	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueWithFailedJob;
	
	public BuildPipeLinePage JiraExtensivePipeline_RunwithParameter(String appName, String PipelineName) {

		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {

			// --------------------------------------Search for pipelinePreProd-------------------
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
			ui_wait(3);
		    ui_click(searchDropDown, "searchDropDown");    
			ui_clearAndSetValue(searchPipelineInput, PipelineName);
			ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
			ui_wait(3);
			ui_click(runwithparameters, "Click on runwithparameters");
			ui_wait(2);
			
			ui_click(allServiceCheckbox, "allService Checkbox");
			ui_click(runpipeline, "Click on runwithparameters button");
			ui_wait(10);
			ui_click(searchPipelinehyperlink, "Click on pipeline hyperlink");
			
			// -------------------------------Jira Ticket Create-----------------
			ui_wait(10);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(60);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(20);
			ui_wait(5);
			Assert.assertEquals(summaryJira.getText().trim().contains("Initiating production release for services"), true);
			ui_getUIDriver().navigate().refresh();
			
			// ----------------------- --------Go No Go Approval Queue-------------------------
			ui_wait(5);
			ui_click(thumbsUp.get(0), "Click on jobDropOne");
			ui_IsElementDisplay(ui_waitForElementToDisplay(inputCommentOne, Pause.MEDIUM));
			ui_clearAndSetValue(inputCommentOne, "Jira Auto Test Ticket is created");
			ui_click(buttonApprove, "Click on jobDropOne");
			ui_wait(30);
			ui_getUIDriver().navigate().refresh();
			ui_click(jobdrops.get(1), "Click on jobDropOne");
			ui_wait(20);
			ui_click(jobdrops.get(1), "Click on jobDropOne");
			ui_getUIDriver().navigate().refresh();
			//----------------------Dev Lead Sign Off----------------------------
			ui_wait(5);
			ui_click(thumbsUp.get(1), "Click on jobDropOne");
			ui_IsElementDisplay(ui_waitForElementToDisplay(inputCommentOne, Pause.MEDIUM));
			ui_clearAndSetValue(inputCommentOne, "Jira Auto Test Ticket is created");
			ui_click(buttonApprove, "Click on jobDropOne");
			ui_wait(40);
			ui_click(jobdrops.get(2), "Click on jobDropOne");
			ui_wait(10);
			ui_click(jobdrops.get(2), "Click on jobDropOne");
			ui_wait(2);
			//Assert.assertEquals(commentCheck.getText().trim().contains("All questions approved by the approver"), true);
			ui_wait(2);
			ui_getUIDriver().navigate().refresh();
			
	// ---------------------------------------Manage Pop-up ---------------------------------
			
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(manageFailurePopUp, Pause.MEDIUM));
			ui_click(manageFailurePopUp, "manage Failure PopUp");
			ui_wait(2);
			ui_click(continueWithFailure, "continue With Failure");
			ui_click(continueWithFailedJob, "continue With Failed Job");
			ui_wait(50);	
			
			//-----------------------QA Build & Deploy------------------
			ui_wait(5);
			ui_click(thumbsUp.get(2), "Click on jobDropOne");
			ui_wait(5);
			ui_setvalue(jiraComment, "Dev Lead Comment", "dev lead signoff");
			ui_click(buttonApprove, "Approve button");
			ui_wait(10);
			ui_getUIDriver().navigate().refresh();
			//----------------------------------
			ui_wait(5);
			ui_click(jobdrops.get(4), "Click on jobDropOne");
			ui_wait(5);
			ui_click(jobdrops.get(4), "Click on jobDropOne");
			ui_wait(2);
			//Assert.assertEquals(commentCheck.getText().trim().contains("dev lead sign off received"), true);
			ui_wait(2);
//			Assert.assertEquals(statusCheck.getText().trim().contains("DEV SIGNOFF"), true);
			ui_wait(2);
			ui_getUIDriver().navigate().refresh();	
			
						
			//----------------------------------------
			ui_wait(5);
			ui_click(thumbsUp.get(3), "Click on jobDropOne");
			ui_wait(5);
			ui_setvalue(jiraComment, "QA Lead Comment", "qa lead signoff");
			ui_click(buttonApprove, "Approve button");
			ui_wait(10);
			ui_getUIDriver().navigate().refresh();
			ui_wait(90);
			ui_click(jobdrops.get(6), "Click on jobDropOne");
			ui_wait(5);
			ui_click(jobdrops.get(6), "Click on jobDropOne");
			ui_wait(2);
//			Assert.assertEquals(commentCheck.getText().trim().contains("QA lead sign off received"), true);
//			ui_wait(2);
////			Assert.assertEquals(statusCheck.getText().trim().contains("QA SIGN OFF DONE"), true);
//			ui_wait(2);
			ui_getUIDriver().navigate().refresh();
			
			//---------------------------
			ui_wait(5);
			ui_click(thumbsUp.get(5), "Click on jobDropOne");
			ui_wait(5);
			ui_setvalue(jiraComment, "Release Owner Comment", "Release Owner Comment");
			ui_click(buttonApprove, "Approve button");
			ui_wait(10);
			ui_getUIDriver().navigate().refresh();
			ui_wait(50);
			ui_click(jobdrops.get(10), "Click on jobDropOne");
			ui_wait(5);
			ui_click(jobdrops.get(10), "Click on jobDropOne");
			
			//-----------------------
			ui_wait(10);
			ui_getUIDriver().navigate().refresh();
			ui_wait(10);
			ui_click(jobdrops.get(12), "Click on jobDropOne");
			ui_wait(5);
			ui_click(jobdrops.get(12), "Click on jobDropOne");
			//--------------------------
			ui_wait(10);
			ui_getUIDriver().navigate().refresh();
			ui_wait(10);
			ui_click(jobdrops.get(14), "Click on jobDropOne");
			
		}
		return this;
	}
	@FindBy(xpath = "//div[contains(@class,'status-div ')]")
	WebElement firstJobStatus;
	@FindBy(xpath = "//div[@class='status-chip status-chip-success']")
	WebElement pipelineSuccess;
	public BuildPipeLinePage MobileCI_DEV_RunwithParameter(String appName, String PipelineName) {

		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {

			// --------------------------------------Search for pipeline-------------------
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
			ui_wait(3);
		    ui_click(searchDropDown, "searchDropDown");    
			ui_clearAndSetValue(searchPipelineInput, PipelineName);
			ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
			ui_wait(3);
			ui_click(runwithparameters, "Click on runwithparameters");
			ui_wait(2);
			
			ui_click(allServiceCheckbox, "allService Checkbox");
			ui_click(runpipeline, "Click on runwithparameters button");
			ui_wait(10);
			ui_click(searchPipelinehyperlink, "Click on pipeline hyperlink");
			
			ui_wait(10);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(90);
			Assert.assertEquals(firstJobStatus.getText().trim().equals("SUCCESS"), true);	
			
			// Search Pipeline and Verify Status
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
			ui_wait(3);
		    ui_click(searchDropDown, "searchDropDown");    
			ui_clearAndSetValue(searchPipelineInput, PipelineName);
			ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
			ui_wait(5);
			Assert.assertEquals(ui_IsElementPresent(pipelineSuccess, "3"), true);	
			
			
		}
		return this;
	}
	
	public BuildPipeLinePage MobileCI_PROD_RunwithParameter(String appName, String PipelineName) {

		boolean projectSelection = false;
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for (WebElement element : poc_qaProjectLink) {
			if (element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if (projectSelection) {

			// --------------------------------------Search for pipeline-------------------
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
			ui_wait(3);
		    ui_click(searchDropDown, "searchDropDown");    
			ui_clearAndSetValue(searchPipelineInput, PipelineName);
			ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
			ui_wait(3);
			ui_click(runwithparameters, "Click on runwithparameters");
			ui_wait(2);
			
			ui_click(allServiceCheckbox, "allService Checkbox");
			ui_click(runpipeline, "Click on runwithparameters button");
			ui_wait(10);
			ui_click(searchPipelinehyperlink, "Click on pipeline hyperlink");
			
			ui_wait(10);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(60);
			Assert.assertEquals(firstJobStatus.getText().trim().equals("SUCCESS"), true);	
			ui_wait(60);
						
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(2);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			
			ui_wait(50);
			ui_click(thumbsUp.get(0), "Click on jobDropOne");
			ui_IsElementDisplay(ui_waitForElementToDisplay(inputCommentOne, Pause.MEDIUM));
			ui_clearAndSetValue(inputCommentOne, "Prod Stage done");
			ui_click(buttonApprove, "Click on jobDropOne");
			ui_wait(40);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(2);
			ui_click(jobdrops.get(0), "Click on jobDropOne");
			ui_wait(2);
			//Assert.assertEquals(commentCheck.getText().trim().contains("All questions approved by the approver"), true);
			ui_wait(2);
			
			// Search Pipeline and Verify Status
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
			ui_wait(3);
		    ui_click(searchDropDown, "searchDropDown");    
			ui_clearAndSetValue(searchPipelineInput, PipelineName);
			ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
			ui_wait(5);
			Assert.assertEquals(ui_IsElementPresent(pipelineSuccess, "3"), true);	
			
			
		}
		return this;
	}
	
	
	public BuildPipeLinePage Jira() {
	
		RestAssured.baseURI="https://buildpiper.atlassian.net/rest/api/3";
		RestAssured.given().header("Authorization","Basic ATATT3xFfGF0XPkXmuEgfSuyHrARc1cKnWuLQqKqdHnPZVkO6reQ4ucuo27_ThuCVcuJlEVHsEQ3oHPpbcdwqbWUW9UsQnEN8oxzgALFc5DhpbfsWyYcxATu64ZCqY0p03OE4mShHOVKT-sN59t9zxaTm0HPna1dB6pRqrYztKsRJqPLthR18TM=5BB781F5")
		.when().get("project")
		.then().assertThat().statusCode(200).log().all();
		return this;
	}


}
