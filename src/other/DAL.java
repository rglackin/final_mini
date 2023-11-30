package other;
import java.sql.*;

public class DAL {
    static final String connectionString = "jdbc:mysql://localhost:3306/epic_db";
    static final String USER = "root";
    static final String PASS = "password";
   
    
    public static boolean InsertQuery(String query){
        try(Connection conn = DriverManager.getConnection(connectionString, USER, PASS);
        Statement stmt = conn.createStatement();){
            
            stmt.executeUpdate(query);
                
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //generic lambda function can return different data types depending on result set
    public interface ResultSetProcessor<T>{
         T process(ResultSet resultSet) throws SQLException;
    }
    public static <T> T SelectQuery(String query, ResultSetProcessor<T> processor){
        try(Connection conn = DriverManager.getConnection(connectionString, USER, PASS);
        Statement stmt = conn.createStatement();)
        {
            ResultSet rset = null;
            rset = stmt.executeQuery(query);
            //result set is processed in calling function
            
            return processor.process(rset);    
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
    } 
    
}
