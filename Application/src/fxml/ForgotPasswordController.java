package fxml;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import mySQLConnection.InsertUpdateDelete;
import mySQLConnection.SelectData;

public class ForgotPasswordController {
    // -------------------- VARIABLES -------------------- \\
    @FXML
    private TextField forgotPasswordEmailField;
    @FXML
    private TextField forgotPasswordSecurityQuestion;
    @FXML
    private TextField forgotPasswordSecurityQuestionAnswer;
    @FXML
    private PasswordField forgotPasswordPasswordField;

    private String email;
    private SceneController sceneController = new SceneController();
    // -------------------- END OF VARIABLES -------------------- \\

    public void getSecurityQuestion(ActionEvent event) {
        // Check == 0 will show Email doesn't exist, Check == 1 will not show it
        int check = 0;
        email = forgotPasswordEmailField.getText();
        // Check if email field is empty
        if(email.equals("")) {
            check = 1;
            JOptionPane.showMessageDialog(null, "Email is required");
        }
        else {
            // Select user from database with email
            ResultSet userData = SelectData.getData("SELECT * FROM users WHERE email='"+email+"'");
            try {
                // If user exists with that email, get security question
                if(userData.next()) {
                    check = 1;
                    forgotPasswordSecurityQuestion.setEditable(false);
                    forgotPasswordEmailField.setEditable(false);
                    forgotPasswordSecurityQuestion.setText(userData.getString(4));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        // Show Email doesn't exists if check == 0
        if(check == 0) {
            JOptionPane.showMessageDialog(null, "Email doesn't exist!");
        }
    }

    public void updatePassword(ActionEvent event) {
        // Check == 0 will Incorrect answer, Check == 1 will not show it
        int check = 0;
        String securityQuestion = forgotPasswordSecurityQuestion.getText();
        String securityQuestionAnswer = forgotPasswordSecurityQuestionAnswer.getText();
        String newPassword = forgotPasswordPasswordField.getText();
        // Check if fields are empty
        if(securityQuestionAnswer.equals("") || newPassword.equals("")) {
            check = 1;
            JOptionPane.showMessageDialog(null, "Please fill all of the fields!");
        }
        else {
            // Get user from database that matches email, security question and answer
            ResultSet userData = SelectData.getData("SELECT * FROM users WHERE email='"+email+"' AND securityQuestion='"+securityQuestion+"' AND answer='"+securityQuestionAnswer+"'");
            try {
                // User exists -> Update password
                if(userData.next()) {
                    check = 1;
                    InsertUpdateDelete.setData("UPDATE users SET password='"+newPassword+"' WHERE email='"+email+"'", "Password updated succesfully!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        // Show Incorrect answer if check == 0
        if(check == 0) {
            JOptionPane.showMessageDialog(null, "Incorrect answer");
        }
    }

    // -------------------- PAGE CHANGES -------------------- \\
    public void switchToSignUp(ActionEvent event) {
        sceneController.switchToSignUp(event);
    }

    public void switchToLogin(ActionEvent event) {
        sceneController.switchToLogin(event);
    }
    // -------------------- END OF PAGE CHANGES -------------------- \\
}
