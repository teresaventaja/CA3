/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

/**
 *
 * @author User
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String role;

    // Constructor for users
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Abstract method to reuse in different type of users
    
    public abstract void showOptions();

    // Getters and setters
    
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
