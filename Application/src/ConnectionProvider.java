import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salesapp", System.getenv("mySQLusername"), System.getenv("mySQLpassword"));
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}
