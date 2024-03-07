package com.buildpiper.base;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.buildpiper.base.Capability;



public class BasePage extends Capability {

	
	 protected BasePage(){
		ui_setDriver();
		PageFactory.initElements(ui_getUIDriver(), this);
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(List<WebElement> selectRegistry, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(WebElement element, String value) {
		// TODO Auto-generated method stub
		
	}
	
}
