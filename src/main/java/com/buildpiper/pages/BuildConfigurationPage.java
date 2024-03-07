package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;

public class BuildConfigurationPage extends BasePage {

		
//	@FindBy(xpath = "//*[text()='Build Details']")
//	WebElement validateBuildDetails;
	
	@FindBy(xpath = "//*[text()='Env Build Details']")
	WebElement validateBuildDetails;

	@FindBy(xpath = "//*[text()='Configure Build']")
	WebElement clickConfigBuild;

	@FindBy(xpath = "//div[@class='md-step-circle current']")
	WebElement currentStepCircle;

	@FindBy(xpath = "//h4[@class='mainHeading']")
	WebElement sourceDetailsHeading;

	@FindBy(xpath = "//*[text()='Please Select the Git URL']")
	WebElement sourceDetailsTitle1;

	@FindBy(xpath = "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiAutocomplete-inputRoot MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-adornedEnd MuiOutlinedInput-adornedEnd']")
	WebElement gitUrlEnterDropdown;

	@FindBy(xpath = "//input[@name='git_url']")
	WebElement gitUrlInputField;

	@FindBy(xpath = "//ul[@id='git_url-popup']//li")
	List<WebElement> autoCompleteURLList;

	@FindBy(xpath = "//*[text()='Load Branches']")
	WebElement loadBranchesBtn;

	@FindBy(xpath = "//select[@name='branch_name']")
	WebElement selectBranchName;

	@FindBy(xpath = "//input[@name='file_paths']")
	WebElement fillFilePath;

	@FindBy(xpath = "//input[@name='file_paths_build_context']")
	WebElement fillDockerFilePath;

	@FindBy(xpath = "//input[@name='image_name']")
	WebElement enterImageName;

	@FindBy(xpath = "//*[text()='Advance Configurations']")
	WebElement sourceDetailsTitle2;

	@FindBy(xpath = "//span[@class='switch-handle']")
	WebElement advanceConfigSwitch;

	@FindBy(xpath = "//*[text()='Default Image is generated for AMD 64 Platform/OS']")
	WebElement sourceDetailsTitle3;

	@FindBy(xpath = "//input[@name='buildkit_enabled']/..//span[@class='switch-handle']")
	WebElement defaultImageSwitch;

	@FindBy(xpath = "//*[text()='Select Platforms']")
	WebElement sourceDetailsTitle4;

	@FindBy(xpath = "//*[text()='Please note we use ']")
	WebElement sourceDetailsTitle5;

	@FindBy(xpath = "//div[@class='input-component']//div[@class='input-chip-checkbox']//label")
	List<WebElement> selectChipType;

	@FindBy(xpath = "//*[text()='linux/amd64']")
	WebElement selectamd64;

	@FindBy(xpath = "//*[text()='linux/arm64']")
	WebElement selectarm64;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement clickContinueBtn;

	@FindBy(xpath = "//h4[@class='mainHeading']")
	WebElement ciDetailsHeading;

	@FindBy(xpath = "//div[@class='d-flex space-between']")
	WebElement ciDetailsSubHeading;

	@FindBy(xpath = "//input[@name='language'][@type='checkbox']")
	List<WebElement> selectLanguage;

	@FindBy(xpath = "//*[text()=' Publish CI Report to Sonar?']")
	WebElement ciDetailsSubHeading2;

	@FindBy(xpath = "//span[@class='switch-label'][@data-on]")
	WebElement sonarSwitch;

	@FindBy(xpath = "//*[text()='Project Key']")
	WebElement ciDetailsSubHeading3;

	@FindBy(xpath = "//input[@name='sonar_project_key']")
	WebElement sonarKeyEnter;

	@FindBy(xpath = "//h4[@class='mainHeading']")
	WebElement envHeading;

	@FindBy(xpath = "//*[text()='Build Env Details']")
	WebElement envSubHeading;

	@FindBy(xpath = "//span[@class='switch-label'][@data-on]")
	WebElement envDetailsSwitch;

	@FindBy(xpath = "//*[text()='Use Field view to manage small number of variables. Use Editor view in case you want to copy paste or manage large number of variables']")
	WebElement envDetailsDefText;

	@FindBy(xpath = "//input[@name='key']")
	WebElement envName;

	@FindBy(xpath = "//input[@name='value']")
	WebElement envValue;

	@FindBy(xpath = "//h4[@class='mainHeading']")
	WebElement hooksHeading;

	@FindBy(xpath = "//*[text()='Do you have build pre hooks?']")
	WebElement hooksSubHeading;

	@FindBy(xpath = "//*[text()='Do you have build post hooks?']")
	WebElement hooksSubHeading2;

