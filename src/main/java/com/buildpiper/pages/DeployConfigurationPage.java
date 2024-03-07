package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.Pause;

public class DeployConfigurationPage extends BasePage {
	
	@FindBy(xpath = "//*[text()='Deploy Details']")
	WebElement DeployDetailsTab;
	
	@FindBy(xpath = "//*[text()='Env Deploy Details']")
	WebElement validateDeployDetails;

	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement buildPiperUIDeployConfigBtn;
	
	@FindBy(xpath = "//input[@placeholder='Image Name']")
	WebElement accessLevelImageName;
	
	@FindBy(xpath = "//span//input[@type='radio' and @value='PRIVATE']")
	WebElement privateAccess;
	
	@FindBy(xpath = "//span//input[@type='radio' and @value='PUBLIC']")
	WebElement publicAccess;
	
	@FindBy(xpath = "//span//input[@type='radio' and @value='PROTECTED']")
	WebElement protectedAccess;
	
	@FindBy(xpath = "//button//span[text()=' Add Access Level']")
	WebElement addAcessLevel;

	@FindBy(xpath = "//input[@placeholder='name'][@name='name']")
	WebElement accessName;
	
	@FindBy(xpath = "//input[@placeholder='4001'][@name='port']")
	WebElement port;
	
	@FindBy(xpath = "//input[@placeholder='4001'][@name='target_port']")
	WebElement targetPort;
	
	@FindBy(xpath = "//button[@class='btn btn-v2-primary btn-sm']")
	WebElement addAccessBtn;
	
	@FindBy(xpath = "//select[@name='image_pull_policy']")
	WebElement imagePullPolicy;
	
	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//div[@class='btn-group btn-icon-group']//button[@title]")
	List<WebElement> serviceBtnList;

	@FindBy(xpath = "//div[@class='heading'][contains(.,'No Cache Use')]")
	WebElement deployDetailsText1;
	
	@FindBy(xpath = "//button[contains(@class,'btn btn-primary-v2')][contains(.,'Trigger Build')]")
	WebElement triggerBuildBtn;
	
	@FindBy(xpath = "//*[text()='Stable Configuration ']")
	WebElement deployStableConfigCheck;
	
