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

import io.restassured.response.Response;

public class AccountTest {
	String testName=null;
	String authToken=null;
	
	@BeforeTest
	public void setUP() {
		initExtentReport("AccountTest");
	}
	
	@AfterTest
	public void setDown() {
		flushExtentReport("AccountTest");
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
	public void getAccount() {
        Response response = RestResource.get(Route.ACCOUNT, authToken);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}
	
	@Test(priority = 3)
	public void getAccountDetails() {
        Response response = RestResource.get(Route.ACCOUNT+"/"+"80003", authToken);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}
	
	@Test(priority = 4)
	public void getAccountTransactionDetails() {
      Response response = RestResource.get(Route.ACCOUNT+"/"+"80003"+"/transactions", authToken);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}
    
	@Test(priority = 5)
	public void getTransactionDetailsBetweenDateRange() {
		Object obj="{\n"
				+ "  \"startDate\": \"2024-03-12\",\n"
				+ "  \"endDate\": \"2024-06-17\"\n"
				+ "}";
		logRequestInReport(obj.toString());
		
		Response response = RestResource.post(Route.ACCOUNT+"/"+"80003"+"/transactions", authToken, obj);
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}
}
