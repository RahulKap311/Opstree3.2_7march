package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;

public class JobTemplatePage extends BasePage {

	@FindBy(xpath = "//li//button[contains(@class,'main-nav-1')]//span[1][@title]")
	List<WebElement> poc_qaProjectLink;

	@FindBy(xpath = "//button//span[2][@class='flaticon-expand-arrow']/../..//div//span[@title='Job Templates']")
	WebElement jobTemplateLink;

	@FindBy(xpath = "//div[text()='perfeasy-testing-v3']")
	WebElement v3TemplateName;

	@FindBy(xpath = "//div//p//div[text()='perfeasy-testing-v3']/../../../../..//a[@class='text-anchor-blue'][contains(@href,'/application/')][contains(@href,'/application/')]")
	WebElement jobTemplateEditLink;

	@FindBy(xpath = "//label[text()='integration_Job']/..//button[@class='transparent-btn nowrap']")
	WebElement jiraIntegrationJobEyeLink;

	@FindBy(xpath = "//div[contains(@class,'step-card-design')]//label[text()='integration_testing']/..//button[@class='transparent-btn nowrap']")
	WebElement editIntegrationDetails;

	@FindBy(xpath = "//div[text()='Edit Step']")
	WebElement editIntegrationStep;

	@FindBy(xpath = "//div[@class='custom-notify-header']/../..//button[text()='Close']")
	WebElement closeEditIntegrationStep;

	@FindBy(xpath = "//label[text()='Add New Step']")
	WebElement addNewIntegrationStep;

	@FindBy(xpath = "//label[text()='No Steps Selected']")
	WebElement displayEmptyStepsSelected;

	@FindBy(xpath = "//div[@class='card-body ']//div[contains(@class,'card-description')]//span")
	List<WebElement> integrationImageTagContainer;

	@FindBy(xpath = "//div[@class='card-body ']//div[contains(@class,'card-description')]//div[@title]")
	List<WebElement> integrationTextContainer;

	@FindBy(xpath = "//div[@class='click-btn']")
	List<WebElement> clickSelectStepUnderIntegrationJob;

	@FindBy(xpath = "//input[@name='WORKSPACE']")
	WebElement inputWorkspaceName;

	@FindBy(xpath = "//input[@name='INSTRUCTION']")
	WebElement inputInstruction;

	@FindBy(xpath = "(//div//h5)[1]")
	WebElement selectStepLeftHeader;

	@FindBy(xpath = "//div[@class='left-content']//button")
	List<WebElement> stepnameList;
	
	@FindBy(xpath = "//a[@class='addbtn' and text()='Add Custom Variable']")
	WebElement addCustomVariable;
	
	@FindBy(xpath = "//input[@name='variable_name']")
	WebElement inputVariableName;
	
	@FindBy(xpath = "//input[@name='variable_value']")
	WebElement inputVariableValue;
	
	@FindBy(xpath = "(//button[@class='btn btn-submit'])[2]")
	WebElement addVariable;
	
	@FindBy(xpath = "(//button[@class='btn btn-submit'])[3]")
	WebElement saveMavenExecute;
	
	@FindBy(xpath = "///div[@class='staticwrap-inner']//h5")
	WebElement openedCardHeader;
	
	@FindBy(xpath = "//button[@class='btn btn-submit'][text()='Save']")
	WebElement saveStep;

	public JobTemplatePage() {

	}

