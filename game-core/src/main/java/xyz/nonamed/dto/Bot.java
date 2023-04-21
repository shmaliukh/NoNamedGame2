package xyz.nonamed.dto;

import jakarta.persistence.*;
import lombok.*;
import xyz.nonamed.utils.DataUtils;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bot {

    // FIXME extract 'alive' class or interface (bot and hero are similar)

    public static final double DEFAULT_BOT_POS_X = 200;
    public static final double DEFAULT_BOT_POS_Y = 200;

    public static final double DEFAULT_BOT_HEIGHT = 60;
    public static final double DEFAULT_BOT_WIDTH = 40;

    public static final double DEFAULT_BOT_MAX_HEALTH = 500;
    public static final double DEFAULT_BOT_DAMAGE = DEFAULT_BOT_MAX_HEALTH / 100;
    public static final double DEFAULT_BOT_SPEED = 1;
    public static final double DEFAULT_BOT_DISTANCE_TO_ACTIVATE = 100;

    public static final String BOT_1 = "bot_1";
    public static final String BOT_2 = "bot_2";
    public static final String BOT_3 = "bot_3";
    public static final String BOT_4 = "bot_4";
    public static final String BOT_5 = "bot_5";
    public static final String BOT_6 = "bot_6";
    public static final String DEFAULT_BOT_TYPE = BOT_6;

    public static List<String> botTypeList = Arrays.asList(
            BOT_1,
            BOT_2,
            BOT_3,
            BOT_4,
            BOT_5,
            BOT_6
    );

    public Bot(Bot bot) {
        this.id = bot.id;
        this.name = bot.name;
        this.sessionCode = bot.sessionCode;
        this.posX = bot.posX;
        this.posY = bot.posY;
        this.height = bot.height;
        this.width = bot.width;
        this.health = bot.health;
        this.maxHealth = bot.maxHealth;
        this.damage = bot.damage;
        this.speed = bot.speed;
        this.minDistanceToActivate = bot.minDistanceToActivate;
        this.type = bot.type;
        this.color = bot.color;
        this.animationType = bot.animationType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    public String name = DataUtils.getRandomName();
    public String sessionCode;
    public String userName = null; // who delegate actions

    public double posX = DEFAULT_BOT_POS_X;
    public double posY = DEFAULT_BOT_POS_Y;

    public double height = DEFAULT_BOT_HEIGHT;
    public double width = DEFAULT_BOT_WIDTH;

    public double health = DEFAULT_BOT_MAX_HEALTH;
    public double maxHealth = DEFAULT_BOT_MAX_HEALTH;
    public double damage = DEFAULT_BOT_DAMAGE;
    public double speed = DEFAULT_BOT_SPEED;
    public double minDistanceToActivate = DEFAULT_BOT_DISTANCE_TO_ACTIVATE;

    public String type = DEFAULT_BOT_TYPE;
    public String color;
    public String animationType;

}
