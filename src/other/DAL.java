package other;
import java.sql.*;


public class DAL {
    static final String connectionString = "jdbc:sqlite:epic_db.db";
    
    public static void Test() {
        Class.forName("org.sqlite.JDBC");
        try (Connection connection = DriverManager.getConnection(connectionString);
                Statement statement = connection.createStatement()) {
                    PreparedStatement selectStatement = connection.prepareStatement("select * from users");
            ResultSet rs = selectStatement.executeQuery();

            //List<User> users = new ArrayList<>();

            while (rs.next()) { // will traverse through all rows
                Integer id = rs.getInt("id");
                String username = rs.getString("first_name");
                String password = rs.getString("last_name");
                System.out.printf("%s\n%s\n%s",id,username,password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
