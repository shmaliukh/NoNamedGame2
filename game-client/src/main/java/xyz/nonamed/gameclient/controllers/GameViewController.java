package xyz.nonamed.gameclient.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;
import xyz.nonamed.Alive;
import xyz.nonamed.dto.*;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.SoundParam;
import xyz.nonamed.gameclient.handlers.*;
import xyz.nonamed.gameclient.printable.*;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.SessionParam;
import xyz.nonamed.gameclient.config.UserParam;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static xyz.nonamed.Constants.*;
import static xyz.nonamed.dto.Hero.STOP;
import static xyz.nonamed.dto.Hero.WALK;
import static xyz.nonamed.gameclient.ClientApplication.mainStage;
import static xyz.nonamed.gameclient.config.UserParam.USER_HERO;
import static xyz.nonamed.gameclient.controllers.StaticData.*;

/**
 * @author artem1018
 */
public class GameViewController implements Initializable {

    public static final int BOT_PERIOD = 100;
    public AnchorPane mainView;
    public Pane gamePane;
    public static Pane staticGamePane;
    public Label userNameTextLabel;
    public Label codeTextLabel;
    public Pane miniMapPane;
    public Pane hudPane;
    static WorldFX WORLD_FX = new WorldFX();
    static List<GameObjectFX> gameObjectFXList = new ArrayList<>();
    public static List<BotFX> botFXList = new ArrayList<>();
    static List<HeroFX> heroFXList = new ArrayList<>();
    static List<BorerFX> borerFXList = new ArrayList<>();

    static HeroHandler heroHandler = new HeroHandler();

    static Timer timer1 = new Timer();
    static Timer timer2 = new Timer();
    static Timer timer3 = new Timer();
    static Timer timer4 = new Timer();
    static Timer timer5 = new Timer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            staticGamePane = gamePane;

            botFXList = new ArrayList<>();
            heroFXList = new ArrayList<>();
            gameObjectFXList = new ArrayList<>();
            setInfoPanelValues();
            setScreenSize();
            initGameSettings();
            handleHeroAction();

            gamePane.setLayoutX(0);
            gamePane.setLayoutY(0);

            borerFXList = new BorerHandler().getBorerList(UserParam.USERNAME, UserParam.SESSION_CODE).stream()
                    .map(BorerFX::new)
                    .collect(Collectors.toList()); // TODO
            WORLD_FX.addToPane(gamePane);
            WORLD_FX.print(gamePane);
            MY_HERO_FX.addToPane(gamePane);
            MY_HERO_FX.print(gamePane);

            gamePane.setLayoutX(mainStage.getWidth() / 2 - MY_HERO_FX.getPosX() - MY_HERO_FX.getWidth() / 2);
            gamePane.setLayoutY(mainStage.getHeight() / 2 - MY_HERO_FX.getPosY() - MY_HERO_FX.getHeight() / 2);

            generateGameObjects();

