package com.apiautomationframework.models.pojo;

/**
 * POJO class for Login API Request
 * Represents the JSON payload for login authentication
 */
public class LoginRequest {
    
    private String username;
    private String password;

    // Default constructor (required for JSON deserialization)
    public LoginRequest() {
    }

    // Parameterized constructor
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "LoginRequest{" +
               "username='" + username + '\'' +
               ", password='[HIDDEN]'" +  // Hide password in logs
               '}';
    }

    // equals and hashCode methods for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(username, password);
    }
} 