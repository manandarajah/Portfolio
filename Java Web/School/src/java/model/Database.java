/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mugiesshan
 */
public class Database implements AutoCloseable {
    private final static String CONNECTION = "jdbc:derby://localhost:1527/grades";
    private Connection con;
    
    public Database() throws SQLException {
        this.con = DriverManager.getConnection(CONNECTION);
    }

    @Override
    public void close() throws SQLException {
        this.con.close();
    }
    
    public void registerNewUser(User u) throws SQLException {
        String sql = "INSERT INTO gradeuser (student,password,teacher) VALUES (?,?,?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setBoolean(3, u.isTeacher());
            
            if (stmt.executeUpdate() != 1) {
                throw new SQLException("Could not insert new user");
            }
        }
    }
    
    public User logInUser(String username, String password) throws SQLException {
        String sql = "select * from gradeuser where student = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return new User(username, rs.getString("password"), rs.getBoolean("teacher"));
            }
        }
    }
}
