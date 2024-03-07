package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.RandomStrings;
import com.buildpiper.utils.TimeUtililty;

public class EnvironmentCreationPage extends BasePage {

	public ArrayList<String> configNames = new ArrayList<>();
	public ArrayList<String> secretNames = new ArrayList<>();

	SoftAssert softAssert = new SoftAssert();

	String EnvironmentName = "dev-" + RandomStrings.generateRandomString(6);
	String SchedulerName = "AutoScheduler-" + RandomStrings.generateRandomString(3);

	//@FindBy(xpath = "//li//button[contains(@class,'main-nav-1')]//span[@title]")
	@FindBy(xpath = "//li//button[contains(@class,'app-cluster-button')]//span[@title]")	
	List<WebElement> poc_qaProjectLink;

	//@FindBy(xpath = "//button//span[2][@class='flaticon-expand-arrow']/../..//div//span[@title='Environment Overview']")
	@FindBy(xpath = "//button[@class='app-cluster-button selected-border selected-shadow']/..//div//a[contains(.,'Environment Overview')]")
	WebElement environmentOverview;

	@FindBy(xpath = "//a[text()='New Environment']")
	WebElement addNewEnvironment;

	@FindBy(xpath = "//span//input[@name='environment_master_id'][@type='checkbox']")
	List<WebElement> environmentTypeCheckbox;
	// WebElement environmentTypeCheckboxdev;

	@FindBy(xpath = "//input[@placeholder='Environment Name']")
	WebElement environmentName;

	@FindBy(xpath = "//input[@name='manual_build']")
	WebElement manualBuildYes;

	@FindBy(xpath = "//input[@name='manual_deploy']")
	WebElement manualDeployYes;

	@FindBy(xpath = "//select[@name='cluster_id']")
	WebElement selectCluster;
	@FindBy(xpath = "//button[text()='Okay']")
	WebElement confirmPopup;
	

	@FindBy(xpath = "//select[@name='namespace_id']")
	WebElement selectNameSpace;

	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement continueBtn;

	@FindBy(xpath = "//div[@class='image-upload-chip']")
	WebElement uploadedFile;

	@FindBy(xpath = "//select[@name='registry_id']")
	WebElement selectRegistry;

	@FindBy(xpath = "//input[@name='name' and @placeholder='Name']")
	WebElement envSearchByName;

	@FindBy(xpath = "//button[@aria-label='Search'][@type='button']")
	WebElement searchButton;

	@FindBy(xpath = "//div[contains(@class,'card-body')]//a[contains(@href,'dashboard')]//p")
	WebElement selectedEnvHyperlink;

	@FindBy(xpath = "//span[contains(@class,'span-class')]//div[contains(@class,'ml')]")
	List<WebElement> envBannerCheckList;

	@FindBy(xpath = "//ul[@class='headerul']//li[text()='Associated Services']")
	WebElement envConfigOptionsAssociateServices;

	@FindBy(xpath = "//ul[@class='headerul appheaderul']//li[text()='Config Maps']")
	WebElement envConfigOptionsConfigMaps;
	
	@FindBy(xpath = "//div[@class='pd-10']//span")
	List<WebElement> associatedServicesBannerText;
	
	@FindBy(xpath = "//label[text()='(Switch to Bp managed config maps)']")
	WebElement switchtoBPmanagedConfigMaps;
	
	@FindBy(xpath = "//a[contains(@class,'btn btn-submit')][text()='Add ConfigMaps']")
	WebElement addConfigMaps_BPmanagedConfig;
	
	@FindBy(xpath = "//select[@name='properties_files']")
	WebElement configMapStrategyDropdown;
	
	@FindBy(xpath = "(//input[@name='upload_type']/..)[2]")
	WebElement uploadCustomManifest_BPmanagedConfig;
	@FindBy(xpath = "//input[@value='MANIFEST_GIT']/..")
	WebElement uploadCustomManifest_GitURL;
	
	@FindBy(xpath = "//input[@value='MANIFEST_UPLOAD']/..")
	WebElement manifestUpload_Radiobutton;
	
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
	@FindBy(xpath = "//input[@name='file_path']")
	WebElement filePath;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveEnvironment;
	@FindBy(xpath = "//button[contains(text(),'Save & Deploy')]")
	WebElement saveandDeployEnvironment;
	@FindBy(xpath = "//div[text()='Namespace Config_map deployed successfully!']")
	WebElement configmapDeploySuccessfully;
	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueDeploy;
	

	

	public EnvironmentCreationPage() {

	}

	///// negative test case tc0001 ////////////
	////////// tC0002 ///////////

	public EnvironmentCreationPage CreateAndValidateEnvironment(String appName, ArrayList<String> EnvironmentType,
			String BranchName, String NameSpace, String RegistryName) {

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
			ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			ui_wait(3);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			// ui_clearAndSetValue(environmentName, EnvironmentName);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(environmentTypeCheckboxdev, Pause.LOW));
//		ui_click(environmentTypeCheckboxdev, "clicks on dev");
			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			ui_click(confirmPopup, "Click on Okay Button");
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);			
			dropdown1.selectByVisibleText(NameSpace);
			ui_click(confirmPopup, "Click on Okay Button");			
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			ui_click(confirmPopup, "Click on Okay Button");
			ui_wait(10);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			//ui_ActionMoveAndClick(continueBtn, "user clicks save button");
			//ui_ActionClick(continueBtn, "user clicks save button");
			ui_click(continueBtn, "user clicks save button");
			ui_wait(15);
		
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			
			//ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			/*if (selectedEnvHyperlink.getText().equals(EnvironmentName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}*/
			ui_wait(5);
//		softAssert.assertEquals(envBannerCheckList.get(0).getText().trim(), BranchName);
//		softAssert.assertEquals(envBannerCheckList.get(1).getText().trim(), EnvironmentName);
//		softAssert.assertEquals(envBannerCheckList.get(2).getText().trim(), NameSpace);
//		softAssert.assertEquals(envBannerCheckList.get(3).getText().trim(), "none");
//		softAssert.assertEquals(envBannerCheckList.get(4).getText().trim(), "none");
//		ui_wait(5);
//		ui_clearAndSetValue(envSearchByName, EnvironmentName);
//	    ui_wait(4);
//	    ui_click(searchButton, "clicks on search icon next to env name entered");
//	    ui_wait(2);
//        if(selectedEnvHyperlink.getText().equals(EnvironmentName)){
//        	ui_click(selectedEnvHyperlink, "click on the selected environment");
//        }
//        ui_wait(10);
			ui_click(envConfigOptionsAssociateServices, "clicks on associate services option");
			boolean bannerTxtStatus = true;
			for (WebElement associatedServicesBanner : associatedServicesBannerText) {
				String serviceText = associatedServicesBanner.getText();
				System.out.println(serviceText);
				if (!(serviceText.contains("Associated Services")
						|| serviceText.contains("Displaying list of the services associated with this Environment"))) {
					bannerTxtStatus = false;
					break;
				}
			}
			Assert.assertTrue(bannerTxtStatus, "Unable to  validate the Associated Services Banner Text");
//		ui_click(envConfigOptionsConfigMaps, "clicks on config maps option");
			ui_getUIDriver().quit();

