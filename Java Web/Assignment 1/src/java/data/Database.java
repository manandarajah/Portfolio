/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Mugiesshan
 */
//Database class that connects to the database (date value auto-generated)
public class Database {
    
    private static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/EHT";
    private static final String USERNAME = "anandamu";
    private static final String PASSWORD = "MAcstS814";
    
    //Updates row when food already in database
    public static boolean updateTable(String food, int servings) throws SQLException, InsertUnsuccessfulException {
        
        boolean foodInDatabase = false;
        int sqlservings = 0;
        
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            String selectFoodQuery = "SELECT description,servings FROM fooddiary";
            String updateServingsQuery = "UPDATE fooddiary SET servings = ? WHERE description = ?";
            String updateDateQuery = "UPDATE fooddiary SET fooddate = ? WHERE description = ?";
            
            try (PreparedStatement stmt = con.prepareStatement(selectFoodQuery)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        if (rs.getString("description").equals(food)) {
                            foodInDatabase = true;
                            sqlservings = rs.getInt("servings");
                            break;
                        }
                    }
                }
            }
            
            if (foodInDatabase) {
                sqlservings += servings;
                Date date = new Date();
                
                try (PreparedStatement stmt = con.prepareStatement(updateServingsQuery)) {
                    stmt.setInt(1, sqlservings);
                    stmt.setString(2, food);
                    
                    int numRows = stmt.executeUpdate();
                
                    if (numRows != 1) {
                        throw new InsertUnsuccessfulException (stmt.executeUpdate()+" rows were inserted instead of 1");
                    }
                }
                
                try (PreparedStatement stmt = con.prepareStatement(updateDateQuery)) {
                    stmt.setDate(1, new java.sql.Date(date.getTime()));
                    stmt.setString(2, food);
                    
                    int numRows = stmt.executeUpdate();
                
                    if (numRows != 1) {
                        throw new InsertUnsuccessfulException (stmt.executeUpdate()+" rows were inserted instead of 1");
                    }
                }
            }
            con.close();
        }
        return foodInDatabase;
    }
    
    //Inserts food item into database
    public static void insertIntoTable (String food, String foodgroup, int servings, boolean foodInDatabase) throws SQLException, InsertUnsuccessfulException {
        Date date = new Date();
        
        if (!foodInDatabase) {
            try (Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
                String insertQuery = "INSERT INTO fooddiary (FoodDate,Description,FoodGroup,Servings) VALUES (?,?,?,?)";

                try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
                    stmt.setDate(1, new java.sql.Date(date.getTime()));
                    stmt.setString(2, food);
                    stmt.setString(3, foodgroup);
                    stmt.setInt(4, servings);

                    int numRows = stmt.executeUpdate();

                    if (numRows != 1) {
                        throw new InsertUnsuccessfulException (stmt.executeUpdate()+" rows were inserted instead of 1");
                    }
                }
                con.close();
            }
        }
    }
    
    //Returns a collection of FoodInfo objects
    public static Collection<FoodInfo> getFoodInfo() throws SQLException {
        
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM fooddiary";
            
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet results = stmt.executeQuery()) {
                    ArrayList<FoodInfo> foodinfo = new ArrayList<>();
                    
                    while (results.next()) {
                        Date date = results.getDate("fooddate");
                        String food = results.getString("description");
                        String foodgroup = results.getString("foodgroup");
                        int servings = results.getInt("servings");
                        
                        foodinfo.add(new FoodInfo(date, food, foodgroup, servings));
                    }
                    con.close();
                    return foodinfo;
                }
            }
        }
    }
}
