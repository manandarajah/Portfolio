/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mugiesshan
 */
public class Database {
    private static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/firstdb";
    
    /**
     * 
     * @param username
     * @param password
     * @return the LoginInfo Object
     * @throws SQLException 
     */
    public static LoginInfo getLoginInfo(String username, String password) throws SQLException {
        
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
            String query = "SELECT * FROM logininfo WHERE username = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, username);
                
                try (ResultSet results = stmt.executeQuery()) {

                    while (results.next()) {
                        String dbPassword = results.getString("password");

                        if (dbPassword.equals(password)) {
                            boolean admin = results.getBoolean("admin");
                            String name = results.getString("name");
                            LoginInfo login = new LoginInfo(username, password, admin, name);
                            return login;
                        }
                    }
                    return null;
                }
            }
        }
    }
    
    /**
     * Add a new user to the loginInfo database table.
     * 
     * @param username the new user's username
     * @param password the new user's password
     * @param isAdmin the user is an administrator
     * @param name the user's real name
     */
    public static void addUser(String username, String password, boolean isAdmin, String name) throws SQLException, InsertUnsuccessfulException {
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "INSERT INTO logininfo (username, password, admin, name) VALUES (?,?,?,?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setBoolean(3, isAdmin);
                stmt.setString(4, name);
                
                int numRows = stmt.executeUpdate();
                
                if (numRows != 1) {
                    throw new InsertUnsuccessfulException(numRows+" rows were inserted instead of 1");
                }
            }
        }
    }
}
