package xyz.nonamed.gameclient.printable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import xyz.nonamed.dto.World;

public class GameObjectFX extends World implements PrintableFx {

    public static final Image HOME_IMAGE1 = new Image("xyz/nonamed/gameclient/images/home/home1.png");
    public static final Image HOME_IMAGE2 = new Image("xyz/nonamed/gameclient/images/home/home2.png");

    ImageView imageView = new ImageView(HOME_IMAGE1);

    double posX;
    double posY;

    public GameObjectFX(double x, double y) {
        posX = x;
        posY = y;
    }

    @Override
    public void addToPane(Pane pane) {
        pane.getChildren().add(imageView);
    }

    @Override
    public void print(Pane pane) {
        imageView.setLayoutX(posX);
        imageView.setLayoutY(posY);
        width = HOME_IMAGE1.getWidth();
        height = HOME_IMAGE1.getHeight();
    }

}
