/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
public class OutputLecturer {
    
    // Connection to database
    
    String url = "jdbc:mysql://localhost:3306/ca3_2";
    String user = "root";
    String password = "root24";
    
    // Method to output headings in the console
    
    public static void consoleHeadings(ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnLabel(i) + (i == columnCount ? "" : " - "));
        }
        System.out.println(); // Move to the next line to prevent errors
    }

    // Method to output rows in the console
    
    public static void consoleRowsLecturer(List<LecturerReportConstructor> lecturers) {
        for (LecturerReportConstructor lecturer : lecturers) {
            System.out.println(lecturer.getLecturer() + " - " + lecturer.getRole() + " - " + lecturer.getModulesTeaching() + " - " + lecturer.getNumberOfStudents() + " - " + lecturer.getModulesCanTeach());
        }
    }
    
    // Output method to call in Main - Print in the console
    
    public static void consoleOutput(String url, String user, String password) {
    
    List<LecturerReportConstructor> lecturers = LecturerReportVariables.fetchLecturerInfo(url, user, password);
        
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(LecturerReportVariables.sql);
         ResultSet rs = pstmt.executeQuery()) {

        ResultSetMetaData metaData = rs.getMetaData();
        consoleHeadings(metaData); // Print column headings
        consoleRowsLecturer(lecturers); // Print rows
    } catch (SQLException e) {
        System.err.println("SQL error.");
        e.printStackTrace();
    }
    }
    
    // Print headings in a file
    
    public static void printHeadingsToFile(ResultSetMetaData metaData, PrintWriter writer) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            writer.print(metaData.getColumnLabel(i) + (i == columnCount ? "" : " - "));
        }
        writer.println(); // Move to the next line to prevent errorsn
    }

    // Print rows in a file
    
    public static void printRowsToFile(List<LecturerReportConstructor> lecturers, PrintWriter writer) {
        for (LecturerReportConstructor lecturer : lecturers) {
            writer.println(lecturer.getLecturer() + " - " + lecturer.getRole() + " - " + lecturer.getModulesTeaching() + " - " + lecturer.getNumberOfStudents() + " - " + lecturer.getModulesCanTeach());
        }
    }

    // Output method to call in Main - report in a .txt file
    
    public static void outputToFile(String url, String user, String password, String filePath) {

        List<LecturerReportConstructor> lecturers = LecturerReportVariables.fetchLecturerInfo(url, user, password);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(LecturerReportVariables.sql); 
             ResultSet rs = pstmt.executeQuery();
             PrintWriter writer = new PrintWriter(filePath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            printHeadingsToFile(metaData, writer); // Column headings to file
            printRowsToFile(lecturers, writer); // Rows information to file
            
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File not found error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Headings to CSV
    
    public static void printHeadingsToCSV(ResultSetMetaData metaData, PrintWriter writer) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            writer.print("\"" + metaData.getColumnLabel(i) + "\"" + (i == columnCount ? "" : ","));
        }
        writer.println(); // Move to the next line to prevent errors
    }

    // Rows to CSV
    
    public static void printRowsToCSV(List<LecturerReportConstructor> lecturers, PrintWriter writer) {
        for (LecturerReportConstructor lecturer : lecturers) {
            writer.println("\"" + lecturer.getLecturer() + "\",\"" + lecturer.getRole() + "\",\"" + lecturer.getModulesTeaching() + "\",\"" + lecturer.getNumberOfStudents() + "\",\"" + lecturer.getModulesCanTeach() + "\"");
        }
    }

    // Output method to call in Main - report in CSV format
    
    public static void outputToCSV(String url, String user, String password, String csvPath) {
        List<LecturerReportConstructor> lecturers = LecturerReportVariables.fetchLecturerInfo(url, user, password);

        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(LecturerReportVariables.sql);
            ResultSet rs = pstmt.executeQuery();
            PrintWriter writer = new PrintWriter(csvPath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            printHeadingsToCSV(metaData, writer); // Column headings to CSV
            printRowsToCSV(lecturers, writer); // Rows information to CSV
            
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File not found error: " + e.getMessage());
            e.printStackTrace();
        }
}
    
}
    
