package fxml;

import mySQLConnection.SelectData;

import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    // -------------------- VARIABLES -------------------- \\
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField loginEmailField;
    @FXML
    private PasswordField loginPasswordField;
    // -------------------- END OF VARIABLES -------------------- \\

    public void sendLoginData(ActionEvent event) {
        // Check == 0 will show Incorrect email or password, Check == 1 will not show it
        int check = 0;
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();
        // Check if fields are empty
        if(email.equals("") || password.equals("")) {
            check = 1;
            JOptionPane.showMessageDialog(null, "Please provide all the fields!");
        }
        // Check if user is Admin and show admin homepage
        else if (email.equals("admin") && password.equals("admin")) {
            check = 1;
            switchToAdminHome(event);
        }
        else {
            // Select user from database
            ResultSet loggedInUser = SelectData.getData("SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"'");
            try {
                // If user exists
                if(loggedInUser.next()) {
                    check = 1;
                    // If user is active -> show home page
                    if(loggedInUser.getString(7).equals("true")) {
                        switchToHome(event);
                    }
                    // User is not active
                    else {
                        JOptionPane.showMessageDialog(null, "Wait for admin approval");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        // Show Incorrect Email or Password if check == 0
        if(check == 0) {
            JOptionPane.showMessageDialog(null, "Incorrect Email or Password!");
        }
    }

    // -------------------- PAGE CHANGES -------------------- \\
    public void switchToAdminHome(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("adminHome.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHome(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void switchToSignUp(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // -------------------- END OF PAGE CHANGES -------------------- \\
}
