package xyz.nonamed.gameclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import xyz.nonamed.gameclient.config.SoundParam;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class ClientApplication extends Application {

    public static Stage mainStage;
    public static AudioClip backgroundMusic;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("views/main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        backgroundMusic = new AudioClip(Objects.requireNonNull(this.getClass().getResource("music/what-is-love-piano.mp3")).toString());
        backgroundMusic.setVolume(SoundParam.BACKGROUND_VOLUME);
        backgroundMusic.setCycleCount(INDEFINITE);
        backgroundMusic.play();
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