package xyz.nonamed.gameclient.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.SessionParam;
import xyz.nonamed.gameclient.config.UserParam;
import xyz.nonamed.gameclient.handlers.HeroHandler;
import xyz.nonamed.gameclient.handlers.SessionHandler;
import xyz.nonamed.gameclient.handlers.UserHandler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author artem1018
 */
public class GameViewController implements Initializable {


    public AnchorPane mainView;
    public Pane gamePane;
    public Label userNameTextLabel;
    public Label codeTextLabel;


    public static Hero MY_HERO;

    static HeroHandler heroHandler = new HeroHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setInfoPanelValues();
            setScreenSize();


            initGameSettings();
    }

    private void setInfoPanelValues() {
        userNameTextLabel.setText(UserParam.USERNAME);
        codeTextLabel.setText(UserParam.SESSION_CODE);
    }

    public void setScreenSize() {
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
    }

    public void onSessionViewButtonClick(){
        ClientApplication.changeScreen("views/session-view.fxml", "Вибір сесії");
    }

    private static void initGameSettings(){
        if (UserParam.USER_HERO == null)
            UserParam.USER_HERO = new Hero();

        UserParam.USER_HERO.setName(UserParam.USERNAME);
        UserParam.USER_HERO.setType(Hero.HERO_1);



        UserHandler userHandler = new UserHandler();
        SessionHandler sessionHandler = new SessionHandler();

        userHandler.postRegisterUser(UserParam.USERNAME);
        sessionHandler.postConnectUserToSession(UserParam.USERNAME, UserParam.SESSION_CODE);
        MY_HERO = heroHandler.postRegisterHero(UserParam.USER_HERO, UserParam.USERNAME, UserParam.SESSION_CODE); // need to update our hero stats from server
        sessionHandler.postRunSession(UserParam.USERNAME,  UserParam.SESSION_CODE);

        System.out.println("<- connected ->  ");
        System.out.println("Імʼя користувача: " + UserParam.USERNAME);
        System.out.println("Код сесії: " + UserParam.SESSION_CODE);
        System.out.println("Макс. гравців: " + SessionParam.SESSION_MAX_USERS);

    }

}