	@FindBy(partialLinkText = "Build #")
	WebElement buildHyperLink;

	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Cloning Repository')]" )
	WebElement cloningRepositorySuccessLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Pre Hooks Executing')]" )
	WebElement preHooksExecuingSuccessLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'TRIVY_IMAGE_SCANNING')]" )
	WebElement trivyImageScanningSuccessLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Build Docker Image')]" )
	WebElement buildDockerImageSuccessLink;
	
	@FindBy(xpath = "//div[@class='d-grid grid-temp-log-line']//pre[contains(.,'docker build command')]" )
	WebElement dockerBuildCommandLogLine;
	
	@FindBy(xpath = "//div[@class='d-grid grid-temp-log-line']//pre[contains(.,'Start executing:')]" )
	WebElement preHooksCommandLogLine;
	
	@FindBy(xpath = "//label[@class='switch']//input[@name='configMap_file_path']" )
	WebElement addConfigToDeploy;
	
	@FindBy(xpath = "//select[@name='configMap_name' and @class='select']" )
	WebElement selectDropDownConfigNames;
	
	@FindBy(xpath = "//button[contains(@class,'btn-round')]//span[contains(@class,'refresh-button')]" )
	WebElement refreshBuildStatus;
	
	@FindBy(xpath = "//div[contains(@class,'round-chip bg-round-green status')][text()='SUCCESS']" )
	WebElement buildSuccessStatus;
	
	public DeployConfigurationPage() {

	}
	

	public DeployConfigurationPage CreateAndValidateDeployConfig(String AccessType,String AccessName,String portNumber,String TargetPort,ArrayList<String> ServiceButton, String configName) {
		
		//String imageNameValidateText = ServiceName+"/"+EnvName+"/"+ProjectName;
		//System.out.println(imageNameValidateText);
        ui_wait(3);
		ui_IsElementDisplay(ui_waitForElementToDisplay(validateDeployDetails, Pause.MEDIUM));
		ui_click(validateDeployDetails, "Poc_QA validateDeployDetails");
		ui_wait(2);
		ui_IsElementDisplay(ui_waitForElementToDisplay(DeployDetailsTab, Pause.MEDIUM));
		ui_click(DeployDetailsTab, "Poc_QA validateDeployDetails");
		ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperUIDeployConfigBtn, Pause.MEDIUM));
		ui_click(buildPiperUIDeployConfigBtn, "clicks on buildPiperUI button");
		//ui_getTextForElements(accessLevelImageName);
		//Assert.assertEquals(ui_getTextForElements(imageNameValidateText), accessLevelImageName, "Image Name Does Not Match");
		ui_click(addAcessLevel, "Add Access Level");
		ui_wait(2);
		
		if(AccessType.contains("PRIVATE"))
		{
			ui_click(privateAccess, "Poc_QA privateAccess");

		}else if(AccessType.contains("PUBLIC"))
		{

			ui_click(publicAccess, "Poc_QA publicAccess");

		}else {

			ui_click(protectedAccess, "Poc_QA protectedAccess");

		}
		ui_clearAndSetValue(accessName, AccessName);
		ui_clearAndSetValue(port, portNumber);
		ui_clearAndSetValue(targetPort, TargetPort);
		ui_click(addAccessBtn, "clicks add button");
		ui_wait(2);
		Select imagepule=new Select(imagePullPolicy);
		imagepule.selectByValue("Always");
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
//		ui_click(addConfigToDeploy, "switches config maps add to yes");
//		Select dropdown = new Select(selectDropDownConfigNames);
//		dropdown.selectByVisibleText(configName);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(validateDeployDetails, "Clicks on Deploy Details tag");
		//ui_click(buildFlashBtn, "clicks build button");
//    	if (ServiceButton.contains(serviceBtnList.get(0).getAttribute("title").trim())) {
//    		
//    		System.out.println(serviceBtnList.size());
//
//    		serviceBtnList.get(0).click();
//    	}
		//serviceBtnList.get(0).click();
//		ui_click(triggerBuildBtn, "clicks trigger build button");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(deployDetailsText1, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(deployStableConfigCheck, Pause.MEDIUM));
//		ui_click(buildHyperLink, "clicks on recent build history hyperlink");
////		ui_switchToNewWindow();
//		ui_IsElementDisplay(ui_waitForElementToDisplay(cloningRepositorySuccessLink, Pause.MEDIUM));
//		ui_click(cloningRepositorySuccessLink, "cloningRepository click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs1 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status1 = consoleLogs1.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status1);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(preHooksExecuingSuccessLink, Pause.MEDIUM));
//		ui_click(preHooksExecuingSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs2 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status2 = consoleLogs2.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status2);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(preHooksExecuingSuccessLink, Pause.MEDIUM));
//		ui_click(preHooksExecuingSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs3 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status3 = consoleLogs3.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status3);
//		/////validateion from build config page /////////////
//		
//		ui_wait(6000);
//	    ui_getUIDriver().navigate().refresh();
//		
//		
//		ui_IsElementDisplay(ui_waitForElementToDisplay(buildDockerImageSuccessLink, Pause.MEDIUM));
//		ui_click(buildDockerImageSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs4 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status4 = consoleLogs4.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status4);
//		ui_wait(3);
//		ui_getUIDriver().quit();
		
		//String TxtValidationDockerLine = dockerBuildCommandLogLine.getText().trim();
		//TxtValidationDockerLine.contains("docker build command : DOCKER_BUILDKIT=1 docker build -t registry.buildpiper.in/automation-ccmzklcafvsaytf/qa/qa:1-20230524T1441 --build-arg qa=**** -f spring3hibernate/./Dockerfile spring3hibernate/.");
		
		//String TxtValidationPreHooksLine = preHooksCommandLogLine.getText().trim();
		//TxtValidationPreHooksLine.contains("PRE_HOOKS with order id: 1");
		
