/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Admin extends User {
    private Scanner scanner;
    private ManageUsers manageUsers;
    public static String adminUsername;
    public static String adminPassword;

    public Admin(String username, String password, ManageUsers manageUsers) {
        super(username, password, "admin");
        Admin.adminUsername = username;
        Admin.adminPassword = password;
        this.manageUsers = manageUsers;
        this.scanner = new Scanner(System.in);
    }
    
        public static void setInitialCredentials(String username, String password) {
        Admin.adminUsername = username;
        Admin.adminPassword = password;
    }

    // add a new user
public void addUser() {
    scanner = new Scanner(System.in); 
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    System.out.print("Enter role (Admin/Officer/Lecturer): ");
    String role = scanner.nextLine();

    User newUser = null;
    switch (role.toLowerCase()) {
        case "Admin":
            System.out.println("Creating new admins is not supported through this method.");
            break;
        case "Officer":
            newUser = new Officer(username, password);
            break;
        case "Lecturer":
            newUser = new Lecturer(username, password);
            break;
        default:
            System.out.println("Invalid role. Make sure the first letter is capitalized.");
            return; // Exit the method if the role is invalid
    }

    if (newUser != null) {
        manageUsers.addUser(newUser);
        System.out.println("User added successfully.");
    }
}


public void changeUserCredentials() {
    
    System.out.println("Change User Credentials");
    
    // Offer a choice between updating admin's own credentials or another user's
    System.out.println("1. Update my (admin) credentials");
    System.out.println("2. Update another user's credentials");
    System.out.print("Select an option: ");
 
    
    int choice = 0;
    try {
        choice = scanner.nextInt();
    } catch (InputMismatchException e) {
        scanner.nextLine(); // Clear the buffer of the wrong input
        System.out.println("Please enter a valid number for your choice.");
        return; // Exit the method to prevent further execution
    }
    scanner.nextLine(); // Always consume the newline character
    
    if (choice == 1) {
        // Directly changing the admin's credentials
        System.out.print("Enter new username for admin: ");
        Admin.adminUsername = scanner.nextLine();
        System.out.print("Enter new password for admin: ");
        Admin.adminPassword = scanner.nextLine();
                
        System.out.println("Admin credentials updated successfully.");
    } else if (choice == 2) {
        System.out.print("Enter the username of the user to update: ");
        String currentUsername = scanner.nextLine();
        
        // Proceed to update another user's credentials
        User userToUpdate = manageUsers.findUserByUsername(currentUsername);

        if (userToUpdate != null) {
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();

            // Update user credentials
            userToUpdate.changeUsername(newUsername);
            userToUpdate.changePassword(newPassword);

            System.out.println("User credentials updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    } else {
        System.out.println("Invalid choice.");
    }
}

public void modifyUser() {
    System.out.println("Modify User");

    System.out.print("Enter the current username of the user to modify: ");
    String currentUsername = scanner.nextLine();

    // Check if the user to modify is the admin
    if (Admin.adminUsername.equals(currentUsername)) {
        // Handle admin credentials update
        System.out.print("Enter new username for admin: ");
        String newAdminUsername = scanner.nextLine();
        System.out.print("Enter new password for admin: ");
        String newAdminPassword = scanner.nextLine();

        if (!newAdminUsername.isEmpty() || !newAdminPassword.isEmpty()) {
            Admin.adminUsername = !newAdminUsername.isEmpty() ? newAdminUsername : Admin.adminUsername;
            Admin.adminPassword = !newAdminPassword.isEmpty() ? newAdminPassword : Admin.adminPassword;
            System.out.println("Admin credentials updated successfully.");
        } else {
            System.out.println("No changes detected for admin credentials.");
        }
    } else {
        // Proceed with non-admin users
        User userToModify = manageUsers.findUserByUsername(currentUsername);
        if (userToModify != null) {
            System.out.print("Enter new username (press Enter to keep current): ");
            String newUsername = scanner.nextLine().trim();
            System.out.print("Enter new password (press Enter to keep current): ");
            String newPassword = scanner.nextLine().trim();

            // Assuming role change is not applicable for admin, as admin is a singleton in this context
            if (!newUsername.isEmpty() || !newPassword.isEmpty()) {
                manageUsers.deleteUser(currentUsername); // Remove the old user entry first
                User newUser;
                if (userToModify instanceof Officer) {
                    newUser = new Officer(newUsername.isEmpty() ? userToModify.getUsername() : newUsername, newPassword.isEmpty() ? userToModify.getPassword() : newPassword);
                } else if (userToModify instanceof Lecturer) {
                    newUser = new Lecturer(newUsername.isEmpty() ? userToModify.getUsername() : newUsername, newPassword.isEmpty() ? userToModify.getPassword() : newPassword);
                } else {
                    System.out.println("Error: Unrecognized user type.");
                    return;
                }
                manageUsers.addUser(newUser);
                System.out.println("User modified successfully.");
            } else {
                System.out.println("No changes detected. User not modified.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}


    // delete an user
   public void deleteUser() {
    System.out.println("Delete User");

    System.out.print("Enter the username of the user to delete: ");
    String username = scanner.nextLine();

    // Check if the user exists before attempting to delete
    User userToDelete = manageUsers.findUserByUsername(username);
    
    if (userToDelete != null) {
        manageUsers.deleteUser(username);
        System.out.println("User '" + username + "' has been deleted successfully.");
    } else {
        System.out.println("User not found. No user has been deleted.");
    }
}


}
