/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

import java.util.Scanner;


/**
 *
 * @author User
 */
public class Admin extends User {
    
    // Create an Admin user constructor

    private UserManager userManager; 
    public Admin(String username, String password, UserManager userManager) {
        super(username, password, "ADMIN");
        this.userManager = userManager; 
    }

    // Use abstract class from User to display menus for every user (Admin in this case)
    
        @Override
    public void showOptions() {
        System.out.println("ADMIN MENU");
        System.out.println("1. Add user");
        System.out.println("2. Change username");
        System.out.println("3. Change password");
        System.out.println("4. Back to login (login as admin, officer or lecturer)");
        if (userManager.hasNonAdminUsers()) { // Show only if there are users other than non admin
            System.out.println("5. Modify user");
            System.out.println("6. Delete user");
            System.out.println("7. View existing users");
        }
    }
    
    // Coding logic for every option
    
    public void processAdminCommand(int choice, Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
        
    if (choice >= 5 && choice <= 7 && !userManager.hasNonAdminUsers()) {
        System.out.println("Invalid selection"); // Guide the user that is used to hitting some options so that they pay more attention
        return;
    }
        
    switch (choice) {
        case 1: // Add user
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Enter role (OFFICER/LECTURER):");
            String role = scanner.nextLine();
            userManager.addUser(username, password, role);
            break;
        case 2: // Change username
            System.out.println("Enter old username:");
            String oldUsername = scanner.nextLine();
            System.out.println("Enter new username:");
            String newUsername = scanner.nextLine();
            if (userManager.modifyUsername(oldUsername, newUsername)) {
                System.out.println("Username successfully changed.");
            } else {
                System.out.println("Failed to change username.");
            }
            break;
        case 3: // Change password
            System.out.println("Enter username:");
            String usr = scanner.nextLine();
            System.out.println("Enter new password:");
            String newPassword = scanner.nextLine();
            if (userManager.modifyPassword(usr, newPassword)) {
                System.out.println("Password successfully changed.");
            } else {
                System.out.println("Failed to change password.");
            }
            break;
        case 4: // Logout and back to login menu
            consoleMenu.setShowLoginMenu(true);
            System.out.println("Logged out successfully.");
            break;
        case 5: // Modify user
            modifyUser(scanner, userManager);
            break;
        case 6: // Delete user
            deleteUser(scanner, userManager);
            break;
        case 7: // View existing users
            userManager.displayUsers();
            break;
        default:
            System.out.println("Invalid selection");
    }
}
    
    // Display menu unless the user logs out (goes back to login menu)
    
    public void handleAdminActions(Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
    int choice;
    do {
        showOptions(); // Show admin menu options
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume a new line
        processAdminCommand(choice, scanner, userManager, consoleMenu);
    } while (choice != 4);
}
    
    // Logic to modify users based on username
    
    private void modifyUser(Scanner scanner, UserManager userManager) {
        
    // Check if there are any non-admin users to exit this option
    
    if (!userManager.hasNonAdminUsers()) {
        System.out.println("No modifiable users found. Only the admin exists.");
        return; 
    }
    
    System.out.println("Enter the username of the user you want to modify:");
    String oldUsername = scanner.nextLine();

    
    // Check if the username to modify is not "admin" (admin must be there by default)
    
    if ("admin".equalsIgnoreCase(oldUsername)) {
        System.out.println("Modifying the admin user is not allowed through this option.");
        return;
    }
    
    System.out.println("Enter the new username for the user (press Enter to skip):");
    String newUsername = scanner.nextLine();

    System.out.println("Enter the new password for the user (press Enter to skip):");
    String newPassword = scanner.nextLine();

    boolean isUpdated = false;

    // Update the username if a new username is provided (handle when the user presses "Enter")
    
    if (!newUsername.isEmpty()) {
        boolean usernameUpdated = userManager.modifyUsername(oldUsername, newUsername);
        if (usernameUpdated) {
            System.out.println("Username updated successfully.");
            isUpdated = true;
            oldUsername = newUsername;
        } else {
            System.out.println("Failed to update username.");
        }
    }

    // Update the username if a new password is provided (handle when the user presses "Enter")
    
    if (!newPassword.isEmpty()) {
        boolean passwordUpdated = userManager.modifyPassword(newUsername.isEmpty() ? oldUsername : newUsername, newPassword);
        if (passwordUpdated) {
            System.out.println("Password updated successfully.");
            isUpdated = true;
        } else {
            System.out.println("Failed to update password.");
        }
    }

    // If no changes are made (user presses "Enter" instead of providing username or password)
    
    if (!isUpdated) {
        System.out.println("No updates made. Please provide at least a new username or a new password.");
    }
}
    
    // Logic to delete user based on username
    
    private void deleteUser(Scanner scanner, UserManager userManager) {
    System.out.println("Enter the username of the user you want to delete:");
    String username = scanner.nextLine();
    userManager.deleteUser(username);
    }      
     
}
