/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Mugiesshan
 */
public class LoginInfo {
    
    private String username, password, name;
    private boolean admin;
    
    public LoginInfo (String username, String password, boolean admin, String name) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.name = name;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public boolean getAdmin() {
        return this.admin;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
