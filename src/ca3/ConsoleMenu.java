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
public class ConsoleMenu {
    private Scanner scanner;
    private boolean officerExists = false; // Initially, no Officer exists
    private boolean lecturerExists = false; // Initially, no Lecturer exists
    private ManageUsers users; // may need to use ManageUsers as the array with users is there
    private Admin admin;
    
    public ConsoleMenu(ManageUsers users, Admin admin) {
        this.scanner = new Scanner(System.in);
        this.users = users;
        this.admin = admin;
    }
    
    // This method checks if officers and lecturers exist within the ManageUsers' users list
    public void checkRolesExistence() {
        boolean officerExists = users.getUsers().stream().anyMatch(user -> "Officer".equals(user.getRole()));
        boolean lecturerExists = users.getUsers().stream().anyMatch(user -> "Lecturer".equals(user.getRole()));
        
        displayLoginMenu(officerExists, lecturerExists);
    }
    
    private void verifyAdminLogin() {
    System.out.print("Enter admin username: ");
    String username = scanner.nextLine();
    System.out.print("Enter admin password: ");
    String password = scanner.nextLine();

    if (Admin.adminUsername.equals(username) && Admin.adminPassword.equals(password)) {
        displayAdminMenu();
    } else {
        System.out.println("Incorrect username or password.");
    }
    
}


    public void displayLoginMenu(boolean officerExists, boolean lecturerExists) {
        System.out.println("Login menu: ");
        System.out.println("1. Login as Admin: ");
        if (officerExists) {
            System.out.println("2. Login as Officer");
        }
        if (lecturerExists) {
            System.out.println("3. Login as Lecturer");
        }
        System.out.println("4. Exit");
        
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        
        switch (option) {
            case 1:
                verifyAdminLogin();
                break;
            case 2:
                if (officerExists) displayOfficerMenu();
                break;
            case 3:
                if (lecturerExists) displayLecturerMenu();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                displayLoginMenu(officerExists, lecturerExists);
        }
    }

    public void displayAdminMenu() {
    boolean inAdminMenu = true;
    while (inAdminMenu) {
        System.out.println("Admin Menu:");
        System.out.println("1. Add user");
        System.out.println("2. Change username and password");
        System.out.println("3. Logout");

        // Additional options if Officer and Lecturer exist
        if (officerExists && lecturerExists) {
            System.out.println("4. Modify user");
            System.out.println("5. Delete user");
            System.out.println("6. Back to the login menu");
        }

        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        
        switch (option) {
        case 1:
            admin.addUser();
            break;
        case 2:
            admin.changeUserCredentials();
            break;
        case 3:
            System.out.println("Log out successful.");
            inAdminMenu = false;
            break;
        case 4:
            admin.modifyUser();
            break;
        case 5:
            admin.deleteUser();
            break;
        case 6:
            inAdminMenu = false;
            break;
        default:
            System.out.println("Invalid option. Please try again.");
            displayAdminMenu();
            break;
        }
    }
    }

    public void displayOfficerMenu() {
        System.out.println("Officer Menu:");
        System.out.println("1. Generate Lecturer Report");
        System.out.println("2. Generate Student Report");
        System.out.println("3. Generate Course Report");
        System.out.println("4. Change username and password");
        System.out.println("5. Logout");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        // Placeholder for handling user's selection
    }

    public void displayLecturerMenu() {
        System.out.println("Lecturer Menu:");
        System.out.println("1. Generate Lecturer Report");
        System.out.println("2. Change username and password");
        System.out.println("3. Logout");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        // Placeholder for handling user's selection
    }


   
}

