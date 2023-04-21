package xyz.nonamed.gameclient.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.UserParam;
import xyz.nonamed.gameclient.handlers.IsAliveServerHandler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author artem1018
 */
public class MainMenuViewController implements Initializable {

    public AnchorPane mainView;
    public TextField userNameTextField;
    public Button newGameButton;
    public ImageView localServerStatusImage;
    public ImageView globalServerStatusImage;
    public ImageView refreshServerStatusButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();
        userNameTextField.setText(UserParam.USERNAME);

        onRefreshServerStatus();

    }

    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
        System.out.println(Screen.getPrimary().getBounds().getWidth());
    }


    @FXML
    public void onChangeUserNameTextField() {
        UserParam.USERNAME = userNameTextField.getText();
    }

    @FXML
    public void onMouseMovedEntered() {
    }

    @FXML
    public void onNewGameButtonClick() {
        ClientApplication.changeScreen("views/session-view.fxml", "Вибір сесії");
    }

    @FXML
    public void onSettingsButtonClick() {
    }

    @FXML
    public void onExitButtonClick() {
        ClientApplication.mainStage.close();
    }

    @FXML
    public void onInfoButtonClick() {

    }

    @FXML
    public void onFastVolumeSwitchButtonClick(){

    }

    @FXML
    public void onRefreshServerStatus(){
        IsAliveServerHandler isAliveServerHandler = new IsAliveServerHandler();

        boolean localServerStatus = isAliveServerHandler.isLocalAlive();
        boolean globalServerStatus = isAliveServerHandler.isGlobalAlive();

        if (localServerStatus){
            localServerStatusImage.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/localhostAvailable.gif"));
        }else {
            localServerStatusImage.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/localhostNonAvailable.gif"));
        }

        if(globalServerStatus){
            globalServerStatusImage.setImage(new Image("xyz/nonamed/gameclient/images/mainMenuImages/globalhostAvailable.gif"));
        }else {
            //TODO add global host not available image
        }

    }

}
