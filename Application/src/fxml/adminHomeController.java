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
    }

    public void insertUsersToTable() {
        String nameOrEmail = searchText.getText();
        ResultSet users = SelectData.getData("SELECT * FROM users WHERE name LIKE '%"+nameOrEmail+"%' OR email LIKE '%"+nameOrEmail+"%'");
        obList.clear();
        try {
        while (users.next()) {
            obList.add(new UserModelTable(users.getString("name"), users.getString("email"), users.getString("address"), users.getString("status")));
        }
        users.close();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    userTable.setItems(obList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet users = SelectData.getData("SELECT * FROM users");
        try {
        while (users.next()) {
            obList.add(new UserModelTable(users.getString("name"), users.getString("email"), users.getString("address"), users.getString("status")));
        }
        users.close();
        } catch(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
        userTable.setItems(obList);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));  
    }
}
