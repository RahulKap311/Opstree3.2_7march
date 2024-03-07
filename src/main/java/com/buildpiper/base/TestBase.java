package com.buildpiper.base;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.buildpiper.report.Log;
import com.buildpiper.report.TestLinkUtility;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.ExcelUtility;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestBase extends Capability{

	public static ExtentReports extent=null;
	public static RequestSpecification requestSpecificationstatic =null;
	public Response response=null;
	
	
	public TestBase() {

	}

	@BeforeTest
	public void Reportsetup() throws IOException
	{
		

	}

	@BeforeMethod(alwaysRun = true)
	public void testName(ITestContext test,Method method) throws HeadlessException, IOException, AWTException{
		
		_session=new TestCase(method.getName(),method.getDeclaringClass().getPackage().getName());
		_session.set_data((HashMap<String, String>) new ExcelUtility().getTestData(_session.get_testCaseName())); 
		_session._screenRecorder.startRecording(method.getName(), false);
		
	}


	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception{
		_session.stopRecording();
		Reporter.setCurrentTestResult(result); Configuration.get("executionid");
		System.out.println("Test Descpription: "+result.getMethod().getDescription());
		TestLinkUtility.updateResult(result);
		Log.info("####### End of Test Case: " + _session.get_testCaseName() +" #######");
		ui_closeDriver();
	}

	@BeforeSuite
	protected void setUp(ITestContext test) {

	}
	@AfterTest
	public void tearDown() {
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(WebElement element, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ui_selectValueFromDropDownByXPath(List<WebElement> selectRegistry, String value) {
		// TODO Auto-generated method stub
		
	}

	



}
