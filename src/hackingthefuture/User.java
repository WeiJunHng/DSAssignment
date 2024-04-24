/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hackingthefuture;

/**
 *
 * @author Asus
 */
public class User {
    protected String email, username, password, role, location;

    protected User(String email, String username, String password, String role, String location) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getLocation() {
        return location;
    }
    
    
}