//		if(!ui_IsElementPresent(buildSuccessStatus,"5")==true) {
//			
//			refreshBuildStatus.click();
//			
//		}

		return this;
	}
	
	String ManifestYAMLFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testfiles\\upload\\UploadYAML\\testYaml.yaml";
	
	@FindBy(xpath = "//input[@placeholder='Name'][@name='name']")
	WebElement menifestAccessName;
	@FindBy(xpath = "//input[@class='file-upload-input']")
	WebElement uploadYAML;
	@FindBy(xpath = "(//input[@name='upload_type'])[2]/..")
	WebElement uploadCustomeManifest;
	@FindBy(xpath = "(//button[text()='Continue'])[2]")
	WebElement continueManifestUpload;
	@FindBy(xpath = "//input[@name='configMap_file_path']/../span[2]")
	WebElement defineConfigMapRadiobutton;
	@FindBy(xpath = "//select[@name='configMap_name']")
	WebElement defineConfigMapName;
	@FindBy(xpath = "//input[@name='configMap_mount_path']")
	WebElement defineConfigMapPath;
	@FindBy(xpath = "//input[@name='pvc_file_path']/../span[2]")
	WebElement bindPVCRadiobutton;
	@FindBy(xpath = "//select[@name='pvc_name']")
	WebElement pVCName;
	@FindBy(xpath = "//input[@name='pvc_path']")
	WebElement pvcPath;
	@FindBy(xpath = "//input[@name='deploy_variable_raw_input']/../span[2]")
	WebElement defineRawKeyValuePairRadiobutton;
	@FindBy(xpath = "//input[@name='env_key']")
	WebElement envKey;
	@FindBy(xpath = "//input[@name='env_value']")
	WebElement envValue;	
	@FindBy(xpath = "//input[@name='nodeAffinityRequired']/../span[2]")
	WebElement nodeAffinityRadiobutton;
	@FindBy(xpath = "//select[@name='key']")
	WebElement nodeAffinityKey;
	@FindBy(xpath = "//select[@name='operator']")
	WebElement nodeAffinityOperator;
	@FindBy(xpath = "//select[@name='value']")
	WebElement nodeAffinityValue;
	@FindBy(xpath = "//input[@name='liveliness']/../span[2]")
	WebElement livelinesRadiobutton;
	@FindBy(xpath = "//input[@name='readiness']/../span[2]")
	WebElement readinessRadiobutton;
	
	@FindBy(xpath = "//input[@name='path']")
	WebElement urlPath;
	@FindBy(xpath = "//input[@name='port']")
	WebElement portRunning;
	@FindBy(xpath = "//input[@name='initial_delay_seconds']")
	WebElement delay;
	@FindBy(xpath = "//input[@name='period_seconds']")
	WebElement period;
	@FindBy(xpath = "//input[@name='timeout_seconds']")
	WebElement timeout;
	@FindBy(xpath = "//input[@name='failure_threshold']")
	WebElement failure;
	
	
	
	
