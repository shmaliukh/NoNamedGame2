package xyz.nonamed.gameclient.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.factories.HeroFactory;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.config.ScreenParam;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author artem1018
 */
public class HeroChooseViewController implements Initializable {

    public ImageView heroTypeImageView;
    public Text damageTextValue;
    public Text healthTextValue;
    public Text speedTextValue;
    public Text colorTextValue;

    public AnchorPane mainView;


    public int currentHeroOnScreen = 0;
    public ArrayList<Image> heroImages = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();

        heroImages.add(new Image("xyz/nonamed/gameclient/images/heroes/type-1/heroWalkType-1.gif"));
        heroImages.add(new Image("xyz/nonamed/gameclient/images/heroes/type-2/heroWalkType-2.gif"));


        initHeroValues();
    }

    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
    }

    @FXML
    public void onNextButtonClick() {

        if (currentHeroOnScreen + 1 == heroImages.size()) {
            currentHeroOnScreen = 0;
        } else {
            currentHeroOnScreen += 1;
        }
        initHeroValues();

    }


    public void initHeroValues() {
        HeroFactory heroFactory = new HeroFactory();
        Hero hero = null;
        if (currentHeroOnScreen == 0) {
            hero = heroFactory.create(Hero.HERO_1);
        }
        assert hero != null;
        damageTextValue.setText(String.valueOf(hero.getDamage()));
        healthTextValue.setText(String.valueOf(hero.getHealth()));
        speedTextValue.setText(String.valueOf(hero.getSpeed()));
        colorTextValue.setText(String.valueOf(hero.getColor()));


    }

    @FXML
    public void openGameView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("views/game-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ClientApplication.mainStage.setTitle("Game view");
            ClientApplication.mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onMouseMovedEntered() {
        ClientApplication.playButtonEnteredSound();
    }


}
