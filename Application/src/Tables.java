import java.sql.*;

import javax.swing.JOptionPane;

public class Tables {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE Users (name varchar(200),email varchar(200),password varchar(50),securityQuestion varchar(200),answer varchar(200),address varchar(200),status varchar(20))");
            JOptionPane.showMessageDialog(null, "Table created succesfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try {
                connection.close();
                statement.close();
            } catch (Exception e) {
                
            }
        }
    }
}
