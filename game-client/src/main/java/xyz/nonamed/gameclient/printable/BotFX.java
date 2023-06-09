package xyz.nonamed.gameclient.printable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.gameclient.config.UserParam;
import xyz.nonamed.gameclient.handlers.BotHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static xyz.nonamed.dto.Hero.*;
import static xyz.nonamed.gameclient.controllers.GameViewController.botFXList;
import static xyz.nonamed.gameclient.controllers.GameViewController.staticGamePane;
import static xyz.nonamed.gameclient.controllers.StaticData.MY_HERO_FX;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class BotFX extends Bot implements AliveFx {

    public static Map<String, Image> typeImageMap = new HashMap<>();

    static {
        typeImageMap.put(BOT_1 + WALK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot1-walk.gif")).toString()));
        typeImageMap.put(BOT_1 + LEFT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot1-attack-left.gif")).toString()));
        typeImageMap.put(BOT_1 + RIGHT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot1-attack-right.gif")).toString()));

        typeImageMap.put(BOT_2 + WALK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot2-walk.gif")).toString()));
        typeImageMap.put(BOT_2 + LEFT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot2-walk.gif")).toString()));
        typeImageMap.put(BOT_2 + RIGHT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot2-walk.gif")).toString()));

        typeImageMap.put(BOT_3 + WALK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot3-walk.gif")).toString()));
        typeImageMap.put(BOT_3 + LEFT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot3-attack-left.gif")).toString()));
        typeImageMap.put(BOT_3 + RIGHT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot3-attack-right.gif")).toString()));

        typeImageMap.put(BOT_4 + WALK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot4-walk.gif")).toString()));
        typeImageMap.put(BOT_4 + LEFT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot4-attack-left.gif")).toString()));
        typeImageMap.put(BOT_4 + RIGHT_ATTACK, new Image(Objects.requireNonNull(BotFX.class.getResource("/xyz/nonamed/gameclient/images/bots/bot4-attack-right.gif")).toString()));
    }

    ImageView imageView = new ImageView();
    Rectangle healthRectangle = new Rectangle();
    Rectangle damageRectangle = new Rectangle();
    Label label = new Label();
    Circle circle = new Circle();

    public BotFX(Bot bot) {
        super(bot);
        setAnimationType(WALK);
        addDamageHandler();
    }

    private void addDamageHandler() {
        BotHandler botHandler = new BotHandler();
        imageView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.out.println("bot got damage");
                setHealth(getHealth() - MY_HERO_FX.getDamage());
                Bot bot = botHandler.postUpdateBot(this, UserParam.USERNAME, UserParam.SESSION_CODE);

                if (bot == null) {
                    deleteFromPane(staticGamePane);
                    botFXList.remove(this);
                } else {
                    this.posX = bot.getPosX();
                    this.posY = bot.getPosY();
                    this.health = bot.getHealth();
                    print(staticGamePane);
                }
            }

        });
    }


    public void print(Pane pane) {
        imageView.setImage(typeImageMap.get(getType() + getAnimationType()));
        imageView.setLayoutX(getPosX());
        imageView.setLayoutY(getPosY());
        imageView.setTranslateZ(1);

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

        circle.setRadius(getMinDistanceToActivate());
        circle.setLayoutX(getPosX());
        circle.setLayoutY(getPosY());
        circle.setCenterX(getWidth() / 2);
        circle.setCenterY(getHeight() / 2);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        this.setAnimationType(WALK);
    }

    @Override
    public void addToPane(Pane pane) {
        if (!pane.getChildren().contains(imageView)) {
            pane.getChildren().add(damageRectangle);
            pane.getChildren().add(healthRectangle);
            pane.getChildren().add(label);
            pane.getChildren().add(circle);
            pane.getChildren().add(imageView);
        }
    }

    @Override
    public void deleteFromPane(Pane pane) {
        pane.getChildren().remove(imageView);
        pane.getChildren().remove(damageRectangle);
        pane.getChildren().remove(healthRectangle);
        pane.getChildren().remove(label);
        pane.getChildren().remove(circle);
    }

}
