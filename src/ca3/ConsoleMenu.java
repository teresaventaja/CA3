/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ConsoleMenu {
    
    private UserManager userManager;
    private Scanner scanner;
    private boolean exitApplication = false;
    private boolean showLoginMenu = true; 

    public ConsoleMenu(UserManager userManager) {
        this.userManager = userManager;
        this.scanner = new Scanner(System.in);
    }
    
    public void setShowLoginMenu(boolean showLoginMenu) {
        this.showLoginMenu = showLoginMenu;
    }
    
    public void displayMenu() {
        while (!exitApplication) {
            if (showLoginMenu) {
                showLoginMenu();
            }
        }
    }

    public void showLoginMenu() {
        System.out.println("LOGIN MENU");
        if (userManager.hasNonAdminUsers()) {
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Officer");
            System.out.println("3. Login as Lecturer");
            System.out.println("4. Exit Application"); 
        } else {
            System.out.println("1. Login as Admin");
            System.out.println("2. Exit Application"); 
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                // Perform admin login
                performLogin("ADMIN");
                break;
                        case 2:
                if (userManager.hasNonAdminUsers()) {
                    // Perform officer login
                    performLogin("OFFICER");
                } else {
                    exitApplication(); // Directly exit if no non-admin users
                }
                break;
            case 3:
                // Perform lecturer login, if option 3 is valid
                if (userManager.hasNonAdminUsers()) {
                    performLogin("LECTURER");
                } else {
                    System.out.println("Invalid choice."); // Handle invalid choice differently if you wish
                }
                break;
            case 4:
                // Exit application, if option 4 is valid
                if (userManager.hasNonAdminUsers()) {
                    exitApplication();
                } else {
                    System.out.println("Invalid choice.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void performLogin(String role) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Optional<User> userOptional = userManager.getUser(username, password);

     if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (user.getRole().equals(role)) {
            if (user instanceof Officer) {
                ((Officer) user).handleOfficerActions(scanner, userManager, this);
            } else if (user instanceof Admin) {
                ((Admin) user).handleAdminActions(scanner, userManager, this);
            } else if (user instanceof Lecturer) {
                ((Lecturer) user).handleLecturerActions(scanner, userManager, this);
            } 
            setShowLoginMenu(true);
        } else {
            System.out.println("Login failed. Role does not match.");
        }
    } else {
        System.out.println("User not found or password incorrect.");
    }
}

    
        public void exitApplication() {
        this.exitApplication = true;
        scanner.close(); // Remember to close the scanner when the application exits
    }
}