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

public class StepCataloguePage extends BasePage {

	String StepName = "Auto-Step" + RandomStrings.generateRandomString(9);
	//Version 3

	@FindBy(xpath = "//span[text()='Step Catalogs']")
	WebElement stepCatalogsBtn;
	
	@FindBy(xpath = "//p[text()='Create a new step']")
	WebElement CreateStepText;
	
	@FindBy(xpath = "//input[@placeholder='Clone Repository']")
	WebElement inputStepName;
	
	@FindBy(xpath = "//select[@name='version']")
	WebElement versionSelectDrop;
	
	@FindBy(xpath = "//input[@name='code' and @placeholder='Step Code (Must be Unique)']")
	WebElement inputStepCode;
	
	@FindBy(xpath = "//input[@name='container_type' and @value='Dockerize']")
	WebElement stepTypeRadio1;
	
	@FindBy(xpath = "//input[@name='container_type' and @value='Non-Dockerize']")
	WebElement stepTypeRadio2;
	
	public StepCataloguePage SearchExisitingRepo(String version) {

		ui_click(stepCatalogsBtn, "Step Catalogs button under side bar");
		ui_IsElementDisplay(ui_waitForElementToDisplay(CreateStepText, Pause.MEDIUM));
		ui_clearAndSetValue(inputStepName, StepName);
		Select dropdown = new Select(versionSelectDrop);
		dropdown.selectByVisibleText(version); 
		ui_clearAndSetValue(inputStepCode, StepName);
		if (!(stepTypeRadio1.isSelected()))
			ui_click(stepTypeRadio1, "radio button : Dockerize");
		
		return this;
	}

}