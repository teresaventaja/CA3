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
    private boolean exitApplication = false; // To be used to exit loops
    private boolean showLoginMenu = true; // For the interaction of user menu with login menu

    // The arraylist storing users is in "UserManager", so we are calling it here
    
    public ConsoleMenu(UserManager userManager) {
        this.userManager = userManager;
        this.scanner = new Scanner(System.in);
    }
    
    // To use to show user menu under certain conditions (used in lecturer class)
    
    public void setShowLoginMenu(boolean showLoginMenu) {
        this.showLoginMenu = showLoginMenu;
    }
    
    // Use to call an exit from the menu (exit loop)
    
    public void exitApplication() {
    this.exitApplication = true;
    scanner.close(); // Close scanner upon exit from application
    }

    // Prior to login - shows the options of login as different users
    
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
        scanner.nextLine(); // move to the next line

        switch (choice) {
            case 1:
                // Perform admin login
                performLogin("ADMIN");
                break;
            case 2:
                if (userManager.hasNonAdminUsers()) { // Option 2 displayed only if users other than Admin are present
                // Perform officer login
                    performLogin("OFFICER");
                } else {
                    exitApplication(); // Option 2 if only Admin is present (no other users) - Exit application
                }
                break;
            case 3:
                // Perform lecturer login
                if (userManager.hasNonAdminUsers()) { // Option 3 displayed only if users other than Admin are present
                    performLogin("LECTURER");
                } else {
                    System.out.println("Invalid choice."); // Option 3 if only Admin is present (no other users) - invalid cause there are only 2
                }
                break;
            case 4:
                // Exit application
                if (userManager.hasNonAdminUsers()) { // Option 4 displayed only if users other than Admin are present
                    exitApplication();
                } else {
                    System.out.println("Invalid choice."); //  Option 4 if only Admin is present (no other users) - invalid cause there are only 2
                }
                break;
            default:
                System.out.println("Invalid choice."); // Incorrect selection
                break;
        }
    }

    // To use to display the login menu while option "exit application" is not set as true
    
    public void displayMenu() {
        while (!exitApplication) {
            if (showLoginMenu) {
                showLoginMenu();
            }
        }
    }
    
    // Login menu
    
    private void performLogin(String role) {

    System.out.println("Enter username:");
    String username = scanner.nextLine();
    System.out.println("Enter password:");
    String password = scanner.nextLine();

    // Handdle login and ensuring they will be connected with the options for each specific role
        
    Optional<User> userOptional = userManager.getUser(username, password);  
    
    if (userOptional.isPresent()) {
        User user = userOptional.get(); // connect with user array in UserManager class so that we retrieve the user details
        
        
        if (user.getRole().equals(role)) {
            System.out.println("Login successful.");

            // Handle specific role actions
            
            if (user instanceof Officer) { // Officer login
                ((Officer) user).handleOfficerActions(scanner, userManager, this);
            } else if (user instanceof Admin) { // Admin login
                ((Admin) user).handleAdminActions(scanner, userManager, this);
            } else if (user instanceof Lecturer) { // Lecturer login
                ((Lecturer) user).handleLecturerActions(scanner, userManager, this);
            } 
            setShowLoginMenu(true); // Present login options
        } else {
            System.out.println("Login failed. Role does not match."); // Not presenting login options cause the role doesn't match
        }
        
    } else {
        System.out.println("User not found or password incorrect."); // if UserManager arraylist didn't find the user we are looking for
    }
    }

}