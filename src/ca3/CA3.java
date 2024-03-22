/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca3;

import static ca3.CourseReportVariables.fetchModuleInfo;
import static ca3.LecturerReportVariables.fetchLecturerInfo;
import static ca3.OutputLecturer.outputToFile;
import static ca3.OutputLecturer.consoleOutput;
import static ca3.OutputLecturer.outputToCSV;
import static ca3.OutputStudent.consoleStudent;
import static ca3.OutputStudent.studentToCSV;
import static ca3.OutputStudent.studentToFile;
import static ca3.StudentReportVariables.fetchStudentInfo;
import static ca3.OutputCourse.courseToCSV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public class CA3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    String url = "jdbc:mysql://localhost:3306/ca3_2";
    String user = "root";
    String password = "root24";
    String filePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\LecturerReport.txt";
    String csvPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\LecturerReport.csv";
    String studentFilePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\StudentReport.txt";
    String studentCSVPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\StudentReport.csv";
    String courseFilePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\CourseReport.txt";
    String courseCSVPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\CA3\\CourseReport.csv";
   
    // Test if it works
    
    UserManager userManager = new UserManager();
    ConsoleMenu consoleMenu = new ConsoleMenu(userManager);
        

     consoleMenu.displayMenu();

    } 
    
}
