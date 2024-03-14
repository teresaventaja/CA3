/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca3;

/**
 *
 * @author User
 */
public class Admin extends User {
    private ManageUsers manageUsers;

    public Admin(String username, String password, ManageUsers manageUsers) {
        super(username, password, "admin"); // call parent
        this.manageUsers = manageUsers; // use methods in ManageUaers
    }

    // add a new user
    public void addUser(User user) {
        manageUsers.addUser(user);
    }

    // delete an user
    public void deleteUser(String username) {
        manageUsers.deleteUser(username);
    }

    // modify user details
    public void modifyUser(String username, String newUsername, String newPassword, String newRole) {
        manageUsers.modifyUser(username, newUsername, newPassword, newRole);
    }
}
