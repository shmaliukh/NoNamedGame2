package xyz.nonamed.gameclient.printable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import xyz.nonamed.dto.World;

import java.util.Objects;


public class WorldFX extends World implements PrintableFx {

    public static final Image WORLD_IMAGE = new Image(Objects.requireNonNull(WorldFX.class.getResource("/xyz/nonamed/gameclient/images/map/mapground.png")).toString());
    public static final ImagePattern WORLD_IMAGE_PATTERN = new ImagePattern(WORLD_IMAGE);

    ImageView imageView = new ImageView(WORLD_IMAGE);

    @Override
    public void addToPane(Pane pane) {
        pane.getChildren().add(imageView);
    }

    @Override
    public void print(Pane pane) {
        width = WORLD_IMAGE.getWidth();
        height = WORLD_IMAGE.getHeight();
    }

}
