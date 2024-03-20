/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

/**
 *
 * @author User
 */
public class Officer extends User {
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
    }
