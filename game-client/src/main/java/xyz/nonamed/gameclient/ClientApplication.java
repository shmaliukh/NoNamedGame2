package xyz.nonamed.gameclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import xyz.nonamed.gameclient.config.SoundParam;
import xyz.nonamed.gameclient.config.UserParam;

import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class ClientApplication extends Application {

    public static Stage mainStage;
    public static AudioClip backgroundMusic;
    public static AudioClip buttonEnteredSound;

    public static AudioClip buttonClickSound;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("views/main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        initializeMusic();
    }

    private void initializeMusic() {
        backgroundMusic = new AudioClip(Objects.requireNonNull(this.getClass().getResource("music/background/what-is-love-piano.mp3")).toString());
        backgroundMusic.setVolume(SoundParam.BACKGROUND_VOLUME);
        backgroundMusic.setCycleCount(INDEFINITE);
        backgroundMusic.play();

        buttonEnteredSound = new AudioClip(Objects.requireNonNull(this.getClass().getResource("music/buttons/buttonSoundType1.mp3")).toString());

        buttonClickSound = new AudioClip(Objects.requireNonNull(this.getClass().getResource("music/buttons/buttonClickType1.mp3")).toString());

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


    public static void playButtonEnteredSound() {
        if (buttonEnteredSound != null) {
            buttonEnteredSound.setVolume(SoundParam.ELEMENT_VOLUME);
            buttonEnteredSound.play();
        }

    }

    public static void playButtonClickSound() {
        if (buttonClickSound != null){
            buttonClickSound.setVolume(SoundParam.ELEMENT_VOLUME);
            buttonClickSound.play();
        }

    }




    public static void main(String[] args) {
        launch();
    }

}