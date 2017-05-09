import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public abstract class Database implements AutoCloseable {
    
    private Connection CONN;
    
    protected void initConnection(String CONN_URL, String USERNAME, String PASSWORD) throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl(CONN_URL);
        bds.setUsername(USERNAME);
        bds.setPassword(PASSWORD);
        CONN = bds.getConnection();
    }
    
    public boolean executeStatement(String sql, Object[] list, ResultSet rs) throws SQLException {
        
        try (PreparedStatement stmt = CONN.prepareStatement(sql)) {
            
            if (sql.contains("?")) {
                int count = 0;
                
                for (Object o : list) {
                    count++;
                    stmt.setObject(count, o);
                }
            }
            rs = stmt.executeQuery();
            
            if (rs == null && (sql.contains("insert") 
                    || sql.contains("INSERT")
                    || sql.contains("delete")
                    || sql.contains("DELETE")))
                return true;
            
            else if (rs != null && (sql.contains("select") || sql.contains("SELECT")))
                return true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        this.CONN.close();
    }
}