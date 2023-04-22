package xyz.nonamed.gameclient.printable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.nonamed.dto.Hero;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static xyz.nonamed.gameclient.controllers.StaticData.MY_HERO_FX;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class HeroFX extends Hero implements AliveFx {

    public static Map<String, Image> typeImageMap = new HashMap<>();

    static {
        typeImageMap.put(HERO_1 + STOP, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-1/heroType-1.gif")).toString()));
        typeImageMap.put(HERO_1 + WALK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-1/heroWalkType-1.gif")).toString()));
        typeImageMap.put(HERO_1 + LEFT_ATTACK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-1/heroLeftAttackType-1.gif")).toString()));

        typeImageMap.put(HERO_2 + STOP, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroType-2.gif")).toString()));
        typeImageMap.put(HERO_2 + WALK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroWalkType-2.gif")).toString()));
        typeImageMap.put(HERO_2 + LEFT_ATTACK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroLeftAttackType-2.gif")).toString()));
        typeImageMap.put(HERO_2 + RIGHT_ATTACK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroRightAttackType-2.gif")).toString()));
    }

    ImageView imageView = new ImageView();
    Rectangle healthRectangle = new Rectangle();
    Rectangle damageRectangle = new Rectangle();
    Label label = new Label();

    public HeroFX(Hero hero) {
        super(hero);
        setAnimationType(STOP);
    }


    public void print(Pane pane) {
        imageView.setLayoutX(getPosX());
        imageView.setLayoutY(getPosY());
        imageView.setImage(typeImageMap.get(getType() + getAnimationType()));
        width = 128;
        height = 128;

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
        MY_HERO_FX.setAnimationType(STOP);
    }

    @Override
    public void addToPane(Pane pane) {
        pane.getChildren().add(imageView);
        pane.getChildren().add(damageRectangle);
        pane.getChildren().add(healthRectangle);
        pane.getChildren().add(label);
    }

    @Override
    public void deleteFromPane(Pane pane) {
        pane.getChildren().remove(imageView);
        pane.getChildren().remove(damageRectangle);
        pane.getChildren().remove(healthRectangle);
        pane.getChildren().remove(label);
    }

}
