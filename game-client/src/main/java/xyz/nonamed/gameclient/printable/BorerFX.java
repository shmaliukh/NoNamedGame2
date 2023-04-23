package xyz.nonamed.gameclient.printable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.nonamed.dto.Borer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class BorerFX extends Borer implements PrintableFx, AliveFx {

    public static Map<String, Image> typeImageMap = new HashMap<>();

    static {
        typeImageMap.put(BORER_1, new Image(Objects.requireNonNull(BorerFX.class.getResource("/xyz/nonamed/gameclient/images/borer/image_2023-04-21_16-17-20.png")).toString()));
        typeImageMap.put(BORER_2, new Image(Objects.requireNonNull(BorerFX.class.getResource("/xyz/nonamed/gameclient/images/borer/image_2023-04-21_16-17-20.png")).toString()));
    }

    Rectangle rectangle = new Rectangle();
    ImageView imageView1 = new ImageView();
    ImageView imageView2 = new ImageView();
    Circle circle = new Circle();

    public BorerFX(Borer borer) {
        this.id = borer.id;

        this.name = borer.name;
        this.sessionCode = borer.sessionCode;

        this.posX = borer.posX;
        this.posY = borer.posY;

        this.height = borer.height;
        this.width = borer.width;
        this.maxEnergy = borer.getMaxEnergy();
        this.startEnergy = borer.getStartEnergy();
        this.animationType = borer.getAnimationType();
        this.isActive = borer.isActive();
        this.isActive = borer.isActive(); // FIXME

//        this.isCollision = borer.isCollision;
//        this.collisionPosX = borer.collisionPosX + posX;
//        this.collisionPosY = borer.collisionPosY + posY;
//        rectangle.setLayoutX(this.collisionPosX);
//        rectangle.setLayoutY(this.collisionPosY);

//        this.collisionHeight = borer.collisionHeight;
//        this.collisionWidth = borer.collisionWidth;

//        this.type = borer.getType();
        this.color = borer.color;
    }

    @Override
    public void addToPane(Pane pane) {
        imageView1 = new ImageView(typeImageMap.get(BORER_1));
        imageView2 = new ImageView(typeImageMap.get(BORER_2));

        if (!pane.getChildren().contains(circle)) {
            pane.getChildren().add(circle);
            pane.getChildren().add(rectangle);
            pane.getChildren().add(imageView1);
            pane.getChildren().add(imageView2);
        }
    }

    @Override
    public void print(Pane pane) {
        imageView1.setLayoutX(getPosX());
        imageView1.setLayoutY(getPosY());

        imageView2.setLayoutX(getPosX());
        imageView2.setLayoutY(getPosY());

        rectangle.setLayoutX(getPosX());
        rectangle.setLayoutY(getPosY() - 10);
        rectangle.setWidth(getWidth() * getStartEnergy() / getMaxEnergy());
        rectangle.setHeight(8);
        rectangle.setFill(Color.GREEN);

        circle.setRadius(getMinDistanceToActivate());
        circle.setLayoutX(getPosX());
        circle.setLayoutY(getPosY());
        circle.setCenterX(getWidth() / 2);
        circle.setCenterY(getHeight() / 2);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
    }


    @Override
    public void deleteFromPane(Pane pane) {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double getMaxHealth() {
        return 0;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}