	@FindBy(xpath = "//input[@name='pre_hook_enabled']/../span[@class='switch-handle']")
	WebElement preHooksSwitch;

	@FindBy(xpath = "//input[@name='post_hook_enabled']/../span[@class='switch-handle']")
	WebElement postHooksSwitch;

	@FindBy(xpath = "//p[contains(.,'Add Pre Hook')]/../../..//input[@placeholder='Run Command' and string-length(@value)=0]")
	WebElement preHooksPasswordBox;
	
	@FindBy(xpath = "//button[@class='nowrap']")
	WebElement addNewPreHook;
	
	@FindBy(xpath = "(//button[@class='nowrap'])[2]")
	WebElement addNewPostHook;
	

	@FindBy(xpath = "//p[contains(.,'Add Post Hook')]/../../..//input[@placeholder='Run Command' and string-length(@value)=0]")
	WebElement postHooksPasswordBox;

	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement submitHooks;

	@FindBy(xpath = "//div[@class='font-27 font-family-nunito font-weight-300 lh-12 mr-5']")
	WebElement createdServiceName;

	@FindBy(xpath = "//a[@class='text-anchor-blue']")
	WebElement checkHealthStatus;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[1]/div/div[1]/div/div/div[2]/div[2]/span[4]")
	WebElement healthStatus;

	@FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div/div[1]/div/div/div[2]/div[2]/span[2]")
	WebElement projectNameCheck;

	@FindBy(xpath = "//*[text()='Job Template: ']")
	WebElement validateJobTemplate;

	@FindBy(xpath = "//span[contains(@class,'text-grey-6e')][contains(.,'QA')]")
	WebElement validateUserRole;

	@FindBy(xpath = "//*[text()='Repository Details']")
	WebElement repoDetails;

	@FindBy(xpath = "//button[@title='Clear']")
	WebElement clearClickGitDropDown;
	
	@FindBy(xpath = "//button[@title='Clear']")
	WebElement buildDetailsGrid;

	public BuildConfigurationPage() {

	}

	public BuildConfigurationPage CreateAndValidateBuildConfig(String gitURL,String BranchName,String FilePath,String DockerFilePath,ArrayList<String> ChipType,ArrayList<String> LanguageName,String preHookPass,String EnvName) {

		ui_IsElementDisplay(ui_waitForElementToDisplay(validateBuildDetails, Pause.MEDIUM));
		ui_click(validateBuildDetails, "Poc_QA validateBuildDetails");
		ui_IsElementDisplay(ui_waitForElementToDisplay(clickConfigBuild, Pause.MEDIUM));
		ui_click(clickConfigBuild, "Poc_QA clickConfigBuild");

		/////////// Source Details Fill ////////////////////////////
		ui_wait(3);

		ui_IsElementDisplay(ui_waitForElementToDisplay(currentStepCircle, Pause.MEDIUM));
		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsHeading, Pause.MEDIUM));
		String SourceDetailsHeading = sourceDetailsHeading.getText();
		Assert.assertEquals(SourceDetailsHeading, "Source Details", "Unable to validate 'Source Details'");
		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsTitle1, Pause.MEDIUM));
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
		ui_clearAndSetValue(fillFilePath, FilePath);
		ui_clearAndSetValue(fillDockerFilePath, DockerFilePath);
		ui_wait(3);
		//ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsTitle2, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(advanceConfigSwitch, Pause.MEDIUM));
//		ui_click(advanceConfigSwitch, "Poc_QA advanceConfigSwitch");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsTitle3, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(defaultImageSwitch, Pause.MEDIUM));
//		ui_click(defaultImageSwitch, "Poc_QA defaultImageSwitch");
//		ui_wait(20);
//		((JavascriptExecutor) ui_getUIDriver()).executeScript("arguments[0].scrollIntoView();", selectamd64);
//		
//		ui_wait(2);
//        for (int i = 0; i < selectChipType.size(); i++) {
//        	System.out.println(selectChipType.get(i).getText().trim());
//        	if (ChipType.contains(selectChipType.get(i).getText().trim())) {
//        		
//        		
//        		selectChipType.get(i).click();
//        	}
//        }
		ui_click(clickContinueBtn, "Poc_QA clickContinueBtn");
		ui_wait(5);

		/////////// CI Details Fill ////////////////////////////

