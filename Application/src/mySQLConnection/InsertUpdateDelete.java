package mySQLConnection;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class InsertUpdateDelete {
    public static void setData(String query, String message) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            // If message is given, show it
            if(!message.equals("")) {
                JOptionPane.showMessageDialog(null, message);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            try {
                connection.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
