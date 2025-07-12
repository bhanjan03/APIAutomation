package com.apiautomationframework.models.pojo;

public class User {
	private String username;
    private String password;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String username, String password) {
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
    
 // toString method for debugging
   /* @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               '}';
    }*/

}
