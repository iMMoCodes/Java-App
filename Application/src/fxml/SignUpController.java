package fxml;

import mySQLConnection.InsertUpdateDelete;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {
    // -------------------- VARIABLES -------------------- \\
    @FXML
    private ComboBox<String> securityComboBox;
    @FXML
    private Button signUpButton;
    @FXML
    private TextField signUpNameField;
    @FXML
    private TextField signUpEmailField;
    @FXML
    private PasswordField signUpPasswordField;
    @FXML
    private TextField signUpAnswerField;
    @FXML
    private TextField signUpAddressField;

    // Security question options
    private String[] securityChoices = {
        "What is the name of your first pet?",
        "What was your first car?",
        "What elementary school did you attend?",
        "What is the name of town where you were born?"
        };
    
        private SceneController sceneController = new SceneController();
    // -------------------- END OF VARIABLES -------------------- \\

    public void sendSignUpData(ActionEvent event) {
        // Get text from fields
        String name = signUpNameField.getText();
        String email = signUpEmailField.getText();
        String password = signUpPasswordField.getText();
        String securityQuestion = securityComboBox.getValue();
        String answer = signUpAnswerField.getText();
        String address = signUpAddressField.getText();
        // Check that all values are filled
        if(name.equals("") || email.equals("") || password.equals("") || answer.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields!");
        } else {
            // Insert data to database
            String query;
            query = "INSERT INTO users VALUES('"+name+"','"+email+"','"+password+"','"+securityQuestion+"','"+answer+"','"+address+"','false')";
            InsertUpdateDelete.setData(query, "Registered succesfully!");
        }
    }
    
    // Populate security question select box
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        securityComboBox.getItems().addAll(securityChoices);
    }

    // -------------------- PAGE CHANGES -------------------- \\
    public void switchToLogin(ActionEvent event) {
        sceneController.switchToLogin(event);
    }
    // -------------------- END OF PAGE CHANGES -------------------- \\
}
