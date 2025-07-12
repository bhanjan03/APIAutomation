package com.apiautomationframework.models.pojo;

/**
 * POJO class for Login API Response
 * Represents the JSON response from login authentication
 */
public class LoginResponse {
    
    private String Authorization;
    private String success;

    // Default constructor (required for JSON deserialization)
    public LoginResponse() {
    }

    // Parameterized constructor
    public LoginResponse(String Authorization, String success) {
        this.Authorization = Authorization;
        this.success = success;
    }

    // Getters and setters
    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String Authorization) {
        this.Authorization = Authorization;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "LoginResponse{" +
               "Authorization='" + Authorization + '\'' +
               ", success='" + success + '\'' +
               '}';
    }

    // equals and hashCode methods for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponse that = (LoginResponse) o;
        return java.util.Objects.equals(Authorization, that.Authorization) && 
               java.util.Objects.equals(success, that.success);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Authorization, success);
    }
} 