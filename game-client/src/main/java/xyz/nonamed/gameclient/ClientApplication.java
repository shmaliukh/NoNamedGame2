package xyz.nonamed.gameclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("views/main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void changeScreen(String fxml, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setTitle(title);
            mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        launch();
    }

}