public DeployConfigurationPage CreateManifestDeployConfig(String AccessType,String AccessName,String portNumber,String TargetPort,ArrayList<String> ServiceButton, String configName) {
		
		//String imageNameValidateText = ServiceName+"/"+EnvName+"/"+ProjectName;
		//System.out.println(imageNameValidateText);

		ui_IsElementDisplay(ui_waitForElementToDisplay(validateDeployDetails, Pause.MEDIUM));
		ui_click(validateDeployDetails, "Poc_QA validateDeployDetails");
		ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperUIDeployConfigBtn, Pause.MEDIUM));
		ui_click(buildPiperUIDeployConfigBtn, "clicks on buildPiperUI button");
		//ui_getTextForElements(accessLevelImageName);
		//Assert.assertEquals(ui_getTextForElements(imageNameValidateText), accessLevelImageName, "Image Name Does Not Match");
		ui_click(addAcessLevel, "Add Access Level");
		ui_wait(2);
		
		if(AccessType.contains("PRIVATE"))
		{
			ui_click(privateAccess, "Poc_QA privateAccess");

		}else if(AccessType.contains("PUBLIC"))
		{

			ui_click(publicAccess, "Poc_QA publicAccess");

		}else {

			ui_click(protectedAccess, "Poc_QA protectedAccess");

		}
		
		ui_IsElementDisplay(ui_waitForElementToDisplay(uploadCustomeManifest, Pause.MEDIUM));
        ui_click(uploadCustomeManifest, "clicks on upload Custom Manifest");
        ui_wait(2);
        ui_clearAndSetValue(menifestAccessName, AccessName);
		ui_clearAndSetValue(port, portNumber);
		ui_wait(2);
        ui_IsElementDisplay(ui_waitForElementToDisplay(uploadYAML, Pause.MEDIUM));
        ui_click(uploadYAML, "clicks on upload YAML");
		ui_wait(5);
		ui_FileUpload(Configuration.get("browser"), ManifestYAMLFilePath);
		ui_wait(15);
		ui_getUIDriver().switchTo().defaultContent();
		ui_wait(2);
		ui_click(continueManifestUpload, "clicks Continue button");
		ui_wait(2);
		Select imagepule=new Select(imagePullPolicy);
		imagepule.selectByValue("Always");
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);		
		ui_click(defineConfigMapRadiobutton, "define ConfigMap Radiobutton");
		ui_wait(8);	
		ui_IsElementDisplay(ui_waitForElementToDisplay(defineConfigMapName, Pause.MEDIUM));
		Select dropdown = new Select(defineConfigMapName);
		//dropdown.selectByValue("cfg-values-via-git");
		dropdown.selectByValue("kube-root-ca.crt");
		ui_wait(2);	
		//ui_setvalue(defineConfigMapPath, "define ConfigMap Path", "/opt");
		ui_setvalue(defineConfigMapPath, "define ConfigMap Path", "/DockerFile");
		ui_wait(2);
		/*ui_click(bindPVCRadiobutton, "define ConfigMap Radiobutton");
		ui_wait(6);	
		ui_IsElementDisplay(ui_waitForElementToDisplay(pVCName, Pause.MEDIUM));
		Select dropdown1 = new Select(pVCName);
		dropdown1.selectByVisibleText("anuj-pvc");
		ui_wait(2);	
		ui_setvalue(pvcPath, "PVC Path", "/tmp");
		*/
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(defineRawKeyValuePairRadiobutton, "define Raw KeyValuePair Radiobutton");
		ui_setvalue(envKey, "envKey", "test");
		ui_setvalue(envValue, "envValue", "test");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		/*ui_click(nodeAffinityRadiobutton, "define node Affinity Radiobutton");
		ui_wait(6);	
		ui_IsElementDisplay(ui_waitForElementToDisplay(nodeAffinityKey, Pause.MEDIUM));
		Select Key = new Select(nodeAffinityKey);
		Key.selectByValue("beta.kubernetes.io/arch");
		ui_wait(3);	
		Select operator = new Select(nodeAffinityOperator);
		operator.selectByValue("In");
		ui_wait(3);	
		Select value = new Select(nodeAffinityValue);
		value.selectByValue("amd64");
		ui_wait(2);	
		*/
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);		
		ui_click(readinessRadiobutton, "readiness Radiobutton");
		ui_wait(2);
		ui_setvalue(urlPath, "urlPath", "/demo-app/healthcheck");
		ui_setvalue(portRunning, "portRunning", "8000");
		ui_setvalue(delay, "delay", "5");
		ui_setvalue(period, "period", "5");
		ui_setvalue(timeout, "timeout", "25");
		ui_setvalue(failure, "failure", "100");
		ui_wait(2);
		ui_click(livelinesRadiobutton, "livelines Radiobutton");
		ui_wait(2);
		ui_setvalue(urlPath, "urlPath", "/demo-app/healthcheck");
		ui_setvalue(portRunning, "portRunning", "8000");
		ui_setvalue(delay, "delay", "5");
		ui_setvalue(period, "period", "5");
		ui_setvalue(timeout, "timeout", "25");
		ui_setvalue(failure, "failure", "100");
		ui_wait(2);
		
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(preHooksSwitch, "Poc_QA preHooksSwitch");
		ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox1", "ls");
		
		ui_click(addNewPreHook, "clicks on add New Hook");
		ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox2", "ls");
		
		ui_click(postHooksSwitch, "Poc_QA postHooksSwitch");
		//ui_IsElementDisplay(ui_waitForElementToDisplay(postHooksPasswordBox, Pause.MEDIUM));
		ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox1", "ls");

		ui_click(addNewPostHook, "clicks on add New Post Hook");
		ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox2", "ls");
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(validateDeployDetails, "Clicks on Deploy Details tag");
		//ui_click(buildFlashBtn, "clicks build button");
