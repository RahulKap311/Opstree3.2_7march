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

public class RepoIntegrationPage extends BasePage {

	String RepoName1 = "Auto-bitbucket" + RandomStrings.generateRandomString(9);
	String RepoName2 = "Auto-bitbucket-Secret" + RandomStrings.generateRandomString(9);
	String credential_Name = "Auto-Cred" + RandomStrings.generateRandomString(5);

	@FindBy(xpath = "//button[contains(@class,'main-menu hover-effect ')]//p[text()='Integrations']")
	WebElement integrationsButton;

	@FindBy(xpath = "//a[@href='/repo/list']")
	WebElement RepoListButton;

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary-v2')][text()=' Add Repo']")
	WebElement addRepoBtn;

	@FindBy(xpath = "//div[@class='integration-boxes']//p")
	List<WebElement> integrationoptions;

	@FindBy(xpath = "//input[@name='name' and @placeholder]")
	WebElement inputRepoInputName;

	@FindBy(xpath = "//select[@name='credential_id' and @class='select']")
	WebElement selectSecrets;

	@FindBy(xpath = "//input[@name='git_url' and @placeholder='https://bitbucket.buildpiper.in']")
	WebElement gitUrlInputName;

	@FindBy(xpath = "//button[@class='btn btn-submit' and text()='Test Connection ']")
	WebElement testConnectionBtn;

	@FindBy(xpath = "//button[contains(@class,'btn btn-submit')][contains(text(),'Save')]")
	WebElement repoSaveBtn;

	@FindBy(xpath = "//div[contains(@class,'alert-success undefined')]//p")
	WebElement connectionSuccessMsg;

	@FindBy(xpath = "//div[contains(@class,'search-bar')]//input[@name='name' and @placeholder='Name']")
	WebElement repoSearchTxtBox;

	@FindBy(xpath = "//div//div//p[contains(@title,'Auto')]")
	WebElement repoAccountGetTxt;

	@FindBy(xpath = "//div[@class='manage-update-icons']//button[@aria-controls='long-menu']")
	WebElement repoEditDeleteMenu;

	@FindBy(xpath = "//a[contains(@href,'/repo/')][contains(@href,'/edit')]")
	WebElement repoEditBtn;

	@FindBy(xpath = "//button[contains(@class,'MuiIconButton')][@type='button']//span[contains(text(),'Delete')]")
	WebElement repoDeleteBtn;

	@FindBy(xpath = "//div[@class='input-component']//*[@name='remarks' and @placeholder='Please enter the reason to delete']")
	WebElement reasonToDeleteTxt;

	@FindBy(xpath = "//button[@class='btn btn-danger'][text()='Delete']")
	WebElement DeleteBtn;

	public RepoIntegrationPage() {

	}