//		ui_IsElementDisplay(ui_waitForElementToDisplay(ciDetailsHeading, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(ciDetailsSubHeading, Pause.MEDIUM));
//		
//        for (int i = 0; i < selectLanguage.size(); i++) {
//        	System.out.println(selectLanguage.get(i).getAttribute("value").trim());
//        	if (LanguageName.contains(selectLanguage.get(i).getAttribute("value").trim())) {
//        		
//        		
//        		selectLanguage.get(i).click();
//        	}
//        }
//
//		ui_click(clickContinueBtn, "Poc_QA clickContinueBtn");

		/////////// ENV Details Fill ////////////////////////////

		ui_IsElementDisplay(ui_waitForElementToDisplay(envHeading, Pause.MEDIUM));
		ui_IsElementDisplay(ui_waitForElementToDisplay(envSubHeading, Pause.MEDIUM));
		ui_click(envDetailsSwitch, "Poc_QA envDetailsSwitch");
		ui_setvalue(envName, "Poc_QA envName", EnvName);
		ui_setvalue(envValue, "Poc_QA envValue", "UAT-QA");
		ui_click(clickContinueBtn, "Poc_QA clickContinueBtn");

		/////////// HOOKS Details Fill ////////////////////////////
		
		//ui_IsElementDisplay(ui_waitForElementToDisplay(hooksHeading, Pause.MEDIUM));
		//Assert.assertEquals(hooksHeading, "Hooks Details");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(hooksSubHeading, Pause.MEDIUM));
		//Assert.assertEquals(hooksSubHeading, "Hooks Details");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(hooksSubHeading2, Pause.MEDIUM));
		ui_click(preHooksSwitch, "Poc_QA preHooksSwitch");
		ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox1", preHookPass);
		
		ui_click(addNewPreHook, "clicks on add New Hook");
		ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox2", preHookPass);
		
		ui_click(postHooksSwitch, "Poc_QA postHooksSwitch");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(postHooksPasswordBox, Pause.MEDIUM));
		ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox1", preHookPass);

		ui_click(addNewPostHook, "clicks on add New Post Hook");
		ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox2", preHookPass);
		
		ui_click(submitHooks, "Poc_QA submitHooks");

		/////////// Service Status /////////////////////////////////////

		//ui_IsElementDisplay(ui_waitForElementToDisplay(createdServiceName, Pause.MEDIUM));
		//assert CreatedServiceName.equals("automationservice");
		//ui_click(checkHealthStatus, "Poc_QA checkHealthStatus");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(healthStatus, Pause.MEDIUM));
		//String HealthStatus = healthStatus.getText();
		//assert HealthStatus.equals("N/A");
		ui_IsElementDisplay(ui_waitForElementToDisplay(projectNameCheck, Pause.MEDIUM));
		//String ProjectNameCheck = projectNameCheck.getText();
		//assert ProjectNameCheck.equals("poc-qa");
		ui_IsElementDisplay(ui_waitForElementToDisplay(validateJobTemplate, Pause.MEDIUM));
		//String ValidateJobTemplate = validateJobTemplate.getText();
		//assert ValidateJobTemplate.equals("poc-pinelabs-v2");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(validateUserRole, Pause.MEDIUM));
		//String ValidateUserRole = validateUserRole.getText();
		//assert ValidateUserRole.equals("poc-pinelabs-v2");
		
