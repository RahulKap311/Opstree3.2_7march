package com.buildpiper.utils;

import java.util.Calendar;

import org.testng.Reporter;

public class ReportUtility {


	private static String reportName = "BuildPiper_TestAutomation_Report";

	private static String splitTimeAndMsg = "<===>";
	public static void log(String msg) {
		long timeMillis = Calendar.getInstance().getTimeInMillis();
		Reporter.log(timeMillis + splitTimeAndMsg + msg, true);
	}

	public static String getReportName() {
		return reportName;
	}

	public static String getSpiltTimeAndMsg() {
		return splitTimeAndMsg;
	}

	public static void setReportName(String reportName) {
		if(StringUtility.isNotEmpty(reportName)){
			ReportUtility.reportName = reportName;
		}
	}
}