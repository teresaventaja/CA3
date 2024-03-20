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

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public abstract void showOptions();

    public void setUsername(String newUsername) {
        this.username = newUsername;
        System.out.println("Username successfully changed to: " + newUsername);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password successfully changed.");
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
