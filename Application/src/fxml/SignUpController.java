package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class SignUpController implements Initializable {
    @FXML
    private ComboBox<String> securityComboBox;
    private String[] securityChoices = {
        "What is the name of your first pet?",
        "What was your first car?",
        "What elementary school did you attend?",
        "What is the name of town where you were born?"
        };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        securityComboBox.getItems().addAll(securityChoices);
    }
}
