package fxml;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.UserModelTable;
import mySQLConnection.InsertUpdateDelete;
import mySQLConnection.SelectData;

public class AdminHomeController implements Initializable{
    // -------------------- VARIABLES -------------------- \\
    @FXML
    private TextField searchText;
    @FXML
    private TableView<UserModelTable> userTable;
    @FXML
    private TableColumn<UserModelTable,String> name;
    @FXML
    private TableColumn<UserModelTable,String> email;
    @FXML
    private TableColumn<UserModelTable,String> address;
    @FXML
    private TableColumn<UserModelTable,String> status;

    ObservableList<UserModelTable> obList = FXCollections.observableArrayList();

    private SceneController sceneController = new SceneController();
    // -------------------- END OF VARIABLES -------------------- \\

    public void logOut(ActionEvent event) {
        int logOutChoice = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if(logOutChoice == 0) {
            sceneController.switchToLogin(event);
        }
    }

    public void clearSearch() {
        searchText.setText("");
        // Get all users from database
        ResultSet users = SelectData.getData("SELECT * FROM users");
        // Clear old results
        obList.clear();
        try {
        // Loop through the users and add them to array
        while (users.next()) {
            obList.add(new UserModelTable(users.getString("name"), users.getString("email"), users.getString("address"), users.getString("status")));
        }
        users.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // Set array to table
        userTable.setItems(obList);
    }

    public void insertUsersToTable() {
        // Get searchbar text
        String nameOrEmail = searchText.getText();
        // Get results from database that match the searchbar text
        ResultSet users = SelectData.getData("SELECT * FROM users WHERE name LIKE '%"+nameOrEmail+"%' OR email LIKE '%"+nameOrEmail+"%'");
        // Remove old items from the table
        obList.clear();
        try {
        // Loop through users that match and set them to array
        while (users.next()) {
            obList.add(new UserModelTable(users.getString("name"), users.getString("email"), users.getString("address"), users.getString("status")));
        }
        users.close();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    // Set user array to table
    userTable.setItems(obList);
    }

    public void changeStatusOfUser() {
        // Get email and status
        UserModelTable emailField = userTable.getSelectionModel().getSelectedItem();
        String email = emailField.getEmail();
        UserModelTable statusField = userTable.getSelectionModel().getSelectedItem();
        String status = statusField.getStatus();
        // Change status
        if(status.equals("true")) {
            status = "false";
        } else {
            status = "true";
        }
        // Update status on database
        try {
            int changeStatusValue = JOptionPane.showConfirmDialog(null, "Do you want to change status of "+email+"", "Change status", JOptionPane.YES_NO_OPTION);
            if(changeStatusValue == 0) {
                InsertUpdateDelete.setData("UPDATE users SET status='"+status+"' WHERE email='"+email+"'", "Status changed successfully!");
                insertUsersToTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get all users from database
        ResultSet users = SelectData.getData("SELECT * FROM users");
        try {
        // Loop through the users and add them to array
        while (users.next()) {
            obList.add(new UserModelTable(users.getString("name"), users.getString("email"), users.getString("address"), users.getString("status")));
        }
        users.close();
        } catch(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
        // Set array to table
        userTable.setItems(obList);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));  
    }
}
