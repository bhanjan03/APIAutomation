package com.apiautomationframework.utils;

/**
 * Utility class to manage authorization tokens across test classes
 */
public class AuthTokenManager {
    
    private static String currentAuthToken;
    private static String currentUsername;
    
    /**
     * Store the authorization token for current session
     */
    public static void setAuthToken(String username, String authToken) {
        currentUsername = username;
        currentAuthToken = authToken;
        System.out.println("🔐 Auth token stored for user: " + username);
    }
    
    /**
     * Get the current authorization token
     */
    public static String getAuthToken() {
        if (currentAuthToken == null || currentAuthToken.isEmpty()) {
            System.out.println("⚠️  No authorization token available. Please login first.");
            return null;
        }
        return currentAuthToken;
    }
    
    /**
     * Get the current username
     */
    public static String getCurrentUsername() {
        return currentUsername;
    }
    
    /**
     * Check if authorization token is available
     */
    public static boolean hasAuthToken() {
        return currentAuthToken != null && !currentAuthToken.isEmpty();
    }
    
    /**
     * Clear the stored authorization token
     */
    public static void clearAuthToken() {
        currentAuthToken = null;
        currentUsername = null;
        System.out.println("🧹 Auth token cleared");
    }
    
    /**
     * Get authorization header value for API calls
     */
    public static String getAuthHeader() {
        if (hasAuthToken()) {
            return "Bearer " + currentAuthToken;
        }
        return null;
    }
} 