//    	if (ServiceButton.contains(serviceBtnList.get(0).getAttribute("title").trim())) {
//    		
//    		System.out.println(serviceBtnList.size());
//
//    		serviceBtnList.get(0).click();
//    	}
		//serviceBtnList.get(0).click();
//		ui_click(triggerBuildBtn, "clicks trigger build button");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(deployDetailsText1, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(deployStableConfigCheck, Pause.MEDIUM));
//		ui_click(buildHyperLink, "clicks on recent build history hyperlink");
////		ui_switchToNewWindow();
//		ui_IsElementDisplay(ui_waitForElementToDisplay(cloningRepositorySuccessLink, Pause.MEDIUM));
//		ui_click(cloningRepositorySuccessLink, "cloningRepository click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs1 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status1 = consoleLogs1.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status1);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(preHooksExecuingSuccessLink, Pause.MEDIUM));
//		ui_click(preHooksExecuingSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs2 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status2 = consoleLogs2.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status2);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(preHooksExecuingSuccessLink, Pause.MEDIUM));
//		ui_click(preHooksExecuingSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs3 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status3 = consoleLogs3.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status3);
//		/////validateion from build config page /////////////
//		
//		ui_wait(6000);
//	    ui_getUIDriver().navigate().refresh();
//		
//		
//		ui_IsElementDisplay(ui_waitForElementToDisplay(buildDockerImageSuccessLink, Pause.MEDIUM));
//		ui_click(buildDockerImageSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs4 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status4 = consoleLogs4.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status4);
//		ui_wait(3);
//		ui_getUIDriver().quit();
		
		//String TxtValidationDockerLine = dockerBuildCommandLogLine.getText().trim();
		//TxtValidationDockerLine.contains("docker build command : DOCKER_BUILDKIT=1 docker build -t registry.buildpiper.in/automation-ccmzklcafvsaytf/qa/qa:1-20230524T1441 --build-arg qa=**** -f spring3hibernate/./Dockerfile spring3hibernate/.");
		
		//String TxtValidationPreHooksLine = preHooksCommandLogLine.getText().trim();
		//TxtValidationPreHooksLine.contains("PRE_HOOKS with order id: 1");
		
//		if(!ui_IsElementPresent(buildSuccessStatus,"5")==true) {
//			
//			refreshBuildStatus.click();
//			
//		}

		return this;
	}

String DeployManifestYAMLFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testfiles\\upload\\UploadYAML\\deploymentManifestSample.yaml";

@FindBy(xpath = "(//button[@class='btn btn-submit'])[2]")
WebElement buildPiperCustomManifestConfigBtn;
@FindBy(xpath = "//input[@value='MANIFEST_UPLOAD']/..")
WebElement uploadManifestRadioButton;


