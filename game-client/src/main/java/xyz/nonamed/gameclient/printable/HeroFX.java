package xyz.nonamed.gameclient.printable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
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
        typeImageMap.put(HERO_1 + RIGHT_ATTACK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-1/hero1-attack-right.gif")).toString()));

        typeImageMap.put(HERO_2 + STOP, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroType-2.gif")).toString()));
        typeImageMap.put(HERO_2 + WALK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroWalkType-2.gif")).toString()));
        typeImageMap.put(HERO_2 + LEFT_ATTACK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroLeftAttackType-2.gif")).toString()));
        typeImageMap.put(HERO_2 + RIGHT_ATTACK, new Image(Objects.requireNonNull(HeroFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-2/heroRightAttackType-2.gif")).toString()));

        typeImageMap.put(HERO_3 + STOP, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-3/hero3.gif")).toString()));
        typeImageMap.put(HERO_3 + WALK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-3/hero3-walk.gif")).toString()));
        typeImageMap.put(HERO_3 + LEFT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-3/hero3-attack-left.gif")).toString()));
        typeImageMap.put(HERO_3 + RIGHT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/heroes/type-3/hero3-attack-right.gif")).toString()));
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
        if (animationType == null) {
            animationType = Hero.WALK;
        }
        imageView.setLayoutX(getPosX());
        imageView.setLayoutY(getPosY());
        imageView.setImage(typeImageMap.get(getType() + getAnimationType()));
        imageView.setTranslateZ(1);

        if (!getName().equalsIgnoreCase(MY_HERO_FX.getName())) {
            healthRectangle.setLayoutX(getPosX());
            healthRectangle.setLayoutY(getPosY() - 10.0d);
            healthRectangle.setWidth(getWidth() * getHealth() / getMaxHealth());
            healthRectangle.setHeight(4);
            healthRectangle.setFill(Color.RED);

            damageRectangle.setLayoutX(getPosX());
            damageRectangle.setLayoutY(getPosY() - 5.0d);
            damageRectangle.setWidth(getWidth() * getDamage() / getMaxHealth() * 20);
            damageRectangle.setHeight(4);
            damageRectangle.setFill(Color.DARKVIOLET);
        }

        label.setText(getName());
        label.setPrefWidth(getWidth());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setLayoutX(getPosX());
        label.setLayoutY(getPosY() - 35);
        this.setAnimationType(STOP);
    }

    @Override
    public void addToPane(Pane pane) {
        if (!pane.getChildren().contains(imageView)) {
            pane.getChildren().add(damageRectangle);
            pane.getChildren().add(healthRectangle);
            pane.getChildren().add(label);
            pane.getChildren().add(imageView);
        }
    }

    @Override
    public void deleteFromPane(Pane pane) {
        if (pane.getChildren().contains(imageView)) {
            pane.getChildren().remove(imageView);
            pane.getChildren().remove(damageRectangle);
            pane.getChildren().remove(healthRectangle);
            pane.getChildren().remove(label);
        }
    }

}