	public RepoIntegrationPage AddRepoOption1(String inetgrationType, String secretName1, String gitURL,
			String ValidateMsg) {

		ui_click(integrationsButton, "Integration button under side bar");
		ui_click(RepoListButton, "Repo Integration button under Integration button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addRepoBtn, Pause.MEDIUM));
		ui_click(addRepoBtn, "clicks on add repo button");
		for (int i = 0; i < integrationoptions.size(); i++) {
			if (inetgrationType.contains(integrationoptions.get(i).getText().trim())) {
				integrationoptions.get(i).click();
			}
		}
		ui_clearAndSetValue(inputRepoInputName, RepoName1);
		ui_IsElementDisplay(ui_waitForElementToDisplay(gitUrlInputName, Pause.MEDIUM));
		ui_clearAndSetValue(gitUrlInputName, gitURL);
		ui_click(testConnectionBtn, "clicks on test connection button after entering URL");
		ui_IsElementDisplay(ui_waitForElementToDisplay(connectionSuccessMsg, Pause.MEDIUM));
		Assert.assertEquals(connectionSuccessMsg.getText().trim(), ValidateMsg, "Validating Success Message");
		System.out.println(connectionSuccessMsg.getText().trim());
		ui_click(repoSaveBtn, "clicks on save button after entering URL");
		ui_IsElementDisplay(ui_waitForElementToDisplay(repoSearchTxtBox, Pause.MEDIUM));
		ui_clearAndSetValue(repoSearchTxtBox, RepoName1);
		repoSearchTxtBox.sendKeys(Keys.ENTER);
//            Assert.assertEquals(repoAccountGetTxt.getText().trim(), RepoName1, "Repo Name Validated");
		ui_click(repoEditDeleteMenu, "edit icon in repo integration page");
		ui_IsElementDisplay(ui_waitForElementToDisplay(repoEditBtn, Pause.MEDIUM));
		ui_click(repoDeleteBtn, "clicks delete button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(reasonToDeleteTxt, Pause.MEDIUM));
		ui_clearAndSetValue(reasonToDeleteTxt, "vbjgcxh");
		ui_click(DeleteBtn, "clicks delete button");

		return this;

	}

	@FindBy(xpath = "//a[@href='/secrets/add']")
	WebElement addSecretBtn;

	@FindBy(xpath = "//span//input[@name='credential_type']")
	List<WebElement> secretRadioType;

	@FindBy(xpath = "//input[@name='name' and @placeholder='Unique Credential Name']")
	WebElement credentialName;

	@FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
	WebElement userNameField;

	@FindBy(xpath = "//textarea[@name='credential' and @placeholder='Access Token']")
	WebElement accessTokenField;

	@FindBy(xpath = "//textarea[@name='aws_access_key' and @placeholder='aws_access_key']")
	WebElement awsAccessKey;

	@FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
	WebElement password;

	@FindBy(xpath = "//textarea[@name='credential' and @placeholder='SSH KEY']")
	WebElement sshKey;

	@FindBy(xpath = "//textarea[@name='password' and @placeholder='Token']")
	WebElement tokenValue;

	@FindBy(xpath = "//textarea[@name='aws_secret_access_key' and @placeholder='aws_secret_access_key']")
	WebElement awsSecretAccessKey;

	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement saveBtn;

	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement secretSearchBox;

	@FindBy(xpath = "(//div[@class='reposcard'])[1]//button[@type='button' and @aria-controls='long-menu']")
	WebElement secretsEditDeleteMenu;

	public RepoIntegrationPage AddSecret(String inetgrationType, String secretName1, String secretName,
			String userName_Val, String accessToken_Val, String awsAccessKey, String Password_Val, String SSH_Val,
			String Token_Val, String awsSecretKey_Val) {

		ui_click(integrationsButton, "Integration button under side bar");
		ui_click(RepoListButton, "Repo Integration button under Integration button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(addRepoBtn, Pause.MEDIUM));
		ui_click(addRepoBtn, "clicks on add repo button");
		for (int i = 0; i < integrationoptions.size(); i++) {
			if (inetgrationType.contains(integrationoptions.get(i).getText().trim())) {
				integrationoptions.get(i).click();
			}
		}
		ui_clearAndSetValue(inputRepoInputName, RepoName2);
		ui_click(addSecretBtn, "adds new secret");
		ui_wait(7);
		boolean radioSelection = false;
		System.out.println(secretName);
		for (int i = 0; i < secretRadioType.size(); i++) {
			if (secretName.contains(secretRadioType.get(i).getAttribute("value").trim())) {
				secretRadioType.get(i).click();
				radioSelection = true;
				break;
			}
			if (radioSelection) {

				ui_clearAndSetValue(credentialName, credential_Name);
				if (secretName == "USERNAME_PASSWORD" || secretName == "SSH_KEY" || secretName == "TOKEN") {
					ui_clearAndSetValue(userNameField, userName_Val);
				} else if (secretName == "ACCESS_TOKEN") {
					ui_clearAndSetValue(accessTokenField, accessToken_Val);
				} else if (secretName == "AWS_CREDENTIAL") {
					ui_clearAndSetValue(awsAccessKey, awsAccessKey);

				}
				if (secretName == "USERNAME_PASSWORD") {
					ui_clearAndSetValue(password, Password_Val);
				} else if (secretName == "SSH_KEY") {
					ui_clearAndSetValue(sshKey, SSH_Val);
				} else if (secretName == "TOKEN") {
					ui_clearAndSetValue(tokenValue, Token_Val);
				} else if (secretName == "AWS_CREDENTIAL") {
					ui_clearAndSetValue(awsSecretAccessKey, awsSecretKey_Val);

				}
				ui_IsElementDisplay(ui_waitForElementToDisplay(saveBtn, Pause.MEDIUM));
				ui_click(saveBtn, "user saves secrets");
				ui_IsElementDisplay(ui_waitForElementToDisplay(secretSearchBox, Pause.MEDIUM));
				ui_clearAndSetValue(secretSearchBox, credential_Name);
				secretSearchBox.sendKeys(Keys.ENTER);
				ui_click(repoEditDeleteMenu, "edit icon in repo integration page");
				ui_IsElementDisplay(ui_waitForElementToDisplay(repoEditBtn, Pause.MEDIUM));
				ui_click(repoDeleteBtn, "clicks delete button");
				ui_IsElementDisplay(ui_waitForElementToDisplay(reasonToDeleteTxt, Pause.MEDIUM));
				ui_clearAndSetValue(reasonToDeleteTxt, "vbjgcxh");
				ui_click(DeleteBtn, "clicks delete button");

			}

		}

		return this;

	}
	
	public RepoIntegrationPage SearchExisitingRepo(String repo) {

		ui_click(integrationsButton, "Integration button under side bar");
		ui_click(RepoListButton, "Repo Integration button under Integration button");
		ui_IsElementDisplay(ui_waitForElementToDisplay(repoSearchTxtBox, Pause.MEDIUM));
		ui_clearAndSetValue(repoSearchTxtBox, repo);
		repoSearchTxtBox.sendKeys(Keys.ENTER);
		
		return this;
	}

}