public DeployConfigurationPage CreateCustomManifestDeployConfig(String AccessType,String AccessName,String portNumber,String TargetPort,ArrayList<String> ServiceButton, String configName) {
	
	//String imageNameValidateText = ServiceName+"/"+EnvName+"/"+ProjectName;
	//System.out.println(imageNameValidateText);

	ui_IsElementDisplay(ui_waitForElementToDisplay(validateDeployDetails, Pause.MEDIUM));
	ui_click(validateDeployDetails, "Poc_QA validateDeployDetails");
	ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperCustomManifestConfigBtn, Pause.MEDIUM));
	ui_click(buildPiperCustomManifestConfigBtn, "clicks on CustomManifest Config button");
	ui_wait(4);
	ui_IsElementDisplay(ui_waitForElementToDisplay(imagePullPolicy, Pause.MEDIUM));
	Select imagepule=new Select(imagePullPolicy);
	imagepule.selectByValue("Always");
	ui_wait(3);
    ui_IsElementDisplay(ui_waitForElementToDisplay(uploadManifestRadioButton, Pause.MEDIUM));
    ui_click(uploadManifestRadioButton, "clicks on uploadManifest RadioButton");
    ui_IsElementDisplay(ui_waitForElementToDisplay(uploadYAML, Pause.MEDIUM));
    ui_click(uploadYAML, "clicks on upload YAML");
	ui_wait(5);
	ui_FileUpload(Configuration.get("browser"), DeployManifestYAMLFilePath);
	ui_wait(15);
	ui_getUIDriver().switchTo().defaultContent();
	ui_wait(2);	
    ui_click(continueBtn, "clicks continue button");
	
	return this;
}

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
@FindBy(xpath = "//span[text()='Setup Other Deployment']")
WebElement setupOtherDeployment;
@FindBy(xpath = "//div[@class='input-component']//label")
WebElement cloneFromEnvValidateTxt;
@FindBy(xpath = "//input[@name='deployment_name']")
WebElement deploymentNameField;


