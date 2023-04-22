package xyz.nonamed.gameclient.printable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import xyz.nonamed.dto.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class WorldFX extends World implements PrintableFx {

    public static final Image WORLD_IMAGE_1 = new Image(Objects.requireNonNull(WorldFX.class.getResource("/xyz/nonamed/gameclient/images/map/mapground1.png")).toString());
    public static final Image WORLD_IMAGE_2 = new Image(Objects.requireNonNull(WorldFX.class.getResource("/xyz/nonamed/gameclient/images/map/mapground2.png")).toString());
    public static final Image WORLD_IMAGE_3 = new Image(Objects.requireNonNull(WorldFX.class.getResource("/xyz/nonamed/gameclient/images/map/mapground3.png")).toString());

    public static Map<String, Image> typeImageMap = new HashMap<>();

    static {
        typeImageMap.put(World.WORLD_1, WORLD_IMAGE_1);
        typeImageMap.put(World.WORLD_2, WORLD_IMAGE_2);
        typeImageMap.put(World.WORLD_3, WORLD_IMAGE_3);
    }


    ImageView imageView = new ImageView();
//    ImageView imageView1 = new ImageView();
//    ImageView imageView2 = new ImageView();
//    ImageView imageView3 = new ImageView();
//    ImageView imageView4 = new ImageView();

    public WorldFX(World worldBySession) {
        this.id = worldBySession.getId();
        this.sessionCode = worldBySession.getSessionCode();
        this.type = worldBySession.getType();
        this.width = worldBySession.getWidth();
        this.height = worldBySession.getHeight();
    }

    @Override
    public void addToPane(Pane pane) {
        imageView.setImage(typeImageMap.get(getType()));
//        imageView1.setImage(typeImageMap.get(getType()));
//        imageView1.setX(-getWidth());
//        imageView2.setImage(typeImageMap.get(getType()));
//        imageView2.setX(getWidth());
//        imageView3.setImage(typeImageMap.get(getType()));
//        imageView3.setY(-getHeight());
//        imageView4.setImage(typeImageMap.get(getType()));
//        imageView4.setY(-getHeight());

        pane.getChildren().add(imageView);
//        pane.getChildren().add(imageView1);
//        pane.getChildren().add(imageView2);
//        pane.getChildren().add(imageView3);
//        pane.getChildren().add(imageView4);
    }

    @Override
    public void print(Pane pane) {

    }

}
