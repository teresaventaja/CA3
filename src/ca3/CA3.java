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
import static ca3.outputCourse.courseToCSV;
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
     * Currently, there are three types of reports the college would
like to see, but their needs may change in the future (must support adding more reports)
○ A Course Report, that should contain:

Module	Programme	Number of students	Lecturer	Classroom
				Or “online”

○ A Student Report, that should contain:
Name and student number	Programme	Enrolled modules	Completed modules and grades	Modules to repeat
				

○ A Lecturer Report, that should contain:
Lecturer name	Lecturer role	Modules teaching this semester	Number of students taking modules	Type of classes they can teach (potential modules)
				

■ The types of classes they can teach (eg Java, Web Dev, Python, Maths etc)


● The user should have the option to have the reports in the following formats, with the ability
to add more in the future:
○ A txt file
○ A csv file
○ Output to the NetBeans Console
● A menu system in the NetBeans console, with appropriate and helpful messages.

FIRST LOGIN
Admin login (■ Username: admin
■ Password: java)
Add user (username, password, role)
Change username and password


WHEN USER CREATED
Login as admin
Add user
Modify user
Delete user
Change username and password

Login as office
Generate Course Report
Generate Student Report
Generate Lecturer Report
Change username and password

Login as lecturer
Generate Lecturer Report
Change username and password

     */
    
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
    
    /**
    List<CourseReportConstructor> modules = fetchModuleInfo(url, user, password);
    for (CourseReportConstructor module : modules) {
        System.out.println(module.getModuleName() + " - " + module.getProgramme() + " - " + module.getNumberOfStudents() + " - " + module.getLecturerName() + " - " + module.getClassroom());
    }
     
    List<StudentReportConstructor> students = fetchStudentInfo(url, user, password);
    for (StudentReportConstructor student : students) {
        System.out.println(student.getStudent() + " - " + student.getProgramme() + " - " + student.getEnrolledModules() + " - " + student.getCompletedModulesAndGrades() + " - " + student.getToRepeatModules());
    }
    */ 
    
    // Console output for lecturer report
    
   // consoleStudent(url, user, password);
    
    // File output for lecturer report
 // studentToFile(url, user, password, studentFilePath);
    
    // CSV output for lecturer report
  //courseToCSV(url, user, password, courseCSVPath);

        UserManager userManager = new UserManager();
       // ConsoleMenu consoleMenu = new ConsoleMenu(userManager);
        


      //  consoleMenu.displayMenu();
                userManager.displayUsers();
    }
   
    
}