public DeployConfigurationPage CreateAndValidateMultiDeployConfig(String cloneText, String envCloneValue,String AccessType,String AccessName,String portNumber,String TargetPort,ArrayList<String> ServiceButton, String configName,String DeploymentName) {
	
	//String imageNameValidateText = ServiceName+"/"+EnvName+"/"+ProjectName;
	//System.out.println(imageNameValidateText);
    ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(validateDeployDetails, Pause.MEDIUM));
	ui_click(validateDeployDetails, "Poc_QA validateDeployDetails");
	ui_wait(2);
	ui_IsElementDisplay(ui_waitForElementToDisplay(DeployDetailsTab, Pause.MEDIUM));
	ui_click(DeployDetailsTab, "Poc_QA validateDeployDetails");
	ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperUIDeployConfigBtn, Pause.MEDIUM));
	ui_click(buildPiperUIDeployConfigBtn, "clicks on buildPiperUI button");
	//ui_getTextForElements(accessLevelImageName);
	//Assert.assertEquals(ui_getTextForElements(imageNameValidateText), accessLevelImageName, "Image Name Does Not Match");
	ui_click(addAcessLevel, "Add Access Level");
	ui_wait(2);
	
	if(AccessType.contains("PRIVATE"))
	{
		ui_click(privateAccess, "Poc_QA privateAccess");

	}else if(AccessType.contains("PUBLIC"))
	{

		ui_click(publicAccess, "Poc_QA publicAccess");

	}else {

		ui_click(protectedAccess, "Poc_QA protectedAccess");

	}
	ui_clearAndSetValue(accessName, AccessName);
	ui_clearAndSetValue(port, portNumber);
	ui_clearAndSetValue(targetPort, TargetPort);
	ui_click(addAccessBtn, "clicks add button");
	ui_wait(2);
	Select imagepule=new Select(imagePullPolicy);
	imagepule.selectByValue("Always");
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
//	ui_click(addConfigToDeploy, "switches config maps add to yes");
//	Select dropdown = new Select(selectDropDownConfigNames);
//	dropdown.selectByVisibleText(configName);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(preHooksSwitch, "Poc_QA preHooksSwitch");
	ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox1", "ls");
	
	ui_click(addNewPreHook, "clicks on add New Hook");
	ui_setvalue(preHooksPasswordBox, "Poc_QA preHooksPasswordBox2", "ls");
	
	ui_click(postHooksSwitch, "Poc_QA postHooksSwitch");
	//ui_IsElementDisplay(ui_waitForElementToDisplay(postHooksPasswordBox, Pause.MEDIUM));
	ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox1", "ls");

	ui_click(addNewPostHook, "clicks on add New Post Hook");
	ui_setvalue(postHooksPasswordBox, "Poc_QA postHooksPasswordBox2", "ls");
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(validateDeployDetails, "Clicks on Deploy Details tag");
	ui_wait(2);	
	ui_IsElementDisplay(ui_waitForElementToDisplay(DeployDetailsTab, Pause.MEDIUM));
	ui_click(DeployDetailsTab, "Poc_QA validateDeployDetails");
	ui_wait(4);
	ui_click(setupOtherDeployment, "Clicks on set upOther Deployment");
	ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperUIDeployConfigBtn, Pause.MEDIUM));
	ui_click(buildPiperUIDeployConfigBtn, "clicks on buildPiperUI button");
	ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(cloneFromEnvValidateTxt, Pause.MEDIUM));
	Assert.assertEquals(cloneFromEnvValidateTxt.getText().trim(), cloneText, "cloning text validated");
	ui_selectValueFromDropDownByXPath(selectEnvDrop, "selectEnvDrop");
	Select envDrop = new Select(selectEnvDrop);
	envDrop.selectByVisibleText(envCloneValue);
	ui_wait(3);				
	ui_IsElementDisplay(ui_waitForElementToDisplay(selectEnvDrop, Pause.MEDIUM));
	ui_selectValueFromDropDownByXPath(selectEnvDrop, "selectEnvDrop");
	Select envDrop1 = new Select(selectEnvDrop);
	envDrop1.selectByVisibleText(envCloneValue);
	ui_wait(3);
	ui_IsElementDisplay(ui_waitForElementToDisplay(nextBtn, Pause.MEDIUM));
	ui_click(nextBtn, "user clicks next button");
	ui_wait(3);
	ui_clearAndSetValue(deploymentNameField, DeploymentName);
	ui_wait(2);
	ui_click(addAcessLevel, "Add Access Level");
	ui_wait(2);
	
	if(AccessType.contains("PRIVATE"))
	{
		ui_click(privateAccess, "Poc_QA privateAccess");

	}else if(AccessType.contains("PUBLIC"))
	{

		ui_click(publicAccess, "Poc_QA publicAccess");

	}else {

		ui_click(protectedAccess, "Poc_QA protectedAccess");

	}
	ui_clearAndSetValue(accessName, AccessName);
	ui_clearAndSetValue(port, portNumber);
	ui_clearAndSetValue(targetPort, TargetPort);
	ui_click(addAccessBtn, "clicks add button");
	ui_wait(2);
	Select imagepule1=new Select(imagePullPolicy);
	imagepule1.selectByValue("Always");
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);		
	ui_click(defineConfigMapRadiobutton, "define ConfigMap Radiobutton");
	ui_wait(10);	
	ui_IsElementDisplay(ui_waitForElementToDisplay(defineConfigMapName, Pause.MEDIUM));
	Select dropdown = new Select(defineConfigMapName);
	//dropdown.selectByValue("cfg-values-via-git");
	dropdown.selectByValue("tcm");
	ui_wait(2);	
	//ui_setvalue(defineConfigMapPath, "define ConfigMap Path", "/opt");
	ui_setvalue(defineConfigMapPath, "define ConfigMap Path", "/DockerFile");
	ui_wait(2);
	/*ui_click(bindPVCRadiobutton, "define ConfigMap Radiobutton");
	ui_wait(6);	
	ui_IsElementDisplay(ui_waitForElementToDisplay(pVCName, Pause.MEDIUM));
	Select dropdown1 = new Select(pVCName);
	dropdown1.selectByVisibleText("anuj-pvc");
	ui_wait(2);	
	ui_setvalue(pvcPath, "PVC Path", "/tmp");
	*/
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(defineRawKeyValuePairRadiobutton, "define Raw KeyValuePair Radiobutton");
	ui_setvalue(envKey, "envKey", "test");
	ui_setvalue(envValue, "envValue", "test");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	/*ui_wait(2);
	ui_click(nodeAffinityRadiobutton, "define node Affinity Radiobutton");
	ui_wait(10);	
	ui_IsElementDisplay(ui_waitForElementToDisplay(nodeAffinityKey, Pause.MEDIUM));
	Select Key = new Select(nodeAffinityKey);
	Key.selectByValue("beta.kubernetes.io/arch");
	ui_wait(3);	
	Select operator = new Select(nodeAffinityOperator);
	operator.selectByValue("In");
	ui_wait(3);	
	Select value = new Select(nodeAffinityValue);
	value.selectByValue("amd64");
	ui_wait(2);	*/
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);		
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(validateDeployDetails, "Clicks on Deploy Details tag");

	
	
	return this;
}

