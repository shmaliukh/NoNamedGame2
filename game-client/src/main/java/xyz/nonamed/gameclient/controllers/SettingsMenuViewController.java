package xyz.nonamed.gameclient.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.SoundParam;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author artem1018
 */
public class SettingsMenuViewController implements Initializable {


    public AnchorPane mainView;
    public Button backButton;
    public Slider sliderGameVolume;
    public Slider sliderElementVolume;
    public Slider sliderBackgroundVolume;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();
        setPrefVolumeLevels();

    }

    private void setPrefVolumeLevels() {
        sliderBackgroundVolume.setValue(SoundParam.BACKGROUND_VOLUME * 100);
        sliderElementVolume.setValue(SoundParam.ELEMENT_VOLUME * 100);
        sliderGameVolume.setValue(SoundParam.GAME_VOLUME * 100);

    }

    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
    }

    public void onSliderBackgroundVolumeChange() {
        SoundParam.BACKGROUND_VOLUME = (float) (sliderBackgroundVolume.getValue() / 100);
        ClientApplication.backgroundMusic.stop();
        ClientApplication.backgroundMusic.setVolume(SoundParam.BACKGROUND_VOLUME);
        ClientApplication.backgroundMusic.play();
    }

    public void onSliderElementVolumeChange() {
        SoundParam.ELEMENT_VOLUME = (float) (sliderElementVolume.getValue() / 100);
    }

    public void onSliderGameVolumeChange(){
        SoundParam.GAME_VOLUME = (float) (sliderGameVolume.getValue() / 100);
    }


    @FXML
    public void onBackButtonClick() {
        ClientApplication.changeScreen("views/main-menu-view.fxml", "Main menu");
    }

    @FXML
    public void onMouseMovedEntered() {
        ClientApplication.playButtonEnteredSound();
    }

    @FXML
    public void onSliderClick(){
        ClientApplication.playButtonClickSound();
    }

}
