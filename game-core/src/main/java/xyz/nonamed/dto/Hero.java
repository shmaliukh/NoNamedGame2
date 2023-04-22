package xyz.nonamed.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    public static final double DEFAULT_HERO_POS_X = 200;
    public static final double DEFAULT_HERO_POS_Y = 200;

    public static final double DEFAULT_HERO_HEIGHT = 128;
    public static final double DEFAULT_HERO_WIDTH = 128;

    public static final double DEFAULT_HERO_MAX_HEALTH = 1000;
    public static final double DEFAULT_HERO_DAMAGE = 10;
    public static final double DEFAULT_HERO_SPEED = 50;


    public static final String HERO_1 = "hero_1";
    public static final String HERO_2 = "hero_2";
    public static final String HERO_3 = "hero_3";
    public static final String DEFAULT_HERO_TYPE = HERO_1;

    public static final List<String> heroTypeList = Arrays.asList(
            HERO_1,
            HERO_2,
            HERO_3
    );

    public static final int DEFAULT_HERO_DISTANCE_TO_ACTIVATE = 200;

    public static final String STOP = "stop";
    public static final String WALK = "walk";
    public static final String LEFT_ATTACK = "left_attack";
    public static final String RIGHT_ATTACK = "right_attack";

    public Hero(Hero hero) {
        this.id = hero.id;
        this.name = hero.name;
        this.sessionCode = hero.sessionCode;
        this.posX = hero.posX;
        this.posY = hero.posY;
        this.height = hero.height;
        this.width = hero.width;
        this.health = hero.health;
        this.maxHealth = hero.maxHealth;
        this.damage = hero.damage;
        this.speed = hero.speed;
        this.minDistanceToActivate = hero.minDistanceToActivate;
        this.type = hero.type;
        this.color = hero.color;
        this.animationType = hero.animationType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String name;
    public String sessionCode;

    public double posX = DEFAULT_HERO_POS_X;
    public double posY = DEFAULT_HERO_POS_Y;

    public double height = DEFAULT_HERO_HEIGHT;
    public double width = DEFAULT_HERO_WIDTH;

    public double health = DEFAULT_HERO_MAX_HEALTH;
    public double maxHealth = DEFAULT_HERO_MAX_HEALTH;
    public double damage = DEFAULT_HERO_DAMAGE;
    public double speed = DEFAULT_HERO_SPEED;
    public double minDistanceToActivate = DEFAULT_HERO_DISTANCE_TO_ACTIVATE;

    public String type = DEFAULT_HERO_TYPE;
    public String color;
    public String animationType = STOP;

}
