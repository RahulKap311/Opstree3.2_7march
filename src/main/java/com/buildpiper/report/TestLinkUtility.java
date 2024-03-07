package com.buildpiper.report;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.buildpiper.base.Capability;
import com.buildpiper.utils.Configuration;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;




public class TestLinkUtility extends Capability{

	public static Map<Integer, ExecutionStatus> testlinkStatus = new HashMap<Integer, ExecutionStatus>() {{
		put(Integer.valueOf(1), ExecutionStatus.PASSED);
		put(Integer.valueOf(2), ExecutionStatus.FAILED);
		put(Integer.valueOf(3), ExecutionStatus.NOT_RUN);
	}};

	public static void updateResult(ITestResult results) throws TestLinkAPIException {

		
		if(!"Yes".equalsIgnoreCase(Configuration.get("UpdateTestLink"))) {
			 Reporter.log(String.format("Result upload to testlink is disabled, Please ensure you have 'UpdateTestLink=Yes' in project.properties "),2,true);
			 return;
		}
		String project=Configuration.get("TestLink_Project");
		String testPlanName=Configuration.get("TestLink_Plan");
        String testCaseName=results.getName();
   
		
		TestLinkAPI api=null;
		try {
			api = new TestLinkAPI(new URL(Configuration.get("TESTLINK_URL")),Configuration.get("DEV_KEY"));
		} catch (TestLinkAPIException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TestProject projectDetails = isProjectExist(api,project);
		if(projectDetails==null) { 
			 Reporter.log(String.format("Unable to find any Project with name  %s , project is required before uploading result", project),2,true);
			 return;}
		
		TestPlan planDetails = isPlanExist(api,testPlanName,projectDetails.getId());
		if(planDetails==null) { 
			Reporter.log(String.format("Unable to find any Plan with name  %s in Project %s , Plan is required before uploading result", project,testPlanName),2,true);
			
			return;
			
		}


		TestCase testCaseDetails = isTestExistInPlan(api,planDetails.getId(),testCaseName);
		if(testCaseDetails==null) {
			Reporter.log(String.format("Unable to find any Test Case with name  %s in Plan %s ", testCaseName,testPlanName),2,true);
			testCaseDetails=createTestCase(api,testCaseName,projectDetails.getId(),planDetails.getId());
			}
		
		Build buildDetails = isBuildExist(api,planDetails.getId(),Configuration.get("executionid"));
		if(buildDetails==null) {
			Reporter.log(String.format("Creating Build %s ", Configuration.get("executionid")),2,true);
			buildDetails = api.createBuild(planDetails.getId(), Configuration.get("executionid") , "");
			}
		
	   Reporter.log(String.format("Logging result for test case  %s ", testCaseName),2,true);
	   api.reportTCResult(testCaseDetails.getId(), null, planDetails.getId(), testlinkStatus.get(results.getStatus()), null, buildDetails.getId(), buildDetails.getName(), null, null, null, testPlanName, null, testCaseName, null, null, null, null);


	}

	
	
	private static Build isBuildExist(TestLinkAPI api, Integer planId, String buildName) {
		// TODO Auto-generated method stub
		
		Build[] builds = api.getBuildsForTestPlan(planId);
		if(builds==null) {return null;}
		for (int i = 0; i < builds.length; i++) {
			if(buildName.equalsIgnoreCase(builds[i].getName())) {
				return builds[i];
			}	
		}
		Reporter.log(String.format("Unable to find any Build with name  %s", buildName),2,true);
		return null;
	}



	private static TestCase createTestCase(TestLinkAPI api,String testCaseName, Integer projectid, Integer planid) {
		// TODO Auto-generated method stub
		TestSuite suite = isSuiteExist(api,planid,"Automation_Suite");
		if(suite==null) {
			Reporter.log(String.format("No TestSuite like Automation_Suite exist for Project "),2,true);
			suite =api.createTestSuite(projectid, "Automation_Suite", "", null, 0, false,
					  ActionOnDuplicate.BLOCK); api.getProjects();
			Reporter.log(String.format("Created Suite 'Automation_Suite' inside project with id %s",projectid),2,true);
		}
		
		List<TestCaseStep> steps = new ArrayList<TestCaseStep>();
		TestCaseStep step = new TestCaseStep();
		step.setNumber(1);
		step.setExpectedResults(_session.get_testCaseName());
		step.setExecutionType(ExecutionType.MANUAL);
		step.setActions(_session.get_testCaseName());
		steps.add(step);

		
		TestCase tc = api.createTestCase(testCaseName, suite.getId(), projectid, "Automation_User", "summary", steps, "", null, null, null, null, null, true, ActionOnDuplicate.BLOCK);
		api.addTestCaseToTestPlan(projectid, planid, tc.getId(), 1, null, null, null);
		Reporter.log(String.format("Successfully created Test case: %s inside Suite %s", testCaseName,suite.getName()),2,true);
		return tc;
	}

	private static TestSuite isSuiteExist(TestLinkAPI api, Integer planId,String suiteName) {
		// TODO Auto-generated method stub
		
		TestSuite[] suites = api.getTestSuitesForTestPlan(planId);
		if(suites==null) {return null;}
		for (int i = 0; i < suites.length; i++) {
			if(suiteName.equalsIgnoreCase(suites[i].getName())) {
				return suites[i];
			}	
		}
		
		return null;
	}


	private static TestCase isTestExistInPlan(TestLinkAPI api, Integer planId,String testCaseName) {
		// TODO Auto-generated method stub

		TestSuite[] suites = api.getTestSuitesForTestPlan(planId);
		if(suites==null) {return null;}
		for (int i = 0; i < suites.length; i++) {
			TestCase[] testcases=api.getTestCasesForTestSuite(suites[i].getId(), true, TestCaseDetails.FULL);
			for (int j = 0; j < testcases.length; j++) {
				if(testCaseName.equalsIgnoreCase(testcases[j].getName())) {
					return testcases[j];
				}
			}
		}
		
		Reporter.log(String.format("No test with name %s exist in Plan",testCaseName),2,true);
		return null;
	}
	
	private static TestProject isProjectExist(TestLinkAPI api, String project) {
		// TODO Auto-generated method stub

		TestProject[] projects = api.getProjects();
		if(projects==null) {return null;}
		for (int i = 0; i < projects.length; i++) {
			if(project.equalsIgnoreCase(projects[i].getName())) {
				return projects[i];
			}
		}	
		Reporter.log(String.format("No project with name %s exist",project),2,true);
		return null;
	}

	private static TestPlan isPlanExist(TestLinkAPI api, String plan,Integer projectId) {
		// TODO Auto-generated method stub

		TestPlan[] plans = api.getProjectTestPlans(projectId);
		if(plans==null) {return null;}
		for (int i = 0; i < plans.length; i++) {
			if(plan.equalsIgnoreCase(plans[i].getName())) {
				return plans[i];
			}
		}	
		Reporter.log(String.format("No Plan with name %s exist",plan),2,true);
		return null;
	}






	public static void main(String args[]) throws TestLinkAPIException, MalformedURLException {}



	@Override
	public void ui_selectValueFromDropDownByXPath(WebElement element, String value) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ui_selectValueFromDropDownByXPath(List<WebElement> selectRegistry, String value) {
		// TODO Auto-generated method stub
		
	}
}