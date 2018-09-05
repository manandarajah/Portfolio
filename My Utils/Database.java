import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;

public abstract class Database implements AutoCloseable {
    
    private Connection CONN;
    private ResultSet rs = null;
    private boolean SecureConn = true;
    
    protected void initConnectionMySQL(String CONN_URL, String USERNAME, String PASSWORD) throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl(CONN_URL);
        bds.setUsername(USERNAME);
        bds.setPassword(PASSWORD);
        CONN = bds.getConnection();
    }
    
    protected void initConnectionPostgreSQL(String HOST, String DATA_BASE, String USERNAME, String PASSWORD) {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("A Data Source");
        source.setServerName(HOST);
        source.setDatabaseName(DATA_BASE);
        source.setUser(USERNAME);
        source.setPassword(PASSWORD);
        source.setMaxConnections(10);
        CONN = bds.getConnection();
    }
    
    protected void initConnection(String CONN_URL, String USERNAME, String PASSWORD) {
        try {
            CONN = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
            secureConn = false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean executeStatement(String sql, Object[] list) throws SQLException {
        
        try (PreparedStatement stmt = CONN.prepareStatement(sql)) {
            
            if (sql.contains("?")) {
                int count = 0;
                
                for (Object o : list) {
                    count++;
                    stmt.setObject(count, o);
                }
            }
            
            if (sql.contains("insert") 
                    || sql.contains("INSERT")
                    || sql.contains("delete")
                    || sql.contains("DELETE")
                    || sql.contains("update")
                    || sql.contains("UPDATE"))
                stmt.executeUpdate();
                return true;
            else if (sql.contains("select") || sql.contains("SELECT")) {
                if (secureConn) {
                    rs = stmt.executeQuery();
                }
                
                else {
                    Statement stmt2 = CONN.createStatement();
                    rs = stmt2.executeQuery(sql);
                }
                return true;
            }
        }
        return false;
    }
    
    public ResultSet getResultSet() {
        return this.rs;   
    }

    @Override
    public void close() throws Exception {
        this.CONN.close();
    }
}
