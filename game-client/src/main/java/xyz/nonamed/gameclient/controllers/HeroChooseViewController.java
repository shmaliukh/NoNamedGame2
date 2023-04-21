package xyz.nonamed.gameclient.controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import xyz.nonamed.gameclient.config.ScreenParam;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();
    }


    public void setScreenSize() {
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
    }
}
