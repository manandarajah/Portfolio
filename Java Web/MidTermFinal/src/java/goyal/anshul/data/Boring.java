/*
ANSHUL
MID TERM JAVA EXAM
17TH JULY

*/
package goyal.anshul.data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Boring {

  
     public  void insertQuery() throws SQLException {
                  
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/afternoonclass");
            String mysql="INSERT INTO LOVEDECLARATION (LOVER) VALUES(?)";
            PreparedStatement prepare =con.prepareStatement(mysql);
            
            prepare.setString(1,goyal.anshul.data.Database.getLove());
               
            prepare.executeUpdate();
              
           }
    public void output() throws SQLException{
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/afternoonclass");
        String mysql="SELECT count(LOVER) from LOVEDECLARATION Where LOVED=? ";
        PreparedStatement prepare=con.prepareStatement(mysql);
        prepare.setString(1,goyal.anshul.data.Database.getLover());
        ResultSet results = prepare.executeQuery();
         while(results.next()){
            String lover=results.getString("LOVER");
         
            String mysql="UPDATE (LOVER) from LOVEDECLARATION Where LOVED=? ";
         }
        
    }
    
}