	public JobTemplatePage V3MultipleIntegrationSupport(String appName, String templateStr,
			String buildTestStepImageName, String mavenExecuteImageName, String s3FileUploadImageName,
			String s3FileDownloadImageName, String manageRemoteProcessImageName, String secureCopyImageName,
			String k8ManifestApplyImageName, String googleChatNotificationImageName, String cloneRepositoryImageName,
			String dockerImageBuildImageName, String workSpaceName, String instructionsText, String step1, String step2,
			String step3, String step4, String step5, String step6, String step7, String step8, String step9,
			String step10, String variableName, String variableValue) {

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

			ui_IsElementDisplay(ui_waitForElementToDisplay(jobTemplateLink, Pause.MEDIUM));
			ui_click(jobTemplateLink, "User clicks on job template link in left panel");
			ui_IsElementDisplay(ui_waitForElementToDisplay(v3TemplateName, Pause.MEDIUM));
			ui_click(jobTemplateEditLink, "User clicks on job template edit");
			ui_click(jiraIntegrationJobEyeLink, "User Clicks on eye button over Integration Job");
			ui_click(editIntegrationDetails, "User clicks on edit integration details");
			ui_click(editIntegrationStep, "User clicks on edit integration step button");
			ui_click(closeEditIntegrationStep, "User clicks on close edit step button");
			ui_click(addNewIntegrationStep, "User clicks on add new step for integration job");
			ui_IsElementDisplay(ui_waitForElementToDisplay(displayEmptyStepsSelected, Pause.MEDIUM));
			boolean imageTxtStatus = true;
			for (WebElement imageContainer : integrationImageTagContainer) {
				String imageText = imageContainer.getText();
				System.out.println(imageText);
				if (!(imageText.contains(buildTestStepImageName) || imageText.contains(mavenExecuteImageName)
						|| imageText.contains(s3FileUploadImageName) || imageText.contains(s3FileDownloadImageName)
						|| imageText.contains(manageRemoteProcessImageName) || imageText.contains(secureCopyImageName)
						|| imageText.contains(k8ManifestApplyImageName)
						|| imageText.contains(googleChatNotificationImageName)
						|| imageText.contains(cloneRepositoryImageName)
						|| imageText.contains(dockerImageBuildImageName))) {
					imageTxtStatus = false;
					break;
				}
			}
			Assert.assertTrue(imageTxtStatus, "Unable to  validate the Image container Text");
			boolean StepTxtStatus = true;
			for (WebElement stepContainer : integrationTextContainer) {
				String stepTxt = stepContainer.getAttribute("title");
				System.out.println(stepTxt);
				if (!(stepTxt.contains("This step helps to build a Maven project.")
						|| stepTxt.contains("This step helps to upload s3 file.")
						|| stepTxt.contains("This step helps to download s3 file.")
						|| stepTxt.contains(
								"This step helps to request a service from a program located in another computer on a network without having to understand the network's details.")
						|| stepTxt.contains(
								"This step helps to securely copy files/folders between Linux systems on a network.")
						|| stepTxt.contains("This step helps to apply/delete/update k8's resources.")
						|| stepTxt.contains("This will send notification of Build/Deploy Status.")
						|| stepTxt.contains("This step helps to clone the git repository of your service source code")
						|| stepTxt.contains("This step helps to build docker images for your services"))) {
					StepTxtStatus = false;
					break;
				}
			}
			Assert.assertTrue(StepTxtStatus, "Unable to  validate the integration step text container");
//			int randmDigit = RandomStrings.generateRandomInt(1);
//			System.out.println(clickSelectStepUnderIntegrationJob.get(randmDigit));
			ArrayList<String> stepNameArr = new ArrayList<String>();
			for (int i = 0; i < stepnameList.size(); i++) {
				stepNameArr.add(stepnameList.get(i).getAttribute("title").trim());
			}
			Collections.sort(stepNameArr);

			ArrayList<String> stepNameExpArr = new ArrayList<String>();
			stepNameExpArr.add(step1);
			stepNameExpArr.add(step2);
			stepNameExpArr.add(step3);
			stepNameExpArr.add(step4);
			stepNameExpArr.add(step5);
			stepNameExpArr.add(step6);
			stepNameExpArr.add(step7);
			stepNameExpArr.add(step8);
			stepNameExpArr.add(step9);
			stepNameExpArr.add(step10);

			Collections.sort(stepNameExpArr);
			System.out.println(stepNameExpArr);
			
			//Assert.assertTrue(stepNameArr.equals(stepNameExpArr),"");
//			for (int i = 0; i < clickSelectStepUnderIntegrationJob.size(); i++) {
//				ui_click(clickSelectStepUnderIntegrationJob.get(i), "User Clicks on Step Name");
//				SoftAssertion.assertEquals(stepNameExpArr.contains(openedCardHeader.getText()),
//						"Validated left header on each step");
////				for (int k = 0; k < stepNameExpArr.size(); k++) {
////					for (int j = 0; j < stepNameArr.size(); j++) {
////						SoftAssertion.assertEquals(stepNameArr.get(j), stepNameExpArr.get(k), "Validated left header on each step");
////					}
////				}
//				ui_click(closeEditIntegrationStep, "clicks on close button");
//			}
	

			ui_click(clickSelectStepUnderIntegrationJob.get(1), "User clicks on random step to be added");
			ui_clearAndSetValue(inputWorkspaceName, workSpaceName);
			ui_setvalue(inputWorkspaceName, "sets value for input workspace field", workSpaceName);
			ui_setvalue(inputInstruction, "Instructions Text", instructionsText);
			ui_click(addCustomVariable, "User clicks on add custom step button");
			ui_IsElementDisplay(ui_waitForElementToDisplay(inputVariableName, Pause.MEDIUM));
			ui_clearAndSetValue(inputVariableName, variableName);
			ui_clearAndSetValue(inputVariableValue, variableValue);
			ui_click(addVariable, "User clicks on add variable hyperlink");
			ui_click(saveMavenExecute, "User clicks on save maven button");
			ui_click(saveStep, "User clicks on save step button");

			ui_wait(5);
			

		}

		return this;

	}

}