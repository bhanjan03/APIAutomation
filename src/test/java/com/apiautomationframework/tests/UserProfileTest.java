package com.apiautomationframework.tests;

import com.apiautomationframework.models.builders.RestResource;
import com.apiautomationframework.utils.AuthTokenManager;

import io.restassured.response.Response;
import static com.apiautomationframework.reports.ExtentLogger.logRequestInReport;
import static com.apiautomationframework.reports.ExtentLogger.logResponseInReport;
import static com.apiautomationframework.reports.ExtentReport.createTest;
import static com.apiautomationframework.reports.ExtentReport.initExtentReport;
import static com.apiautomationframework.reports.ExtentReport.flushExtentReport;
import static com.apiautomationframework.reports.ExtentReport.resetExtentReports;

/**
 * Test class demonstrating how to use stored authorization token for authenticated API calls
 */
public class UserProfileTest {

    public static void main(String[] args) {
        // 1. Initialize ExtentReports
        initExtentReport("UserProfileTest");
        
        // 2. Create test
        createTest("User Profile API Test - Using Stored Auth Token");

        // 3. Check if we have a stored auth token
        if (!AuthTokenManager.hasAuthToken()) {
            System.out.println("❌ No authorization token available. Please run LoginTestWithPOJO first.");
            logResponseInReport("ERROR", "No authorization token available. Please login first.");
            flushExtentReport("UserProfileTest");
            resetExtentReports();
            return;
        }

        System.out.println("👤 Current user: " + AuthTokenManager.getCurrentUsername());
        System.out.println("🔑 Using stored auth token: " + AuthTokenManager.getAuthToken());
        
        // 4. Make authenticated API calls
        getUserProfile();
        getAccountBalance();
        
        // 5. Flush the report
        flushExtentReport("UserProfileTest");
        resetExtentReports();
    }
    
    /**
     * Get user profile information
     */
    private static void getUserProfile() {
        try {
            String profilePath = "https://demo.testfire.net/api/account";
            
            System.out.println("📋 Getting user profile...");
            logRequestInReport("GET USER PROFILE - " + profilePath);
            
            Response profileResponse = RestResource.get(profilePath, AuthTokenManager.getAuthToken());
            
            System.out.println("Profile Response Status: " + profileResponse.getStatusCode());
            System.out.println("Profile Response Body: " + profileResponse.prettyPrint());
            
            logResponseInReport("PROFILE RESPONSE STATUS", String.valueOf(profileResponse.getStatusCode()));
            logResponseInReport("PROFILE RESPONSE BODY", profileResponse.prettyPrint());
            
            if (profileResponse.getStatusCode() == 200) {
                System.out.println("✅ User profile retrieved successfully!");
                logResponseInReport("PROFILE VALIDATION", "Success - User profile retrieved");
            } else {
                System.out.println("❌ Failed to retrieve user profile!");
                logResponseInReport("PROFILE VALIDATION", "Failed - Could not retrieve user profile");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error getting user profile: " + e.getMessage());
            logResponseInReport("PROFILE ERROR", "Exception: " + e.getMessage());
        }
    }
    
    /**
     * Get account balance information
     */
    private static void getAccountBalance() {
        try {
            String balancePath = "https://demo.testfire.net/api/balance";
            
            System.out.println("💰 Getting account balance...");
            logRequestInReport("GET ACCOUNT BALANCE - " + balancePath);
            
            Response balanceResponse = RestResource.get(balancePath, AuthTokenManager.getAuthToken());
            
            System.out.println("Balance Response Status: " + balanceResponse.getStatusCode());
            System.out.println("Balance Response Body: " + balanceResponse.prettyPrint());
            
            logResponseInReport("BALANCE RESPONSE STATUS", String.valueOf(balanceResponse.getStatusCode()));
            logResponseInReport("BALANCE RESPONSE BODY", balanceResponse.prettyPrint());
            
            if (balanceResponse.getStatusCode() == 200) {
                System.out.println("✅ Account balance retrieved successfully!");
                logResponseInReport("BALANCE VALIDATION", "Success - Account balance retrieved");
            } else {
                System.out.println("❌ Failed to retrieve account balance!");
                logResponseInReport("BALANCE VALIDATION", "Failed - Could not retrieve account balance");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error getting account balance: " + e.getMessage());
            logResponseInReport("BALANCE ERROR", "Exception: " + e.getMessage());
        }
    }
} 