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
public class Officer extends User {
    
    String url = "jdbc:mysql://localhost:3306/ca3_2"; // Adjust these values accordingly
    String user = "root";
    String password = "root24";
    
    public Officer(String username, String password) {
        super(username, password, "OFFICER");
    }
        
    @Override
    public void showOptions() {
        System.out.println("OFFICER MENU");
        System.out.println("1. Generate Lecturer Report");
        System.out.println("2. Generate Student Report");
        System.out.println("3. Generate Course Report");
        System.out.println("4. Change username");
        System.out.println("5. Change password");
        System.out.println("6. Logout");
    }
    
    
    public void handleOfficerActions(Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
        int choice;
        do {
            showOptions();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left-over
            switch (choice) {
                case 1:
                    generateLecturerReport(scanner);
                    break;
                case 2:
                    generateStudentReport(scanner);
                    break;
                case 3:
                    generateCourseReport(scanner); 
                    break;
                case 4:
                    changeUsername(scanner, userManager);
                    break;
                case 5:
                    changePassword(scanner, userManager);
                    break;
                case 6:
                    consoleMenu.setShowLoginMenu(true);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (choice != 6);
    }


private void generateStudentReport(Scanner scanner) {
    System.out.println("Select the report format:");
    System.out.println("1. TXT format");
    System.out.println("2. CSV format");
    System.out.println("3. Console output");
    int formatChoice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline left-over

    String studentFilePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\StudentReport.txt";
    String studentCSVPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\StudentReport.csv";

    switch (formatChoice) {
        case 1:
            // Generate report in TXT format
            OutputStudent.studentToFile(url, user, password, studentFilePath);
            System.out.println("Student report generated in TXT format at " + studentFilePath);
            break;
        case 2:
            // Generate report in CSV format
            OutputStudent.studentToCSV(url, user, password, studentCSVPath);
            System.out.println("Student report generated in CSV format at " + studentCSVPath);
            break;
        case 3:
            // Generate report in the console
            OutputStudent.consoleStudent(url, user, password);
            break;
        default:
            System.out.println("Invalid option. Please select a valid format.");
            break;
    }
}

private void generateCourseReport(Scanner scanner) {
    System.out.println("Select the report format:");
    System.out.println("1. TXT format");
    System.out.println("2. CSV format");
    System.out.println("3. Console output");
    int formatChoice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline left-over

    String courseFilePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\CourseReport.txt";
    String courseCSVPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\CourseReport.csv";

    switch (formatChoice) {
        case 1:
            // Generate report in TXT format
            outputCourse.coursesToFile(url, user, password, courseFilePath);
            System.out.println("Report generated in TXT format.");
            break;
        case 2:
            // Generate report in CSV format
            outputCourse.courseToCSV(url, user, password, courseCSVPath);
            System.out.println("Report generated in CSV format.");
            break;
        case 3:
            // Generate report in the console
            outputCourse.consoleCourse(url, user, password);
            System.out.println("Report displayed in console.");
            break;
        default:
            System.out.println("Invalid option. Please select a valid format.");
            break;
    }
}

private void generateLecturerReport(Scanner scanner) {
    System.out.println("Select the report format:");
    System.out.println("1. TXT format");
    System.out.println("2. CSV format");
    System.out.println("3. Console output");
    int formatChoice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline left-over


    String filePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\LecturerReport.txt";
    String csvPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\LecturerReport.csv";

    switch (formatChoice) {
        case 1:
            // Generate report in TXT format
            OutputLecturer.outputToFile(url, user, password, filePath);
            System.out.println("Report generated in TXT format at " + filePath);
            break;
        case 2:
            // Generate report in CSV format
            OutputLecturer.outputToCSV(url, user, password, csvPath);
            System.out.println("Report generated in CSV format at " + csvPath);
            break;
        case 3:
            // Generate report in the console
            OutputLecturer.consoleOutput(url, user, password);
            break;
        default:
            System.out.println("Invalid option. Please select a valid format.");
            break;
    }
}

private void changeUsername(Scanner scanner, UserManager userManager) {
    String oldUsername = getUsername();
    System.out.println("Enter your new username:");
    String newUsername = scanner.nextLine();
    
    boolean success = userManager.modifyUsername(oldUsername, newUsername);
    if (success) {
        System.out.println("Username successfully changed to: " + newUsername);
        setUsername(newUsername); // Update the username in the current User object
    } else {
        System.out.println("Failed to change username.");
    }
}

private void changePassword(Scanner scanner, UserManager userManager) {
    String username = getUsername();
    System.out.println("Enter your new password:");
    String newPassword = scanner.nextLine();
    
    boolean success = userManager.modifyPassword(username, newPassword);
    if (success) {
        System.out.println("Password successfully changed.");
        setPassword(newPassword); // Update the password in the current User object
    } else {
        System.out.println("Failed to change password.");
    }
}
    }
