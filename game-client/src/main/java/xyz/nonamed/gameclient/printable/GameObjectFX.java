package xyz.nonamed.gameclient.printable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.nonamed.dto.GameObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class GameObjectFX extends GameObject implements PrintableFx {

    public static Map<String, Image> typeImageMap = new HashMap<>();
    static {
        typeImageMap.put(GAME_OBJECT_1, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/home1.png")).toString()));
        typeImageMap.put(GAME_OBJECT_2, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/home2.png")).toString()));
        typeImageMap.put(GAME_OBJECT_3, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/tank1.png")).toString()));
        typeImageMap.put(GAME_OBJECT_4, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/tank2.png")).toString()));
        typeImageMap.put(GAME_OBJECT_5, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/tank3.png")).toString()));
        typeImageMap.put(GAME_OBJECT_6, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/tank4.png")).toString()));
        typeImageMap.put(GAME_OBJECT_7, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/home3.png")).toString()));
        typeImageMap.put(GAME_OBJECT_8, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/home4.png")).toString()));
        typeImageMap.put(GAME_OBJECT_9, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/bord1.png")).toString()));
        typeImageMap.put(GAME_OBJECT_10, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/bord2.png")).toString()));
        typeImageMap.put(GAME_OBJECT_11, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/bord3.png")).toString()));
        typeImageMap.put(GAME_OBJECT_12, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/car1.png")).toString()));
        typeImageMap.put(GAME_OBJECT_13, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/car2.png")).toString()));
        typeImageMap.put(GAME_OBJECT_14, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/server1.png")).toString()));
        typeImageMap.put(GAME_OBJECT_15, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/server2.png")).toString()));
        typeImageMap.put(GAME_OBJECT_16, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/barrel.png")).toString()));
        typeImageMap.put(GAME_OBJECT_17, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/box.png")).toString()));
        typeImageMap.put(GAME_OBJECT_18, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/luckybox.png")).toString()));
        typeImageMap.put(GAME_OBJECT_19, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/nlo.png")).toString()));
        typeImageMap.put(GAME_OBJECT_20, new Image(Objects.requireNonNull(GameObjectFX.class.getResource("/xyz/nonamed/gameclient/images/home/solar.png")).toString()));
    }

    ImageView imageView;
    Rectangle rectangle = new Rectangle();

    public GameObjectFX(GameObject gameObject) {
        this.id = gameObject.id;

        this.name = gameObject.name;
        this.sessionCode = gameObject.sessionCode;

        this.posX = gameObject.posX;
        this.posY = gameObject.posY;

        this.height = gameObject.height;
        this.width = gameObject.width;

        this.isCollision = gameObject.isCollision;
        this.collisionPosX = gameObject.collisionPosX + posX;
        this.collisionPosY = gameObject.collisionPosY + posY;
        rectangle.setLayoutX(this.collisionPosX);
        rectangle.setLayoutY(this.collisionPosY);

        this.collisionHeight = gameObject.collisionHeight;
        this.collisionWidth = gameObject.collisionWidth;

        this.type = gameObject.getType();
        this.color = gameObject.color;
    }

    @Override
    public void addToPane(Pane pane) {
        imageView = new ImageView(typeImageMap.get(getType()));
        pane.getChildren().add(imageView);
        pane.getChildren().add(rectangle);
    }

    @Override
    public void print(Pane pane) {
        imageView.setLayoutX(posX);
        imageView.setLayoutY(posY);
        width = imageView.getFitWidth();
        height = imageView.getFitHeight();


        rectangle.setWidth(collisionWidth);
        rectangle.setHeight(collisionHeight);
        //TODO change color
        rectangle.setFill(Color.TRANSPARENT);
    }



}
