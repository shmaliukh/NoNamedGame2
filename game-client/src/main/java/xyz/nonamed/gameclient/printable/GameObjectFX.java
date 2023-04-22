package xyz.nonamed.gameclient.printable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.World;

import java.util.Objects;

public class GameObjectFX extends GameObject implements PrintableFx {

    public static final Image HOME_IMAGE1 = new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/home1.png")).toString());
    public static final Image HOME_IMAGE2 = new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/home2.png")).toString());

    ImageView imageView = new ImageView(HOME_IMAGE1);


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