			// click on Choose button--//div[@class='image-upload-wrap']/input
			// addNewEnvironment.sendKeys(Keys.ENTER);
		}
		return this;
	}

	// String ConfigName = "AutoConfig-"+RandomStrings.generateRandomString(9);

	String configFilePath = System.getProperty("user.dir")+ "\\src\\test\\resources\\testfiles\\upload\\UploadConfig\\CMconfig.txt";

	@FindBy(xpath = "//div//input[@name='name' and @placeholder='dev']")
	WebElement configName;

	@FindBy(xpath = "//button[contains(@class,'btn btn-with-icon')][text()='Add ConfigMap']")
	WebElement addConfig;

	@FindBy(xpath = "//div[contains(@class,'MuiFormGroup-row')][@role='radiogroup']//span[contains(@class,'MuiFormControlLabel')]")
	List<WebElement> setConfigFromRadioTypes;

	@FindBy(xpath = "//select[@name='properties_files']")
	WebElement selectPropertiesFilesDropdown;

	@FindBy(xpath = "//div[@class='image-upload-wrap']/input")
	WebElement chooseFileButton;

	@FindBy(xpath = "//button[@class='transparent-btn nowrap'][text()=' Add Row']")
	WebElement configAddRowButton;

	@FindBy(xpath = "//div[text()='Namespace Config_map deployed successfully!']")
	WebElement confifgAddConfirmationMessage;

	@FindBy(xpath = "//div[contains(@class,'msg-div')]//span[@class='color-success']")
	List<WebElement> successMessageTextContainer;
	
	@FindBy(xpath = "//div[contains(@class,'card-body')]//a[contains(@href,'dashboard')]//p")
	List<WebElement> envHyperlinkList;
		
	@FindBy(xpath = "//div[contains(@class,'blank-div-text')]")
	WebElement environmentNotFoundMessage;

	@FindBy(xpath = "//span[text()='generating manifest for namespace configmap']")
	WebElement successTxt1;

	@FindBy(xpath = "//span[text()='loading the kube config for namespace configmap']")
	WebElement successTxt2;

	@FindBy(xpath = "//span[text()='deploying namespace configmap']")
	WebElement successTxt3;
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-dismissible')]/p[text()='Manual Build Not Allowed']")
	WebElement manualBuildnotAvailable;

	@FindBy(xpath = "//div[contains(@class,'alert alert-dismissible')]/p[text()='Manual Deploy Not Allowed']")
	WebElement manualDeploynotAvailable;
	
	@FindBy(xpath = "//button[@aria-label='close'][@type='button']")
	WebElement closeButton;
	
	public EnvironmentCreationPage SeacrhEnvironment(String appName,String Environment) throws Exception {
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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_wait(3);
			ArrayList<String> environmentOverviewList_Initial = new ArrayList<String>();
			for(int i=0;i<envHyperlinkList.size();i++) {
				environmentOverviewList_Initial.add(envHyperlinkList.get(i).getText());
				//System.out.println(envHyperlinkList.get(i).getText());
			}
			ui_clearAndSetValue(envSearchByName, Environment);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(8);
			//Boolean textResult=false;
			for(int i=0;i<envHyperlinkList.size();i++) {
		    Assert.assertTrue(envHyperlinkList.get(i).getText().contains(Environment));
		    System.out.println("--"+envHyperlinkList.get(i).getText());
			}
			ui_click(closeButton, "click on Close search Environment");
			ui_wait(3);
			//store environment list after Click on Close Search environment List
			ArrayList<String> environmentOverviewList_AfterClosebuttonClick = new ArrayList<String>();
			for(int i=0;i<envHyperlinkList.size();i++) {
				System.out.println(envHyperlinkList.get(i).getText());
				environmentOverviewList_AfterClosebuttonClick.add(envHyperlinkList.get(i).getText());
			}
			ui_wait(3);
			//Compare Initial Environment List and After Close button click
			for(int i=0;i<environmentOverviewList_Initial.size();i++) {
				Assert.assertTrue(environmentOverviewList_Initial.get(i).equals(environmentOverviewList_AfterClosebuttonClick.get(i)), "Environment List not maching");
			}
			 							
		}
		return this;
	}
	
