package fxml;

import mySQLConnection.SelectData;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    // -------------------- VARIABLES -------------------- \\
    @FXML
    private TextField loginEmailField;
    @FXML
    private PasswordField loginPasswordField;

    private SceneController sceneController = new SceneController();
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
        sceneController.switchToAdminHome(event);
    }

    public void switchToHome(ActionEvent event) {
        sceneController.switchToHome(event);
    }
    
    public void switchToSignUp(ActionEvent event) {
        sceneController.switchToSignUp(event);
    }

    public void switchToForgotPassword(ActionEvent event) {
        sceneController.switchToForgotPassword(event);
    }
    // -------------------- END OF PAGE CHANGES -------------------- \\
}
