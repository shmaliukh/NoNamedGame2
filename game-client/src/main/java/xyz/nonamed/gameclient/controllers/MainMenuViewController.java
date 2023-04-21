package xyz.nonamed.gameclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.UserParam;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author artem1018
 */
public class MainMenuViewController implements Initializable {

    public AnchorPane mainView;
    public TextField userNameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();
        userNameTextField.setText(UserParam.USERNAME);

    }

    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
        System.out.println(Screen.getPrimary().getBounds().getWidth());
    }


    @FXML
    public void onChangeUserNameTextField(KeyEvent keyEvent) {
    }

    @FXML
    public void onMouseMovedEntered(MouseEvent mouseEvent) {
    }

    @FXML
    public void onNewGameButtonClick(ActionEvent actionEvent) {
        ClientApplication.changeScreen("views/session-view.fxml", "Вибір сесії");
    }

    @FXML
    public void onSettingsButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onInfoButtonClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void onFastVolumeSwitchButtonClick(){

    }
}
