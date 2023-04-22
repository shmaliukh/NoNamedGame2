package xyz.nonamed.gameclient.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.SoundParam;
import xyz.nonamed.gameclient.config.UserParam;
import xyz.nonamed.gameclient.handlers.IsAliveServerHandler;



import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static xyz.nonamed.gameclient.ClientApplication.backgroundMusic;

/**
 * @author artem1018
 */
public class MainMenuViewController implements Initializable {


    public static final Image LOCALHOST_AVAILABLE_IMAGE = new Image(Objects.requireNonNull(MainMenuViewController.class.getResource("/xyz/nonamed/gameclient/images/mainMenuImages/localhostAvailable.gif")).toString());
    public static final Image LOCALHOST_NON_AVAILABLE_IMAGE = new Image(Objects.requireNonNull(MainMenuViewController.class.getResource("/xyz/nonamed/gameclient/images/mainMenuImages/localhostNonAvailable.gif")).toString());
    public static final Image GLOBALHOST_AVAILABLE_IMAGE = new Image(Objects.requireNonNull(MainMenuViewController.class.getResource("/xyz/nonamed/gameclient/images/mainMenuImages/globalhostAvailable.gif")).toString());
    public static final Image GLOBALHOST_NON_AVAILABLE_IMAGE = new Image(Objects.requireNonNull(MainMenuViewController.class.getResource("/xyz/nonamed/gameclient/images/mainMenuImages/globalhostNonAvailable.gif")).toString());

    public AnchorPane mainView;
    public TextField userNameTextField;
    public Button newGameButton;
    public ImageView localServerStatusImage;
    public ImageView globalServerStatusImage;
    public ImageView refreshServerStatusButton;
    public ImageView volumeFastSwitchButton;

    public ImageView choosenNetworkServerImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();
        userNameTextField.setText(UserParam.USERNAME);

        addFilterToUserInputFields();

        onRefreshServerStatus();

    }

    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
    }


    @FXML
    public void onChangeUserNameTextField() {
        ClientApplication.playButtonClickSound();
        UserParam.USERNAME = userNameTextField.getText();
    }

    @FXML
    public void onMouseMovedEntered() {
        ClientApplication.playButtonEnteredSound();
    }



    @FXML
    public void onNewGameButtonClick() {
        ClientApplication.playButtonClickSound();
        ClientApplication.changeScreen("views/session-view.fxml", "Вибір сесії");

    }

    @FXML
    public void onSettingsButtonClick() {
        ClientApplication.playButtonClickSound();

        ClientApplication.changeScreen("views/settings-menu-view.fxml", "Налаштування");
    }

    @FXML
    public void onExitButtonClick() {
        ClientApplication.playButtonClickSound();
        ClientApplication.mainStage.close();
    }

    @FXML
    public void onInfoButtonClick() {
        ClientApplication.playButtonClickSound();
        ClientApplication.changeScreen("views/info-menu-view.fxml", "Про програму");
    }