//		ui_click(validateBuildDetails, "Poc_QA validateBuildDetails");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(repoDetails, Pause.MEDIUM));

		return this;
	
	}
	
	@FindBy(xpath = "//div[contains(@class,'round-btn-group')]//*[contains(@class,'text-success')]")
	WebElement ApproveThumbsUpBtn;
	
	@FindBy(xpath = "//button[contains(@class,'btn-round')]//span[contains(@class,'refresh-button')]")
	WebElement refreshBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	WebElement confirmApproval;
	
	@FindBy(xpath = "//span[text()='IN APPROVAL']")
	WebElement inApprovalTxt;
	
	@FindBy(xpath = "//span[text()='IN USE']")
	WebElement inUseTxt;
	
	@FindBy(xpath = "//span[text()='Sending for Approval']")
	WebElement sendingForApprovalTxt;
	
	public BuildConfigurationPage approveConfiguartions() {

		ui_getUIDriver().navigate().refresh();
		ui_IsElementDisplay(ui_waitForElementToDisplay(ApproveThumbsUpBtn, Pause.MEDIUM));
		ui_click(ApproveThumbsUpBtn, "clicks on Approve Thumbs Up button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(confirmApproval, Pause.MEDIUM));
		ui_click(confirmApproval, "clicks on Approve Thumbs Up button");
		ui_wait(20);
		ui_IsElementDisplay(ui_waitForElementToDisplay(sendingForApprovalTxt, Pause.MEDIUM));
		ui_click(refreshBtn, "clicks on refresh button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(inApprovalTxt, Pause.MEDIUM));
//		ui_wait(10);
		ui_click(refreshBtn, "clicks on refresh button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(ApproveThumbsUpBtn, Pause.MEDIUM));
		ui_click(ApproveThumbsUpBtn, "clicks on Approve Thumbs Up button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(confirmApproval, Pause.MEDIUM));
		ui_click(confirmApproval, "clicks on Approve Thumbs Up button");
		ui_wait(20);
		ui_click(refreshBtn, "clicks on refresh button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(inUseTxt, Pause.MEDIUM));



		return this;
	}
	
	@FindBy(xpath = "//span[text()='Sending for approval']")
	WebElement sendingForApprovalTxtDeploy;
	
	public BuildConfigurationPage approveConfiguartionsDeploy() {

		ui_getUIDriver().navigate().refresh();
		ui_wait(5);
		ui_IsElementDisplay(ui_waitForElementToDisplay(ApproveThumbsUpBtn, Pause.MEDIUM));
		ui_click(ApproveThumbsUpBtn, "clicks on Approve Thumbs Up button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(confirmApproval, Pause.MEDIUM));
		ui_click(confirmApproval, "clicks on Approve Thumbs Up button");
		ui_wait(10);
		ui_IsElementDisplay(ui_waitForElementToDisplay(sendingForApprovalTxtDeploy, Pause.MEDIUM));
		ui_click(refreshBtn, "clicks on refresh button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(inApprovalTxt, Pause.MEDIUM));
//		ui_wait(10);
		ui_click(refreshBtn, "clicks on refresh button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(ApproveThumbsUpBtn, Pause.MEDIUM));
		ui_click(ApproveThumbsUpBtn, "clicks on Approve Thumbs Up button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(confirmApproval, Pause.MEDIUM));
		ui_click(confirmApproval, "clicks on Approve Thumbs Up button");
		ui_wait(20);
		ui_click(refreshBtn, "clicks on refresh button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(inUseTxt, Pause.MEDIUM));



		return this;
	}
	
	public BuildConfigurationPage validateInUseText() {

		ui_IsElementDisplay(ui_waitForElementToDisplay(inUseTxt, Pause.MEDIUM));

		return this;
	}
	
	@FindBy(xpath = "//input[@name='buildx_platform_settings']/../span[2]")
	WebElement advanceConfigRadiobutton;
	@FindBy(xpath = "//input[@name='buildkit_enabled']/../span[2]")
	WebElement defaultImageGeneratedRadiobutton;
	@FindBy(xpath = "//input[@id='linux/amd64']/following-sibling::label[1]")
	WebElement linux_amd64_Option;
	@FindBy(xpath = "//input[@id='linux/arm64']/following-sibling::label[1]")
	WebElement linux_arm64_Option;
	
	public BuildConfigurationPage CreateAndValidateBuildConfigMenifest(String gitURL,String BranchName,String FilePath,String DockerFilePath,ArrayList<String> ChipType,ArrayList<String> LanguageName,String preHookPass,String EnvName) {

		ui_IsElementDisplay(ui_waitForElementToDisplay(validateBuildDetails, Pause.MEDIUM));
		ui_click(validateBuildDetails, "Poc_QA validateBuildDetails");
		ui_IsElementDisplay(ui_waitForElementToDisplay(clickConfigBuild, Pause.MEDIUM));
		ui_click(clickConfigBuild, "Poc_QA clickConfigBuild");

		/////////// Source Details Fill ////////////////////////////
		ui_wait(3);

		ui_IsElementDisplay(ui_waitForElementToDisplay(currentStepCircle, Pause.MEDIUM));
		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsHeading, Pause.MEDIUM));
		String SourceDetailsHeading = sourceDetailsHeading.getText();
		Assert.assertEquals(SourceDetailsHeading, "Source Details", "Unable to validate 'Source Details'");
		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsTitle1, Pause.MEDIUM));
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
		ui_clearAndSetValue(fillFilePath, FilePath);
		ui_clearAndSetValue(fillDockerFilePath, DockerFilePath);
		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsTitle2, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(advanceConfigSwitch, Pause.MEDIUM));
//		ui_click(advanceConfigSwitch, "Poc_QA advanceConfigSwitch");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(sourceDetailsTitle3, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(defaultImageSwitch, Pause.MEDIUM));
//		ui_click(defaultImageSwitch, "Poc_QA defaultImageSwitch");
//		ui_wait(20);
//		((JavascriptExecutor) ui_getUIDriver()).executeScript("arguments[0].scrollIntoView();", selectamd64);
//		
//		ui_wait(2);
//        for (int i = 0; i < selectChipType.size(); i++) {
//        	System.out.println(selectChipType.get(i).getText().trim());
//        	if (ChipType.contains(selectChipType.get(i).getText().trim())) {
//        		
//        		
//        		selectChipType.get(i).click();
//        	}
//        }		
		ui_wait(2);
		ui_IsElementDisplay(ui_waitForElementToDisplay(advanceConfigRadiobutton, Pause.MEDIUM));
		ui_click(advanceConfigRadiobutton, "Poc_QA advanceConfig Radiobutton");
		ui_wait(2);
		ui_click(defaultImageGeneratedRadiobutton, "Poc_QA defaultImageGenerated Radiobutton");
		ui_wait(2);
		ui_click(linux_amd64_Option, "Poc_QA linux_amd64 Option");
		ui_wait(1);
		ui_click(linux_arm64_Option, "Poc_QA linux_arm64 Option");
		ui_wait(2);
		ui_click(clickContinueBtn, "Poc_QA clickContinueBtn");
		ui_wait(5);

		/////////// CI Details Fill ////////////////////////////

//		ui_IsElementDisplay(ui_waitForElementToDisplay(ciDetailsHeading, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(ciDetailsSubHeading, Pause.MEDIUM));
//		
//        for (int i = 0; i < selectLanguage.size(); i++) {
//        	System.out.println(selectLanguage.get(i).getAttribute("value").trim());
//        	if (LanguageName.contains(selectLanguage.get(i).getAttribute("value").trim())) {
//        		
//        		
//        		selectLanguage.get(i).click();
//        	}
//        }
//
//		ui_click(clickContinueBtn, "Poc_QA clickContinueBtn");

		/////////// ENV Details Fill ////////////////////////////

		ui_IsElementDisplay(ui_waitForElementToDisplay(envHeading, Pause.MEDIUM));
		ui_IsElementDisplay(ui_waitForElementToDisplay(envSubHeading, Pause.MEDIUM));
		ui_click(envDetailsSwitch, "Poc_QA envDetailsSwitch");
		ui_setvalue(envName, "Poc_QA envName", EnvName);
		ui_setvalue(envValue, "Poc_QA envValue", "UAT-QA");
		ui_click(clickContinueBtn, "Poc_QA clickContinueBtn");

		/////////// HOOKS Details Fill ////////////////////////////
		
		//ui_IsElementDisplay(ui_waitForElementToDisplay(hooksHeading, Pause.MEDIUM));
		//Assert.assertEquals(hooksHeading, "Hooks Details");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(hooksSubHeading, Pause.MEDIUM));
		//Assert.assertEquals(hooksSubHeading, "Hooks Details");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(hooksSubHeading2, Pause.MEDIUM));
		ui_click(preHooksSwitch, "Poc_QA preHooksSwitch");
		ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox1", preHookPass);
		
		ui_click(addNewPreHook, "clicks on add New Hook");
		ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox2", preHookPass);
		
		ui_click(postHooksSwitch, "Poc_QA postHooksSwitch");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(postHooksPasswordBox, Pause.MEDIUM));
		ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox1", preHookPass);

		ui_click(addNewPostHook, "clicks on add New Post Hook");
		ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox2", preHookPass);
		
		ui_click(submitHooks, "Poc_QA submitHooks");

		/////////// Service Status /////////////////////////////////////

		//ui_IsElementDisplay(ui_waitForElementToDisplay(createdServiceName, Pause.MEDIUM));
		//assert CreatedServiceName.equals("automationservice");
		//ui_click(checkHealthStatus, "Poc_QA checkHealthStatus");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(healthStatus, Pause.MEDIUM));
		//String HealthStatus = healthStatus.getText();
		//assert HealthStatus.equals("N/A");
		ui_IsElementDisplay(ui_waitForElementToDisplay(projectNameCheck, Pause.MEDIUM));
		//String ProjectNameCheck = projectNameCheck.getText();
		//assert ProjectNameCheck.equals("poc-qa");
		ui_IsElementDisplay(ui_waitForElementToDisplay(validateJobTemplate, Pause.MEDIUM));
		//String ValidateJobTemplate = validateJobTemplate.getText();
		//assert ValidateJobTemplate.equals("poc-pinelabs-v2");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(validateUserRole, Pause.MEDIUM));
		//String ValidateUserRole = validateUserRole.getText();
		//assert ValidateUserRole.equals("poc-pinelabs-v2");
		
//		ui_click(validateBuildDetails, "Poc_QA validateBuildDetails");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(repoDetails, Pause.MEDIUM));

		return this;
	
	}

}
