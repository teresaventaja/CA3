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
    
    // Arraylist storing users
    
    private List<User> users = new ArrayList<>();

    // Adding our admin to the list
    
    public UserManager() {
       users.add(new Admin("admin", "java", this));
    }
    
    // To use to add users

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

    // To be used to delete users (admin option)
    
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

    // To be used to modify username (admin option)
    
    public boolean modifyUsername(String oldUsername, String newUsername) {
        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                return true;
            }
        }
        return false;
    }

    // To be used to modify password (admin option)
    
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

    // To isolate instances in which menu options must hide (for example, user logins other than admin)
    
    public boolean hasNonAdminUsers() {
        return users.stream().anyMatch(user -> !(user instanceof Admin));
    }

    // Handling invalid option to display users when none was created
    
    public void displayUsers() {
        
    // Handling invalid option to display users when none was created
        
    if (users.isEmpty()) {
        System.out.println("No users found.");
        return;
    }
    
    // Show existing users
    
    System.out.println("Existing users:");
    for (User user : users) {
        System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole());
    }
}
}