//RK
	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root btn btn-with-icon btn-round'])[2]")
	WebElement environmentDeleteIcon;
	
	@FindBy(xpath = "//input[@name='remarks' and @placeholder='Please enter the reason to delete']")
	WebElement environmentDeleteReason;
	
	@FindBy(xpath = "//div[@class='dialogue-heading']")
	WebElement errorMessagePopupforDeleteEnvironnment;

	
	public EnvironmentCreationPage DeleteEmptyEnvironment(String appName, ArrayList<String> EnvironmentType,
			String BranchName, String NameSpace, String RegistryName) {
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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			ui_click(confirmPopup, "confirmPopup");
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);
			dropdown1.selectByVisibleText(NameSpace);
			ui_click(confirmPopup, "confirmPopup");
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			ui_click(confirmPopup, "confirmPopup");
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			ui_click(continueBtn, "user clicks save button");
			ui_wait(5);
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(3);
			if (selectedEnvHyperlink.getText().equals(EnvironmentName)) {
				ui_click(environmentDeleteIcon, "click on the delete Icon");
			}
			ui_wait(2);
			ui_clearAndSetValue(environmentDeleteReason, "Automated reason to delete Environment");
			ui_click(deleteButton, "clicks on delete button after adding Environment");
			ui_wait(5);
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(3);
			Assert.assertTrue(environmentNotFoundMessage.getText().contains("No Env found with the name"));
			ui_getUIDriver().quit();
		}
	return this;
		
}

	public EnvironmentCreationPage DeleteNonEmptyEnvironment(String appName, ArrayList<String> EnvironmentType,
			String BranchName, String NameSpace, String RegistryName) {
		String ServiceName="test-";
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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			ui_click(confirmPopup, "confirmPopup");
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);
			dropdown1.selectByVisibleText(NameSpace);
			ui_click(confirmPopup, "confirmPopup");
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			ui_click(confirmPopup, "confirmPopup");
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			ui_click(continueBtn, "user clicks save button");
			ui_wait(5);
			
			//Search Service and Add Environment
			ui_click(new ServiceCreationPage().serviceOverViewTab, "Poc_QA serviceOverviewLink");
			ui_wait(5);
			ui_clearAndSetValue(envSearchByName, ServiceName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(5);
			ui_click(new ServiceCreationPage().serviceList.get(0), "ServiceList");
			ui_wait(2);
			ui_click(new ServiceCreationPage().addNewEnvToRunningService, "adds new environment to running service");
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(new ServiceCreationPage().envDropdown, Pause.LOW));
			Select selectEnvironmentdropdown = new Select(new ServiceCreationPage().envDropdown);
			selectEnvironmentdropdown.selectByVisibleText(EnvironmentName);
			ui_wait(5);
            ui_click(new ServiceCreationPage().submitHooks, "Service Save & Continue");
            ui_wait(5);
			//Select again Environment and Check Service is added and Delete that Environment
        	ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
    		for (WebElement element : poc_qaProjectLink) {
    			if (element.getText().trim().equalsIgnoreCase(appName)) {
    				element.click();
    				projectSelection = true;
    				break;
    			}
    		}
            ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
            ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(3);
			if (selectedEnvHyperlink.getText().equals(EnvironmentName)) {
				ui_click(environmentDeleteIcon, "click on the delete Icon");
			}
			ui_wait(3);
			Assert.assertTrue(errorMessagePopupforDeleteEnvironnment.getText().contains("You can not perform delete"));
			ui_getUIDriver().quit();
		}
	return this;
		
}
	//RK
	@FindBy(xpath = "//div[text()='Environment Master Manual Build Deploy']")
	WebElement EnvironmentMasterManualBuildDeployVersioning;
	
	@FindBy(xpath = "//select[@name='DEV_ENVIRONMENT_MANUAL_DEPLOY']")
	WebElement DEV_EnvironmentMasterManualDeploy_DropDown;
	
	@FindBy(xpath = "//select[@name='DEV_ENVIRONMENT_MANUAL_BUILD']")
	WebElement DEV_EnvironmentMasterManualBuild_DropDown;
	
	@FindBy(xpath = "//select[@name='QA_ENVIRONMENT_MANUAL_DEPLOY']")
	WebElement QA_EnvironmentMasterManualDeploy_DropDown;
	
	@FindBy(xpath = "//select[@name='QA_ENVIRONMENT_MANUAL_BUILD']")
	WebElement QA_EnvironmentMasterManualBuild_DropDown;
	
	@FindBy(xpath = "//select[@name='UAT_ENVIRONMENT_MANUAL_DEPLOY']")
	WebElement UAT_EnvironmentMasterManualDeploy_DropDown;
	
	@FindBy(xpath = "//select[@name='UAT_ENVIRONMENT_MANUAL_BUILD']")
	WebElement UAT_EnvironmentMasterManualBuild_DropDown;
	
	@FindBy(xpath = "//select[@name='STAGING_ENVIRONMENT_MANUAL_DEPLOY']")
	WebElement STAGING_EnvironmentMasterManualDeploy_DropDown;
	
	@FindBy(xpath = "//select[@name='STAGING_ENVIRONMENT_MANUAL_BUILD']")
	WebElement STAGING_EnvironmentMasterManualBuild_DropDown;
	
	@FindBy(xpath = "//select[@name='PROD_ENVIRONMENT_MANUAL_DEPLOY']")
	WebElement PROD_EnvironmentMasterManualDeploy_DropDown;
	
	@FindBy(xpath = "//select[@name='PROD_ENVIRONMENT_MANUAL_BUILD']")
	WebElement PROD_EnvironmentMasterManualBuild_DropDown;
	
	@FindBy(xpath = "//button[text()='Apply immediately']")
	WebElement ApplySettingImmediately;
	
	public EnvironmentCreationPage ChangeVersioning(String Build_or_Deploy,String EnvironmentType,String Enable_or_Dissable) {
		new PreRequisitesPage().systemSettings();
		ui_click(EnvironmentMasterManualBuildDeployVersioning, "clicks on EnvironmentMasterManualBuildDeploy Versioning Button");
		ui_wait(5);
		if(Build_or_Deploy.equals("Deploy")) {
			if(EnvironmentType.equals("DEV")) {
				if(Enable_or_Dissable.equals("true")) {
					Select dropdown = new Select(DEV_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("true");
				}
				else {
					Select dropdown = new Select(DEV_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("false");
				}
			}
			if(EnvironmentType.equals("QA")) {
				if(Enable_or_Dissable.equals("true")) {
					Select dropdown = new Select(QA_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("true");
				}
				else {
					Select dropdown = new Select(QA_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("false");
				}
			}
			if(EnvironmentType.equals("UAT")) {
				if(Enable_or_Dissable.equals("true")) {
					Select dropdown = new Select(UAT_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("true");
				}
				else {
					Select dropdown = new Select(UAT_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("false");
				}
			}
			if(EnvironmentType.equals("STAGING")) {
				if(Enable_or_Dissable.equals("true")) {
					Select dropdown = new Select(STAGING_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("true");
				}
				else {
					Select dropdown = new Select(STAGING_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("false");
				}
			}
			if(EnvironmentType.equals("PROD")) {
				if(Enable_or_Dissable.equals("true")) {
					Select dropdown = new Select(PROD_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("true");
				}
				else {
					Select dropdown = new Select(PROD_EnvironmentMasterManualDeploy_DropDown);
					dropdown.selectByVisibleText("false");
				}
			}
			if(Build_or_Deploy.equals("Build")) {
				if(EnvironmentType.equals("DEV")) {
					if(Enable_or_Dissable.equals("true")) {
						Select dropdown = new Select(DEV_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("true");
					}
					else {
						Select dropdown = new Select(DEV_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("false");
					}
				}
				if(EnvironmentType.equals("QA")) {
					if(Enable_or_Dissable.equals("true")) {
						Select dropdown = new Select(QA_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("true");
					}
					else {
						Select dropdown = new Select(QA_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("false");
					}
				}
				if(EnvironmentType.equals("UAT")) {
					if(Enable_or_Dissable.equals("true")) {
						Select dropdown = new Select(UAT_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("true");
					}
					else {
						Select dropdown = new Select(UAT_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("false");
					}
				}
				if(EnvironmentType.equals("PROD")) {
					if(Enable_or_Dissable.equals("true")) {
						ui_wait(3);
						Select dropdown = new Select(PROD_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("true");
					}
					else {
						ui_wait(3);
						Select dropdown = new Select(PROD_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("false");
					}
				}
				if(EnvironmentType.equals("STAGING")) {
					if(Enable_or_Dissable.equals("true")) {
						Select dropdown = new Select(STAGING_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("true");
					}
					else {
						Select dropdown = new Select(STAGING_EnvironmentMasterManualBuild_DropDown);
						dropdown.selectByVisibleText("false");
					}
				}
				

			}
		}
		ui_wait(3);
		ui_click(ApplySettingImmediately, "ApplySettingImmediately Button Click");
		
	return this;
		
}
	public EnvironmentCreationPage AddEnvironemntsWithVersioning(String appName, String EnvironmentType,
			String BranchName, String NameSpace, String RegistryName,String VersioningEnable_Dissable) {
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
			if(VersioningEnable_Dissable.equals("true")) {				
			
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.toLowerCase().contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			ui_wait(4);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);
			dropdown1.selectByVisibleText(NameSpace);
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			ui_click(continueBtn, "user clicks save button");
			ui_wait(10);
			}
			else {
				ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
				ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
				ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
				ui_click(addNewEnvironment, "clicks on add new environment button");
				ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
				ui_setvalue(environmentName, "", EnvironmentName);
				// environmentTypeCheckbox.get(0).click();
				for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
					if (EnvironmentType.toLowerCase().contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
						environmentTypeCheckbox.get(i).click();
					}
				}
				ui_wait(2);
				Assert.assertEquals(ui_IsElementDisplay(manualBuildnotAvailable), true);
				Assert.assertEquals(ui_IsElementDisplay(manualDeploynotAvailable), true);
				// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
				Select dropdown = new Select(selectCluster);
				dropdown.selectByVisibleText(BranchName);
				ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
				Select dropdown1 = new Select(selectNameSpace);
				dropdown1.selectByVisibleText(NameSpace);
				ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
				Select dropdown2 = new Select(selectRegistry);
				dropdown2.selectByVisibleText(RegistryName);
				ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
				ui_click(continueBtn, "user clicks save button");
				ui_wait(10);
			
			}
		}
	return this;
		
}

	
	

	public EnvironmentCreationPage EnvironmentConfigurationAndAssociateServices(String appName,
			ArrayList<String> configInputType, String PropertiesFileType1, String envName) {

		String ConfigName = "AutoConfig-" + RandomStrings.generateRandomString(9);
		configNames.add(ConfigName); // Store ConfigName in the configNames ArrayList

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envConfigOptionsConfigMaps, "clicks on config maps option");
			ui_click(addConfig, "adds config");
			ui_setvalue(configName, "Sets Unique Config Name", ConfigName);
			for (int i = 0; i < setConfigFromRadioTypes.size(); i++) {
				if (configInputType.contains(setConfigFromRadioTypes.get(i).getText().trim())) {
					setConfigFromRadioTypes.get(i).click();
				}
			}
			Select dropdown = new Select(selectPropertiesFilesDropdown);
			dropdown.selectByVisibleText(PropertiesFileType1);
//		ui_wait(5);
			ui_click(chooseFileButton, "clicks on upload file button");
			ui_wait(5);
			ui_FileUpload(Configuration.get("browser"), configFilePath);
			ui_wait(5);
			ui_getUIDriver().switchTo().defaultContent();
//		ui_click(configAddRowButton, "Adds new Row for config Properties section");

			ui_waitForElementToDisplay(uploadedFile, Pause.MEDIUM);
			ui_wait(5);
			// JavascriptExecutor js = (JavascriptExecutor)ui_getUIDriver();
			// js.executeScript("arguments[0].click();", continueBtn);
			ui_ActionMoveAndClick(continueBtn, "saves the key/value configmaps");
			// ui_click(continueBtn, "saves the key/value configmaps");
			ui_IsElementDisplay(ui_waitForElementToDisplay(confifgAddConfirmationMessage, Pause.LOW));
			boolean successMsgs = true;
			for (WebElement successMessageContainer : successMessageTextContainer) {
				String successText = successMessageContainer.getText();
				System.out.println(successText);
				if (!(successText.contains("Success") || successText.contains("Success")
						|| successText.contains("Success"))) {
					successMsgs = false;
					break;
				}
			}
			Assert.assertTrue(successMsgs,
					"Unable to  validate Success Messages after adding key/value pair configmaps");
			ui_IsElementDisplay(ui_waitForElementToDisplay(successTxt1, Pause.LOW));
			ui_IsElementDisplay(ui_waitForElementToDisplay(successTxt2, Pause.LOW));
			ui_IsElementDisplay(ui_waitForElementToDisplay(successTxt2, Pause.LOW));
			ui_click(continueBtn, "continue to homepage");
		}
		return this;

	}

	@FindBy(xpath = "//ul[@class='headerul appheaderul']//li[text()='Secrets']")
	WebElement envSecretsOptionConfigMaps;

	@FindBy(xpath = "//button[contains(@class,'btn btn-with-icon')][text()='Add Secret']")
	WebElement addSecret;

	@FindBy(xpath = "//div[contains(@class,'MuiFormGroup-row')][@role='radiogroup']//input[@name='upload_type']")
	List<WebElement> setSecretKeyFromRadioTypes;

	@FindBy(xpath = "//input[@name='key' and @placeholder='dev']")
	WebElement inputKey;

	@FindBy(xpath = "//div[contains(@class,'MuiFormGroup-row')][@role='radiogroup']//input[@name='properties_files']")
	List<WebElement> setSecretFromRadioTypes;

	@FindBy(xpath = "//input[@type='password' and @placeholder='Add value']")
	WebElement inputSecret;

	public EnvironmentCreationPage EnvironmentSecrets(String appName, String envName, ArrayList<String> secretInputType,
			ArrayList<String> secretValueInputType) {

		String secretName = "AutoSecret-" + RandomStrings.generateRandomString(9);
		secretNames.add(secretName); // Store ConfigName in the configNames ArrayList
		String Key = "Key-" + RandomStrings.generateRandomString(9);
		String Secret = "Secret-" + RandomStrings.generateRandomString(20);

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envSecretsOptionConfigMaps, "clicks on secrets option");
			ui_click(addSecret, "adds secrets");
			ui_setvalue(configName, "Sets Unique secret Name", secretName);
			for (int i = 0; i < setSecretKeyFromRadioTypes.size(); i++) {
				if (secretInputType.contains(setSecretKeyFromRadioTypes.get(i).getAttribute("value").trim())) {
					setSecretKeyFromRadioTypes.get(i).click();
				}
			}
			ui_setvalue(inputKey, "adds key value", Key);
			for (int i = 0; i < setSecretFromRadioTypes.size(); i++) {
				if (secretValueInputType.contains(setSecretFromRadioTypes.get(i).getAttribute("value").trim())) {
					setSecretFromRadioTypes.get(i).click();
				}
			}
			ui_setvalue(inputSecret, "adds secret value", Secret);
			ui_click(continueBtn, "saves the key/value secrets");

//		ui_IsElementDisplay(ui_waitForElementToDisplay(confifgAddConfirmationMessage, Pause.LOW));
//		boolean successMsgs= true;
//		for(WebElement successMessageContainer : successMessageTextContainer)
//		{
//			String successText = successMessageContainer.getText();
//			System.out.println(successText);
//			if(!(successText.contains("Success") || 
//					successText.contains("Success")
//				|| successText.contains("Success")))
//			{
//				successMsgs =false;
//				break;
//			}
//		}
//		Assert.assertTrue(successMsgs,"Unable to  validate Success Messages after adding key/value pair configmaps");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(successTxt1, Pause.LOW));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(successTxt2, Pause.LOW));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(successTxt2, Pause.LOW));
//		ui_click(continueBtn, "continue to homepage");

		}

		return this;
	}

	@FindBy(xpath = "//ul[@class='headerul appheaderul']//li[text()='Downtime Scheduler']")
	WebElement envDowntimeOptionConfigMaps;

	@FindBy(xpath = "//a[@class='btn btn-submit'][text()='Add Scheduler']")
	WebElement addScheduler;

	@FindBy(xpath = "//input[@name='name' and @placeholder='cron-job-4']")
	WebElement schedulerInputName;

	@FindBy(xpath = "//button//label[text()='Scale Up']")
	WebElement scaleUpButton;

	@FindBy(xpath = "//button//label[text()='Scale Down']")
	WebElement scaleDownButton;

	@FindBy(xpath = "//input[@name='hours_hrs' and @placeholder='HH']")
	WebElement HHTime;

	@FindBy(xpath = "//input[@name='hours_mins' and @placeholder='MM']")
	WebElement MMTime;

	@FindBy(xpath = "//select[@name='timezone' and @class='select']")
	WebElement timeZoneDropDown;

	@FindBy(xpath = "//button[@class='btn btn-submit'][text()='Add to Schedule']")
	WebElement addToScheduleBtn;

	@FindBy(xpath = "//span[@class='MuiIconButton-label']//input[@type='checkbox']")
	List<WebElement> userCheckBox;

	@FindBy(xpath = "//button[@class='btn btn-submit'][text()='Save Scheduler']")
	WebElement saveScheduleBtn;
	
	@FindBy(xpath = "//span[@class='switch-label']/../span[2]")
	WebElement scaledown_ActiveInactive;

	public EnvironmentCreationPage EnvironmentDownTimeSchedulerScaleUp(String appName, String envName, String timeZone,
			ArrayList<String> user) {

		String SchedulerName = "AutoScheduler-" + RandomStrings.generateRandomString(3);
//	    secretNames.add(secretName); // Store ConfigName in the configNames ArrayList
//	    String Key = "Key-" + RandomStrings.generateRandomString(9);
//	    String Secret = "Secret-" + RandomStrings.generateRandomString(20);

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			ui_click(addScheduler, "clicks on add scheduler button");
			ui_clearAndSetValue(schedulerInputName, SchedulerName);
			String HourTime = TimeUtililty.currentDateAndTime("HH");
			System.out.println(HourTime);
			ui_clearAndSetValue(HHTime, HourTime);
			String MinuteTime = TimeUtililty.addedMinutesTimes("mm", 1);
			System.out.println(MinuteTime);
			ui_clearAndSetValue(MMTime, MinuteTime);
			Select dropdown = new Select(timeZoneDropDown);
			dropdown.selectByVisibleText(timeZone);
			ui_click(addToScheduleBtn, "add to Scheduler Btn");
			for (int i = 0; i < userCheckBox.size(); i++) {
				if (user.contains(userCheckBox.get(i).getAttribute("value").trim())) {
					userCheckBox.get(i).click();
				}
			}
			ui_click(saveScheduleBtn, "clicks on save schedule button");
			ui_wait(2);
			ui_click(scaledown_ActiveInactive, "clicks on scaledown_Active Inactive button");
			ui_wait(70);
			ui_getUIDriver().navigate().refresh();
			ui_wait(2);
			ui_getUIDriver().navigate().refresh();
			ui_wait(2);
			ui_click(envAssociatedServicesOptionConfigMaps, "clicks on Associated Services option");
//		Assert.assertEquals(heathStatus, MinuteTime);
			ui_wait(20);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			
			//Verify Pipeline Created
			ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
			for (WebElement element : poc_qaProjectLink) {
				if (element.getText().trim().equalsIgnoreCase(appName)) {
					element.click();
					projectSelection = true;
					break;
				}
			}
			ui_wait(3);
			String Pipelinename=envName+"_"+SchedulerName+"_up";
			VerifyScaleDownPipeline(Pipelinename,"FAILED");
			ui_wait(3);
			
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			ui_wait(2);
			
            // Delete Created Scheduler
			ui_wait(2);
			ui_click(deleteScheduler, "delete scheduler");
			ui_clearAndSetValue(reasonToDelete, "Automated reason to delete schedule");
			ui_click(deleteButton, "clicks on delete button after adding reason to delete scheduler");

		}
		return this;
	}

	@FindBy(xpath = "//button//div//span[@class='flaticon-expand-arrow']/../../..//div//button//p[text()='Pipeline Overview']" )
	WebElement pipelineOverviewLink;
	@FindBy(xpath = "//div[@class='service-name']/a" )
	List<WebElement> pipelineList;
	@FindBy(xpath = "(//span[contains(text(),'Status:')]/../div)[1]" )
	WebElement pipelinestatus;
	@FindBy(xpath = "(//*[contains(@class,'ad-more-search mb-')]/div/div//*[@class='MuiSvgIcon-root'])[1]")
	WebElement searchDropDown;
	@FindBy(xpath = "//input[@class='search-input-si']")
	WebElement searchPipelineInput;
	@FindBy(xpath = "//span[@class='MuiIconButton-label']/input")
	WebElement searchPipelineCheckbox;
	public EnvironmentCreationPage VerifyScaleDownPipeline(String PipelineName,String Status) {
		
		    ui_wait(4);
			ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineOverviewLink, Pause.MEDIUM));			
			ui_click(pipelineOverviewLink, "Poc_QA pipelineOverviewLink");
//			ui_IsElementDisplay(ui_waitForElementToDisplay(PipelineSubject, Pause.MEDIUM));
			ui_IsElementDisplay(ui_waitForElementToDisplay(searchDropDown, Pause.MEDIUM));
			ui_wait(3);
		    ui_click(searchDropDown, "searchDropDown");    
		    ui_wait(2);
			ui_clearAndSetValue(searchPipelineInput, PipelineName);
			ui_wait(1);
			ui_click(searchPipelineCheckbox, "searchPipelineCheckbox");
            ui_wait(30);
            ui_IsElementDisplay(ui_waitForElementToDisplay(pipelineList.get(0), Pause.MEDIUM));	
            Assert.assertEquals(pipelineList.get(0).getText(), PipelineName);
            System.out.println("--------------"+pipelinestatus.getText());
            Assert.assertEquals(pipelinestatus.getText().contains(Status), true);
		return this;
	}
	
	@FindBy(xpath = "//ul[@class='headerul appheaderul']//li[text()='Associated Services']")
	WebElement envAssociatedServicesOptionConfigMaps;

	@FindBy(xpath = "//*[contains(@class,'color-danger')]")
	List<WebElement> heathStatus;

	@FindBy(xpath = "//div[contains(@class,'sch-inp-controller')]//button[@type='button']")
	WebElement deleteScheduler;

	@FindBy(xpath = "//input[@name='remarks' and @placeholder='Please enter the reason to delete']")
	WebElement reasonToDelete;

	@FindBy(xpath = "//button[@class='btn btn-danger'][text()='Delete']")
	WebElement deleteButton;

	public EnvironmentCreationPage EnvironmentDownTimeSchedulerScaleDown(String appName, String envName,
			String timeZone, ArrayList<String> user) {

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			ui_click(addScheduler, "clicks on add scheduler button");
			ui_clearAndSetValue(schedulerInputName, SchedulerName);
			ui_click(scaleDownButton, "clicks on scale down button");
			String HourTime = TimeUtililty.currentDateAndTime("HH");
			System.out.println(HourTime);
			ui_clearAndSetValue(HHTime, HourTime);
			String MinuteTime = TimeUtililty.addedMinutesTimes("mm", 1);
			System.out.println(MinuteTime);
			ui_clearAndSetValue(MMTime, MinuteTime);
			Select dropdown = new Select(timeZoneDropDown);
			dropdown.selectByVisibleText(timeZone);
			ui_click(addToScheduleBtn, "add to Scheduler Btn");
			for (int i = 0; i < userCheckBox.size(); i++) {
				if (user.contains(userCheckBox.get(i).getAttribute("value").trim())) {
					userCheckBox.get(i).click();
				}
			}
			ui_click(saveScheduleBtn, "clicks on save schedule button");
			ui_wait(2);
			ui_click(scaledown_ActiveInactive, "clicks on scaledown_Active Inactive button");
			ui_wait(70);
			ui_getUIDriver().navigate().refresh();
			ui_wait(2);
			ui_getUIDriver().navigate().refresh();
			ui_wait(2);
			ui_click(envAssociatedServicesOptionConfigMaps, "clicks on Associated Services option");
//		Assert.assertEquals(heathStatus, MinuteTime);
			ui_wait(20);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
						
			//Verify Pipeline Created
			ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
			for (WebElement element : poc_qaProjectLink) {
				if (element.getText().trim().equalsIgnoreCase(appName)) {
					element.click();
					projectSelection = true;
					break;
				}
			}
			ui_wait(3);
			String Pipelinename=envName+"_"+SchedulerName+"_down";
			VerifyScaleDownPipeline(Pipelinename,"SUCCESS");
			ui_wait(3);
			
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			ui_wait(2);
            // Delete Created Scheduler
			ui_click(deleteScheduler, "delete scheduler");
			ui_clearAndSetValue(reasonToDelete, "Automated reason to delete schedule");
			ui_click(deleteButton, "clicks on delete button after adding reason to delete scheduler");

		}
		return this;
	}

	@FindBy(xpath = "//div//p[@class='common-error']")
	WebElement commonError;

	@FindBy(xpath = "//div//h1[@class='error-msg-service-card']")
	WebElement errorMessage;

	public EnvironmentCreationPage CreateAndValidateEnvironmentNegativeTest1(String appName,
			ArrayList<String> EnvironmentType, String BranchName, String NameSpace, String RegistryName,
			String existingEnv) {

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", existingEnv);
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}

			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);
			dropdown1.selectByVisibleText(NameSpace);
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			ui_click(continueBtn, "user clicks save button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(commonError, Pause.MEDIUM));
			Assert.assertEquals(commonError.getText().trim(),
					"\"Oh!! We regret the inconvenience!! Something is not ok, please refresh after sometime or contact the administrator!!\"",
					"Common Error Message logged");
			System.out.println(commonError.getText().trim());
			ui_IsElementDisplay(ui_waitForElementToDisplay(errorMessage, Pause.MEDIUM));
			Assert.assertEquals(errorMessage.getText().trim(),
					"unable to save due to following reasons: An environment with this name already exists in this project.",
					"Error Message logged");
			System.out.println(errorMessage.getText().trim());

		}
		return this;
	}

	@FindBy(xpath = "//input[@class='error' and @value='repeatSchedule']/..//div[@class='error-message']")
	WebElement errorMessage1;

	public EnvironmentCreationPage EnvironmentDownTimeSchedulerScaleDownNegativeTest2(String appName, String envName,
			String timeZone, ArrayList<String> user, String existingSchedulerName) {

		// String SchedulerName = "AutoScheduler-" +
		// RandomStrings.generateRandomString(9);

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			ui_click(addScheduler, "clicks on add scheduler button");
			ui_clearAndSetValue(schedulerInputName, existingSchedulerName);
			ui_click(scaleDownButton, "clicks on scale down button");
			String HourTime = TimeUtililty.currentDateAndTime("HH");
			System.out.println(HourTime);
			ui_clearAndSetValue(HHTime, HourTime);
			String MinuteTime = TimeUtililty.addedMinutesTimes("mm", 1);
			System.out.println(MinuteTime);
			ui_clearAndSetValue(MMTime, MinuteTime);
			Select dropdown = new Select(timeZoneDropDown);
			dropdown.selectByVisibleText(timeZone);
			ui_click(addToScheduleBtn, "add to Scheduler Btn");
			for (int i = 0; i < userCheckBox.size(); i++) {
				if (user.contains(userCheckBox.get(i).getAttribute("value").trim())) {
					userCheckBox.get(i).click();
				}
			}
			ui_click(saveScheduleBtn, "clicks on save schedule button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(errorMessage1, Pause.MEDIUM));
			Assert.assertEquals(errorMessage1.getText().trim(),
					"project env scheduler cron meta data with this name already exists.", "Error Message logged");
			System.out.println(errorMessage1.getText().trim());
		}
		return this;
	}

	@FindBy(xpath = "//input[@type='number']/..//div")
	WebElement errorMessage2;

	public EnvironmentCreationPage EnvironmentDownTimeSchedulerScaleDownNegativeTest3(String appName, String envName,
			String timeZone, ArrayList<String> user) {

		// String SchedulerName = "AutoScheduler-" +
		// RandomStrings.generateRandomString(9);

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(2);
			if (selectedEnvHyperlink.getText().equals(envName)) {
				ui_click(selectedEnvHyperlink, "click on the selected environment");
			}
			ui_wait(3);
			ui_click(envDowntimeOptionConfigMaps, "clicks on DownTime option");
			ui_click(addScheduler, "clicks on add scheduler button");
			ui_clearAndSetValue(schedulerInputName, SchedulerName);
			ui_click(scaleDownButton, "clicks on scale down button");
//			String HourTime = TimeUtililty.currentDateAndTime("HH");
//			System.out.println(HourTime);
			ui_clearAndSetValue(HHTime, "abc");
//			String MinuteTime = TimeUtililty.addedMinutesTimes("mm",1);
//			System.out.println(MinuteTime);
			ui_clearAndSetValue(MMTime, "xyz");
			Select dropdown = new Select(timeZoneDropDown);
			dropdown.selectByVisibleText(timeZone);
			ui_click(addToScheduleBtn, "add to Scheduler Btn");
			ui_IsElementDisplay(ui_waitForElementToDisplay(errorMessage2, Pause.MEDIUM));
			Assert.assertEquals(errorMessage2.getText().trim(), "This is required", "Error Message logged");
			System.out.println(errorMessage2.getText().trim());
		}
		return this;
	}
	
	public EnvironmentCreationPage searchEnvironment(String appName, String envName) {

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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under application name");
			ui_clearAndSetValue(envSearchByName, envName);
			envSearchByName.sendKeys(Keys.ENTER);
		}
		return this;
	}
	
	String ConfigManifestYAMLFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testfiles\\upload\\UploadYAML\\config.yaml";
	@FindBy(xpath = "//input[@class='file-upload-input']")
	WebElement uploadYAML;
	@FindBy(xpath = "//div[@class='material-icon-custom-style']")
	WebElement deployConfig;
	@FindBy(xpath = "//input[@name='file_paths']")
	WebElement fillFilePath;
	public EnvironmentCreationPage CreateEnvironmentandBPManagedConfigMap(String appName, ArrayList<String> EnvironmentType,
			String BranchName, String NameSpace, String RegistryName,String gitURL,String branch,String path) {
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
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			ui_wait(3);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			// ui_clearAndSetValue(environmentName, EnvironmentName);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(environmentTypeCheckboxdev, Pause.LOW));
//		ui_click(environmentTypeCheckboxdev, "clicks on dev");
			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			
			//ui_click(confirmPopup, "Click on Okay Button");
			
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);			
			dropdown1.selectByVisibleText(NameSpace);
			
			//ui_click(confirmPopup, "Click on Okay Button");
			
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			//ui_click(confirmPopup, "Click on Okay Button");
			ui_wait(10);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			//ui_ActionMoveAndClick(continueBtn, "user clicks save button");
			//ui_ActionClick(continueBtn, "user clicks save button");
			ui_click(continueBtn, "user clicks save button");
			ui_wait(15);
		
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(3);
			ui_click(envHyperlinkList.get(0), "First Hyperlink Clicked");
			ui_wait(2);
			ui_click(envConfigOptionsConfigMaps, "envConfigOptions ConfigMaps");
			ui_wait(2);
			ui_click(switchtoBPmanagedConfigMaps, "switchtoBPmanaged ConfigMaps");
			ui_wait(2);
			ui_click(addConfigMaps_BPmanagedConfig, "addConfigMaps BPmanagedConfig");
			ui_wait(2);
			String Configname="ConfigMap"+ RandomStrings.generateRandomString(4);
			ui_setvalue(configName, "configName", Configname);
			ui_wait(3);
			Select configmapstrategy=new Select(configMapStrategyDropdown);
			configmapstrategy.selectByValue("only_file_upload_vcs");
			ui_wait(4);
			ui_click(clearClickGitDropDown, "ClicksclearField");
			ui_clearAndSetValue(gitUrlInputField, gitURL);
			ui_wait(2);
			ui_clickfromList(autoCompleteURLList, gitURL);
			ui_wait(5);

			ui_IsElementDisplay(ui_waitForElementToDisplay(loadBranchesBtn, Pause.MEDIUM));
			ui_click(loadBranchesBtn, "Poc_QA loadBranchesBtn");
			ui_wait(10);
			ui_selectValueFromDropDownByXPath(selectBranchName, "Selects Branch Name");
			Select branchname = new Select(selectBranchName);
			branchname.selectByVisibleText(branch);
			ui_wait(3);
			ui_clearAndSetValue(fillFilePath, path);			
			ui_wait(20);
			ui_IsElementDisplay(ui_waitForElementToDisplay(saveandDeployEnvironment, Pause.MEDIUM));		
			ui_click(saveandDeployEnvironment, "saveandDeploy Environment");
			ui_wait(20);
			ui_IsElementDisplay(ui_waitForElementToDisplay(configmapDeploySuccessfully, Pause.MEDIUM));
			Assert.assertEquals(ui_IsElementPresent(configmapDeploySuccessfully, "3"), true);
			ui_click(continueDeploy, "continue Deploy");
		}
	return this;
		
}
	@FindBy(xpath = "//ul[@class='headerul appheaderul']//li[text()='Secrets']")
	WebElement envSecretsTab;
	@FindBy(xpath = "//label[text()='(Switch to Bp managed secrets)']")
	WebElement switchtoBPmanagedSecret;
	@FindBy(xpath = "//a[contains(@class,'btn btn-submit')][text()='Add Secret']")
	WebElement addSecrets_BPmanagedConfig;
	@FindBy(xpath = "//input[@name='file_path']")
	WebElement addFilePath;
	@FindBy(xpath = "//div[text()='Namespace Secret deployed successfully!']")
	WebElement secretDeploySuccessfully;
	public EnvironmentCreationPage CreateEnvironmentwithSecret(String appName, ArrayList<String> EnvironmentType,
			String BranchName, String NameSpace, String RegistryName,String gitURL,String branch,String path) {
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
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			ui_wait(3);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			// ui_clearAndSetValue(environmentName, EnvironmentName);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(environmentTypeCheckboxdev, Pause.LOW));
//		ui_click(environmentTypeCheckboxdev, "clicks on dev");
			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			//ui_click(confirmPopup, "Click on Okay Button");
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);			
			dropdown1.selectByVisibleText(NameSpace);
			//ui_click(confirmPopup, "Click on Okay Button");			
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
			//ui_click(confirmPopup, "Click on Okay Button");
			ui_wait(10);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			//ui_ActionMoveAndClick(continueBtn, "user clicks save button");
			//ui_ActionClick(continueBtn, "user clicks save button");
			ui_click(continueBtn, "user clicks save button");
			ui_wait(15);
		
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(3);
			ui_click(envHyperlinkList.get(0), "First Hyperlink Clicked");
			ui_wait(2);
			ui_click(envSecretsTab, "envSecretsTab");
			ui_wait(2);
			ui_click(switchtoBPmanagedSecret, "switchtoBPmanaged ConfigMaps");
			ui_wait(2);
			ui_click(addSecrets_BPmanagedConfig, "addConfigMaps BPmanagedConfig");
			ui_wait(2);
			String Configname="Secrets"+ RandomStrings.generateRandomString(4);
			ui_setvalue(configName, "configName", Configname);
			ui_wait(3);
			ui_click(uploadCustomManifest_BPmanagedConfig, "uploadCustomManifest_BPmanagedConfig");
			ui_wait(2);
			ui_click(uploadCustomManifest_GitURL, "uploadCustomManifest_GitURL");
			ui_wait(2);
			ui_click(clearClickGitDropDown, "ClicksclearField");
			ui_clearAndSetValue(gitUrlInputField, gitURL);
			ui_wait(2);
			ui_clickfromList(autoCompleteURLList, gitURL);
			ui_wait(5);

			ui_IsElementDisplay(ui_waitForElementToDisplay(loadBranchesBtn, Pause.MEDIUM));
			ui_click(loadBranchesBtn, "Poc_QA loadBranchesBtn");
			ui_wait(10);
			ui_selectValueFromDropDownByXPath(selectBranchName, "Selects Branch Name");
			Select branchname = new Select(selectBranchName);
			branchname.selectByVisibleText(branch);
			ui_wait(3);
			ui_clearAndSetValue(addFilePath, path);			
			ui_wait(20);
			ui_IsElementDisplay(ui_waitForElementToDisplay(saveandDeployEnvironment, Pause.MEDIUM));		
			ui_click(saveandDeployEnvironment, "saveandDeploy Environment");
			ui_wait(20);
			ui_IsElementDisplay(ui_waitForElementToDisplay(secretDeploySuccessfully, Pause.MEDIUM));
			Assert.assertEquals(ui_IsElementPresent(secretDeploySuccessfully, "3"), true);
			ui_click(continueDeploy, "continue Deploy");
		}
	return this;
		
}
	@FindBy(xpath = "//input[@name='value' and @placeholder='Add value']")
	WebElement inputValue;
	public EnvironmentCreationPage CreateEnvironmentwithSecret_KeyValuePair(String appName, ArrayList<String> EnvironmentType,
			String BranchName, String NameSpace, String RegistryName,String key,String value) {
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
			ui_wait(5);
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_IsElementDisplay(ui_waitForElementToDisplay(addNewEnvironment, Pause.MEDIUM));
			ui_click(addNewEnvironment, "clicks on add new environment button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentName, Pause.LOW));
			ui_setvalue(environmentName, "", EnvironmentName);
			ui_wait(3);
			// environmentTypeCheckbox.get(0).click();
			for (int i = 0; i < environmentTypeCheckbox.size(); i++) {
				if (EnvironmentType.contains(environmentTypeCheckbox.get(i).getAttribute("value").trim())) {
					environmentTypeCheckbox.get(i).click();
				}
			}
			// ui_clearAndSetValue(environmentName, EnvironmentName);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(environmentTypeCheckboxdev, Pause.LOW));
//		ui_click(environmentTypeCheckboxdev, "clicks on dev");
			ui_wait(2);
			if (manualBuildYes.getAttribute("value").equals("false"))
				ui_click(manualBuildYes, "Poc_QA allowManualBuildYes");
			if (manualDeployYes.getAttribute("value").equals("false"))
				ui_click(manualDeployYes, "Poc_QA allowManualDeployYes");
			// ui_selectValueFromDropDownByXPath(selectCluster, "Selects cluster Name");
			Select dropdown = new Select(selectCluster);
			dropdown.selectByVisibleText(BranchName);
			//ui_click(confirmPopup, "Click on Okay Button");
			ui_selectValueFromDropDownByXPath(selectNameSpace, "Selects namespace");
			Select dropdown1 = new Select(selectNameSpace);			
			dropdown1.selectByVisibleText(NameSpace);
		//	ui_click(confirmPopup, "Click on Okay Button");			
			ui_selectValueFromDropDownByXPath(selectRegistry, "Selects Registry");
			Select dropdown2 = new Select(selectRegistry);
			dropdown2.selectByVisibleText(RegistryName);
		//	ui_click(confirmPopup, "Click on Okay Button");
			ui_wait(10);
			ui_IsElementDisplay(ui_waitForElementToDisplay(continueBtn, Pause.MEDIUM));
			//ui_ActionMoveAndClick(continueBtn, "user clicks save button");
			//ui_ActionClick(continueBtn, "user clicks save button");
			ui_click(continueBtn, "user clicks save button");
			ui_wait(15);
		
			ui_clearAndSetValue(envSearchByName, EnvironmentName);
			envSearchByName.sendKeys(Keys.ENTER);
			ui_wait(3);
			ui_click(envHyperlinkList.get(0), "First Hyperlink Clicked");
			ui_wait(2);
			ui_click(envSecretsTab, "envSecretsTab");
			ui_wait(2);
			ui_click(switchtoBPmanagedSecret, "switchtoBPmanaged ConfigMaps");
			ui_wait(2);
			ui_click(addSecrets_BPmanagedConfig, "addConfigMaps BPmanagedConfig");
			ui_wait(2);
			String Configname="Secrets"+ RandomStrings.generateRandomString(4);
			ui_setvalue(configName, "configName", Configname);
			ui_wait(3);	
			ui_setvalue(inputKey, "adds key value", key);
			ui_setvalue(inputValue, "adds key value", value);		
			ui_wait(20);
			ui_IsElementDisplay(ui_waitForElementToDisplay(saveandDeployEnvironment, Pause.MEDIUM));		
			ui_click(saveandDeployEnvironment, "saveandDeploy Environment");
			ui_wait(20);
			ui_IsElementDisplay(ui_waitForElementToDisplay(secretDeploySuccessfully, Pause.MEDIUM));
			Assert.assertEquals(ui_IsElementPresent(secretDeploySuccessfully, "3"), true);
			ui_click(continueDeploy, "continue Deploy");
		}
	return this;
		
}

	
	@FindBy(xpath = "(//div[@class='d-flex space-between']/a)[1]")
	WebElement firstEnvironmentEditLink;	
	@FindBy(xpath = "//div[(@class='heading-section-service')]/div[1]")
	WebElement editEnvironmentHeading;	
	public EnvironmentCreationPage editEnvironment(String appName) throws Exception {
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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_wait(3);
			ui_click(firstEnvironmentEditLink, "first Environment Edit Link");
			ui_wait(3);
			Assert.assertEquals(editEnvironmentHeading.getText(), "Edit Environment");
		}
		return this;
	}
	
	@FindBy(xpath = "//div[text()='CPU Utilization of Namespace']")
	WebElement environmentDashboard_CpuUtilization;
	@FindBy(xpath = "//div[text()='Memory Utilization of Namespace']")
	WebElement environmentDashboard_MemoryUtilization;
	@FindBy(xpath = "//div[text()='Top 3 Services with Highest CPU Utilization']")
	WebElement environmentDashboard_Top3ServiceCpuUtilization;
	@FindBy(xpath = "//div[text()='Top 3 Services with Highest Memory Utilization']")
	WebElement environmentDashboard_Top3ServiceMemoryUtilization;
	@FindBy(xpath = "//div[@class='pd-10']/following-sibling::div/div/div[1]/div")
	List<WebElement> environmentAssociatedServices_Headers;
	@FindBy(xpath = "//input[@placeholder='Search on ConfigMap Name']")
	WebElement environmentConfigMaps_SearchConfig;
	@FindBy(xpath = "//input[@placeholder='Search on Secret Name']")
	WebElement environmentSecret_SearchSecret;
	@FindBy(xpath = "//button[text()='Add Secret']")
	WebElement environmentSecret_AddSecret;
	@FindBy(xpath = "//p[text()='Available Environment Downtime Scheduler']")
	WebElement environmentdowntimeScheduler_Heading;
	
	public EnvironmentCreationPage VerifyEnvironmentDetail(String appName,String Environment) throws Exception {
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
			ui_IsElementDisplay(ui_waitForElementToDisplay(environmentOverview, Pause.MEDIUM));
			ui_click(environmentOverview, "clicks on environment overview tab under applicartion name");
			ui_wait(3);
			ArrayList<String> environmentOverviewList_Initial = new ArrayList<String>();
			for(int i=0;i<envHyperlinkList.size();i++) {
				environmentOverviewList_Initial.add(envHyperlinkList.get(i).getText());
				System.out.println(envHyperlinkList.get(i).getText());
			}
			ui_clearAndSetValue(envSearchByName, Environment);
			envSearchByName.sendKeys(Keys.ENTER);
			// ui_click(searchButton, "clicks on search icon next to env name entered");
			ui_wait(8);
			//Boolean textResult=false;
			for(int i=0;i<envHyperlinkList.size();i++) {
		    Assert.assertTrue(envHyperlinkList.get(i).getText().contains(Environment));
		    ui_click(envHyperlinkList.get(i), "env Hyperlink");
			}
			Assert.assertEquals(ui_IsElementPresent(environmentDashboard_CpuUtilization, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(environmentDashboard_MemoryUtilization, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(environmentDashboard_Top3ServiceCpuUtilization, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(environmentDashboard_Top3ServiceMemoryUtilization, "2"), true);
			
			ui_click(envAssociatedServicesOptionConfigMaps, "env AssociatedServices Tab");
			ui_wait(2);
			Assert.assertEquals(associatedServicesBannerText.get(0).getText(), "Associated Services");
			Assert.assertEquals(environmentAssociatedServices_Headers.get(0).getText(), "Service Name");
			Assert.assertEquals(environmentAssociatedServices_Headers.get(1).getText(), "Deployment Status");
			Assert.assertEquals(environmentAssociatedServices_Headers.get(2).getText(), "Health Status");
			Assert.assertEquals(environmentAssociatedServices_Headers.get(3).getText(), "Artifact");
			Assert.assertEquals(environmentAssociatedServices_Headers.get(4).getText(), "Resource Utilization");
			Assert.assertEquals(environmentAssociatedServices_Headers.get(5).getText(), "Actions");
			
			ui_click(envConfigOptionsConfigMaps, "env Config Maps Tab");
			ui_wait(2);
			Assert.assertEquals(ui_IsElementPresent(switchtoBPmanagedConfigMaps, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(addConfig, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(environmentConfigMaps_SearchConfig, "2"), true);
			
			ui_click(envSecretsOptionConfigMaps, "env Secrets Tab");			
			ui_wait(2);
			Assert.assertEquals(ui_IsElementPresent(switchtoBPmanagedSecret, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(environmentSecret_AddSecret, "2"), true);
			Assert.assertEquals(ui_IsElementPresent(environmentSecret_SearchSecret, "2"), true);			
			
			ui_click(envDowntimeOptionConfigMaps, "env Downtime Tab");
			ui_wait(2);
			Assert.assertEquals(ui_IsElementPresent(addScheduler, "2"), true);				
			Assert.assertEquals(ui_IsElementPresent(environmentdowntimeScheduler_Heading, "2"), true);				
		}
		return this;
	}

	
}