@FindBy(xpath = "//span[text()='Setup Other Deployment']")
WebElement setupOtherDeploymentbutton;
@FindBy(xpath = "(//label//..//..//..//select)[1]")
WebElement selectEnvDrop;
@FindBy(xpath = "(//label//..//..//..//select)[2]")
WebElement selectDeployment;
@FindBy(xpath = "//button[@class='btn btn-primary' and text()=' Next ']")
WebElement nextBtn;

public DeployConfigurationPage SetupOtherDeployment(String env,String AccessType,String AccessName,String portNumber,String TargetPort,ArrayList<String> ServiceButton, String configName) {
	
	//String imageNameValidateText = ServiceName+"/"+EnvName+"/"+ProjectName;
	//System.out.println(imageNameValidateText);
    ui_click(setupOtherDeploymentbutton, "setupOtherDeploymentbutton");
	ui_IsElementDisplay(ui_waitForElementToDisplay(validateDeployDetails, Pause.MEDIUM));
	ui_click(validateDeployDetails, "Poc_QA validateDeployDetails");
	ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperUIDeployConfigBtn, Pause.MEDIUM));
	ui_click(buildPiperUIDeployConfigBtn, "clicks on buildPiperUI button");
	ui_selectValueFromDropDownByXPath(selectEnvDrop, "selectEnvDrop");
	Select envDrop = new Select(selectEnvDrop);
	envDrop.selectByVisibleText(env);
	ui_wait(4);
	Select deploymentDrop = new Select(selectDeployment);
	deploymentDrop.selectByIndex(1);
	ui_wait(4);
	ui_click(nextBtn, "user clicks next button");
	ui_wait(4);
	ui_click(addAcessLevel, "Add Access Level");
	ui_wait(2);
	
	if(AccessType.contains("PRIVATE"))
	{
		ui_click(privateAccess, "Poc_QA privateAccess");

	}else if(AccessType.contains("PUBLIC"))
	{

		ui_click(publicAccess, "Poc_QA publicAccess");

	}else {

		ui_click(protectedAccess, "Poc_QA protectedAccess");

	}
	ui_clearAndSetValue(accessName, AccessName);
	ui_clearAndSetValue(port, portNumber);
	ui_clearAndSetValue(targetPort, TargetPort);
	ui_click(addAccessBtn, "clicks add button");
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
//	ui_click(addConfigToDeploy, "switches config maps add to yes");
//	Select dropdown = new Select(selectDropDownConfigNames);
//	dropdown.selectByVisibleText(configName);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	ui_click(continueBtn, "clicks continue button");
	ui_wait(2);
	//ui_click(validateDeployDetails, "Clicks on Deploy Details tag");
	return this;
}



}
