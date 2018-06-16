/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anandarajah.mugiesshan.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Mugiesshan
 */
public class Database {
    
    private static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/midterm";
    private static final String USERNAME = "anandamu";
    private static final String PASSWORD = "xminibike6000";
    
    public static void insertIntoTable(String name, Date date) throws SQLException, Exception {
        
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            try (PreparedStatement stmt = con.prepareStatement("INSERT INTO birthday VALUES (?,?)")) {
                stmt.setString(1, name);
                stmt.setDate(2, new java.sql.Date(date.getTime()));

                int rows = stmt.executeUpdate();

                if (rows > 1) {
                    throw new Exception(stmt.executeUpdate() + " rows were entered instead of one");
                }
            }
            con.close();
        }
    }
    
    public static Collection<BirthdayInfo> getBirthdayInfo(Date date) throws SQLException {
        
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM birthday WHERE birthdate = ?")) {
                stmt.setDate(1, new java.sql.Date(date.getTime()));
                
                try (ResultSet rs = stmt.executeQuery()) {
                    ArrayList<BirthdayInfo> birthdayinfo = new ArrayList<>();

                    while (rs.next()) {
                        String name = rs.getString("person");
                        Date d = rs.getDate("birthdate");
                        birthdayinfo.add(new BirthdayInfo(name, d));
                    }
                    con.close();
                    return birthdayinfo;
                }
            }
        }
    }
}
