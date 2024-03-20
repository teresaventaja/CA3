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

    public Admin(String username, String password) {
        super(username, password, "ADMIN");
    }

    @Override
    public void showOptions() {
        System.out.println("ADMIN MENU");
        System.out.println("1. Add user");
        System.out.println("2. Change username");
        System.out.println("3. Change password");
        System.out.println("4. Back to login (login as admin, officer or lecturer)");
        System.out.println("5. Modify user");
        System.out.println("6. Delete user");
    }
    
    public void processAdminCommand(int choice, Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
    switch (choice) {
        case 1:
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Enter role (OFFICER/LECTURER):");
            String role = scanner.nextLine();
            userManager.addUser(username, password, role);
            break;
        case 2:
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
        case 3:
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
        case 4:
            // Logout logic
            consoleMenu.setShowLoginMenu(true); // This will allow the login menu to be displayed again
            System.out.println("Logged out successfully.");
            break;
        case 5:
            modifyUser(scanner, userManager);
            break;
        case 6:
            deleteUser(scanner, userManager);
            break;
        default:
            System.out.println("Invalid option.");
    }
}
    
     private void modifyUser(Scanner scanner, UserManager userManager) {
     System.out.println("Enter the username of the user you want to modify:");
    String oldUsername = scanner.nextLine();

    System.out.println("Enter the new username for the user (press Enter to skip):");
    String newUsername = scanner.nextLine();

    System.out.println("Enter the new password for the user (press Enter to skip):");
    String newPassword = scanner.nextLine();

    boolean isUpdated = false;

    // Update the username if a new username is provided
    if (!newUsername.isEmpty()) {
        boolean usernameUpdated = userManager.modifyUsername(oldUsername, newUsername);
        if (usernameUpdated) {
            System.out.println("Username updated successfully.");
            isUpdated = true;
            oldUsername = newUsername; // Update reference for password change if needed
        } else {
            System.out.println("Failed to update username.");
        }
    }

    // Update the password if a new password is provided
    if (!newPassword.isEmpty()) {
        boolean passwordUpdated = userManager.modifyPassword(newUsername.isEmpty() ? oldUsername : newUsername, newPassword);
        if (passwordUpdated) {
            System.out.println("Password updated successfully.");
            isUpdated = true;
        } else {
            System.out.println("Failed to update password.");
        }
    }

    if (!isUpdated) {
        System.out.println("No updates made. Please provide at least a new username or a new password.");
    }
}
     
         private void deleteUser(Scanner scanner, UserManager userManager) {
        System.out.println("Enter the username of the user you want to delete:");
        String username = scanner.nextLine();
        userManager.deleteUser(username);
    }
         
         
    public void handleAdminActions(Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
    int choice;
    do {
        showOptions(); // Show admin menu options
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over
        processAdminCommand(choice, scanner, userManager, consoleMenu);
    } while (choice != 4); // logout
}
     
}
