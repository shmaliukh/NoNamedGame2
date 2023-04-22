package xyz.nonamed.gameclient.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author artem1018
 */
public class InfoMenuViewController implements Initializable {


    public AnchorPane mainView;
    public Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setScreenSize();

    }

    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
    }


    @FXML
    public void onBackButtonClick(){
        ClientApplication.playButtonClickSound();
        ClientApplication.changeScreen("views/main-menu-view.fxml", "Вибір сесії");
    }

    @FXML
    public void onMouseMovedEntered() {
        ClientApplication.playButtonEnteredSound();
    }


}
