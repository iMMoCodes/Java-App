package fxml;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

public class adminHomeController {
    private SceneController sceneController = new SceneController();

    public void logOut(ActionEvent event) {
        int logOutChoice = JOptionPane.showConfirmDialog(null, "Do you really wanna logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if(logOutChoice == 0) {
            sceneController.switchToLogin(event);
        }
    }
}
