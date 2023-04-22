package xyz.nonamed.gameclient.controllers;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.handlers.GameObjectHandler;
import xyz.nonamed.gameclient.printable.GameObjectFX;
import xyz.nonamed.gameclient.printable.HeroFX;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.SessionParam;
import xyz.nonamed.gameclient.config.UserParam;
import xyz.nonamed.gameclient.handlers.HeroHandler;
import xyz.nonamed.gameclient.handlers.SessionHandler;
import xyz.nonamed.gameclient.handlers.UserHandler;
import xyz.nonamed.gameclient.printable.WorldFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static xyz.nonamed.Constants.*;
import static xyz.nonamed.gameclient.ClientApplication.mainStage;
import static xyz.nonamed.gameclient.controllers.StaticData.*;

/**
 * @author artem1018
 */
public class GameViewController implements Initializable {

    public AnchorPane mainView;
    public Pane gamePane;
    public Label userNameTextLabel;
    public Label codeTextLabel;
    public Pane miniMapPane;
    public Pane hudPane;
    static WorldFX WORLD_FX = new WorldFX();
    static List<GameObjectFX> gameObjectFXList = new ArrayList<>();

    static HeroHandler heroHandler = new HeroHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        handleHeroAction();
        setInfoPanelValues();
        setScreenSize();
        initGameSettings();

        gamePane.setLayoutX(0);
        gamePane.setLayoutY(0);

        MY_HERO_FX.setSpeed(50); // dev

