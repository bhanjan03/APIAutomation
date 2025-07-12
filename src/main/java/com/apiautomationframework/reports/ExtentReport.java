package com.apiautomationframework.reports;

import com.apiautomationframework.exceptions.ReportInitializationException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;
import static com.apiautomationframework.constants.FrameworkConstants.getExtentReportPath;


public final class ExtentReport {
	private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(getExtentReportPath());
	  private static ExtentReports extentReports;

	  /**
	   * This method is to initialize the Extent Report
	   */
	  public static void initExtentReport() {
	    try {
	      if (Objects.isNull(extentReports)) {
	        extentReports = new ExtentReports();
	        extentReports.attachReporter(extentSparkReporter);
	        InetAddress ip = InetAddress.getLocalHost();
	        String hostname = ip.getHostName();
	        extentReports.setSystemInfo("Host Name", hostname);
	        extentReports.setSystemInfo("Environment", "API Automation - RestAssured");
	        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
	        extentSparkReporter.config().setDocumentTitle("HTML Report");
	        extentSparkReporter.config().setReportName("API Automation Test");
	        extentSparkReporter.config().setTheme(Theme.DARK);
	      }
	    } catch (Exception e) {
	      throw new ReportInitializationException("Failed to initialize extent report - " + e.getMessage());
	    }
	  }

	  /**
	   * This method is to initialize the Extent Report with custom name
	   */
	  public static void initExtentReport(String testClassName) {
	    try {
	      if (Objects.isNull(extentReports)) {
	        extentReports = new ExtentReports();
	        String customReportPath = getCustomReportPath(testClassName);
	        ExtentSparkReporter customSparkReporter = new ExtentSparkReporter(customReportPath);
	        extentReports.attachReporter(customSparkReporter);
	        InetAddress ip = InetAddress.getLocalHost();
	        String hostname = ip.getHostName();
	        extentReports.setSystemInfo("Host Name", hostname);
	        extentReports.setSystemInfo("Environment", "API Automation - RestAssured");
	        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
	        extentReports.setSystemInfo("Test Class", testClassName);
	        customSparkReporter.config().setDocumentTitle(testClassName + " - HTML Report");
	        customSparkReporter.config().setReportName(testClassName + " - API Automation Test");
	        customSparkReporter.config().setTheme(Theme.DARK);
	      }
	    } catch (Exception e) {
	      throw new ReportInitializationException("Failed to initialize extent report for " + testClassName + " - " + e.getMessage());
	    }
	  }

	  private static String getCustomReportPath(String testClassName) {
	    String projectPath = System.getProperty("user.dir");
	    String extentReportPath = projectPath + File.separator + "extent-test-report";
	    return extentReportPath + File.separator + testClassName + File.separator + "index.html";
	  }

	  public static void createTest(String testCaseName) {
	    ExtentManager.setExtentTest(extentReports.createTest(testCaseName));
	  }

	  public static void flushExtentReport() {
	    if (Objects.nonNull(extentReports)) {
	      extentReports.flush();
	    }
	    ExtentManager.unload();
	    try {
	      Desktop.getDesktop().browse(new File(getExtentReportPath()).toURI());
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  /**
	   * This method is to flush the Extent Report with custom path
	   */
	  public static void flushExtentReport(String testClassName) {
	    if (Objects.nonNull(extentReports)) {
	      extentReports.flush();
	    }
	    ExtentManager.unload();
	    try {
	      String customReportPath = getCustomReportPath(testClassName);
	      Desktop.getDesktop().browse(new File(customReportPath).toURI());
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  /**
	   * This method is to reset the ExtentReports instance for multiple reports
	   */
	  public static void resetExtentReports() {
	    extentReports = null;
	  }

}
