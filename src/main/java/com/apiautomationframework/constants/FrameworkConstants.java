package com.apiautomationframework.constants;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.apiautomationframework.config.ConfigFactory;



public final class FrameworkConstants {
	
	private FrameworkConstants() {
    }

	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String extentReportPath = PROJECT_PATH + File.separator + "extent-test-report";

	  public static String getExtentReportPath() {
	    return ConfigFactory.getConfig().override_reports().equalsIgnoreCase("yes") ?
	      extentReportPath + File.separator + "index.html" :
	      extentReportPath + File.separator + getCurrentDateTime() + File.separator + "index.html";
	  }

	  private static String getCurrentDateTime() {
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
	    return dateTimeFormatter.format(LocalDateTime.now());
	  }

}