//    @FXML
//    public void onFastVolumeSwitchButtonClick() {
//        ClientApplication.playButtonClickSound();
//
//        if (SoundParam.BACKGROUND_VOLUME == 0) {
//            SoundParam.BACKGROUND_VOLUME = SoundParam.LAST_BACKGROUND_VOLUME;
//            SoundParam.ELEMENT_VOLUME = SoundParam.LAST_ELEMENT_VOLUME;
//            SoundParam.GAME_VOLUME = SoundParam.LAST_GAME_VOLUME;
//            volumeFastSwitchButton.setImage(new Image(Objects.requireNonNull(MainMenuViewController.class.getResource("/xyz/nonamed/gameclient/images/mainMenuImages/soundInfoButtonON.png")).toString()));
//        } else {
//            SoundParam.LAST_BACKGROUND_VOLUME = SoundParam.BACKGROUND_VOLUME;
//            SoundParam.BACKGROUND_VOLUME = 0;
//            SoundParam.LAST_ELEMENT_VOLUME = SoundParam.ELEMENT_VOLUME;
//            SoundParam.ELEMENT_VOLUME = 0;
//            SoundParam.LAST_GAME_VOLUME = SoundParam.GAME_VOLUME;
//            SoundParam.GAME_VOLUME = 0;
//            volumeFastSwitchButton.setImage(new Image(Objects.requireNonNull(MainMenuViewController.class.getResource("/xyz/nonamed/gameclient/images/mainMenuImages/soundInfoButtonOFF.png")).toString()));
//        }
//
//        backgroundMusic.stop();
//        backgroundMusic.setVolume(SoundParam.BACKGROUND_VOLUME);
//        backgroundMusic.play();
//
//// FIXME
//
//
//    }


    @FXML
    public void onRefreshServerStatus() {
        new Thread(() -> {
            ClientApplication.playButtonClickSound();
            IsAliveServerHandler isAliveServerHandler = new IsAliveServerHandler();

            boolean localServerStatus = isAliveServerHandler.isLocalAlive();
            boolean globalServerStatus = isAliveServerHandler.isGlobalAlive();

            if (localServerStatus) {
                localServerStatusImage.setImage(LOCALHOST_AVAILABLE_IMAGE);
            } else {
                localServerStatusImage.setImage(LOCALHOST_NON_AVAILABLE_IMAGE);
            }

            if (globalServerStatus) {
                globalServerStatusImage.setImage(GLOBALHOST_AVAILABLE_IMAGE);
            } else {
                globalServerStatusImage.setImage(GLOBALHOST_NON_AVAILABLE_IMAGE);
            }
        }).start();
    }
    
//    public void onFastVolumeSwitchButtonClick(){
//        ClientApplication.playButtonClickSound();
//
//        if (SoundParam.BACKGROUND_VOLUME == 0){
//            SoundParam.BACKGROUND_VOLUME = SoundParam.LAST_BACKGROUND_VOLUME;
//            SoundParam.ELEMENT_VOLUME = SoundParam.LAST_ELEMENT_VOLUME;
//            SoundParam.GAME_VOLUME = SoundParam.LAST_GAME_VOLUME;
//            volumeFastSwitchButton.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/soundInfoButtonON.png"));
//        }else {
//            SoundParam.LAST_BACKGROUND_VOLUME = SoundParam.BACKGROUND_VOLUME;
//            SoundParam.BACKGROUND_VOLUME = 0;
//            SoundParam.LAST_ELEMENT_VOLUME = SoundParam.ELEMENT_VOLUME;
//            SoundParam.ELEMENT_VOLUME = 0;
//            SoundParam.LAST_GAME_VOLUME = SoundParam.GAME_VOLUME;
//            SoundParam.GAME_VOLUME = 0;
//            volumeFastSwitchButton.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/soundInfoButtonOFF.png"));
//        }
//
//        backgroundMusic.stop();
//        backgroundMusic.setVolume(SoundParam.BACKGROUND_VOLUME);
//        backgroundMusic.play();
//    }


    public void addFilterToUserInputFields() {
        //Додаємо перевірку для обробки значення коду сесії
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9a-zA-Z]*")) {
                return change;
            }
            return null; // відхиляємо неприпустимі зміни
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        userNameTextField.setTextFormatter(textFormatter);

    }


    @FXML
    public void onSwitchServerButtonPressed(){
        if (UserParam.SELECTED_HOST.equals("localhost")){
            choosenNetworkServerImageView.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/pressedLocal.gif"));
        } else {
            choosenNetworkServerImageView.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/pressedGlobal.gif"));
        }
    }


    @FXML
    public void onSwitchServerButtonReleased(){
        if (UserParam.SELECTED_HOST.equals("localhost")){
            UserParam.SELECTED_HOST = "globalhost";
            choosenNetworkServerImageView.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/switchGlobal.gif"));
        } else {
            UserParam.SELECTED_HOST = "localhost";
            choosenNetworkServerImageView.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/switchLocal.gif"));
        }
    }

}
