package com.apiautomationframework.tests;

import static com.apiautomationframework.reports.ExtentLogger.logRequestInReport;
import static com.apiautomationframework.reports.ExtentLogger.logResponseInReport;
import static com.apiautomationframework.reports.ExtentReport.createTest;
import static com.apiautomationframework.reports.ExtentReport.flushExtentReport;
import static com.apiautomationframework.reports.ExtentReport.initExtentReport;
import static com.apiautomationframework.reports.ExtentReport.resetExtentReports;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apiautomationframework.config.ConfigFactory;
import com.apiautomationframework.models.builders.RestResource;
import com.apiautomationframework.models.builders.Route;
import com.apiautomationframework.models.pojo.LoginRequest;
import com.apiautomationframework.models.pojo.LoginResponse;
import com.apiautomationframework.models.pojo.User;

import io.restassured.response.Response;

public class LoginTest {
	
	String testName=null;
	String authToken=null;
	
	@BeforeTest
	public void setUP() {
		initExtentReport("LoginTest");
	}
	
	@AfterTest
	public void setDown() {
		flushExtentReport("LoginTest");
		resetExtentReports();
	}
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		testName=m.getName();
		createTest("STARTING TEST: " + testName);
	}
	
	@AfterMethod
	public void afterMethod() {
		
	}
	
	
	
	@Test(priority = 1)
	public void login() {
		String path="/login";
		
		LoginRequest loginRequest = new LoginRequest(ConfigFactory.getConfig().username(),ConfigFactory.getConfig().password());
		logRequestInReport(loginRequest.toString());
		
		Response response = RestResource.post(Route.LOGIN, loginRequest);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        
        
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println("Deserialized Response: " + loginResponse);
        
        authToken = loginResponse.getAuthorization();
        System.out.println("🔑 Authorization Token: " + authToken);
        
        
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
        logResponseInReport("DESERIALIZED RESPONSE", loginResponse.toString());
     
        if (loginResponse.getSuccess() != null && loginResponse.getSuccess().contains("logged in")) {
            System.out.println("✅ Login successful! User: " + loginResponse.getSuccess());
            logResponseInReport("VALIDATION", "Login successful - User is logged in");
        } else {
            System.out.println("❌ Login failed!");
            logResponseInReport("VALIDATION", "Login failed - User not logged in");
        }
	}
	
	@Test(priority = 2)
	public void getLogin() {
		
		String path="/login";
		
		Response response = RestResource.get(Route.LOGIN, authToken);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
        
		
	}
	
	

}
