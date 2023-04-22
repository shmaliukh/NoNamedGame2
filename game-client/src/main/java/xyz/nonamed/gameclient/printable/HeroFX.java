package xyz.nonamed.gameclient.printable;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.nonamed.dto.Hero;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class HeroFX extends Hero implements AliveFx {

    Rectangle rectangle = new Rectangle();
    Rectangle healthRectangle = new Rectangle();
    Rectangle damageRectangle = new Rectangle();
    Label label = new Label();

    public HeroFX(Hero hero) {
        super(hero);
    }

    public void print(Pane pane) {
        rectangle.setLayoutX(getPosX());
        rectangle.setLayoutY(getPosY());
        rectangle.setHeight(getHeight());
        rectangle.setWidth(getWidth());
        rectangle.setFill(Color.RED);
//        rectangle.setFill(aliveImageMap.get(getType())); TODO
//        if (MOVE_LEFT.equals(getAnimationType())) {
//            rectangle.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
//        }

        damageRectangle.setLayoutX(getPosX());
        damageRectangle.setLayoutY(getPosY() - 5.0d);
        damageRectangle.setWidth(getWidth() * getDamage() / getMaxHealth() * 20);
        damageRectangle.setHeight(4);
        damageRectangle.setFill(Color.DARKVIOLET);

        healthRectangle.setFill(Color.RED);
        healthRectangle.setLayoutX(getPosX());
        healthRectangle.setLayoutY(getPosY() - 10.0d);
        healthRectangle.setWidth(getWidth() * getHealth() / getMaxHealth());
        healthRectangle.setHeight(4);
        healthRectangle.setFill(Color.RED);

        label.setText(getName());
        label.setLayoutX(getPosX());
        label.setLayoutY(getPosY() - 30);
    }

    @Override
    public void addToPane(Pane pane) {
        pane.getChildren().add(rectangle);
        pane.getChildren().add(damageRectangle);
        pane.getChildren().add(healthRectangle);
        pane.getChildren().add(label);
    }

    @Override
    public void deleteFromPane(Pane pane) {
        pane.getChildren().remove(rectangle);
        pane.getChildren().remove(damageRectangle);
        pane.getChildren().remove(healthRectangle);
        pane.getChildren().remove(label);
    }

}