            setUpThreadsWithUpdate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void setUpThreadsWithUpdate() {
        try {
            timer1.cancel();
            timer2.cancel();
            timer3.cancel();
            timer4.cancel();
            timer5.cancel();
            timer1 = new Timer();
            timer2 = new Timer();
            timer3 = new Timer();
            timer4 = new Timer();
            timer5 = new Timer();
            timer1.schedule(new UpdateHeroTask(), 0, 100);
            timer2.schedule(new UpdateBotTask(), 0, BOT_PERIOD);
            timer3.schedule(new UpdateAllHeroTask(), 0, 100);
            timer4.schedule(new UpdateMiniMapTask(), 0, 250);
            timer5.schedule(new UpdateMiniMapTask(), 0, 250);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }


    private class UpdateBorerTask extends TimerTask {

        @Override
        public void run() {
            try {
                try {
                    Platform.runLater(() -> {

                        // TODO implement logic to collect energy

                    });
                } catch (Throwable error) {
                    error.printStackTrace();
                }
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }
    }

    private class UpdateMiniMapTask extends TimerTask {

        @Override
        public void run() {
            try {
                try {
                    Platform.runLater(() -> {
                        miniMapPane.getChildren().clear();
                        printMiniMap(botFXList);
                        printMiniMap(heroFXList);
                        printMiniMap(borerFXList);
                        printMiniMap(Collections.singletonList(MY_HERO_FX));

                    });
                } catch (Throwable error) {
                    error.printStackTrace();
                }
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }

        private void printMiniMap(List<? extends Alive> gameObjectList) {
            for (Alive gameObject : gameObjectList) {
                Rectangle mapItem = new Rectangle();
                mapItem.setX(miniMapPane.getWidth() * gameObject.getPosX() / WORLD_FX.getWidth());
                mapItem.setY(miniMapPane.getHeight() * gameObject.getPosY() / WORLD_FX.getHeight());
                mapItem.setWidth(3);
                mapItem.setHeight(3);
                mapItem.setFill(Color.valueOf(gameObject.getColor() != null ? gameObject.getColor() : Color.WHITE.toString()));
                miniMapPane.getChildren().add(mapItem);
            }
        }
    }

    private class UpdateHeroTask extends TimerTask {
        HeroHandler heroHandler = new HeroHandler();

        @Override
        public void run() {
            try {
                heroHandler.postActions(new Actions(actionList), UserParam.USERNAME, UserParam.SESSION_CODE);
                actionList = new ArrayList<>();
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }
    }

    private class UpdateAllHeroTask extends TimerTask {
        HeroHandler heroHandler = new HeroHandler();

        @Override
        public void run() {
            try {
                List<Hero> serverHeroeList = heroHandler.getHeroList(UserParam.USERNAME, UserParam.SESSION_CODE);
                updateMyHero(serverHeroeList);
                addNewHeroesFromServer(serverHeroeList);
                updateExistsHeroes(serverHeroeList);
                printAllHeroes();
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }

        private void printAllHeroes() {
            try {
                try {
                    Platform.runLater(() -> {
                        heroFXList.stream()
                                .filter(Objects::nonNull)
                                .forEach(botFX -> botFX.print(gamePane));
                    });
                } catch (Throwable error) {
                    error.printStackTrace();
                }
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }

        private void updateExistsHeroes(List<Hero> serverHeroeList) {
            serverHeroeList.stream()
                    .filter(Objects::nonNull)
                    .filter(hero -> heroFXList.stream().anyMatch(heroFX -> Objects.equals(hero.getId(), heroFX.getId())))
                    .forEach(hero -> {
                        for (HeroFX heroFX : heroFXList) {
                            if (heroFX.getId().equals(hero.getId())) {
                                heroFX.setPosX(hero.getPosX());
                                heroFX.setPosY(hero.getPosY());
                                heroFX.setHealth(hero.getHealth());
                                heroFX.setSpeed(hero.getSpeed());
                                heroFX.setDamage(hero.getDamage());
                                heroFX.setAnimationType(hero.getAnimationType());
                            }
                        }
                    });
        }

        private void addNewHeroesFromServer(List<Hero> serverHeroeList) {
            try {
                serverHeroeList.stream()
                        .filter(Objects::nonNull)
                        .filter(hero -> heroFXList.stream().noneMatch(heroFX -> Objects.equals(hero.getId(), heroFX.getId())))
                        .forEach(hero -> {
                            HeroFX heroFX = new HeroFX(hero);
                            heroFXList.add(heroFX);
                            try {
                                Platform.runLater(() -> {
                                    heroFX.addToPane(gamePane);
                                });
                            } catch (Throwable error) {
                                error.printStackTrace();
                            }
                        });
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }

        private void updateMyHero(List<Hero> serverHeroeList) {
            heroHandler.postUpdateHero(MY_HERO_FX, UserParam.USERNAME, UserParam.SESSION_CODE);
            Optional<Hero> optionalHero = serverHeroeList.stream().filter(hero -> Objects.equals(MY_HERO_FX.getId(), hero.getId())).findFirst();
            optionalHero.ifPresent(serverHeroeList::remove);
        }
    }

    private class UpdateBotTask extends TimerTask {
        BotHandler botHandler = new BotHandler();

        @Override
        public void run() {
            try {
                List<Bot> serverBots = botHandler.getBotList(UserParam.USERNAME, UserParam.SESSION_CODE);
                serverBots.stream()
                        .filter(Objects::nonNull)
                        .filter(bot -> botFXList.stream().noneMatch(botFX -> Objects.equals(bot.getId(), botFX.getId())))
                        .forEach(bot -> {
                            BotFX botFX = new BotFX(bot);
                            botFXList.add(botFX);

                            try {
                                Platform.runLater(() -> botFX.addToPane(gamePane));
                            } catch (Throwable error) {
                                error.printStackTrace();
                            }


                        });
                serverBots.stream()
                        .filter(Objects::nonNull)
                        .filter(bot -> botFXList.stream().anyMatch(botFX -> Objects.equals(bot.getId(), botFX.getId())))
                        .forEach(bot -> {
                            for (BotFX botFX : botFXList) {
                                if (botFX.id.equals(bot.getId())) {
                                    try {
                                        Platform.runLater(() -> {
                                            Timeline timeline = new Timeline(
                                                    new KeyFrame(Duration.ZERO, new KeyValue(botFX.getImageView().layoutXProperty(), botFX.getPosX())),
                                                    new KeyFrame(Duration.ZERO, new KeyValue(botFX.getImageView().layoutYProperty(), botFX.getPosY())),
                                                    new KeyFrame(Duration.millis(BOT_PERIOD), new KeyValue(botFX.getImageView().layoutXProperty(), bot.getPosX())),
                                                    new KeyFrame(Duration.millis(BOT_PERIOD), new KeyValue(botFX.getImageView().layoutYProperty(), bot.getPosY()))
                                            );
                                            timeline.play();
                                        });
                                    } catch (Throwable error) {
                                        error.printStackTrace();
                                    }

                                    botFX.setPosX(bot.getPosX());
                                    botFX.setPosY(bot.getPosY());
                                    botFX.setHealth(bot.getHealth());
                                    botFX.setAnimationType(bot.getAnimationType());
                                }
                            }
                        });
                try {
                    Platform.runLater(() -> {
                        botFXList.stream()
                                .filter(Objects::nonNull)
                                .forEach(botFX -> botFX.print(gamePane));
                        botFXList.stream()
                                .filter(Objects::nonNull)
                                .filter(botFX -> botFX.getImageView().getBoundsInParent().intersects(MY_HERO_FX.getImageView().getBoundsInParent()))
                                .forEach(botFX -> {
                                    MY_HERO_FX.setHealth(MY_HERO_FX.getHealth() - botFX.getDamage());
                                    if (MY_HERO_FX.getHealth() <= 0) {
                                        MY_HERO_FX.setDead(true);
                                        MY_HERO_FX.setAnimationType(STOP);
                                        new HeroHandler().postUpdateHero(MY_HERO_FX, UserParam.USERNAME, UserParam.SESSION_CODE);
                                        // FIXME add alert
                                    }
                                    MY_HERO_FX.print(gamePane);
                                });

                    });

                } catch (Throwable error) {
                    error.printStackTrace();
                }
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }
    }

    private void generateGameObjects() {
        GameObjectHandler gameObjectHandler = new GameObjectHandler();
        List<GameObject> gameObjectList = gameObjectHandler.getGameObjectList(UserParam.USERNAME, UserParam.SESSION_CODE).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
                .filter(Objects::nonNull)
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
            if (MY_HERO_FX.isDead == false) {
                KeyCode key = e.getCode();
                if ((key == KeyCode.W || key == KeyCode.UP)
                        && !checkToCollision(MY_HERO_FX.getPosX(), MY_HERO_FX.getPosY() - MY_HERO_FX.getSpeed())) {
                    actionList.add(MOVE_UP);
                    MY_HERO_FX.setPosY(MY_HERO_FX.getPosY() - MY_HERO_FX.getSpeed());
                    gamePane.setLayoutY(gamePane.getLayoutY() + MY_HERO_FX.getSpeed());
                }
                if ((key == KeyCode.S || key == KeyCode.DOWN)
                        && !checkToCollision(MY_HERO_FX.getPosX(), MY_HERO_FX.getPosY() + MY_HERO_FX.getSpeed())) {
                    actionList.add(MOVE_DOWN);
                    MY_HERO_FX.setPosY(MY_HERO_FX.getPosY() + MY_HERO_FX.getSpeed());
                    gamePane.setLayoutY(gamePane.getLayoutY() - MY_HERO_FX.getSpeed());
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
                MY_HERO_FX.setAnimationType(WALK);
                MY_HERO_FX.print(gamePane);
            }
        };

//        try {
//            mainStage.removeEventHandler(KeyEvent.KEY_PRESSED, heroActionHandler);
//            mainStage.removeEventHandler(KeyEvent.KEY_RELEASED, heroActionHandler);
//        } catch (Throwable throwable) {
//        }

        mainStage.addEventHandler(KeyEvent.KEY_PRESSED, heroActionHandler);
        mainStage.addEventHandler(KeyEvent.KEY_RELEASED, heroActionHandler);
        mainStage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Point2D start = new Point2D(MY_HERO_FX.getPosX(), MY_HERO_FX.getPosY());
            Point2D end = new Point2D(mouseEvent.getX() + MY_HERO_FX.getPosX(), mouseEvent.getY() + MY_HERO_FX.getPosY());
            Circle bullet = new Circle(3, Color.RED);
            bullet.setEffect(new DropShadow(10, Color.BLACK));
            bullet.setCenterX(start.getX());
            bullet.setCenterY(start.getY());
            gamePane.getChildren().add(bullet);

            double diffX = MY_HERO_FX.getWidth() / 2;
            double diffY = MY_HERO_FX.getHeight() / 2;
            double sceneX = mouseEvent.getSceneX();
            double sceneY = mouseEvent.getSceneY();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(bullet.layoutXProperty(), diffX)),
                    new KeyFrame(Duration.ZERO, new KeyValue(bullet.layoutYProperty(), diffY)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(bullet.layoutXProperty(), sceneX - mainStage.getWidth() / 2 + diffX)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(bullet.layoutYProperty(), sceneY - mainStage.getHeight() / 2 + diffY))
            );
            timeline.setCycleCount(1);

            AudioClip gunshot = new AudioClip(getClass().getResource("/xyz/nonamed/gameclient/music/gunshot.mp3").toString());
            gunshot.setVolume(SoundParam.GAME_VOLUME);
            gunshot.play();
            System.out.println("sceneX: " + (sceneX - mainStage.getWidth() / 2));
            MY_HERO_FX.setAnimationType(sceneX - mainStage.getWidth() / 2 > 0 ? HeroFX.RIGHT_ATTACK : HeroFX.LEFT_ATTACK);
            MY_HERO_FX.print(gamePane);

            timeline.play();
            timeline.setOnFinished(event -> {
                gamePane.getChildren().remove(bullet);
                MY_HERO_FX.setAnimationType(WALK);
                MY_HERO_FX.print(gamePane);
            });
        });
    }

    public void wrapPlayer() {
        double playerX = MY_HERO_FX.getPosX();
        double playerY = MY_HERO_FX.getPosY();

        if (playerX < 0) {
            MY_HERO_FX.setPosX(WORLD_FX.getWidth() - MY_HERO_FX.getWidth());
            gamePane.setLayoutX(mainStage.getHeight() - WORLD_FX.getWidth() - MY_HERO_FX.getHeight() / 2);
        } else if (playerX >= WORLD_FX.getWidth() - MY_HERO_FX.getWidth()) {
            MY_HERO_FX.setPosX(0);
            gamePane.setLayoutX(0 + mainStage.getWidth() / 2 - MY_HERO_FX.getHeight() / 2);
        }
        if (playerY < 0) {
            MY_HERO_FX.setPosY(WORLD_FX.getHeight() - MY_HERO_FX.getHeight());
            gamePane.setLayoutY(-WORLD_FX.getHeight() + mainStage.getHeight() / 2 + MY_HERO_FX.getHeight() - MY_HERO_FX.getHeight() / 2);
        } else if (playerY >= WORLD_FX.getHeight() - MY_HERO_FX.getHeight()) {
            MY_HERO_FX.setPosY(0);
            gamePane.setLayoutY(0 + mainStage.getHeight() / 2 - MY_HERO_FX.getHeight() / 2);
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
        try {
            USER_HERO = new Hero();


            UserHandler userHandler = new UserHandler();
            SessionHandler sessionHandler = new SessionHandler();

            UserEntity user = userHandler.postRegisterUser(UserParam.USERNAME);
            sessionHandler.postConnectUserToSession(UserParam.USERNAME, UserParam.SESSION_CODE);
            USER_HERO.setName(UserParam.USERNAME);
            USER_HERO.setType(UserParam.HERO_TYPE);
            MY_HERO_FX = new HeroFX(heroHandler.postRegisterHero(USER_HERO, UserParam.USERNAME, UserParam.SESSION_CODE)); // need to update our hero stats from server
            if (MY_HERO_FX == null) {
                System.out.println("problem to connect to session");
                // TODO changeScreen();
            }
            sessionHandler.postRunSession(UserParam.USERNAME, UserParam.SESSION_CODE);

            System.out.println("<- connected ->  ");
            System.out.println("Імʼя користувача: " + UserParam.USERNAME);
            System.out.println("Код сесії: " + UserParam.SESSION_CODE);
            System.out.println("Макс. гравців: " + SessionParam.SESSION_MAX_USERS);

            WorldHandler worldHandler = new WorldHandler();
            WORLD_FX = new WorldFX(worldHandler.getWorldBySession(UserParam.USERNAME, UserParam.SESSION_CODE));
            System.out.println(WORLD_FX);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

}
