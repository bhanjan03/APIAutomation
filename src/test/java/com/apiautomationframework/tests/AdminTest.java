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

public class AdminTest {
	String testName=null;
	String authToken=null;
	
	@BeforeTest
	public void setUP() {
		initExtentReport("AdminTest");
	}
	
	@AfterTest
	public void setDown() {
		flushExtentReport("AdminTest");
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
	public void addUser() {
		Object obj="{\n"
				+ "  \"firstname\": \"Bilbo\",\n"
				+ "  \"lastname\": \"Baggins\",\n"
				+ "  \"username\": \"bilbob\",\n"
				+ "  \"password1\": \"S3l3ctS0methingStr0ng5AsP@ssword\",\n"
				+ "  \"password2\": \"S3l3ctS0methingStr0ng5AsP@ssword\"\n"
				+ "}";
		
		Response response = RestResource.post(Route.ADMIN+"/"+"addUser", authToken, obj);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        
        
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}
	
	@Test(priority = 3)
	public void changePassword() {
		Object obj="{\n"
				+ "  \"username\": \"jdoe\",\n"
				+ "  \"password1\": \"Th1s!sz3nu3Passv0rd\",\n"
				+ "  \"password2\": \"Th1s!sz3nu3Passv0rd\"\n"
				+ "}";
        Response response = RestResource.post(Route.ADMIN+"/"+"changePassword", authToken, obj);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}
	
	@Test(priority = 4)
	public void logOut() {
        Response response = RestResource.get(Route.LOGOUT);
		
		System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
        logResponseInReport("API RESPONSE BODY", response.prettyPrint());
        logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
	}

}
