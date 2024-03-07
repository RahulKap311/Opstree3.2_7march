package com.buildpiper.listeners;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	int counter = 0;
	
	

	/**
	 * @author: smehta 
	 * 
	 * This method checks the number of times a test needs to be retry.
	 * TestNg will call this method every time a test fails.
	 * 
	 * Note: This method will return true if a tests needs to be retried
	 * and false it not
	 *
	 */
	@Override
	public boolean retry(ITestResult result) {// check if the test method had RetryCountIfFailed annotation
		RetryCountIfFailed annotation = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(RetryCountIfFailed.class);
		// based on the value of annotation see if test needs to be rerun
		if((annotation != null) && (counter < annotation.value()))
		{
			counter++;
			return true;
		}
		return false;
	}
}