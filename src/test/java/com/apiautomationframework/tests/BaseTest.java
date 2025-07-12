package com.apiautomationframework.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {
	@BeforeSuite
	public void beforeSuite() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}
	
	@AfterSuite
	public void afterSuite() {
		
	}

}
