/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */

public class ManageUsers {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username)); // delete user by username
    }

    // method to modify user
    
    public void modifyUser(String username, String newUsername, String newPassword, String newRole) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.changeUsername(newUsername);
                user.changePassword(newPassword);
                user.setRole(newRole);
                break; // Exit the loop once the user is updated
            } else {
                System.out.println("User could not be modified because it was not found");
            }
        }
    }

    // Method to find a user by username
    
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }
}