        WORLD_FX.addToPane(gamePane);
        WORLD_FX.print(gamePane);
        MY_HERO_FX.addToPane(gamePane);
        MY_HERO_FX.print(gamePane);
        generateGameObjects();
//        gameObjectFXList.forEach(gameObjectFX -> gameObjectFX.addToPane(gamePane));
//        gameObjectFXList.forEach(gameObjectFX -> gameObjectFX.print(gamePane));


    }

    private void generateGameObjects() {
        GameObjectHandler gameObjectHandler = new GameObjectHandler();
        List<GameObject> gameObjectList = gameObjectHandler.getGameObjectList(UserParam.USERNAME, UserParam.SESSION_CODE);
        if (gameObjectList.isEmpty()) {
            gameObjectList = gameObjectHandler.postGenerateGameObjects(WORLD_FX, UserParam.USERNAME, UserParam.SESSION_CODE);
        }
        for (GameObject gameObject : gameObjectList) {
            GameObjectFX gameObjectFX = new GameObjectFX(gameObject);
            gameObjectFX.addToPane(gamePane);
            gameObjectFX.print(gamePane);
            gameObjectFXList.add(gameObjectFX);
        }
    }

    private boolean checkToCollision(double pointX, double pointY) {
        pointX += MY_HERO_FX.getWidth() / 2;
        pointY += MY_HERO_FX.getHeight() / 2;
        List<GameObjectFX> collisionObjects = gameObjectFXList.stream()
                .filter(GameObject::isCollision)
                .toList();
        for (GameObjectFX collisionObject : collisionObjects) {
            if (pointX >= collisionObject.getCollisionPosX()
                    && pointX <= collisionObject.getCollisionPosX() + collisionObject.getCollisionWidth()
                    && pointY >= collisionObject.getCollisionPosY()
                    && pointY <= collisionObject.getCollisionPosY() + collisionObject.getCollisionHeight()) {
                System.out.println(pointX + " " + pointY);
                return true; // collision detected
            }
        }
        return false;
    }

    private void handleHeroAction() {
        EventHandler<KeyEvent> heroActionHandler = e -> {
            KeyCode key = e.getCode();
            if ((key == KeyCode.W || key == KeyCode.UP)
                    && !checkToCollision(MY_HERO_FX.getPosX(), MY_HERO_FX.getPosY() - MY_HERO_FX.getSpeed())) {
                actionList.add(MOVE_UP);
                MY_HERO_FX.setPosY(MY_HERO_FX.getPosY() - MY_HERO_FX.getSpeed());
//                if (MY_HERO_FX.getPosY() < mainView.getHeight() / 2 - mainView.getHeight() * 0.2) {
                gamePane.setLayoutY(gamePane.getLayoutY() + MY_HERO_FX.getSpeed());
//                }
            }
            if ((key == KeyCode.S || key == KeyCode.DOWN)
                    && !checkToCollision(MY_HERO_FX.getPosX(), MY_HERO_FX.getPosY() + MY_HERO_FX.getSpeed())) {
                actionList.add(MOVE_DOWN);
                MY_HERO_FX.setPosY(MY_HERO_FX.getPosY() + MY_HERO_FX.getSpeed());
//                if (MY_HERO_FX.getPosY() > mainView.getHeight() / 2 + mainView.getHeight() * 0.2) {
                gamePane.setLayoutY(gamePane.getLayoutY() - MY_HERO_FX.getSpeed());
//                }
            }
            if ((key == KeyCode.A || key == KeyCode.LEFT)
                    && !checkToCollision(MY_HERO_FX.getPosX() - MY_HERO_FX.getSpeed(), MY_HERO_FX.getPosY())) {
                actionList.add(MOVE_LEFT);
                MY_HERO_FX.setPosX(MY_HERO_FX.getPosX() - MY_HERO_FX.getSpeed());
                gamePane.setLayoutX(gamePane.getLayoutX() + MY_HERO_FX.getSpeed());
            }
            if ((key == KeyCode.D || key == KeyCode.RIGHT)
                    && !checkToCollision(MY_HERO_FX.getPosX() + MY_HERO_FX.getSpeed(), MY_HERO_FX.getPosY())) {
                actionList.add(MOVE_RIGHT);
                MY_HERO_FX.setPosX(MY_HERO_FX.getPosX() + MY_HERO_FX.getSpeed());
                gamePane.setLayoutX(gamePane.getLayoutX() - MY_HERO_FX.getSpeed());
            }
            wrapPlayer();
            MY_HERO_FX.print(gamePane);
        };

        mainStage.addEventHandler(KeyEvent.KEY_PRESSED, heroActionHandler);
        mainStage.addEventHandler(KeyEvent.KEY_RELEASED, heroActionHandler);
    }

    public void wrapPlayer() {
        double playerX = MY_HERO_FX.getPosX();
        double playerY = MY_HERO_FX.getPosY();

        if (playerX < 0) {
            MY_HERO_FX.setPosX(WORLD_FX.getWidth() - MY_HERO_FX.getWidth());
            gamePane.setLayoutX(mainStage.getHeight() - WORLD_FX.getWidth());
        } else if (playerX >= WORLD_FX.getWidth() - MY_HERO_FX.getWidth()) {
            MY_HERO_FX.setPosX(0);
            gamePane.setLayoutX(0);
        }
        if (playerY < 0) {
            MY_HERO_FX.setPosY(WORLD_FX.getHeight() - MY_HERO_FX.getHeight());
            gamePane.setLayoutY(-WORLD_FX.getHeight() + mainStage.getHeight() - 300);
        } else if (playerY >= WORLD_FX.getHeight() - MY_HERO_FX.getHeight()) {
            MY_HERO_FX.setPosY(0);
            gamePane.setLayoutY(0);
        }
    }


    private void setInfoPanelValues() {
        userNameTextLabel.setText(UserParam.USERNAME);
        codeTextLabel.setText(UserParam.SESSION_CODE);
    }

    public void setScreenSize() {
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
    }

    public void onSessionViewButtonClick() {
        ClientApplication.changeScreen("views/session-view.fxml", "Вибір сесії");
    }

    private static void initGameSettings() {
        if (UserParam.USER_HERO == null) {
            UserParam.USER_HERO = new Hero();
        }

        UserParam.USER_HERO.setName(UserParam.USERNAME);
        UserParam.USER_HERO.setType(Hero.HERO_1);


        UserHandler userHandler = new UserHandler();
        SessionHandler sessionHandler = new SessionHandler();

        userHandler.postRegisterUser(UserParam.USERNAME);
        sessionHandler.postConnectUserToSession(UserParam.USERNAME, UserParam.SESSION_CODE);
        MY_HERO_FX = new HeroFX(heroHandler.postRegisterHero(UserParam.USER_HERO, UserParam.USERNAME, UserParam.SESSION_CODE)); // need to update our hero stats from server
        sessionHandler.postRunSession(UserParam.USERNAME, UserParam.SESSION_CODE);

        System.out.println("<- connected ->  ");
        System.out.println("Імʼя користувача: " + UserParam.USERNAME);
        System.out.println("Код сесії: " + UserParam.SESSION_CODE);
        System.out.println("Макс. гравців: " + SessionParam.SESSION_MAX_USERS);

    }

}
