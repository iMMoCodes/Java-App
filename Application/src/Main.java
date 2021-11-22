import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Scene login = new Scene(root);
            Image icon = new Image("/images/iMMoCodes.png");
            stage.setResizable(false);
            stage.getIcons().add(icon);
            stage.setTitle("Login");
            stage.setScene(login);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args){
        launch(args);
    }

}
