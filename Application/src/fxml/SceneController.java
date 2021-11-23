package fxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // -------------------- LOGIN -------------------- \\
    public void switchToLogin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------- SIGN UP -------------------- \\
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

    // -------------------- FORGOT PASSWORD -------------------- \\
    public void switchToForgotPassword(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Reset Password");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------- HOME -------------------- \\
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

    // -------------------- ADMIN HOME -------------------- \\
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
}
