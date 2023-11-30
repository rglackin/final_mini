package other;
import java.sql.*;



public class DAL {
    static final String connectionString = "jdbc:mysql://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "password";
    public static void Test() {
        try(Connection conn = DriverManager.getConnection(connectionString, USER, PASS);
        Statement stmt = conn.createStatement();){
            String sql = "";
            stmt.executeQuery(sql);
                
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
}
