/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

/**
 *
 * @author User
 */
public class Lecturer extends User {
    public Lecturer(String username, String password) {
        super(username, password, "LECTURER");
    }

    @Override
    public void showOptions() {
        System.out.println("LECTURER MENU");
        System.out.println("1. Generate Lecturer Report");
        System.out.println("2. Change username");
        System.out.println("3. Change password");
        System.out.println("4. Logout");
    }
}
