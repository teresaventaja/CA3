/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */

public class UserManager {
    private List<User> users = new ArrayList<>();

    public UserManager() {
        // Add a default admin user
       users.add(new Admin("admin", "java", this));
    }
    
    

    public void addUser(String username, String password, String role) {
        switch (role.toUpperCase()) {
            case "ADMIN":
                System.out.println("Admin creation is not allowed.");
                break;
            case "OFFICER":
                users.add(new Officer(username, password));
                break;
            case "LECTURER":
                users.add(new Lecturer(username, password));
                break;
            default:
                System.out.println("Invalid role.");
        }
    }

    
    public void deleteUser(String username) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(username)) {
                iterator.remove();
                System.out.println("User " + username + " deleted.");
                return;
            }
        }
        System.out.println("User not found.");
    }

 public boolean modifyUsername(String oldUsername, String newUsername) {
        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                return true;
            }
        }
        return false;
    }

    public boolean modifyPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public Optional<User> getUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.password.equals(password))
                .findFirst();
    }

    public boolean hasNonAdminUsers() {
        return users.stream().anyMatch(user -> !(user instanceof Admin));
    }

    public void displayUsers() {
    if (users.isEmpty()) {
        System.out.println("No users found.");
        return;
    }

    System.out.println("Existing users:");
    for (User user : users) {
        System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole());
    }
}
}