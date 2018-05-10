package com.qa.ExtentReportListener;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	
	public static ExtentReports getInstance(){ 
		
		if(extent == null){
			extent = new ExtentReports(System.getProperty("user.dir")+ "/test-output/ExtentAutomationReport.html",true);
			extent.addSystemInfo("Host Name", "Srinivas Rao HP");
		    extent.addSystemInfo("Environment", "QA");
		}
		return extent;
	}

}
